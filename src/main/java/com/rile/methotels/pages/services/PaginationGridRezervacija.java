package com.rile.methotels.pages.services;

import com.rile.methotels.entities.Rezervacija;
import com.rile.methotels.services.dao.RezervacijaDao;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.util.TextStreamResponse;

/**
 *
 * @author Stefan
 */
public class PaginationGridRezervacija {

    @Inject
    private RezervacijaDao rezervacijaDao;
    private int size = 20;

    Object onActivate(@RequestParameter("page") int page) {
        Class<?> act = null;
        int sizeOfAll = rezervacijaDao.getTotalRezervacija();
        List<Rezervacija> lista = new ArrayList<Rezervacija>();
        String response = "<table class=\"navigation\" > <th>\n"
                + " Ime rezervacije\n"
                + " </th>\n"
                + " ";
        lista = rezervacijaDao.loadAllRezervacijaFromTo(page);
        for (Rezervacija r : lista) {
            response += (" <tr>\n"
                    + " <td> " + r.getIme() + "</td>\n"
                    + " </tr>");
        }
        response += "</table>";
        float ceil = (float) sizeOfAll / (float) 20;
        int totalPageSizes = (int) Math.ceil(ceil);
        response += "<ul class=\"pagination\">";
        for (int i = 1; i <= totalPageSizes; i++) {
            if (page == i) {
                response += ("<li class=\"callservice active\"><a href=\"#\">" + i + "</a></li>\n");
            } else {
                response += ("<li class=\"callservice\"><a href=\"#\">" + i + "</a></li>\n");
            }
        }
        response += "</ul>";
        return new TextStreamResponse("text/plain", response);
    }
}
