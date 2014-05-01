package org.tpa.pelita.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.tpapelita.dao.OutcomeDao;
import org.tpapelita.pojo.Administrator;
import org.tpapelita.pojo.Outcome;

@ManagedBean
@SessionScoped
public class OutcomeC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Outcome outcome;
	private List<OutcomeC> list;
	private int totalOutcomeDetails;
	private String outcomeResponsibility;
	private int autoOutcomeId;
	/*
	 * Support Method
	 */
	public OutcomeC() {
		this.outcome = new Outcome();
	}
	
	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	public int getAutoOutcomeId() {
		return autoOutcomeId;
	}

	public void setAutoOutcomeId(int autoOutcomeId) {
		this.autoOutcomeId = autoOutcomeId;
	}

	public String getOutcomeResponsibility() {
		return outcomeResponsibility;
	}
	
	public void setOutcomeResponsibility(String outcomeResponsibility) {
		this.outcomeResponsibility = outcomeResponsibility;
	}

	public int getTotalOutcomeDetails() {
		return totalOutcomeDetails;
	}

	public void setTotalOutcomeDetails(int totalOutcomeDetails) {
		this.totalOutcomeDetails = totalOutcomeDetails;
	}

	public String getOutcomeDateModif() {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("dd MMM, yyyy HH:mm:ss");
			return sf.format(getOutcome().getOutcomeDate()).toString();
		} catch (NullPointerException e) {
			return "0000-00-00";
		}
	}

	public List<OutcomeC> getList() {
		return list;
	}

	public void setList(List<OutcomeC> list) {
		this.list = list;
	}

	public String getGenOutcomeId() {
		int temp = 1;
		String uniqueChar = "OUTTR";
		String id = uniqueChar+temp;
		setAutoOutcomeId(temp);
		List<Outcome> outcome = getReadLastId(); 
		if ( outcome.size() != 0) {
			temp = outcome.get(0).getOutcomeId()+temp;
			id = uniqueChar+temp;
			setAutoOutcomeId(temp);
		}
		return id;
	}

	public String getOutcomeIdModif() {
		try {
			return ("OUTTR" + getOutcome().getOutcomeId());
		} catch (NullPointerException e) {
			return "OUTTR0";
		}
	}
	
	public void clear() {
		setOutcome(new Outcome());

	}
	/*
	 * CRUD Method
	 */
	public void create() {
		System.out.println("Masuk Create");
		OutcomeDao dao = new OutcomeDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = "";
		getGenOutcomeId();
		getOutcome().setOutcomeId(getAutoOutcomeId());
		getOutcome().setOutcomeDate(new Date());
		Administrator admin = new Administrator();
		admin.setAdminId(0);
		getOutcome().setAdministrator(admin);
		msg = dao.create(getOutcome());
		System.out.println(msg);
		clear();
		context.addMessage(null, new FacesMessage(msg));
	}

	public List<OutcomeC> getRead() {
		List<OutcomeC> list = new ArrayList<OutcomeC>();
		try {
			OutcomeDao dao = new OutcomeDao();
			List<Outcome> outcome = dao.getRead();
			for (int i = 0; i < outcome.size(); i++) {
				OutcomeC oc = new OutcomeC();
				oc.setOutcome(outcome.get(i));
				list.add(oc);
			}
			return list;
		} catch (NullPointerException e) {
			return new ArrayList<OutcomeC>();
		}
	}

	public List<Outcome> getReadLastId() {
		try {
			OutcomeDao dao = new OutcomeDao();
			List<Outcome> outcome= dao.getReadLastId(); 
			return outcome;
		} catch (NullPointerException e) {
			return new ArrayList<Outcome>();
		}
	}

	public void delete() {
		OutcomeDao dao = new OutcomeDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.delete(getOutcome());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		clear();
	}
}
