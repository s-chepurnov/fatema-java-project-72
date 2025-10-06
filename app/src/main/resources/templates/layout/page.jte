@import hexlet.code.util.NamedRoutes
@import hexlet.code.dto.BasePage
@import gg.jte.Content
@param Content content
@param Content footer = null
@param BasePage page = null

<style>
     .half-screen {
          margin-left: 0px;
          background-color: #303030;
          height: 5%;
     }

     nav a {
          margin-right: 20px;
          color: white;
     }

     nav .page {
          margin-left: 20px;
          font-size: 20px;
          font-weight: bold;
     }

     nav .main {
          font-size: 16px;
     }

     nav .web {
          font-size: 16px;
     }

      .message {
          margin-top: 0px;
          margin-left: 0px;
          margin-bottom: 0px;
          color: #072C63;
          background-color: #909590;
          height: 40px
      }
</style>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>java-project-72</title>
        <link rel="stylesheet" href="/assets/build.css">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

    </head>
    <body>
     <header class="half-screen">
          <nav>
               <a href="${NamedRoutes.rootPath()}" class="page">Page analyzer</a>
               <a href="${NamedRoutes.rootPath()}" class="main">Main</a>
               <a href="${NamedRoutes.urlsPath()}" class="web">Websites</a>
          </nav>
    </header>

    <main class="half-screen">
         @if(page != null && page.getFlash() != null)
              <p class="message">${page.getFlash()}</p>
         @endif
         ${content}
    </main>

         @if (footer != null)
              <div class="footer">
                   ${footer}
              </div>
         @endif
    </body>
</html>