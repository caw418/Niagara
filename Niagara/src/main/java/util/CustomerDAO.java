package util;

import java.sql.Connection;
import java.util.ArrayList;

import main.Customer;

//Create your abstract class with your CRUD operations (Create, Read, Update, and Delete
 
public abstract class CustomerDAO 
{
	protected final Connection connection;
	
	public CustomerDAO(Connection connection){
        super();
        this.connection = connection;
    }
	
	public abstract boolean createOrder(Customer customer);
	
	public abstract Customer findByID(int id);
	
	public abstract ArrayList<Customer> findByFirstName(String first_name);
	
	public abstract void updateByID(int id, Customer customer);
	
	public abstract void deleteByID(int id);
}
