@import hexlet.code.util.NamedRoutes
@import java.time.format.DateTimeFormatter
@import hexlet.code.dto.UrlsPage
@param UrlsPage page


@template.layout.page(
    page = page,
    content = @`

<style>
     .full-table {
          margin-top: 0px;
          margin-left: 0px;
          background-color: #2B3D5B;
          padding-bottom: 100px
     }

     table {
          margin-left: 500px;
          width: 50%;
          border-collapse: collapse;
     }

     th, td {
          border: 1px solid #000;
          padding: 5px;
     }

     th {
          background-color: white;
     }

     td {
          color: white;
     }

    .websites {
         margin-left: 500px;
         font-size: 60px;
         font-weight: bold;
         color: white;
    }

    .not-available {
         margin-left: 15px;
         color: white;
         font-size: 20px;
    }
</style>

     @if(page.getUrls().size() > 0)
         <div class="full-table">
              <p class="websites">Websites</p>
                   <table>
                        <thead>
                             <tr>
                                  <th>ID</th>
                                  <th>Name</th>
                                  <th>Last check</th>
                                  <th>Result code</th>
                             </tr>
                        </thead>
                        <tbody>
                             @for(var url : page.getUrls())
                                  <tr>
                                       <td>${url.getId()}</td>
                                       <td><a href="${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a></td>
                                       <td>${url.getLastCheck() != null ?
                                       url.getLastCheck().getCreatedAt()
                                       .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "No check"}</td>
                                       <td>${url.getLastCheck() != null ?
                                       String.valueOf(url.getLastCheck().getStatusCode()) : "No code"}</td>
                                  </tr>
                             @endfor
                        </tbody>
                   </table>
         </div>
    @else
         <p class="not-available">No urls available.</p>
    @endif

     `,
  )
