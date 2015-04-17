package com.rile.methotels.services.dao;

import com.rile.methotels.entities.Korisnik;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Stefan
 */
public class KorisnikDaoImpl implements KorisnikDao {

    @Inject
    private Session session;
    
    @Override
    public List<Korisnik> getListKorisnika() {
        return session.createCriteria(Korisnik.class).list();
    }

    @Override
    public void removeKorisnik(Integer id) {
        session.delete(
            (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("id", id)).uniqueResult()
        );
    }
        
    @Override
    public Korisnik checkKorisnik(String email, String password) {
        try {
            Korisnik korisnik = (Korisnik) session.createCriteria(Korisnik.class).add(
                Restrictions.eq("email", email)).add(Restrictions.eq("lozinka", password)).uniqueResult();
            if (korisnik != null) {
                return korisnik;
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public Korisnik registerKorisnik(Korisnik korisnik) {
        return (Korisnik) session.merge(korisnik);
    }

    @Override
    public boolean checkIfEmailExists(String email) {
        Long rows = (Long) session.createCriteria(Korisnik.class).add(
            Restrictions.eq("email", email)).setProjection(Projections.rowCount()).uniqueResult();
        return (rows != 0);
    }

}
