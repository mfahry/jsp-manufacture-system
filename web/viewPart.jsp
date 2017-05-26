<%-- 
    Document   : viewComponent
    Created on : Apr 23, 2015, 11:42:23 PM
    Author     : ACER
--%>

<%@page import="model.Product"%>
<%@page import="model.Component"%>
<%@page import="model.Part"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                    <small>Part</small><br/>
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
                                <h3 class="box-title">Latest Component</h3>
                                <div class="box-tools pull-right">
                                    <!--<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>-->
                                    <!--<button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>-->
                                </div>
                            </div><!-- /.box-header -->
                            <div class="box-body">
                                <table id="tableD" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>Part Name</th>
                                            <th>Product</th>
                                            <th>Quantity</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<Part> listData = (ArrayList<Part>) request.getAttribute("part");
                                            for (int i = 0; i < listData.size(); i++) {
                                        %>
                                        <tr>
                                            <td><%=listData.get(i).getPartName()%></td>
                                            <td><%=listData.get(i).getProduct().getProductName()%></td>
                                            <td><%=listData.get(i).getQuantity()%></td>
                                            <td align="right">
                                                <div class="btn-group">
                                                    <a class="btn btn-default btn-sm btn-custom-detail" data-toggle="modal" data-target="#myModal" cs-data="<% out.print(listData.get(i).getPartName() + "|" + listData.get(i).getProduct().getProductName() + "|" + listData.get(i).getQuantity() + "|" + listData.get(i).getImgUrl());%>"><i class="fa fa-list-alt"></i></a>
                                                    <a class="btn btn-default btn-sm" href="ProductionController?act=formUpdatePart&id=<%=listData.get(i).getPartId()%>"><i class="fa fa-edit"></i></a>
                                                    <a class="btn btn-default btn-sm btn-custom-delete" data-toggle="modal" data-target="#myModalDel" cs-id="<%=listData.get(i).getPartId()%>" cs-name="<%=listData.get(i).getPartName()%>"><i class="fa fa-trash-o"></i></a>
                                                </div><!-- /.btn-group -->
                                            </td>
                                        </tr>
                                        <% }%>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>Part Name</th>
                                            <th>Product</th>
                                            <th>Quantity</th>
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
                                <h3 class="box-title">Part Form</h3>
                            </div><!-- /.box-header -->
                            <!-- form start -->
                            <%
                                Part partData = (Part) request.getAttribute("partData");
                                if (partData != null) {%>
                            <form method="POST" enctype="multipart/form-data" action="ProductionController?act=updatePart">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="partName">Part Name</label>
                                        <input type="hidden" id="partId" name="partId" value="<%=partData.getPartId()%>">
                                        <input type="text" class="form-control" id="partName" name="partName" placeholder="Enter Name" value="<%=partData.getPartName()%>">
                                    </div>
                                    <!-- select -->
                                    <div class="form-group">
                                        <label for="productId">Product</label>
                                        <select class="form-control" id="productId" name="productId">
                                            <%
                                            ArrayList<Product> listProduct = (ArrayList<Product>) request.getAttribute("product");
                                            for (int i = 0; i < listProduct.size(); i++) {
                                                if (listProduct.get(i).getProductId() == partData.getProduct().getProductId()) {%>
                                            <option value="<%=listProduct.get(i).getProductId()%>" selected="selected"><%=listProduct.get(i).getProductName()%></option>
                                            <%
                                             } else {%>
                                            <option value="<%=listProduct.get(i).getProductId()%>"><%=listProduct.get(i).getProductName()%></option>
                                            <%
                                                }
                                            }%>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="quantity">Quantity</label>
                                        <input type="number" class="form-control" id="quantity"  name="quantity" placeholder="Enter Quantity" value="<%=partData.getQuantity()%>">
                                    </div>
                                    <div class="form-group">
                                        <label for="imgUrl">File Image</label>
                                        <input type="file" id="compImg" name="imgUrl">
                                    </div>
                                </div><!-- /.box-body -->

                                <%
                            } else { %>
                                <form method="POST" enctype="multipart/form-data" action="ProductionController?act=savePart">
                                    <div class="box-body">
                                        <div class="form-group">
                                            <label for="partName">Part Name</label>
                                            <input type="text" class="form-control"  name="partName" placeholder="Enter Name" >
                                        </div>
                                        <!-- select -->
                                        <div class="form-group">
                                            <label for="productId">Product Id</label>
                                            <select class="form-control"  name="productId">
                                                <%
                                            ArrayList<Product> listProduct = (ArrayList<Product>) request.getAttribute("product");
                                            for (int i = 0; i < listProduct.size(); i++) {%>
                                                <option value="<%=listProduct.get(i).getProductId()%>"><%=listProduct.get(i).getProductName()%></option>
                                                <%
                                            } %>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="quantity">Quantity</label>
                                            <input type="number" class="form-control" name="quantity" placeholder="Enter Quantity">
                                        </div>
                                        <div class="form-group">
                                            <label for="imgUrl">File Image</label>
                                            <input type="file" name="imgUrl">
                                        </div>
                                    </div><!-- /.box-body -->
                                    <%
                                        }
                                    %>                         
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
                                    <h4 class="modal-title" id="myModalLabel">Component Detail</h4>
                                </div>
                                <div class="modal-body">
                                    <table class="table" style="border:0px;">
                                        <tr>
                                            <td>Part Name</td>
                                            <td>:</td>
                                            <td id="part-name"></td>
                                        </tr>
                                        <tr>
                                            <td>Product</td>
                                            <td>:</td>
                                            <td id="product-name"></td>
                                        </tr>
                                        <tr>
                                            <td>Quantity</td>
                                            <td>:</td>
                                            <td id="part-quantity"></td>
                                        </tr>
                                        <tr>
                                            <td>Image</td>
                                            <td>:</td>
                                            <td id="img-url"><img src="" height="300" width="300"/></td>
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
                                    <h4 class="modal-title" id="myModalLabel">Delete <label id="header-part-name"></label></h4>
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
        $(document).ready(function () {

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
                var name = $(this).attr("cs-name");
                //alert(id);
                $("#myModalDel .modal-footer a").attr("href", "ProductionController?act=deletePart&id=" + id);
                $("#header-part-name").html(name);
            });

            $(".btn-custom-detail").click(function () {
                var data = $(this).attr("cs-data");
                var arr = data.split("|");

                $("#part-name").html(arr[0]);
                $("#product-name").html(arr[1]);
                $("#part-quantity").html(arr[2]);
                $("#img-url img").attr("src", arr[3]);
            });
        });


    </script>