package gg.jte.generated.ondemand.layout;
import hexlet.code.util.NamedRoutes;
import hexlet.code.dto.BasePage;
import gg.jte.Content;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,24,24,24,25,25,25,26,26,27,27,27,28,28,30,30,30,32,32,34,34,34,3,4,5,5,5,5};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content, Content footer, BasePage page) {
		jteOutput.writeContent("\r\n\r\n<!doctype html>\r\n<html lang=\"en\">\r\n    <head>\r\n        <meta charset=\"utf-8\" />\r\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\r\n        <title>java-project-72</title>\r\n\r\n          <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\r\n          integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\r\n\r\n          <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"\r\n          integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\"\r\n          crossorigin=\"anonymous\"></script>\r\n\r\n    </head>\r\n    <body>\r\n        ");
		if (page != null && page.getFlash() != null) {
			jteOutput.writeContent("\r\n            <p>");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(page.getFlash());
			jteOutput.writeContent("</p>\r\n        ");
		}
		jteOutput.writeContent("\r\n       ");
		jteOutput.setContext("body", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\r\n        ");
		if (footer != null) {
			jteOutput.writeContent("\r\n        <div class=\"footer\">\r\n            ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(footer);
			jteOutput.writeContent("\r\n        </div>\r\n    ");
		}
		jteOutput.writeContent("\r\n    </body>\r\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		Content footer = (Content)params.getOrDefault("footer", null);
		BasePage page = (BasePage)params.getOrDefault("page", null);
		render(jteOutput, jteHtmlInterceptor, content, footer, page);
	}
}
