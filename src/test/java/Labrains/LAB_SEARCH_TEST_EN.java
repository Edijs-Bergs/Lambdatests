package Labrains;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LAB_SEARCH_TEST_EN {
    public RemoteWebDriver driver = null;
    String username = "artcunami";
    String accessKey = "GEgw9pj51Cr89G25mTpkeaiHuVRULl8x9gAnJAcQC8i3GGkmqd";
    String Resulting = "failed";

    @BeforeTest
    public void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        Map<String, Object> profile = new HashMap<String, Object>();
        Map<String, Object> contentSettings = new HashMap<String, Object>();

// SET CHROME OPTIONS
// 0 - Default, 1 - Allow, 2 - Block
        contentSettings.put("notifications", 2);
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);
        options.setExperimentalOption("prefs", prefs);

// SET CAPABILITY



        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability("build", "LAB_FINISHED");
        capabilities.setCapability("name", "LAB_SEARCH_TEST_EN");
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("version","99.0");
        capabilities.setCapability("resolution","1920x1080");
        capabilities.setCapability("selenium_version","3.141.59");
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
    public void LAB_SEARCH_TEST_EN(){
        try {
            driver.get("https://labrains.eu/en"); //Go to Store site

            driver.manage().window().maximize(); // Go full page

            WebDriverWait wait = new WebDriverWait(driver,10); //pause

            driver.findElement(By.cssSelector("ul.nav__list.nav__list--end > li.nav__item > span.nav__link.has-subnav")).click(); //Click searsh button
            WebElement Serch = driver.findElement(By.xpath("//input[@id='search_catalog_desc']")); //Search Eko somiņa
            Serch.sendKeys("eco Bag"); //Send string Eko Somiņa
            Thread.sleep(2000); // Wait for sersults
            System.out.println("Waiting for results"); //print value
            driver.findElement(By.xpath("//input[@id='search_catalog_desc']")).sendKeys(Keys.RETURN); //Hit enter key
            System.out.println("Hitting enter"); //print value

            String page_url = driver.getCurrentUrl(); //Get currennt url
            System.out.println(page_url); //print url

            String Substring = "search_product_title=eco+Bag"; //Comparison string
            boolean result = page_url.contains(Substring); //Determine page
            System.out.println(result); //print value
            if (result)
            {
                WebElement Search_Product = driver.findElement(By.xpath("//a[contains(text(),'ECO Cosmetic bag')]")); // Search for product that contains
                String Product = Search_Product.getText(); // Get the value of the
                System.out.println(Product); //Print product string

                String Product_Name = "ECO Cosmetic bag";
                boolean Product_value = Product.contains(Product_Name);
                System.out.println(Product_value);
                if (Product_value)
                {
                    System.out.println("Product found"); //print value
                    Resulting = "passed";
                }
                else
                {
                    System.out.println("There Was no Product"); //print value
                    Resulting = "failed";
                }
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

