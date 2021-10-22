package Pony_express;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class FLYB_CALC_TEST {
    public RemoteWebDriver driver = null;
    String username = "artcunami";
    String accessKey = "GEgw9pj51Cr89G25mTpkeaiHuVRULl8x9gAnJAcQC8i3GGkmqd";
    String Resulting = "failed";


    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "TESTING_FLYB");
        capabilities.setCapability("name", "FLYB_CALC_TEST");
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("version","94.0");
        capabilities.setCapability("resolution","1920x1080");
        capabilities.setCapability("selenium_version","3.13.0");
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
    @Test(enabled = true, priority = 1)
    public void FLYB_CALC_TEST() throws Exception {
        try {

            driver.get("https://ponyexpress.delivery/lv");

            driver.manage().window().maximize();

            WebElement fromcountry = driver.findElement(By.cssSelector(".form-content__where .custom-select")); //search name
            fromcountry.sendKeys("Latvia");
            Thread.sleep(500);
            fromcountry.sendKeys(Keys.DOWN);
            fromcountry.sendKeys(Keys.RETURN);

            WebElement ZipFrom = driver.findElement(By.id("zip_from"));
            ZipFrom.clear();
            ZipFrom.sendKeys("LV-1009");

            WebElement tocountry = driver.findElement(By.cssSelector(".form-content__destination .custom-select")); //search name
            tocountry.sendKeys("Germany");
            Thread.sleep(500);
            tocountry.sendKeys(Keys.DOWN);
            tocountry.sendKeys(Keys.RETURN);

            WebElement ZipTo = driver.findElement(By.id("zip_to"));
            ZipTo.clear();
            ZipTo.sendKeys("10115");

            WebElement W0 = driver.findElement(By.id("weight_0"));
            W0.sendKeys("0.5");

            WebElement L0 = driver.findElement(By.id("length_0"));
            L0.sendKeys("10");

            WebElement WI0 = driver.findElement(By.id("width_0"));
            WI0.sendKeys("20");

            WebElement H0 = driver.findElement(By.id("height_0"));
            H0.sendKeys("30");

            WebElement Q0 = driver.findElement(By.id("quantity_0"));
            Q0.clear();
            Q0.sendKeys("2");

            driver.findElement(By.id("calculator_btn")).click();

            Thread.sleep(12000);


            driver.findElement(By.xpath("//div[@id='calc-result']/div/div/div/form/div/div[3]/input")).click();

            Thread.sleep(2000);
            String page_url = driver.getCurrentUrl();
            String Substring = "login";
            boolean result = page_url.contains(Substring);
            System.out.println(result);


            if (result) {
                Resulting = "passed";
            } else {
                Resulting = "failed";
            }
            System.out.println(Resulting);

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
