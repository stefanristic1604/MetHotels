package com.rile.methotels.components;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;

@Import(stylesheet="context:mybootstrap/css/mystyle.css")
public class Layout {

    @Inject
    private ComponentResources resources;

    /**
     * The page title, for the <title> element and the <h1> element.
     */
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    private String pageName;

    @Property
    @Inject
    @Symbol(SymbolConstants.APPLICATION_VERSION)
    private String appVersion;
    
    public String getClassForPageName() {
        return resources.getPageName().equalsIgnoreCase(pageName)
                ? "active"
                : null;
    }
    
    public String[] getPageNames() {
        return new String[] {"Index", "DodavanjeSoba", "RezervacijeSoba", "ONama"};
    }
    
    public String getMenuPageName() {
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
