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
			String sql = "INSERT INTO file (user_id, folder_id, file_displayname, file_newname, file_type, file_size, file_hash, file_path) values(?, ?, ?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			// set the param values for the student
			myStmt.setInt(1, newFile.getUserId());
			myStmt.setInt(2, newFile.getFolderId());
			myStmt.setString(3, newFile.getDisplayName());
			myStmt.setString(4, newFile.getNewName());
			myStmt.setString(5, newFile.getFileType());
			myStmt.setString(6, newFile.getFileSize());
			myStmt.setString(7, newFile.getFileHash());
			myStmt.setString(8, newFile.getFilePath());
			
			
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
