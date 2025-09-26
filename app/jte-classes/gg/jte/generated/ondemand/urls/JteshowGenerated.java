package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.UrlPage;
import java.time.format.DateTimeFormatter;
import hexlet.code.util.NamedRoutes;
public final class JteshowGenerated {
	public static final String JTE_NAME = "urls/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,4,4,4,7,7,7,7,8,8,8,9,9,9,11,11,11,11,11,11,11,11,11,14,14,14,14,14,14,14,14,14,16,16,16,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\r\n<main>\r\n    <div>ID: ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(page.getUrl().getId());
		jteOutput.writeContent("</div>\r\n    <div>Name: ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(page.getUrl().getName());
		jteOutput.writeContent("</div>\r\n    <div>Created At: ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(page.getUrl().getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
		jteOutput.writeContent("</div>\r\n    <div>\r\n        <a");
		var __jte_html_attribute_0 = NamedRoutes.urlsPath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_0);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">Return to urls list</a>\r\n    </div>\r\n    <div>\r\n       <a");
		var __jte_html_attribute_1 = NamedRoutes.rootPath();
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(__jte_html_attribute_1);
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">return to main page</a>\r\n    </div>\r\n</main>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
