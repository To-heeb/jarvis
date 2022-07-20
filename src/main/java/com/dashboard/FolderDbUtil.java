package com.dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.sql.DataSource;



public class FolderDbUtil {

	private DataSource dataSource;
	
	private Connection myConn;

	public FolderDbUtil(DataSource thedataSource) {
		dataSource = thedataSource;
	}
	
	public String createFolder(Folder newFolder) throws Exception {
		
		PreparedStatement myStmt = null;
		
		try {
			
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO folder (user_id, folder_id, folder_name) values(?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			// set the param values for the student
			myStmt.setInt(1, newFolder.getUserId());
			myStmt.setInt(2, newFolder.getFolderId());
			myStmt.setString(3, newFolder.getFolderName());
			
			
			try {
				// execute sql insert
				int rowCount = myStmt.executeUpdate();
				
				if(rowCount > 0) {
					// if success
					return "success";
					
					//return newFolder.toString();
					
				}else {
					// if failed
					return "failed";
				}
			}
			catch (SQLException e) {
			   
			    throw new Exception("SQL error");
			}
			
		}
		finally {
			// clean up JDBC object
			close(myConn, myStmt, null);
		}
		
	}

	public String updateFolder(Folder newFolder) throws SQLException {
		
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for update statement
			String sql = "UPDATE folder SET folder_name = ? WHERE id = ? AND user_id = ?";

			// prepare the statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, newFolder.getFolderName());
			myStmt.setInt(2, newFolder.getFolderId());
			myStmt.setInt(3, newFolder.getUserId());
			
			try {
				// execute SQL statement
				int rowCount = myStmt.executeUpdate();
				
				if(rowCount > 0) {
					// if success
					return "update_success";
					
					//return newFolder.toString();
					
				}else {
					// if failed
					return "failed";
				}
			}
			catch (SQLException e) {
			   
			    throw new SQLException("SQL error");
			}

		} finally {

			// close JDBC object
			close(myConn, myStmt, null);
		}
	}

	public String starFolder(Folder newFolder) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for update statement
			String sql = "UPDATE folder SET fav_status = ? WHERE id = ? AND user_id = ?";

			// prepare the statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, "1");
			myStmt.setInt(2, newFolder.getFolderId());
			myStmt.setInt(3, newFolder.getUserId());
			
			try {
				// execute SQL statement
				int rowCount = myStmt.executeUpdate();
				
				if(rowCount > 0) {
					// if success
					return "star_success";
					
					//return newFolder.toString();
					
				}else {
					// if failed
					return "failed";
				}
			}
			catch (SQLException e) {
			   
			    throw new SQLException("SQL error");
			}

		} finally {

			// close JDBC object
			close(myConn, myStmt, null);
		}
	}

	public String unstarFolder(Folder newFolder) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for update statement
			String sql = "UPDATE folder SET fav_status = ? WHERE id = ? AND user_id = ?";

			// prepare the statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, "0");
			myStmt.setInt(2, newFolder.getFolderId());
			myStmt.setInt(3, newFolder.getUserId());
			
			try {
				// execute SQL statement
				int rowCount = myStmt.executeUpdate();
				
				if(rowCount > 0) {
					// if success
					return "unstar_success";
					
					//return newFolder.toString();
					
				}else {
					// if failed
					return "failed";
				}
			}
			catch (SQLException e) {
			   
			    throw new SQLException("SQL error");
			}

		} finally {

			// close JDBC object
			close(myConn, myStmt, null);
		}
	}

	public String trashFolder(Folder newFolder) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for update statement
			String sql = "UPDATE folder SET trash_status = ? WHERE id = ? AND user_id = ?";

			// prepare the statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, "1");
			myStmt.setInt(2, newFolder.getFolderId());
			myStmt.setInt(3, newFolder.getUserId());
			
			try {
				// execute SQL statement
				int rowCount = myStmt.executeUpdate();
				
				if(rowCount > 0) {
					// if success
					return "trash_success";
					
					//return newFolder.toString();
					
				}else {
					// if failed
					return "failed";
				}
			}
			catch (SQLException e) {
			   
			    throw new SQLException("SQL error");
			}

		} finally {

			// close JDBC object
			close(myConn, myStmt, null);
		}
	}

	public String untrashFolder(Folder newFolder) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for update statement
			String sql = "UPDATE folder SET trash_status = ? WHERE id = ? AND user_id = ?";

			// prepare the statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, "0");
			myStmt.setInt(2, newFolder.getFolderId());
			myStmt.setInt(3, newFolder.getUserId());
			
			try {
				// execute SQL statement
				int rowCount = myStmt.executeUpdate();
				
				if(rowCount > 0) {
					// if success
					return "untrash_success";
					
					//return newFolder.toString();
					
				}else {
					// if failed
					return "failed";
				}
			}
			catch (SQLException e) {
			   
			    throw new SQLException("SQL error");
			}

		} finally {

			// close JDBC object
			close(myConn, myStmt, null);
		}
	}
	
	private void close(Connection Conn, Statement myStmt, ResultSet myRs) {
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
