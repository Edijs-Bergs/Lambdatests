
package lambdatest; //<your package name>


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;

public class Lambdatest {

    public RemoteWebDriver driver = null;
    String username = "artcunami";
    String accessKey = "GEgw9pj51Cr89G25mTpkeaiHuVRULl8x9gAnJAcQC8i3GGkmqd";

    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "First Test");
        capabilities.setCapability("name", "Sample Test");
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

    @Test(enabled = true)
    public void testScript() throws Exception {
        try {
            driver.get("https://fitnesaveikals.lv/");
            driver.manage().window().maximize();
            driver.findElement(By.xpath("//*[@id=\"nav\"]/div/ul/div/li[1]/a")).click(); //Akcijas preces
            driver.findElement(By.xpath("//*[@id=\"search_block\"]/div[1]/div[1]/div/div/h4/a")).click(); //product link
            driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div[1]/div[3]/div/form/div/div/button[2]")).click(); //plus button
            driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div[1]/div[3]/div/form/button")).click();//Add to cart
            driver.findElement(By.xpath("/html/body/div[1]/header/div[3]/div/ul/li[3]/a")).click(); //Go cart
            driver.findElement(By.xpath("//*[@id=\"cartTable\"]/div[4]/a[2]")).click();//Submit order
            driver.findElement(By.xpath("/html/body/div[1]/main/section[1]/div/div[2]/div/div[2]/div[1]/button[2]")).click(); //Not registered
            WebElement orderName = driver.findElement(By.id("reg_name")); //search Name input
            orderName.sendKeys("Arturs"); //keys name
            WebElement orderSName = driver.findElement(By.id("reg_sname")); //Search Sname input
            orderSName.sendKeys("Rasnacis"); //Keys sname
            WebElement orderEmail = driver.findElement(By.id("reg_email")); //search Email
            orderEmail.sendKeys("cunami@mailinator.com"); //Input email
            WebElement phone = driver.findElement(By.id("reg_tel")); //Search phone
            phone.sendKeys("20000000"); //send phone
            driver.findElement(By.xpath("//*[@id=\"unreg_user\"]/form/div[6]/div[1]/div[1]/div[4]/label")).click(); //Find pay at store


            driver.findElements(By.id("terms_2")).get(1).click();



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @AfterClass
    public void tearDown() throws Exception {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=passed");
            driver.quit();
        }
    }
}