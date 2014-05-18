package org.tpapelita.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.tpapelita.pojo.SupportCenter;
import org.tpapelita.util.HibernateUtil;


@ManagedBean
@SessionScoped
public class SupportCenterDao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String create(SupportCenter sc) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(sc);
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
	public List<SupportCenter> getRead() {
        List<SupportCenter> sc = new ArrayList<SupportCenter>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            sc = session.createQuery("from SupportCenter order by supportShow, supportDate DESC").list();
            return sc;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<SupportCenter>();
        } finally {
            session.flush();
            session.close();
        }
    }
	
	@SuppressWarnings("unchecked")
	public List<SupportCenter> getReadLastId() {
        List<SupportCenter> sc = new ArrayList<SupportCenter>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            sc = session.createQuery("from SupportCenter order by supportShow, supportDate DESC").list();
            return sc;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<SupportCenter>();
        } finally {
            session.flush();
            session.close();
        }
    }
	
	@SuppressWarnings("unchecked")
	public List<SupportCenter> getReadById(int investorId) {
        List<SupportCenter> sc = new ArrayList<SupportCenter>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            sc = session.createQuery("from Investor where investorId = "+investorId).list();
            return sc;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<SupportCenter>();
        } finally {
            session.flush();
            session.close();
        }
    }
	
    public String update(SupportCenter sc) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(sc);
            session.getTransaction().commit();
            return "Update Successfully";
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
 	
	public String delete(SupportCenter sc) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.delete(sc);
            session.getTransaction().commit();
            return "Delete Successfully";
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
