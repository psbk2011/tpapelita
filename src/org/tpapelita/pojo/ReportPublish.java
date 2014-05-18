package org.tpapelita.pojo;

// Generated May 18, 2014 1:43:50 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ReportPublish generated by hbm2java
 */
@Entity
@Table(name = "report_publish", catalog = "master_tpapelita")
public class ReportPublish implements java.io.Serializable {

	private int reportId;
	private Date reportDateCreate;
	private Date reportDate;

	public ReportPublish() {
	}

	public ReportPublish(int reportId) {
		this.reportId = reportId;
	}

	public ReportPublish(int reportId, Date reportDateCreate, Date reportDate) {
		this.reportId = reportId;
		this.reportDateCreate = reportDateCreate;
		this.reportDate = reportDate;
	}

	@Id
	@Column(name = "report_id", unique = true, nullable = false)
	public int getReportId() {
		return this.reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "report_date_create", length = 19)
	public Date getReportDateCreate() {
		return this.reportDateCreate;
	}

	public void setReportDateCreate(Date reportDateCreate) {
		this.reportDateCreate = reportDateCreate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "report_date", length = 10)
	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

}
