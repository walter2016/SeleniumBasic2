package org.walter.intermedio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNG {
    String baseURL = "http://www.newtours.demoaut.com/index.php";
    WebDriver driver;
    String chromePath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
    String expectedResult = "";
    String actualResult= "";

    @BeforeTest
    public void launchBrowser()
    {
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void verifyHomePageTitle(){
        expectedResult = "Welcome: Mercury Tours";
        actualResult = driver.getTitle();

        Assert.assertEquals(actualResult,expectedResult,"Title is not equals");

    }

    @AfterMethod
    public void goBackToHomePage(){
        driver.findElement(By.linkText("Home")).click();
    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }


    @Test
    public void register(){
        driver.findElement(By.linkText("REGISTER")).click();
        expectedResult = "Register: Mercury Tours";
        actualResult = driver.getTitle();
        Assert.assertEquals(actualResult,expectedResult,"Title is not equals");
    }

    @Test
    public void support(){
        driver.findElement(By.linkText("SUPPORT")).click();
        expectedResult = "Under Construction: Mercury Tours";
        actualResult = driver.getTitle();
        Assert.assertEquals(actualResult,expectedResult,"Title is not equals");
    }



}
