package lambdatest;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class FIT_LT_REGISTERED {

    public RemoteWebDriver driver = null;
    String username = "artcunami";
    String accessKey = "GEgw9pj51Cr89G25mTpkeaiHuVRULl8x9gAnJAcQC8i3GGkmqd";
    String Resulting = "failed";

    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "Finished_FIT");
        capabilities.setCapability("name", "FIT_LT_REGISTERED");
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
    public void FIT_LT_REG(){
        try {

            driver.get("https://fitstore.lt/");

            driver.manage().window().maximize();

            Thread.sleep(2000);

            /*boolean SmallDialog = driver.findElement(By.xpath("//*[@id=\"small-dialog\"]/button")).isDisplayed();
            if (SmallDialog)
            {
                driver.findElement(By.xpath("//*[@id=\"small-dialog\"]/button")).click();
            }*/

            /*boolean BotBanner = driver.findElement(By.xpath("//*[@id=\"bottom-banner-id\"]/span")).isDisplayed();
            if (BotBanner)
            {
                driver.findElement(By.xpath("//*[@id=\"bottom-banner-id\"]/span")).click();
            }*/

            driver.findElement(By.xpath("//header/nav/div/ul/div/li/a/div")).click(); //Sale items

            driver.findElement(By.cssSelector(".col-xl-3:nth-child(7) .product__content a")).click(); //2nd product

            WebElement BtnClass = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
            String BtnClassName = BtnClass.getAttribute("class");
            System.out.println(BtnClassName);

            String NotClick = "disable-btn";
            boolean resultNotClick = BtnClassName.contains(NotClick);
            System.out.println(resultNotClick);
            if (resultNotClick)
            {
                driver.navigate().back();
                driver.findElement(By.cssSelector(".col-xl-3:nth-child(8) .product__content a")).click();
                System.out.println("Went back");
                driver.findElement(By.xpath("//button[2]")).click(); //Add item
                driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click(); // add to cart
            }
            else
            {
                driver.findElement(By.xpath("//button[2]")).click(); //Add item
                driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click(); // add to cart
                System.out.println("added to cart");
            }

            driver.findElement(By.xpath("//img[contains(@src,'https://fitstore.lt/themes/fitnesaveikals/assets/img/cart.svg')]")).click(); //Go to cart
            driver.findElement(By.xpath("//a[contains(@href, 'https://fitstore.lt/checkout')]")).click(); //to checkout

            WebElement Log_email = driver.findElement(By.xpath("//input[@id='email']")); // find email
            Log_email.sendKeys("cunami@mailinator.com"); //Send keys
            WebElement Log_Pass = driver.findElement(By.xpath("//input[@id='password']"));// find pass
            Log_Pass.sendKeys("Maskavas127"); //send keys

            driver.findElement(By.xpath("//form[@id='login-form']/button")).click(); // login
            driver.findElement(By.xpath("//img[contains(@src,'https://fitstore.lt/themes/fitnesaveikals/assets/img/cart.svg')]")).click(); //Go to cart
            driver.findElement(By.xpath("//a[contains(@href, 'https://fitstore.lt/checkout')]")).click(); //to checkout

            driver.findElement(By.xpath("//div[2]/label")).click(); //Select paysera
            driver.findElement(By.xpath("//div[@id='delivery_courier--wrapper']/label")).click(); //Delliver by courier

            Select dropdownCountry = new Select(driver.findElement(By.xpath("//select[@id='courier_shipping_country_3']"))); //Select dropdows
            dropdownCountry.selectByVisibleText("Lithuania"); //Select lithuania

            WebElement Zip_code = driver.findElement(By.xpath("//input[@id='shipping_zip_3']")); //Find zip
            Zip_code.sendKeys("LT-00000"); //input zip

            WebElement City = driver.findElement(By.xpath("//input[@id='shipping_city_3']")); //City
            City.sendKeys("Vilnus"); //input city

            WebElement Street = driver.findElement(By.xpath("//input[@id='shipping_address_3']")); //Street
            Street.sendKeys("Ringuvos"); //input street

            Select dropdowntel2 = new Select(driver.findElement(By.xpath("//select[@name='shipping[courier][phone_country]']"))); //Select dropdown
            dropdowntel2.selectByVisibleText("LV +371"); //Select LV index

            WebElement phone2 = driver.findElement(By.xpath("//input[@id='shipping_phone_3']")); //Search phone
            phone2.sendKeys("20000000");

            driver.findElement(By.cssSelector(".col-lg-6:nth-child(1) .checkbox__label")).click(); //accept terms

            driver.findElement(By.xpath("//button[@id='create_order_btn']")).click();  //submit

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
