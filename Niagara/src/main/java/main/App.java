package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import util.CustomerDAOImpl;

public class App {

	static Scanner console = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
				"postgres", "postgres", "password");
		CustomerDAOImpl cdi = null;
		int userInput;
		String firstName;
		String lastName;
		String product;
		Customer cust;
		int cust_id = 0;
		
		try {
			Connection connection = dcm.getConnection();
			cdi = new CustomerDAOImpl(connection);
		} catch(SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		do
		{
			System.out.println("Welcome to Niagara!");
			System.out.println("1 - Create A Customer/Order");
			System.out.println("2 - Find Customer by ID");
			System.out.println("3 - Find Customers by Name");
			System.out.println("4 - Update Customer/Order by ID");
			System.out.println("5 - Delete Customer/Order by ID");
			userInput = console.nextInt();
			switch(userInput)
			{
			case 1:
				System.out.println("What is the customer's first name?");
				firstName = console.next();
				System.out.println("What is the customer's last name?");
				lastName = console.next();
				System.out.println("What did they buy?");
				product = console.next() + console.nextLine();
				cust = new Customer(firstName, lastName, product);
				if(cdi.createOrder(cust))
				{
					System.out.println("Customer/Order Successfully Created");
				}
				else
				{
					System.out.println("Operation Failed");
				}
				break;
			case 2:
				System.out.println("Enter the id of the customer you want to find: ");
				try {
					cust_id = console.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Please enter numeric input");
					System.exit(0);
				}
				cdi.findByID(cust_id);
				cust = cdi.findByID(cust_id);
				if(cust.getCustomer_id() == 0)
				{
					System.out.println("A customer with id " + cust_id + " was not found.");
				}
				else
				{
					System.out.println(cust);
				}
				break;
			case 3:
				System.out.println("Enter the name of the customer(s) you want to find: ");
				String name = console.next();
				ArrayList<Customer> clist = cdi.findByFirstName(name);
				if(clist.isEmpty())
				{
					System.out.println("No matches found");
				}
				else
				{
					System.out.println(clist);
				}
				break;
			case 4:
				System.out.println("Enter the id of the customer you want to update: ");
				try {
					cust_id = console.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Please enter numeric input");
					System.exit(0);
				}
				System.out.println("What is the customer's first name?");
				firstName = console.next();
				System.out.println("What is the customer's last name?");
				lastName = console.next();
				System.out.println("What did they buy?");
				product = console.next() + console.nextLine();
				cust = new Customer(firstName, lastName, product);
				cdi.updateByID(cust_id, cust);
				break;
			case 5:
				System.out.println("Enter the id of the customer you want to delete: ");
				try {
					cust_id = console.nextInt();
					cdi.deleteByID(cust_id);
				} catch (InputMismatchException e) {
					System.out.println("Please enter numeric input");
					System.exit(0);
				}
				break;
			default:
				System.out.println("Select a valid option.");
			}
			System.out.println("Select another option? 1 - Yes, 2 - No");
			try {
				userInput = console.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please enter numeric input");
				System.exit(0);
			}
		}
		while(userInput == 1);
	}
}