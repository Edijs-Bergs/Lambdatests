package Labrains;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
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

public class LAB_CATEGORY_TEST_LV {
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
        capabilities.setCapability("name", "LAB_CATEGORY_TEST_LV");
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
    public void LAB_CATEGORY_TEST_LV(){
        try {
            driver.get("https://labrains.eu/lv"); //Go to Store site

            driver.manage().window().maximize(); // Go full page

            WebDriverWait wait = new WebDriverWait(driver,10); //pause

            Actions a = new Actions(driver);
            WebElement move = driver.findElement(By.xpath("//a[contains(text(),'VEIKALS')]"));
            a.moveToElement(move).perform();
            wait = new WebDriverWait(driver,2); //pause
            move = driver.findElement(By.xpath("//a[contains(text(),'LABRIGHTS')]"));
            a.moveToElement(move).click().perform();

            String page_url = driver.getCurrentUrl(); //Get currennt url
            System.out.println(page_url); //print url

            String Substring = "labrights"; //Comparison string
            boolean result = page_url.contains(Substring); //Determine page
            System.out.println(result); //print value
            if (result) {
                WebElement Search_Product = driver.findElement(By.xpath("//h1[contains(text(),'LABRIGHTS')]"));
                String Product = Search_Product.getText(); // Get the value of the
                System.out.println(Product); //Print product string

                String Test_String = "LABRIGHTS";
                boolean Element_On_Page = Product.contains(Test_String);
                System.out.println(Element_On_Page);
                if (Element_On_Page)
                {
                    move = driver.findElement(By.xpath("//a[contains(text(),'VEIKALS')]"));
                    a.moveToElement(move).perform();
                    wait = new WebDriverWait(driver,2); //pause
                    move = driver.findElement(By.xpath("//a[contains(text(),'??das atjauno??ana')]"));
                    a.moveToElement(move).click().perform();

                    String page_url2 = driver.getCurrentUrl(); //Get currennt url
                    System.out.println(page_url2); //print url

                    String Substring2 = "adas-atjaunosana"; //Comparison string
                    boolean result2 = page_url2.contains(Substring2); //Determine page
                    System.out.println(result2); //print value
                    if (result2) {
                        WebElement Search_Product2 = driver.findElement(By.xpath("//h1[contains(text(),'??das atjauno??ana')]"));
                        String Product2 = Search_Product2.getText(); // Get the value of the
                        System.out.println(Product2); //Print product string

                        String Test_String2 = "??DAS ATJAUNO??ANA";
                        boolean Element_On_Page2 = Product2.contains(Test_String2);
                        System.out.println(Element_On_Page2);
                        if (Element_On_Page2)
                        {
                            Resulting = "passed";
                        }
                        else
                        {
                            Resulting = "failed";
                        }
                    }
                    else
                    {
                        Resulting = "failed";
                    }
                }
                else
                {
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