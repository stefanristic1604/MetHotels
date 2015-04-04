package com.rile.methotels.services;

import com.rile.methotels.entities.Rezervacija;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Stefan
 */
public class RezervacijaDaoImpl implements RezervacijaDao {

    @Inject
    private Session session;
    
    @Override
    public List<Rezervacija> getListRezervacija() {
        return session.createCriteria(Rezervacija.class).list();
    }

    @Override
    public Rezervacija getRezervacijaById(Integer id) {
        return (Rezervacija) session.createCriteria(Rezervacija.class).add(
                Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void addRezervacija(Rezervacija rezervacija) {
        session.persist(rezervacija);
    }

    @Override
    public void removeRezervacija(Integer id) {
        session.delete(
            (Rezervacija) session.createCriteria(Rezervacija.class).add(Restrictions.eq("id", id)).uniqueResult()
        );
    }
    
}
