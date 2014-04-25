package org.tpapelita.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.tpapelita.pojo.Investor;
import org.tpapelita.util.HibernateUtil;


@ManagedBean
@SessionScoped
public class InvestorDao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String create(Investor investor) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(investor);
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
	public List<Investor> getRead() {
        List<Investor> investors = new ArrayList<Investor>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            investors = session.createQuery("from Investor order by investorStatus , investorId DESC").list();
            return investors;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<Investor>();
        } finally {
            session.flush();
            session.close();
        }
    }
	
	@SuppressWarnings("unchecked")
	public List<Investor> getReadById(int investorId) {
        List<Investor> investors = new ArrayList<Investor>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            investors = session.createQuery("from Investor where investorId = "+investorId).list();
            return investors;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ArrayList<Investor>();
        } finally {
            session.flush();
            session.close();
        }
    }
	
    public String update(Investor investor) {
    	System.out.println("Masuk dao.update");
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("Berhasil initialization session");
        try {
        	System.out.println("Masuk try case");
            trns = session.beginTransaction();
            session.update(investor);
            session.getTransaction().commit();
            return "Update Successfully";
        } catch (RuntimeException e) {
        	System.out.println("Masuk cath");
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
 	
	public String delete(Investor investor) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.delete(investor);
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
