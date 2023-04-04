package seleniumGlueCode;
import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.DateUtil;

public class Hooks {
    //se ejecuta en cada caso de prueba
    // private static ChromeOptions option;
    private static ChromeDriver driver;
    public static ChromeDriver getDriver() {
        return driver;
    }

    @Before
    public void setUp(){

        WebDriverManager.chromedriver().browserVersion("111").setup();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("credentials_enable_service", false); //no utiliza credenciales guardadas
        chromePrefs.put("profile.password_manager_enabled", false); // no utilice contraseñas guardadas

        ChromeOptions option = new ChromeOptions();

        option.addArguments("start-maximized");
        option.addArguments("no-sandbox");
        option.addArguments("disable-notifications");
        //  option.setHeadless(true);
        option.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        option.setExperimentalOption("prefs", chromePrefs);
        option.addArguments("--remote-allow-origins=*");
        //option.addArguments("window-size=1600,768");
        driver = new ChromeDriver(option);
        driver.get("https://www.mercadolibre.cl/#from=homecom");





    }

    @After
    public  void tearDown(){
        driver.quit();

    }
}
