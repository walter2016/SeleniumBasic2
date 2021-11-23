package org.walter.intermedio;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JavaScripts {

    private WebDriver driver;
    String expectedResult = null;
    String actualResult=null;
    String baseUrl = "http://www.newtours.demoaut.com/";
    private JavascriptExecutor js;
    String pageLoadStatus = "";

    private boolean highLight(WebElement element){
        js = (JavascriptExecutor)driver;
        for (int i=0; i<3;i++){
            try {
                js.executeScript("arguments[0].setAttribute('style', 'background:red')",element);
                Thread.sleep(1000);
                js.executeScript("arguments[0].setAttribute('style', 'background:')",element);
            }catch (Exception e){
                System.err.println("JavaScript | Method: highLight | Exeption desc: " + e.getMessage());
                return false;
            }

        }
        return  true;
    }

    private boolean scrollWindows(){

            try {
                js = (JavascriptExecutor)driver;
                //Scroll up (0,-250) down(0,250)
                js.executeScript("window.scrollBy(0,250)");

            }catch (Exception e){
                System.err.println("JavaScript | Method: scrollWindows | Exeption desc: " + e.getMessage());
                return false;
            }


        return  true;
    }

    private boolean waitForPageToLoad(){

        try {
            do {
                js = (JavascriptExecutor)driver;
                pageLoadStatus = (String) js.executeScript("return document.readyState");

            }while(!pageLoadStatus.equals("complete"));


        }catch (Exception e){
            System.err.println("JavaScript | Method: waitForPageToLoad | Exeption desc: " + e.getMessage());
            return false;
        }


        return  true;
    }


    @BeforeTest
    public void launchBrowser(){
        String chromePath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        waitForPageToLoad();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @Test(priority = 0)
    public void goToRegisterPage(){
        WebElement lnkRegister = driver.findElement(By.linkText("REGISTER"));
        Assert.assertTrue(highLight(lnkRegister));
        js.executeScript("arguments[0].click();",lnkRegister);
        expectedResult = "Register: Mercury Tours";
        actualResult = driver.getTitle();
        Assert.assertEquals(actualResult,expectedResult);
        Assert.assertTrue(scrollWindows());

    }

    @Test(priority = 1)
    public void registerAnUser(){
        try {
            WebElement txtFirstName = driver.findElement(By.name("firstName"));
            highLight(txtFirstName);
            txtFirstName.sendKeys("Walter Q");

            WebElement ddlCountry = driver.findElement(By.name("country"));
            highLight(ddlCountry);
            new Select(ddlCountry).selectByVisibleText("AUSTRALIA");

            highLight(driver.findElement(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("walteralex1992@gmail.com");

            highLight(driver.findElement(By.name("password")));
            driver.findElement(By.name("password")).sendKeys("123");

            WebElement txtConfirmPass = driver.findElement(By.name("confirmPassword"));
            highLight(txtConfirmPass);
            txtConfirmPass.sendKeys("123");
            txtConfirmPass.submit();

            Assert.assertTrue(waitForPageToLoad());
            highLight(driver.findElement(By.xpath("//*[contains(text(),'Note:')]")));



        }catch (NoSuchElementException ex){
            Assert.fail("El test fallo");
        }catch (Exception e){
            Assert.fail(e.getMessage());
        }

    }

}
