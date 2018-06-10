package com.ems.dao;

import java.util.List;
import java.util.Map;

import com.ems.model.Department;
import com.ems.model.Employee;
import com.ems.model.RegulationAnalysis;
import com.ems.model.RegulationDetail;

public interface BaseDao {
	 public boolean login(Long employeeId, String password);
	 public boolean addEmployee(String firstname, String lastname, String dob, String department);
	 public boolean register(Employee employee);
	 public Employee findTypeOfEmployee(String username);
	 public boolean createDepartment(Department department);
	 public boolean deleteDepartment(Department department);
	 public List<String> getAllDepartments(String deptName);
	 public Map<Long, List<String>> getAllHeadofDeptsNames();
	 public boolean updateHeadOfDeptInfo(Department dept);
	 public Employee getEmployeeDetails(Long id);
	 public List<Employee> getAllEmployees();
	 public boolean deleteEmployee(Employee emp);
	 public Department findIdByDepartmentName(String departmentName);
	 public Employee findEmployeeById(Long id);
	 public boolean addRegulation(RegulationDetail regulation);
	 public List<RegulationDetail> getAllRegulations();
	 public RegulationDetail findRegulationById(Long id);
	 public boolean deleteRegulation(RegulationDetail regulation);
	 public boolean updateEmployee(Employee employeeUpdated);
	 public List<Employee> getEmployeesByDepartment(String departmentName, String role);
	 public Long findEmployeeIdByFullName(String firstname, String lastname);
	 public boolean assignNewRegulation(RegulationAnalysis regAnalysis);
	 public List<Long> findAssignedRegylationsForEmployee();
	 public RegulationAnalysis findRegulationByEmployeeId(Long employeeId);
	 public boolean saveCommentToRegulation(RegulationAnalysis regAnalysis);
	 public Long findIdByDepartmentHead(String departmentHeadName);
	

}
