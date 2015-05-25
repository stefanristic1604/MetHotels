package com.rile.methotels.pages;

import com.rile.methotels.data.NavigationPage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefan
 */
public class TestComponent {
    
    public List<NavigationPage> getNavPages() {
        List<NavigationPage> nav = new ArrayList<NavigationPage>();
        nav.add(0, new NavigationPage("Pocetna"));
        nav.add(1, 
            new NavigationPage("Usluge", 
            new ArrayList<NavigationPage>() {{
                add(new NavigationPage("DodavanjeSoba"));
                add(new NavigationPage("Rezervacije", 
                    new ArrayList<NavigationPage>() {{
                        add(new NavigationPage("R1"));
                        add(new NavigationPage("R2"));
                        add(new NavigationPage("R3"));
                    }}
                ));
                add(new NavigationPage("UnosKorisnika"));
            }}
        ));
        nav.add(2, new NavigationPage("ONama"));
        
        return nav;
    }
    
}