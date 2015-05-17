package com.rile.methotels.mixins;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 *
 * @author Stefan
 */
@Import(library = "Confirm.js")
public class Confirm {

    @Parameter(value = "Da li ste sigurni?", defaultPrefix = BindingConstants.LITERAL)
    private String message;
    @Inject
    private JavaScriptSupport js;
    @InjectContainer
    private ClientElement element;

    /**
     * Add script.
     */
    @AfterRender
    public void afterRender() {
        js.addInitializerCall("confirmation", new JSONObject("id", this.element.getClientId(),
                "message", this.message));
    }
}
