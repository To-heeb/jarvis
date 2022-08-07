package com.dashboard;

import java.util.Date;

public class Folder {

	private int id;
	private int folderId;
	private int parentFolderId;
	private int userId;
	private String folderName;
	private Date updatedAt;
	private Date createdAt;
	private int favStatus;
	private int trashStatus;
	private boolean status;
	
	public Folder(int folder_id, int user_id, String folder_name, boolean status) {
		this.folderId = folder_id;
		this.userId = user_id;
		this.folderName = folder_name;
		this.status = status;
	}
	
	public Folder(int id, int folder_id, String folder_name, int fav_status, int trash_status, Date updated_at) {
		this.id = id;
		this.folderId = folder_id;
		this.folderName = folder_name;
		this.favStatus = fav_status;
		this.trashStatus = trash_status;
		this.updatedAt = updated_at;
	}
	
	public Folder(int folder_id, int user_id, String folder_name) {
		this.folderId = folder_id;
		this.userId = user_id;
		this.folderName = folder_name;
	}

	public Folder(int folder_id, int user_id) {
		this.folderId = folder_id;
		this.userId = user_id;
	}
	
	public Folder(int folder_id, int parent_folder_id, int user_id) {
		this.folderId = folder_id;
		this.userId = user_id;
		this.parentFolderId = parent_folder_id;
	}

	public Folder(int id, int folder_id, String folder_name, int fav_status, int trash_status,Date created_at,Date updated_at) {
		this.id = id;
		this.folderId = folder_id;
		this.folderName = folder_name;
		this.favStatus = fav_status;
		this.trashStatus = trash_status;
		this.createdAt = created_at;
		this.updatedAt = updated_at;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setParentFolderId(int parentFolderId) {
		this.parentFolderId = parentFolderId;
	}
	
	public int getParentFolderId() {
		return parentFolderId;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getFolderId() {
		return folderId;
	}

	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public int getFavStatus() {
		return favStatus;
	}

	public void setFavStatus(int favStatus) {
		this.favStatus = favStatus;
	}

	public int getTrashStatus() {
		return trashStatus;
	}

	public void setTrashStatus(int trashStatus) {
		this.trashStatus = trashStatus;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Folder [folderId=" + folderId + ", userId=" + userId + ", folderName=" + folderName + ", favStatus="
				+ favStatus + ", trashStatus=" + trashStatus + ", status=" + status + "]";
	}
}
