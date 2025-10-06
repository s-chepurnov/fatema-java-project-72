package gg.jte.generated.ondemand.layout;
import hexlet.code.util.NamedRoutes;
import hexlet.code.dto.BasePage;
import gg.jte.Content;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,62,62,62,62,62,62,62,62,62,62,63,63,63,63,63,63,63,63,63,64,64,64,64,64,64,64,64,64,69,69,70,70,70,71,71,72,72,72,75,75,77,77,77,79,79,81,81,81,3,4,5,5,5,5};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content, Content footer, BasePage page) {
		jteOutput.writeContent("\n<style>\n     .half-screen {\n          margin-left: 0px;\n          background-color: #303030;\n          height: 5%;\n     }\n\n     nav a {\n          margin-right: 20px;\n          color: white;\n     }\n\n     nav .page {\n          margin-left: 20px;\n          font-size: 20px;\n          font-weight: bold;\n     }\n\n     nav .main {\n          font-size: 16px;\n     }\n\n     nav .web {\n          font-size: 16px;\n     }\n\n      .message {\n          margin-top: 0px;\n          margin-left: 0px;\n          margin-bottom: 0px;\n          color: #072C63;\n          background-color: #909590;\n          height: 40px\n      }\n</style>\n\n<!doctype html>\n<html lang=\"en\">\n    <head>\n        <meta charset=\"utf-8\" />\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n        <title>java-project-72</title>\n        <link rel=\"stylesheet\" href=\"/assets/build.css\">\n\n        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n        integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n\n        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"\n        integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\"\n        crossorigin=\"anonymous\"></script>\n\n    </head>\n    <body>\n     <header class=\"half-screen\">\n          <nav>\n               <a");
		var __jte_html_attribute_0 = NamedRoutes.rootPath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" class=\"page\">Page analyzer</a>\n               <a");
		var __jte_html_attribute_1 = NamedRoutes.rootPath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_1);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" class=\"main\">Main</a>\n               <a");
		var __jte_html_attribute_2 = NamedRoutes.urlsPath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_2);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(" class=\"web\">Websites</a>\n          </nav>\n    </header>\n\n    <main class=\"half-screen\">\n         ");
		if (page != null && page.getFlash() != null) {
			jteOutput.writeContent("\n              <p class=\"message\">");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(page.getFlash());
			jteOutput.writeContent("</p>\n         ");
		}
		jteOutput.writeContent("\n         ");
		jteOutput.setContext("main", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n    </main>\n\n         ");
		if (footer != null) {
			jteOutput.writeContent("\n              <div class=\"footer\">\n                   ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(footer);
			jteOutput.writeContent("\n              </div>\n         ");
		}
		jteOutput.writeContent("\n    </body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		Content footer = (Content)params.getOrDefault("footer", null);
		BasePage page = (BasePage)params.getOrDefault("page", null);
		render(jteOutput, jteHtmlInterceptor, content, footer, page);
	}
}
