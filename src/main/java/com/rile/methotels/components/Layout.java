package com.rile.methotels.components;

import com.rile.methotels.data.NavigationPage;
import com.rile.methotels.entities.Korisnik;
import com.rile.methotels.entities.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.services.PersistentLocale;

@Import(stylesheet = "context:mybootstrap/css/mystyle.css")
public class Layout {

    @Inject
    private ComponentResources resources;

    @Property
    @Parameter(autoconnect = true)
    private boolean showMarketing;

    @Property
    @Parameter(autoconnect = true)
    private boolean showBodyTitle;
    
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    @SessionState
    private Korisnik loggedInKorisnik;
    
    @Inject
    private Messages messages;
    @Inject
    private PersistentLocale persistentLocale;

    public boolean isLoggedIn() {
        return loggedInKorisnik.getEmail() != null;
    }
    
    public void onActionFromLogout() {
        loggedInKorisnik = null;
    }

    public List<NavigationPage> getLeftNavigationMenu() {
        List<NavigationPage> leftNavMenu = new ArrayList<NavigationPage>();
        leftNavMenu.add(0, new NavigationPage("Pocetna"));
        leftNavMenu.add(1, new NavigationPage("Usluge", 
            new ArrayList<NavigationPage>() {{
                add(new NavigationPage("PregledSoba"));
            }} 
        ));
        leftNavMenu.add(2, new NavigationPage("ONama"));
        leftNavMenu.add(3, new NavigationPage("Primeri",
            new ArrayList<NavigationPage>() {{
                add(new NavigationPage("TestComponent"));
                add(new NavigationPage("PrimerPaginacijaRezervacijaJQ"));
                add(new NavigationPage("PrimerPretragaRezervacijaJQ"));
                add(new NavigationPage("PrimerPretragaPlusPaginacijaRezervacija"));
                add(new NavigationPage("PrimerOsvezavanjaZone"));
                add(new NavigationPage("PrimerOsvezavanjaFormiZone"));
                add(new NavigationPage("PrimerInPlaceEditor"));
                add(new NavigationPage("RezervacijaService"));
            }}
        ));
        
        if (isLoggedIn()) {
            if (loggedInKorisnik.getRola() == Role.Admin) {
                leftNavMenu.set(1, new NavigationPage("Usluge", 
                    new ArrayList<NavigationPage>() {{
                        add(new NavigationPage("AdminPanel"));
                        add(new NavigationPage("UnosKorisnika"));
                        add(new NavigationPage("DodavanjeSoba"));
                        add(new NavigationPage("RezervacijeSoba"));
                        add(new NavigationPage("PregledSoba"));
                    }}    
                ));
            }
            else if (loggedInKorisnik.getRola() == Role.Sluzbenik) {
                leftNavMenu.set(1, new NavigationPage("Usluge", 
                    new ArrayList<NavigationPage>() {{
                        add(new NavigationPage("DodavanjeSoba"));
                        add(new NavigationPage("RezervacijeSoba"));
                        add(new NavigationPage("PregledSoba"));
                    }} 
                ));
            }
            else if (loggedInKorisnik.getRola() == Role.Korisnik) {
                leftNavMenu.set(1, new NavigationPage("Usluge", 
                    new ArrayList<NavigationPage>() {{
                        add(new NavigationPage("RezervacijeSoba"));
                        add(new NavigationPage("PregledSoba"));
                    }}
                ));
            }
        }
        return leftNavMenu;
    }
    
    Object onActionFromChangeLanguage() {
        System.out.println("");
        if (!persistentLocale.isSet()) {
            persistentLocale.set(new Locale("en"));
        }
        if ("en".equalsIgnoreCase(persistentLocale.get().getLanguage())) {
            persistentLocale.set(new Locale("sr"));
        } else {
            persistentLocale.set(new Locale("en"));
        }
        return this;
    }
    
}
