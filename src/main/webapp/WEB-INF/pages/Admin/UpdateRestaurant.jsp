<%@page import="com.narola.fooddelivery.restaurants.model.Restaurant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantOfServlet"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="MetaTag.jsp"%>

</head>

<body>
	<div class="container-scroller">
		<%@include file="Navigation.jsp"%>
		<div class="container-fluid page-body-wrapper">
			<%@include file="Sidebar.jsp"%>
			<div class="main-panel">
				<div class="content-wrapper">




					<h2>
						<strong>Update Your Restaurant Data </strong>
					</h2>
					<br />

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
						action="<%=request.getContextPath() + URLConstantOfServlet.UPDATERESTAURANT%>"
						method="post" enctype="multipart/form-data">
						<%
						Restaurant restaurant = (Restaurant) request.getAttribute("Restaurant");
						%>
						<input type="hidden" name="restaurantId"
							value="<%=restaurant.getRestId()%>">
						<div class="row">
							<div class="col-12 col-sm-2">Name:</div>
							<div class="col-12 col-sm-5">
								<input class="form-control" type="text" name="restaurantName"
									value="<%=restaurant.getRestName()%>">
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">Email:</div>
							<div class="col-12 col-sm-5">
								<input class="form-control" type="text" name="email"
									value="<%=restaurant.getEmail()%>">

							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">Picture:</div>
							<div class="col-auto">
								<img class="img-thumbnail"
									src="data:image/png;base64,<%=restaurant.getRestphotoAsBase64()%>"
									alt="No image found" style="width: 300px;"><br> <input
									type="file" name="restPic" />
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">AddressLine:</div>
							<div class="col-auto">
								<textarea class="form-control" rows="3" cols="50"
									name="location.addressLine" width="60%"><%=restaurant.getLocation().getAddressLine()%></textarea>
							</div>

						</div>
						<br />



						<div class="row">
							<div class="col-12 col-sm-2">Area:</div>
							<div class="col-auto">
								<input class="form-control" type="text" name="location.area"
									value="<%=restaurant.getLocation().getArea()%>">
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">City:</div>
							<div class="col-auto">
								<input class="form-control" type="text" name="location.city"
									value="<%=restaurant.getLocation().getCity()%>">
							</div>

						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">State:</div>
							<div class="col-auto">
								<input class="form-control" type="text" name="location.state"
									value="<%=restaurant.getLocation().getState()%>">
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2">Pincode:</div>
							<div class="col-auto">
								<input class="form-control" type="text" name="location.pincode"
									value="<%=restaurant.getLocation().getPincode()%>">
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-12 col-sm-2"></div>
							<div class="col-auto">

								<input type="checkbox" name="disable"
									<%=restaurant.getDisableFlag() == 0 ? "" : " checked"%>> <b><%=restaurant.getDisableFlag() == 0 ? "Check to disable Restaurant" : "Uncheck to enable restaurant"%></b>
							</div>
						</div>
						<br /> <input class="btn btn-secondary" type="reset"> <input
							class="btn btn-primary" type="submit"> <br />




					</form>

				</div>
			</div>

		</div>
	</div>


	<%@include file="Scripts.jsp"%>


</body>

</html>