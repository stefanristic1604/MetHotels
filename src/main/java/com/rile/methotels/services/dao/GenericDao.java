package com.rile.methotels.services.dao;

import com.rile.methotels.entities.AbstractEntity;
import java.util.List;

/**
 *
 * @author Stefan
 */
public interface GenericDao<T extends AbstractEntity> {

    public abstract T persist(T t);
    public abstract T getByID(Integer id);
    public abstract T merge(T t);
    public abstract T delete(Integer id);
    public abstract T saveOrUpdate(T t);
    public abstract List<T> loadAll();
            
}
