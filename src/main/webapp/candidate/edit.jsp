<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.dream.model.Post" %>
<%@ page import="ru.job4j.dream.model.Candidate" %>
<%@ page import="ru.job4j.dream.store.PsqlStore" %>
<%@ page import="ru.job4j.dream.model.Photo" %>
<%@ page import="ru.job4j.dream.model.City" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>Работа мечты</title>
</head>
<script>
    function validate() {
        let fields = [$("#name"), $("#idP"), $("#town"), $("#resume"), $("#date")];
        let result = true;
        let answer = '';
        for (let i = 0; i < fields.length; i++) {
            if (fields[i].val() === "") {
                answer += fields[i].attr("placeholder") + "\n";
                result = false;
            }
        }
        if (!result) {
            alert(answer);
        }
        return result;
    }
    $(document).ready(function () {
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/dreamjob/towns",
            dataType: 'json',
            success: function (data) {
                let cities = "";
                for (let i = 0; i < data.length; i++) {
                    cities += "<option value=" + data[i]['town'] + ">" + data[i]['town'] + "</option>";
                }
                $('#townX option:last').after(cities);
            }
        })
    })
//value='" +"'
</script>
<body>
<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">Отмена</a>
            </li>
        </ul>
    </div>
</div>
<%
    String id = request.getParameter("id");
    Candidate candidate = new Candidate(0, "",new City(0, ""), "", new Photo(0));
    if (id != null) {
        candidate = PsqlStore.instOf().findByIdCandidate(Integer.parseInt(id));
    }
%>
<%
    String photo_id = (String) request.getAttribute("idP");
    if (photo_id == null && candidate.getId() > 0) {
        photo_id = String.valueOf(candidate.getPhoto().getIdP());
    }
%>
<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <% if (id == null && photo_id == null) { %>
                Новый кандидат.
                <% } else { %>
                Редактирование кандидата.
                <% } %>
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/candidates.do?id=<%=candidate.getId()%>" method="post">
                    <div class="form-group">
                        <label>Имя</label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="Введите Имя" value="<%=candidate.getName()%>">
                    </div>
                    <div class="form-group">
                        <label>Фото</label>
                        <input type="text" class="form-control" name="idP" id="idP" placeholder="Введите Id Фото в БД" value="<%=candidate.getPhoto().getIdP()%>">
                    </div>
                    <div class="form-group">
                        <label>Город</label>
                        <select class="form-group custom-select" name="townX" id="townX">
                            <option value="<%=candidate.getCity().getTown()%>" selected><%=candidate.getCity().getTown()%></option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Резюме</label>
                        <input type="text" class="form-control" name="resume" id="resume" placeholder="Введите Ваше Резюме" value="<%=candidate.getResume()%>">
                    </div>
                    <div class="form-group">
                        <label>Дата создания</label>
                        <input type="text" class="form-control" name="date" id="date" placeholder="Введите Актуальную дату" value="<%=candidate.getDate()%>">
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>