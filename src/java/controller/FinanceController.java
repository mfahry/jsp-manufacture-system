/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static config.GlobalData.FAIL;
import static config.GlobalData.IS_LOGIN;
import static config.GlobalData.USERGROUP_SESSION;
import static config.GlobalData.USERID_SESSION;
import dao.ComponentDAO;
import dao.CostProductionDAO;
import dao.PartDAO;
import dao.ProductDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Component;
import model.CostProduction;
import model.Part;
import model.Product;
import model.User;

/**
 *
 * @author ACER
 */
public class FinanceController extends HttpServlet {

    private String action = "";
    private RequestDispatcher rd;

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
                case "edit":
                    editProduct(request, response);
                    break;
                case "update":
                    updateProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "save":
                    saveProduct(request, response);
                    break;
                default:
                    request.setAttribute(FAIL, "");
                    CostProductionDAO cosDao = new CostProductionDAO();
                    ProductDAO proDAO = new ProductDAO();
                    ArrayList<CostProduction> listCostDao = cosDao.selectAllCostProduction();
                    ArrayList<Product> productList = proDAO.selectAllProduct();
                    request.setAttribute("listCostDao", listCostDao);
                    request.setAttribute("productList", productList);
                    request.getRequestDispatcher("viewCostProduction.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
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

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        CostProductionDAO costDAO = new CostProductionDAO();
        ProductDAO proDAO = new ProductDAO();
        ArrayList<CostProduction> listCostDao = costDAO.selectAllCostProduction();
        ArrayList<Product> productList = proDAO.selectAllProduct();
//        CostProduction cosProData = costDAO.selectById(Integer.parseInt(request.getParameter("id")));
        CostProduction cosProData = costDAO.selectById(new String[]{"PRODUCT_ID", "PRODUCTION_YEAR"},
                new String[]{request.getParameter("id"), request.getParameter("year")});
        request.setAttribute("listCostDao", listCostDao);
        request.setAttribute("productList", productList);
        request.setAttribute("cosProData", cosProData);
        rd = request.getRequestDispatcher("viewCostProduction.jsp");
        rd.forward(request, response);
    }

    private void saveProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String productId = request.getParameter("costName");
        String productYear = request.getParameter("costYear");
        int productAmount = Integer.parseInt(request.getParameter("costAmount"));
        int productUsed = Integer.parseInt(request.getParameter("costUsed"));

        CostProductionDAO costProDao = new CostProductionDAO();
        CostProduction costPro = new CostProduction();
        costPro.setProduct(costProDao.getPDao().selectById(Integer.parseInt(productId)));
        costPro.setProductionYear(productYear);
        costPro.setAmount(productAmount);
        costPro.setUsedAmount(productUsed);

        if (costProDao.saveCostProduction(costPro) > 0) {
            request.setAttribute("message", "save successfully !!");
        } else {
            request.setAttribute("message", "save failed !!");
        }

        ArrayList<CostProduction> listCostDao = costProDao.selectAllCostProduction();
        ArrayList<Product> productList = costProDao.getPDao().selectAllProduct();
        request.setAttribute("listCostDao", listCostDao);
        request.setAttribute("productList", productList);

        rd = request.getRequestDispatcher("viewCostProduction.jsp");
        rd.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        //declare variable local
        String oldProductId = request.getParameter("costId");
        String productId = request.getParameter("costName");
        String productYear = request.getParameter("costYear");
        int productAmount = Integer.parseInt(request.getParameter("costAmount"));
        int productUsed = Integer.parseInt(request.getParameter("costUsed"));

        CostProductionDAO costProDao = new CostProductionDAO();
        CostProduction costPro = new CostProduction();
        costPro.setProduct(costProDao.getPDao().selectById(Integer.parseInt(productId)));
        costPro.setProductionYear(productYear);
        costPro.setAmount(productAmount);
        costPro.setUsedAmount(productUsed);

        if (costProDao.updateCostProduction(costPro, productAmount, productUsed) > 0) {
            request.setAttribute("message", "update successfully !!");
        } else {
            request.setAttribute("message", "update failed !!");
        }

        ArrayList<CostProduction> listCostDao = costProDao.selectAllCostProduction();
        ArrayList<Product> productList = costProDao.getPDao().selectAllProduct();
        request.setAttribute("listCostDao", listCostDao);
        request.setAttribute("productList", productList);

        rd = request.getRequestDispatcher("viewCostProduction.jsp");
        rd.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        CostProductionDAO costDao = new CostProductionDAO();

        CostProduction costPro = costDao.selectById(new String[]{"PRODUCT_ID", "PRODUCTION_YEAR"},
                new String[]{request.getParameter("id"), request.getParameter("year")});
        if (costDao.deleteCostProduction(costPro) > 0) {
            request.setAttribute("message", "delete successfully !!");
        } else {
            request.setAttribute("message", "delete failed !!");
        }
        
        ArrayList<CostProduction> listCostDao = costDao.selectAllCostProduction();
        ArrayList<Product> productList = costDao.getPDao().selectAllProduct();
        request.setAttribute("listCostDao", listCostDao);
        request.setAttribute("productList", productList);

        rd = request.getRequestDispatcher("viewCostProduction.jsp");
        rd.forward(request, response);
    }

}
