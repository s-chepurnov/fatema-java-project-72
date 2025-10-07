@import hexlet.code.util.NamedRoutes
@import hexlet.code.dto.BuildUrlPage
@param BuildUrlPage page

<style>
     body, html {
          background-color: #4e5d94;

     }

     .full {
          margin-top: 0px;
          margin-left: 0px;
         background-color: #4e5d94;
         padding-bottom: 100px
     }

     .page-analyze {
          margin-left: 500px;
          font-size: 100px;
          font-weight: bold;
          color: white;
     }

     .check {
          margin-left: 500px;
          font-size: 20px;
          color: white;
     }

     .url-name {
          margin-left: 500px;
          font-size: 30px;
          color: green;
          background-color: white;
          height: 50px;
          width: 1000px;
     }

     .checking {
          margin-left: 50px;
          font-size: 30px;
          font-weight: bold;
          color: white;
          background-color: #7289da;
          height: 50px;
          width: 200px;
     }

     .example {
          margin-left: 500px;
          font-size: 15px;
          color: white;
     }
</style>

<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Page analyzer</title>
</head>
<body>

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
   <div class="full">
        <p class="page-analyze">Page analyzer</p>
        <p class="check">Check websites for free for SEO suitability</p>
       <label>
       <input class="url-name" type="text" required name="url" placeholder="web link" />
       </label>
       <input class="checking" type="submit" value="Check" />
       <p class="example">Example: https://www.example.com</p>
   </div>
   </form>

     `,
)
</body>
</html>
