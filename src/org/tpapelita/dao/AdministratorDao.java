package org.tpapelita.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.tpapelita.pojo.Administrator;
import org.tpapelita.util.HibernateUtil;

@ManagedBean
@SessionScoped
public class AdministratorDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String create(Administrator admin) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.save(admin);
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
	public List<Administrator> getRead() {
		List<Administrator> admin = new ArrayList<Administrator>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			admin = session.createQuery("from Administrator").list();
			return admin;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ArrayList<Administrator>();
		} finally {
			session.flush();
			session.close();
		}
	}

	public String update(Administrator admin) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.update(admin);
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

	public String delete(Administrator admin) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			session.delete(admin);
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
