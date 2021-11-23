package org.walter.listeners;


import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(RealTimeReport.class)
public class TestRealReport {

    @Test
    public void oneTest(){
        Assert.assertTrue(true);
    }

    @Test
    public void twoTest(){
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "twoTest")
    public void threeTest(){
        Assert.assertTrue(true);
    }



}
