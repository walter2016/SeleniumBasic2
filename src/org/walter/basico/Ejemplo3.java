package org.walter.basico;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Ejemplo3 {

    static WebDriver driver;
    static String chromePath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
    public static void main(String[] args){
        String baseURL = "https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt";

        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebDriverWait waitVar = new WebDriverWait(driver,10);


        try{
            driver.switchTo().frame("iframeResult");
            WebElement btnTry = driver.findElement(By.xpath("/html/body/button"));
            waitVar.until(ExpectedConditions.elementToBeClickable(btnTry));
            btnTry.click();
            Thread.sleep(2000);

            waitVar.until(ExpectedConditions.alertIsPresent());
            Alert alrtWindows = driver.switchTo().alert();
            String alerText = alrtWindows.getText();
            System.out.println(alerText);
            alrtWindows.sendKeys("Walter");
            alrtWindows.accept();

            String finalText = driver.findElement(By.id("demo")).getText();
            System.out.println(finalText.contains("Walte")?finalText:"Prueba fallo");

        }catch (NoSuchElementException ne){
            System.err.println("No se encontro el elemento WebElement " + ne.getMessage());

        }catch (NoSuchFrameException fr) {

            System.err.println("No se encontro el Frame " + fr.getMessage());
        }catch (NoAlertPresentException na){
            System.err.println("No se encontro la alerta " + na.getMessage());
        }catch (WebDriverException we){
            System.err.println("Fallo el web Driver " + we.getMessage());
        }
        /*catch (TimeoutException te){
            System.err.println("No se encontro la alerta " + te.getMessage());
        } */
        catch (Exception e){
            System.err.println(e.getMessage());
        } finally {
            driver.quit();
        }

    }

}
