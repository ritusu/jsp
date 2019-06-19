package com.lti.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lti.exception.DataAccessException;
import com.lti.model.Product;

public class ProductDao {
	//public void add(int id,String name,double price,int quantity) {
	
       
		public List<Product> fetchAll(int from, int to)  throws DataAccessException {
			Connection conn=null;
			PreparedStatement stmt=null;
			ResultSet rs=null;
	
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr");
					
					String sql="select id,name,price,quantity from ( select  t.*, row_number() over (order by ID) r from tbl_product t) where r between ? and ?"; 
						 
						 							
					stmt=conn.prepareStatement(sql);
					
					stmt.setInt(1, from);
					stmt.setInt(2, to);
					rs=stmt.executeQuery();
					
					List<Product> products=new ArrayList<Product>();
					while(rs.next()) {
						Product p=new Product();
						p.setId(rs.getInt(1));
						p.setName(rs.getString(2));
						p.setPrice(rs.getDouble(3));
						p.setQuantity(rs.getInt(4));
						products.add(p);
					}
							return products;
				}
				catch(ClassNotFoundException e) {
					throw new DataAccessException("unable to load the JDBC driver");
				}
				catch(SQLException e) {
					throw new DataAccessException("problem while fetching products from DB",e);
				}
				finally {
					try 
					{
						conn.close();
						}
					catch(Exception e) {
						
					}
				}
	
}

}
	

