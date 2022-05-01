package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestScenario3 {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
    	 String username = "kanagalanareshbabu";
         String authkey = "yPkOlyeuoI53UfquQKmvXf88QjpMQmdvd1iEGifG1E4XyRZp5n";
//        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
//        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 11");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("name", m.getName() + " - " + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);

    }

    @Test
    public void basicTest() throws InterruptedException {
        String spanText;
        System.out.println("Loading Url");
        driver.get("https://www.lambdatest.com/selenium-playground/");
  
        driver.findElement(By.linkText("Input Form Submit")).click();
        Thread.sleep(150);
        driver.findElement(By.cssSelector(".btn.selenium_btn")).click();
        Thread.sleep(5000);

//        String text =driver.findElement(By.xpath("//*[contains(text(),'out this field')]")).getText();       
//        Assert.assertEquals("Please fill out this field", text);
        
        driver.findElement(By.id("name")).sendKeys("Naresh");
        driver.findElement(By.id("inputEmail4")).sendKeys("kanagala.nareshbabu@sutherlandglobal.com");
        driver.findElement(By.id("inputPassword4")).sendKeys("Abc@123");
        driver.findElement(By.id("company")).sendKeys("sutherland global");
        driver.findElement(By.id("websitename")).sendKeys("www.sutherlandglobal.com");
//        {
//          WebElement dropdown = driver.findElement(By.name("country"));
//          dropdown.findElement(By.xpath("//option[. = 'United States']")).click();
//        }
        WebElement country = driver.findElement(By.name("country"));
        Select s = new Select(country);
        s.selectByVisibleText("United States");
        driver.findElement(By.id("inputCity")).sendKeys("hyderabad");
        driver.findElement(By.id("inputAddress1")).sendKeys("hyderabad");
        driver.findElement(By.id("inputAddress2")).sendKeys("kukatpally");
        driver.findElement(By.id("inputState")).sendKeys("TS");
        driver.findElement(By.id("inputZip")).sendKeys("523201");
        driver.findElement(By.cssSelector(".btn")).click();
      
        spanText =driver.findElement(By.cssSelector(".success-msg")).getText();       
        Assert.assertEquals("Thanks for contacting us, we will get back to you shortly.", spanText);
        Status = "passed";
        Thread.sleep(150);
        System.out.println("TestFinished");
       
    }

    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}