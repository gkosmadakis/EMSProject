package com.ems.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.StandardBasicTypes;

import com.ems.controller.AddDepartmentController;
import com.ems.model.Department;
import com.ems.model.Employee;
import com.ems.model.RegulationAnalysis;
import com.ems.model.RegulationDetail;
import com.ems.util.HibernateUtils;

public class BaseDaoImpl implements BaseDao {
	
	private static final Logger logger = Logger.getLogger(BaseDaoImpl.class);
	
	/*All methods in this class contain database transactions*/
	
	@Override
	public boolean login(Long employeeId, String password) {

		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {

				Employee employee = (Employee) session.get(Employee.class, employeeId);

				if (password.equals(employee.getPassword())) {

					logger.info("Employee: " + employee.toString());

					return true;
				}

			} catch (Exception exception) {

				logger.error("Exception occred while reading user data: "
						+ exception.getMessage());

				return false;
			}

		} else {

			logger.info("DB server down..");

		}

		return false;
	}

	@Override
	public boolean addEmployee(String firstname, String lastname, String dob, String department) {

		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {

				Employee employee = (Employee) session.get(Employee.class, firstname);

				if (firstname.equals(employee.getFirstname())) {

					logger.info("Employee: " + employee.toString());

					return true;
				}

			} catch (Exception exception) {

				logger.error("Exception occred while reading employee data: "
						+ exception.getMessage());

				return false;
			}

		} else {

			logger.info("DB server down..");
		}

		return false;
	}

	@Override
	public boolean register(Employee employee) {

		String msg = "Registration unsuccessful, try again.....";

		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {

				if (employee != null) {

					Long id = (Long) session.save(employee);
					session.beginTransaction().commit();

					msg = "Employee " + id
							+ " created successfully, please login...";

					return true;
				}

			} catch (Exception exception) {

				logger.error("Exception occured while reading user data: "
						+ exception.getMessage());
				
				return false;
			}

		} else {

			logger.info("DB server down..");
		}

		logger.info("msg:" + msg);

		return false;

	}

	@SuppressWarnings("deprecation")
	@Override
	public Employee findTypeOfEmployee(String username) {

		Employee employeeRetrieved = null;
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {

					Query<?> query = session.createQuery("from Employee WHERE username = :username")
							.setParameter("username", username);

					session.beginTransaction().commit();

					employeeRetrieved =  (Employee) query.getSingleResult();

			} catch (Exception exception) {

				logger.error("Exception occured while reading user data: "
						+ exception.getMessage());

			}

			finally {

				session.close();

			}

		}

		return employeeRetrieved;

	}

	@Override
	public boolean createDepartment(Department department) {
		
		String msg = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
				
				if (department != null) {

				Long dept = (Long) session.save(department);
				
				session.beginTransaction().commit();

				msg = "Department " + dept
						+ " created successfully...";

					return true;
				}
			

			} catch (Exception exception) {

				logger.error("Exception occured while reading department data: "
						+ exception.getMessage());

				return false;
			}

		} else {

			logger.info("DB server down..");
		}
		
		logger.info("Department Creation status: " + msg);
		
		return false;
	}

	
	@SuppressWarnings("deprecation")
	@Override
	public boolean deleteDepartment(Department department) {
	
		String msg = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
				
				if (department != null) {
					
				session.delete(department);
				
				session.beginTransaction().commit();

				msg = "Department " + department
						+ " deleted successfully...";

					return true;
				}
			

			} catch (Exception exception) {

				logger.error("Exception occured while reading department data: "
						+ exception.getMessage());

				return false;
			}

		} else {

			logger.info("DB server down..");
		}
		
		logger.info("Department Deletion status: " + msg);
		
		return false;
	}
	
	@Override
	public Department findIdByDepartmentName(String departmentName) {
		
		Department department = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
					
				Query<?> query = session.createQuery("SELECT id  FROM Department WHERE "
							+ "deptname = :deptname");

				query.setParameter("deptname", departmentName);

				session.beginTransaction().commit();

				Long departmentRetrieved =  (Long) query.uniqueResult();
					
				department = (Department) session.get(Department.class, departmentRetrieved);
				
				department.setHeadOfDept(department.getHeadOfDept());
				
				department.setId(department.getId());
				
				} catch (Exception exception) {

					logger.error("Exception occured while reading department data: "
							+ exception.getMessage());

					
				}

			} else {

				logger.info("DB server down..");
			}
			
		return department;
		
		
	}
	
	@Override
	public Long findIdByDepartmentHead(String departmentHeadName) {
		
		Long departmentId = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
					
				Query<?> query = session.createQuery("SELECT id  FROM Department WHERE "
							+ "headofdept = :headofdept");

				query.setParameter("headofdept", departmentHeadName);

				session.beginTransaction().commit();

				departmentId =  (Long) query.uniqueResult();
				
				} catch (Exception exception) {

					logger.error("Exception occured while reading department data: "
							+ exception.getMessage());

					
				}

			} else {

				logger.info("DB server down..");
			}
			
		return departmentId;
		
		
	}
	
	@Override
	public List<String> getAllDepartments(String deptName) {
		
		String msg = null;
		
		List<String> departments = new ArrayList<>();
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
				
				if (deptName != null) {

				SQLQuery query = session.createSQLQuery("SELECT deptname FROM department");
				
				session.beginTransaction().commit();
				
				List departmentsRetrieved = query.getResultList();
				
					for (Object dept: departmentsRetrieved) {
						departments.add((String) dept);
					}
					msg = "success";
				}
			

			} catch (Exception exception) {

				logger.error("Exception occured while reading department data: "
						+ exception.getMessage());

			}

		} else {

			logger.info("DB server down..");
		}
		
		logger.info("Department Retrieved status: " + msg);
		
		return departments;
	}

	@Override
	public Map<Long, List<String>> getAllHeadofDeptsNames() {
		
		String msg = null;
		
		Map<Long,List<String>> headOfDeptsWithNames = new HashMap<Long, List<String>>();
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
				
				CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
				CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
				Root<Department> root = criteriaQuery.from(Department.class);
				criteriaQuery.select(root);
				Query<Department> query = session.createQuery(criteriaQuery);
				List<Department> departmentsRetrieved = query.getResultList();
				
				session.beginTransaction().commit();
				
				 for(int i =0; i<departmentsRetrieved.size(); i ++) {
					 List<String> list =new ArrayList<String>();
					 list.add("Department:"+departmentsRetrieved.get(i).getDeptName());
					 list.add("HeadOfDept:"+departmentsRetrieved.get(i).getHeadOfDept());
					 headOfDeptsWithNames.put(departmentsRetrieved.get(i).getId(), list);
					 
				 }
				 
				msg = "success";
		
			} catch (Exception exception) {

				logger.error("Exception occured while reading department data: "
						+ exception.getMessage());

			}

		} else {

			logger.info("DB server down..");
		}
		
		logger.info("Head of Department Names Retrieved status: " + msg);
		
		return headOfDeptsWithNames;
	}

	@Override
	public boolean updateHeadOfDeptInfo(Department department) {
		
		String msg = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
				
				if (department != null) {

				Department dept = new Department();
				
				dept.setId(department.getId());
				
				dept.setDeptName(department.getDeptName());
				
				dept.setHeadOfDept(department.getHeadOfDept());
				
				session.update(dept);
				
				session.beginTransaction().commit();

				msg = "Department Head" + department.getDeptName()
						+ " updated successfully...";

					return true;
				}
			

			} catch (Exception exception) {

				logger.error("Exception occured while reading department data: "
						+ exception.getMessage());

				return false;
			}

		} else {

			logger.info("DB server down..");
		}
		
		logger.info("Department Head Info status: " + msg);
	
		return false;
	}

	@Override
	public Employee getEmployeeDetails(Long id) {
		
		Employee employee = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {

				employee = (Employee) session.get(Employee.class, id);

			} catch (Exception exception) {

				logger.error("Exception occured while reading user data: "
						+ exception.getMessage());

			}

			finally {

				session.close();

			}

		}

		
		return employee;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() {

		String msg = null;

		List<Employee> employees = new ArrayList<>();

		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {

				Criteria cr = session.createCriteria(Employee.class);

				employees =  (List<Employee>)cr.list();

				session.beginTransaction().commit();

				msg = "success";

			} catch (Exception exception) {

				logger.error("Exception occured while reading department data: "
						+ exception.getMessage());

			}

		} else {

			logger.info("DB server down..");
		}

		logger.info("Employee Retrieved status: " + msg);

		return employees;
	}

	@Override
	public boolean deleteEmployee(Employee emp) {
		
		String msg = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
				
				if (emp != null) {
				
				//session.merge(emp);
				
				session.delete(emp);
				
				session.beginTransaction().commit();

				msg = "Employee " + emp.getFirstname()+" "+emp.getLastname()
						+ " deleted successfully...";

					return true;
				}
			

			} catch (Exception exception) {

				logger.error("Exception occured while reading department data: "
						+ exception.getMessage());

				return false;
			}

		} else {

			logger.info("DB server down..");
		}
		
		logger.info("Employee Deletion status: " + msg);
	
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Employee findEmployeeById(Long id) {
		
		Employee employee = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
				
				Query<?> query = session.createQuery("from Employee WHERE id = :id")
							.setParameter("id", id);

				session.beginTransaction().commit();

				employee =  (Employee) query.uniqueResult();
				
				} catch (Exception exception) {

					logger.error("Exception occured while reading department data: "
							+ exception.getMessage());
					
				}

			} else {

				logger.info("DB server down..");
			}
			
		return employee;
	}

	@Override
	public boolean addRegulation(RegulationDetail regulation) {
		
		String msg = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
				
				if (regulation != null) {

				Long reg = (Long) session.save(regulation);
				
				session.beginTransaction().commit();

				msg = "Regulation " + regulation.getRegulationTitle()
						+ " created successfully...";

					return true;
				}
			

			} catch (Exception exception) {

				logger.error("Exception occured while reading regulation data: "
						+ exception.getMessage());

				return false;
			}

		} else {

			logger.info("DB server down..");
		}
		
		logger.info("Regulation Creation status: " + msg);
		
		return false;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<RegulationDetail> getAllRegulations() {
		
		String msg = null;
		
		List<RegulationDetail> regulations = new ArrayList<RegulationDetail>();
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {

				Criteria cr = session.createCriteria(RegulationDetail.class);
				
				session.beginTransaction().commit();
				
				regulations = (List<RegulationDetail>) cr.list();
					
					msg = "success";

			} catch (Exception exception) {

				logger.error("Exception occured while reading department data: "
						+ exception.getMessage());

			}

		} else {

			logger.info("DB server down..");
		}
		
		logger.info("Regulation Retrieved status: " + msg);
		
		return regulations;
		
	}

	@Override
	public RegulationDetail findRegulationById(Long id) {
	
		RegulationDetail regulation = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
					
				Query<?> query = session.createQuery("from RegulationDetail WHERE "
							+ "id = :id");

				query.setParameter("id", id);

				session.beginTransaction().commit();

				regulation =  (RegulationDetail) query.uniqueResult();
					
				} catch (Exception exception) {

					logger.error("Exception occured while reading Regulation Detail data: "
							+ exception.getMessage());

					
				}

			} else {

				logger.info("DB server down..");
			}
			
		return regulation;
	}

	@Override
	public boolean deleteRegulation(RegulationDetail regulation) {
		
		String msg = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
				
				if (regulation != null) {
					
				session.delete(regulation);
				
				session.beginTransaction().commit();

				msg = "Regulation " + regulation.getRegulationTitle()
						+ " deleted successfully...";

					return true;
				}
			

			} catch (Exception exception) {

				logger.error("Exception occured while reading Regulation data: "
						+ exception.getMessage());

				return false;
			}

		} else {

			logger.info("DB server down..");
		}
		
		logger.info("Regulation Deletion status: " + msg);
		
		return false;
	}

	@Override
	public boolean updateEmployee(Employee employeeUpdated) {

		String msg = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
				
				if (employeeUpdated != null) {
				
				session.update(employeeUpdated);
				
				session.beginTransaction().commit();

				msg = "Employee " + employeeUpdated.getFirstname() + " "+employeeUpdated.getLastname()
						+ " updated successfully...";

					return true;
				}
			

			} catch (Exception exception) {

				logger.error("Exception occured while reading department data: "
						+ exception.getMessage());

				return false;
			}

		} else {

			logger.info("DB server down..");
		}
		
		logger.info("Employee Update status: " + msg);
	
		return false;
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String departmentName, String role) {
		
		List<Employee> employee = new ArrayList<Employee>();
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
					
				Query<?> query = session.createQuery("from Employee WHERE "
							+ "userdeptname = :userdeptname and userrole = :userrole");

				query.setParameter("userdeptname", departmentName);
				
				query.setParameter("userrole", role);

				session.beginTransaction().commit();

				employee =  (List<Employee>) query.list();
					
				} catch (Exception exception) {

					logger.error("Exception occured while reading Employee data: "
							+ exception.getMessage());

					
				}

			} else {

				logger.info("DB server down..");
			}
			
		return employee;
	}

	@Override
	public Long findEmployeeIdByFullName(String firstname, String lastname) {
		
		Long employeeId = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
					
				Query<?> query = session.createQuery("SELECT id  FROM Employee WHERE "
							+ "firstname = :firstname and lastname = :lastname");

				query.setParameter("firstname", firstname);
				
				query.setParameter("lastname", lastname);

				session.beginTransaction().commit();

				employeeId =  (Long) query.uniqueResult();
		
				} catch (Exception exception) {

					logger.error("Exception occured while reading department data: "
							+ exception.getMessage());

					
				}

			} else {

				logger.info("DB server down..");
			}
			
		return employeeId;
	}

	@Override
	public boolean assignNewRegulation(RegulationAnalysis regAnalysis) {
		
		String msg = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
				
				if (regAnalysis != null) {

				Long regulation = (Long) session.save(regAnalysis);
				
				session.beginTransaction().commit();

				msg = "Regulation Analysis " + regulation
						+ " created successfully...";

					return true;
				}
			

			} catch (Exception exception) {

				logger.error("Exception occured while reading Regulation Analysis data: "
						+ exception.getMessage());

				return false;
			}

		} else {

			logger.info("DB server down..");
		}
		
		logger.info("Regulation Analysis Creation status: " + msg);
		
		return false;
	}

	@Override
	public List<Long> findAssignedRegylationsForEmployee() {
	
		List<Long> employeesIdWithAssignedRegulations = new ArrayList<Long>();
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
					
				Query<?> query = session.createQuery("Select employeeId from RegulationAnalysis");

				session.beginTransaction().commit();

				employeesIdWithAssignedRegulations =  (List<Long>) query.list();
					
				} catch (Exception exception) {

					logger.error("Exception occured while reading Regulation Analysis data: "
							+ exception.getMessage());

					
				}

			} else {

				logger.info("DB server down..");
			}
			
		return employeesIdWithAssignedRegulations;
	}

	@Override
	public RegulationAnalysis findRegulationByEmployeeId(Long employeeId) {
		
		RegulationAnalysis regulation = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
					
				Query<?> query = session.createQuery("from RegulationAnalysis WHERE "
							+ "employeeid = :employeeid");

				query.setParameter("employeeid", employeeId);

				session.beginTransaction().commit();

				regulation =  (RegulationAnalysis) query.uniqueResult();
					
				} catch (Exception exception) {

					logger.error("Exception occured while reading Regulation Analysis data: "
							+ exception.getMessage());

					
				}

			} else {

				logger.info("DB server down..");
			}
			
		return regulation;
	}

	@Override
	public boolean saveCommentToRegulation(RegulationAnalysis regAnalysis) {
		
		String msg = null;
		
		Session session = HibernateUtils.getSession();

		if (session != null) {

			try {
				
				if (regAnalysis != null) {
				
					session.update(regAnalysis);
					
					session.beginTransaction().commit();

					msg = "Regulation Analysis Comment" + regAnalysis.getComments()
						+ " added successfully...";

					return true;
				}
			

			} catch (Exception exception) {

				logger.error("Exception occured while reading Regulation Analysis data: "
						+ exception.getMessage());

				return false;
			}

		} else {

			logger.info("DB server down..");
		}
		
		logger.info("Regulation Analysis Insertion Comment status: " + msg);
		
		return false;
	}
	
	
	
}
