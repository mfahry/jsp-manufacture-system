///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controller;
//
//import config.GlobalData;
//import dao.ComponentDAO;
//import dao.OrderComponentDAO;
//import dao.PartDAO;
//import dao.ProductDAO;
//import dao.UserDAO;
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Date;
//import java.sql.SQLException;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Locale;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import model.Component;
//import model.OrderComponent;
//import model.Part;
//import model.Product;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//
///**
// *
// * @author ACER
// */
//public class ProductionController extends HttpServlet implements GlobalData{
//    /*RequestDispatcher rd;
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException, SQLException, ParseException {
//            response.setContentType("text/html;charset=UTF-8");
//            
//            String act = request.getParameter("act");
//            switch(act){
//                case "saveComponent" : 
//                    saveComponent(request, response);
//                    break;
//                case "formUpdateComponent" : 
//                    formUpdateComponent(request, response);
//                    break;
//                case "updateComponent" : 
//                    updateComponent(request, response);
//                    break;
//                case "deleteComponent" : 
//                    deleteComponent(request, response);
//                    break;
//                case "manageComponent" : 
//                    manageComponent(request, response);
//                    break;    
//                case "savePart" : 
//                    savePart(request, response);
//                    break;
//                case "formUpdatePart" : 
//                    formUpdatePart(request, response);
//                    break;
//                case "updatePart" : 
//                    updatePart(request, response);
//                    break;
//                case "deletePart" : 
//                    deletePart(request, response);
//                    break;
//                case "managePart" : 
//                    managePart(request, response);
//                    break;
//                case "saveProduct" : 
//                    saveProduct(request, response);
//                    break;
//                case "formUpdateProduct" : 
//                    formUpdateProduct(request, response);
//                    break;
//                case "updateProduct" : 
//                    updateProduct(request, response);
//                    break;
//                case "deleteProduct" : 
//                    deleteProduct(request, response);
//                    break;
//                case "manageProduct" : 
//                    manageProduct(request, response);
//                    break;
//                case "saveOrderComponent" : 
//                    saveOrderComponent(request, response);
//                    break;
//                case "updateOrderComponent" : 
//                    updateOrderComponent(request, response);
//                    break;
//                case "formUpdateOrderComponent" : 
//                    formUpdateOrderComponent(request, response);
//                    break;
//                case "deleteOrderComponent" : 
//                    deleteOrderComponent(request, response);
//                    break;
//                case "manageOrderComponent" : 
//                    manageOrderComponent(request, response);
//                    break;
//            }
//                    
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws Servleption if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            processRequest(request, response);
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ParseException ex) {
//            Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            processRequest(request, response);
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ParseException ex) {
//            Logger.getLogger(ProductionController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//    private void saveComponent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
//        //declare variable local
//        String imgUrl = "" ;
//        Component c = new Component();
//        ComponentDAO cDAO = new ComponentDAO();
//        PartDAO pDAO = new PartDAO();
//        
//        // checks if the request actually contains upload file
//        if (ServletFileUpload.isMultipartContent(request)) {
//            long serialVersionUID = 1L;
//
//            // location to store file uploaded
//            String UPLOAD_DIRECTORY = "image/component";
//
//            // upload settings
//            int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
//            int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
//            int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
//            
//            // configures upload settings
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            // sets memory threshold - beyond which files are stored in disk
//            factory.setSizeThreshold(MEMORY_THRESHOLD);
//            // sets temporary location to store files
//            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//
//            ServletFileUpload upload = new ServletFileUpload(factory);
//
//            // sets maximum size of upload file
//            upload.setFileSizeMax(MAX_FILE_SIZE);
//
//            // sets maximum size of request (include file + form data)
//            upload.setSizeMax(MAX_REQUEST_SIZE);
//
//            // constructs the directory path to store upload file
//            // this path is relative to application's directory
//            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//
//            // creates the directory if it does not exist
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            try {
//                // parses the request's content to extract file data
//                @SuppressWarnings("unchecked")
//                List<FileItem> formItems = upload.parseRequest(request);
//
//                if (formItems != null && formItems.size() > 0) {
//                    // iterates over form's fields
//                    for (FileItem item : formItems) {
//                        // processes only fields that are not form fields
//                        if (!item.isFormField()) {
//                            String fileName = new File(item.getName()).getName();
//                            String filePath = uploadPath + File.separator + fileName;
//                            File storeFile = new File(filePath);
//                            imgUrl = UPLOAD_DIRECTORY + "/" + fileName;
//                            // saves the file on disk
//                            item.write(storeFile);
//                        }
//                        else{
//                            String fieldName = item.getFieldName();
//                            if(fieldName.equals("compName")){
//                                c.setComponentName(item.getString());
//                            }
//                            else if(fieldName.equals("compPart")){
//                                c.setPart(pDAO.selectById(Integer.parseInt(item.getString())));
//                            }
//                            else if(fieldName.equals("compQuant")){
//                                c.setQuantity(Integer.parseInt(item.getString()));
//                            }
//                        }
//                    }
//                }
//            } catch (Exception ex) {
//                request.setAttribute("message",
//                        "There was an error: " + ex.getMessage());
//            }
//        }
//        c.setImgUrl(imgUrl);
//        
//        if(cDAO.saveComponent(c) > 0 ) {
//            request.setAttribute("message", "save successfully !!");
//        }
//        else{
//            request.setAttribute("message", "save failed !!");
//        }
//        rd = request.getRequestDispatcher("ProductionController?act=manageComponent");
//        rd.forward(request, response);  
//    }
//
//    private void manageOrderComponent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        ComponentDAO cDAO = new ComponentDAO();
//        OrderComponentDAO dao = new OrderComponentDAO();
//        ArrayList<OrderComponent> orderComponent= dao.selectAllOrderComponent();
//        ArrayList<Component> component= cDAO.selectAllComponent();
//        request.setAttribute("orderComponent", orderComponent);
//        request.setAttribute("component", component);
//        rd = request.getRequestDispatcher("viewOrderComponent.jsp");
//        rd.forward(request, response);
//    }
//
//    private void deleteOrderComponent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        OrderComponentDAO cDAO = new OrderComponentDAO();
//        OrderComponent c = cDAO.selectById(Integer.parseInt(request.getParameter("id")));
//        if(cDAO.deleteOrderComponent(c) > 0 ) {
//            request.setAttribute("message", "delete successfully !!");
//        }
//        else{
//            request.setAttribute("message", "delete failed !!");
//        }
//        rd = request.getRequestDispatcher("ProductionController?act=manageOrderComponent");
//        rd.forward(request, response);  
//    }
//
//    private void updateOrderComponent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, ServletException, IOException {
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//        
//        double newCost = Double.parseDouble(request.getParameter("cost"));
//        int newStatus = Integer.parseInt(request.getParameter("status"));
//        int newQuantity = Integer.parseInt(request.getParameter("quantity"));
//        int orderComponentId = Integer.parseInt(request.getParameter("orderComponentId"));
//        int newComponentId = Integer.parseInt(request.getParameter("componentId"));
//        String newUsername = request.getParameter("username");
//        String newScheduledBuyUsername = request.getParameter("scheduledBuyUsername");
//        Date newRequiredDate = (Date) format.parse(request.getParameter("requiredDate"));
//        Date newScheduledBuyDate;
//        if(request.getParameter("scheduledBuyDate").equals("")){
//            newScheduledBuyDate = null;
//        }else{
//            newScheduledBuyDate = (Date) format.parse(request.getParameter("scheduledBuyDate"));
//        }
//        
//        
//        OrderComponentDAO cDAO = new OrderComponentDAO();
//        OrderComponent o = cDAO.selectById(Integer.parseInt(request.getParameter("orderComponentId")));
//        Date newOrderDate = o.getOrderDate();
//        
//        if(cDAO.updateOrderComponent(o, newComponentId, newUsername, newOrderDate, newRequiredDate,  newScheduledBuyDate, newScheduledBuyUsername, newStatus, newQuantity, newCost) > 0){
//            request.setAttribute("message", "update successfully !!");
//        }
//        else{
//            request.setAttribute("message", "update failed !!");
//        }
//        rd = request.getRequestDispatcher("ProductionController?act=manageOrderComponent");
//        rd.forward(request, response);  
//        
//    }
//
//    private void saveOrderComponent(HttpServletRequest request, HttpServletResponse response) throws ParseException, SQLException, ServletException, IOException {
//        OrderComponent o = new OrderComponent();
//        OrderComponentDAO dao = new OrderComponentDAO();
//        ComponentDAO cDAO = new ComponentDAO();
//        UserDAO uDAO = new UserDAO();
//        //date format parse
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        
//        //get parameter request
//        //int cost = Integer.parseInt(request.getParameter("cost"));
//        //int status = Integer.parseInt(request.getParameter("status"));
//        int quantity = Integer.parseInt(request.getParameter("quantity"));
//        int componentId = Integer.parseInt(request.getParameter("componentId"));
//        String username = request.getParameter("username");
//        //String scheduledBuyUsername = request.getParameter("scheduledBuyUsername");
//        Date requiredDate = (Date) df.parse(request.getParameter("requiredDate"));
//        //Date scheduledBuyDate = (Date) df.parse(request.getParameter("scheduledBuyDate"));
//        Date orderDate = Calendar.getInstance().getTime();;
//        
//        
//        //set requirment
//        o.setComponent(cDAO.selectById(componentId));
//        //o.setCost(cost);
//        o.setOrderDate(orderDate);
//        o.setQuantity(quantity);
//        o.setRequiredDate(requiredDate);
//        //o.setScheduleBuyDate(scheduledBuyDate);
//        o.setScheduleBuyUser(uDAO.selectById(username));
//        o.setStatus(0);
//        o.setUser(uDAO.selectById(username));
//        
//        if(dao.saveOrderComponent(o) > 0){
//            request.setAttribute("message", "save successfully !!");
//        }
//        else{
//            request.setAttribute("message", "save failed !!");
//        }
//        rd = request.getRequestDispatcher("ProductionController?act=manageOrderComponent");
//        rd.forward(request, response);  
//    }
//
//    private void manageProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        ProductDAO cDAO = new ProductDAO();
//        ArrayList<Product> product= cDAO.selectAllProduct();
//        request.setAttribute("product", product);
//        rd = request.getRequestDispatcher("viewProduct.jsp");
//        rd.forward(request, response);  
//    }
//
//    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        ProductDAO cDAO = new ProductDAO();
//        Product c = cDAO.selectById(Integer.parseInt(request.getParameter("id")));
//        if(cDAO.deleteProduct(c) > 0 ) {
//            request.setAttribute("message", "delete successfully !!");
//        }
//        else{
//            request.setAttribute("message", "delete failed !!");
//        }
//        rd = request.getRequestDispatcher("ProductionController?act=manageProduct");
//        rd.forward(request, response);  
//    }
//
//    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//        //declare variable local
//        String imgUrl = null ;
//        String productName = "";
//        String quantity = "";
//        String productId = "";
//        
//        Product c = new Product();
//        ProductDAO cDAO = new ProductDAO();
//        
//        // location to store file uploaded
//        String UPLOAD_DIRECTORY = "image/product";
//        
//        // checks if the request actually contains upload file
//        if (ServletFileUpload.isMultipartContent(request)) {
//            long serialVersionUID = 1L;
//
//            
//
//            // upload settings
//            int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
//            int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
//            int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
//            
//            // configures upload settings
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            // sets memory threshold - beyond which files are stored in disk
//            factory.setSizeThreshold(MEMORY_THRESHOLD);
//            // sets temporary location to store files
//            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//
//            ServletFileUpload upload = new ServletFileUpload(factory);
//
//            // sets maximum size of upload file
//            upload.setFileSizeMax(MAX_FILE_SIZE);
//
//            // sets maximum size of request (include file + form data)
//            upload.setSizeMax(MAX_REQUEST_SIZE);
//
//            // constructs the directory path to store upload file
//            // this path is relative to application's directory
//            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//
//            // creates the directory if it does not exist
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            try {
//                // parses the request's content to extract file data
//                @SuppressWarnings("unchecked")
//                List<FileItem> formItems = upload.parseRequest(request);
//
//                if (formItems != null && formItems.size() > 0) {
//                    // iterates over form's fields
//                    for (FileItem item : formItems) {
//                        // processes only fields that are not form fields
//                        if (!item.isFormField()) {
//                            String fileName = new File(item.getName()).getName();
//                            String filePath = uploadPath + File.separator + fileName;
//                            File storeFile = new File(filePath);
//                            imgUrl = UPLOAD_DIRECTORY + "/" + fileName;
//                            // saves the file on disk
//                            item.write(storeFile);
//                        }
//                        else{
//                            String fieldName = item.getFieldName();
//                            if(fieldName.equals("productId")){
//                                productId = item.getString();
//                            }
//                            else if(fieldName.equals("productName")){
//                                productName = item.getString();
//                            }
//                            else if(fieldName.equals("quantity")){
//                                quantity = item.getString();
//                            }
//                        }
//                    }
//                }
//            } catch (Exception ex) {
//                request.setAttribute("message",
//                        "There was an error: " + ex.getMessage());
//            }
//        }
//        
//        c = cDAO.selectById(Integer.parseInt(productId));
//        
//        //for image not update
//        if(imgUrl.equals(UPLOAD_DIRECTORY + "/")) {
//            imgUrl = c.getImgUrl();
//        }
//        
//        if(cDAO.updateProduct(c, productName, Integer.parseInt(productId), Integer.parseInt(quantity), imgUrl) > 0 ) {
//            request.setAttribute("message", "update successfully !!");
//        }
//        else{
//            request.setAttribute("message", "update failed !!");
//        }
//        rd = request.getRequestDispatcher("ProductionController?act=manageProduct");
//        rd.forward(request, response);  
//    }
//
//    private void formUpdateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        ProductDAO cDAO = new ProductDAO();
//        ArrayList<Product> product= cDAO.selectAllProduct();
//        Product productData = cDAO.selectById(Integer.parseInt(request.getParameter("id")));
//        request.setAttribute("product", product);
//        request.setAttribute("productData", productData);
//        rd = request.getRequestDispatcher("ProductionController?act=manageProduct");
//        rd.forward(request, response); 
//    }
//
//    private void saveProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //declare variable local
//        String imgUrl = "" ;
//        Product c = new Product();
//        ProductDAO cDAO = new ProductDAO();
//        
//        // checks if the request actually contains upload file
//        if (ServletFileUpload.isMultipartContent(request)) {
//            long serialVersionUID = 1L;
//
//            // location to store file uploaded
//            String UPLOAD_DIRECTORY = "image/product";
//
//            // upload settings
//            int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
//            int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
//            int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
//            
//            // configures upload settings
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            // sets memory threshold - beyond which files are stored in disk
//            factory.setSizeThreshold(MEMORY_THRESHOLD);
//            // sets temporary location to store files
//            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//
//            ServletFileUpload upload = new ServletFileUpload(factory);
//
//            // sets maximum size of upload file
//            upload.setFileSizeMax(MAX_FILE_SIZE);
//
//            // sets maximum size of request (include file + form data)
//            upload.setSizeMax(MAX_REQUEST_SIZE);
//
//            // constructs the directory path to store upload file
//            // this path is relative to application's directory
//            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//
//            // creates the directory if it does not exist
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            try {
//                // parses the request's content to extract file data
//                @SuppressWarnings("unchecked")
//                List<FileItem> formItems = upload.parseRequest(request);
//
//                if (formItems != null && formItems.size() > 0) {
//                    // iterates over form's fields
//                    for (FileItem item : formItems) {
//                        // processes only fields that are not form fields
//                        if (!item.isFormField()) {
//                            String fileName = new File(item.getName()).getName();
//                            String filePath = uploadPath + File.separator + fileName;
//                            File storeFile = new File(filePath);
//                            imgUrl = UPLOAD_DIRECTORY + "/" + fileName;
//                            // saves the file on disk
//                            item.write(storeFile);
//                        }
//                        else{
//                            String fieldName = item.getFieldName();
//                            if(fieldName.equals("productName")){
//                                c.setProductName(item.getString());
//                            }
//                            else if(fieldName.equals("quantity")){
//                                c.setQuantity(Integer.parseInt(item.getString()));
//                            }
//                        }
//                    }
//                }
//            } catch (Exception ex) {
//                request.setAttribute("message",
//                        "There was an error: " + ex.getMessage());
//            }
//        }
//        c.setImgUrl(imgUrl);
//        
//        if(cDAO.saveProduct(c) > 0 ) {
//            request.setAttribute("message", "save successfully !!");
//        }
//        else{
//            request.setAttribute("message", "save failed !!");
//        }
//        rd = request.getRequestDispatcher("ProductionController?act=manageProduct");
//        rd.forward(request, response);  
//    }
//
//    private void managePart(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        PartDAO cDAO = new PartDAO();
//        ProductDAO pDAO = new ProductDAO();
//        ArrayList<Part> part= cDAO.selectAllPart();
//        ArrayList<Product> product= pDAO.selectAllProduct();
//        request.setAttribute("part", part);
//        request.setAttribute("product", product);
//        rd = request.getRequestDispatcher("viewPart.jsp");
//        rd.forward(request, response);  
//    }
//
//    private void deletePart(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        PartDAO cDAO = new PartDAO();
//        Part c = cDAO.selectById(Integer.parseInt(request.getParameter("id")));
//        if(cDAO.deletePart(c) > 0 ) {
//            request.setAttribute("message", "delete successfully !!");
//        }
//        else{
//            request.setAttribute("message", "delete failed !!");
//        }
//        rd = request.getRequestDispatcher("ProductionController?act=manageComponent");
//        rd.forward(request, response);  
//    }
//
//    private void updatePart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//        //declare variable local
//        String imgUrl = null ;
//        String partName = "";
//        String productId = "";
//        String quantity = "";
//        String partId = "";
//        
//        Part c = new Part();
//        PartDAO cDAO = new PartDAO();
//        ProductDAO pDAO = new ProductDAO();
//        String UPLOAD_DIRECTORY = "image/part";
//        
//        // checks if the request actually contains upload file
//        if (ServletFileUpload.isMultipartContent(request)) {
//            long serialVersionUID = 1L;
//
//            // location to store file uploaded
//            
//
//            // upload settings
//            int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
//            int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
//            int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
//            
//            // configures upload settings
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            // sets memory threshold - beyond which files are stored in disk
//            factory.setSizeThreshold(MEMORY_THRESHOLD);
//            // sets temporary location to store files
//            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//
//            ServletFileUpload upload = new ServletFileUpload(factory);
//
//            // sets maximum size of upload file
//            upload.setFileSizeMax(MAX_FILE_SIZE);
//
//            // sets maximum size of request (include file + form data)
//            upload.setSizeMax(MAX_REQUEST_SIZE);
//
//            // constructs the directory path to store upload file
//            // this path is relative to application's directory
//            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//
//            // creates the directory if it does not exist
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            try {
//                // parses the request's content to extract file data
//                @SuppressWarnings("unchecked")
//                List<FileItem> formItems = upload.parseRequest(request);
//
//                if (formItems != null && formItems.size() > 0) {
//                    // iterates over form's fields
//                    for (FileItem item : formItems) {
//                        // processes only fields that are not form fields
//                        if (!item.isFormField()) {
//                            String fileName = new File(item.getName()).getName();
//                            String filePath = uploadPath + File.separator + fileName;
//                            File storeFile = new File(filePath);
//                            imgUrl = UPLOAD_DIRECTORY + "/" + fileName;
//                            // saves the file on disk
//                            item.write(storeFile);
//                        }
//                        else{
//                            String fieldName = item.getFieldName();
//                            if(fieldName.equals("partId")){
//                                partId = item.getString();
//                            }
//                            else if(fieldName.equals("partName")){
//                                partName = item.getString();
//                            }
//                            else if(fieldName.equals("productId")){
//                                productId = item.getString();
//                            }
//                            else if(fieldName.equals("quantity")){
//                                quantity = item.getString();
//                            }
//                        }
//                    }
//                }
//            } catch (Exception ex) {
//                request.setAttribute("message",
//                        "There was an error: " + ex.getMessage());
//            }
//        }
//        
//        c = cDAO.selectById(Integer.parseInt(partId));
//        
//        //for image not update
//        if(imgUrl.equals(UPLOAD_DIRECTORY + "/")) {
//            imgUrl = c.getImgUrl();
//        }
//        
//        if(cDAO.updatePart(c, partName, Integer.parseInt(productId), Integer.parseInt(quantity), imgUrl) > 0 ) {
//            request.setAttribute("message", "update successfully !!");
//        }
//        else{
//            request.setAttribute("message", "update failed !!");
//        }
//        rd = request.getRequestDispatcher("ProductionController?act=managePart");
//        rd.forward(request, response);  
//    }
//
//    private void formUpdatePart(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        PartDAO cDAO = new PartDAO();
//        ProductDAO pDAO = new ProductDAO();
//        ArrayList<Part> part= cDAO.selectAllPart();
//        ArrayList<Product> product= pDAO.selectAllProduct();
//        Part partData = cDAO.selectById(Integer.parseInt(request.getParameter("id")));
//        request.setAttribute("part", part);
//        request.setAttribute("product", product);
//        request.setAttribute("partData", partData);
//        rd = request.getRequestDispatcher("ProductionController?act=managePart");
//        rd.forward(request, response); 
//    }
//
//    private void savePart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //declare variable local
//        String imgUrl = "" ;
//        Part c = new Part();
//        PartDAO cDAO = new PartDAO();
//        ProductDAO pDAO = new ProductDAO();
//        
//        // checks if the request actually contains upload file
//        if (ServletFileUpload.isMultipartContent(request)) {
//            long serialVersionUID = 1L;
//
//            // location to store file uploaded
//            String UPLOAD_DIRECTORY = "image/part";
//
//            // upload settings
//            int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
//            int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
//            int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
//            
//            // configures upload settings
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            // sets memory threshold - beyond which files are stored in disk
//            factory.setSizeThreshold(MEMORY_THRESHOLD);
//            // sets temporary location to store files
//            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//
//            ServletFileUpload upload = new ServletFileUpload(factory);
//
//            // sets maximum size of upload file
//            upload.setFileSizeMax(MAX_FILE_SIZE);
//
//            // sets maximum size of request (include file + form data)
//            upload.setSizeMax(MAX_REQUEST_SIZE);
//
//            // constructs the directory path to store upload file
//            // this path is relative to application's directory
//            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//
//            // creates the directory if it does not exist
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            try {
//                // parses the request's content to extract file data
//                @SuppressWarnings("unchecked")
//                List<FileItem> formItems = upload.parseRequest(request);
//
//                if (formItems != null && formItems.size() > 0) {
//                    // iterates over form's fields
//                    for (FileItem item : formItems) {
//                        // processes only fields that are not form fields
//                        if (!item.isFormField()) {
//                            String fileName = new File(item.getName()).getName();
//                            String filePath = uploadPath + File.separator + fileName;
//                            File storeFile = new File(filePath);
//                            imgUrl = UPLOAD_DIRECTORY + "/" + fileName;
//                            // saves the file on disk
//                            item.write(storeFile);
//                        }
//                        else{
//                            String fieldName = item.getFieldName();
//                            if(fieldName.equals("partName")){
//                                c.setPartName(item.getString());
//                            }
//                            else if(fieldName.equals("productId")){
//                                c.setProduct(pDAO.selectById(Integer.parseInt(item.getString())));
//                            }
//                            else if(fieldName.equals("quantity")){
//                                c.setQuantity(Integer.parseInt(item.getString()));
//                            }
//                        }
//                    }
//                }
//            } catch (Exception ex) {
//                request.setAttribute("message",
//                        "There was an error: " + ex.getMessage());
//            }
//        }
//        c.setImgUrl(imgUrl);
//        
//        if(cDAO.savePart(c) > 0 ) {
//            request.setAttribute("message", "save successfully !!");
//        }
//        else{
//            request.setAttribute("message", "save failed !!");
//        }
//        rd = request.getRequestDispatcher("ProductionController?act=managePart");
//        rd.forward(request, response);  
//    }
//
//    private void manageComponent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
//        ComponentDAO cDAO = new ComponentDAO();
//        PartDAO pDAO = new PartDAO();
//        ArrayList<Component> component= cDAO.selectAllComponent();
//        ArrayList<Part> part= pDAO.selectAllPart();
//        request.setAttribute("component", component);
//        request.setAttribute("part", part);
//        rd = request.getRequestDispatcher("viewComponent.jsp");
//        rd.forward(request, response);  
//    }
//
//    private void deleteComponent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        ComponentDAO cDAO = new ComponentDAO();
//        Component c = cDAO.selectById(Integer.parseInt(request.getParameter("id")));
//        if(cDAO.deleteComponent(c) > 0 ) {
//            request.setAttribute("message", "delete successfully !!");
//        }
//        else{
//            request.setAttribute("message", "delete failed !!");
//        }
//        rd = request.getRequestDispatcher("ProductionController?act=manageComponent");
//        rd.forward(request, response);  
//    }
//
//    private void updateComponent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        //declare variable local
//        String imgUrl = null ;
//        String componentName = "";
//        String partId = "";
//        String quantity = "";
//        String componentId = "";
//        
//        Component c = new Component();
//        ComponentDAO cDAO = new ComponentDAO();
//        PartDAO pDAO = new PartDAO();
//        
//        // location to store file uploaded
//        String UPLOAD_DIRECTORY = "image/component";
//        
//        // checks if the request actually contains upload file
//        if (ServletFileUpload.isMultipartContent(request)) {
//            long serialVersionUID = 1L;
//
//            
//
//            // upload settings
//            int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
//            int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
//            int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
//            
//            // configures upload settings
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            // sets memory threshold - beyond which files are stored in disk
//            factory.setSizeThreshold(MEMORY_THRESHOLD);
//            // sets temporary location to store files
//            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//
//            ServletFileUpload upload = new ServletFileUpload(factory);
//
//            // sets maximum size of upload file
//            upload.setFileSizeMax(MAX_FILE_SIZE);
//
//            // sets maximum size of request (include file + form data)
//            upload.setSizeMax(MAX_REQUEST_SIZE);
//
//            // constructs the directory path to store upload file
//            // this path is relative to application's directory
//            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//
//            // creates the directory if it does not exist
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            try {
//                // parses the request's content to extract file data
//                @SuppressWarnings("unchecked")
//                List<FileItem> formItems = upload.parseRequest(request);
//
//                if (formItems != null && formItems.size() > 0) {
//                    // iterates over form's fields
//                    for (FileItem item : formItems) {
//                        // processes only fields that are not form fields
//                        if (!item.isFormField()) {
//                            String fileName = new File(item.getName()).getName();
//                            String filePath = uploadPath + File.separator + fileName;
//                            File storeFile = new File(filePath);
//                            imgUrl = UPLOAD_DIRECTORY + "/" + fileName;
//                            // saves the file on disk
//                            item.write(storeFile);
//                        }
//                        else{
//                            String fieldName = item.getFieldName();
//                            if(fieldName.equals("compId")){
//                                componentId = item.getString();
//                            }
//                            else if(fieldName.equals("compName")){
//                                componentName = item.getString();
//                            }
//                            else if(fieldName.equals("compPart")){
//                                partId = item.getString();
//                            }
//                            else if(fieldName.equals("compQuant")){
//                                quantity = item.getString();
//                            }
//                        }
//                    }
//                }
//            } catch (Exception ex) {
//                request.setAttribute("message",
//                        "There was an error: " + ex.getMessage());
//            }
//        }
//        
//        c = cDAO.selectById(Integer.parseInt(componentId));
//        
//        //for image not update
//        if(imgUrl.equals(UPLOAD_DIRECTORY + "/")) {
//            imgUrl = c.getImgUrl();
//        }
//        
//        if(cDAO.updateComponent(c, componentName, Integer.parseInt(partId), Integer.parseInt(quantity), imgUrl) > 0 ) {
//            request.setAttribute("message", "update successfully !!");
//        }
//        else{
//            request.setAttribute("message", "update failed !!");
//        }
//        rd = request.getRequestDispatcher("ProductionController?act=manageComponent");
//        rd.forward(request, response);  
//    }
//
//    private void formUpdateComponent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
//        ComponentDAO cDAO = new ComponentDAO();
//        PartDAO pDAO = new PartDAO();
//        ArrayList<Component> component= cDAO.selectAllComponent();
//        ArrayList<Part> part= pDAO.selectAllPart();
//        Component componentData = cDAO.selectById(Integer.parseInt(request.getParameter("id")));
//        request.setAttribute("component", component);
//        request.setAttribute("part", part);
//        request.setAttribute("componentData", componentData);
//        rd = request.getRequestDispatcher("ProductionController?act=manageComponent");
//        rd.forward(request, response);  
//    }
//
//    private void formUpdateOrderComponent(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        ComponentDAO cDAO = new ComponentDAO();
//        OrderComponentDAO dao = new OrderComponentDAO();
//        ArrayList<OrderComponent> orderComponent= dao.selectAllOrderComponent();
//        ArrayList<Component> component= cDAO.selectAllComponent();
//        OrderComponent orderComponentData = dao.selectById(Integer.parseInt(request.getParameter("id")));
//        request.setAttribute("orderComponent", orderComponent);
//        request.setAttribute("component", component);
//        request.setAttribute("orderComponentData", orderComponentData);
//        rd = request.getRequestDispatcher("viewOrderComponent.jsp");
//        rd.forward(request, response);
//    }
//}
