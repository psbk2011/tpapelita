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

import org.tpapelita.dao.InvestmentDao;
import org.tpapelita.dao.InvestorDao;
import org.tpapelita.pojo.Investment;
import org.tpapelita.pojo.Investor;

@ManagedBean
@SessionScoped
public class InvestorC implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Investor investor;
	private List<InvestorC> list;
	private int autoInvestorId;
	
	/*
	 * Support Method
	 */
	public InvestorC() {
		this.investor = new Investor();
	}
	
	public int getAutoInvestorId() {
		return autoInvestorId;
	}

	public void setAutoInvestorId(int autoInvestorId) {
		this.autoInvestorId = autoInvestorId;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public String getGenInvestorId() {
		int temp = 1;
		String uniqueChar = "INV";
		String id = uniqueChar+temp;
		setAutoInvestorId(temp);
		InvestorDao dao = new InvestorDao();
		List<Investor> investor= dao.getReadLastId();  
		if ( investor.size() != 0) {
			temp = investor.get(0).getInvestorId()+temp;
			id = uniqueChar+temp;
			setAutoInvestorId(temp);
		}
		return id;
	}

	public String getInvestorStatusModif() {
		String status = "Aktif";
		try {
			if (getInvestor().getInvestorStatus() == true) {
				status = "Nonaktif";
			}
			return status;
		} catch (NullPointerException e) {
			return status;
		}
	}

	public List<InvestorC> getList() {
		return list;
	}

	public void setList(List<InvestorC> list) {
		this.list = list;
	}

	public String getInvestorIdModif() {
		return "INV" + getInvestor().getInvestorId();
	}

	public void clear() {
		setInvestor(new Investor());
	}

	public String getInvestorRegistrationModif() {
		try {
			Date date = getInvestor().getInvestorRegistration();
			SimpleDateFormat sf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
			return sf.format(date);
		} catch (NullPointerException e) {
			return "0000-00-00";
		}
		
	}

	// CRUD METHOD

	public void create() {
		InvestorDao dao = new InvestorDao();
		getInvestor().setInvestorId(getAutoInvestorId());
		getInvestor().setInvestorPass(getInvestor().getInvestorPhone());
		getInvestor().setInvestorLastPass(getInvestor().getInvestorPhone());
		getInvestor().setInvestorRegistration(new Date());
		getInvestor().setInvestorStatus(false);
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.create(getInvestor());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		clear();
	}

	public List<InvestorC> getRead() {
		List<InvestorC> list = new ArrayList<InvestorC>();
		try {
			InvestorDao dao = new InvestorDao();
			List<Investor> investor= dao.getRead(); 
			for (int i = 0; i < investor.size(); i++) {
				InvestorC ic = new InvestorC();
				ic.setInvestor(investor.get(i));
				list.add(ic);
			}
			return list;
		} catch (NullPointerException e) {
			return new ArrayList<InvestorC>();
		}
	}

	public List<Investment> getReadOneToMany() {
		try {
			InvestmentDao dao = new InvestmentDao();
			return dao.getReadManyToOne(getInvestor().getInvestorId());
		} catch (NullPointerException e) {
			return new ArrayList<Investment>();
		}
	}

	public void update() {
		InvestorDao dao = new InvestorDao();
		if ("Nonaktif".equalsIgnoreCase(getInvestorStatusModif())) {
			getInvestor().setInvestorStatus(true);
		} else {
			getInvestor().setInvestorStatus(false);
		}
		getInvestor().setInvestorLastPass(getInvestor().getInvestorPass());
		getInvestor().setInvestorPass(getInvestor().getInvestorPhone());
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.update(getInvestor());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		clear();
	}
	
	public void delete() {
		InvestorDao dao = new InvestorDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.delete(getInvestor());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage("Delete Succesfully"));
		clear();
	}
}
