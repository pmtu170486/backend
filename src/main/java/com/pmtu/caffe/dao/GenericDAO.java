package com.pmtu.caffe.dao;

import com.pmtu.caffe.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Mr Tu on 06-05-2017.
 */
public class GenericDAO<T> implements IGenericDAO<T> {

    @Autowired
    private SessionFactory sessionFactory;

    /*public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
*/
    protected Session getSession() {
        //return HibernateUtil.getSessionFactory().getCurrentSession();
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(T t) throws Exception {
        try {
            getSession().beginTransaction();
            getSession().save(t);
            getSession().getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean update(T t) throws Exception {
        try {
            getSession().beginTransaction();
            getSession().update(t);
            getSession().getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean delete(Long id, Class<T> t) throws Exception {
        try {
            getSession().beginTransaction();
            getSession().delete(getSession().get(t, id));
            getSession().getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public T get(Long id, Class<T> t) throws Exception {
        T t1;
        try {
            getSession().beginTransaction();
            t1 = (T) getSession().get(t, id);
            getSession().getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
        return t1;
    }

    @Override
    public List<T> getall(Class<T> t) throws Exception {
        List<T> list = null;
        Criteria crit = null;
        try {
            getSession().beginTransaction();
            list = getSession().createCriteria(t).list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }
    //...........
}
