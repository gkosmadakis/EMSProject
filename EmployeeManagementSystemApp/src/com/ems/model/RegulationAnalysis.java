package com.ems.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*Model Class for Regulation Analysis*/
@Entity()
@Table(name="regulationanalysis")
public class RegulationAnalysis {
	
	@Id
	@Column(length=10,name="reganalysisid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long regAnalysisId;
	@Column(length=50,name="regulationdate")
	private Date regulationDate;
	@Column(length=10,name="employeeid")
	private Long employeeId;
	
	@Column(length=200,name="regulationdetails")
	private String regulationDetails;
	@Column(length=50,name="deptHeadName")
	private String deptHeadName;
	@Column(length=50,name="regulationStatus")
	private String regulationStatus;
	@Column(length=200, name="comments")
	private String comments;
	
	public RegulationAnalysis() {
		
	}
	
	public RegulationAnalysis(Long regAnalysisId,Date regulationDate,Long employeeId, String deptHeadName, String regulationStatus,String comments) {
		super();
		this.regAnalysisId = regAnalysisId;
		this.regulationDate = regulationDate;
		this.employeeId = employeeId;
		this.deptHeadName = deptHeadName;
		this.regulationStatus = regulationStatus;
		this.comments = comments;
	}


	public Long getRegAnalysisId() {
		return regAnalysisId;
	}


	public void setRegAnalysisId(Long regAnalysisId) {
		this.regAnalysisId = regAnalysisId;
	}


	public Date getRegulationDate() {
		return regulationDate;
	}


	public void setRegulationDate(Date regulationDate) {
		this.regulationDate = regulationDate;
	}


	public Long getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}


	public String getRegulationDetails() {
		return regulationDetails;
	}


	public void setRegulationDetails(String regulationsDetails) {
		this.regulationDetails = regulationsDetails;
	}


	public String getDeptHeadName() {
		return deptHeadName;
	}


	public void setDeptHeadName(String deptHeadName) {
		this.deptHeadName = deptHeadName;
	}


	public String getRegulationStatus() {
		return regulationStatus;
	}


	public void setRegulationStatus(String regulationStatus) {
		this.regulationStatus = regulationStatus;
	}
	

	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	@Override
	public String toString() {
		return "RegulationAnalysis [regAnalysisId=" + regAnalysisId + ", regulationDate=" + regulationDate
				+ ", employeeId=" + employeeId + ", regulationDetails=" + regulationDetails + ", deptHeadName="
				+ deptHeadName + ", regulationStatus=" + regulationStatus + ", comments=" + comments + "]";
	}

	
	
}
