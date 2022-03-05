package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.Customer;

public class CustomerDAOImpl extends CustomerDAO {

	private static final String INSERT = "INSERT INTO customers (first_name, last_name, product) " +
			"VALUES (?,?,?)";
	private static final String GET_ONE = "SELECT * FROM customers WHERE customer_id = ?"; 
	
	private static final String GET_ALL = "SELECT * FROM customers WHERE first_name = ?";
	
	private static final String UPDATE = "UPDATE customers SET first_name = ?, last_name = ?, " +
			"product = ? WHERE customer_id = ?";
	
	private static final String DELETE = "DELETE FROM customers WHERE customer_id = ?";
	
	public CustomerDAOImpl(Connection connection) {
		super(connection);
	}
	
	@Override
	public boolean createOrder(Customer customer) {
		try(PreparedStatement ps = this.connection.prepareStatement(INSERT)) {
			ps.setString(1, customer.getFirst_name());
			ps.setString(2, customer.getLast_name());
			ps.setString(3, customer.getProduct());	
			ps.execute();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Customer findByID(int id) {
		try(PreparedStatement ps = this.connection.prepareStatement(GET_ONE)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Customer cust = new Customer();
			while(rs.next()) {
				cust.setCustomer_id(rs.getInt(1));
				cust.setFirst_name(rs.getString(2));
				cust.setLast_name(rs.getString(3));
				cust.setProduct(rs.getString(4));
			}
			return cust;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Customer> findByFirstName(String first_name) {
		try(PreparedStatement ps = this.connection.prepareStatement(GET_ALL)) {
			ps.setString(1, first_name);
			Customer cust = new Customer();
			ArrayList<Customer> custList = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				cust.setCustomer_id(rs.getInt(1));
				cust.setFirst_name(rs.getString(2));
				cust.setLast_name(rs.getString(3));
				cust.setProduct(rs.getString(4));
				custList.add(cust);
			}
			return custList;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateByID(int id, Customer customer) {
		try(PreparedStatement ps = this.connection.prepareStatement(UPDATE)) {
			ps.setString(1, customer.getFirst_name());
			ps.setString(2, customer.getLast_name());
			ps.setString(3, customer.getProduct());
			ps.setInt(4, id);
			if(ps.executeUpdate() < 1)
			{
				System.out.println("Update Failed");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteByID(int id) {
		try(PreparedStatement ps = this.connection.prepareStatement(DELETE)) {
			ps.setInt(1, id);
			if(ps.executeUpdate() < 1)
			{
				System.out.println("Delete Failed");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}