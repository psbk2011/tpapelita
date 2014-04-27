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





import org.tpapelita.dao.AdministratorDao;
import org.tpapelita.pojo.Administrator;


@ManagedBean
@SessionScoped
@ViewScoped
@ApplicationScoped
public class AdministratorC implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Administrator admin;
	private List<AdministratorC> list;
	
	
	/*
	 * Support Method
	 */
	public AdministratorC() {
		this.admin = new Administrator();
	}
	
	public List<AdministratorC> getList() {
		return list;
	}

	public void setList(List<AdministratorC> list) {
		this.list = list;
	}

	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}
	
	public void clear() {
		setAdmin(new Administrator());
		
	}

	/*
	 * CRUD Method
	 */
	public void create() {
		AdministratorDao dao = new AdministratorDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.create(getAdmin());
		System.out.println(msg);
		clear();
		context.addMessage(null, new FacesMessage(msg));
	}

	public List<AdministratorC> getRead() {
		List<AdministratorC> list = new ArrayList<AdministratorC>();
		try {
			AdministratorDao dao = new AdministratorDao();
			List<Administrator> admin= dao.getRead(); 
			for (int i = 0; i < admin.size(); i++) {
				AdministratorC ac = new AdministratorC();
				ac.setAdmin(admin.get(i));
				list.add(ac);
			}
			return list;
		} catch (NullPointerException e) {
			return new ArrayList<AdministratorC>();
		}
	}
	
	public void update() {
		AdministratorDao dao = new AdministratorDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.update(getAdmin());
		System.out.println(msg);
        context.addMessage(null, new FacesMessage(msg));
        clear();
	}
	public void delete() {
		AdministratorDao dao = new AdministratorDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.delete(getAdmin());
		System.out.println(msg);
        context.addMessage(null, new FacesMessage(msg));
        clear();
	}
}
