<%-- 
    Document   : index
    Created on : Apr 9, 2015, 10:42:22 PM
    Author     : Akip Maulana
--%>

<%@page import="config.GlobalData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><% 
                session.setMaxInactiveInterval(60*60*24);
                session.setAttribute(GlobalData.IS_LOGIN, null);
                session.setAttribute("adminis", null);
                response.sendRedirect("LoginServlet"); 
            %></h1>
    </body>
</html>
