
package lambdatest; //<your package name>

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
    String username = "edijs.bergs";
    String accessKey = "efXaj9AJ8OqREqFZugLoEJLa5lIn8mNICacqsmNeEdjlmGrvPC";

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
            driver.get("https://fitstore.lt/lt");
            driver.manage().window().maximize();
            driver.findElement(By.xpath("//*[@id=\"nav\"]/div/ul/div/li[1]/a")).click(); //Akcijas preces
            driver.findElement(By.xpath("//*[@id=\"search_block\"]/div[1]/div[2]/div/div/h4/a")).click(); //product link
            driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div[1]/div[3]/div/form/div/div/button[2]")).click(); //plus burtton
            driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div[1]/div[3]/div/form/button")).click();//Add to cart
            driver.findElement(By.xpath("/html/body/div[1]/header/div[3]/div/ul/li[3]/a")).click(); //Go cart
            driver.findElement(By.xpath("//*[@id=\"cartTable\"]/div[4]/a[2]")).click();//Submit order
            driver.findElement(By.xpath("/html/body/div[1]/main/section[1]/div/div[2]/div/div[2]/div[1]/button[2]")).click(); //Not registered
            WebElement orderName = driver.findElement(By.xpath("//*[@id=\"reg_name\"]"));
            orderName.sendKeys("Arturs");
            WebElement orderSName = driver.findElement(By.xpath("//*[@id=\"reg_sname\"]"));
            orderSName.sendKeys("Rasnacis");
            WebElement orderEmail = driver.findElement(By.xpath("//*[@id=\"reg_email\"]"));
            orderEmail.sendKeys("cunami@mailinator.com");
            Select objSelect = new Select(driver.findElement(By.xpath("//*[@id=\"unreg_user\"]/form/div[3]/div[4]/div/div/div[1]/div/div/select")));
            objSelect.selectByValue("AC");




            //driver.findElement(By.xpath("")).click();



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /*@AfterClass
    public void tearDown() throws Exception {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }*/
}