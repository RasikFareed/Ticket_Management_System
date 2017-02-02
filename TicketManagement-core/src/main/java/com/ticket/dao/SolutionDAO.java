package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import com.ticket.model.Employee;
import com.ticket.model.Issue;
import com.ticket.model.Solution;
import com.ticket.util.ConnectionUtil;

public class SolutionDAO implements Dao<Solution>{
	
	private JdbcTemplate jdbcTemplate=ConnectionUtil.getJdbcTemplate();
	@Override
	public void save(Solution solution) {
	String sql = "INSERT INTO SOLUTIONS(ISSUE_ID,EMPLOYEE_ID,RESOLUTION_DESCRIPTION)(?,?,?)";
	Object[] params = {solution.getIssueId(),solution.getEmployeeId(),solution.getResolutionDescription()};
	int rows = jdbcTemplate.update(sql, params);
	System.out.println(rows);
}
	@Override
public void update(Solution solution) {
	String sql = "UPDATE ISSUES SET ISSUE_ID=?,EMPLOYEE_ID=?,RESOLUTION_DESCRIPTION=? WHERE ID=?";
	Object[] params = {solution.getIssueId(),solution.getEmployeeId(),solution.getResolutionDescription(),solution.getId()};
	int rows = jdbcTemplate.update(sql, params);
	System.out.println(rows);

}
	@Override
public void delete(int id) {
	String sql = "DELETE FROM SOLUTIONS WHERE ID=?";
	Object params = id;
	int rows = jdbcTemplate.update(sql, params);
	System.out.println(rows);

}
	
@Override
public List<Solution> findAll() {
	String sql = "SELECT ID,ISSUE_ID,EMPLOYEE_ID,RESOLUTION_DESCRIPTION FROM SOLUTIONS";
	return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));

}

@Override
public Solution findOne(int id) {
	String sql = "SELECT ID,ISSUE_ID,EMPLOYEE_ID,RESOLUTION_DESCRIPTION FROM SOLUTIONS WHERE ID = ?";
	Object[] params = { id };
	return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));

}

private Solution convert(ResultSet rs) throws SQLException {
	Solution solution = new Solution();
	solution.setId(rs.getInt("ID"));
	
	Issue issue=new Issue();
	issue.setId(rs.getInt("ISSUE_ID"));
	solution.setIssueId(issue);
	
	Employee employee=new Employee();
	employee.setId(rs.getInt("EMPLOYEE_ID"));
	solution.setEmployeeId(employee);
	
	solution.setResolutionDescription(rs.getString("RESOLUTION_DESCRIPTION"));
	
	return solution;
}


}
