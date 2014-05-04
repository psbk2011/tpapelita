package org.tpapelita.service;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class Login implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String pass;
	private String page;

	public Login() {

	}

	public Login(String id, String pass) {
		this.id = id;
		this.pass = pass;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String logAdmin() {
		String str = "";
		try {
			if (getId().equalsIgnoreCase("ADMIN0")
					&& getPass().equalsIgnoreCase("123456")) {
				str="Success";
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Login Failed"));
			}
			return str;
		} catch (NullPointerException e) {
			return str;
		}
	}
	
	public String logCS() {
		String str = "";
		try {
			if (getId().equalsIgnoreCase("CS0")
					&& getPass().equalsIgnoreCase("123456")) {
				str="Success";
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Login Failed"));
			}
			return str;
		} catch (NullPointerException e) {
			return str;
		}
	}
	
	public String logInvestor() {
		String str = "";
		try {
			if (getId().equalsIgnoreCase("INV0")
					&& getPass().equalsIgnoreCase("123456")) {
				str="Success";
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Login Failed"));
			}
			return str;
		} catch (NullPointerException e) {
			return str;
		}
	}
}
