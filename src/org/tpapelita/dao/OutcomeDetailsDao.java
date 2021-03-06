package org.tpapelita.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.tpapelita.pojo.OutcomeDetails;
import org.tpapelita.util.HibernateUtil;

@ManagedBean
@SessionScoped
public class OutcomeDetailsDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String create(OutcomeDetails outDetails) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.save(outDetails);
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
	public List<OutcomeDetails> getRead() {
		List<OutcomeDetails> invesDetails = new ArrayList<OutcomeDetails>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			invesDetails = session.createQuery("from OutcomeDetails ORDER BY detailsId DESC").list();
			return invesDetails;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ArrayList<OutcomeDetails>();
		} finally {
			session.flush();
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<OutcomeDetails> getReadById(int outcomeId) {
		List<OutcomeDetails> invesDetails = new ArrayList<OutcomeDetails>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			invesDetails = session.createQuery("from OutcomeDetails where outcome="+outcomeId).list();
			return invesDetails;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ArrayList<OutcomeDetails>();
		} finally {
			session.flush();
			session.close();
		}
	}


	public String update(OutcomeDetails invesDetails) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.update(invesDetails);
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

	public String delete(OutcomeDetails invesDetails) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.delete(invesDetails);
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
	@SuppressWarnings("unchecked")
	public long countRowBy(long min) {
		long count = 0;
		List<OutcomeDetails> outcome = new ArrayList<OutcomeDetails>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			outcome= session.createQuery(
					"FROM OutcomeDetails WHERE detailsId LIKE '"+ min+"%' ORDER BY detailsId DESC").list();
			if (outcome.size() != 0) {
				count = Long.valueOf(outcome.get(0).getDetailsId())+1;
			} else {
				count = (min*1000)+1;
			}
			return count;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return count;
		} finally {
			session.flush();
			session.close();
		}
	}
}
