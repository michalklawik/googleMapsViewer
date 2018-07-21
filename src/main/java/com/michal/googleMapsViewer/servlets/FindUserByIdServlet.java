package com.michal.googleMapsViewer.servlets;


import com.michal.googleMapsViewer.dao.UsersRepositoryDao;
import com.michal.googleMapsViewer.dao.UsersRepositoryDaoBean;
import com.michal.googleMapsViewer.domain.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

@WebServlet("/find-user-by-id")
public class FindUserByIdServlet extends HttpServlet {

    @EJB
    private UsersRepositoryDao usersRepositoryDao = new UsersRepositoryDaoBean();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        User user;

        try {
            user = usersRepositoryDao.getUserById(Integer.parseInt(req.getParameter("id")));
        } catch (NoSuchElementException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html><html><body>" + user.toString() + "</body></html>");
    }
}
