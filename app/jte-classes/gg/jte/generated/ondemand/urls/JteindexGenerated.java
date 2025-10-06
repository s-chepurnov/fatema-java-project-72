package gg.jte.generated.ondemand.urls;
import hexlet.code.util.NamedRoutes;
import java.time.format.DateTimeFormatter;
import hexlet.code.dto.UrlsPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "urls/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,6,6,9,9,52,52,65,65,67,67,67,68,68,68,68,68,68,68,68,68,68,68,68,69,71,71,71,71,72,73,73,73,75,75,79,79,81,81,83,83,83,84,84,84,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\n\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n\n<style>\n     .full-table {\n          margin-top: 0px;\n          margin-left: 0px;\n          background-color: #2B3D5B;\n          padding-bottom: 100px\n     }\n\n     table {\n          margin-left: 500px;\n          width: 50%;\n          border-collapse: collapse;\n     }\n\n     th, td {\n          border: 1px solid #000;\n          padding: 5px;\n     }\n\n     th {\n          background-color: white;\n     }\n\n     td {\n          color: white;\n     }\n\n    .websites {\n         margin-left: 500px;\n         font-size: 60px;\n         font-weight: bold;\n         color: white;\n    }\n\n    .not-available {\n         margin-left: 15px;\n         color: white;\n         font-size: 20px;\n    }\n</style>\n\n     ");
				if (page.getUrls().size() > 0) {
					jteOutput.writeContent("\n         <div class=\"full-table\">\n              <p class=\"websites\">Websites</p>\n                   <table>\n                        <thead>\n                             <tr>\n                                  <th>ID</th>\n                                  <th>Name</th>\n                                  <th>Last check</th>\n                                  <th>Result code</th>\n                             </tr>\n                        </thead>\n                        <tbody>\n                             ");
					for (var url : page.getUrls()) {
						jteOutput.writeContent("\n                                  <tr>\n                                       <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(url.getId());
						jteOutput.writeContent("</td>\n                                       <td><a");
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
						jteOutput.writeContent("</a></td>\n                                       <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(url.getLastCheck() != null ?
                                       url.getLastCheck().getCreatedAt()
                                       .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "No check");
						jteOutput.writeContent("</td>\n                                       <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(url.getLastCheck() != null ?
                                       String.valueOf(url.getLastCheck().getStatusCode()) : "No code");
						jteOutput.writeContent("</td>\n                                  </tr>\n                             ");
					}
					jteOutput.writeContent("\n                        </tbody>\n                   </table>\n         </div>\n    ");
				} else {
					jteOutput.writeContent("\n         <p class=\"not-available\">No urls available.</p>\n    ");
				}
				jteOutput.writeContent("\n\n     ");
			}
		}, null, page);
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
