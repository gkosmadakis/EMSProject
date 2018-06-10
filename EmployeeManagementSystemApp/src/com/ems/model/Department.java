package com.ems.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/*Model Class for Department*/

@Entity()
@Table(name="department")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	@Column(length=50,name="deptname")
	private String deptName;
	@Column(name="headofdept")
	private String headOfDept;

	public Department() {

	}

	public Department(String deptName, String headOfDept) {
		super();
		this.deptName = deptName;
		this.headOfDept = headOfDept;
	}


	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getHeadOfDept() {
		return headOfDept;
	}

	public void setHeadOfDept(String headOfDept) {
		this.headOfDept = headOfDept;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Department [deptName=" + deptName + ", headOfDept=" + headOfDept + "]";
	}




}
