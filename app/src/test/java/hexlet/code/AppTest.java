package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AppTest {
    private static MockWebServer mockWebServer;
    private static Javalin app;
    private static String mockUrl;
    private Url testUrl;
    private UrlCheck testUrlCheck;

    static String readFileFixtures(String fileName) throws IOException {
        Path filePath = Paths.get("src/test/resources/", fileName);
        return new String(Files.readAllBytes(filePath));
    }

    @BeforeEach
    final void setup() throws IOException, SQLException {
        System.setProperty("JDBC_DATABASE_URL", "jdbc:h2:mem:project");
        app = App.getApp();

        testUrl = new Url("https://en.hexlet.io");
        UrlRepository.save(testUrl);

        testUrlCheck = new UrlCheck(
                testUrl.getId(),
                200,
                "Test page",
                "Do not expect a miracle, miracles yourself!",
                "statements of great people"
        );
        UrlCheckRepository.saveUrlCheck(testUrlCheck);
    }


    @Test
    void testRootPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Page analyzer");
        });
    }

    @Test
    void testUrlCreation() {
        JavalinTest.test(app, (server, client) -> {
            String fixture = "https://www.google.com";
            Url urlObj = new Url(fixture);
            UrlRepository.save(urlObj);
            String urlForAdding = "url=" + fixture;
            var response = client.post(NamedRoutes.urlsPath(), urlForAdding);

            assertThat(response).isNotNull();
            assertThat(response.code()).isEqualTo(200);

            var responseUrl = client.get(NamedRoutes.urlsPath());
            assertThat(responseUrl.code()).isEqualTo(200);
            assertThat(responseUrl.body().string()).contains(fixture);

            var urlByName = UrlRepository.getUrlByName(fixture);
            assertThat(urlByName).isNotNull();
            assertThat(urlByName.get("name").toString()).isEqualTo(fixture);
        });
    }

    @Test
    void testUrlCheckCreation() throws SQLException, IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody(readFileFixtures("mock_response.html")));
        mockWebServer.start();
        mockUrl = mockWebServer.url("/").toString().replaceAll("/$", "");
        Url orlObj = new Url(mockUrl);
        UrlRepository.save(orlObj);

        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=" + mockUrl;
            var response = client.post("/urls", requestBody);

            assertThat(response.code()).isEqualTo(200);

            var actualUrl = UrlRepository.getUrlByName(mockUrl);
            assertThat(actualUrl).isNotNull();
            System.out.println("\n!!!!!");
            System.out.println(actualUrl);

            System.out.println("\n");
            assertThat(actualUrl.get("name").toString()).isEqualTo(mockUrl);

            client.post(NamedRoutes.urlChecksPath(orlObj.getId()));
            assertThat(client.get(NamedRoutes.urlPath(orlObj.getId())).code()).isEqualTo(200);

            mockWebServer.shutdown();

            UrlCheck actualCheck = UrlCheckRepository.getLastChecksForAllUrls().get(1L);
            assertThat(actualCheck).isNotNull();
            assertThat(actualCheck.getTitle()).isEqualTo("Test page");
            assertThat(actualCheck.getH1()).isEqualTo("Do not expect a miracle, miracles yourself!");
            assertThat(actualCheck.getDescription()).isEqualTo("statements of great people");
        });
    }

    @Test
    void testUrlIDNamePage() {
        JavalinTest.test(app, (server, client) -> {
            String fixture = "https://www.google.com";
            var url = new Url(fixture);
            UrlRepository.save(url);
            var response = client.get("/urls/" + url.getId());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains(fixture);
            assertFalse(UrlRepository.findByName(fixture).isEmpty());
        });
    }

    @Test
    void testShowUrlWithMultipleChecks() throws SQLException {
        UrlCheck secondCheck = new UrlCheck(
                testUrl.getId(),
                200,
                "Second Page Title",
                "Second H1",
                "Second Description"
        );
        UrlCheckRepository.saveUrlCheck(secondCheck);

        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlPath(testUrl.getId()));
            String body = response.body().string();

            assertThat(body)
                    .contains(testUrlCheck.getTitle())
                    .contains(secondCheck.getTitle());

            assertThat(body.indexOf(testUrlCheck.getTitle()))
                    .isLessThan(body.indexOf(secondCheck.getTitle()));
        });
    }

    @Test
    void testShowUrl() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlsPath());
            assertThat(response.code()).isEqualTo(200);
            String body = response.body().string();
            assertThat(body).contains(testUrl.getName());
            assertThat(body).contains(String.valueOf(testUrlCheck.getStatusCode()));
        });
    }

    @Test
    void testUrlNotFound() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/999999");
            assertThat(response.code()).isEqualTo(404);
        });
    }
}
