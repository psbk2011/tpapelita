package org.tpa.pelita.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.tpapelita.dao.InvestmentDao;
import org.tpapelita.pojo.Investment;
import org.tpapelita.pojo.Investor;

@ManagedBean
@SessionScoped
@ViewScoped
@ApplicationScoped
public class InvestmentC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Investment inves;
	private List<InvestmentC> list;
	private int transType;

	/*
	 * Support Method
	 */
	public InvestmentC() {
		this.inves = new Investment();
	}

	public String getInvesDateModif() {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("dd MMM, yyyy HH:mm:ss");
			return sf.format(getInves().getInvesDate()).toString();
		} catch (NullPointerException e) {
			return "0000-00-00";
		}
	}

	public int getTransType() {
		return transType;
	}

	public void setTransType(int transType) {
		this.transType = transType;
	}

	public List<InvestmentC> getList() {
		return list;
	}

	public void setList(List<InvestmentC> list) {
		this.list = list;
	}

	public Investment getInves() {
		return inves;
	}

	public void setInves(Investment inves) {
		this.inves = inves;
	}

	public String getGenInvesId() {
		return "TR" + (getRead().size() + 1);
	}

	public String getInvesIdModif() {
		try {
			return ("TR" + getInves().getInvesId());
		} catch (NullPointerException e) {
			return "TR0";
		}
	}

	public Map<String, String> getInvesTypeOption() {
		Map<String, String> invesType = new HashMap<String, String>();
		invesType.put("Zakat", "Zakat");
		invesType.put("Infak", "Infak");
		invesType.put("Sodaqoh", "Sodaqoh");
		return invesType;
	}

	public void clear() {
		setInves(new Investment());

	}
	/*
	 * CRUD Method
	 */
	public void create() {
		InvestmentDao dao = new InvestmentDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = "";
		if (getTransType() == 0) {
			getInves().setInvesDate(new Date());
			Investor investor = new Investor();
			investor.setInvestorId(1);
			getInves().setInvestor(investor);
			getInves().setInvesTransType(false);
			msg = dao.create(getInves());
		} else {
			getInves().setInvesDate(new Date());
			getInves().setInvesTransType(true);
			msg = dao.create(getInves());
		}
		System.out.println(msg);
		clear();
		context.addMessage(null, new FacesMessage(msg));
	}

	public List<InvestmentC> getRead() {
		List<InvestmentC> list = new ArrayList<InvestmentC>();
		try {
			InvestmentDao dao = new InvestmentDao();
			List<Investment> inves = dao.getRead();
			for (int i = 0; i < inves.size(); i++) {
				InvestmentC ic = new InvestmentC();
				ic.setInves(inves.get(i));
				list.add(ic);
			}
			return list;
		} catch (NullPointerException e) {
			return new ArrayList<InvestmentC>();
		}
	}

	public List<InvestmentC> getReadByIncome() {
		List<InvestmentC> list = new ArrayList<InvestmentC>();
		try {
			InvestmentDao dao = new InvestmentDao();
			List<Investment> inves = dao.getReadBy(1);
			for (int i = 0; i < inves.size(); i++) {
				InvestmentC ic = new InvestmentC();
				ic.setInves(inves.get(i));
				list.add(ic);
			}
			return list;
		} catch (NullPointerException e) {
			return new ArrayList<InvestmentC>();
		}
	}

	public List<InvestmentC> getReadByOutCome() {
		List<InvestmentC> list = new ArrayList<InvestmentC>();
		try {
			InvestmentDao dao = new InvestmentDao();
			List<Investment> inves = dao.getReadBy(2);
			for (int i = 0; i < inves.size(); i++) {
				InvestmentC ic = new InvestmentC();
				ic.setInves(inves.get(i));
				list.add(ic);
			}
			return list;
		} catch (NullPointerException e) {
			return new ArrayList<InvestmentC>();
		}
	}

	public void update() {
		InvestmentDao dao = new InvestmentDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.update(getInves());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		clear();
	}

	public void delete() {
		InvestmentDao dao = new InvestmentDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.delete(getInves());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		clear();
	}
}
