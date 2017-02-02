package com.ticket.dao;


import com.ticket.model.User;


public class LoginDAO {
	

	User user=new User();
	UserDAO userDao=new UserDAO();
	public boolean login(String emailId,String password){
		String s =userDao.findOne(emailId).getPassword();
		if(s.equals(password))
			return true;
		return false;
		
	}

}
