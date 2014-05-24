package org.tpapelita.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;





import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.tpapelita.dao.InvestmentDao;
import org.tpapelita.dao.InvestorDao;
import org.tpapelita.pojo.Investment;
import org.tpapelita.pojo.Investor;

@ManagedBean
@SessionScoped
public class InvestmentC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Investment inves;
	private List<InvestmentC> list;
	private int totalInvesDetails;
	private String invesResponsibility;
	private Investor investor;
	private long totalInves;
	

	/*
	 * Support Method
	 */
	public InvestmentC() {
		this.inves = new Investment();
		this.investor = new Investor();
	}
	
	
	public Investor getInvestor() {
		return investor;
	}


	public void setInvestor(Investor investor) {
		this.investor = investor;
	}


	public Investment getInves() {
		return inves;
	}

	public void setInves(Investment inves) {
		this.inves = inves;
	}

	public String getInvesResponsibility() {
		return invesResponsibility;
	}
	
	public void setInvesResponsibility(String invesResponsibility) {
		this.invesResponsibility = invesResponsibility;
	}

	public int getTotalInvesDetails() {
		return totalInvesDetails;
	}
	
	

	public void setTotalInves(long totalInves) {
		this.totalInves = totalInves;
	}


	public void setTotalInvesDetails(int totalInvesDetails) {
		this.totalInvesDetails = totalInvesDetails;
	}

	public String getInvesDateModif() {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("dd MMM, yyyy HH:mm:ss");
			return sf.format(getInves().getInvesDate()).toString();
		} catch (NullPointerException e) {
			return "0000-00-00";
		}
	}
	
	public long getTotalInves() {
		InvestmentDao dao = new InvestmentDao();
		List<Investment> inves = dao.getRead();
		totalInves = 0;
		for (int i = 0; i < inves.size(); i++) {
			totalInves += (inves.get(i).getInvesTransfer());
		}
		return totalInves;
	}

	public List<InvestmentC> getList() {
		return list;
	}

	public void setList(List<InvestmentC> list) {
		this.list = list;
	}
	
	@SuppressWarnings("deprecation")
	public String getGenInvesId() {
		Date d = new Date();
		InvestmentDao invesDao = new InvestmentDao();
		int year = (d.getYear()+1900);
		int month = (1+d.getMonth());
		int date = d.getDate();
		long temp = (year*10000)+(month*100)+date;
		long count = invesDao.countRowBy((temp));
		long id = count;
		return String.valueOf(id);			
	}
	
	public String getInvestorIdModif() {
		String id = "INV0";
		try {
			id = "INV"+getInves().getInvestor().getInvestorId();
			return id;
		} catch (NullPointerException e) {
			return id;
		}
		
	}
	
	public String getInvesStatusModif() {
		try {
			String status = "";
			if (getInves().getInvesStatus()==1) {
				status = "Recieved";
			} else if(getInves().getInvesStatus()==2) {
				status = "Not Valid";
			} else {
				status = "Pending";
			}
			return status;
		} catch (Exception e) {
			return "Pending";
		}
		
	}
	
	public Map<String, Integer> getInvestorList(){
		Map<String,Integer> investor = new HashMap<String, Integer>();
		List<Investor> list = new ArrayList<Investor>();
		InvestorDao id = new InvestorDao();
		try {
			list = id.getReadBy();
			int i = 0;
			while (i < list.size()) {
				investor.put(("INV"+String.valueOf(list.get(i).getInvestorId())), list.get(i).getInvestorId());
				i++;
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
        return investor;
	}
		
	public String getInvesAccountNoModif(){
		String acc = "NO ACCOUNT";
		if (!getInves().getInvesAccountNo().isEmpty()) {
			acc = getInves().getInvesAccountNo();
		}
		return acc;
	}
	
	public void clear() {
		setInves(new Investment());
		setInvestor(new Investor());
	}
	/*
	 * CRUD Method
	 */
	public void create() {
		FacesContext context = FacesContext.getCurrentInstance();
		InvestmentDao dao = new InvestmentDao();
		String msg = "";
		getInves().setInvesId(getGenInvesId());
		getInves().setInvesDate(new Date());
		getInves().setInvesStatus(3);
		getInves().setInvestor(getInvestor());
		if (!getInves().getInvesBankName().equalsIgnoreCase("NO BANK") && !getInves().getInvesAccountNo().isEmpty()) {
			msg = dao.create(getInves());
			System.out.println(msg);
			clear();
			RequestContext.getCurrentInstance().execute("addInvestment.hide()");
			context.addMessage(null, new FacesMessage(msg));
		} else if (getInves().getInvesBankName().equalsIgnoreCase("NO BANK")) {
			getInves().setInvesAccountNo("");
			msg = dao.create(getInves());
			System.out.println(msg);
			clear();
			RequestContext.getCurrentInstance().execute("addInvestment.hide()");
			context.addMessage(null, new FacesMessage(msg));
		} else {
			context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_WARN, "Please Insert Account No", "Please Insert Account No"));
		}
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

	public List<Investment> getReadLastId() {
		try {
			InvestmentDao dao = new InvestmentDao();
			List<Investment> inves= dao.getReadLastId(); 
			return inves;
		} catch (NullPointerException e) {
			return new ArrayList<Investment>();
		}
	}

	public void update() {
		InvestmentDao dao = new InvestmentDao();
		FacesContext context = FacesContext.getCurrentInstance();
		clear();
		if (!getInves().getInvesBankName().equalsIgnoreCase("NO BANK") && !getInves().getInvesAccountNo().isEmpty()) {
			String msg = dao.update(getInves());
			System.out.println(msg);
			context.addMessage(null, new FacesMessage(msg));
			RequestContext.getCurrentInstance().execute("editInvestment.hide()");
		} else if (getInves().getInvesBankName().equalsIgnoreCase("NO BANK")) {
			getInves().setInvesAccountNo("");
			String msg = dao.update(getInves());
			System.out.println(msg);
			context.addMessage(null, new FacesMessage(msg));
			RequestContext.getCurrentInstance().execute("editInvestment.hide()");
		} else {
			context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_WARN, "Please Insert Account No", "Please Insert Account No"));
		}
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
