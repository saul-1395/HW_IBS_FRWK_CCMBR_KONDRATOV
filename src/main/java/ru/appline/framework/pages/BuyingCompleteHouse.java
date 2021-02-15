package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static ru.appline.framework.managers.DriverManager.getWebDriver;
import static ru.appline.framework.managers.PageManager.getPageManager;


public class BuyingCompleteHouse extends BasePageSber {
    @FindBy(xpath = "//*[@id='iFrameResizer0']")
    WebElement fraim;

    Boolean onFreim = false;

    WebElement header = getWebDriver().findElement(By.xpath("//*[contains(text(), 'Кредитный калькулятор')]"));

    @Step("Прокручиваем к   '{header}'")
    public BuyingCompleteHouse scrollToHeader(String header) {
        scrollToElementJs(findHeaderForText(header));
        return this;
    }

    private WebElement findHeaderForText(String text) {
        header = getWebDriver().findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
        return header;
    }

    @Step("Переходим на работу с фреймом")
    public FraimPage goToFraim() {
        getWebDriver().switchTo().frame(fraim);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Использовать материнский капитал']")));
        onFreim = true;
        return getPageManager().getFraimPage();
    }

}
