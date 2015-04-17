package com.rile.methotels.pages;

import com.rile.methotels.services.security.ProtectedPage;
import javax.annotation.security.RolesAllowed;

/**
 *
 * @author Stefan
 */
@ProtectedPage
@RolesAllowed(value={"Admin"})
public class AdminPanel {
    
}
