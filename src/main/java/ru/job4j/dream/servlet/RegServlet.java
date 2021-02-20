package ru.job4j.dream.servlet;

import ru.job4j.dream.model.User;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        if (PsqlStore.instOf().findByEmailUser(email) != null) {
            req.setAttribute("error", "Пользователь с указанным email уже зарегистрирован");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        } else {
            PsqlStore.instOf().createUser(new User(req.getParameter("name"), req.getParameter("email"), req.getParameter("password")));
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }
}
