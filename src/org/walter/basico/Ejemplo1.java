package org.walter.basico;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejemplo1 {
    public static void main(String[] args){
        //instanciar un objeto de tipo Web driver

        WebDriver driver;

        //Declaracion de variables.

        String baseURL = "http://www.newtours.demoaut.com/";
        String actualResult = "";
        String expectedResult = "Welcome: Mercury Tours";

        //Indicar la localizacion de archivo

        //System.getProperty("user.dir") =

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

        //abrir el navegador.

        driver = new ChromeDriver();

        driver.get(baseURL);

        actualResult=driver.getTitle();
        System.out.println(actualResult.contentEquals(expectedResult)?"Prueba Exitosa:\n" + actualResult: "Prueba Fallida");

        driver.close();


    }


}
