package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.Department;
import com.ticket.model.Employee;
import com.ticket.model.Role;
import com.ticket.util.ConnectionUtil;

public class EmployeeDAO implements Dao<Employee> {

		private JdbcTemplate jdbcTemplate=ConnectionUtil.getJdbcTemplate();
		@Override
		public void save(Employee employee) {
		String sql = "INSERT INTO EMPLOYEES(DEPARTMENT_ID,ROLE_ID,NAME,EMAIL,PASSWORD)VALUES(?,?,?,?)";
		Object[] params = {employee.getDepartmentId(),employee.getRoleId(),employee.getName(),employee.getEmailId(),employee.getPassword()};
		int rows = jdbcTemplate.update(sql, params);
		System.out.println(rows);
	}
		@Override
	public void update(Employee employee) {
		String sql = "UPDATE EMPLOYEES SET DEPARTMENT_ID=?,ROLE_ID=?, NAME=?,EMAIL=?,PASSWORD=?,ACTIVE=? WHERE ID=?";
		Object[] params = {employee.getDepartmentId(),employee.getRoleId(),employee.getName(),employee.getEmailId(),employee.getPassword(),employee.isActive(),employee.getId()};
		int rows = jdbcTemplate.update(sql, params);
		System.out.println(rows);

	}
		@Override
	public void delete(int id) {
		String sql = "DELETE FROM EMPLOYEES WHERE ID=?";
		Object params = id;
		int rows = jdbcTemplate.update(sql, params);
		System.out.println(rows);

	}
		
	@Override
	public List<Employee> findAll() {
		String sql = "SELECT ID,DEPARTMENT_ID,ROLE_ID,NAME,EMAIL,ACTIVE FROM EMPLOYEES";
		return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));

	}

	@Override
	public Employee findOne(int id) {
		String sql = "SELECT ID,DEPARTMENT_ID,ROLE_ID,NAME,EMAIL,ACTIVE FROM EMPLOYEES WHERE ID = ?";
		Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));

	}

	private Employee convert(ResultSet rs) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("ID"));
		
		Department department=new Department();
		department.setId(rs.getInt("DEPARTMENT_ID"));
		employee.setDepartmentId(department);
		
		Role role=new Role();
		role.setId(rs.getInt("ROLE_ID"));
		employee.setRoleId(role);
		
		employee.setName(rs.getString("NAME"));
		employee.setEmailId(rs.getString("EMAIL"));
		employee.setActive(rs.getBoolean("ACTIVE"));
		
		return employee;
	}

}
