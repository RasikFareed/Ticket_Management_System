package com.ticket.service;

import com.ticket.dao.CreateTicketDAO;
import com.ticket.exception.PersistenceException;
import com.ticket.exception.ServiceException;
import com.ticket.model.Issue;
import com.ticket.model.User;

public class TestCreateTicketService {

	public static void main(String[] args) throws ServiceException, PersistenceException {

		CreateTicketService cts=new CreateTicketService();
		CreateTicketDAO createTicket=new CreateTicketDAO();
		User user=new User();
		user.setId(3);
		int userId=user.getId();
		user.setEmailId("rasik@gmail.com");
		user.setPassword("abc123");
		
		Issue issue=new Issue();
		issue.setId(7);
		int issueId=issue.getId();
		
		String subject="Login Failure";
		String description="I could not login on my computer";
		String department="HR";
		String priority="High";
		
		cts.createTicket(user.getEmailId(),user.getPassword(), subject, description, department, priority);
		cts.updateTicket(user.getEmailId(),user.getPassword(), issueId, description);
		cts.updateClose(user.getEmailId(),user.getPassword(),issueId);

	}

}
