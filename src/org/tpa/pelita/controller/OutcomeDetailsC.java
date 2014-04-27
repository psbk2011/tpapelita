package org.tpa.pelita.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.tpapelita.dao.OutcomeDetailsDao;
import org.tpapelita.pojo.OutcomeDetails;


@ManagedBean
@SessionScoped
@ViewScoped
@ApplicationScoped
public class OutcomeDetailsC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutcomeDetails outDetails;
	private List<OutcomeDetailsC> list;
	/*
	 * Support Method
	 */
	public OutcomeDetailsC() {
		this.outDetails = new OutcomeDetails();
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
		OutcomeDetailsDao dao = new OutcomeDetailsDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.create(getOutDetails());
		System.out.println(msg);
		clear();
		context.addMessage(null, new FacesMessage(msg));
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

	public void update() {
		OutcomeDetailsDao dao = new OutcomeDetailsDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.update(getOutDetails());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		clear();
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
