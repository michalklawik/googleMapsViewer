package com.michal.googleMapsViewer.servlets;


import com.michal.googleMapsViewer.cdiBeans.MaxPulse;
import com.michal.googleMapsViewer.cdiBeans.MaxPulseBean;
import com.michal.googleMapsViewer.dao.UsersRepositoryDao;
import com.michal.googleMapsViewer.dao.UsersRepositoryDaoBean;
import com.michal.googleMapsViewer.domain.Gender;
import com.michal.googleMapsViewer.domain.User;

import javax.ejb.EJB;
import javax.inject.Inject;
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

    @Inject
    MaxPulse maxPulse = new MaxPulseBean();

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

        if (user.getGender().equals(Gender.MAN)) {
            printOut(resp, user, maxPulse.calculateMaxPulseForMen(user.getAge()));
        } else {
            printOut(resp, user, maxPulse.calculateMaxPulseForWomen(user.getAge()));
        }
    }

    private void printOut(HttpServletResponse resp, User user, double maxPulse) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html><html><body>" + user.toString() + "<br>" + "Max pulse: " + maxPulse + "</body></html>");
    }
}
