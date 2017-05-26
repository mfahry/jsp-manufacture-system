/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.GlobalData;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Component;
import model.User;

/**
 *
 * @author Akip Maulana
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet implements GlobalData {

    private String action = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            action = request.getParameter("act");
            
            String page = "index.jsp";
            
            HttpSession session = request.getSession();//session

            UserDAO userD = new UserDAO();

            if (action == null) {
                action = "_";
            }

            switch (action) {
                case "signin":
                    String user = request.getParameter("user");
                    String pass = request.getParameter("pass");
                    ArrayList<User> userAkun = null;
                    try {
                        userAkun = userD.selectConditionUser(new String[]{"USERNAME", "PASSWORD"},
                                new Object[]{user, pass});
                        if (!userAkun.isEmpty()) {
                            //Login Berhasil
                            session.setAttribute(IS_LOGIN, "ok");
                            session.setAttribute(USERID_SESSION, userAkun.get(0).getUsername());
                            session.setAttribute(USERGROUP_SESSION, userAkun.get(0).getUserGroup());
                            response.sendRedirect("DashboardController");
                        } else {
                            request.setAttribute(FAIL, FAIL);
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "signout":
                    request.getSession().setAttribute(IS_LOGIN, null);
                    request.getSession().setAttribute("adminis", null);
                    request.getSession().setAttribute(USERID_SESSION, "");
                    request.getSession().setAttribute(USERGROUP_SESSION, "");
                    response.sendRedirect("LoginServlet");
                    break;
                default:
                    request.setAttribute(FAIL, "");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
