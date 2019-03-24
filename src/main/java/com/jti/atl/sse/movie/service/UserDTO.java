package com.jti.atl.sse.movie.service;

public class UserDTO {
	private Integer id;
	private String userName;
	
	public UserDTO(Integer id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
