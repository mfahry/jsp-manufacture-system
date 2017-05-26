/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.GlobalData;
import static config.GlobalData.FAIL;
import static config.GlobalData.IS_LOGIN;
import static config.GlobalData.USERID_SESSION;
import dao.ComponentDAO;
import dao.CostProductionDAO;
import dao.OrderComponentDAO;
import dao.PartDAO;
import dao.ProductDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CostProduction;
import model.OrderComponent;
import model.Product;
import model.User;

/**
 *
 * @author Akip Maulana
 */
public class DashboardController extends HttpServlet implements GlobalData{

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

            if (action == null) {
                action = "_";
            }

            switch (action) {
                case "unknown":
                    
                    break;
                default:
                    request.setAttribute(FAIL, "");
                    ProductDAO cDAO = new ProductDAO();
                    ArrayList<Product> product= cDAO.selectAllProduct();
                    request.setAttribute("product", product);
                    
                    OrderComponentDAO oDAO = new OrderComponentDAO();
                    ArrayList<OrderComponent> order = oDAO.selectAllOrderComponent();
                    request.setAttribute("order", order);
                    
                    PartDAO partDao = new PartDAO();
                    int totalPart = partDao.selectAllPart().size();
                    request.setAttribute("totalPart", totalPart);
                    
                    ComponentDAO compDao = new ComponentDAO();
                    int totalComp = compDao.selectAllComponent().size();
                    request.setAttribute("totalComp", totalComp);
                    
                    int totalPro = product.size();
                    request.setAttribute("totalPro", totalPro);
                    
                    CostProductionDAO costDao = new CostProductionDAO();
                    String totalCost = costDao.getUsedAmountTotal();
                    request.setAttribute("totalCost", totalCost);
                    
                    
                    ArrayList<CostProduction> grafikCost = costDao.getGrafikCostProduction();
                    request.setAttribute("grafikCost", grafikCost);
                    
                    request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex){
            ex.printStackTrace();
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
