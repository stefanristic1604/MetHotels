package com.rile.methotels.services.dao;

import com.rile.methotels.entities.Korisnik;

/**
 *
 * @author Stefan
 */
public interface KorisnikDao extends GenericDao<Korisnik> {

    Korisnik checkKorisnik(String korisnickoIme, String lozinka);
    boolean checkIfEmailExists(String email);
    Korisnik checkIfFaceBookExists(String id);

}
