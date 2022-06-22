<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@page import="com.narola.fooddelivery.dishes.model.Dish"%>
<%@page import="com.narola.fooddelivery.restaurants.model.Restaurant"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantAdmin"%>
<%@page import="com.narola.fooddelivery.utility.URLConstantOfServlet"%>

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
				
				
	<%
									Restaurant restaurant=(Restaurant)request.getAttribute("Restaurant");
									%>

<div align="center">
<h2>
			<strong><%=restaurant.getRestName()%></strong>
		</h2>
		<p><%=restaurant.getLocation().getAddressLine()%>,
			<%=restaurant.getLocation().getArea()%>,
			<%=restaurant.getLocation().getCity()%>,
			<%=restaurant.getLocation().getPincode()%>.
		</p>
		<p>
			<%
			List<Dish> catList = null;

				catList = (ArrayList) restaurant.getCategories();

				Iterator itr = catList.iterator();
				while (itr.hasNext()) {
					String str=(String)itr.next();
			%>
			<span class="badge badge-pill badge-primary"><%=str%></span>

			<%
			}
			%>

		</p>


</div>	
			
	<!-- <div class="container"> -->
		<div align="center">
			<h3>
				<strong>Menu </strong>
			</h3>
		</div>
			<%
			int count = 0;
				List<Dish> al = null;

				al = (ArrayList) restaurant.getMenu();

				itr = al.iterator();
				while (itr.hasNext()) {
					Dish d = (Dish) itr.next();
					count++;
			%>
		
				


	<div style="align-items: center;align:center;">
		<div class="row">
		<div class="col-12 col-sm-2"></div>
				<div class="card col-12 col-sm-8">
					
					<div class="card-body">
						<div class="row">
							<div class="col-12 col-sm-8">
								<label><h4><%=d.getDishId()%>. <strong><%=d.getDishName()%></strong>
									</h4> <%=d.getIngrident()%><br> <label>-/<%=d.getPrice()%></label><br>

									<span class="badge badge-primary"><%=d.getCategory()%></span>
									 <%
									 if (d.getDishtype() == 0) {
									 %> <span class="badge badge-success">Veg</span> 
									 <%
 									 } else {
 									 %> <span class="badge badge-danger">NonVeg</span> <%
 }
 %><br><br>
										
								&nbsp;&nbsp;&nbsp; 
								<a href="<%=request.getContextPath() + URLConstantOfServlet.UPDATEDISH%>?DishId=<%=d.getDishId()%>">
								<i class="fa-solid fa-pen-to-square" style="font-size: 24px; color: black; text-align: right"></i>
								</a>&nbsp;
								<a href="<%=request.getContextPath() + URLConstantOfServlet.DELETEDISH%>?DishId=<%=d.getDishId()%>" align="right"> 
								<i class="fas fa-trash-alt" style="font-size: 24px; color: black; align: right;"></i>
								</a>&nbsp;&nbsp;
								<a type="button" class="btn btn-warning" href="<%=request.getContextPath() + URLConstantOfServlet.VIEWDISH%>?DishId=<%=d.getDishId()%>">
									Order </a>
							</div>
							
							
							<div class="col-12 col-sm-4">
								<img src="data:image/png;base64,<%=d.getPhotoAsBase64()%>"
									alt="No image found" style="height:150px;">
							</div>

						</div>
					</div>

				</div>
			</div><br />

			<%
			}
			%>
			<%
			if (count == 0) {
			%>
			<b>No Record</b>
			<%
			}
			%>


		</div>



	</div>
</div>
			</div>

		</div>
	<!-- </div> -->


<%@include file="Scripts.jsp"%>
</body>
</html>