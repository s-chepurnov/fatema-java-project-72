package gg.jte.generated.ondemand.urls;
import hexlet.code.util.NamedRoutes;
import hexlet.code.dto.UrlsPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "urls/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,8,8,10,10,11,11,13,13,13,13,13,13,13,13,13,13,13,13,15,15,17,17,19,19,22,22,22,22,22,22,22,22,22,26,26,26,26,26,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n\r\n    ");
				if (page.getUrls().size() > 0) {
					jteOutput.writeContent("\r\n          ");
					for (var url : page.getUrls()) {
						jteOutput.writeContent("\r\n              <p>\r\n              <a");
						var __jte_html_attribute_0 = NamedRoutes.urlPath(url.getId());
						if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
							jteOutput.writeContent(" href=\"");
							jteOutput.setContext("a", "href");
							jteOutput.writeUserContent(__jte_html_attribute_0);
							jteOutput.setContext("a", null);
							jteOutput.writeContent("\"");
						}
						jteOutput.writeContent(">");
						jteOutput.setContext("a", null);
						jteOutput.writeUserContent(url.getName());
						jteOutput.writeContent("</a>\r\n              </p>\r\n          ");
					}
					jteOutput.writeContent("\r\n\r\n          ");
				} else {
					jteOutput.writeContent("\r\n            <p>No urls available.</p>\r\n    ");
				}
				jteOutput.writeContent("\r\n\r\n    <p>\r\n      <a");
				var __jte_html_attribute_1 = NamedRoutes.rootPath();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
					jteOutput.writeContent(" href=\"");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(__jte_html_attribute_1);
					jteOutput.setContext("a", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">return to main page</a>\r\n    </p>\r\n\r\n\r\n     ");
			}
		}, null, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
