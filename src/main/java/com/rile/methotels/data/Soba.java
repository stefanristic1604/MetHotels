package com.rile.methotels.data;

import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Stefan
 */
public class Soba {

    private String naziv;
    private String sprat;
    private String opis;
    private String refSlika;
    
    @Inject
    public Soba() {}

    public Soba(String naziv, String sprat, String opis, String refSlika) {
        this.naziv = naziv;
        this.sprat = sprat;
        this.opis = opis;
        this.refSlika = refSlika;
    }
    
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSprat() {
        return sprat;
    }

    public void setSprat(String sprat) {
        this.sprat = sprat;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getRefSlika() {
        return refSlika;
    }

    public void setRefSlika(String refSlika) {
        this.refSlika = refSlika;
    }
    
}
