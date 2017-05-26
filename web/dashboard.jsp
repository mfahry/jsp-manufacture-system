<%-- 
 /* global Morris */

   Document   : dashboard
    Created on : Apr 10, 2015, 9:40:31 AM
    Author     : Akip Maulana
--%>

<%@page import="javax.persistence.criteria.Order"%>
<%@page import="model.OrderComponent"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="model.CostProduction"%>
<%@page import="dao.CostProductionDAO"%>
<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--Load header-->
<jsp:include page="template_header.jsp" />
<!-- Morris charts -->
<body class="skin-blue">
    <div class="wrapper">

        <!--Load navbar-->
        <jsp:include page="template_navbar.jsp" />

        <!--Load sidebar-->
        <jsp:include page="template_sidebar.jsp" />
        
        <%
            String totalPart = request.getAttribute("totalPart").toString();
            String totalComp = request.getAttribute("totalComp").toString();
            String totalPro = request.getAttribute("totalPro").toString();
            String totalCost = request.getAttribute("totalCost").toString();
            
        %>

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    Dashboard
                    <small>ERP System</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                    <li class="active">Dashboard</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
                <!-- Info boxes -->
                <div class="row">
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="info-box">
                            <span class="info-box-icon bg-aqua"><i class="fa fa-gear"></i></span>
                            <div class="info-box-content" >
                                <span class="info-box-text">Part Total</span>
                                <span class="info-box-number"><%= totalPart %></span>
                            </div><!-- /.info-box-content -->
                        </div><!-- /.info-box -->
                    </div><!-- /.col -->
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="info-box">
                            <span class="info-box-icon bg-red"><i class="fa fa-gears"></i></span>
                            <div class="info-box-content">
                                <span class="info-box-text">COMPONENT TOTAL</span>
                                <span class="info-box-number"><%= totalComp %></span>
                            </div><!-- /.info-box-content -->
                        </div><!-- /.info-box -->
                    </div><!-- /.col -->

                    <!-- fix for small devices only -->
                    <div class="clearfix visible-sm-block"></div>

                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="info-box">
                            <span class="info-box-icon bg-yellow"><i class="fa fa-truck"></i></span>
                            <div class="info-box-content">
                                <span class="info-box-text">Product Total</span>
                                <span class="info-box-number"><%= totalPro %></span>
                            </div><!-- /.info-box-content -->
                        </div><!-- /.info-box -->
                    </div><!-- /.col -->

                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <div class="info-box">
                            <span class="info-box-icon bg-green"><i class="fa fa-usd"></i></span>
                            <div class="info-box-content">
                                <span class="info-box-text">Cost Production</span>
                                <span class="info-box-number"><%= "Rp "+totalCost+",-" %></span>
                            </div><!-- /.info-box-content -->
                        </div><!-- /.info-box -->
                    </div><!-- /.col -->                    
                </div><!-- /.row -->

                <div class="row">
                    <div class="col-md-4">
                        <div class="box">
                            <div class="box-header with-border">
                                <h3 class="box-title">Each Part Report (Donut Chart)</h3>                                
                            </div><!-- /.box-header -->
                            <div class="box-body">

                                <!-- DONUT CHART -->
                                <div class="box box-danger">
                                    <div class="box-body chart-responsive">
                                        <div class="chart" id="sales-chart" style="height: 300px; position: relative;"></div>
                                    </div><!-- /.box-body -->
                                </div><!-- /.box -->

                            </div><!-- ./box-body -->
                        </div><!-- /.box -->

                    </div><!-- /.col -->

                    <div class="col-md-8">
                        <div class="box">
                            <div class="box-header with-border">
                                <h3 class="box-title">Cost Production (Bar Chart)</h3>                                
                            </div><!-- /.box-header -->
                            <div class="box-body">

                                <!-- BAR CHART -->
                                <div class="box box-success">
                                    <div class="box-body chart-responsive">
                                        <div class="chart" id="bar-chart" style="height: 300px;"></div>
                                    </div><!-- /.box-body -->
                                </div><!-- /.box -->

                            </div><!-- ./box-body -->
                        </div><!-- /.box -->
                    </div>


                </div><!-- /.row -->
                <div class="row">
                    <div class="col-md-8">
                        <!-- TABLE: LATEST ORDERS -->
                        <div class="box box-title">
                            <div class="box-header with-border">
                                <h3 class="box-title">Latest Orders</h3>
                            </div><!-- /.box-header -->
                            <div class="box-body">
                                <div class="table-responsive">
                                    <table class="table no-margin">
                                        <thead>
                                            <tr>
                                                <th>Order ID</th>
                                                <th>Item</th>
                                                <th>Date Order</th>
                                                <th>Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                            ArrayList<OrderComponent> order = (ArrayList<OrderComponent>)request.getAttribute("order");
                                            int countData = order.size();
                                            int countShow = countData>10?10:countData;
                                            for(int i = 0; i < countShow; i++){
                                            %>
                                                <tr>
                                                    <td>ORD<%=order.get(i).getOrderComponentId()%></td>
                                                    <td><%=order.get(i).getComponent().getComponentName()%></td>
                                                    <td><%=order.get(i).getOrderDate()%></td>
                                                    <td><%
                                                        if(order.get(i).getStatus() == 0){ %>
                                                            <small><label class="label label-danger">yet purchased</label></small>
                                                        <%
                                                        }
                                                        else { %>
                                                            <small><label class="label label-info">purchased</label></small>
                                                        <%
                                                        } %>
                                                    </td>
                                                </tr>
                                            <%
                                            } %>
                                        </tbody>
                                    </table>
                                </div><!-- /.table-responsive -->
                            </div><!-- /.box-body -->
                        </div><!-- /.box -->
                    </div><!-- /.col -->

                    <div class="col-md-4">
                        <!-- PRODUCT LIST -->
                        <div class="box box-title">
                            <div class="box-header with-border">
                                <h3 class="box-title">Recently Added Products</h3>                            
                            </div><!-- /.box-header -->
                            <div class="box-body">
                                <ul class="products-list product-list-in-box">
                                    <% 
                                        ArrayList<Product> product = (ArrayList<Product>) request.getAttribute("product"); 
                                        List<Product> products = product.subList(0, 2);
                                        CostProductionDAO costDao = new CostProductionDAO();
                                        Calendar now = Calendar.getInstance();
                                        int year = now.get(Calendar.YEAR);
                                        
                                        for(Product row : products){
                                            CostProduction costPro = costDao.selectById(new String[]{"PRODUCT_ID","PRODUCTION_YEAR"}, 
                                                    new Integer[]{row.getProductId(), year});
                                            
                                    %>
                                        <li class="item">
                                            <div class="product-img">
                                                <img src="<%= row.getImgUrl() %>" alt="Product Image"/>
                                            </div>
                                            <div class="product-info">
                                                <a href="javascript::;" class="product-title"><%= row.getProductName() %>
                                                    <span class="label label-success pull-right"><%= costPro.getProductionYear() %></span></a>
                                                <span class="product-description">
                                                    <table class="table table-mailbox">
                                                        <tr>
                                                            <td>Amount</td>
                                                            <td>:</td>
                                                            <td><%= costPro.getAmount() %></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Used Amount</td>
                                                            <td>:</td>
                                                            <td><%= costPro.getUsedAmount()%></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Quality</td>
                                                            <td>:</td>
                                                            <td><%= row.getQuantity()%></td>
                                                        </tr>
                                                    </table>
                                                </span>
                                            </div>
                                        </li><!-- /.item -->
                                    <% } %>
                                </ul>
                            </div><!-- /.box-body -->
                            <div class="box-footer text-center">
                                <a href="ProductionController?act=manageProduct" class="uppercase">View All Products</a>
                            </div><!-- /.box-footer -->
                        </div><!-- /.box -->
                    </div><!-- /.col -->
                </div>

            </section><!-- /.content -->
        </div><!-- /.content-wrapper -->

        <footer class="main-footer">
            <div class="pull-right hidden-xs">
                <b>Version</b> 2.0
            </div>
            <strong>Copyright &copy; 2014-2015 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights reserved.
        </footer>

    </div><!-- ./wrapper -->

    <!--Load footer-->
    <jsp:include page="template_footer.jsp" />

    <!-- page script -->
    <script type="text/javascript">
        $(function () {
            "use strict";
            //DONUT CHART
            var donut = new Morris.Donut({
                element: 'sales-chart',
                resize: true,
                colors: ["#f39c12", "#dd4b39", "#00c0ef"],
                data: [
                    {label: "Product Total", value: <%= totalPro %>},
                    {label: "Component Total", value: <%= totalComp %>},
                    {label: "Part Total", value: <%= totalPart %>}
                ],
                hideHover: 'auto'
            });
            
            //BAR CHART
            var bar = new Morris.Bar({
              element: 'bar-chart',
              resize: true,
              data: [
                <% 
                    ArrayList<CostProduction> grafikCost = 
                            (ArrayList<CostProduction>) request.getAttribute("grafikCost");
                    for (CostProduction row : grafikCost){ %>
                        {y: '<%= row.getProductionYear() %>', a: <%= row.getAmount() %>, b: <%= row.getUsedAmount() %>},
                <%    }
                %>
              ],
              barColors: ['#00a65a', '#f56954'],
              xkey: 'y',
              ykeys: ['a', 'b'],
              labels: ['Amount', 'Used Amount'],
              hideHover: 'auto'
            });
        });
    </script>