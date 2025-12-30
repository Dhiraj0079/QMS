package com.sunbeam.bean;
import com.sunbeam.pojos.*;
import com.sunbeam.daos.*;
public class RegisterBean {
	private  int id;
	private String name;
	private String email;
	private String password;
	private String role;
	private boolean regStatus;
	
	public RegisterBean() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(boolean regStatus) {
		this.regStatus = regStatus;
	}

	public void registerStudent() {
		try(UserDao userDao=new UserDaoImpl()){
			User user=new User(id, name, email, password, role);
			int count=userDao.save(user);
			this.regStatus=count==1;
			
		}
		catch (Exception e) {
e.printStackTrace();		}
	}
	
	
	

}
