package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.appline.framework.utils.OffOn;

import java.util.List;

import static java.lang.Thread.sleep;
import static ru.appline.framework.managers.DriverManager.getWebDriver;
import static ru.appline.framework.utils.OffOn.ON;

public class FraimPage extends BasePageSber {
    @FindBy(xpath = "//div[@class='dc-input__label-4-9-1']")
    List<WebElement> listInputFields;

    @FindBy(xpath = "//div[@class='_1SP5M8CKBcG5upuK036cJf']")
    List<WebElement> listToggleButton;

    @FindBy(xpath = "//li[@class='_2oHcdFLGCjojtWqwTIofQG']")
    List<WebElement> listCalculate;

    //Boolean onFreim = false;
    WebElement toggle;
    WebElement tempElement;
    WebElement toggleButton;

    @Step("Заполняем поле  '{fieldname}' значением '{value}'")
    public FraimPage inputValueToListForFieldName(String fieldname, String value) {

        WebElement element = findElementForNameFromName(listInputFields, fieldname);
        element.findElement(By.xpath("./..//input")).sendKeys(Keys.CONTROL + "a");
        element.findElement(By.xpath("./..//input")).sendKeys(value);
        return this;
    }
    @Step("На кнопке переключения  '{parameter}' ставим состояние '{state}'")
    public FraimPage clickToggleButton(String parameter, OffOn state) {
        //listToggleButton= getWebDriver().findElements(By.xpath("//div[@class='_1SP5M8CKBcG5upuK036cJf']"));
        toggle = findElementForNameFromSpanName(listToggleButton, parameter);
        toggleButton = toggle.findElement(By.xpath(".//input[@class='switch-input-2-4-0']"));
        String stateAtr = toggleButton.getAttribute("aria-checked");
        System.out.println("atribute " + stateAtr);
        boolean stateToggle = Boolean.parseBoolean(stateAtr);
        boolean stateInput = false;
        if (state == ON) {
            stateInput = true;
        }
        if (stateInput != stateToggle) {
            System.out.println("шлеп");
            toggleButton.click();
        }
        // aria-checked
        return this;
    }

    protected WebElement findElementForNameFromSpanName(List<WebElement> list, String value) {
        //System.out.println(list.size() + " topMenuList " + " value " + value);
        WebElement element = list.stream()
                .filter(x -> {
                    try {
                        // System.out.println(x.getText() + " в цикле");
                        if (x.findElement(By.xpath(".//span")).getText().equals(value)) {
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

        return element;
    }
    @Step("проверяем поле '{fieldname}' на значение '{expected}'")
    public FraimPage checkCalculateList(String fieldname, String expected) {

        boolean flag = true;
        String temp = null;
        String actual = null;
        tempElement = findElementForNameFromSpanName(listCalculate, fieldname);

        while (flag) {
            try {
                sleep(500);
                temp = tempElement.getText();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (temp.equals(tempElement.getText())) {
                actual = tempElement.getText();
                flag = false;
            }
        }

        float actualParam = converter(actual);
        float expectedParam = converter(expected);

        Assertions.assertEquals(expectedParam, actualParam, "Не совпадают параметры в поле " + fieldname);

        return this;
    }
    @Step("Прокручиваем к заголовку  '{header}'")
    public FraimPage scrollToHeader(String header) {
        scrollToElementJs(findHeaderForText(header));
        return this;
    }

    private WebElement findHeaderForText(String text) {
        tempElement = getWebDriver().findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
        return tempElement;
    }

}
