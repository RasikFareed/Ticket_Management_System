package com.ticket.dao;

import java.util.Iterator;
import java.util.List;

import com.ticket.exception.PersistenceException;
import com.ticket.model.Department;
import com.ticket.model.Issue;
import com.ticket.model.User;

public class CreateTicketDAO {
	Issue issue=new Issue();
	IssueDAO issueDao=new IssueDAO();
	
	public void createTicket(String emailId,String password,String subject,String description,String department,String priority) throws PersistenceException{

	LoginDAO loginDao=new LoginDAO();
	if(loginDao.login(emailId, password)){
			
		User user=new User();
		UserDAO userDao=new UserDAO();
		
		int userId=userDao.findUserId(emailId).getId();
		user.setId(userId);
		issue.setUserId(user);
		
		issue.setSubject(subject);
		issue.setDescription(description);
		
		Department departments=new Department();
		DepartmentDAO departmentDao=new DepartmentDAO();
		int departmentId=departmentDao.findId(department).getId();
		departments.setId(departmentId);
		issue.setDepartmentId(departments);
		issue.setPriority(priority);
		issueDao.save(issue);
		}
	else
	{
		System.out.println("Incorrect user name or password");
	}
}
	
	public void updateTicket(String emailId,String password,int issueId,String updateDescription) throws PersistenceException{
		
		LoginDAO loginDao=new LoginDAO();
		if(loginDao.login(emailId, password)){
			User user=new User();
			UserDAO userDao=new UserDAO();
			
			int userId=userDao.findUserId(emailId).getId();
			user.setId(userId);
			issue.setUserId(user);
			
			if("Closed".equals(issueDao.findStatus(userId, issueId).getStatus()) || "CLOSED".equals(issueDao.findStatus(userId, issueId).getStatus()) ){
		
			System.out.println("You cant update now!");
			}
		else
		{
	
		issue.setUserId(user);
		
		issue.setId(issueId);
		issue.setStatus("Inprogress");
		issue.setDescription(updateDescription);
		
		issueDao.updateDescription(issue);
		}
	}
		else
		{
			System.out.println("Incorrect user name or password");
		}
}
	
	public void updateClose(String emailId,String password,int issueId) throws PersistenceException{
		
		LoginDAO loginDao=new LoginDAO();
		if(loginDao.login(emailId, password)){
			
		User user=new User();
		UserDAO userDao=new UserDAO();
			
		int userId=userDao.findUserId(emailId).getId();
		user.setId(userId);
		issue.setUserId(user);
		
		issue.setId(issueId);
		
		issueDao.updateClose(issue);
		}
		else{
			System.out.println("Incorrect user name or password");
		}
	}
	
	public void findUserDetails(String emailId,String password) throws PersistenceException{
		LoginDAO loginDao=new LoginDAO();
		if(loginDao.login(emailId, password)){
			
		User user=new User();
		UserDAO userDao=new UserDAO();
			
		int userId=userDao.findUserId(emailId).getId();
		user.setId(userId);
		issueDao.findUserDetails(user.getId());
		List<Issue> list = issueDao.findUserDetails(userId);
		Iterator<Issue> i = list.iterator();
		while (i.hasNext()) {
			Issue issues = (Issue) i.next();
			System.out.println(issues.getId()+ "\t"+issues.getUserId().getId()+"\t" +issues.getSubject() + "\t"
					+ issues.getDescription() + "\t"+ issues.getStatus());
		}
		}
		else
		{
			System.out.println("Incorrect user name or password");
		}
		
	}

}
