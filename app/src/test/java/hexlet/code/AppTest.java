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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
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
        //testUrl = new Url(mockWebServer.url("/").toString());
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

    @AfterAll
    static void tearDown() throws IOException {
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
            //String fixture = mockWebServer.url("/").toString();
            String urlForAdding = "url=" + fixture;
            var response = client.post(NamedRoutes.urlsPath(), urlForAdding);
            assertThat(response).isNotNull();
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains(fixture);
        });
    }

    @Test
    void testUrlCheckCreation() throws SQLException, IOException {
        var mockUrlString = mockWebServer.url("/").toString().replaceAll("/$", "");
        Url mockUrl = new Url(mockUrlString);
        mockWebServer.enqueue(new MockResponse().setBody(readFileFixtures("mock_response.html")));
        UrlRepository.save(mockUrl);
        System.out.println("Save URL: " + mockUrl.getName());
        Long idInBase = mockUrl.getId();

        JavalinTest.test(app, (server, client) -> {
            var response = client.post(NamedRoutes.urlChecksPath(idInBase));
            assertThat(response.code()).isEqualTo(200);

            response = client.get(NamedRoutes.urlPath(idInBase));
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string())
                    .contains("statements of great people");
            UrlCheck check = UrlCheckRepository.getLastChecksForAllUrls().get(1L);
            assertThat(check.getTitle()).isEqualTo("Test page");
            assertThat(check.getH1()).isEqualTo("Do not expect a miracle, miracles yourself!");

        });
    }

    @Test
    void testUrlIDNamePage() {
        JavalinTest.test(app, (server, client) -> {
            String fixture = "https://www.google.com";
            //String fixture = mockWebServer.url("/").toString();
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
