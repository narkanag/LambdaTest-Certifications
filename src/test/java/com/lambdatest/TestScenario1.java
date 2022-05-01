package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestScenario1 {

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
        caps.setCapability("platform", "MacOS Catalina");
        caps.setCapability("browserName", "Safari");
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
        driver.findElement(By.linkText("Simple Form Demo")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.lambdatest.com/selenium-playground/simple-form-demo");
        String welcometext="Welcome to LamdaTest";
        driver.findElement(By.id("user-message")).sendKeys(welcometext);
        driver.findElement(By.cssSelector("#showInput")).click();
        spanText =driver.findElement(By.id("message")).getText();        
        Assert.assertEquals("Welcome to LamdaTest", spanText); 
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