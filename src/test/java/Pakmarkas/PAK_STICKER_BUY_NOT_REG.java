package Pakmarkas;

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


public class PAK_STICKER_BUY_NOT_REG {
    public RemoteWebDriver driver = null;
    String username = "artcunami";
    String accessKey = "GEgw9pj51Cr89G25mTpkeaiHuVRULl8x9gAnJAcQC8i3GGkmqd";
    String Resulting = "failed";


    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "Finished_PAK");
        capabilities.setCapability("name", "PAK_NOT_REG");
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
    public void PAK_NOT_REG() throws Exception {
        try {

            driver.get("https://www.stensan.com/en");

            driver.manage().window().maximize();

            driver.findElement(By.xpath("//form[@id='store-first-step-form']/div[2]/div/div/div[2]/label[2]/div/div/img")).click(); //Shape cut

            WebElement xinput = driver.findElement(By.xpath("//*[@id=\"store-first-step-form\"]/div[2]/div[1]/div[1]/div[4]/div[2]/div[2]/div/div/input"));
            xinput.clear();
            xinput.sendKeys("60");
            WebElement yinput = driver.findElement(By.xpath("//*[@id=\"store-first-step-form\"]/div[2]/div[1]/div[1]/div[4]/div[2]/div[1]/div/div/input"));
            yinput.clear();
            yinput.sendKeys("70");

            driver.findElement(By.xpath("//button[@id='store-first-step']")).click();
            driver.findElement(By.xpath("//div[@id='use_custom_size_block']/div/div/label")).click();
            driver.findElement(By.xpath("//button[@id='store-first-step']")).click();
            Thread.sleep(2000);

            Select dropdownmaterial = new Select(driver.findElement(By.cssSelector("#material"))); //Select dropdown
            dropdownmaterial.selectByVisibleText("Vellum matte"); //Select material
            Select dropdownadhesive = new Select(driver.findElement(By.xpath("//select[@name='adhesive']"))); //select dropdown
            dropdownadhesive.selectByVisibleText("Standard"); //Select adhesive
            Select dropdowndiameter = new Select(driver.findElement(By.xpath("//select[@id='core_inner_diameter']"))); //select dropdown
            dropdowndiameter.selectByVisibleText("40 mm"); //select diameter
            Select dropdownrolling = new Select(driver.findElement(By.xpath("//select[@name='rolling']"))); //select dropdown
            dropdownrolling.selectByVisibleText("Inner"); //select rolling

            driver.findElement(By.xpath("//button[@id='store-second-step']")).click(); //2nd submit
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(".btn--lg")).click();
            Thread.sleep(2000);

            WebElement name = driver.findElement(By.xpath("//input[@name='user[name]']")); //search name
            name.clear();
            name.sendKeys("Arturs"); //input name
            WebElement sname = driver.findElement(By.xpath("//input[@name='user[surname]']"));  //search sname
            sname.clear();
            sname.sendKeys("Rasnacis"); //input sname
            WebElement email = driver.findElement(By.xpath("//input[@name='user[email]']")); //search email
            email.clear();
            email.sendKeys("cunami@mailinator.com"); //inpun email
            WebElement tel = driver.findElement(By.xpath("//input[@name='user[tel]']"));  //search tel
            tel.clear();
            tel.sendKeys("20000000"); //input tel

            driver.findElement(By.xpath("//label[contains(.,'Paysera')]")).click(); //paysera
            driver.findElement(By.xpath("//div[@id='delivery_free--wrapper']/label")).click(); //In office
            driver.findElement(By.xpath("//*[@id=\"confirm_order_btn\"]")).click(); //next step
            driver.findElement(By.xpath("//label[contains(.,'I agree Purchase rules, Privacy Policy and Delivery policy')]")).click();// terms
            driver.findElement(By.xpath("//*[@id=\"confirm_order_btn\"]")).click(); //Submit order

            Thread.sleep(2000);
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
