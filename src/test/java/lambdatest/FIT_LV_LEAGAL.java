
package lambdatest; //<your package name>


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class FIT_LV_LEAGAL {

    public RemoteWebDriver driver = null;
    String username = "artcunami";
    String accessKey = "GEgw9pj51Cr89G25mTpkeaiHuVRULl8x9gAnJAcQC8i3GGkmqd";
    String Resulting = "failed";

    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "Finished_FIT");
        capabilities.setCapability("name", "FIT_LV_LEAGAL");
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
    public void FIT_LV_LEAGAL(){
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

            driver.findElement(By.xpath("//nav[@id='nav']/div/ul/div/li/div/div/a/div")).click(); //Akcijas preces

            driver.findElement(By.xpath("//*[@id=\"filter\"]/div[4]/div/label")).click(); // filter stock
            wait = new WebDriverWait(driver,7); //pause

            driver.findElement(By.cssSelector(".col-xl-3:nth-child(1) .product__content a")).click(); //2nd product

            WebElement BtnClass = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
            String BtnClassName = BtnClass.getAttribute("class");
            System.out.println(BtnClassName);

            String NotClick = "disable-btn";
            boolean resultNotClick = BtnClassName.contains(NotClick);
            System.out.println(resultNotClick);
            if (resultNotClick)
            {
                driver.navigate().back();
                driver.findElement(By.cssSelector(".col-xl-3:nth-child(2) .product__content a")).click();
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

            driver.findElement(By.xpath("/html/body/div[1]/header/div[3]/div/ul/li[3]/a")).click(); //Go to cart
            System.out.println("Cart has been opened");
            driver.findElement(By.xpath("/html/body/div[1]/header/div[3]/div/ul/li[3]/a")).click(); //Go cart
            driver.findElement(By.xpath("//*[@id=\"cartTable\"]/div[4]/a[2]")).click();//Submit order
            driver.findElement(By.xpath("/html/body/div[1]/main/section[1]/div/div[2]/div/div[2]/div[1]/button[2]")).click(); //Not registered
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
            WebElement Town = driver.findElement(By.id("reg_jur_town")); //search Town
            Town.sendKeys("Rīga"); //keys name
            WebElement orderName = driver.findElement(By.id("reg_name")); //search Name input
            orderName.sendKeys("Arturs"); //keys name
            WebElement orderSName = driver.findElement(By.id("reg_sname")); //Search Sname input
            orderSName.sendKeys("Rasnacis"); //Keys sname
            WebElement orderEmail = driver.findElement(By.id("reg_email")); //search Email
            orderEmail.sendKeys("cunami@mailinator.com"); //Input email
            WebElement phone = driver.findElement(By.id("reg_tel")); //Search phone
            phone.sendKeys("20000000"); //send phone

            driver.findElement(By.xpath("//*[@id=\"unreg_user\"]/form/div[6]/div[1]/div[1]/div[2]/label")).click(); //Find pay at store
            driver.findElement(By.cssSelector(".row:nth-child(9) #delivery_free--wrapper > .radio__label")).click(); //In Office
            driver.findElement(By.cssSelector(".row:nth-child(13) .checkbox__label")).click(); //terms accept
            driver.findElement(By.xpath("//div[@id='unreg_user']/form/div[11]/button")).click(); //Submit

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