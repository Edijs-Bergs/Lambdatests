package lambdatest;

import org.openqa.selenium.JavascriptExecutor;
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

            WebDriverWait wait = new WebDriverWait(driver,5); //pause
            System.out.println("Banner check started");
            boolean smalldialog = driver.findElements(By.xpath("//*[@id=\"small-dialog\"]/button")).size()  == 0;
            //check for element size if element has size of 0 it doesnt exist on the page
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
            System.out.println("Banner check succesfull opening sale itemd");
            driver.findElement(By.xpath("//nav[@id='nav']/div/ul/div/li/a/div")).click(); //Sale item
            driver.findElement(By.cssSelector("#search_block > div:nth-child(1) > div:nth-child(1) > div > div > h4 > a")).click(); //Product select
            System.out.println("product selected");

            WebElement BtnClass = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
            String BtnClassName = BtnClass.getAttribute("class");
            System.out.println(BtnClassName);
            System.out.println("Class name shecked");

            String NotClick = "disable-btn";
            boolean resultNotClick = BtnClassName.contains(NotClick);
            System.out.println(resultNotClick);
            if (resultNotClick)
            {
                driver.navigate().back(); // navigate back
                System.out.println("Went back");
                driver.findElement(By.cssSelector("#search_block > div:nth-child(1) > div:nth-child(2) > div > div > h4 > a")).click(); // Serch for next product
                driver.findElement(By.xpath("//button[2]")).click(); //Add item
                driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click(); // add to cart
                System.out.println("Different item has been added to cart");
            }
            else
            {
                driver.findElement(By.xpath("//button[2]")).click(); //Add item
                driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click(); // add to cart
                System.out.println("added to cart");
            }

            driver.findElement(By.xpath("/html/body/div[1]/header/div[3]/div/ul/li[3]/a")).click(); //Go to cart
            System.out.println("Cart has been opened");
            driver.findElement(By.xpath("//div[@id='cartTable']/div[3]/div/div[4]/a")).click();//Submit order
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
            wait = new WebDriverWait(driver,2); //pause
            Thread.sleep(2000);
            Select dropCounty = new Select(driver.findElement(By.xpath("//select[@id='courier_shipping_region_2']"))); //Select dropdows
            dropCounty.selectByVisibleText("Valga County"); //County
            wait = new WebDriverWait(driver,2); //pause
            Thread.sleep(2000);
            Select dropParish = new Select(driver.findElement(By.xpath("//select[@id='courier_shipping_region_area_2']"))); //Select dropdows
            dropParish.selectByVisibleText("Otep???? Parish"); //Parish
            wait = new WebDriverWait(driver,2); //pause

            WebElement Street = driver.findElement(By.xpath("//input[@id='shipping_address_2']")); //Street
            Street.sendKeys("Palupera tee"); //input street
            wait = new WebDriverWait(driver,2); //pause

            Select dropdowntel2 = new Select(driver.findElement(By.xpath("(//select[@name='shipping[courier][phone_country]'])[2]"))); //Select dropdown
            dropdowntel2.selectByVisibleText("LV +371"); //Select LV index

            WebElement phone2 = driver.findElement(By.xpath("//input[@id='shipping_phone_2']")); //Search phone
            phone2.sendKeys("20000000");

            driver.findElement(By.xpath("//div[@id='unreg_user']/form/div[10]/div/div/div/label/p")).click(); //accept terms

            driver.findElement(By.xpath("(//button[@id='create_order_btn'])[2]")).click();  //submit

            Thread.sleep(2000);
            System.out.println("Test validation");
            String page_url = driver.getCurrentUrl(); //get current url
            String Substring = "popup/pay"; //Sest substring
            boolean result = page_url.contains(Substring); //determine if URL contains substring
            System.out.println(result);
            if (result)
            {
                Resulting = "passed";
                System.out.println("Currect url");
            }
            else
            {
                Resulting = "failed";
                System.out.println("Wrong url");
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
