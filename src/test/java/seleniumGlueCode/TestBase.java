package seleniumGlueCode;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pom.HomePom;
import utils.DateUtil;
import utils.ExtentReport;

public class TestBase {

    static public ExtentReports extentReport;
    protected ChromeDriver driver = Hooks.getDriver();
    protected HomePom home = PageFactory.initElements(driver,pom.HomePom.class);

    static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();


    public void iniciaReporte(String flujo) {
        DateUtil fecha = new DateUtil();
        this.extentReport = new ExtentReports(System.getProperty("user.dir") + "/Reportes/Reporte-"+ flujo + "-" + fecha.fecha() + ".html", true);
        this.extentReport.addSystemInfo("Encoding", "ISO-8859-1");
        this.extentReport.addSystemInfo("Host Name", "Automatizacion Mercado Libre")
                .addSystemInfo("Environment", "https://www.mercadolibre.cl/#from=homecom").addSystemInfo("User Name", "Ricardo");
        this.extentReport.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));

        ExtentReport er = new ExtentReport();
        er.CrearDirectorioScreenshot();

    }

    public WebDriver getDriver() {
        return driver;

    }

    public ExtentReports getExtentReport() {

        return this.extentReport;
    }


    public void afterEachTest(ITestResult result) {
        // System.out.println("morke "+this.getTest().getTest().getName());
        if (result.getStatus() == ITestResult.FAILURE) {
            this.getTest().log(LogStatus.FAIL, result.getThrowable());
            //this.getTest().log(LogStatus.FAIL, result.getThrowable().toString());
            //log.error(result.getThrowable());

            System.out.println("Condicion 1 : NO OK");
        } else if (result.getStatus() == ITestResult.SKIP) {
            this.getTest().log(LogStatus.SKIP, "Test Saltado " + result.getThrowable());
            System.out.println("Condicion 2 : Saltado");
        } else {
            this.getTest().log(LogStatus.PASS, "Test Pasado", DateUtil.fechaReporte());
            System.out.println("Condicion 3 : OK");
        }
        this.getExtentReport().endTest(this.getTest());
        this.getExtentReport().flush();
        // ubico este codigo al final para evitar que no cierre el Extent Report Test y
        // asi genere el Reporte en caso de caida

    }

    public ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public void endTest() {
        extentReport.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }

    public ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }

    public ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extentReport.startTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }


//	public String getTestName() {
//		return test.getTest().getName();
//	}

    public void CapturaPantalla(String strDescripcion, ExtentTest test) {
        ExtentReport.captura(this.getDriver(), test.getTest().getName() , this.getTest(), strDescripcion);
    }

    public void CapturaPantallaFail(String strDescripcion,ExtentTest test) {
        ExtentReport.capturaFail(this.getDriver(), test.getTest().getName(), this.getTest(), strDescripcion);
    }

    public void assertTrueSinCaso(boolean b, String mensaje, ExtentTest test) {
        if(!b) {
            this.CapturaPantallaFail(mensaje,test);
            assertTrue(false,mensaje);
        }
    }

    public void fin() {
        //this.getExtentReport().endTest(this.getTest());
        this.getExtentReport().flush();
    }
}
