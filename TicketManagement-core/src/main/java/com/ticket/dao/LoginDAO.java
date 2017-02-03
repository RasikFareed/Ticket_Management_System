package com.ticket.dao;

import com.ticket.exception.PersistenceException;

public class LoginDAO {

	UserDAO userDao=new UserDAO();
	public boolean login(String emailId,String password) throws PersistenceException{
		String s =userDao.findOne(emailId).getPassword();
		if(s.equals(password))
			return true;
		return false;
		
	}

}
