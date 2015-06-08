package org.automation.test;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class removeLocation {
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

  @Test(priority = 2)
  public void testRemoveLocationSelenium() throws Exception {
    driver.get(baseUrl + "/#/login");
    driver.findElement(By.xpath("//button")).click();
    
    (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Locations")));
    driver.findElement(By.linkText("Locations")).click();
    
    (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
    driver.findElement(By.cssSelector("input.ngSelectionCheckbox")).click();
    
    (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[2]")));
    driver.findElement(By.xpath("//button[2]")).click();
    
    (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h3")));
    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
   
    (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.ng-binding.ng-scope")));
    assertEquals("Location "+driver.findElement(By.xpath("//div[@id='locationGrid']/div[2]/div/div/div[3]/div[2]/div/span")).getText()+" sucessfully removed", driver.findElement(By.cssSelector("div.ng-binding.ng-scope")).getText());

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

/*bug*/
