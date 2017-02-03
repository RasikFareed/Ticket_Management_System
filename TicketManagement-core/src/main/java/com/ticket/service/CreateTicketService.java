package com.ticket.service;

import com.ticket.dao.CreateTicketDAO;
import com.ticket.exception.PersistenceException;
import com.ticket.exception.ServiceException;
import com.ticket.exception.ValidatorException;
import com.ticket.validator.CreateTicketValidator;

public class CreateTicketService {
	
	CreateTicketValidator createTicketValidator=new CreateTicketValidator();
	CreateTicketDAO createTicketDao=new CreateTicketDAO();
	
	public void createTicket(String emailId,String password,String subject,String description,String department,String priority) throws ServiceException, PersistenceException {
		
		try {
			createTicketValidator.createTicket(emailId, password, subject, description, department, priority);
			createTicketDao.createTicket(emailId, password, subject, description, department, priority);
		} catch (ValidatorException e) {
			throw new ServiceException("Cannot Create Ticket",e);
			
		}
	}
	
	
public void updateTicket(String emailId,String password,int issueId,String updateDescription) throws ServiceException, PersistenceException {
		
		try {
			createTicketValidator.updateTicket(emailId, password, issueId, updateDescription);
			createTicketDao.updateTicket(emailId, password, issueId, updateDescription);
		} catch (ValidatorException e) {
			throw new ServiceException("Cannot Create Ticket",e);
			
		}
	}

	public void updateClose(String emailId,String password,int issueId) throws ServiceException, PersistenceException {
		
		try {
			createTicketValidator.updateClose(emailId, password,issueId);
			createTicketDao.updateClose(emailId, password,issueId);
		} catch (ValidatorException e) {
			throw new ServiceException("Cannot Create Ticket",e);
			
		}
	}

}