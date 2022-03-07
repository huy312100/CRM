package cybersoft.javabackend.crm.dto;

import cybersoft.javabackend.crm.model.Role;

public class UserCreateDto {
	/* Data Transfer Object */
	private String email;
	private String password;
	private String fullname;
	private String avatar;
	private int roleId;
	
	public UserCreateDto(String email, String password, String fullname, String avatar, int roleId) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.roleId = roleId;
	}

	public String getEmail() {
		return email;
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
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
}
