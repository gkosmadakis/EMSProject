package com.ems.service;

import java.util.List;
import java.util.Map;

import com.ems.dao.BaseDao;
import com.ems.dao.BaseDaoImpl;
import com.ems.model.Department;
import com.ems.model.Employee;
import com.ems.model.RegulationAnalysis;
import com.ems.model.RegulationDetail;

public class BaseServiceImpl implements BaseService {

	 private BaseDao baseDao = new BaseDaoImpl();

	 @Override 
	 public boolean login(Long employeeId, String password) {
	  return baseDao.login(employeeId, password);
	 }
	 
	 @Override
	 public boolean addEmployee(String firstname, String lastname, String dob, String department) {
	  return baseDao.addEmployee(firstname, lastname, dob, department);
	 }

	 @Override
	 public boolean register(Employee employee) {
	  return baseDao.register(employee);
	 }
	 
	 @Override
	 public Employee findTypeOfEmployee(String username) {
	  return baseDao.findTypeOfEmployee(username); 
	 }
	 
	 @Override
	 public boolean createDepartment(Department department) {
	  return baseDao.createDepartment(department);
	 }
	 
	 @Override
	 public boolean deleteDepartment(Department department) {
	  return baseDao.deleteDepartment(department);
	 }

	@Override
	public List<String> getAllDepartments(String deptName) {
		return baseDao.getAllDepartments(deptName);
	}

	@Override
	public Map<Long, List<String>> getAllHeadOfDeptsNames() {
		
		return baseDao.getAllHeadofDeptsNames();
	}

	@Override
	public boolean updateHeadOfDeptInfo(Department dept) {
		
		return baseDao.updateHeadOfDeptInfo(dept);
	}

	@Override
	public Employee getEmployeeDetails(Long id) {
		
		return baseDao.getEmployeeDetails(id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		return baseDao.getAllEmployees();
	}

	@Override
	public boolean deleteEmployee(Employee emp) {
		
		return  baseDao.deleteEmployee(emp);
	}

	@Override
	public Department findIdByDepartmentName(String departmentName) {
		
		return baseDao.findIdByDepartmentName(departmentName);
	}
	
	public Employee findEmployeeById(Long id) {
		
		return baseDao.findEmployeeById(id);
		
	}

	@Override
	public boolean addRegulation(RegulationDetail regulation) {
		
		return baseDao.addRegulation(regulation);
	}

	@Override
	public List<RegulationDetail> getAllRegulations() {
		
		return baseDao.getAllRegulations();
	}

	@Override
	public RegulationDetail findRegulationById(Long id) {
		
		return baseDao.findRegulationById(id);
	}

	@Override
	public boolean deleteRegulation(RegulationDetail regulation) {
		
		return baseDao.deleteRegulation(regulation);
	}

	@Override
	public boolean updateEmployee(Employee employeeUpdated) {
		
		return baseDao.updateEmployee(employeeUpdated);
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String departmentName, String role) {
		
		return baseDao.getEmployeesByDepartment(departmentName, role);
	}

	@Override
	public Long findEmployeeIdByFullName(String firstname, String lastname) {
		
		return baseDao.findEmployeeIdByFullName(firstname,lastname);
	}

	@Override
	public boolean assignNewRegulation(RegulationAnalysis regAnalysis) {
		
		return baseDao.assignNewRegulation(regAnalysis);
	}

	@Override
	public List<Long> findAssignedRegulationsForEmployee() {
		
		return baseDao.findAssignedRegylationsForEmployee();
	}

	@Override
	public RegulationAnalysis findRegulationByEmployeeId(Long employeeId) {
	
		return baseDao.findRegulationByEmployeeId(employeeId);
	}

	@Override
	public boolean updateRegulation(RegulationAnalysis regAnalysis) {
		
		return baseDao.saveCommentToRegulation(regAnalysis);
	}

	@Override
	public Long findIdByDepartmentHead(String departmentHeadName) {
		
		return baseDao.findIdByDepartmentHead(departmentHeadName);
	}

}
