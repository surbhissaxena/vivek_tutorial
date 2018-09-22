package com.ss.dto;

public class UserDTO {
	private long userId;
	private long profileId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String userPassword;
	private String contactNo;
	private String address;
	private String userName;
	private String passToken;
	private String link;
	private String resetPassword;
	private String role;


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(String resetPassword) {
		this.resetPassword = resetPassword;
	}

	public long getProfileId() {
		return profileId;
	}

	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contectNo) {
		this.contactNo = contectNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getUserId() {
		return (int) userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPassToken() {
		return passToken;
	}

	public void setPassToken(String passToken) {
		this.passToken = passToken;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", profileId=" + profileId
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", userPassword=" + userPassword
				+ ", contactNo=" + contactNo + ", address=" + address
				+ ", userName=" + userName + ", passToken=" + passToken
				+ ", link=" + link + ", resetPassword=" + resetPassword
				+ ", role=" + role + "]";
	}

	
}
