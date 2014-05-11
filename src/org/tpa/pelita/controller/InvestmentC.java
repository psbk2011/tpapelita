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
import org.tpapelita.pojo.Administrator;
import org.tpapelita.pojo.Investment;

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
	private int autoInvestmentId;
	private String investorId;

	/*
	 * Support Method
	 */
	public InvestmentC() {
		this.inves = new Investment();
	}
	
	public Investment getInves() {
		return inves;
	}

	public void setInves(Investment inves) {
		this.inves = inves;
	}
	
	public String getInvestorId() {
		return investorId;
	}

	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}

	public int getAutoInvestmentId() {
		return autoInvestmentId;
	}

	public void setAutoInvestmentId(int autoInvestmentId) {
		this.autoInvestmentId = autoInvestmentId;
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

	public List<InvestmentC> getList() {
		return list;
	}

	public void setList(List<InvestmentC> list) {
		this.list = list;
	}

	public String getGenInvesId() {
		int temp = 1;
		String uniqueChar = "INVTR";
		String id = uniqueChar+temp;
		setAutoInvestmentId(temp);
		List<Investment> inves = getReadLastId(); 
		if ( inves.size() != 0) {
			temp = inves.get(0).getInvesId()+temp;
			id = uniqueChar+temp;
			setAutoInvestmentId(temp);
		}
		return id;
	}

	public String getInvesIdModif() {
		try {
			return ("INVTR" + getInves().getInvesId());
		} catch (NullPointerException e) {
			return "INVTR0";
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
	
	public String getInvesTypeModif() {
		String type = "";
		if (getInves().getInvesType()==1) {
			type = "Zakat";
		} else if(getInves().getInvesType()==2) {
			type = "Infaq";
		} else {
			type = "Sodaqoh";
		}
		return type;
	}

	public void clear() {
		setInves(new Investment());

	}
	/*
	 * CRUD Method
	 */
	public void create() {
		if (String.valueOf(inves.getInvesId()).isEmpty()
				|| inves.getInvesBankName().isEmpty()
				|| String.valueOf(inves.getInvesType()).isEmpty() 
				|| inves.getInvesSenderName().isEmpty() 
				|| inves.getInvesBankName().isEmpty()
				|| inves.getInvesAccountNo().isEmpty()
				|| String.valueOf(inves.getInvesTransfer()).isEmpty()){

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"gagal membuat!", "isi semua kolom!"));
			clear();

		} else {
			InvestmentDao dao = new InvestmentDao();
			FacesContext context = FacesContext.getCurrentInstance();
			String msg = "";
			getInves().setInvesId(getAutoInvestmentId());
			getInves().setInvesDate(new Date());
			getInves().setInvesStatus(3);
			Administrator admin = new Administrator();
			admin.setAdminId(0);
			getInves().setAdministrator(admin);
			msg = dao.create(getInves());
			System.out.println(msg);
			clear();
			context.addMessage(null, new FacesMessage(msg));
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
