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
	
	public Folder getFolder(Folder theFolder) throws Exception {
		Folder folderData = null;

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create a sql statement
			String sql = "SELECT * FROM folder WHERE id = ? AND folder_id = ? AND user_id = ?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, theFolder.getFolderId());
			myStmt.setInt(2, theFolder.getParentFolderId());
			myStmt.setInt(3, theFolder.getUserId());
			
			//
			myRs = myStmt.executeQuery();

			// process result set
			if (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				int folderId = myRs.getInt("folder_id");
				String folderName = myRs.getString("folder_name");
				int favStatus = myRs.getInt("fav_status");
				int trashStatus = myRs.getInt("trash_status");
				Date updatedAt = myRs.getDate("updated_at");

				// create new folder object
				 folderData = new Folder(id, folderId, folderName, favStatus, trashStatus, updatedAt);

				

			}else {
				throw new Exception("The folder does not exist: " + theFolder.getFolderId());
			}

			return folderData;
		}
		catch(Exception e) {
			return folderData;
		}
		finally {
			// close JDBC object
			close(myConn, myStmt, myRs);

		}
	}
	
	public List<Folder> getStarredFolders(Folder theFolder) throws Exception {
		List<Folder> folders = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create a sql statement
			String sql = "SELECT * FROM folder WHERE folder_id = ? AND user_id = ? AND fav_status = '1' ";

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
	
	public List<Folder> getTrashedFolders(Folder theFolder) throws Exception {
		List<Folder> folders = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create a sql statement
			String sql = "SELECT * FROM folder WHERE folder_id = ? AND user_id = ? AND trash_status = '1' ";

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
	
	public List<Filex> getFiles(Filex theFile) {
		List<Filex> files = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create a sql statement
			String sql = "SELECT * FROM file WHERE folder_id = ? AND user_id = ?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, theFile.getFolderId());
			myStmt.setInt(2, theFile.getUserId());
			
			//
			myRs = myStmt.executeQuery();

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				int folderId = myRs.getInt("folder_id");
				String fileDisplayName = myRs.getString("file_displayname");
				String fileNewName = myRs.getString("file_newname");
				String fileType = myRs.getString("file_type");
				String fileSize = myRs.getString("file_size");
				String fileHash = myRs.getString("file_hash");
				int favStatus = myRs.getInt("fav_status");
				int trashStatus = myRs.getInt("trash_status");
				Date createdAt = myRs.getDate("created_at");
				Date updatedAt = myRs.getDate("updated_at");

				// create new folder object
				Filex tempFile= new Filex(id, folderId, fileDisplayName, fileNewName, fileType, fileSize, fileHash,favStatus, trashStatus, createdAt, updatedAt);

				// add it to the list of folders
				files.add(tempFile);

			}

			return files;
		}
		catch(Exception e) {
			return files;
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
	
}
