package org.walter.basico;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class EdgeBrowser {

    static String chromePath = System.getProperty("user.dir") + "\\drivers\\msedgedriver.exe";

    @Test
    public void EdgeBrowser(){
        System.setProperty("webdriver.edge.driver", chromePath);
        //System.getProperty("webdriver.ie.driver", "C:\\Users\\Walter Quintanilla\\Documents\\JarSelenium\\IEDriverServer_Win32_3.150.1");

        WebDriver driver = new EdgeDriver();
        driver.get("http://www.google.com");
        driver.close();
    }

}
