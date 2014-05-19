package org.tpapelita.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Query;
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
	public List<Investor> getReadLastId() {
        List<Investor> investors = new ArrayList<Investor>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            investors = session.createQuery("from Investor order by investorId DESC").list();
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
	public List<Investor> getReadBy() {
        List<Investor> investors = new ArrayList<Investor>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            investors = session.createQuery("from Investor order by investorId").list();
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
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(investor);
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
	
	/**
	 * puput 1-705
	 */
	
	@SuppressWarnings("unchecked")
	public Boolean Read(int id, String pass) {
		List<Investor> investor = new ArrayList<Investor>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println(id+", "+pass);
		try {
			investor = session.createQuery("from Investor where investor_id = "+id+" and investor_pass = '"+pass+"'").list();
			System.out.println(investor.get(0));
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		} finally {
			session.flush();
			session.close();
		}
	}
	
	public boolean cekLogin(Investor i) {
		boolean kondisi = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String sqlQuery = "FROM Investor WHERE investorId = '"
					+ i.getInvestorId() + "' and investorPass = '"
					+ i.getInvestorPass()+"'";
			Query query = session.createQuery(sqlQuery);
			if (!query.list().isEmpty()) {
				kondisi = true;
			} else {
			}
		} catch (Exception e) {
		}  finally {
			session.flush();
			session.close();
		}
		return kondisi;
	}
}
