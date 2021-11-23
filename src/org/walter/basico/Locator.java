package org.walter.basico;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locator {

    public static void main(String[] args){
        //instanciar un objeto de tipo Web driver

        WebDriver driver;

        //Declaracion de variables.

        String baseURL = "http://live.demoguru99.com/index.php/checkout/cart";
        String actualResult = "";
        String expectedResult = "$615.00";


        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

        //abrir el navegador.

        driver = new ChromeDriver();

        driver.get(baseURL);

        driver.manage().window().maximize();

        driver.findElement(By.linkText("TV")).click();

        driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[1]/div/div[3]/button/span/span")).click();
        actualResult = driver.findElement(By.cssSelector("#shopping-cart-table>tbody>tr>td.product-cart-total>span>span")).getText();

        if(actualResult.contentEquals(expectedResult)){
            System.out.println("Prueba Exitosa" + actualResult);
        }else
        {System.out.println("Prueba Fallo");}


        driver.close();
    }
}
