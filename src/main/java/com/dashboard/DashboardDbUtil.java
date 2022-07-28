package com.dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

public class DashboardDbUtil {

	private DataSource dataSource;
	
	private Connection myConn;

	public DashboardDbUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public List<Folder> getFolders(Folder theFolder) throws Exception {
		List<Folder> folders = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create a sql statement
			String sql = "SELECT * FROM folder WHERE folder_id = ? AND user_id = ? AND trash_status = '0' ";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, theFolder.getFolderId());
			myStmt.setInt(2, theFolder.getUserId());
			
			//
			myRs = myStmt.executeQuery();

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				int folderId = myRs.getInt("folder_id");
				String folderName = myRs.getString("folder_name");
				int favStatus = myRs.getInt("fav_status");
				int trashStatus = myRs.getInt("trash_status");
				Date updatedAt = myRs.getDate("updated_at");

				// create new folder object
				Folder tempFolder = new Folder(id, folderId, folderName, favStatus, trashStatus, updatedAt);

				// add it to the list of folders
				folders.add(tempFolder);

			}

			return folders;
		}
		catch(Exception e) {
			return folders;
		}
		finally {
			// close JDBC object
			close(myConn, myStmt, myRs);

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


	public List<File> getFiles() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
