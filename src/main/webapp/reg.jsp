<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.dream.model.Post" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <title>Работа мечты</title>
</head>
<script>
    function validate() {
        let fields = [$("#name"), $("#eml"), $("#psw")];
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

<div class="container pt-3">

    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Регистрация
            </div>
            <div class="container">
                <div class="row">
                    <c:if test="${not empty error}">
                        <div style="color:red; font-weight: bold; margin: 30px 0;">
                                ${error}
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/reg.do" method="post">
                    <div class="form-group">
                        <label>Имя</label>
                        <input type="text" class="form-control" id="name" placeholder="Введите Имя" name="name">
                    </div>
                    <div class="form-group">
                        <label>Почта</label>
                        <input type="text" class="form-control" id="eml" placeholder="Введите Почту"name="email">
                    </div>
                    <div class="form-group">
                        <label>Пароль</label>
                        <input type="text" class="form-control" id="psw" placeholder="Введите Пароль" name="password">
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Зарегистрироваться</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
