<%-- 
    Document   : viewComponent
    Created on : Apr 23, 2015, 11:42:23 PM
    Author     : ACER
--%>

<%@page import="java.sql.Date"%>
<%@page import="model.OrderComponent"%>
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
                    <small>Order Component</small><br/>
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
                                <h3 class="box-title">Latest Order Component</h3>
                                <div class="box-tools pull-right">
                                    <!--<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>-->
                                    <!--<button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>-->
                                </div>
                            </div><!-- /.box-header -->
                            <div class="box-body">
                                <table id="tableD" class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>Component Name</th>
                                            <th>Divisi</th>
                                            <th>Order Date</th>
                                            <th>Quantity</th>
                                            <th>Status</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            ArrayList<OrderComponent> listData = (ArrayList<OrderComponent>) request.getAttribute("orderComponent");
                                            for (int i = 0; i < listData.size(); i++) {
                                        %>
                                        <tr>
                                            <td><%=listData.get(i).getComponent().getComponentName()%></td>
                                            <td><%=listData.get(i).getUser().getUserGroup()%></td>
                                            <td><%=listData.get(i).getOrderDate()%></td>
                                            <td><%=listData.get(i).getQuantity()%></td>
                                            <td><%
                                                if (listData.get(i).getStatus() == 0) { %>
                                                <small><label class="label label-danger">yet purchased</label></small>
                                                    <%
                                                } else { %>
                                                <small><label class="label label-info">purchased</label></small>
                                                <%
                                                    }%>
                                            </td>
                                            <td align="right">
                                                <div class="btn-group">
                                                    <a class="btn btn-default btn-sm btn-custom-detail" data-toggle="modal" data-target="#myModal" cs-data="<%
                                                        out.print(
                                                                listData.get(i).getComponent().getComponentName() + "|" + listData.get(i).getUser().getUsername() + "|" + listData.get(i).getOrderDate() + "|"
                                                                + listData.get(i).getRequiredDate() + "|" + listData.get(i).getScheduleBuyDate() + "|" + listData.get(i).getScheduleBuyUser().getUsername() + "|"
                                                                + listData.get(i).getStatus() + "|" + listData.get(i).getQuantity() + "|" + listData.get(i).getCost()
                                                        );
                                                       %>"><i class="fa fa-list-alt"></i></a>
                                                    <a class="btn btn-default btn-sm" href="ProductionController?act=formUpdateOrderComponent&id=<%=listData.get(i).getOrderComponentId()%>"><i class="fa fa-edit"></i></a>
                                                    <a class="btn btn-default btn-sm btn-custom-delete" data-toggle="modal" data-target="#myModalDel" cs-id="<%=listData.get(i).getOrderComponentId()%>" cs-name="Ordered"><i class="fa fa-trash-o"></i></a>
                                                </div><!-- /.btn-group -->
                                            </td>
                                        </tr>
                                        <% }%>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>Component Name</th>
                                            <th>Group</th>
                                            <th>Order Date</th>
                                            <th>Quantity</th>
                                            <th>Status</th>
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
                                <h3 class="box-title">Product Form</h3>
                            </div><!-- /.box-header -->
                            <!-- form start -->
                            <%
                                OrderComponent orderComponentData = (OrderComponent) request.getAttribute("orderComponentData");
                                if (orderComponentData != null) {%>
                            <form method="POST"  action="ProductionController?act=updateOrderComponent">
                                <div class="box-body">
                                    <!-- select -->
                                    <div class="form-group">
                                        <input type="hidden" name="username" value="<%=orderComponentData.getUser().getUsername()%>">
                                        <input type="hidden" name="orderComponentId" value="<%=orderComponentData.getOrderComponentId()%>">
                                        <label for="componentId">Component</label>
                                        <select class="form-control" id="componentId" name="componentId" <%
                                            if(!session.getAttribute(GlobalData.USERGROUP_SESSION).equals("PRODUCTION")){
                                                out.print("readonly='readonly'");
                                            }
                                        %>>
                                            <%
                                                ArrayList<Component> listComponent = (ArrayList<Component>) request.getAttribute("component");
                                                for (int i = 0; i < listComponent.size(); i++) {
                                                    if (listComponent.get(i).getComponentId() == orderComponentData.getComponent().getComponentId()) {%>
                                            <option value="<%=listComponent.get(i).getComponentId()%>" selected="selected"><%=listComponent.get(i).getComponentName()%></option>
                                            <%
                                            } else {%>
                                            <option value="<%=listComponent.get(i).getComponentId()%>"><%=listComponent.get(i).getComponentName()%></option>
                                            <%
                                                    }
                                                }%>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="requiredDate">Required Date</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input class="form-control" data-inputmask="'alias': 'yyyy/mm/dd'" data-mask="" type="text" name="requiredDate" value="<%=orderComponentData.getRequiredDate()%>" <%
                                            if(!session.getAttribute(GlobalData.USERGROUP_SESSION).equals("PRODUCTION")){
                                                out.print("readonly='readonly'");
                                            }
                                        %>>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="scheduledBuyDate">Scheduled Buy</label>
                                        <input type="hidden" name="scheduledBuyUsername" value="<%=session.getAttribute(GlobalData.USERID_SESSION)%>" <%
                                            if(!session.getAttribute(GlobalData.USERGROUP_SESSION).equals("LOGISTIC")){
                                                out.print("readonly='readonly'");
                                            }
                                        %>>
                                        <div class="input-group">
                                            <div class="input-group-addon">
                                                <i class="fa fa-calendar"></i>
                                            </div>
                                            <input class="form-control" data-inputmask="'alias': 'yyyy/mm/dd'" data-mask="" type="text" name="scheduledBuyDate" value="<%=orderComponentData.getScheduleBuyDate()%>" <%
                                            if(!session.getAttribute(GlobalData.USERGROUP_SESSION).equals("LOGISTIC")){
                                                out.print("readonly='readonly'");
                                            }
                                        %>>
                                        </div>
                                    </div>    
                                    <div class="form-group">
                                        <label for="Status">Status</label>
                                        <select class="form-control" id="status" name="status" <%
                                            if(!session.getAttribute(GlobalData.USERGROUP_SESSION).equals("LOGISTIC")){
                                                out.print("readonly='readonly'");
                                            }
                                        %>>
                                            <option value="0">yet purchased</option>
                                            <option value="1">purchased</option>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="quantity">Quantity</label>
                                        <input type="number" class="form-control" id="quantity"  name="quantity" placeholder="Enter Quantity" value="<%=orderComponentData.getQuantity()%>" <%
                                            if(!session.getAttribute(GlobalData.USERGROUP_SESSION).equals("PRODUCTION")){
                                                out.print("readonly='readonly'");
                                            }
                                        %>>
                                    </div>    
                                    <div class="form-group">
                                        <label for="s">Cost</label>
                                        <input type="text" id="cost" name="cost" value="<%=orderComponentData.getCost()%>" class="form-control" placeholder="Enter Cost" <%
                                            if(!session.getAttribute(GlobalData.USERGROUP_SESSION).equals("LOGISTIC")){
                                                out.print("readonly='readonly'");
                                            }
                                        %>>
                                    </div>
                                </div><!-- /.box-body -->
                                <div class="box-footer">
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                <%
                                } else { %>
                                <form method="POST" action="ProductionController?act=saveOrderComponent">
                                    <div class="box-body">
                                        <!-- select -->
                                        <div class="form-group">
                                            <input type="hidden" name="username" value="<%=session.getAttribute(GlobalData.USERID_SESSION)%>"> 
                                            <label for="componentId">Component</label>
                                            <select class="form-control" id="componentId" name="componentId" <%
                                            if(!session.getAttribute(GlobalData.USERGROUP_SESSION).equals("PRODUCTION")){
                                                out.print("disabled='disabled'");
                                            }
                                        %>>
                                                <%
                                                    ArrayList<Component> listComponent = (ArrayList<Component>) request.getAttribute("component");
                                                    for (int i = 0; i < listComponent.size(); i++) {%>
                                                <option value="<%=listComponent.get(i).getComponentId()%>"><%=listComponent.get(i).getComponentName()%></option>
                                                <%
                                                    }%>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="requiredDate">Required Date</label>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <input class="form-control" id="datemask1" data-inputmask="'alias': 'yyyy/mm/dd'" data-mask="" type="text" name="requiredDate" <%
                                            if(!session.getAttribute(GlobalData.USERGROUP_SESSION).equals("PRODUCTION")){
                                                out.print("disabled='disable'");
                                            }
                                        %>>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="scheduledBuyDate">Scheduled Buy</label>
                                            <input type="hidden" name="scheduledBuyUsername" value="<%=session.getAttribute(GlobalData.USERID_SESSION)%>" <%
                                            if(!session.getAttribute(GlobalData.USERGROUP_SESSION).equals("LOGISTIC")){
                                                out.print("disabled='disable'");
                                            }
                                        %>>
                                            <div class="input-group">
                                                <div class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </div>
                                                <input class="form-control" id="datemask" data-inputmask="'alias': 'yyyy/mm/dd'" data-mask="" type="text" name="scheduledBuyDate" <% 
                                                    if(!session.getAttribute(GlobalData.USERGROUP_SESSION).equals("LOGISTIC")){
                                                        out.print("disabled='disable'");
                                                    }
                                                %>>
                                            </div>
                                        </div>    
                                        <div class="form-group">
                                            <label for="Status">Status</label>
                                            <select class="form-control" id="status" name="status" <% 
                                                    if(!session.getAttribute(GlobalData.USERGROUP_SESSION).equals("LOGISTIC")){
                                                        out.print("disabled='disable'");
                                                    }
                                            %>>
                                                <option value="0">yet purchased</option>
                                                <option value="1">purchased</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="quantity">Quantity</label>
                                            <input type="number" class="form-control" id="quantity"  name="quantity" placeholder="Enter Quantity" <%
                                            if(!session.getAttribute(GlobalData.USERGROUP_SESSION).equals("PRODUCTION")){
                                                out.print("disabled='disable'");
                                            }
                                        %>>
                                        </div>    
                                        <div class="form-group">
                                            <label for="s">Cost</label>
                                            <input type="text" id="cost" name="cost" class="form-control" placeholder="Enter Cost" <% 
                                                    if(!session.getAttribute(GlobalData.USERGROUP_SESSION).equals("LOGISTIC")){
                                                        out.print("disabled='disable'");
                                                    }
                                            %>>
                                        </div>
                                    </div><!-- /.box-body -->
                                    <div class="box-footer">
                                        <%
                                        if(session.getAttribute(GlobalData.USERGROUP_SESSION).equals("PRODUCTION")){%>
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        <%
                                    }%>
                                    <%
                                        }
                                    %>                         
                                    
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
                                    <h4 class="modal-title" id="myModalLabel">Order Component Detail</h4>
                                </div>
                                <div class="modal-body">
                                    <table class="table" style="border:0px;">
                                        <tr>
                                            <td>Component Name</td>
                                            <td>:</td>
                                            <td id="component-name"></td>
                                        </tr>
                                        <tr>
                                            <td>Orders By</td>
                                            <td>:</td>
                                            <td id="user"></td>
                                        </tr>
                                        <tr>
                                            <td>Order Date</td>
                                            <td>:</td>
                                            <td id="order-date"></td>
                                        </tr>
                                        <tr>
                                            <td>Required Date</td>
                                            <td>:</td>
                                            <td id="required-date"></td>
                                        </tr>
                                        <tr>
                                            <td>Scheduled Buy Date</td>
                                            <td>:</td>
                                            <td id="scheduled-buy-date"></td>
                                        </tr>
                                        <tr>
                                            <td>Scheduled Buy By</td>
                                            <td>:</td>
                                            <td id="scheduled-buy-by"></td>
                                        </tr>
                                        <tr>
                                            <td>Quantity</td>
                                            <td>:</td>
                                            <td id="order-quantity"></td>
                                        </tr>
                                        <tr>
                                            <td>Cost</td>
                                            <td>:</td>
                                            <td id="order-cost"></td>
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
                                    <h4 class="modal-title" id="myModalLabel">Delete <label id="header-order-name"></label></h4>
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

            //Datemask dd/mm/yyyy
            $("#datemask").inputmask("yyyy-mm-dd", {"placeholder": "yyyy-mm-dd"});
            $("#datemask1").inputmask("yyyy-mm-dd", {"placeholder": "yyyy-mm-dd"});
            //Datemask2 mm/dd/yyyy
            $("#datemask2").inputmask("mm/dd/yyyy", {"placeholder": "mm/dd/yyyy"});

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
                $("#myModalDel .modal-footer a").attr("href", "ProductionController?act=deleteOrderComponent&id=" + id);
                $("#header-order-name").html(name);
            });

            $(".btn-custom-detail").click(function () {
                var data = $(this).attr("cs-data");
                var arr = data.split("|");
                
                $("#component-name").html(arr[0]);
                $("#user").html(arr[1]);
                $("#order-date").html(arr[2]);
                $("#required-date").html(arr[3]);
                $("#scheduled-buy-date").html(arr[4]);
                $("#scheduled-buy-by").html(arr[5]);
                $("#order-quantity").html(arr[7]);
                $("#ordercost").html(arr[8]);
            });
        });


    </script>