package org.walter.intermedio;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class ActionExample {

    static WebDriver driver;

    public static void main(String[] args) {
        String baseURL = "https://www.facebook.com/";
        String chromePath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();

        try{
            WebElement txtUser = driver.findElement(By.id("email"));

            Actions builder = new Actions(driver);
            Action serieOActions = builder
                    .moveToElement(txtUser)
                    .click()
                    .keyDown(Keys.SHIFT)
                    .sendKeys("hello")
                    .keyUp(Keys.SHIFT)
                    .doubleClick()
                    .contextClick(txtUser)
                    .build();

            serieOActions.perform();
            Thread.sleep(2000);
            System.out.println("Test Passed");

        }catch (NoSuchElementException ne){
            System.err.println("No se encontro el elemento WebElement " + ne.getMessage());

        }catch (Exception e){
            System.err.println(e.getMessage());
        } finally {
            driver.quit();
        }

    }



}
