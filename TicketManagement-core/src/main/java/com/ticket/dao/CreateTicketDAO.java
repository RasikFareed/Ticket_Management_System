package com.ticket.dao;

import com.ticket.model.Department;
import com.ticket.model.Issue;
import com.ticket.model.User;

public class CreateTicketDAO {
	Issue issue=new Issue();
	IssueDAO issueDao=new IssueDAO();
	
	public void createTicket(int userId,String subject,String description,String department,String priority){

		User user=new User();
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
	
	public void updateTicket(int userId,int issueId,String updateDescription){
		if("Closed".equals(issueDao.findStatus(userId, issueId).getStatus()) || "CLOSED".equals(issueDao.findStatus(userId, issueId).getStatus()) ){
		
			System.out.println("You cant update now!");
		}
		else
		{
		User user=new User();
		user.setId(userId);
		issue.setUserId(user);
		
		issue.setId(issueId);
		issue.setStatus("Inprogress");
		issue.setDescription(updateDescription);
		
		issueDao.updateDescription(issue);
		}
	
	}
	
	public void updateClose(int userId,int issueId){
		User user=new User();
		user.setId(userId);
		issue.setUserId(user);
		
		issue.setId(issueId);
		
		issueDao.updateClose(issue);
		
	}

}
