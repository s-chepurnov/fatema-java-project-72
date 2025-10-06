package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.UrlPage;
import java.time.format.DateTimeFormatter;
import hexlet.code.util.NamedRoutes;
public final class JteshowGenerated {
	public static final String JTE_NAME = "urls/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,6,6,8,8,71,71,71,82,82,82,83,83,83,84,84,84,101,101,104,104,104,105,105,105,106,106,106,107,107,107,108,108,108,109,109,109,112,112,114,114,114,114,119,119,119,119,119,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\n\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n\n<style>\n    main {\n        margin-top: 0px;\n        margin-left: 0px;\n        background-color: #468FAF;\n        padding-bottom: 100px\n    }\n\n    table {\n        margin-left: 500px;\n        width: 50%;\n        border-collapse: collapse;\n    }\n\n    th, td {\n        border: 1px solid #000;\n        padding: 5px;\n    }\n\n    th {\n        background-color: white;\n    }\n\n    td {\n        color: white;\n    }\n\n    th:nth-child(4), td:nth-child(4) {\n         width: 100px;\n    }\n\n    th:nth-child(6), td:nth-child(6) {\n         width: 160px;\n    }\n\n    .website {\n        margin-left: 500px;\n        font-size: 45px;\n        font-weight: bold;\n        color: white;\n    }\n\n    .checks {\n        margin-left: 500px;\n        font-size: 45px;\n        font-weight: bold;\n        color: white;\n    }\n\n    .run {\n        margin-left: 500px;\n        margin-top: 20px;\n        font-size: 20px;\n        color: white;\n        background-color: #A9D6E5;\n        height: 50px;\n        width: 200px;\n    }\n</style>\n\n<main>\n     <p class=\"website\">Website: ");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</p>\n     <table>\n          <thead>\n               <tr>\n                 <th>ID</th>\n                 <th>Name</th>\n                 <th>Created date</th>\n               </tr>\n          </thead>\n          <tbody>\n               <tr>\n                 <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.writeContent("</td>\n                 <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</td>\n                 <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
				jteOutput.writeContent("</td>\n               </tr>\n          </tbody>\n     </table>\n     <p class=\"checks\">Checks</p>\n     <table>\n          <thead>\n               <tr>\n                    <th>ID</th>\n                    <th>Answer code</th>\n                    <th>Title</th>\n                    <th>H1</th>\n                    <th>Description</th>\n                    <th>Check date</th>\n               </tr>\n          </thead>\n\n    ");
				for (var check : page.getUrlChecks()) {
					jteOutput.writeContent("\n      <tbody>\n           <tr>\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getId());
					jteOutput.writeContent("</td>\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getStatusCode());
					jteOutput.writeContent("</td>\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getTitle());
					jteOutput.writeContent("</td>\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getH1());
					jteOutput.writeContent("</td>\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getDescription());
					jteOutput.writeContent("</td>\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(check.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
					jteOutput.writeContent("</td>\n           </tr>\n      </tbody>\n    ");
				}
				jteOutput.writeContent("\n   </table>\n   <form action=\"/urls/");
				jteOutput.setContext("form", "action");
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.setContext("form", null);
				jteOutput.writeContent("/checks\" method=\"post\">\n   <button class=\"run\" type=\"submit\">Run check</button>\n   </form>\n</main>\n\n     ");
			}
		}, null, null);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
