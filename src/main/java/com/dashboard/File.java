package com.dashboard;

public class File {

	private int id;
	private int folderId;
	private int userId;
	private String displayName;
	private String newName;
	private String fileType;
	private String fileSize;
	private String fileHash;
	private String updatedAt;
	private String createdAt;
	private boolean favStatus;
	private boolean trashStatus;
	private boolean status;
	
	
	public File(int id, int folderId, int userId, String displayName, String newName, String fileType, String fileSize,
			String fileHash, String updatedAt, String createdAt, boolean favStatus, boolean trashStatus,
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
	
	public String getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public boolean isFavStatus() {
		return favStatus;
	}
	
	public void setFavStatus(boolean favStatus) {
		this.favStatus = favStatus;
	}
	
	public boolean isTrashStatus() {
		return trashStatus;
	}
	
	public void setTrashStatus(boolean trashStatus) {
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
				+ fileHash + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt + ", favStatus=" + favStatus
				+ ", trashStatus=" + trashStatus + ", status=" + status + "]";
	}
}
