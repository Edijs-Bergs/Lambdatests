package lambdatest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class FIT_LV_SERCH_TEST {
    public RemoteWebDriver driver = null;
    String username = "artcunami";
    String accessKey = "GEgw9pj51Cr89G25mTpkeaiHuVRULl8x9gAnJAcQC8i3GGkmqd";
    String Resulting = "failed";
    String[] Products = {"Mad Max", "Elastiska josta", "Ironmax Pudele", "aerobikas hanetele", "Spiky Massage"};
    char ch = '-';

    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "Finished_FIT");
        capabilities.setCapability("name", "FIT_LV_SERCH_TEST");
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




    @Test()
    public void FIT_LV_SERCH_TEST(){
        try {

            driver.get("https://fitnesaveikals.lv/");
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(driver,2); //pause

            boolean smalldialog = driver.findElements(By.xpath("//*[@id=\"small-dialog\"]/button")).size()  == 0;
            if (smalldialog != true)
            {
                driver.findElement(By.xpath("//*[@id=\"small-dialog\"]/button")).click();
                System.out.println("Big banner was there");
            }
            else
            {
                System.out.println("Big banner wasn't there");
            }

            boolean botbanner = driver.findElements(By.xpath("//*[@id=\"bottom-banner-id\"]/span")).size() == 0;
            if (botbanner != true)
            {
                driver.findElement(By.xpath("//*[@id=\"bottom-banner-id\"]/span")).click();
                System.out.println("Bottom banner was there");
            }
            else
            {
                System.out.println("Big banner wasn't there");
            }

            int idx = new Random().nextInt(Products.length);
            String S_Product = (Products[idx]);
            System.out.println(S_Product);
            String S_Term = S_Product.replaceAll(" ", "+");
            System.out.println(S_Term);



            WebElement Serch = driver.findElement(By.xpath("//input[@id='search_catalog']")); // Find Search
            Serch.sendKeys(S_Product); //Send keys
            driver.findElement(By.xpath("//input[@id='search_catalog']")).sendKeys(Keys.RETURN); //Hit enter key

            wait = new WebDriverWait(driver,2); //pause

            String page_url = driver.getCurrentUrl(); //Get currennt url
            System.out.println(page_url); //print url

            String Substring = "search_product_title=".concat(S_Term); //Comparison string
            boolean result = page_url.contains(Substring); //Determine page
            System.out.println(result); //print value

            if (result)
            {
                WebElement Search_Product = driver.findElement(By.xpath("//a[contains(text(),'"+S_Product+"')]"));
                System.out.println(Search_Product);
                Resulting = "passed";
            }
            else
            {
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
