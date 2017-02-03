package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.Department;
import com.ticket.model.Issue;
import com.ticket.model.User;
import com.ticket.util.ConnectionUtil;

public class IssueDAO implements Dao<Issue> {
	private JdbcTemplate jdbcTemplate=ConnectionUtil.getJdbcTemplate();
	@Override
	public void save(Issue issue) {
	String sql = "INSERT INTO ISSUES(USER_ID,DEPARTMENT_ID,SUBJECT,DESCRIPTION,PRIORITY)VALUES(?,?,?,?,?)";
	Object[] params = {issue.getUserId().getId(),issue.getDepartmentId().getId(),issue.getSubject(),issue.getDescription(),issue.getPriority()};
	int rows = jdbcTemplate.update(sql, params);
	System.out.println(rows);
}
	@Override
public void update(Issue issue) {
	String sql = "UPDATE ISSUES SET USER_ID=?,DEPARTMENT_ID=?,SUBJECT=?,DESCRIPTION=?,DATE_REPORTED=?,DATE_RESOLVED=?,STATUS=? WHERE ID=?";
	Object[] params = {issue.getUserId(),issue.getDepartmentId(),issue.getSubject(),issue.getDescription(),issue.getDateReported(),issue.getDateResolved(),issue.getStatus(),issue.getId()};
	int rows = jdbcTemplate.update(sql, params);
	System.out.println(rows);

}
	
	public void updateDescription(Issue issue) {
		String sql = "UPDATE ISSUES SET DESCRIPTION=?,STATUS=? WHERE ID=? AND USER_ID=?";
		Object[] params = {issue.getDescription(),issue.getStatus(),issue.getId(),issue.getUserId().getId()};
		int rows = jdbcTemplate.update(sql, params);
		System.out.println(rows);

	}
	
	public void updateClose(Issue issue) {
		String sql = "UPDATE ISSUES SET STATUS='Closed' WHERE ID=? AND USER_ID=?";
		Object[] params = {issue.getId(),issue.getUserId().getId()};
		int rows = jdbcTemplate.update(sql, params);
		System.out.println(rows);

	}
	
	@Override
public void delete(int id) {
	String sql = "DELETE FROM ISSUES WHERE ID=?";
	Object params = id;
	int rows = jdbcTemplate.update(sql, params);
	System.out.println(rows);

}
	
@Override
public List<Issue> findAll() {
	String sql = "SELECT ID,USER_ID,DEPARTMENT_ID,SUBJECT,DESCRIPTION,DATE_REPORTED,DATE_RESOLVED,STATUS,PRIORITY FROM ISSUES";
	return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));

}

public List<Issue> findUserDetails(int userId) {
	String sql = "SELECT ID,USER_ID,SUBJECT,DESCRIPTION,STATUS,PRIORITY FROM ISSUES WHERE USER_ID=?";
	Object[] params = { userId };
	return jdbcTemplate.query(sql,params, (rs, rowNo) ->{
		Issue issue=new Issue();
		issue.setId(rs.getInt("ID"));
		
		User user=new User();
		user.setId(rs.getInt("USER_ID"));
		issue.setUserId(user);
		
		issue.setSubject(rs.getString("SUBJECT"));
		issue.setDescription(rs.getString("DESCRIPTION"));
		issue.setStatus(rs.getString("STATUS"));
		issue.setPriority(rs.getString("PRIORITY"));
		return issue;
	
	});

}

@Override
public Issue findOne(int id) {
	String sql = "SELECT ID,USER_ID,DEPARTMENT_ID,SUBJECT,DESCRIPTION,DATE_REPORTED,DATE_RESOLVED,STATUS,PRIORITY FROM ISSUES WHERE ID = ?";
	Object[] params = { id };
	return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));

}

public Issue findStatus(int userId,int id) {
	String sql = "SELECT STATUS FROM ISSUES WHERE USER_ID=? AND ID = ?";
	Object[] params = { userId,id };
	return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
		Issue issue=new Issue();
		issue.setStatus(rs.getString("Status"));
		return issue;
	
	});

}

private Issue convert(ResultSet rs) throws SQLException {
	Issue issue = new Issue();
	issue.setId(rs.getInt("ID"));
	
	User user=new User();
	user.setId(rs.getInt("USER_ID"));
	issue.setUserId(user);
	
	Department department=new Department();
	department.setId(rs.getInt("DEPARTMENT_ID"));
	issue.setDepartmentId(department);
	
	issue.setSubject(rs.getString("SUBJECT"));
	issue.setDescription(rs.getString("DESCRIPTION"));
	issue.setDateReported(rs.getDate("DATE_REPORTED").toLocalDate());
	issue.setDateResolved(rs.getDate("DATE_RESOLVED").toLocalDate());
	issue.setStatus(rs.getString("STATUS"));
	issue.setPriority(rs.getString("PRIORITY"));
	return issue;
}


}
