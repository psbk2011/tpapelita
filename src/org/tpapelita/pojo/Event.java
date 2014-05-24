package org.tpapelita.pojo;

// Generated May 20, 2014 3:44:50 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Event generated by hbm2java
 */
@Entity
@Table(name = "event", catalog = "master_tpapelita", uniqueConstraints = @UniqueConstraint(columnNames = "admin_id"))
public class Event implements java.io.Serializable {

	private Integer eventId;
	private Administrator administrator;
	private Date eventDateCreate;
	private String eventTitle;
	private Date eventTime;
	private String eventText;

	public Event() {
	}

	public Event(Administrator administrator, Date eventDateCreate,
			String eventTitle, Date eventTime, String eventText) {
		this.administrator = administrator;
		this.eventDateCreate = eventDateCreate;
		this.eventTitle = eventTitle;
		this.eventTime = eventTime;
		this.eventText = eventText;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "event_id", unique = true, nullable = false)
	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id", unique = true)
	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "event_date_create", length = 19)
	public Date getEventDateCreate() {
		return this.eventDateCreate;
	}

	public void setEventDateCreate(Date eventDateCreate) {
		this.eventDateCreate = eventDateCreate;
	}

	@Column(name = "event_title", length = 45)
	public String getEventTitle() {
		return this.eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "event_time", length = 19)
	public Date getEventTime() {
		return this.eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	@Column(name = "event_text", length = 65535)
	public String getEventText() {
		return this.eventText;
	}

	public void setEventText(String eventText) {
		this.eventText = eventText;
	}

}
