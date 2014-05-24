package org.tpa.pelita.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.tpapelita.dao.OutcomeDetailsDao;
import org.tpapelita.pojo.Outcome;
import org.tpapelita.pojo.OutcomeDetails;


@ManagedBean
@SessionScoped
public class OutcomeDetailsC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutcomeDetails outDetails;
	private List<OutcomeDetailsC> list;
	private Outcome outcome;
	private long totalOutDetails;
	private long subTotalOutDetails;
	private long totalInves;

	/*
	 * Support Method
	 */
	public OutcomeDetailsC() {
		this.outDetails = new OutcomeDetails();
		this.outcome = new Outcome();
	}
	
	public long getTotalOutDetails() {
		OutcomeDetailsDao dao = new OutcomeDetailsDao();
		List<OutcomeDetails> outDetails = dao.getRead();
		totalOutDetails = 0;
		for (int i = 0; i < outDetails.size(); i++) {
			totalOutDetails += (outDetails.get(i).getDetailsUnitQty()*outDetails.get(i).getDetailsUnitPrice());
		}
		return totalOutDetails;
	}
	
	public long getTotalInves() {
		return totalInves;
	}

	public void setTotalInves(long totalInves) {
		this.totalInves = totalInves;
	}

	public void setTotalOutDetails(long totalOutDetails) {
		this.totalOutDetails = totalOutDetails;
	}

	public long getSubTotalOutDetails() {
		long temp = 0;
		try {
			int qty = getOutDetails().getDetailsUnitQty();
			int price = getOutDetails().getDetailsUnitPrice();
			temp = qty * price;
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		subTotalOutDetails = temp;
		return subTotalOutDetails;
	}

	public void setSubTotalOutDetails(long subTotalOutDetails) {
		this.subTotalOutDetails = subTotalOutDetails;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}
	
	public List<OutcomeDetailsC> getList() {
		return list;
	}

	public void setList(List<OutcomeDetailsC> list) {
		this.list = list;
	}

	public OutcomeDetails getOutDetails() {
		return outDetails;
	}

	public void setOutDetails(OutcomeDetails outDetails) {
		this.outDetails = outDetails;
	}

	public void clear() {
		setOutDetails(new OutcomeDetails());
	}
	
	
	@SuppressWarnings("deprecation")
	public String getGenOutDetailsId() {
		Date d = new Date();
		OutcomeDetailsDao outDao = new OutcomeDetailsDao();
		int year = (d.getYear()+1900);
		int month = (1+d.getMonth());
		int date = d.getDate();
		long temp = (year*10000)+(month*100)+date;
		long count = outDao.countRowBy((temp));
		long id = count;
		return String.valueOf(id);			
	}

	/*
	 * CRUD Method
	 */
	public void create() {
		FacesContext context = FacesContext.getCurrentInstance();
		OutcomeDetailsDao dao = new OutcomeDetailsDao();
		InvestmentC ic = new InvestmentC();
		setTotalInves(ic.getTotalInves());
		long balance = getTotalInves()-getTotalOutDetails();
		if ((balance-(getOutDetails().getDetailsUnitPrice()*getOutDetails().getDetailsUnitQty())) >= 0) {
			getOutDetails().setDetailsId(getGenOutDetailsId());
			String msg = dao.create(getOutDetails());
			System.out.println(msg);
			clear();
			context.addMessage(null, new FacesMessage(msg));
			RequestContext.getCurrentInstance().execute("addOutDetails.hide()");
		} else {
			context.addMessage(null, new FacesMessage( FacesMessage.SEVERITY_WARN, "Balance not enough", ("Balance is Rp "+String.valueOf(getTotalInves()))));
		}
	}

	public List<OutcomeDetailsC> getRead() {
		List<OutcomeDetailsC> list = new ArrayList<OutcomeDetailsC>();
		try {
			OutcomeDetailsDao dao = new OutcomeDetailsDao();
			List<OutcomeDetails> outDetails = dao.getRead();
			for (int i = 0; i < outDetails.size(); i++) {
				OutcomeDetailsC odc = new OutcomeDetailsC();
				odc.setOutDetails(outDetails.get(i));
				list.add(odc);
			}
			return list;
		} catch (NullPointerException e) {
			return new ArrayList<OutcomeDetailsC>();
		}
	}

	public void update() {
		OutcomeDetailsDao dao = new OutcomeDetailsDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.update(getOutDetails());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		clear();
		RequestContext.getCurrentInstance().execute("editOutDetails.hide()");
	}

	public void delete() {
		OutcomeDetailsDao dao = new OutcomeDetailsDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.delete(getOutDetails());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		clear();
	}
}
