package hexlet.code.dto;

import hexlet.code.model.UrlCheck;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



public class UrlCheckService {
    private static final String DESCRIPTION = "meta[name=description]";
    public UrlCheck performCheck(String url) throws Exception {
        HttpResponse<String> response = Unirest.get(url).asString();
        int statusCode = response.getStatus();
        Document doc = Jsoup.parse(response.getBody());

        @SuppressWarnings("java:S106")
        String title = !doc.title().isEmpty() ? doc.title() : "No title";
        @SuppressWarnings("java:S106")
        String h1 = doc.select("h1").isEmpty() ? "No h1" : doc.select("h1").text();
        @SuppressWarnings("java:S106")
        String description = doc.select(DESCRIPTION).isEmpty()
                ? "No description" : doc.select(DESCRIPTION).attr("content");

        UrlCheck urlCheck = new UrlCheck();
        urlCheck.setStatusCode(statusCode);
        urlCheck.setTitle(title);
        urlCheck.setH1(h1);
        urlCheck.setDescription(description);

        return urlCheck;
    }
}
