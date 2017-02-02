package com.ticket.dao;

import java.util.Iterator;
import java.util.List;

import com.ticket.model.Issue;
import com.ticket.model.User;

public class TestCreateTicketDAO {

	public static void main(String[] args) {

		CreateTicketDAO createTicket=new CreateTicketDAO();
		User user=new User();
		user.setId(3);
		int userId=user.getId();
		
		Issue issue=new Issue();
		issue.setId(5);
		int issueId=issue.getId();
		
		String subject="Login Failure";
		String description="I could not login in revature platform";
		String department="HR";
		String priority="High";
		//createTicket.createTicket(userId, subject, description, department, priority);
		//createTicket.updateTicket(userId, issueId, description);
		//createTicket.updateClose(userId, issueId);
		
		IssueDAO issueDao=new IssueDAO();
		
		List<Issue> list = issueDao.findUserDetails(userId);
		Iterator<Issue> i = list.iterator();
		while (i.hasNext()) {
			Issue issues = (Issue) i.next();
			System.out.println(issues.getId()+ "\t" +issues.getSubject() + "\t"
					+ issues.getDescription() + "\t" +issues.getDateReported()+"\t"+ "\t" +issues.getDateResolved()+issues.getStatus());
		}
	}

}
