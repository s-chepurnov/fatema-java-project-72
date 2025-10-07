package hexlet.code.controller;

import hexlet.code.dto.BuildUrlPage;
import hexlet.code.dto.UrlCheckService;
import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static hexlet.code.repository.UrlCheckRepository.saveUrlCheck;
import static io.javalin.rendering.template.TemplateUtil.model;

@Slf4j
public final class UrlsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlsController.class);
    private static final String FLASH = "flash";

    private UrlsController() {
    }
    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();

        var url = UrlRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));

        List<UrlCheck> checksList = UrlCheckRepository.findAllChecksByUrlId(id);

        UrlPage urlPage = new UrlPage(url, checksList);

        Map<String, Object> model = new HashMap<>();
        model.put("page", urlPage);

        ctx.render("urls/show.jte", model);
    }
    @SuppressWarnings("java:S1192")
    public static void index(Context ctx) {
        try {
            var urls = UrlRepository.getEntities();
            for (Url url : urls) {
                Optional<UrlCheck> lastCheck = UrlCheckRepository.findLastCheckByUrlId(url.getId());
                lastCheck.ifPresent(url::setLastCheck);
            }
            var page = new UrlsPage(urls);
            page.setFlash(ctx.consumeSessionAttribute(FLASH));
            ctx.render("urls/index.jte", model("page", page));
        } catch (SQLException e) {
            LOGGER.error("Server error occurred", e);
            ctx.status(500).result("Server error occurred");
        }
    }

    public static void build(Context ctx) {
        var page = new BuildUrlPage();
        page.setFlash(ctx.consumeSessionAttribute(FLASH));
        ctx.render("urls/build.jte", model("page", page));
    }

    @SuppressWarnings("java:S1192")
    public static void create(Context ctx) throws SQLException {
        String name = ctx.formParam("url");

        if (!name.startsWith("http://") && !name.startsWith("https://")) {
            ctx.sessionAttribute(FLASH, "URL is not corrected");
            ctx.redirect(NamedRoutes.rootPath());
            return;
        }

        try {
            URI uri = new URI(name);
            URL url = uri.toURL();

            String baseUrl = url.getProtocol() + "://" + url.getHost();
            if (url.getPort() != -1) {
                baseUrl += ":" + url.getPort();
            }

            LOGGER.info("Base URL: {}", baseUrl);

            if (UrlRepository.existsByName(baseUrl)) {
                ctx.sessionAttribute(FLASH, "Webpage already exists");
                ctx.redirect(NamedRoutes.rootPath());
                return;
            } else {
                Url urlEntity = new Url(baseUrl);
                UrlRepository.save(urlEntity);
                ctx.sessionAttribute(FLASH, "Url is successfully created.");
            }
        } catch (URISyntaxException | MalformedURLException e) {
            ctx.sessionAttribute(FLASH, "URL is not corrected");
            ctx.redirect(NamedRoutes.rootPath());
            return;
        }

        ctx.redirect(NamedRoutes.urlsPath());
    }

    @SuppressWarnings("java:S106")
    public static void createCheck(Context ctx) throws SQLException {
        Long id = Long.parseLong(ctx.pathParam("id"));

        Optional<Url> optionalUrl;
        try {
            optionalUrl = UrlRepository.findById(id);
        } catch (SQLException e) {
            LOGGER.error("Error in database", e);
            ctx.status(500).result("Error in database");
            return;
        }

        if (optionalUrl.isEmpty()) {
            ctx.status(404).result("URL is not found");
            return;
        }

        Url url = optionalUrl.get();
        UrlCheckService urlCheckService = new UrlCheckService();

        try {
            UrlCheck newCheck = urlCheckService.performCheck(url.getName());
            newCheck.setUrlId(id);
            saveUrlCheck(newCheck);
            ctx.redirect("/urls/" + id);
        } catch (Exception e) {
            LOGGER.error("Error during verification URL", e);
            ctx.status(500).result("Error during verification URL");
        }
    }
}
