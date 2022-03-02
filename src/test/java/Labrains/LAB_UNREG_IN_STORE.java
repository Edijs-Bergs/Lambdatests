package Labrains;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LAB_UNREG_IN_STORE {

    public RemoteWebDriver driver = null;
    String username = "artcunami";
    String accessKey = "GEgw9pj51Cr89G25mTpkeaiHuVRULl8x9gAnJAcQC8i3GGkmqd";
    String Resulting = "failed";

    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "LAB_FINISHED");
        capabilities.setCapability("name", "LAB_UNREG_IN_STORE");
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("version","94.0");
        capabilities.setCapability("resolution","1920x1080");
        capabilities.setCapability("selenium_version","3.141.59");
        capabilities.setCapability("console",true);
        capabilities.setCapability("network",true);
        capabilities.setCapability("visual",true);
        capabilities.setCapability("geoLocation","LV");
        capabilities.setCapability("chrome.driver","93.0");

        try {
            driver= new RemoteWebDriver(new URL("https://"+username+":"+accessKey+"@hub.lambdatest.com/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        }
    }




    @Test()
    public void LAB_UNREG_IN_STORE(){
        try {

            driver.get("https://labrains.eu/en");

            driver.manage().window().maximize();

            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[contains(@href, 'https://labrains.eu/en/category/1/shop')]")).click(); // click shop in nav bar
            driver.findElement(By.xpath("//*[@id=\"cookiesAlert\"]/div/div/div[2]/button[1]")).click(); //Accept cookies
            driver.findElement(By.cssSelector(".col-xl-3:nth-child(1) .btn > span")).click(); // Click on 1st product
            driver.findElement(By.cssSelector(".radio:nth-child(3) > .radio__label")).click(); //first radio
            driver.findElement(By.xpath("(//button[@type='button'])[4]")).click(); //plus button
            driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click(); //Add to cart
            driver.findElement(By.xpath("//a[contains(@href, 'https://labrains.eu/en/cart')]")).click(); //Open cart
            driver.findElement(By.xpath("//div[@id='cartTable']/div[4]/a[2]")).click(); //BTN costomize
            WebElement Name = driver.findElement(By.xpath("//input[@name='user[name]']")); //Web element Name find
            Name.sendKeys("Arturs"); //Send name
            WebElement SName = driver.findElement(By.xpath("//input[@name='user[surname]']")); //Web element S-Name find
            SName.sendKeys("Rasnacis"); //Send S-name
            WebElement Email = driver.findElement(By.xpath("//input[@name='user[email]']")); //Web element Email find
            Email.sendKeys("cunami@mailinator.com"); //Send Email
            WebElement Tel = driver.findElement(By.xpath("//input[@name='user[tel]']")); //Web element Phone find
            Tel.sendKeys("20000000"); //Send name
            System.out.println("Credentials have been input");
            driver.findElement(By.xpath("//label[contains(.,'Payment upon pickup')]")).click(); // paysera
            driver.findElement(By.xpath("//*[@id=\"delivery_free--wrapper\"]/label")).click(); // dpd locker
            System.out.println("Recive Info selected");
            Thread.sleep( 2000);
            driver.findElement(By.xpath("//*[@id=\"confirm_order_btn\"]")).click(); // Click order btn
            System.out.println("Clicked Next step");
            WebDriverWait wait = new WebDriverWait(driver,40);
//
            WebElement GetDelivery = driver.findElement(By.id("shipping_service_label"));
            String Delivery = GetDelivery.getText();
            System.out.println(Delivery);

            String NotClick = "At Labrains office";
            boolean DeliverMethod = Delivery.contains(NotClick);
            System.out.println(DeliverMethod);
            if (DeliverMethod)
            {
                WebElement element = driver.findElement(By.xpath("//*[@id=\"terms\"]"));
                JavascriptExecutor js =(JavascriptExecutor)driver;
                js.executeScript("arguments[0].click();", element);
                System.out.println("Terms Accepted");
                driver.findElement(By.xpath("//*[@id=\"confirm_order_btn\"]")).click();
                System.out.println("Purchase created");

                Thread.sleep(2000);
                String page_url = driver.getCurrentUrl();
                System.out.println(page_url);

                String Substring = "labrains.eu/en/cart-done";
                boolean result = page_url.contains(Substring);
                System.out.println(result);
                if (result)
                {
                    Resulting = "passed";
                }
                else
                {
                    Resulting = "failed";
                }
                System.out.println(Resulting);
            }
            else
            {
                Resulting = "failed";
                System.out.println("Wasnt  pickup in store");

            }

//            Thread.sleep(2000);
//
//            WebElement GetDelivery = driver.findElement(By.id("shipping_service_label"));
//            String Delivery = GetDelivery.getText();
//            System.out.println(Delivery);
//
//            String NotClick = "DPD Pickup point";
//            boolean DeliverMethod = Delivery.contains(NotClick);
//            System.out.println(DeliverMethod);
//            if (DeliverMethod)
//            {
//                WebElement element = driver.findElement(By.xpath("//*[@id=\"terms\"]"));
//                JavascriptExecutor js =(JavascriptExecutor)driver;
//                js.executeScript("arguments[0].click();", element);
//                System.out.println("Terms Accepted");
//                driver.findElement(By.xpath("//*[@id=\"confirm_order_btn\"]")).click();
//                System.out.println("Purchase created");
//
//                Thread.sleep(2000);
//                String page_url = driver.getCurrentUrl();
//                System.out.println(page_url);
//
//                String Substring = "/popup/pay";
//                boolean result = page_url.contains(Substring);
//                System.out.println(result);
//                if (result)
//                {
//                   Resulting = "passed";
//               }
//                else
//               {
//                  Resulting = "failed";
//               }
//                System.out.println(Resulting);
//            }
//            else
//            {
//                Resulting = "failed";
//                System.out.println("Dellivery wasnt DPD");
//
//            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + Resulting);
            driver.quit();
        }
    }
}
