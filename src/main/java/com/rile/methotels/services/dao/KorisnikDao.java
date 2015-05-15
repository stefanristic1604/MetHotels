package com.rile.methotels.services.dao;

import com.rile.methotels.entities.Korisnik;

/**
 *
 * @author Stefan
 */
public interface KorisnikDao extends GenericDao<Korisnik> {

    public Korisnik checkKorisnik(String korisnickoIme, String lozinka);
    public boolean checkIfEmailExists(String email);

}
