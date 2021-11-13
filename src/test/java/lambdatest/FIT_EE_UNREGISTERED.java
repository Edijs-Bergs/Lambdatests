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

public class FIT_EE_UNREGISTERED {

    public RemoteWebDriver driver = null;
    String username = "artcunami";
    String accessKey = "GEgw9pj51Cr89G25mTpkeaiHuVRULl8x9gAnJAcQC8i3GGkmqd";
    String Resulting = "failed";

    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "Finished_FIT");
        capabilities.setCapability("name", "FIT_EE_NOT_REGISTERED");
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
    public void FIT_EE_NOT_REG(){
        try {

            driver.get("https://fitstore.ee/");

            driver.manage().window().maximize();

            Thread.sleep(2000);

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

            driver.findElement(By.xpath("//header/nav/div/ul/div/li/a/div")).click(); //Sale items

            driver.findElement(By.cssSelector(".col-xl-3:nth-child(9) .product__content a")).click(); //2nd product

            WebElement BtnClass = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
            String BtnClassName = BtnClass.getAttribute("class");
            System.out.println(BtnClassName);

            String NotClick = "disable-btn";
            boolean resultNotClick = BtnClassName.contains(NotClick);
            System.out.println(resultNotClick);
            if (resultNotClick)
            {
                driver.navigate().back();
                driver.findElement(By.cssSelector(".col-xl-3:nth-child(10) .product__content a")).click();
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

            driver.findElement(By.xpath("//img[contains(@src,'https://fitstore.ee/themes/fitnesaveikals/assets/img/cart.svg')]")).click(); //Go to cart
            driver.findElement(By.xpath("//a[contains(@href, 'https://fitstore.ee/checkout')]")).click(); //to checkout
            driver.findElement(By.xpath("(//button[@type='button'])[4]")).click(); // Unregistered

            WebElement orderName = driver.findElement(By.id("reg_name")); //search Name input
            orderName.sendKeys("Arturs"); //keys name

            WebElement orderSName = driver.findElement(By.id("reg_sname")); //Search Sname input
            orderSName.sendKeys("Rasnacis"); //Keys sname

            WebElement orderEmail = driver.findElement(By.id("reg_email")); //search Email
            orderEmail.sendKeys("cunami@mailinator.com"); //Input email

            Select dropdowntel = new Select(driver.findElement(By.xpath("//select[@name='user[phone_country]']"))); //Select dropdows
            dropdowntel.selectByVisibleText("LV +371"); //Select LV index

            WebElement phone = driver.findElement(By.id("reg_tel")); //Search phone
            phone.sendKeys("20000000");

            driver.findElement(By.xpath("//div[6]/div/div/div[2]/label")).click(); //Select paysera
            driver.findElement(By.xpath("(//div[@id='delivery_courier--wrapper']/label)[2]")).click(); //Delliver by courier

            Select dropdownCountry = new Select(driver.findElement(By.xpath("//select[@id='courier_shipping_country_2']"))); //Select dropdows
            dropdownCountry.selectByVisibleText("Estonia"); //Select lithuania

            WebElement Zip_code = driver.findElement(By.xpath("//input[@id='shipping_zip_2']")); //Find zip
            Zip_code.sendKeys("76704"); //input zip

            WebElement City = driver.findElement(By.xpath("//input[@id='shipping_city_2']")); //City
            City.sendKeys("Adra"); //input city

            Select dropCounty = new Select(driver.findElement(By.xpath("//select[@id='courier_shipping_region_2']"))); //Select dropdows
            dropCounty.selectByVisibleText("Valga County"); //County

            Select dropParish = new Select(driver.findElement(By.xpath("//select[@id='courier_shipping_region_area_2']"))); //Select dropdows
            dropParish.selectByVisibleText("Otepää Parish"); //Parish

            WebElement Street = driver.findElement(By.xpath("//input[@id='shipping_address_2']")); //Street
            Street.sendKeys("Palupera tee"); //input street

            Select dropdowntel2 = new Select(driver.findElement(By.xpath("(//select[@name='shipping[courier][phone_country]'])[2]"))); //Select dropdown
            dropdowntel2.selectByVisibleText("LV +371"); //Select LV index

            WebElement phone2 = driver.findElement(By.xpath("//input[@id='shipping_phone_2']")); //Search phone
            phone2.sendKeys("20000000");

            driver.findElement(By.xpath("//*[@id=\"unreg_user\"]/form/div[9]/div/div/div/label/p")).click(); //accept terms

            driver.findElement(By.xpath("(//button[@id='create_order_btn'])[2]")).click();  //submit

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
