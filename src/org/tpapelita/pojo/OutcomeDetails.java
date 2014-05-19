package org.tpapelita.pojo;

// Generated May 19, 2014 9:35:17 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OutcomeDetails generated by hbm2java
 */
@Entity
@Table(name = "outcome_details", catalog = "master_tpapelita")
public class OutcomeDetails implements java.io.Serializable {

	private Integer detailsId;
	private Outcome outcome;
	private String detailsUnitName;
	private Integer detailsUnitPrice;
	private Integer detailsUnitQty;
	private String detailsInfo;

	public OutcomeDetails() {
	}

	public OutcomeDetails(Outcome outcome, String detailsUnitName,
			Integer detailsUnitPrice, Integer detailsUnitQty, String detailsInfo) {
		this.outcome = outcome;
		this.detailsUnitName = detailsUnitName;
		this.detailsUnitPrice = detailsUnitPrice;
		this.detailsUnitQty = detailsUnitQty;
		this.detailsInfo = detailsInfo;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "details_id", unique = true, nullable = false)
	public Integer getDetailsId() {
		return this.detailsId;
	}

	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "outcome_id")
	public Outcome getOutcome() {
		return this.outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
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
