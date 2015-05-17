package com.rile.methotels.pages;

import com.rile.methotels.entities.Role;
import com.rile.methotels.entities.Korisnik;
import com.rile.methotels.services.dao.KorisnikDao;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.PageLoaded;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Stefan
 */
public class Registracija {

    @Property
    private Korisnik korisnikReg;
    @SessionState
    private Korisnik loggedInKorisnik;
    @Inject
    private KorisnikDao korisnikDao;
    @Component
    private BeanEditForm form;

    @PageLoaded
    void onPageLoad() {}
    
    Object onActivate() {
        if (loggedInKorisnik.getEmail() != null) {
            return Index.class;
        }
        return null;
    }
    
    public String getMD5Hash(String yourString) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(yourString.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    @CommitAfter
    Object onSuccess() {
        if (!korisnikDao.checkIfEmailExists(korisnikReg.getEmail())) {
            String unhashPassword = korisnikReg.getLozinka();
            korisnikReg.setLozinka(getMD5Hash(unhashPassword));
            korisnikReg.setRola(Role.Korisnik);
            // registruj korisnika
            Korisnik korisnik = korisnikDao.merge(korisnikReg);
            loggedInKorisnik = korisnik;
            return Index.class;
        } else {
            form.recordError("Email koji ste uneli vec postoji");
            return null;
        }
    }

}
