package org.tpapelita.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.tpapelita.dao.SupportCenterDao;
import org.tpapelita.pojo.SupportCenter;

@ManagedBean
@SessionScoped
public class SupportCenterC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SupportCenter sc;
	private List<SupportCenterC> list;


	/*
	 * Support Method
	 */
	public SupportCenterC() {
		this.sc = new SupportCenter();
	}

	public SupportCenter getSc() {
		return sc;
	}

	public void setSc(SupportCenter sc) {
		this.sc = sc;
	}

	public String getSupportCenterStatusModif() {
		String status = "Aktif";
		try {
			if (getSc().getSupportStatus() == 0) {
				status = "Nonaktif";
			}
			return status;
		} catch (NullPointerException e) {
			return status;
		}
	}

	public List<SupportCenterC> getList() {
		return list;
	}

	public void setList(List<SupportCenterC> list) {
		this.list = list;
	}

	public void clear() {
		setSc(new SupportCenter());
	}

	public String getSupportDateModif() {
		try {
			Date date = getSc().getSupportDate();
			SimpleDateFormat sf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
			return sf.format(date);
		} catch (NullPointerException e) {
			return "0000-00-00";
		}

	}

	// CRUD METHOD

	public void create() {
		SupportCenterDao dao = new SupportCenterDao();
		getSc().setSupportDate(new Date());
		getSc().setSupportStatus(0);
		getSc().setSupportShow(false);
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.create(getSc());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		clear();
		RequestContext.getCurrentInstance().execute("suppoertDialog.hide()");
	}

	public List<SupportCenterC> getRead() {
		List<SupportCenterC> list = new ArrayList<SupportCenterC>();
		try {
			SupportCenterDao dao = new SupportCenterDao();
			List<SupportCenter> lsc = dao.getRead();
			for (int i = 0; i < lsc.size(); i++) {
				SupportCenterC sc = new SupportCenterC();
				sc.setSc(lsc.get(i));
				list.add(sc);
			}
			return list;
		} catch (NullPointerException e) {
			return new ArrayList<SupportCenterC>();
		}
	}

	public void update() {
		SupportCenterDao dao = new SupportCenterDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.update(getSc());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		RequestContext.getCurrentInstance().execute("editInvestor.hide()");
		clear();
	}

	public void delete() {
		SupportCenterDao dao = new SupportCenterDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.delete(getSc());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage("Delete Succesfully"));
		clear();
	}
}
