package org.tpapelita.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.tpapelita.pojo.Investment;
import org.tpapelita.util.HibernateUtil;

@ManagedBean
@SessionScoped
public class InvestmentDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String create(Investment inves) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.save(inves);
			session.getTransaction().commit();
			return "Save Succesfully";
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
			return "Save Failed";
		} finally {
			session.flush();
			session.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Investment> getRead() {
		List<Investment> investment = new ArrayList<Investment>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			investment = session.createQuery("from Investment").list();
			return investment;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return investment;
		} finally {
			session.flush();
			session.close();
		}
	}
	@SuppressWarnings("unchecked")
	public List<Investment> getReadBy(int value) {
		List<Investment> investment = new ArrayList<Investment>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			switch (value) {
			case 1: investment = session.createQuery("from Investment where invesTransType = 0").list();break;
			case 2: investment = session.createQuery("from Investment where invesTransType = 1").list();break;
			}
			return investment;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ArrayList<Investment>();
		} finally {
			session.flush();
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Investment> getReadManyToOne(int investorId) {
		List<Investment> investment = new ArrayList<Investment>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			investment = session.createQuery(
					"from Investment where investor = " + investorId).list();
			return investment;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ArrayList<Investment>();
		} finally {
			session.flush();
			session.close();
		}
	}

	public String update(Investment inves) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.update(inves);
			session.getTransaction().commit();
			return "Update Succesfully";
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
			return "Update Failed";
		} finally {
			session.flush();
			session.close();
		}
	}

	public String delete(Investment inves) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.delete(inves);
			session.getTransaction().commit();
			return "Delete Succesfully";
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
			return "Delete Failed";
		} finally {
			session.flush();
			session.close();
		}
	}
}
