package com.rile.methotels.components;

import com.rile.methotels.data.NavigationPage;
import java.util.List;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;

/**
 *
 * @author Stefan
 */
public class Navigation {

    @Parameter(required = true)
    private List<NavigationPage> source;
    @Parameter(value = "null")
    private String cssClass;
    
    @BeginRender
    void beginRender(MarkupWriter writer) {
        writer.writeRaw("<ul " + "class='" + cssClass + "'>");
        renderNavigation(writer, source, false);
        writer.writeRaw("</ul>");
    }

    void renderNavigation(MarkupWriter writer, List<NavigationPage> navigationPages, boolean hasMultiLevel) {
        for (NavigationPage navPage : navigationPages) {
            boolean hasSubPages = navPage.getSubPages() != null && !navPage.getSubPages().isEmpty();

            if (hasSubPages) {
                writer.writeRaw(
                    "<li class='" + (hasMultiLevel && cssClass != null ? "dropdown-submenu" : "") + "'>" +
                        "<a data-toggle='dropdown' class='dropdown-toggle'" + 
                            "href='" + getPageClassName(navPage.getMainPage()) + "'>" + 
                            getPageName(navPage.getMainPage()) + 
                            (hasMultiLevel && cssClass != null ? "" : "<b class='caret'></b>") +
                        "</a>" + 
                        "<ul class='" + (cssClass != null ? "dropdown-menu" : "") + "'>"
                );
                renderNavigation(writer, navPage.getSubPages(), true);
                writer.writeRaw(
                        "</ul>" +
                    "</li>"
                );
            }
            else {
                writer.writeRaw(
                    "<li>" + 
                        "<a href='" + getPageClassName(navPage.getMainPage()) +"'>" + 
                            getPageName(navPage.getMainPage()) + 
                        "</a>" +
                    "</li>"
                );                
            }
        }
    }

    String getPageName(String classPageName) {
        return String.join(" ", classPageName.trim().split("(?=\\p{Upper})"));
    }
    
    String getPageClassName(String pageName) {
        return pageName;
    }
    
    String applyCSSActive() {
        // TO ADD: @Inject ComponentResource resource;
        return "";
    }
    
}
