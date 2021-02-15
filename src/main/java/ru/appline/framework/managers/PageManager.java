
package ru.appline.framework.managers;


import ru.appline.framework.pages.*;

/**
 * менеджер страниц синглтонов
 */
public class PageManager {
    private static PageManager pageManager;

    private BuyingCompleteHouse buyingCompleteHouse;

    private StartPage startPage;
    private FraimPage fraimPage;

    private PageManager() {
    }

    public static PageManager getPageManager() {
        if(pageManager==null){
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public StartPage getStartPage() {
        if(startPage==null){
            startPage = new StartPage();
        }
        return startPage;
    }

    public BuyingCompleteHouse getBuyingCompleteHouse() {
        if(buyingCompleteHouse==null){
            buyingCompleteHouse = new BuyingCompleteHouse();
        }
        return buyingCompleteHouse;
    }

    public FraimPage getFraimPage() {
        if(fraimPage==null){
            fraimPage= new FraimPage();
        }
        return fraimPage;
    }
}
