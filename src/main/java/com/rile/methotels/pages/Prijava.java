package com.rile.methotels.pages;

import com.rile.methotels.entities.Korisnik;
import com.rile.methotels.entities.Role;
import com.rile.methotels.services.dao.KorisnikDao;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Stefan
 */
public class Prijava {

    @Property
    private Korisnik korisnikLogin;
    @SessionState
    private Korisnik loggedInKorisnik;
    @Inject
    private KorisnikDao korisnikDao;
    @Component
    private BeanEditForm form;

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

    Object onSuccess() {
        String lozinka = getMD5Hash(korisnikLogin.getLozinka());
        Korisnik korisnik = korisnikDao.checkKorisnik(korisnikLogin.getKorisnickoIme(), lozinka);
        if (korisnik != null) {
            loggedInKorisnik = korisnik;
            if (loggedInKorisnik.getRola() == Role.Admin) {
                return AdminPanel.class;
            }
            return Index.class;
        } else {
            form.recordError("Uneli ste pogrešne parametre");
            System.out.println("Loši parameteri");
            return null;
        }
    }
}
