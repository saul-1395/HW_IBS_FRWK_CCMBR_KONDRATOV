package ru.appline.steps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.appline.framework.managers.PageManager;
import ru.appline.framework.utils.OffOn;


public class Steps {
    /**
     * Менеджер страничек
     */
    private PageManager app = PageManager.getPageManager();

    @Когда("^Загружена стартовая страница$")
    public void getInitialPage() {
        app.getStartPage();
    }

    @Когда("^Выбрать вкладку '(.*)'$")
    public void selectNameBaseMenu(String nameBaseMenu) {
        app.getStartPage().selectServiceTopMenuClick(nameBaseMenu);
    }

    @Когда("^Дождаться выпадающего меню$")
    public void checkDropDownMenu() {
        app.getStartPage().checkVisibleDropDownMenu();
    }

    @Когда("^Выбрать меню '(.*)'$")
    public void selectNameDropDownMenu(String nameSubMenu) {
        app.getStartPage().selectServiceDropDownMenuClick(nameSubMenu);
    }

    @Когда("^Сделать скрол к заголовку '(.*)'$")
    public void scrollToHeader(String header) {
        app.getBuyingCompleteHouse().scrollToHeader(header);
    }

    @Тогда("^Перейти к окну с условиями ипотеки$")
    public void goToFraim() {
        app.getBuyingCompleteHouse().goToFraim();
    }

    @Когда("^Заполняем поля значениями$")
    public void inputValueToField(DataTable dataTable) {
        dataTable.cells().forEach(
                raw -> {
                    app.getFraimPage().inputValueToListForFieldName(raw.get(0), raw.get(1));
                }
        );

    }

    @Когда("^Сделать скрол к '(.*)'$")
    public void scrollToHeaderContainsText(String header) {
        app.getFraimPage().scrollToHeader(header);
    }

    @Когда("Выставляем значения переключателей")
    public void settingЕ(DataTable dataTable) {
        dataTable.cells().forEach(
                raw -> {
                    app.getFraimPage().clickToggleButton(raw.get(0), OffOn.valueOf(raw.get(1)));
                }
        );
    }

    @Тогда("Проверяем результат работы кредитного калькулятора")
    public void проверяемРезультатРаботыКредитногоКалькулятора(DataTable dataTable) {

        dataTable.cells().forEach(
                raw -> {
                    app.getFraimPage().checkCalculateList(raw.get(0), (raw.get(1)));
                }
        );
    }
}
