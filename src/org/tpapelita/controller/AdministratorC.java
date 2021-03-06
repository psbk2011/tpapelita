package org.tpapelita.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.tpapelita.dao.AdministratorDao;
import org.tpapelita.pojo.Administrator;
import org.tpapelita.util.Util;

@ManagedBean
@SessionScoped
public class AdministratorC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Administrator admin;
	private List<AdministratorC> list;
	private int autoAdminId;

	/*
	 * Support Method
	 */
	public AdministratorC() {
		this.admin = new Administrator();
	}

	public int getAutoAdminId() {
		return autoAdminId;
	}

	public void setAutoAdminId(int autoAdminId) {
		this.autoAdminId = autoAdminId;
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

	public String getAdminStatusModif() {
		String status = "Aktif";
		try {
			if (getAdmin().getAdminStatus() == true) {
				status = "Nonaktif";
			}
			return status;
		} catch (NullPointerException e) {
			return status;
		}
	}

	public String getAdminIdModif() {
		return "CS" + getAdmin().getAdminId();
	}

	public String getAdminJobModif() {
		String job = "Customer Service";
		if (getAdmin().getAdminJob() == false) {
			job = "Super Admin";
		}
		return job;
	}

	public String getGenAdminId() {
		AdministratorDao dao = new AdministratorDao();
		int temp = 1;
		String uniqueChar = "CS";
		String id = uniqueChar + temp;
		setAutoAdminId(temp);
		List<Administrator> admin = dao.getReadLastId();
		if (admin.size() != 0) {
			temp = admin.get(0).getAdminId() + temp;
			id = uniqueChar + temp;
			setAutoAdminId(temp);
		}
		return id;
	}

	/*
	 * CRUD Method
	 */
	public void create() {
		AdministratorDao dao = new AdministratorDao();
		FacesContext context = FacesContext.getCurrentInstance();
		getAdmin().setAdminId(getAutoAdminId());
		getAdmin().setAdminPass(getAdmin().getAdminPhone());
		getAdmin().setAdminLastPass(getAdmin().getAdminPass());
		getAdmin().setAdminJob(true);
		getAdmin().setAdminStatus(false);
		String msg = dao.create(getAdmin());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		RequestContext.getCurrentInstance().execute("addCS.hide()");
		clear();
	}

	public List<AdministratorC> getRead() {
		List<AdministratorC> list = new ArrayList<AdministratorC>();
		try {
			AdministratorDao dao = new AdministratorDao();
			List<Administrator> admin = dao.getRead();
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
		RequestContext.getCurrentInstance().execute("editCS.hide()");
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

	/**
	 * puput 1-105
	 */

	public String login() {
		AdministratorDao dao = new AdministratorDao();
		boolean result = dao.Read(admin.getAdminId(), admin.getAdminPass());
		if (result) {
			// get Http Session and store username
			HttpSession session = Util.getSession();
			session.setAttribute("id", admin.getAdminPass());

			return "index";
		} else {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Invalid Login!", "Please Try Again!"));
			return "login";
		}
	}

	public String logout() {
		HttpSession session = Util.getSession();
		session.invalidate();
		return "login";
	}
}
