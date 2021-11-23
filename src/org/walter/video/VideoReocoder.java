package org.walter.video;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.monte.media.VideoFormatKeys.*;

public class VideoReocoder {
    private ScreenRecorder screenRecorder;

    public void stopRecording() throws IOException {
        this.screenRecorder.stop();
    }

    public void startRecording(String videoPath) throws IOException, AWTException {
        File file = new File(videoPath);
        Dimension screentSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = screentSize.width;
        int height = screentSize.height;

        Rectangle captureSize = new Rectangle(0,0,width,height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        this.screenRecorder = new SpecializedScreenRecorder(gc,captureSize,
                new Format(MediaTypeKey,MediaType.FILE,MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey,MediaType.VIDEO,EncodingKey,ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey,24,FrameRateKey, Rational.valueOf(15),
                QualityKey,1.0f,KeyFrameIntervalKey, 15*60),
                new Format(MediaTypeKey,MediaType.VIDEO,EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                null, file, "ScreenRecoder");

        this.screenRecorder.start();
    }


    @Test
    public void videoTest() throws IOException, AWTException {
        VideoReocoder vr = new VideoReocoder();
        String chromePath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromePath);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        vr.startRecording(System.getProperty("user.dir") + "\\video\\");

        driver.get("https://www.google.com");

        WebElement txtSearch = driver.findElement(By.name("q"));
        txtSearch.sendKeys("Tester");
        txtSearch.submit();
        System.out.println("El titulo de la pgina es" + driver.getTitle());
        driver.quit();
        vr.stopRecording();
    }




}
