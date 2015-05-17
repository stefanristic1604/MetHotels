package com.rile.methotels.pages.services;

import com.rile.methotels.entities.Rezervacija;
import com.rile.methotels.services.dao.RezervacijaDao;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.util.TextStreamResponse;

/**
 *
 * @author Stefan
 */
public class FindRezervacija {

    @Inject
    private Request request;
    @Property
    private List<Rezervacija> rezervacije;
    @Property
    private Rezervacija rezervacija;
    @Inject
    private RezervacijaDao rezervacijaDao;

    Object onActivate(@RequestParameter("ime") String ime) {
        if (rezervacije == null) {
            rezervacije = new ArrayList<Rezervacija>();
        }
        String response = "<table class=\"navigation\" > <th>\n" + 
                " Ime rezervacije\n" + 
                " </th>\n" + " ";
        
        rezervacije = rezervacijaDao.getListByIme(ime);
        for (Rezervacija r : rezervacije) {
            response += (" <tr>\n" + 
                    " <td> " + r.getIme() + "</td>\n" + 
                    " </tr>"
            );
        }
        response += "</table>";
        return new TextStreamResponse("text/plain", response);
    }

}
