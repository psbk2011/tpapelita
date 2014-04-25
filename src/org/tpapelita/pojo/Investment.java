package org.tpapelita.pojo;

// Generated Apr 19, 2014 10:17:30 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Investment generated by hbm2java
 */
@ManagedBean
@SessionScoped
@Entity
@Table(name = "investment", catalog = "master_tpapelita")
public class Investment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer invesId;
	private Administrator administrator;
	private Investor investor;
	private Date invesDate;
	private Boolean invesTransType;
	private Short invesType;
	private Integer invesTransfer;
	private String invesSenderName;
	private String invesBankName;
	private String invesAccountNo;
	private Set<InvestmentDetails> investmentDetailses = new HashSet<InvestmentDetails>(
			0);

	public Investment() {
	}

	public Investment(Administrator administrator, Investor investor,
			Date invesDate, Boolean invesTransType, Short invesType,
			Integer invesTransfer, String invesSenderName,
			String invesBankName, String invesAccountNo,
			Set<InvestmentDetails> investmentDetailses) {
		this.administrator = administrator;
		this.investor = investor;
		this.invesDate = invesDate;
		this.invesTransType = invesTransType;
		this.invesType = invesType;
		this.invesTransfer = invesTransfer;
		this.invesSenderName = invesSenderName;
		this.invesBankName = invesBankName;
		this.invesAccountNo = invesAccountNo;
		this.investmentDetailses = investmentDetailses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "inves_id", unique = true, nullable = false)
	public Integer getInvesId() {
		return this.invesId;
	}

	public void setInvesId(Integer invesId) {
		this.invesId = invesId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "investor_id")
	public Investor getInvestor() {
		return this.investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "inves_date", length = 19)
	public Date getInvesDate() {
		return this.invesDate;
	}

	public void setInvesDate(Date invesDate) {
		this.invesDate = invesDate;
	}

	@Column(name = "inves_trans_type")
	public Boolean getInvesTransType() {
		return this.invesTransType;
	}

	public void setInvesTransType(Boolean invesTransType) {
		this.invesTransType = invesTransType;
	}

	@Column(name = "inves_type")
	public Short getInvesType() {
		return this.invesType;
	}

	public void setInvesType(Short invesType) {
		this.invesType = invesType;
	}

	@Column(name = "inves_transfer")
	public Integer getInvesTransfer() {
		return this.invesTransfer;
	}

	public void setInvesTransfer(Integer invesTransfer) {
		this.invesTransfer = invesTransfer;
	}

	@Column(name = "inves_sender_name", length = 45)
	public String getInvesSenderName() {
		return this.invesSenderName;
	}

	public void setInvesSenderName(String invesSenderName) {
		this.invesSenderName = invesSenderName;
	}

	@Column(name = "inves_bank_name", length = 45)
	public String getInvesBankName() {
		return this.invesBankName;
	}

	public void setInvesBankName(String invesBankName) {
		this.invesBankName = invesBankName;
	}

	@Column(name = "inves_account_no", length = 18)
	public String getInvesAccountNo() {
		return this.invesAccountNo;
	}

	public void setInvesAccountNo(String invesAccountNo) {
		this.invesAccountNo = invesAccountNo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "investment")
	public Set<InvestmentDetails> getInvestmentDetailses() {
		return this.investmentDetailses;
	}

	public void setInvestmentDetailses(
			Set<InvestmentDetails> investmentDetailses) {
		this.investmentDetailses = investmentDetailses;
	}
}
