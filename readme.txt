Setting up the tests.

=================================================================

Before:
Install latest Java JDK
Using Lambdatest to register all tests
1.	Log in to your Lambdatest account.
a.	Login:
i.	E-mail art@cunami.lv
ii.	Pass Maskavas127
2.	Find username and key in lambdatest site
a.	Site: https://accounts.lambdatest.com
3.	Go to Automation tab and in the upper right corner there is a key icon. By clicking on it you can see your username and Key to use test automation.

=================================================================

Setup for tests:
1.	All selenium tests are created in Java language Using Junit Framework.
2.	Java tests can be written In any Java IDE Software, But these tests were made Using “Intellij IDEA”
3.	Open Intellij – then file → new → project
4.	A new dialog opens then on the left select project type as maven.
5.	When project created go to the pom.xml file to set up dependencies an set them up.
a.	Usable dependencies for java testing just paste them in pom.xml file
<dependencies>
        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>3.141.59</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.testng/testng -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.4.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.codehaus.mojo/animal-sniffer-annotations -->


    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M3</version>
            </plugin>
        </plugins>
    </build>

=================================================================

Selenium test creation:
1.	Expand src folder then expand test folder ten right click folder that is named as your project name.
2.	Select new java class name it as you want.
3.	Tests structure:
package {your package name}
import {all needed libraries}
Public class {your class name}
public RemoteWebDriver driver = null;
String username = "artcunami"; {Lambdatest username}
String accessKey = "GEgw9pj51Cr89G25mTpkeaiHuVRULl8x9gAnJAcQC8i3GGkmqd"; {Lambdatest key}
String Resulting = "failed"; {Default test status if some test steps aren’t completed}
@BeforeTest {Seting up the environment before every test}
…
@Test {Selenium tests}
…
@AfterClass {Marking test status & finishing the test}
public void tearDown() throws Exception {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + Resulting);
            driver.quit();
        }
    }

=================================================================

Things that may be needed to import for tests:
1.	import org.openqa.selenium.JavascriptExecutor;
2.	import org.openqa.selenium.WebElement;
3.	import org.openqa.selenium.By;

4.	import org.openqa.selenium.support.ui.ExpectedConditions;
5.	import org.openqa.selenium.support.ui.Select;
6.	import org.openqa.selenium.support.ui.WebDriverWait;

7.	import org.openqa.selenium.remote.DesiredCapabilities;
8.	import org.openqa.selenium.remote.RemoteWebDriver;

9.	import org.testng.annotations.BeforeTest;
10.	import org.testng.annotations.AfterClass;
11.	import org.testng.annotations.Test;

12.	import java.net.MalformedURLException;
13.	import java.net.URL;
14.	import java.util.concurrent.TimeUnit;

15.	import junit.framework.TestResult;

=================================================================

@BeforeTest setup
	Before section can be setup using Lambdatest tool that creates dependencies at
(https://www.lambdatest.com/capabilities-generator/)
1.	@BeforeTest example
2.	@BeforeTest
a.	public void setUp() throws Exception {
b.	        DesiredCapabilities capabilities = new DesiredCapabilities();
c.	        capabilities.setCapability("build", "First Test");
d.	        capabilities.setCapability("name", "FIT_EE_REGISTERED");
e.	        capabilities.setCapability("platform", "Windows 10");
f.	        capabilities.setCapability("browserName", "Chrome");
g.	        capabilities.setCapability("version","94.0");
h.	        capabilities.setCapability("resolution","1920x1080");
i.	        capabilities.setCapability("selenium_version","3.13.0");
j.	        capabilities.setCapability("console",true);
k.	        capabilities.setCapability("network",true);
l.	        capabilities.setCapability("visual",true);
m.	        capabilities.setCapability("geoLocation","LV");
n.	        capabilities.setCapability("chrome.driver","93.0");
o.
p.	        try {
q.	            driver= new RemoteWebDriver(new URL("https://"+username+":"+accessKey+"@hub.lambdatest.com/wd/hub"), capabilities);
r.	        } catch (MalformedURLException e) {
s.	            System.out.println("Invalid grid URL");
t.	        }
u.	    }
Try section needs to be exactly like this to set up a workable driver for testing

=================================================================

@Test section example {Place where you write the test}
	@Test(enabled = true, priority = 1)
    public void FIT_EE_REG() throws Exception {
        try {

            driver.get("https://fitstore.ee/");
            driver.manage().window().maximize();

            driver.findElement(By.xpath("//header/nav/div/ul/div/li/a/div")).click(); //Sale items
            driver.findElement(By.cssSelector(".col-xl-3:nth-child(2) .product__content a")).click(); //Spring
            driver.findElement(By.xpath("//button[2]")).click(); //Add item
            driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click(); //Add to cart
            driver.findElement(By.cssSelector(".menu-tablet-desktop > .menu__item--cart .menu__icon")).click(); //Go to cart
            driver.findElement(By.xpath("//a[contains(@href, 'https://fitstore.ee/checkout')]")).click(); //to checkout

            WebElement Log_email = driver.findElement(By.xpath("//input[@id='email']")); // find email
            Log_email.sendKeys("cunami@mailinator.com"); //Send keys
            WebElement Log_Pass = driver.findElement(By.xpath("//input[@id='password']"));// find pass
            Log_Pass.sendKeys("Maskavas127"); //send keys

            driver.findElement(By.xpath("//form[@id='login-form']/button")).click(); // login
            driver.findElement(By.cssSelector(".menu-tablet-desktop > .menu__item--cart .menu__icon")).click(); //Go to cart
            driver.findElement(By.xpath("//a[contains(@href, 'https://fitstore.ee/checkout')]")).click(); //to checkout

            driver.findElement(By.xpath("//div[2]/label")).click(); //Select paysera
            driver.findElement(By.xpath("//div[@id='delivery_courier--wrapper']/label")).click(); //Delliver by courier

            Select dropdownCountry = new Select(driver.findElement(By.xpath("//select[@id='courier_shipping_country_3']"))); //Select dropdows
            dropdownCountry.selectByVisibleText("Estonia"); //Select lithuania

            WebElement Zip_code = driver.findElement(By.xpath("//input[@id='shipping_zip_3']")); //Find zip
            Zip_code.sendKeys("75704"); //input zip

            WebElement City = driver.findElement(By.xpath("//input[@id='shipping_city_3']")); //City
            City.sendKeys("Tallin"); //input city

            Select dropcounty = new Select(driver.findElement(By.xpath("//select[@id='courier_shipping_region_3']"))); //Select dropdown
            dropcounty.selectByVisibleText("Valga County"); //County

            Select dropParish = new Select(driver.findElement(By.xpath("//select[@id='courier_shipping_region_area_3']"))); //Select dropdown
            dropParish.selectByVisibleText("Valga Parish"); //parish

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

=================================================================

An example of a full test: Elements can be found using locators like (By.id,By.name,By.cssSelector,By.xpath)
best option to use is By.id if you cannot use By.id then use By.Xpath
Tutorial for element identification is on (https://www.browserstack.com/guide/findelement-in-selenium)


package lambdatest;

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

public class FIT_EE_REGISTERED {

    public RemoteWebDriver driver = null;
    String username = "artcunami";
    String accessKey = "GEgw9pj51Cr89G25mTpkeaiHuVRULl8x9gAnJAcQC8i3GGkmqd";
    String Resulting = "failed";

    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build", "First Test");
        capabilities.setCapability("name", "FIT_EE_REGISTERED");
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
    public void FIT_EE_REG() throws Exception {
        try {

            driver.get("https://fitstore.ee/"); //All tests should start with going to the testing page
            driver.manage().window().maximize(); //Maximizing browser

            driver.findElement(By.xpath("//header/nav/div/ul/div/li/a/div")).click(); //Sale items (driver.findelement is the way you find a specific element)
            driver.findElement(By.cssSelector(".col-xl-3:nth-child(2) .product__content a")).click(); //Spring
            driver.findElement(By.xpath("//button[2]")).click(); //Add item
            driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click(); //Add to cart
            driver.findElement(By.cssSelector(".menu-tablet-desktop > .menu__item--cart .menu__icon")).click(); //Go to cart
            driver.findElement(By.xpath("//a[contains(@href, 'https://fitstore.ee/checkout')]")).click(); //to checkout

            WebElement Log_email = driver.findElement(By.xpath("//input[@id='email']")); // find email to input key in an element you need to find it first and asign it a variable
            Log_email.sendKeys("cunami@mailinator.com"); //Send keys is a way to input keys in the eement
            WebElement Log_Pass = driver.findElement(By.xpath("//input[@id='password']"));// find pass
            Log_Pass.sendKeys("Maskavas127"); //send keys

            driver.findElement(By.xpath("//form[@id='login-form']/button")).click(); // login
            driver.findElement(By.cssSelector(".menu-tablet-desktop > .menu__item--cart .menu__icon")).click(); //Go to cart
            driver.findElement(By.xpath("//a[contains(@href, 'https://fitstore.ee/checkout')]")).click(); //to checkout

            driver.findElement(By.xpath("//div[2]/label")).click(); //Select paysera
            driver.findElement(By.xpath("//div[@id='delivery_courier--wrapper']/label")).click(); //Delliver by courier

            Select dropdownCountry = new Select(driver.findElement(By.xpath("//select[@id='courier_shipping_country_3']"))); //Select dropdows
            dropdownCountry.selectByVisibleText("Estonia"); //Select lithuania

            WebElement Zip_code = driver.findElement(By.xpath("//input[@id='shipping_zip_3']")); //Find zip
            Zip_code.sendKeys("75704"); //input zip

            WebElement City = driver.findElement(By.xpath("//input[@id='shipping_city_3']")); //City
            City.sendKeys("Tallin"); //input city

            Select dropcounty = new Select(driver.findElement(By.xpath("//select[@id='courier_shipping_region_3']"))); //Select dropdown
            dropcounty.selectByVisibleText("Valga County"); //County

            Select dropParish = new Select(driver.findElement(By.xpath("//select[@id='courier_shipping_region_area_3']"))); //Select dropdown
            dropParish.selectByVisibleText("Valga Parish"); //parish

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
