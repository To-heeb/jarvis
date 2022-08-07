package com.authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class UserDbUtil {
	
	private DataSource dataSource;
	
	private Connection myConn;
	
	public UserDbUtil(DataSource thedataSource) {
		dataSource = thedataSource;
	}

	public String registerUser(User newUser) throws Exception {
		
		PreparedStatement myStmt = null;
		
		try {
			
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO user (firstname, lastname, email, password) values(?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			// set the param values for the student
			myStmt.setString(1, newUser.getFirstname());
			myStmt.setString(2, newUser.getLastname());
			myStmt.setString(3, newUser.getEmail());
			myStmt.setString(4, newUser.getPassword());
			
			// execute sql insert
			int rowCount = myStmt.executeUpdate();
			
			if(rowCount > 0) {
				// if success
				return "success";
				
			}else {
				// if failed
				return "failed";
			}
			
		}
		finally {
			// clean up JDBC object
			close(myConn, myStmt, null);
		}
		
	}

	public User loginUser(User user) throws SQLException {

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		User userData = new User(0, null, null, null, null, null, null, false);
		
		try {
			
		// get db connection
		myConn = dataSource.getConnection();
					
		// create sql for insert
		String sql = "SELECT * FROM user WHERE email = ? AND password = ? AND account_status = '1'";
		
		// create prepared statement
		myStmt = myConn.prepareStatement(sql);
		
		// set params
		myStmt.setString(1, user.getEmail());
		myStmt.setString(2, user.getPassword());
		
		// execute statement
		myRs = myStmt.executeQuery();
		
		if(myRs.next()) {
			int id = myRs.getInt("id");
			String firstname = myRs.getString("firstname");
			String lastname = myRs.getString("lastname");
			String email = myRs.getString("email");
			String role = myRs.getString("role");
			String memory = myRs.getString("memory_usage");
			String image = myRs.getString("profile_image");
			
			return new User(id, firstname, lastname, email, role, memory, image, true);
		}
			
		return userData;
		
		}
		finally {
			
			// close JDBC object
			close(myConn, myStmt, myRs);
		}
	}
	
	public String updateSettings(User updatedUser) throws SQLException {

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			// get db connection
			myConn = dataSource.getConnection();
						
			// create sql for insert
			String sql = "UPDATE user SET firstname = ?, lastname = ?, email = ? WHERE id = ?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, updatedUser.getFirstname());
			myStmt.setString(2, updatedUser.getLastname());
			myStmt.setString(3, updatedUser.getEmail());
			myStmt.setInt(4, updatedUser.getId());
			
			
				try {
					// execute SQL statement
					int rowCount = myStmt.executeUpdate();
					
					if(rowCount > 0) {
						// if success
						return "settings_success";
						
						//return newFolder.toString();
						
					}else {
						// if failed
						return "settings_failed";
					}
				}
				catch (SQLException e) {
				   
				    throw new SQLException("SQL error");
				}
			
			} finally {
				
				// close JDBC object
				close(myConn, myStmt, myRs);
			}
		
	}

	public boolean deactivateAccount(int userId) throws SQLException {

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			// get db connection
			myConn = dataSource.getConnection();
						
			// create sql for insert
			String sql = "UPDATE user SET account_status = '0' WHERE id = ?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, userId);
		
				try {
					// execute SQL statement
					int rowCount = myStmt.executeUpdate();
					
					if(rowCount > 0) {
						// if success
						return true;
						
						//return newFolder.toString();
						
					}else {
						// if failed
						return false;
					}
				}
				catch (SQLException e) {
				   
				    throw new SQLException("SQL error");
				}
			
			} finally {
				
				// close JDBC object
				close(myConn, myStmt, myRs);
			}
		
	}

	public String updatePassword(int userId, String passwordHash) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			// get db connection
			myConn = dataSource.getConnection();
						
			// create sql for insert
			String sql = "UPDATE user SET password = ? WHERE id = ?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, passwordHash);
			myStmt.setInt(2, userId);
		
				try {
					// execute SQL statement
					int rowCount = myStmt.executeUpdate();
					
					if(rowCount > 0) {
						// if success
						return "password_success";
						
						//return newFolder.toString();
						
					}else {
						// if failed
						return "password_failed";
					}
				}
				catch (SQLException e) {
				   
				    throw new SQLException("SQL error");
				}
			
			} finally {
				
				// close JDBC object
				close(myConn, myStmt, myRs);
			}
	}

	private void close(Connection Conn, PreparedStatement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		
		try {
			
			if(myRs != null) {
				myRs.close();
			}
			
			if(myStmt != null) {
				myStmt.close();
			}
			
			if(Conn != null) {
				Conn.close();
			}
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
		
	}
}
