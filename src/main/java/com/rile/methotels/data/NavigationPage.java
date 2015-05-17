package com.rile.methotels.data;

import java.util.List;

/**
 *
 * @author Stefan
 */
public class NavigationPage {

    private String mainPage;
    private List<NavigationPage> subPages;

    public NavigationPage(String mainPage) {
        this.mainPage = mainPage;
    }

    public NavigationPage(String mainPage, List<NavigationPage> subPages) {
        this.mainPage = mainPage;
        this.subPages = subPages;
    }

    public String getMainPage() {
        return mainPage;
    }

    public List<NavigationPage> getSubPages() {
        return subPages;
    }

}
