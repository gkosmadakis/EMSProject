package com.ems.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*Model Class for Regulation Detail*/
@Entity()
@Table(name="regulationdetail")
public class RegulationDetail {

	
	@Id
	@Column(length=10,name="regid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long regulationId;
	@Column(length=100,name="regulationdetail")
	private String regulationDetail;
	@Column(length=50,name="createdate")
	private Date createDate;
	@Column(length=50,name="deptheadname")
	private String deptHeadName;
	@Column(length=50,name="deptname")
	private String deptName;
	@Column(length=50,name="regulationtitle")
	private String regulationTitle;
		
	public RegulationDetail() {
		
	}
		
	public RegulationDetail(Long regulationId, String regulationDetail, Date createDate, String deptHeadName, String deptName,String regulationTitle) {
		super();
		this.regulationId = regulationId;
		this.regulationDetail = regulationDetail;
		this.createDate = createDate;
		this.deptHeadName = deptHeadName;
		this.deptName = deptName;
		this.regulationTitle = regulationTitle;
	}


	public Long getRegulationId() {
		return regulationId;
	}


	public void setRegulationId(Long regulationId) {
		this.regulationId = regulationId;
	}

	public String getRegulationDetail() {
		return regulationDetail;
	}


	public void setRegulationDetail(String regulationsDetail) {
		this.regulationDetail = regulationsDetail;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getDeptHeadName() {
		return deptHeadName;
	}


	public void setDeptHeadName(String deptHeadName) {
		this.deptHeadName = deptHeadName;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public String getRegulationTitle() {
		return regulationTitle;
	}


	public void setRegulationTitle(String regulationTitle) {
		this.regulationTitle = regulationTitle;
	}

	@Override
	public String toString() {
		return "RegulationDetail [regulationId=" + regulationId + ", regulationDetail=" + regulationDetail
				+ ", createDate=" + createDate + ", deptHeadName=" + deptHeadName + ", deptName=" + deptName
				+ ", regulationTitle=" + regulationTitle + "]";
	}



	
}
