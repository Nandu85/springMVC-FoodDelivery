package com.narola.fooddelivery.restaurants.dao;

import com.narola.fooddelivery.exception.DatabaseException;
import com.narola.fooddelivery.location.LocationDAO;
import com.narola.fooddelivery.restaurants.model.Restaurant;
import com.narola.fooddelivery.utility.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestDAOMYSQL implements IRestDAO {

	@Autowired
	private DBConnection dbConnection;

	private static Connection con;

	public Restaurant addRestaurant(Restaurant restaurant) throws DatabaseException {
		con = dbConnection.getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("INSERT INTO `restaurants`\r\n" + "(`restname`,`Location`,`email`,`Pic`)\r\n"
					+ "VALUES\r\n" + "(?,?,?,?);\r\n" + "", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, restaurant.getRestaurantName());
			ps.setInt(2, restaurant.getLocationId());
			ps.setString(3, restaurant.getEmail());
			ps.setString(4, restaurant.getRestphotoAsBase64());

			ps.executeUpdate();
			resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				restaurant.setRestaurantId(resultSet.getInt(1));

			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get addRestaurant", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return restaurant;
	}

	/**
	 * @param restaurantName
	 * @return
	 * @throws DatabaseException
	 */
	public List<Restaurant> searchRestaurantFromName(String restaurantName) throws DatabaseException {
		List<Restaurant> restaurant;
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			String query = "SELECT * FROM restaurants where restname like \"" + restaurantName + "\"\'%\'";
			ps = con.prepareStatement(query);

			resultSet = ps.executeQuery();
			restaurant = new ArrayList<>();
			while (resultSet.next()) {
				Restaurant rest = new Restaurant();
				rest.setRestaurantUserId(resultSet.getInt("restid"));
				rest.setRestaurantName(resultSet.getString("restname"));
				rest.setLocationId(resultSet.getInt("Location"));
				rest.setEmail(resultSet.getString("email"));
				rest.setRestphotoAsBase64(resultSet.getString("Pic"));
				rest.setLocation(LocationDAO.getLocationFromId(rest.getLocationId()));
				rest.setDisableFlag(resultSet.getInt("Disabled"));
				rest.setUserId(resultSet.getInt("RestaurantAdmin"));

				restaurant.add(rest);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get searchRestaurantFromName", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return restaurant;

	}

	public List<Restaurant> searchRestaurantFromArea(String area) throws DatabaseException {
		List<Restaurant> restaurant;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		Connection con = DBConnection.getInstance().getConnection();
		try {
			ps = con.prepareStatement(
					"SELECT * FROM restaurants where Location IN(SELECT LocId FROM location_restaurants where Area=?)");
			ps.setString(1, area);
			resultSet = ps.executeQuery();
			restaurant = new ArrayList<>();
			while (resultSet.next()) {
				Restaurant rest = new Restaurant();
				rest.setRestaurantId(resultSet.getInt("restid"));
				rest.setRestaurantName(resultSet.getString("restname"));
				rest.setLocationId(resultSet.getInt("Location"));
				rest.setEmail(resultSet.getString("email"));
				rest.setRestphotoAsBase64(resultSet.getString("Pic"));
				rest.setLocation(LocationDAO.getLocationFromId(rest.getLocationId()));
				rest.setDisableFlag(resultSet.getInt("Disabled"));
				rest.setUserId(resultSet.getInt("RestaurantAdmin"));

				restaurant.add(rest);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while get searchRestaurantFromArea", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return restaurant;

	}

	public List<Restaurant> getAllRestaurants() throws DatabaseException {
		List<Restaurant> restaurant;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		Connection con = DBConnection.getInstance().getConnection();
		try {
			ps = con.prepareStatement("SELECT * FROM restaurants");
			resultSet = ps.executeQuery();
			restaurant = new ArrayList<>();
			while (resultSet.next()) {
				Restaurant rest = new Restaurant();
				rest.setRestaurantId(resultSet.getInt("restid"));
				rest.setRestaurantName(resultSet.getString("restname"));
				rest.setLocationId(resultSet.getInt("Location"));
				rest.setEmail(resultSet.getString("email"));
				rest.setRestphotoAsBase64(resultSet.getString("Pic"));
				rest.setLocation(LocationDAO.getLocationFromId(rest.getLocationId()));
				rest.setDisableFlag(resultSet.getInt("Disabled"));
				rest.setUserId(resultSet.getInt("RestaurantAdmin"));

				restaurant.add(rest);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while getAllRestaurants", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return restaurant;
	}

	public Restaurant getRestaurantFromId(int id) throws DatabaseException {
		Restaurant restaurant = null;
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("SELECT * FROM restaurants where restid=?");
			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				restaurant = new Restaurant();
				restaurant.setRestaurantId(resultSet.getInt("restid"));
				restaurant.setRestaurantName(resultSet.getString("restname"));
				restaurant.setLocationId(resultSet.getInt("Location"));
				restaurant.setEmail(resultSet.getString("email"));
				restaurant.setRestphotoAsBase64(resultSet.getString("Pic"));
				restaurant.setLocation(LocationDAO.getLocationFromId(restaurant.getLocationId()));
				restaurant.setDisableFlag(resultSet.getInt("Disabled"));

				restaurant.setUserId(resultSet.getInt("RestaurantAdmin"));
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while getRestaurantFromId", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return restaurant;
	}

	public Restaurant updateRestaurant(Restaurant restaurant) throws DatabaseException {
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("UPDATE restaurants\r\n"
					+ "SET `restname` = ?, `Location` = ?, `email` = ?, `Pic` = ?, `Disabled`=?\r\n"
					+ " WHERE `restid` = ?");

			ps.setString(1, restaurant.getRestaurantName());
			ps.setInt(2, restaurant.getLocationId());
			ps.setString(3, restaurant.getEmail());
			ps.setString(4, restaurant.getRestphotoAsBase64());
			ps.setInt(5, restaurant.getDisableFlag());
			ps.setInt(6, restaurant.getRestaurantId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DatabaseException("Error while DishesFromSubCategory", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return restaurant;
	}

	public Restaurant setRestaurantAdmin(Restaurant restaurant) throws DatabaseException {
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {


			ps = con.prepareStatement(
					"UPDATE `restaurants`\r\n" + "SET `RestaurantAdmin`=NULL\r\n" + " WHERE `RestaurantAdmin` = ?");
			ps.setInt(1, restaurant.getUserId());
			ps.executeUpdate();

			ps = con.prepareStatement(
					"UPDATE `restaurants`\r\n" + "SET `RestaurantAdmin`=?\r\n" + " WHERE `restid` = ?");

			ps.setInt(1, restaurant.getUserId());
			ps.setInt(2, restaurant.getRestaurantId());
			ps.executeUpdate();

			con.commit();

		} catch (SQLException e) {

			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DatabaseException("Error while setRestaurantAdmin", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return restaurant;
	}

	public List<String> getRestaurantCategories(int restId) throws DatabaseException {
		Connection con = DBConnection.getInstance().getConnection();
		List<String> catList = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(
					"SELECT CategoryName FROM category where categoryId IN(SELECT distinct categoryid FROM dishes where Restaurant=?)");
			ps.setInt(1, restId);
			rs = ps.executeQuery();
			catList = new ArrayList<>();
			while (rs.next()) {
				catList.add(rs.getString(1));
			}

		} catch (SQLException e) {
			throw new DatabaseException("Error while getRestaurantCategories", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, rs);
		}
		return catList;
	}

	public Restaurant getRestaurantFromUserId(int id) throws DatabaseException {
		Restaurant restaurant = new Restaurant();
		Connection con = DBConnection.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("SELECT * FROM `restaurants` where RestaurantAdmin=?");
			ps.setInt(1, id);
			resultSet = ps.executeQuery();

			if (resultSet.next()) {

				restaurant.setRestaurantId(resultSet.getInt("restid"));
				restaurant.setRestaurantName(resultSet.getString("restname"));
				restaurant.setLocationId(resultSet.getInt("Location"));
				restaurant.setEmail(resultSet.getString("email"));
				restaurant.setRestphotoAsBase64(resultSet.getString("Pic"));
				restaurant.setLocation(LocationDAO.getLocationFromId(restaurant.getLocationId()));
				restaurant.setDisableFlag(resultSet.getInt("Disabled"));

				restaurant.setUserId(id);

			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while getRestaurantFromUserId", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}
		return restaurant;
	}

	public List<Restaurant> getRestaurantsFromSubCategory(int id) throws DatabaseException {
		List<Restaurant> restaurant = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		Connection con = DBConnection.getInstance().getConnection();
		try {
			ps = con.prepareStatement("SELECT * FROM restaurants where restid IN(select Restaurant from dishes "
					+ "where SubCategory=?) and Disabled=0");
			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			restaurant = new ArrayList<>();
			while (resultSet.next()) {
				Restaurant rest = new Restaurant();
				rest.setRestaurantId(resultSet.getInt("restid"));
				rest.setRestaurantName(resultSet.getString("restname"));
				rest.setLocationId(resultSet.getInt("Location"));
				rest.setEmail(resultSet.getString("email"));
				rest.setRestphotoAsBase64(resultSet.getString("Pic"));
				rest.setLocation(LocationDAO.getLocationFromId(rest.getLocationId()));
				rest.setDisableFlag(resultSet.getInt("Disabled"));
				rest.setUserId(resultSet.getInt("RestaurantAdmin"));
				rest.setCategories((ArrayList<String>) getRestaurantCategories(rest.getRestaurantId()));
				restaurant.add(rest);
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while getRestaurantsFromSubCategory", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return restaurant;
	}

	public Timestamp getJoinDate(int restId) {
		Timestamp timestamp = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		Connection con = DBConnection.getInstance().getConnection();

		try {
			ps = con.prepareStatement("SELECT * FROM restaurants where restid=?");
			ps.setInt(1, restId);
			resultSet = ps.executeQuery();

			if (resultSet.next()) {
				timestamp = resultSet.getTimestamp("CreatedOn");
			}
		} catch (SQLException e) {
			throw new DatabaseException("Error while getRestaurantsFromSubCategory", e);
		} catch (DatabaseException e) {
			throw e;
		} finally {
			DBConnection.releaseResource(ps, resultSet);
		}

		return timestamp;
	}

}
