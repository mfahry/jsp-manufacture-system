<%-- 
    Document   : viewCostProduction
    Created on : May 1, 2015, 7:14:28 PM
    Author     : JUN
--%>

<%@page import="model.Product"%>
<%@page import="model.CostProduction"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% if (session.getAttribute(GlobalData.IS_LOGIN) != null) {%>
<%@include file="template_header.jsp" %>
<body class="skin-blue">
    <div class="wrapper">

        <!--Load navbar-->
        <jsp:include page="template_navbar.jsp" />

        <!--Load sidebar-->
        <jsp:include page="template_sidebar.jsp" />


        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    Managing
                    <small>Cost Production</small>
                    <div class="form-group has-warning">
                        <small><label class="control-label" for="inputSuccess">message : <i class="fa fa-warning"></i> <%=request.getAttribute("message")%></label></small>
                    </div>
                </h1>
            </section>

            <!-- Main content -->
            <section class="content">
                <div class="row">
                    <div class="col-md-8">
                        <!-- TABLE: LATEST ORDERS -->
                        <div class="box box-info">
                            <div class="box-header with-border">
                                <h3 class="box-title">Latest Cost Production</h3>
                                <div class="box-tools pull-right">
                                    <!--<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>-->
                                    <!--<button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>-->
                                </div>
                            </div><!-- /.box-header -->
                            <div class="box-body">
                                <table id="tableD" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>Product Name</th>
                                            <th>Production Year</th>
                                            <th>Amount</th>
                                            <th>Used Amount</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<CostProduction> listCostDao = (ArrayList<CostProduction>) request.getAttribute("listCostDao");
                                            for (CostProduction row : listCostDao) {

                                        %>
                                        <tr>
                                            <td><%= row.getProduct().getProductName()%></td>
                                            <td><%= row.getProductionYear()%></td>
                                            <td><%= row.getAmount()%></td>
                                            <td><%= row.getUsedAmount()%></td>
                                            <td align="right">
                                                <div class="btn-group">
                                                   <a class="btn btn-default btn-sm btn-custom-detail" data-toggle="modal" data-target="#myModal" cs-data="<% out.print(row.getProduct().getProductName()
                                                               + "|" + row.getProductionYear() + "|" + row.getAmount() + "|" + row.getUsedAmount());%>"><i class="fa fa-list-alt"></i></a>
                                                    <a class="btn btn-default btn-sm" href="FinanceController?act=edit&id=<%=row.getProduct().getProductId()%>&year=<%=row.getProductionYear()%>"><i class="fa fa-edit"></i></a>
                                                    <a class="btn btn-default btn-sm btn-custom-delete" data-toggle="modal" data-target="#myModalDel" 
                                                       cs-id="<%=row.getProduct().getProductId()%>" cs-name="<%=row.getProduct().getProductName()%>" cs-year="<%=row.getProductionYear()%>"><i class="fa fa-trash-o"></i></a>
                                                    <!--<button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal"><i class="fa fa-list-alt"></i></button>-->
                                                    <!--<button class="btn btn-default btn-sm"><i class="fa fa-edit"></i></button>-->
                                                    <!--<button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModalDel"><i class="fa fa-trash-o"></i></button>-->
                                                </div><!-- /.btn-group -->
                                            </td>
                                        </tr>
                                        <% }%>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>Product Name</th>
                                            <th>Production Year</th>
                                            <th>Amount</th>
                                            <th>Used Amount</th>
                                            <th>Actions</th>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div><!-- /.box-body -->
                            <!-- /.box-footer -->
                        </div><!-- /.box -->
                    </div><!-- /.col -->
                    <div class="col-md-4">
                        <!-- FORM COMPONENT -->
                        <!-- general form elements -->
                        <div class="box box-primary">
                            <div class="box-header">
                                <h3 class="box-title">Cost Production Form</h3>
                            </div><!-- /.box-header -->
                            <%
                                CostProduction cosProData = (CostProduction) request.getAttribute("cosProData");
                                String actionForm = "FinanceController?act=save";
                                int productId = 0;
                                String productName = "";
                                String productYear = "";
                                int productAmount = 0;
                                int productUsed = 0;

                                String isDisable = "";

                                if (cosProData != null) {
                                    actionForm = "FinanceController?act=update";
                                    productId = cosProData.getProduct().getProductId();
                                    productName = cosProData.getProduct().getProductName();
                                    productYear = cosProData.getProductionYear();
                                    productAmount = cosProData.getAmount();
                                    productUsed = cosProData.getUsedAmount();
                                    isDisable = "disabled=''";
                                }
                            %>
                            <!-- form start -->
                            <form method="POST" role="form" action="<%= actionForm%>">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="compName">Product Name</label>
                                        <input type="hidden" id="compId" name="costId" value="<%= productId%>">
                                        <% if (isDisable != "") {%>
                                        <input type="hidden" id="costName" name="costName" value="<%= productId%>">
                                        <input type="hidden" id="costYear" name="costYear" value="<%= productYear%>">
                                        <% }%>
                                        <!--<input type="text" class="form-control" id="costName" value="<%= productName%>" placeholder="Enter Name">-->
                                        <select class="form-control" id="costName" name="costName" placeholder="Enter Product" <%= isDisable%>>
                                            <%
                                                ArrayList<Product> productList = (ArrayList<Product>) request.getAttribute("productList");
                                                for (Product row : productList) {
                                                    if (row.getProductId() == productId) {%>
                                            <option value="<%=row.getProductId()%>" selected="selected"><%= row.getProductName()%></option>
                                            <%
                                            } else {%>
                                            <option value="<%=row.getProductId()%>"><%=row.getProductName()%></option>
                                            <%
                                                    }
                                                }%>
                                        </select>
                                    </div>
                                    <!-- select -->
                                    <div class="form-group">
                                        <label for="costYear">Production Year</label>
                                        <select class="form-control" id="costYear" name="costYear" placeholder="Enter Product" <%= isDisable%>>
                                            <%
                                                    if (productYear != "") {%>
                                            <option value="<%= productYear%>" selected="selected"><%= productYear%></option>
                                            <%

                                                    }%>
                                            <option value="2014">2014</option>
                                            <option value="2015">2015</option>
                                            <option value="2016">2016</option>
                                            <option value="2017">2017</option>
                                        </select>
                                        <!--<input type="text" maxlength="4" class="form-control" id="costYear" name="costYear" value="<%= productYear%>" placeholder="Enter Quantity" <%= isDisable%>>-->
                                    </div>
                                    <div class="form-group">
                                        <label for="compQuant">Amount</label>
                                        <input type="text" class="form-control" id="costAmount" name="costAmount" value="<%= productAmount%>" placeholder="Enter Quantity">
                                    </div>
                                    <div class="form-group">
                                        <label for="compQuant">Used Amount</label>
                                        <input type="text" class="form-control" id="costUsed" value="<%= productUsed%>" name="costUsed" placeholder="Enter Quantity">
                                    </div>
                                </div><!-- /.box-body -->

                                <div class="box-footer">
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </div>
                            </form>
                        </div><!-- /.box -->
                    </div><!-- /.col -->

                    <!-- Modal Component Detail-->
                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Cost Production Detail</h4>
                                </div>
                                <div class="modal-body">
                                    <table class="table" style="border:0px;">
                                        <tr>
                                            <td>Product Name</td>
                                            <td>:</td>
                                            <td id="product-name">Mouse</td>
                                        </tr>
                                        <tr>
                                            <td>Production Year</td>
                                            <td>:</td>
                                            <td id="product-year">1945</td>
                                        </tr>
                                        <tr>
                                            <td>Amount</td>
                                            <td>:</td>
                                            <td id="product-amount"></td>
                                        </tr>
                                        <tr>
                                            <td>Used Amount</td>
                                            <td>:</td>
                                            <td id="product-used"></td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="modal-footer">
                                    <!--                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                        <button type="button" class="btn btn-primary">Save changes</button>-->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end of modal ------------------------------>

                    <!-- Modal Delete Confirm-->
                    <div class="modal fade" id="myModalDel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Delete <label id="header-product-name"></label></h4>
                                </div>
                                <div class="modal-body">
                                    <center>
                                        <h3>Are you sure ?</h3>
                                    </center>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                                    <a href="" class="btn btn-danger">Yes</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end of modal ------------------------------>

                </div><!-- /.row -->

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
    <jsp:include page="template_tablefooter.jsp" />

    <!-- page script -->
    <script type="text/javascript">
        $(function () {
            $('#tableD').dataTable({
                "bPaginate": true,
                "bLengthChange": false,
                "bFilter": false,
                "bSort": true,
                "bInfo": true,
                "bAutoWidth": false
            });

            $(".btn-custom-delete").click(function () {
                var id = $(this).attr("cs-id");
                var year = $(this).attr("cs-year");
                var name = $(this).attr("cs-name");
                //alert(id);
                $("#myModalDel .modal-footer a").attr("href", "FinanceController?act=delete&id=" + id +"&year="+year);
                $("#header-product-name").html(name);
            });

            $(".btn-custom-detail").click(function () {
                var data = $(this).attr("cs-data");
                var arr = data.split("|");

                $("#product-name").html(arr[0]);
                $("#product-year").html(arr[1]);
                $("#product-amount").html(arr[2]);
                $("#product-used").html(arr[3]);
            });
        });
    </script>
    <% } else {
            response.sendRedirect("LoginServlet");
        }%>