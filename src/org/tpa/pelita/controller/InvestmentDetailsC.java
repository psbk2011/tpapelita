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

import org.tpapelita.dao.InvestmentDetailsDao;
import org.tpapelita.pojo.InvestmentDetails;


@ManagedBean
@SessionScoped
@ViewScoped
@ApplicationScoped
public class InvestmentDetailsC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InvestmentDetails invesDetails;
	private List<InvestmentDetailsC> list;
	/*
	 * Support Method
	 */
	public InvestmentDetailsC() {
		this.invesDetails = new InvestmentDetails();
	}

	public List<InvestmentDetailsC> getList() {
		return list;
	}

	public void setList(List<InvestmentDetailsC> list) {
		this.list = list;
	}

	public InvestmentDetails getInvesDetails() {
		return invesDetails;
	}

	public void setInvesDetails(InvestmentDetails invesDetails) {
		this.invesDetails = invesDetails;
	}

	public void clear() {
		setInvesDetails(new InvestmentDetails());
	}

	/*
	 * CRUD Method
	 */
	public void create() {
		InvestmentDetailsDao dao = new InvestmentDetailsDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.create(getInvesDetails());
		System.out.println(msg);
		clear();
		context.addMessage(null, new FacesMessage(msg));
	}

	public List<InvestmentDetailsC> getRead() {
		List<InvestmentDetailsC> list = new ArrayList<InvestmentDetailsC>();
		try {
			InvestmentDetailsDao dao = new InvestmentDetailsDao();
			List<InvestmentDetails> invesDetails = dao.getRead();
			for (int i = 0; i < invesDetails.size(); i++) {
				InvestmentDetailsC idc = new InvestmentDetailsC();
				idc.setInvesDetails(invesDetails.get(i));
				list.add(idc);
			}
			return list;
		} catch (NullPointerException e) {
			return new ArrayList<InvestmentDetailsC>();
		}
	}

	public void update() {
		InvestmentDetailsDao dao = new InvestmentDetailsDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.update(getInvesDetails());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		clear();
	}

	public void delete() {
		InvestmentDetailsDao dao = new InvestmentDetailsDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.delete(getInvesDetails());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		clear();
	}
}
