@import hexlet.code.dto.UrlPage
@import java.time.format.DateTimeFormatter
@import hexlet.code.util.NamedRoutes
@param UrlPage page


@template.layout.page(
    content = @`

<style>
    main {
        margin-top: 0px;
        margin-left: 0px;
        background-color: #468FAF;
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

    th:nth-child(4), td:nth-child(4) {
         width: 100px;
    }

    th:nth-child(6), td:nth-child(6) {
         width: 160px;
    }

    .website {
        margin-left: 500px;
        font-size: 45px;
        font-weight: bold;
        color: white;
    }

    .checks {
        margin-left: 500px;
        font-size: 45px;
        font-weight: bold;
        color: white;
    }

    .run {
        margin-left: 500px;
        margin-top: 20px;
        font-size: 20px;
        color: white;
        background-color: #A9D6E5;
        height: 50px;
        width: 200px;
    }
</style>

<main>
     <p class="website">Website: ${page.getUrl().getName()}</p>
     <table>
          <thead>
               <tr>
                 <th>ID</th>
                 <th>Name</th>
                 <th>Created date</th>
               </tr>
          </thead>
          <tbody>
               <tr>
                 <td>${page.getUrl().getId()}</td>
                 <td>${page.getUrl().getName()}</td>
                 <td>${page.getUrl().getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
               </tr>
          </tbody>
     </table>
     <p class="checks">Checks</p>
     <table>
          <thead>
               <tr>
                    <th>ID</th>
                    <th>Answer code</th>
                    <th>Title</th>
                    <th>H1</th>
                    <th>Description</th>
                    <th>Check date</th>
               </tr>
          </thead>

    @for(var check : page.getUrlChecks())
      <tbody>
           <tr>
                <td>${check.getId()}</td>
                <td>${check.getStatusCode()}</td>
                <td>${check.getTitle()}</td>
                <td>${check.getH1()}</td>
                <td>${check.getDescription()}</td>
                <td>${check.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
           </tr>
      </tbody>
    @endfor
   </table>
   <form action="/urls/${page.getUrl().getId()}/checks" method="post">
   <button class="run" type="submit">Run check</button>
   </form>
</main>

     `,
)