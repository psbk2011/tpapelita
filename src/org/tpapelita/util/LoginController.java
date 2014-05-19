package org.tpapelita.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.tpapelita.dao.AdministratorDao;
import org.tpapelita.dao.InvestorDao;
import org.tpapelita.pojo.Administrator;
import org.tpapelita.pojo.Event;
import org.tpapelita.pojo.Investment;
import org.tpapelita.pojo.Investor;
import org.tpapelita.pojo.Outcome;

@ManagedBean(name = "LoginController")
@SessionScoped
public class LoginController {
	private Administrator admin;
	private AdministratorDao ad;
	private Investor investor;
	private InvestorDao id;
	private boolean isLoggedIn;
	@ManagedProperty(value = "#{navigationBean}")
	private NavigationBean navigationBean;

	public LoginController() {
		admin = new Administrator();
		ad = new AdministratorDao();
	}

	
	public Administrator getAdmin() {
		return admin;
	}


	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}
	
	
	public Investor getInvestor() {
		return investor;
	}


	public void setInvestor(Investor investor) {
		this.investor = investor;
	}


	public NavigationBean getNavigationBean() {
		return navigationBean;
	}

	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}

	public boolean getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public String getAdminJobModif(){
		String job = "Customer Service";
		try {
			if (getAdmin().getAdminId() == 0) {
				job = "Super Admin";
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		return job;
	}

	public void resetLogin() {
		admin.setAdminId(-1);
		admin.setAdminPass("");
	}
	
	public String loginAdmin() {
		String adminUsername = admin.getAdminUsername();
		String adminPass = admin.getAdminPass();
		Administrator adm = new Administrator(-1, "", "", "", false,adminUsername, adminPass, "", false, new HashSet<Investment>(0), new HashSet<Event>(0), new HashSet<Outcome>(0));
		List<Administrator> admin = new ArrayList<Administrator>();
		try {
			admin = ad.cekLogin(adm);
			boolean status = false;
			if (admin.size() > 0) {
				status = true;
			}
			
			if (status) {
				isLoggedIn = true;
				setAdmin(admin.get(0));
				return navigationBean.redirectToIndexAdmin();
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed Login",
								"Id and Password is not valid"));
			}
		} catch (Exception e) {
			System.out.println("Erorr :" + e.getMessage());
		}
		return navigationBean.toLoginAdmin();
	}
	
	public String loginCS() {
		String adminUsername = admin.getAdminUsername();
		String adminPass = admin.getAdminPass();
		Administrator adm = new Administrator(-1, "", "", "", false,adminUsername, adminPass, "", false, new HashSet<Investment>(0), new HashSet<Event>(0), new HashSet<Outcome>(0));
		List<Administrator> admin = new ArrayList<Administrator>();
		try {
			admin = ad.cekLogin(adm);
			boolean status = false;
			if (admin.size() > 0) {
				status = true;
			}
			
			if (status) {
				isLoggedIn = true;
				setAdmin(admin.get(0));
				return navigationBean.redirectToIndexCS();
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed Login",
								"Id and Password is not valid"));
			}
		} catch (Exception e) {
			System.out.println("Erorr :" + e.getMessage());
		}
		return navigationBean.toLoginCS();
	}

	public String loginInvestor() {
		int investorId = investor.getInvestorId();
		String investorPass = investor.getInvestorPass();
		Investor i = new Investor(investorId, "", "", "", false, new Date(), investorPass, "", new HashSet<Investment>());
		try {
			if (id.cekLogin(i)) {
				isLoggedIn = true;
				return navigationBean.redirectToIndexInvestor();
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed Login",
								"Id and Password is not valid"));
			}
		} catch (Exception e) {
			System.out.println("Erorr :" + e.getMessage());
		}
		return navigationBean.toLoginInvestor();
	}
	
	public String doLogoutAdmin() {
		isLoggedIn = false;
		return navigationBean.redirectToLoginAdmin();
	}
	
	public String doLogoutInvestor() {
		isLoggedIn = false;
		return navigationBean.redirectToLoginInvestor();
	}
	
	public String doLogoutCS() {
		isLoggedIn = false;
		return navigationBean.redirectToLoginCS();
	}
}
