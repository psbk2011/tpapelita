package org.tpapelita.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.tpapelita.dao.EventDao;
import org.tpapelita.pojo.Event;

@ManagedBean
@SessionScoped
@ViewScoped
@ApplicationScoped
public class EventC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Event event;
	private List<EventC> list;

	/*
	 * Support Method
	 */
	public EventC() {
		this.event = new Event();
	}

	public List<EventC> getList() {
		return list;
	}

	public void setList(List<EventC> list) {
		this.list = list;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void clear() {
		setEvent(new Event());

	}

	/*
	 * CRUD Method
	 */
	public void create() {
		EventDao dao = new EventDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.create(getEvent());
		System.out.println(msg);
		clear();
		context.addMessage(null, new FacesMessage(msg));
	}

	public List<EventC> getRead() {
		List<EventC> list = new ArrayList<EventC>();
		try {
			EventDao dao = new EventDao();
			List<Event> event = dao.getRead();
			for (int i = 0; i < event.size(); i++) {
				EventC e = new EventC();
				e.setEvent(event.get(i));
				list.add(e);
			}
			return list;
		} catch (NullPointerException e) {
			return new ArrayList<EventC>();
		}
	}

	public void update() {
		EventDao dao = new EventDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.update(getEvent());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		clear();
	}

	public void delete() {
		EventDao dao = new EventDao();
		FacesContext context = FacesContext.getCurrentInstance();
		String msg = dao.delete(getEvent());
		System.out.println(msg);
		context.addMessage(null, new FacesMessage(msg));
		clear();
	}
}
