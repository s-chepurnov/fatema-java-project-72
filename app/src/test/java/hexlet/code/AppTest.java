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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AppTest {
    private static MockWebServer mockWebServer;
    private Javalin app;
    private Url testUrl;
    private UrlCheck testUrlCheck;

    static String readFileFixtures(String fileName) throws IOException {
        Path filePath = Paths.get("src/test/resources/", fileName);
        return new String(Files.readAllBytes(filePath));
    }

    @BeforeAll
    static void startMockServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.enqueue(new MockResponse().setBody(readFileFixtures("mock_response.html")));
        mockWebServer.start();
    }

    @BeforeEach
    final void setUp() throws IOException, SQLException {
        app = App.getApp();

        testUrl = new Url("https://en.hexlet.io");
        UrlRepository.save(testUrl);

        testUrlCheck = new UrlCheck(
                200,
                "Test Page Title",
                "Test H1 Heading",
                "Test Description",
                testUrl.getId()
        );
        UrlCheckRepository.saveUrlCheck(testUrlCheck);
    }

    @AfterAll
    public static void tearDown() throws IOException {
        mockWebServer.shutdown();
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
            String urlForAdding = "url=" + fixture;
            var response = client.post(NamedRoutes.urlsPath(), urlForAdding);
            assertThat(response).isNotNull();
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains(fixture);
        });
    }

    @Test
    void testUrlIDNamePage() {
        JavalinTest.test(app, (server, client) -> {
            String fixture = "https://www.google.com";
            var url = new Url("https://www.google.com");
            UrlRepository.save(url);
            var response = client.get("/urls/" + url.getId());
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains("https://www.google.com");
            assertFalse(UrlRepository.findByName("https://www.google.com").isEmpty());
        });
    }

    @Test
    void testShowUrlWithMultipleChecks() throws SQLException {
        UrlCheck secondCheck = new UrlCheck(
                200,
                "Second Page Title",
                "Second H1",
                "Second Description",
                testUrl.getId()
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
    void testUrlCheckCreation() throws SQLException {
        var mockUrlString = mockWebServer.url("/").toString();
        Url mockUrl = new Url(mockUrlString);
        UrlRepository.save(mockUrl);

        var idInBase = mockUrl.getId();

        JavalinTest.test(app, (server, client) -> {
            var response = client.post(NamedRoutes.urlsCheckPath(mockUrl.getId()));
            assertThat(response.code()).isEqualTo(200);

            response = client.get(NamedRoutes.urlPath(mockUrl.getId()));
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains("Test Description");
            UrlCheck check = UrlCheckRepository.getLastChecksForAllUrls().get(1L);
            assertThat(check.getTitle()).isEqualTo("Test Page Title");
            assertThat(check.getH1()).isEqualTo("Test H1 Heading");

        });
    }

    @Test
    void testUrlNotFound() throws Exception {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/urls/999999");
            assertThat(response.code()).isEqualTo(404);
        });
    }
}
