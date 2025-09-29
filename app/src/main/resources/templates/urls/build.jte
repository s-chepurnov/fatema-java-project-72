@import hexlet.code.util.NamedRoutes
@import hexlet.code.dto.BuildUrlPage
@param BuildUrlPage page

@template.layout.page(

    page = page,
    content = @`

    @if(page.getErrors() != null)
                <div class="mb-3">
                    <ul>
                        @for(var validator : page.getErrors().values())
                            @for(var error : validator)
                                <li>${error.getMessage()}</li>
                            @endfor
                        @endfor
                    </ul>
                </div>
            @endif

   <form action="${NamedRoutes.urlsPath()}" method="post">
   <p>
     <h1>Website analyzer</h1>
     <h3>Check websites for free for SEO suitability</h3>
   </p>
   <label>
      <input type="text" required name="url" placeholder="web link" />
   </label>
      <input type="submit" value="Check" />
   <p>
      Example: https://www.example.com
   </p>
   <p>
     <a href="${NamedRoutes.urlsPath()}">Pages</a>
   </p>
   </form>

   `
)