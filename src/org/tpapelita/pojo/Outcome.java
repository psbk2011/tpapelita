package org.tpapelita.pojo;

// Generated May 20, 2014 3:44:50 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Outcome generated by hbm2java
 */
@Entity
@Table(name = "outcome", catalog = "master_tpapelita")
public class Outcome implements java.io.Serializable {

	private int outcomeId;
	private Administrator administrator;
	private Date outcomeDate;

	public Outcome() {
	}

	public Outcome(int outcomeId) {
		this.outcomeId = outcomeId;
	}

	public Outcome(int outcomeId, Administrator administrator, Date outcomeDate) {
		this.outcomeId = outcomeId;
		this.administrator = administrator;
		this.outcomeDate = outcomeDate;
	}

	@Id
	@Column(name = "outcome_id", unique = true, nullable = false)
	public int getOutcomeId() {
		return this.outcomeId;
	}

	public void setOutcomeId(int outcomeId) {
		this.outcomeId = outcomeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "outcome_date", length = 19)
	public Date getOutcomeDate() {
		return this.outcomeDate;
	}

	public void setOutcomeDate(Date outcomeDate) {
		this.outcomeDate = outcomeDate;
	}

}
