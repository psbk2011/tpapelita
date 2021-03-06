package org.tpapelita.pojo;

// Generated May 26, 2014 1:14:55 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OutcomeDetails generated by hbm2java
 */
@Entity
@Table(name = "outcome_details", catalog = "master_tpapelita")
public class OutcomeDetails implements java.io.Serializable {

	private String detailsId;
	private String detailsUnitName;
	private Integer detailsUnitPrice;
	private Integer detailsUnitQty;
	private String detailsInfo;

	public OutcomeDetails() {
	}

	public OutcomeDetails(String detailsId) {
		this.detailsId = detailsId;
	}

	public OutcomeDetails(String detailsId, String detailsUnitName,
			Integer detailsUnitPrice, Integer detailsUnitQty, String detailsInfo) {
		this.detailsId = detailsId;
		this.detailsUnitName = detailsUnitName;
		this.detailsUnitPrice = detailsUnitPrice;
		this.detailsUnitQty = detailsUnitQty;
		this.detailsInfo = detailsInfo;
	}

	@Id
	@Column(name = "details_id", unique = true, nullable = false, length = 11)
	public String getDetailsId() {
		return this.detailsId;
	}

	public void setDetailsId(String detailsId) {
		this.detailsId = detailsId;
	}

	@Column(name = "details_unit_name", length = 50)
	public String getDetailsUnitName() {
		return this.detailsUnitName;
	}

	public void setDetailsUnitName(String detailsUnitName) {
		this.detailsUnitName = detailsUnitName;
	}

	@Column(name = "details_unit_price")
	public Integer getDetailsUnitPrice() {
		return this.detailsUnitPrice;
	}

	public void setDetailsUnitPrice(Integer detailsUnitPrice) {
		this.detailsUnitPrice = detailsUnitPrice;
	}

	@Column(name = "details_unit_qty")
	public Integer getDetailsUnitQty() {
		return this.detailsUnitQty;
	}

	public void setDetailsUnitQty(Integer detailsUnitQty) {
		this.detailsUnitQty = detailsUnitQty;
	}

	@Column(name = "details_info", length = 65535)
	public String getDetailsInfo() {
		return this.detailsInfo;
	}

	public void setDetailsInfo(String detailsInfo) {
		this.detailsInfo = detailsInfo;
	}

}
