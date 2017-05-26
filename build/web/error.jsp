<%-- 
    Document   : error.jsp
    Created on : May 1, 2015, 10:50:36 PM
    Author     : Akip Maulana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="template_header.jsp" %>
<body class="lockscreen">
    <!-- Automatic element centering -->
    <div class="lockscreen-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="error-page">
                <h2 class="headline text-yellow"> 404</h2>
                <div class="error-content">
                    <h3><i class="fa fa-warning text-yellow"></i> Oops! Page not found.</h3>
                    <p>
                        We could not find the page you were looking for.
                        Meanwhile, you may <a href='../../index.html'>return to dashboard</a> or try using the search form.
                    </p>
                    <form class='search-form'>
                        <div class='input-group'>
                            <input type="text" name="search" class='form-control' placeholder="Search"/>
                            <div class="input-group-btn">
                                <button type="submit" name="submit" class="btn btn-warning btn-flat"><i class="fa fa-search"></i></button>
                            </div>
                        </div><!-- /.input-group -->
                    </form>
                </div><!-- /.error-content -->
            </div><!-- /.error-page -->
        </section><!-- /.content -->
    </div><!-- /.content-wrapper -->
    <div class='lockscreen-footer text-center'>
        Copyright &copy; 2014-2015 <b><a href="http://almsaeedstudio.com" class='text-black'>Almsaeed Studio</a></b><br>
        All rights reserved
    </div>
    <!--Load footer-->
    <jsp:include page="template_tablefooter.jsp" />
