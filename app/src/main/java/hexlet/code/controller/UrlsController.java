package hexlet.code.controller;

import hexlet.code.dto.BuildUrlPage;
import hexlet.code.service.UrlCheckService;
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

import java.net.URI;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import static hexlet.code.repository.UrlCheckRepository.saveUrlCheck;
import static io.javalin.rendering.template.TemplateUtil.model;

@Slf4j
public final class UrlsController {
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
            log.error("Server error occurred", e);
            ctx.status(500).result("Server error occurred");
        }
    }

    public static void build(Context ctx) {
        String name = ctx.formParam("url");
        var page = new BuildUrlPage();
        page.setFlash(ctx.consumeSessionAttribute(FLASH));
        page.setName(name);
        ctx.render("urls/build.jte", model("page", page));
    }

    @SuppressWarnings("java:S1192")
    public static void create(Context ctx) throws SQLException {
        var inputUrl = ctx.formParam("url");
        URI parsedUrl;
        try {
            parsedUrl = new URI(inputUrl);
        } catch (Exception e) {
            ctx.sessionAttribute("flash", "URL is not corrected");
            ctx.sessionAttribute("flash-type", "danger");
            ctx.redirect(NamedRoutes.rootPath());
            return;
        }

        String normalizedUrl = String
                .format(
                        "%s://%s%s",
                        parsedUrl.getScheme(),
                        parsedUrl.getHost(),
                        parsedUrl.getPort() == -1 ? "" : ":" + parsedUrl.getPort()
                )
                .toLowerCase();

        Url url = UrlRepository.findByName(normalizedUrl).orElse(null);

        if (url != null) {
            ctx.sessionAttribute("flash", "Webpage already exists");
            ctx.sessionAttribute("flash-type", "info");
        } else {
            Url newUrl = new Url(normalizedUrl);
            UrlRepository.save(newUrl);
            ctx.sessionAttribute("flash", "Url is successfully created.");
            ctx.sessionAttribute("flash-type", "success");
        }

        ctx.redirect("/urls");
    }

    @SuppressWarnings("java:S106")
    public static void createCheck(Context ctx) throws SQLException {
        Long id = Long.parseLong(ctx.pathParam("id"));

        Optional<Url> optionalUrl;
        try {
            optionalUrl = Optional.ofNullable(UrlRepository.findById(id)
                    .orElseThrow(() -> new NotFoundResponse("Page not found")));
        } catch (SQLException e) {
            log.error("Error in database", e);
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
            log.error("Error during verification URL", e);
            ctx.status(500).result("Error during verification URL");
        }
    }
}
