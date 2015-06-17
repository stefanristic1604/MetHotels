package com.rile.methotels.pages;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.rile.methotels.entities.Korisnik;
import com.rile.methotels.entities.Role;
import com.rile.methotels.services.FacebookService;
import com.rile.methotels.services.FacebookServiceInformation;
import com.rile.methotels.services.dao.KorisnikDao;
import java.io.IOException;
import net.smartam.leeloo.common.exception.OAuthProblemException;
import net.smartam.leeloo.common.exception.OAuthSystemException;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.PageLoaded;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
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

    @Inject
    private FacebookService facebookService;
    @SessionState
    @Property
    private FacebookServiceInformation facebookServiceInformation;
    @SessionState
    @Property
    private FacebookServiceInformation information;
    @Property
    private com.restfb.types.User userfb;
    @Property
    @ActivationRequestParameter
    private String code;

    @PageLoaded
    void onPageLoad() {
    }

    Object onActivate() {
        if (loggedInKorisnik.getEmail() != null) {
            return Pocetna.class;
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
            return Pocetna.class;
        } else {
            form.recordError("Uneli ste pogrešne parametre");
            System.out.println("Loši parameteri");
            return null;
        }
    }

    public String getFacebookAuthentificationLink() throws OAuthSystemException {
        return facebookService.getFacebookAuthentificationLink();
    }

    @CommitAfter
    public boolean isLoggedInFb() {
        if (facebookServiceInformation.getAccessToken() != null) {
            Korisnik fbuser = new Korisnik();
            fbuser.setEmail(userfb.getEmail());
            fbuser.setLozinka(" ");
            fbuser.setRola(Role.Korisnik);
            fbuser.setId(Integer.parseInt(userfb.getId()));
            Korisnik exist = null;
            System.out.println("proverava");
            exist = korisnikDao.checkIfFaceBookExists(userfb.getId());
            if (exist == null) {
                korisnikDao.merge(fbuser);
                loggedInKorisnik = fbuser;
                System.out.println("registruje");
            } else {
                loggedInKorisnik = exist;
                System.out.println("postoji");
            }
        }
        return facebookServiceInformation.getAccessToken() != null;
    }

    @SetupRender
    public void setup() throws IOException, OAuthSystemException,
            OAuthProblemException {
        if (code != null) {
            facebookService.getUserAccessToken(code,
                    information.getAccessToken());
        }
        code = null;
        FacebookClient facebookClient = new DefaultFacebookClient(information.getAccessToken());
        if (information.isLoggedIn()) {
            userfb = facebookClient.fetchObject("me", com.restfb.types.User.class);
        }
    }

}
