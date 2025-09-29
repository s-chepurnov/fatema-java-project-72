@import hexlet.code.dto.UrlPage
@import java.time.format.DateTimeFormatter
@import hexlet.code.util.NamedRoutes

@param UrlPage page

<main>
    <div>ID: ${page.getUrl().getId()}</div>
    <div>Name: ${page.getUrl().getName()}</div>
    <div>Created At: ${page.getUrl().getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</div>
    <div>
        <a href="${NamedRoutes.urlsPath()}">Return to urls list</a>
    </div>
    <div>
       <a href="${NamedRoutes.rootPath()}">return to main page</a>
    </div>
</main>