package com.rile.methotels.services.dao;

import com.rile.methotels.entities.AbstractEntity;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Stefan
 */
public abstract class GenericDaoImpl<T extends AbstractEntity> implements GenericDao<T> {

    @Inject
    protected Session session;

    protected Class<T> classType;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        classType = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public T persist(final T t) {
        session.persist(t);
        return t;
    }

    @Override
    public T getByID(final Integer id) {
        return (T) session.createCriteria(classType)
                    .add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    @Override
    public T merge(final T t) {
        return (T) session.merge(t);
    }

    @Override
    public void delete(final Integer id) {
        session.delete(
            session.createCriteria(classType)
            .add(Restrictions.eq("id", id)).uniqueResult()
        );
        session.flush();
    }

    @Override
    public List<T> loadAll() {
        return (List<T>) session.createCriteria(classType).list();
    }

}
