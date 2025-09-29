@import hexlet.code.util.NamedRoutes
@import hexlet.code.dto.UrlsPage
@param UrlsPage page

@template.layout.page(

    page = page,
    content = @`

    @if(page.getUrls().size() > 0)
          @for(var url : page.getUrls())
              <p>
              <a href="${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a>
              </p>
          @endfor

          @else
            <p>No urls available.</p>
    @endif

    <p>
      <a href="${NamedRoutes.rootPath()}">return to main page</a>
    </p>


     `,
  )