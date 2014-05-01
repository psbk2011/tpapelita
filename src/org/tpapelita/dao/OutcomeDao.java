package org.tpapelita.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.tpapelita.pojo.Outcome;
import org.tpapelita.util.HibernateUtil;

@ManagedBean
@SessionScoped
public class OutcomeDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String create(Outcome outcome) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.save(outcome);
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
	public List<Outcome> getRead() {
		List<Outcome> outcome = new ArrayList<Outcome>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			outcome = session.createQuery("from Outcome order by outcomeId Desc").list();
			return outcome;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ArrayList<Outcome>();
		} finally {
			session.flush();
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Outcome> getReadLastId() {
        List<Outcome> outcome = new ArrayList<Outcome>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	outcome = session.createQuery("from Outcome order by outcomeId DESC").list();
            return outcome;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<Outcome>();
        } finally {
            session.flush();
            session.close();
        }
    }

	@SuppressWarnings("unchecked")
	public List<Outcome> getReadManyToOne(int outcomeId) {
		List<Outcome> outcome = new ArrayList<Outcome>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			outcome = session.createQuery(
					"from Investment where investor = " + outcomeId).list();
			return outcome;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ArrayList<Outcome>();
		} finally {
			session.flush();
			session.close();
		}
	}

	public String update(Outcome outcome) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.update(outcome);
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

	public String delete(Outcome outcome) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.delete(outcome);
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
