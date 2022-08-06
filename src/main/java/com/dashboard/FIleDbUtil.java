package com.dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class FileDbUtil {


	private DataSource dataSource;
	
	private Connection myConn;

	public FileDbUtil(DataSource thedataSource) {
		dataSource = thedataSource;
	}

	public boolean createFile(Filex newFile) throws SQLException {
		
		PreparedStatement myStmt = null;
		
		try {
			
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO file (user_id, folder_id, file_displayname, file_newname, file_type, file_category, file_size, file_hash, file_path) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			// set the param values for the student
			myStmt.setInt(1, newFile.getUserId());
			myStmt.setInt(2, newFile.getFolderId());
			myStmt.setString(3, newFile.getDisplayName());
			myStmt.setString(4, newFile.getNewName());
			myStmt.setString(5, newFile.getFileType());
			myStmt.setString(6, newFile.getFileCategory());
			myStmt.setString(7, newFile.getFileSize());
			myStmt.setString(8, newFile.getFileHash());
			myStmt.setString(9, newFile.getFilePath());
			
			
			try {
				// execute sql insert
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
			   
			    e.printStackTrace();
			    //throw new SQLException("SQL error");
			}
			
		}
		finally {
			// clean up JDBC object
			close(myConn, myStmt, null);
		}
		return false;
		
	}

	public String starFile(Filex theFile) throws SQLException {
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for update statement
			String sql = "UPDATE file SET fav_status = ? WHERE id = ? AND user_id = ?";

			// prepare the statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, "1");
			myStmt.setInt(2, theFile.getFileId());
			myStmt.setInt(3, theFile.getUserId());
			
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

	public String unstarFile(Filex theFile) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for update statement
			String sql = "UPDATE file SET fav_status = ? WHERE id = ? AND user_id = ?";

			// prepare the statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, "0");
			myStmt.setInt(2, theFile.getFileId());
			myStmt.setInt(3, theFile.getUserId());
			
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
		
	public String updateFile(Filex theFile) throws SQLException {

		// TODO Auto-generated method stub
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for update statement
			String sql = "UPDATE file SET file_displayname = ? WHERE id = ? AND user_id = ?";

			// prepare the statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theFile.getDisplayName());
			myStmt.setInt(2, theFile.getFileId());
			myStmt.setInt(3, theFile.getUserId());
			
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
	

	public String trashFile(Filex theFile) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for update statement
			String sql = "UPDATE file SET trash_status = ? WHERE id = ? AND user_id = ?";

			// prepare the statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, "1");
			myStmt.setInt(2, theFile.getFileId());
			myStmt.setInt(3, theFile.getUserId());
			
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

	public String untrashFile(Filex theFile) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for update statement
			String sql = "UPDATE file SET trash_status = ? WHERE id = ? AND user_id = ?";

			// prepare the statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, "0");
			myStmt.setInt(2, theFile.getFileId());
			myStmt.setInt(3, theFile.getUserId());
			
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
