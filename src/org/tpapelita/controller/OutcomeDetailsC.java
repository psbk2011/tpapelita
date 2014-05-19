package org.tpapelita.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.tpapelita.dao.OutcomeDetailsDao;
import org.tpapelita.pojo.Outcome;
import org.tpapelita.pojo.OutcomeDetails;


@ManagedBean
@SessionScoped
public class OutcomeDetailsC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutcomeDetails outDetails;
	private List<OutcomeDetailsC> list;
	private Outcome outcome;
	private int totalOutDetails;
	private int subTotalOutDetails;

	/*
	 * Support Method
	 */
	public OutcomeDetailsC() {
		this.outDetails = new OutcomeDetails();
		this.outcome = new Outcome();
	}
	
	public int getTotalOutDetails() {
		return totalOutDetails;
	}

	public void setTotalOutDetails(int totalOutDetails) {
		this.totalOutDetails = totalOutDetails;
	}

	public int getSubTotalOutDetails() {
		return subTotalOutDetails;
	}

	public void setSubTotalOutDetails(int subTotalOutDetails) {
		this.subTotalOutDetails = subTotalOutDetails;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}
	
	public List<OutcomeDetailsC> getList() {
		return list;
	}

	public void setList(List<OutcomeDetailsC> list) {
		this.list = list;
	}

	public OutcomeDetails getOutDetails() {
		return outDetails;
	}

	public void setOutDetails(OutcomeDetails outDetails) {
		this.outDetails = outDetails;
	}

	public void clear() {
		setOutDetails(new OutcomeDetails());
	}

	/*
	 * CRUD Method
	 */
	public void create() {
		FacesContext context = FacesContext.getCurrentInstance();
		OutcomeDetailsDao dao = new OutcomeDetailsDao();
		getOutDetails().setOutcome(getOutcome());
		String msg = dao.create(getOutDetails());
		System.out.println(msg);
		clear();
		context.addMessage(null, new FacesMessage(msg));
		RequestContext.getCurrentInstance().execute("addOutDetails.hide()");
	}

	public List<OutcomeDetailsC> getRead() {
		List<OutcomeDetailsC> list = new ArrayList<OutcomeDetailsC>();
		try {
			OutcomeDetailsDao dao = new OutcomeDetailsDao();
			List<OutcomeDetails> outDetails = dao.getRead();
			for (int i = 0; i < outDetails.size(); i++) {
				OutcomeDetailsC odc = new OutcomeDetailsC();
				odc.setOutDetails(outDetails.get(i));
				list.add(odc);
			}
			return list;
		} catch (NullPointerException e) {
			return new ArrayList<OutcomeDetailsC>();
		}
	}
	
	public List<OutcomeDetailsC> getReadById() {
		List<OutcomeDetailsC> list = new ArrayList<OutcomeDetailsC>();
		try {
			OutcomeDetailsDao dao = new OutcomeDetailsDao();
			List<OutcomeDetails> outDetails = dao.getReadById(getOutcome().getOutcomeId());
			for (int i = 0; i < outDetails.size(); i++) {
				OutcomeDetailsC odc = new OutcomeDetailsC();
				odc.setOutDetails(outDetails.get(i));
				list.add(odc);
			}
			return list;
		} catch (NullPointerException e) {
			return new ArrayList<OutcomeDetailsC>();
		}
	}

	public void update() {
		OutcomeDetailsDao dao = new OutcomeDetailsDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.update(getOutDetails());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		clear();
		RequestContext.getCurrentInstance().execute("editOutDetails.hide()");
	}

	public void delete() {
		OutcomeDetailsDao dao = new OutcomeDetailsDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.delete(getOutDetails());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		clear();
	}
}
