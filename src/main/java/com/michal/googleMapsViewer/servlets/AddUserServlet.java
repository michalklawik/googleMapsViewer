package com.michal.googleMapsViewer.servlets;

import com.michal.googleMapsViewer.dao.UsersRepositoryDao;
import com.michal.googleMapsViewer.dao.UsersRepositoryDaoBean;
import com.michal.googleMapsViewer.domain.Gender;
import com.michal.googleMapsViewer.domain.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add-user")
public class AddUserServlet extends HttpServlet {

    @EJB
    UsersRepositoryDao usersRepositoryDao = new UsersRepositoryDaoBean();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkRequest(req, resp)) return;

        User newUser = createUser(req);

        usersRepositoryDao.addUser(newUser);

        printOut(resp, newUser);
    }

    private void printOut(HttpServletResponse resp, User newUser) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("Dodano usera: " + newUser.toString());
    }

    private User createUser(HttpServletRequest req) {
        return new User(usersRepositoryDao.getUsersList().size() + 1,
                req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("login"),
                Integer.valueOf(req.getParameter("age")),
                Gender.MAN);
    }

    private boolean checkRequest(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("name") == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return true;
        } else if (req.getParameter("surname") == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return true;
        } else if (req.getParameter("login") == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return true;
        } else if (req.getParameter("age") == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return true;
        }
        return false;
    }
}
