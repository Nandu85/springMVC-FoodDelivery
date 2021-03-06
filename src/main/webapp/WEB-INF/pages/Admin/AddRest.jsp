<%@ page language="java" %>

<%@page import="com.narola.fooddelivery.utility.URLConstantOfServlet" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="MetaTag.jsp" %>

    <title>Insert Restaurant here</title>

</head>
<body>
<div class="container-scroller">
    <%@include file="Navigation.jsp" %>
    <div class="container-fluid page-body-wrapper">
        <%@include file="Sidebar.jsp" %>
        <div class="main-panel">
            <div class="content-wrapper">
                <h2>
                    <strong>${title}</strong>
                </h2>
                <br/>
                <%
                    if (request.getAttribute("ErrMsg") != null) {
                %>
                <style>
                    p.error {
                        color: Red;
                        text-align: center;
                    }
                </style>
                <p class="error">
                    <c:if test="${not empty ErrMsg}">
                        <c:forEach items="${ErrMsg}" var="err">
                            ${err}<br>
                        </c:forEach>
                    </c:if>

                </p>
                <%
                    }
                %>
                <form
                        action="<%=request.getContextPath() + URLConstantOfServlet.ADDRESTAURANT%>"
                        method="post" enctype="multipart/form-data">


                    <input type="hidden" name="restaurantId">
                    <div class="row">
                        <div class="col-12 col-sm-2">Name:</div>
                        <div class="col-12 col-sm-10">
                            <div class="row">
                                <div class="col-12 col-sm-6">
                                    <input class="form-control" type="text" name="restaurantName">
                                </div>
                                <div class="col-12 col-sm-6">
                                    <c:if test="${ErrMsg!=null}">
                                        <c:out value="${ErrMsg.get(\"restaurant.name\")}"></c:out>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-12 col-sm-2">Email:</div>
                        <div class="col-12 col-sm-10">
                            <div class="row">
                                <div class="col-12 col-sm-6">
                                    <input class="form-control" type="text" name="email">
                                </div>
                                <div class="col-12 col-sm-6">
                                    <c:if test="${ErrMsg!=null}">
                                        <c:out value="${ErrMsg.get(\"restaurant.email\")}"></c:out>
                                    </c:if>
                                </div>
                            </div>


                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-12 col-sm-2">Picture:</div>
                        <div class="col-auto">

                            <input type="file" name="restPic"/>
                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-12 col-sm-2">AddressLine:</div>
                        <div class="col-12 col-sm-10">

                            <div class="row">
                                <div class="col-12 col-sm-6">
                                    <textarea class="form-control" rows="3" cols="50"
                                              name="location.addressLine" width="60%"></textarea>
                                </div>
                                <div class="col-12 col-sm-6">
                                    <c:if test="${ErrMsg!=null}">
                                        <c:out value="${ErrMsg.get(\"location.addressline\")}"></c:out>
                                    </c:if>
                                </div>
                            </div>
                        </div>

                    </div>
                    <br/>


                    <div class="row">
                        <div class="col-12 col-sm-2">Area:</div>
                        <div class="col-12 col-sm-10">

                            <div class="row">
                                <div class="col-12 col-sm-6">
                                    <input class="form-control" type="text" name="location.area">
                                </div>
                                <div class="col-12 col-sm-6">
                                    <c:if test="${ErrMsg!=null}">
                                        <c:out value="${ErrMsg.get(\"location.area\")}"></c:out>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-12 col-sm-2">City:</div>
                        <div class="col-12 col-sm-10">

                            <div class="row">
                                <div class="col-12 col-sm-6">
                                    <input class="form-control" type="text" name="location.city">
                                </div>
                                <div class="col-12 col-sm-6">
                                    <c:if test="${ErrMsg!=null}">
                                        <c:out value="${ErrMsg.get(\"location.city\")}"></c:out>
                                    </c:if>
                                </div>
                            </div>
                        </div>

                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-12 col-sm-2">State:</div>
                        <div class="col-12 col-sm-10">
                            <div class="row">
                                <div class="col-12 col-sm-6">
                                    <input class="form-control" type="text" name="location.state">
                                </div>
                                <div class="col-12 col-sm-6">
                                    <c:if test="${ErrMsg!=null}">
                                        <c:out value="${ErrMsg.get(\"location.state\")}"></c:out>
                                    </c:if>
                                </div>
                            </div>

                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-12 col-sm-2">Pincode:</div>
                        <div class="col-auto">
                            <input class="form-control" type="text" name="location.pincode">
                        </div>
                    </div>
                    <br/> <br> <input class="btn btn-secondary" type="reset">
                    <input class="btn btn-primary" type="submit"> <br/>
                </form>
                <br>
                <br>
            </div>
        </div>

    </div>
</div>


<%@include file="Scripts.jsp" %>


</body>
</html>