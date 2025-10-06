package gg.jte.generated.ondemand.urls;
import hexlet.code.util.NamedRoutes;
import hexlet.code.dto.BuildUrlPage;
public final class JtebuildGenerated {
	public static final String JTE_NAME = "urls/build.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,65,65,69,69,71,71,74,74,75,75,76,76,76,77,77,78,78,81,81,83,83,83,83,83,83,83,83,83,95,95,95,97,97,97,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, BuildUrlPage page) {
		jteOutput.writeContent("\n<style>\n     body, html {\n          background-color: #4e5d94;\n\n     }\n\n     .full {\n          margin-top: 0px;\n          margin-left: 0px;\n         background-color: #4e5d94;\n         padding-bottom: 100px\n     }\n\n     .page-analyze {\n          margin-left: 500px;\n          font-size: 100px;\n          font-weight: bold;\n          color: white;\n     }\n\n     .check {\n          margin-left: 500px;\n          font-size: 20px;\n          color: white;\n     }\n\n     .url-name {\n          margin-left: 500px;\n          font-size: 30px;\n          color: green;\n          background-color: white;\n          height: 50px;\n          width: 1000px;\n     }\n\n     .checking {\n          margin-left: 50px;\n          font-size: 30px;\n          font-weight: bold;\n          color: white;\n          background-color: #7289da;\n          height: 50px;\n          width: 200px;\n     }\n\n     .example {\n          margin-left: 500px;\n          font-size: 15px;\n          color: white;\n     }\n</style>\n\n<!DOCTYPE html>\n<html lang=\"ru\">\n<head>\n  <meta charset=\"UTF-8\">\n  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n  <title>Page analyzer</title>\n</head>\n<body>\n\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n\n    ");
				if (page.getErrors() != null) {
					jteOutput.writeContent("\n       <div class=\"mb-3\">\n          <ul>\n              ");
					for (var validator : page.getErrors().values()) {
						jteOutput.writeContent("\n                 ");
						for (var error : validator) {
							jteOutput.writeContent("\n                    <li>");
							jteOutput.setContext("li", null);
							jteOutput.writeUserContent(error.getMessage());
							jteOutput.writeContent("</li>\n                 ");
						}
						jteOutput.writeContent("\n              ");
					}
					jteOutput.writeContent("\n          </ul>\n       </div>\n    ");
				}
				jteOutput.writeContent("\n\n   <form");
				var __jte_html_attribute_0 = NamedRoutes.urlsPath();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\">\n   <div class=\"full\">\n        <p class=\"page-analyze\">Page analyzer</p>\n        <p class=\"check\">Check websites for free for SEO suitability</p>\n       <label>\n       <input class=\"url-name\" type=\"text\" required name=\"url\" placeholder=\"web link\" />\n       </label>\n       <input class=\"checking\" type=\"submit\" value=\"Check\" />\n       <p class=\"example\">Example: https://www.example.com</p>\n   </div>\n   </form>\n\n     ");
			}
		}, null, page);
		jteOutput.writeContent("\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		BuildUrlPage page = (BuildUrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
