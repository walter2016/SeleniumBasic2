package org.walter.intermedio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class ParameterByMethod {

    WebDriver driver;

    @BeforeTest
    public void setup(){

        String chromePath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        //
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String baseURL = "https://www.google.com/";
        driver.get(baseURL);
    }

    @AfterTest
    public  void tearDown(){
        driver.quit();
    }

    @Test(dataProvider = "SearchProvider")
    public void testMethodA(String tester,String search) throws InterruptedException {
        WebElement searchText = driver.findElement(By.name("q"));
        searchText.sendKeys(search);
        System.out.println("Welcome -> " + tester + "your search key is -> " + search);
        Thread.sleep(2000);

        String testValue = searchText.getAttribute("value");
        System.out.println("Test value is -> " + testValue + " and is equals to " + search);
        searchText.clear();

        Assert.assertTrue(testValue.equalsIgnoreCase(search));
    }

    @Test(dataProvider = "SearchProvider")
    public void testMethodB(String search) throws InterruptedException {
        WebElement searchText = driver.findElement(By.name("q"));
        searchText.sendKeys(search);
        Thread.sleep(2000);

        String testValue = searchText.getAttribute("value");
        System.out.println("Test value is -> " + testValue + " and is equals to " + search);
        searchText.clear();

        Assert.assertTrue(testValue.equalsIgnoreCase(search));
    }

    @DataProvider(name="SearchProvider")
    public Object[][] getDataFromDataProvider(Method m){
        if(m.getName().equals("testMethodA")){
            return  new  Object[][]{
                    {"Fernando","Google"},
                    {"Jose","Gmail"},
                    {"David","Facebook"},
                    {"Walter","Amazon"}
            };
        }else{
            return  new  Object[][]{
                    {"Mexico"},
                    {"Usa"},
                    {"El Salvador"},
                    {"China"},
                    {"Rusia"}
            };
        }

    }


}
