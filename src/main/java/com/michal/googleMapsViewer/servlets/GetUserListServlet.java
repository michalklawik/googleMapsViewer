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
import java.util.List;

@WebServlet("/get-user-list")
public class GetUserListServlet extends HttpServlet {

    @EJB
    UsersRepositoryDao usersRepositoryDao = new UsersRepositoryDaoBean();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkIfUserListEmpty(resp);

        printOut(resp);
    }

    private void printOut(HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println(printList(usersRepositoryDao.getUsersList()));
    }

    private String printList(List<User> usersList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (User user: usersList) {
            stringBuilder.append(user.toString())
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    private void checkIfUserListEmpty(HttpServletResponse resp) {
        if (usersRepositoryDao.getUsersList().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
    }
}
