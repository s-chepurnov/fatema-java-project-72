package hexlet.code;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;
import hexlet.code.controller.UrlsController;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Properties;
import java.util.stream.Collectors;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;
import hexlet.code.repository.BaseRepository;


@Slf4j
public class App {
    @SuppressWarnings("java:S1144")
    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.valueOf(port);
    }

    private static String readResourceFile(String fileName) throws IOException {
        var inputStream = App.class.getClassLoader().getResourceAsStream(fileName);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        return TemplateEngine.create(codeResolver, ContentType.Html);
    }

    @SuppressWarnings("java:S1144")
    private static HikariDataSource createDataSource() throws IOException {
        Properties properties = new Properties();
        String environment = System.getProperty("app.env", "dev");

        try (InputStream input = App.class.getClassLoader()
                .getResourceAsStream("application-" + environment + ".properties")) {
            if (input == null) {
                throw new FileNotFoundException("Configuration file 'application-"
                        + environment + ".properties' not found in classpath");
            }
            properties.load(input);
        }

        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);

        return new HikariDataSource(config);
    }

    public static Javalin getApp() throws IOException, SQLException {
        HikariDataSource dataSource = createDataSource();
        var sql = readResourceFile("schema.sql");

        log.info(sql);
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            //config.staticFiles.add("/assets", Location.CLASSPATH);
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });



        app.get(NamedRoutes.rootPath(), UrlsController::build);
        app.post(NamedRoutes.urlsPath(), UrlsController::create);

        app.get(NamedRoutes.urlsPath(), UrlsController::index);
        app.get(NamedRoutes.urlPath("{id}"), UrlsController::show);
        app.post("/urls/{id}/checks", UrlsController::createCheck);

        return app;
    }
    @SuppressWarnings("java:S1144")
    public static void main(String[] args) throws SQLException, IOException {
        var app = getApp();
        app.start(getPort());
    }
}
