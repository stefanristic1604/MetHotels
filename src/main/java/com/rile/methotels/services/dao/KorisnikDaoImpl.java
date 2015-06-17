package com.rile.methotels.services.dao;

import com.rile.methotels.entities.Korisnik;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Stefan
 */
public class KorisnikDaoImpl extends GenericDaoImpl<Korisnik> implements KorisnikDao {
            
    @Override
    public Korisnik checkKorisnik(String korisnickoIme, String lozinka) {
        try {
            Korisnik korisnik = (Korisnik) session.createCriteria(classType)
                    .add(Restrictions.eq("korisnickoIme", korisnickoIme))
                    .add(Restrictions.eq("lozinka", lozinka)).uniqueResult();
            return korisnik != null ? korisnik : null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public boolean checkIfEmailExists(String email) {
        Long rows = (Long) session.createCriteria(classType).add(
            Restrictions.eq("email", email)).setProjection(Projections.rowCount()).uniqueResult();
        return (rows != 0);
    }

    @Override
    public Korisnik checkIfFaceBookExists(String id) {
        return (Korisnik) session.createCriteria(classType)
                .add(Restrictions.eq("facebookId", id)).uniqueResult();
    }

}
