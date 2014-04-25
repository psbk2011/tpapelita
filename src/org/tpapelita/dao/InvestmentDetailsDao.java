package org.tpapelita.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.tpapelita.pojo.InvestmentDetails;
import org.tpapelita.util.HibernateUtil;

@ManagedBean
@SessionScoped
public class InvestmentDetailsDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String create(InvestmentDetails invesDetails) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.save(invesDetails);
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
	public List<InvestmentDetails> getRead() {
		List<InvestmentDetails> invesDetails = new ArrayList<InvestmentDetails>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			invesDetails = session.createQuery("from InvestmentDetails").list();
			return invesDetails;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ArrayList<InvestmentDetails>();
		} finally {
			session.flush();
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<InvestmentDetails> getReadManyToOne(int investmentId) {
		List<InvestmentDetails> invesDetails = new ArrayList<InvestmentDetails>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			invesDetails = session.createQuery(
					"from InvestmentDetails where investment = " + investmentId).list();
			return invesDetails;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ArrayList<InvestmentDetails>();
		} finally {
			session.flush();
			session.close();
		}
	}

	public String update(InvestmentDetails invesDetails) {
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

	public String delete(InvestmentDetails invesDetails) {
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
}
