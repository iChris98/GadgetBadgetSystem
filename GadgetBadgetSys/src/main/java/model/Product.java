package model;

import java.sql.*;

public class Product {

	//A common method to connect to the DB
	private Connection connect()
	 {
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", ""); 
		}
		
		catch (Exception e)
		{e.printStackTrace();}
		
		return con;
	 }
	
	 public String insertProduct(String code, String name, String price, String desc)
	 {
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 
			// create a prepared statement
			 String query = " insert into items
					 ('ProductID','ProductCode','ProductName','ProductPrice','ProductDesc')"
					 + " values (?, ?, ?, ?, ?)";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, code);
			 preparedStmt.setString(3, name);
			 preparedStmt.setDouble(4, Double.parseDouble(price));
			 preparedStmt.setString(5, desc);
			 
			 
			// execute the statement
			 	preparedStmt.execute();
			 	con.close();
			 	
			 	output = "Inserted successfully";
		 } 	
		 catch (Exception e) 
		 {
			    output = "Error while inserting the item.";
			    System.err.println(e.getMessage());
		 }
		
		 return output;
	 }   
	 
	 public String readItems()
	 {
		 String output = "";
		 
		 try
		 { 
			    Connection con = connect();
			 
			    if (con == null)
			    {return "Error while connecting to the database for reading."; }
			    
			    // Prepare the html table to be displayed
			    output = "<table border='1'><tr><th>Product Code</th><th>Product Name</th>" +
			    
						"<th>Product Price</th>" +
						"<th>Product Description</th>" +
						"<th>Update</th><th>Remove</th></tr>";
			    
			    
			    String query = "select * from items";
			    Statement stmt = con.createStatement();
			    ResultSet rs = stmt.executeQuery(query);
			    
			   // iterate through the rows in the result set
			    while (rs.next())
			    {
			    	
			    		String itemID = Integer.toString(rs.getInt("ProductID"));
			    		String itemCode = rs.getString("ProductCode");
			    		String itemName = rs.getString("ProductName");
			    		String itemPrice = Double.toString(rs.getDouble("ProductPrice"));
			    		String itemDesc = rs.getString("ProductDesc"); 
			    
			    		// Add into the html table
			    		
			    		 output += "<tr><td>" + ProductCode + "</td>";
			    		 output += "<td>" + ProductName + "</td>";
			    		 output += "<td>" + ProductPrice + "</td>";
			    		 output += "<td>" + ProductDesc + "</td>"; 
			    		 
			    		// buttons
			    		 
			    		 output += "<td><input name='btnUpdate' type='button' value='Update'
			    				 class='btn btn-secondary'></td>"
			    				 + "<td><form method='post' action='items.jsp'>"
			    				 + "<input name='btnRemove' type='submit' value='Remove'
			    				 class='btn btn-danger'>"
			    				 + "<input name='ProductID' type='hidden' value='" + ProductID
			    				 + "'>" + "</form></td></tr>";
			    				 
			    }
			    
			    con.close();
			    
			    // Complete the html table
			    output += "</table>"; 
			    
		 }   
		 catch (Exception e)
		 {
			 output = "Error while reading the items.";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
			    		
	 }		 
			 
	 public String updateItem(String ID, String code, String name, String price, String desc)
	 {
		 String output = ""; 
		 
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			// create a prepared statement
			 String query = "UPDATE Products SET ProductCode=?,ProductName=?,ProductPrice=?,ProductDesc=?
					 WHERE ProductID=?";"
					 		 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			// binding values
			 
			 preparedStmt.setString(1, code);
			 preparedStmt.setString(2, name);
			 preparedStmt.setDouble(3, Double.parseDouble(price));
			 preparedStmt.setString(4, desc);
			 preparedStmt.setInt(5, Integer.parseInt(ID));
			 
			// execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Updated successfully";
			 
		 }
		 catch (Exception e)
		 { 
			 output = "Error while updating the product item.";
			 System.err.println(e.getMessage()); 
		 }
		 
		 return output;
	 }	 
	 
	 public String deleteProduct(String ProductID)
	 {
		
		 String output = "";
		 
		 try
		 { 	 
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 
			 // create a prepared statement
			 String query = "delete from products where ProductID=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(ProductID);
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Deleted successfully"; 
			 
		 }	  
		 catch (Exception e) 
		 {
			 output = "Error while deleting the product.";
			 System.err.println(e.getMessage()); 
		 }
		 
		 return output; 
			 
	 }				 
}
