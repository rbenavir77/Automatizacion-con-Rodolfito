package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePom extends BasePage {
    public HomePom(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "cb1-edit")
    private WebElement buscador;
    String buscadorId = "cb1-edit";
    public boolean cargoMercadolibre() {
        return this.idExist(buscadorId);
    }
    public void ingresarBusqueda (String busqueda) throws Exception {
        this.sendKeys(buscador,busqueda );
    }
}
