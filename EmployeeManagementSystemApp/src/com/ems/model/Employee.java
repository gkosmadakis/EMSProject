package com.ems.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ems.util.HibernateUtils;

/*Model Class for Employee*/
@Entity()
@Table(name="Employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	@Column(length=30,name="userfirstname")
	private String firstname;
	@Column(length=30,name="userlastname")
	private String lastname;
	@Column(length=50,name="userdeptname")
	private String department;
	@Column(length=30,name="useremail")
	private String email;
	@Column(length=30,name="username")
	private String username;
	@Column(length=30,name="userpassword")
	private String password;
	@Column(length=30,name="userrole")
	private String role;
	@Column(length=30, name="dateofbirth")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	public Employee() {
		
	}
	
	public Employee(Long id, String firstname, String lastname, String department, String email, String username, String password, String role, Date dateOfBirth) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.department = department;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.dateOfBirth = dateOfBirth;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		
		return username;
	}
	
	public void setUsername(String username) {
		
		this.username = username;
	}
	
	public String getEmail() {
		
		return email;
	}
	
	public void setEmail(String email) {
		
		this.email = email;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", department="
				+ department + ", email=" + email + ", username=" + username + ", password=" + password + ", role="
				+ role + ", dateOfBirth=" + dateOfBirth + "]";
	}

	public void insertUserIntoDB() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		session.beginTransaction();
		
		  //Add new Employee object
		this.setId(id);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setDepartment(department);
        this.setEmail(email);
        this.setUsername(username);
        this.setPassword(password);
        this.setRole(role);
        this.setDateOfBirth(dateOfBirth);
         
        //Save the employee in database
        session.save(this);
 
        //Commit the transaction
        session.getTransaction().commit();
        
	
	}
	
}
