package org.walter.reports;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    static WebDriver driver;
    static String chromePath = System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";

    public static  WebDriver getDriver(){
        if(driver== null){
            System.setProperty("webdriver.chrome.driver", chromePath);
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;

    }

    public static void takeScreenShot(WebDriver driver, String fileWithPath) throws IOException {
        TakesScreenshot scrShot = (TakesScreenshot)driver;
        File scrFile = scrShot.getScreenshotAs(OutputType.FILE);
        File destFile= new File(fileWithPath);
        FileUtils.copyFile(scrFile,destFile);
    }

    public static void sendPdfReportByEmail(String from, String pass,String to,String subject,String body){
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.user",from);
        props.put("mail.smtp.password",pass);
        props.put("mail.smtp.port","25"); //25,465,587
        props.put("mail.smt.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try{
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject(subject);
            message.setText(body);
            BodyPart objMessageBodyPart = new MimeBodyPart();
            Multipart multipar = new MimeMultipart();
            objMessageBodyPart.setText(body);
            multipar.addBodyPart(objMessageBodyPart);
            objMessageBodyPart= new MimeBodyPart();

            String filename = System.getProperty("user.dir") + "\\SeleniumBasico.pdf";


            DataSource source = new FileDataSource(filename);
            objMessageBodyPart.setDataHandler(new DataHandler(source));
            objMessageBodyPart.setFileName(filename);
            multipar.addBodyPart(objMessageBodyPart);
            
            message.setContent(multipar);
            Transport  transport = session.getTransport("smtp");

            transport.connect(host,from,pass);

            transport.sendMessage(message,message.getAllRecipients());
            System.out.println("Se envio el correo");
            transport.close();


        } catch (AddressException e) {
            System.err.println("Problema con Email Andress" + e.getMessage());

        } catch (MessagingException me) {
           // System.err.println("Error de conexion" + e.getMessage());
            me.printStackTrace();
        }

    }
}
