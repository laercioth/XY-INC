package br.com.xyinc.persistence.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.xyinc.persistence.dao.IGenericDAO;

public abstract class GenericDAO<T, ID> implements IGenericDAO<T, ID> {

    private Class<T> clazz;

    @PersistenceContext(unitName = "xyinc_ds_teste")
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public GenericDAO() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> listAll() {
        return getEntityManager().createQuery("FROM " + clazz.getName()).getResultList();
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    public EntityManager setEntityManager(EntityManager em) {
        return this.em = em;
    }

    public Criteria getHbCriteria() {
        Session session = getSession();
        return session.createCriteria(clazz);
    }

    public Session getSession() {
        return (Session) getEntityManager().unwrap(org.hibernate.Session.class);
    }

    @Override
    public T saveOrUpdate(T t) {
        return getEntityManager().merge(t);
    }

    @Override
    public void remove(T t) {
        getEntityManager().remove(t);
    }

    @Override
    public T findById(ID id) {
        return getEntityManager().find(clazz, id);
    }

    @Override
    public void removeById(ID id) {
        getEntityManager().remove(findById(id));
    }

}
