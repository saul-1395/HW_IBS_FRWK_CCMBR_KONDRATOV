package ru.appline.steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import ru.appline.framework.utils.MyAllureListener;

import static ru.appline.framework.managers.DriverManager.getWebDriver;
import static ru.appline.framework.managers.InitManager.initFramevork;
import static ru.appline.framework.managers.InitManager.quitFramework;
import static ru.appline.framework.managers.PropertyManager.getPropertyManager;
import static ru.appline.framework.utils.PropertyConst.APP_URL;

@ExtendWith(MyAllureListener.class)
public class Hooks {




    protected static JavascriptExecutor jse;
//    protected PageManager app = PageManager.getPageManager();

@Before
    public void  beforeEach() {
        System.out.println(" start test ****************");
        initFramevork();
    getWebDriver().get(getPropertyManager().getProperties(APP_URL));

//        getWebDriver().get(getPropertyManager().getProperties(APP_URL));
//        jse = (JavascriptExecutor) getWebDriver();
    }
@After
    public void afterEach() {
        quitFramework();
    }

}
