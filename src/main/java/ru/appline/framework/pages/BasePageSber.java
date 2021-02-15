package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static ru.appline.framework.managers.DriverManager.getWebDriver;
import static ru.appline.framework.managers.PageManager.getPageManager;

public class BasePageSber {


    WebElement menuElement;
    WebElement dropDownMenuElement;
    WebElement dropDownMenu;
    WebElement tempElement;

    WebDriverWait wait = new WebDriverWait(getWebDriver(), 15, 1000);

    public BasePageSber() {
        PageFactory.initElements(getWebDriver(), this);
    }

    protected JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
    @FindBy(xpath = "//*[@class=' kitt-top-menu__link kitt-top-menu__link_first kitt-top-menu__link_icons kitt-top-menu__link_wide']")
    List<WebElement> topMenuList;

    @FindBy(xpath = "//li[@class='kitt-top-menu__item']")
    List<WebElement> dropDownMenuList;


    protected WebElement findElementForNameFromName(List<WebElement> list, String value) {
      //  System.out.println(list.size() + " topMenuList " + " value " + value);
        tempElement = list.stream()
                .filter(x -> {
                    try {
                        System.out.println(x.getText() + " в цикле");
                        if (x.getText().equals(value)) {
                            return true;
                        } else {
                            return false;
                        }
                    } catch (Exception e) {
                        return false;
                    }
                })
                .findFirst()
                .orElse(null);
//        System.out.println(" ** " + product.getText() + " find " + value + " size " + list.size());
        return tempElement;
    }

    protected BasePageSber findElementForNameFromTopMenu(String value) {
        menuElement = findElementForNameFromName(topMenuList, value);
        return this;
    }

    protected WebElement clickButton(WebElement button) {
        System.out.println("нажимаем кнопку " + button.getText());
        elementClickable(button).click();
        return button;
    }

    public WebElement elementClickable(WebElement element) {
        // System.out.println(element.getText());
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    /**
     * Как только присваеваем  элементу меню значение, сразу привязываем к ниму выпадающее меню
     * Иницилизируем список из меню
     *
     * @param serviceName
     * @return
     */
    @Step("В верхнем меню нажать на '{serviceName}'")
    public BasePageSber selectServiceTopMenuClick(String serviceName) {
        System.out.println(serviceName + " service name");
        findElementForNameFromTopMenu(serviceName);
        dropDownMenu = menuElement.findElement(By.xpath("./..//div[@class='kitt-top-menu__dropdown kitt-top-menu__dropdown_icons']"));

        clickButton(menuElement);
        return this;
    }

    @Step("Ждём пока появится выпадающее меню")
    public BasePageSber checkVisibleDropDownMenu() {
        System.out.println(elementClickable(dropDownMenu).getText());
        return this;
    }

    @Step("Выбираем  '{serviceName}'")
    public BuyingCompleteHouse selectServiceDropDownMenuClick(String serviceName) {
        dropDownMenuElement = findElementForNameFromName(dropDownMenuList, serviceName);
        clickButton(dropDownMenuElement);
        return getPageManager().getBuyingCompleteHouse();
    }


    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected float converter(String value) {
        float f;
        value = value.replaceAll("[^{\\d,}]", "");
        value = value.replace(",", ".");
        f = Float.parseFloat(value);
        return f;
    }
}
