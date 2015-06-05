package org.automation.test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class addResource {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeTest
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://172.20.208.174:4042/admin";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test(priority = 3)
  public void testAddResourceSelenium() throws Exception {
	String nameRoom = "ResourceTest01";
	
	driver.get(baseUrl + "/#/login");
    
    (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button")));
    driver.findElement(By.xpath("//button")).click();
    
    (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Resources")));
    driver.findElement(By.linkText("Resources")).click();
   
    (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/button")));
    driver.findElement(By.xpath("//div/div/button")).click();
   
    (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.col-xs-12 > div.row")));
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys(nameRoom);
    driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys(nameRoom);
    driver.findElement(By.cssSelector("button.info")).click();
    
    (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/button")));
    driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
    
    (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")));
    driver.findElement(By.xpath("//input[@type='text']")).clear();
    
    (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")));
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys(nameRoom);
    //System.out.println(nameRoom);
    
    (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.ng-scope > span.ng-binding")));
    assertEquals(nameRoom, driver.findElement(By.cssSelector("div.ng-scope > span.ng-binding")).getText());
    //assertEquals(driver.findElement(By.xpath("//input[@type='text']")).getText(), driver.findElement(By.cssSelector("div.ng-scope > span.ng-binding")).getText());
  }

  @AfterTest
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
