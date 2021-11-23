package org.walter.basico;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class FBrowser {
    static String chromePath = System.getProperty("user.dir") + "\\drivers\\geckodriver.exe";

    @Test
    public void IEBrowser(){
        System.setProperty("webdriver.gecko.driver", chromePath);
        //System.getProperty("webdriver.ie.driver", "C:\\Users\\Walter Quintanilla\\Documents\\JarSelenium\\IEDriverServer_Win32_3.150.1");

        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.com");
        driver.close();
    }


}
