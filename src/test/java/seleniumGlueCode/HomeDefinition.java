package seleniumGlueCode;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class HomeDefinition extends TestBase {
    Logger log = LogManager.getLogger(HomeDefinition.class);
    ExtentTest test;
    @Given("inicia reporte, flujo: {string}")
    public void inicia_reporte_flujo(String flujo) {
        this.iniciaReporte(flujo);
        test=this.startTest("Buscar Producto"); // sección del reporte
        log.info("se inicia reporte");
    }
    @Given("ingreso a mercado libre")
    public void ingreso_a_mercado_libre() {
        Assert.assertTrue(home.cargoMercadolibre());
        this.CapturaPantalla("Página desplegada", test);

    }
    @When("realiza buqueda {string}")
    public void realiza_buqueda(String producto) throws Exception {
        home.ingresarBusqueda(producto);
        this.getTest().log(LogStatus.PASS,"se escribió la busqueda");
        this.CapturaPantalla("Página desplegada",test);


    }

    @Then("muestra relojes")
    public void muestra_relojes() {

    }
    @Then("selecciona un producto")
    public void selecciona_un_producto() {

    }
    @Then("finaliza reporte")
    public void finaliza_reporte() {
     this.endTest();
     this.fin();
     log.info("se finaliza el  reporte");
    }
}
