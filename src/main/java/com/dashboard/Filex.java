package com.dashboard;

import java.util.Date;

public class Filex {

	private int id;
	private int folderId;
	private int userId;
	private String displayName;
	private String newName;
	private String fileType;
	private String fileSize;
	private String fileHash;
	private String filePath;
	private Date updatedAt;
	private Date createdAt;
	private int favStatus;
	private int trashStatus;
	private boolean status;
	
	
	public Filex(int id, int folderId, int userId, String displayName, String newName, String fileType, String fileSize,
			String fileHash, Date updatedAt, Date createdAt, int favStatus, int trashStatus,
			boolean status) {
		super();
		this.id = id;
		this.folderId = folderId;
		this.userId = userId;
		this.displayName = displayName;
		this.newName = newName;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.fileHash = fileHash;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
		this.favStatus = favStatus;
		this.trashStatus = trashStatus;
		this.status = status;
	}	
	
	public Filex(int id, int folderId, String displayName, String newName, String fileType, String fileSize,
			String fileHash, int favStatus, int trashStatus, Date updatedAt, Date createdAt) {
		super();
		this.id = id;
		this.folderId = folderId;
		this.displayName = displayName;
		this.newName = newName;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.fileHash = fileHash;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
		this.favStatus = favStatus;
		this.trashStatus = trashStatus;
	}

	public Filex(int userId, int folderId, String displayName, String newName, String fileType, String fileSize,
			String fileHash, String filePath) {
		super();
		this.userId = userId;
		this.folderId = folderId;
		this.displayName = displayName;
		this.newName = newName;
		this.fileType = fileType;
		this.fileSize = fileSize;
		this.fileHash = fileHash;
		this.filePath = filePath;
	}

	public Filex(int folderId, int userId) {
		this.folderId = folderId;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public String getDisplayName() {
		return displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getNewName() {
		return newName;
	}
	
	public void setNewName(String newName) {
		this.newName = newName;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFileType() {
		return fileType;
	}
	
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public String getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	public String getFileHash() {
		return fileHash;
	}
	
	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
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
		return "File [id=" + id + ", folderId=" + folderId + ", userId=" + userId + ", displayName=" + displayName
				+ ", newName=" + newName + ", fileType=" + fileType + ", fileSize=" + fileSize + ", fileHash="
				+ fileHash + ", filePath=" + filePath + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt
				+ ", favStatus=" + favStatus + ", trashStatus=" + trashStatus + ", status=" + status + "]";
	}
}
