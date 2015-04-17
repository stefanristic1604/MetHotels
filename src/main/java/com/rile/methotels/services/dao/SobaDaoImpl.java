package com.rile.methotels.services.dao;

import com.rile.methotels.entities.Soba;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Stefan
 */
public class SobaDaoImpl implements SobaDao {

    @Inject
    private Session session;
    
    @Override
    public List<Soba> getListSoba() {
        return session.createCriteria(Soba.class).list();
    }

    @Override
    public Soba getSobaById(Integer id) {
        return (Soba) session.createCriteria(Soba.class).add(
                Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void addSoba(Soba soba) {
        session.persist(soba);
    }

    @Override
    public void removeSoba(Integer id) {
        session.delete(
            (Soba) session.createCriteria(Soba.class).add(Restrictions.eq("id", id)).uniqueResult()
        );
    }
}
