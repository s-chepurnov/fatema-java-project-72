package gg.jte.generated.ondemand.urls;
import hexlet.code.util.NamedRoutes;
import hexlet.code.dto.BuildUrlPage;
public final class JtebuildGenerated {
	public static final String JTE_NAME = "urls/build.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,8,8,10,10,13,13,14,14,15,15,15,16,16,17,17,20,20,22,22,22,22,22,22,22,22,22,35,35,35,35,35,35,35,35,35,39,39,39,39,39,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, BuildUrlPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n\r\n    ");
				if (page.getErrors() != null) {
					jteOutput.writeContent("\r\n                <div class=\"mb-3\">\r\n                    <ul>\r\n                        ");
					for (var validator : page.getErrors().values()) {
						jteOutput.writeContent("\r\n                            ");
						for (var error : validator) {
							jteOutput.writeContent("\r\n                                <li>");
							jteOutput.setContext("li", null);
							jteOutput.writeUserContent(error.getMessage());
							jteOutput.writeContent("</li>\r\n                            ");
						}
						jteOutput.writeContent("\r\n                        ");
					}
					jteOutput.writeContent("\r\n                    </ul>\r\n                </div>\r\n            ");
				}
				jteOutput.writeContent("\r\n\r\n   <form");
				var __jte_html_attribute_0 = NamedRoutes.urlsPath();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\">\r\n   <p>\r\n     <h1>Website analyzer</h1>\r\n     <h3>Check websites for free for SEO suitability</h3>\r\n   </p>\r\n   <label>\r\n      <input type=\"text\" required name=\"url\" placeholder=\"web link\" />\r\n   </label>\r\n      <input type=\"submit\" value=\"Check\" />\r\n   <p>\r\n      Example: https://www.example.com\r\n   </p>\r\n   <p>\r\n     <a");
				var __jte_html_attribute_1 = NamedRoutes.urlsPath();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
					jteOutput.writeContent(" href=\"");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(__jte_html_attribute_1);
					jteOutput.setContext("a", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">Pages</a>\r\n   </p>\r\n   </form>\r\n\r\n   ");
			}
		}, null, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		BuildUrlPage page = (BuildUrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
