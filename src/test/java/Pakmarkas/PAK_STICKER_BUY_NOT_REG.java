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

            //go to the page
            driver.get("https://www.stensan.com/en");
            //maximize the window
            driver.manage().window().maximize();
            //start the test steps
            driver.findElement(By.xpath("//*[@id=\"page\"]/div[1]/div[1]/div/div")).click(); //acept cookies
            System.out.println("Cookies accepted");
//            driver.findElement(By.xpath("//*[@id=\"header_bottom_id\"]/div/div/div/div[2]/ul/li[1]/a")).click(); //start login
//            System.out.println("Went to login page");
//
//            WebElement logemail = driver.findElement(By.xpath("//*[@id=\"login_email\"]"));
//            logemail.sendKeys("cunami@mailinator.com"); //input login email
//            WebElement logpass = driver.findElement(By.xpath("//*[@id=\"login_pswd\"]"));
//            logpass.sendKeys("Maskavas127"); // input password
//            System.out.println("Credentials input");
//            driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[4]/button")).click(); //submit the login info
//            System.out.println("Submit succesfull");
//            Thread.sleep( 2000);

            driver.findElement(By.xpath("//*[@id=\"page\"]/section[1]/div/div/div[1]/div[4]/a[2]")).click(); //open sticker select
            System.out.println("Sticker selection initiated");
            Thread.sleep( 1000);


            driver.findElement(By.xpath("//form[@id='store-first-step-form']/div[2]/div/div/div[2]/label[2]/div/div/img")).click(); //choose the Shape cut

            WebElement xinput = driver.findElement(By.xpath("//*[@id=\"store-first-step-form\"]/div[2]/div[1]/div[1]/div[4]/div[2]/div[2]/div/div/input"));
            xinput.clear(); // clear input feild
            xinput.sendKeys("60"); // send dimention
            WebElement yinput = driver.findElement(By.xpath("//*[@id=\"store-first-step-form\"]/div[2]/div[1]/div[1]/div[4]/div[2]/div[1]/div/div/input"));
            yinput.clear(); // clear input feild
            yinput.sendKeys("70"); // send dimention

            driver.findElement(By.xpath("//button[@id='store-first-step']")).click(); //submit values

            Thread.sleep( 500);

            driver.findElement(By.xpath("//div[@id='use_custom_size_block']/div/div/label")).click(); //accept coustum sizes
            driver.findElement(By.xpath("//button[@id='store-first-step']")).click(); //submit values
            Thread.sleep(2000);

            System.out.println("Shape and cut was set up");

            Select dropdownmaterial = new Select(driver.findElement(By.cssSelector("#material"))); //Select dropdown
            dropdownmaterial.selectByVisibleText("Vellum matte"); //Select material
            Select dropdownadhesive = new Select(driver.findElement(By.xpath("//select[@name='adhesive']"))); //select dropdown
            dropdownadhesive.selectByVisibleText("Standard"); //Select adhesive
            Select dropdowndiameter = new Select(driver.findElement(By.xpath("//select[@id='core_inner_diameter']"))); //select dropdown
            dropdowndiameter.selectByVisibleText("40 mm"); //select diameter
            Select dropdownrolling = new Select(driver.findElement(By.xpath("//select[@name='rolling']"))); //select dropdown
            dropdownrolling.selectByVisibleText("Outer"); //select rolling
            Select dropdownoutdiameter = new Select(driver.findElement(By.id("outer_diameter"))); //select dropdown
            dropdownoutdiameter.selectByVisibleText("100 mm"); //select rollin

            System.out.println("Additional sticker info was selected");



            driver.findElement(By.xpath("//button[@id='store-second-step']")).click(); //2nd submit
            System.out.println("First step is submitted");
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(".btn--lg")).click();
            Thread.sleep(2000);

            System.out.println("Shipping options");
            WebElement Name = driver.findElement(By.xpath("//input[@name='user[name]']"));
            Name.sendKeys("Arturs"); //input login email
            WebElement SName = driver.findElement(By.xpath("//input[@name='user[surname]']"));
            SName.sendKeys("Rasnacis"); // input password
            WebElement logemail = driver.findElement(By.xpath("//input[@name='user[email]']"));
            logemail.sendKeys("cunami@mailinator.com"); //input login email
            WebElement logtel = driver.findElement(By.xpath("//input[@name='user[tel]']"));
            logtel.sendKeys("+37120000000"); // input password
            System.out.println("Credentials input");
            driver.findElement(By.xpath("//label[contains(.,'Paysera')]")).click(); //paysera
            driver.findElement(By.xpath("//div[@id='delivery_free--wrapper']/label")).click(); //In office
            driver.findElement(By.xpath("//*[@id=\"confirm_order_btn\"]")).click(); //next step
            Thread.sleep( 2000);

            System.out.println("Purchache confirmation");






            driver.findElement(By.xpath("//label[contains(.,'I agree Purchase rules, Privacy Policy and Delivery policy')]")).click();// terms accept
            driver.findElement(By.xpath("//*[@id=\"confirm_order_btn\"]")).click(); //Submit order

            System.out.println("Test validaton");
            Thread.sleep(4000);
            String page_url = driver.getCurrentUrl(); //get current url
            String Substring = "popup/pay/paywindow/"; //validation string
            boolean result = page_url.contains(Substring); //determine if current url contains validation string
            System.out.println(result); //print out the result of validation
            // mark the test based on the result value
            if (result)
            {Resulting = "passed";}
            else
            {Resulting = "failed";}
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
