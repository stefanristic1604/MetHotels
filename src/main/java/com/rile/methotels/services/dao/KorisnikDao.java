package com.rile.methotels.services.dao;

import com.rile.methotels.entities.Korisnik;
import java.util.List;

/**
 *
 * @author Stefan
 */
public interface KorisnikDao {

    public List<Korisnik> getListKorisnika();
    public void removeKorisnik(Integer id);
    public Korisnik checkKorisnik(String email, String password);
    public Korisnik registerKorisnik(Korisnik korisnik);
    public boolean checkIfEmailExists(String email);

}
