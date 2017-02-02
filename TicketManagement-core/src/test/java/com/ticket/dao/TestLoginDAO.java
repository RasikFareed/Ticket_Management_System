package com.ticket.dao;

public class TestLoginDAO {

	public static void main(String[] args) {
		
		LoginDAO loginDao=new LoginDAO();
		System.out.println(loginDao.login("rasik@gmail.com", "abc123"));

	}

}
