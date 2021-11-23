package org.walter.basico;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejemplo2 {

    public static void main(String[] args){
        //instanciar un objeto de tipo Web driver

        WebDriver driver;

        //Declaracion de variables.

        String baseURL = "http://localhost/";
        String actualResult = "";
        String expectedResult = "Sistema Clinico";

        //Indicar la localizacion de archivo

        //System.getProperty("user.dir") =

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

        //abrir el navegador.

        driver = new ChromeDriver();

        driver.get(baseURL);

        //actualResult=driver.getTitle();
        //System.out.println(actualResult.contentEquals(expectedResult)?"Prueba Exitosa:\n" + actualResult: "Prueba Fallida");

        driver.manage().window().maximize();

        driver.findElement(By.linkText("INGRESAR")).click();

       // driver.close();


    }
}
