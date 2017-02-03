package com.ticket.dao;

import com.ticket.exception.PersistenceException;
import com.ticket.model.Issue;
import com.ticket.model.User;

public class TestCreateTicketDAO {

	public static void main(String[] args) throws PersistenceException {

		CreateTicketDAO createTicket=new CreateTicketDAO();
		User user=new User();
		user.setId(3);
		int userId=user.getId();
		user.setEmailId("sgbfsf");
		user.setPassword("abc123");
		
		Issue issue=new Issue();
		issue.setId(7);
		int issueId=issue.getId();
		
		String subject="Login Failure";
		String description="I could not login on my computer";
		String department="HR";
		String priority="High";
		createTicket.createTicket(user.getEmailId(),user.getPassword(), subject, description, department, priority);
		//createTicket.updateTicket(user.getEmailId(),user.getPassword(), issueId, description);
		//createTicket.updateClose(user.getEmailId(),user.getPassword(),issueId);
		
/*		IssueDAO issueDao=new IssueDAO();
		
		List<Issue> list = issueDao.findUserDetails(userId);
		Iterator<Issue> i = list.iterator();
		while (i.hasNext()) {
			Issue issues = (Issue) i.next();
			System.out.println(issues.getId()+ "\t" +issues.getSubject() + "\t"
					+ issues.getDescription() + "\t" +issues.getDateReported()+"\t"+ "\t" +issues.getDateResolved()+issues.getStatus());
		}*/
	}

}
