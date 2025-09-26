package com.lordbao.email;

import com.lordbao.business.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/emaillist")
public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "join";

        String url = "/index.jsp";

        if ("join".equals(action)) {
            url = "/index.jsp";
        } else if ("add".equals(action)) {
            String email     = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName  = request.getParameter("lastName");

            User user = new User(email, firstName, lastName);
            request.setAttribute("user", user);

            url = "/thanks.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
