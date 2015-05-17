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

    @Parameter
    private List<NavigationPage> source;

    @BeginRender
    void beginRender(MarkupWriter writer) {
        renderNavigation(writer, source);
    }

    void renderNavigation(MarkupWriter writer, List<NavigationPage> navigationPages) {
        writer.writeRaw("<ul>");
        for (NavigationPage navPage : navigationPages) {
            writer.writeRaw("<li><a href=''>" + navPage.getMainPage() + "</a></li>");
            
            boolean hasSubPages = navPage.getSubPages() != null && !navPage.getSubPages().isEmpty();
            if (hasSubPages) {
                renderNavigation(writer, navPage.getSubPages());
            }
        }
        writer.writeRaw("</ul>");
    }

}
