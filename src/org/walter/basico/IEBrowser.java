package org.walter.basico;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class IEBrowser {

    static String chromePath = System.getProperty("user.dir") + "\\drivers\\IEDriverServer.exe";

    @Test
    public void IEBrowser(){
        System.setProperty("webdriver.ie.driver", chromePath);
        //System.getProperty("webdriver.ie.driver", "C:\\Users\\Walter Quintanilla\\Documents\\JarSelenium\\IEDriverServer_Win32_3.150.1");

        WebDriver driver = new InternetExplorerDriver();
        driver.get("http://www.google.com");
        driver.quit();
    }
}
