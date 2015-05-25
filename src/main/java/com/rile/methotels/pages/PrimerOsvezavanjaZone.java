package com.rile.methotels.pages;

import java.util.Date;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

/**
 *
 * @author Stefan
 */
public class PrimerOsvezavanjaZone {

    @Inject
    private Request request;
    @InjectComponent
    private Zone time2Zone;
    @InjectComponent
    private Zone time3Zone;

    void onRefreshPage() {
    }

    Object onRefreshZone() {
        return request.isXHR() ? time2Zone.getBody() : null;
    }

    public Date getServerTime1() {
        return new Date();
    }

    public Date getServerTime2() {
        return new Date();
    }
    
    public Date getServerTime3() {
        return new Date();
    }
}
