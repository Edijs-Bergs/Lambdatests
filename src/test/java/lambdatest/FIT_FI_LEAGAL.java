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

public class FIT_FI_LEAGAL {

    public RemoteWebDriver driver = null;
    String username = "artcunami";
    String accessKey = "GEgw9pj51Cr89G25mTpkeaiHuVRULl8x9gAnJAcQC8i3GGkmqd";
    String Resulting = "failed";

    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "Finished_FIT");
        capabilities.setCapability("name", "FIT_FI_LEAGAL");
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
    public void FIT_FI_LEAG(){
        try {

            driver.get("https://fitstore.fi/");
            driver.manage().window().maximize();

            /*Thread.sleep(2000);

            boolean SmallDialog = driver.findElement(By.xpath("//*[@id=\"small-dialog\"]/button")).isDisplayed();
            if (SmallDialog)
            {
                driver.findElement(By.xpath("//*[@id=\"small-dialog\"]/button")).click();
            }

            boolean BotBanner = driver.findElement(By.xpath("//*[@id=\"bottom-banner-id\"]/span")).isDisplayed();
            if (BotBanner)
            {
                driver.findElement(By.xpath("//*[@id=\"bottom-banner-id\"]/span")).click();
            }*/

            driver.findElement(By.xpath("//nav[@id='nav']/div/ul/li[6]/a")).click(); //Sports

            driver.findElement(By.cssSelector(".col-xl-4:nth-child(23) .product__content a")).click(); //Bottle

            WebElement BtnClass = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
            String BtnClassName = BtnClass.getAttribute("class");
            System.out.println(BtnClassName);

            String NotClick = "disable-btn";
            boolean resultNotClick = BtnClassName.contains(NotClick);
            System.out.println(resultNotClick);
            if (resultNotClick)
            {
                driver.navigate().back();
                driver.findElement(By.cssSelector(".col-xl-4:nth-child(24) .product__content a")).click();
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

            driver.findElement(By.xpath("//img[contains(@src,'https://fitstore.fi/themes/fitstore-fi/assets/img/cart.svg')]")).click(); //Go to cart
            driver.findElement(By.xpath("//div[@id='cartTable']/div[4]/a[2]")).click(); //to checkout
            driver.findElement(By.xpath("(//button[@type='button'])[4]")).click(); // Unregistered
            driver.findElement(By.xpath("//*[@id=\"unreg_user\"]/form/div[1]/div[2]/label")).click(); //Leagal person


            WebElement Company = driver.findElement(By.id("reg_jur_company")); //search Company name
            Company.sendKeys("SIA CUNAMI WEB"); //keys name
            WebElement Index = driver.findElement(By.id("reg_jur_zip")); //search index
            Index.sendKeys("LV-5134"); //keys name
            WebElement Adress = driver.findElement(By.id("reg_jur_reg_address")); //search Adress
            Adress.sendKeys("Maskavas iela 127, LV-1003"); //keys name
            WebElement RegNumber = driver.findElement(By.id("reg_jur_reg_nr")); //search Ren number
            RegNumber.sendKeys("40103996361"); //keys name
            WebElement TaxNumber = driver.findElement(By.id("reg_jur_tax_nr")); //search Tax Number
            TaxNumber.sendKeys("LV40103996361"); //keys name
            Select dropdownCountry = new Select(driver.findElement(By.xpath("//*[@id=\"reg_jur_country\"]"))); //Select Country
            dropdownCountry.selectByVisibleText("Latvia"); //Select LV
            WebElement Town = driver.findElement(By.id("reg_jur_town")); //search Town
            Town.sendKeys("Rīga"); //keys name
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

            driver.findElement(By.xpath("//div[@id='unreg_user']/form/div[6]/div/div/div[2]/label")).click(); //Select paysera
            driver.findElement(By.xpath("(//div[@id='delivery_fitstore_fi_free--wrapper']/label)[2]")).click(); //Delliver by courier

            driver.findElement(By.xpath("//div[@id='unreg_user']/form/div[13]/div/div/div/label/p")).click(); //accept terms

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

