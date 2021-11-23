package org.walter.basico;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Forms extends Throwable{

    static WebDriver driver;
    static String chromePath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
    public static void main(String[] args){
        String baseURL = "http://www.newtours.demoaut.com/";

        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
        try{
            driver.findElement(By.linkText("REGISTER")).click();
            WebElement txtFirstName = driver.findElement(By.name("firstName"));
            txtFirstName.sendKeys("Walter");
            Thread.sleep(1500);
            txtFirstName.clear();
            Thread.sleep(1500);
            txtFirstName.sendKeys("Alexander");
            driver.findElement(By.name("address1")).sendKeys("Test Andress");

            Select drpCountry = new Select(driver.findElement(By.name("country")));

            Thread.sleep(2000);
            drpCountry.selectByVisibleText("BRAZIL");

            driver.findElement(By.id("email")).sendKeys("walter@email.com");
            driver.findElement(By.name("password")).sendKeys("123");


            WebElement txtConfirPass = driver.findElement(By.name("confirmPassword"));
            txtConfirPass.sendKeys("123");
            txtConfirPass.submit();

            System.out.println("Prueba Exitosa " + driver.findElement(By.xpath("//*[contains(text(),'Note')]")).getText());



        }catch (NoSuchElementException ne){
            System.err.println("No se encontro el elemento WebElement " + ne.getMessage());

        }catch (WebDriverException we){
            System.err.println("Fallo el web Driver " + we.getMessage());
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
                driver.quit();
        }

    }
}
