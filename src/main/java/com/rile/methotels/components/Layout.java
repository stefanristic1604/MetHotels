package com.rile.methotels.components;

import com.rile.methotels.entities.Korisnik;
import com.rile.methotels.entities.Role;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;

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

    // use var:navItem as a parameter instead of property, inside tml:loop
    // jk: http://stackoverflow.com/questions/29679416/pass-variable-to-a-method-as-a-parameter
    @Property
    private NavigationPage navItem;

    @Property
    @Inject
    @Symbol(SymbolConstants.APPLICATION_VERSION)
    private String appVersion;

    @Property
    @SessionState
    private Korisnik loggedInKorisnik;

    public boolean isLoggedIn() {
        if (loggedInKorisnik.getEmail() != null) {
            return true;
        }
        return false;
    }

    public void onActionFromLogout() {
        loggedInKorisnik = null;
    }

    public String getClassForPageName() {
        return resources.getPageName().equalsIgnoreCase(navItem.getMainPage())
                ? "active"
                : null;
    }

    public String getClassForPageName(String pageName) {
        return resources.getPageName().equalsIgnoreCase(pageName)
                ? "active"
                : null;
    }

    public List<NavigationPage> getLeftNavigationMenu() {
        List<NavigationPage> leftNavMenu = new ArrayList<NavigationPage>();
        leftNavMenu.add(0, new NavigationPage("Index", null));
        leftNavMenu.add(1, new NavigationPage("Usluge", new String[] {"PregledSoba"}));
        leftNavMenu.add(2, new NavigationPage("ONama", null));
        
        if (isLoggedIn()) {
            if (loggedInKorisnik.getRola() == Role.Admin) {
                leftNavMenu.set(1, new NavigationPage(
                    "Usluge", 
                    new String[] {"AdminPanel", "UnosKorisnika", "DodavanjeSoba", "RezervacijeSoba", "PregledSoba"}
                ));
            }
            else if (loggedInKorisnik.getRola() == Role.Sluzbenik) {
                leftNavMenu.set(1, new NavigationPage(
                    "Usluge", 
                    new String[] {"DodavanjeSoba", "RezervacijeSoba", "PregledSoba"}
                ));
            }
            else if (loggedInKorisnik.getRola() == Role.Korisnik) {
                leftNavMenu.set(1, new NavigationPage(
                    "Usluge", 
                    new String[] {"RezervacijeSoba", "PregledSoba"}
                ));
            }
        }
        return leftNavMenu;
    }
    
    public NavigationPage[] getRightNavigationMenu() {
        return new NavigationPage[]{
            new NavigationPage("Prijava", null),
            new NavigationPage("Registracija", null)
        };
    }

    protected class NavigationPage {

        private String mainPage;
        private String[] subPages;

        public NavigationPage(String mainPage, String[] subPages) {
            this.mainPage = mainPage;
            this.subPages = subPages;
        }

        public String getMainPage() {
            return mainPage;
        }

        public void setMainPage(String mainPage) {
            this.mainPage = mainPage;
        }

        public String[] getSubPages() {
            return subPages;
        }

        public void setSubPages(String[] subPages) {
            this.subPages = subPages;
        }
    }

    // use parameter instead of pageName property
    public String getMenuPageName(String pageName) {
        String newName = "";
        if (pageName.equals("Index")) {
            return "Početna";
        }
        for (int i = 0; i < pageName.length(); i++) {
            if (Character.isUpperCase(pageName.charAt(i))) {
                newName += " ";
            }
            newName += pageName.charAt(i);
        }
        return newName;
    }

}
