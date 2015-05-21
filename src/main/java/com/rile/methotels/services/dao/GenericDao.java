package com.rile.methotels.services.dao;

import com.rile.methotels.entities.AbstractEntity;
import java.util.List;

/**
 *
 * @author Stefan
 */
public interface GenericDao<T extends AbstractEntity> {

    T persist(T t);
    T getByID(Integer id);
    T merge(T t);
    T delete(Integer id);
    T saveOrUpdate(T t);
    List<T> loadAll();
            
}
