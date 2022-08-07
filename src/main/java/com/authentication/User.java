package com.authentication;

public class User {

	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String role;
	private String memoryUsage;
	private String profilePicture;
	private boolean status;
	
	public User(int id, String firstname, String lastname, String email, String password) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}
	
	public User(int id, String firstname, String lastname, String email) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	
	public User(int id, String firstname, String lastname, String email, String role,  String profilePicture) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.profilePicture = profilePicture;
	}
	
	public User(int id, String firstname, String lastname, String email, String role, String memoryUsage, String profilePicture, boolean status) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.memoryUsage = memoryUsage;
		this.profilePicture = profilePicture;
		this.status = status;
	}
	
	public User(String firstname, String lastname, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	public User(String firstname, String lastname, String email, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMemoryUsage() {
		return memoryUsage;
	}

	public void setMemoryUsage(String memoryUsage) {
		this.memoryUsage = memoryUsage;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + "]";
	}
	
}
