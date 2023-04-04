package utils;
import java.io.File;
import java.text.Normalizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport {
    Logger log = LogManager.getLogger(ExtentReport.class);

    public static void captura(WebDriver D, String paso, ExtentTest loggerER, String descripcion) {
        String fecha = DateUtil.fecha();

        try {

            getScreenshot(D, paso, removerTildes(descripcion) + "-" + fecha );
            String destino = "Screenshots/"+ paso + " - " +  removerTildes(descripcion)+ "-" + fecha+ ".png";
            loggerER.log(LogStatus.PASS, descripcion, loggerER.addScreenCapture(destino));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void capturaFail(WebDriver D, String variacion, ExtentTest loggerER, String descripcion) {
        String fecha = DateUtil.fecha();
        try {
            getScreenshot(D, variacion, removerTildes(descripcion) + "-" + fecha );
            String destino = "Screenshots/"+ variacion + " - " + removerTildes(descripcion)+ "-" + fecha+".png";
            loggerER.log(LogStatus.FAIL, descripcion, loggerER.addScreenCapture(destino));
        } catch (Exception e) {
            e.getMessage();
        }
    }
    /**
     *
     * @param driver
     * @param test
     * @param screenshotName
     * @return
     * @throws Exception
     */
    public static String getScreenshot(WebDriver driver, String variacion, String screenshotName) throws Exception {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE); //path.screenshots=/test-output/testsScreenshots/
        String destination = System.getProperty("user.dir") + "/Reportes/Screenshots/"+ variacion + " - " + screenshotName
                + ".png";
        File finalDestination = new File(destination);
        Files.copy(source, finalDestination);
        return destination;
    }

    public void CrearDirectorioScreenshot() {

        File directorio = new File(System.getProperty("user.dir") + "/Reportes/Screenshots/");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                log.info("Directorio creado");
            } else {
                log.info("Error al crear directorio");
            }
        }
    }
    public static String removerTildes(String palabra) {
        String cadenaSinAcentos=null;
        try {
            String cadenaNormalize = Normalizer.normalize(palabra, Normalizer.Form.NFD);
            cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cadenaSinAcentos;

    }
}
