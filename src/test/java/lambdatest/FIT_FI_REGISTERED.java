package lambdatest;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import junit.framework.TestResult;

public class FIT_FI_REGISTERED {

    public RemoteWebDriver driver = null;
    String username = "artcunami";
    String accessKey = "GEgw9pj51Cr89G25mTpkeaiHuVRULl8x9gAnJAcQC8i3GGkmqd";
    String Resulting = "failed";

    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "First Test");
        capabilities.setCapability("name", "FIT_FI_REGISTERED");
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
    public void FIT_FI_REG() throws Exception {
        try {

            driver.get("https://fitstore.fi/");
            driver.manage().window().maximize();

            driver.findElement(By.xpath("//nav[@id='nav']/div/ul/li[6]/a")).click(); //Sports
            driver.findElement(By.cssSelector(".col-xl-4:nth-child(5) .product__content a")).click(); //Bottle
            driver.findElement(By.xpath("//button[2]")).click(); //Add item
            driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click(); //Add to cart
            driver.findElement(By.xpath("//img[contains(@src,'https://fitstore.fi/themes/fitstore-fi/assets/img/cart.svg')]")).click(); //Go to cart
            driver.findElement(By.xpath("//div[@id='cartTable']/div[4]/a[2]")).click(); //to checkout
            //driver.findElement(By.xpath("(//button[@type='button'])[4]")).click(); // Unregistered

            WebElement Log_email = driver.findElement(By.xpath("//input[@id='email']")); // find email
            Log_email.sendKeys("cunami@mailinator.com"); //Send keys
            WebElement Log_Pass = driver.findElement(By.xpath("//input[@id='password']"));// find pass
            Log_Pass.sendKeys("Maskavas127"); //send keys

            driver.findElement(By.xpath("//form[@id='login-form']/button")).click(); //login

            driver.findElement(By.xpath("//img[contains(@src,'https://fitstore.fi/themes/fitstore-fi/assets/img/cart.svg')]")).click(); //Go to cart
            driver.findElement(By.xpath("//div[@id='cartTable']/div[4]/a[2]")).click(); //to checkout







            driver.findElement(By.xpath("//div[2]/label")).click(); //Select paysera
            driver.findElement(By.xpath("//div[@id='delivery_fitstore_fi_free--wrapper']/label")).click(); //Delliver by courier



            driver.findElement(By.xpath("//div[8]/div/div/div/label/p")).click(); //accept terms

            driver.findElement(By.xpath("//button[@id='create_order_btn']")).click();  //submit

            //Tab switching
            //ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
            //switch to new tab
            //driver.switchTo().window(newTb.get(0));

            String page_url = driver.getCurrentUrl();
            String Substring = "popup/pay";
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
