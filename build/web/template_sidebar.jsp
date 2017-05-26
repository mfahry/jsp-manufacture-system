<%-- 
    Document   : template_sidebar
    Created on : Apr 10, 2015, 12:22:26 AM
    Author     : Akip Maulana
--%>

<%@page import="config.GlobalData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String dashboard = "";
    String component = "";
    String part = "";
    String order = "";
    String production = "";
    String product = "";
%>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" />
            </div>
            <div class="pull-left info">
                <p><%= request.getSession().getAttribute(GlobalData.USERGROUP_SESSION)+" Division" %></p>

                <a href="#"><i class="fa fa-circle text-success"></i> <%= request.getSession().getAttribute(GlobalData.USERID_SESSION) %></a>
            </div>
        </div>
        <!-- search form
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search..."/>
                <span class="input-group-btn">
                    <button type='submit' name='search' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
                </span>
            </div>
        </form>  -->
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>

            <li class="<%= dashboard%> treeview myMenu">
                <a href="DashboardController">
                    <i class="fa fa-dashboard"></i> <span>Dashboard</span> 
                </a>
            </li>

            <% if (session.getAttribute(GlobalData.USERGROUP_SESSION).equals("PRODUCTION") ||
                    session.getAttribute(GlobalData.USERGROUP_SESSION).equals("SUPERADMIN") ) {%>
            <li class="<%= product%> treeview myMenu">
                <a href="ProductionController?act=manageProduct">
                    <i class="fa fa-truck"></i> <span>Manage Product</span> 
                </a>
            </li>
            <% } %>

            <% if (session.getAttribute(GlobalData.USERGROUP_SESSION).equals("PRODUCTION")||
                    session.getAttribute(GlobalData.USERGROUP_SESSION).equals("SUPERADMIN")) {%>
            <li class="<%= part%> treeview myMenu">
                <a href="ProductionController?act=managePart">
                    <i class="fa fa-gear"></i> <span>Manage Part</span> 
                </a>
            </li>
            <% } %>

            <% if (session.getAttribute(GlobalData.USERGROUP_SESSION).equals("PRODUCTION")||
                    session.getAttribute(GlobalData.USERGROUP_SESSION).equals("SUPERADMIN")) {%>
            <li class="<%= component%> treeview myMenu">
                <a href="ProductionController?act=manageComponent">
                    <i class="fa fa-gears"></i> <span>Manage Component</span> 
                </a>
            </li>
            <% } %>

            
            <% if (!session.getAttribute(GlobalData.USERGROUP_SESSION).equals("FINANCE")) {%>
            <li class="<%= order%> treeview myMenu">
                <a href="ProductionController?act=manageOrderComponent">
                    <i class="fa fa-shopping-cart"></i> <span>Manage Order</span> 
                </a>
            </li>
            <% }%>
            
            <% if (session.getAttribute(GlobalData.USERGROUP_SESSION).equals("FINANCE")||
                    session.getAttribute(GlobalData.USERGROUP_SESSION).equals("SUPERADMIN")) {%>
            <li class="<%= production%> treeview myMenu">
                <a href="FinanceController">
                    <i class="fa fa-usd"></i> <span>Cost Production</span> 
                </a>
            </li>
            <% }%>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
