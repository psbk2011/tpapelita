package org.tpapelita.util;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1520318172495977648L;

	public String toLoginAdmin() {
		return "/jakatingkir/login.jsf";
	}
	public String redirectToLoginAdmin(){
		return "/jakatingkir/login.jsf?faces-redirect=true";
	}
	public String indexAdmin() {
		return "/jakatingkir/menu/index.jsf";
	}
	public String redirectToIndexAdmin(){
		return "/jakatingkir/menu/index.jsf?faces-redirect=true";
	}
	public String toLoginCS() {
		return "/cs/login.jsf";
	}
	public String redirectToLoginCS(){
		return "/cs/login.jsf?faces-redirect=true";
	}
	public String indexCS() {
		return "/cs/menu/index.jsf";
	}
	public String redirectToIndexCS(){
		return "/cs/menu/index.jsf?faces-redirect=true";
	}
	public String toLoginInvestor() {
		return "/investor/login.jsf";
	}
	public String redirectToLoginInvestor(){
		return "/investor/login.jsf?faces-redirect=true";
	}
	public String indexInvestor() {
		return "/investor/menu/index.jsf";
	}
	public String redirectToIndexInvestor(){
		return "/investor/menu/index.jsf?faces-redirect=true";
	}
}
