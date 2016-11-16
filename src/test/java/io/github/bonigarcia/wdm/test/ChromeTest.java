/*
 * (C) Copyright 2016 Boni Garcia (http://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.github.bonigarcia.wdm.test;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.ChromeDriverManager;

/**
 * Test with Chrome.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class ChromeTest {

  private WebDriver driver;

  @BeforeClass
  public static void setupClass() {
    ChromeDriverManager.getInstance().setup();
  }

  @Before
  public void setupTest() {
    driver = new ChromeDriver();
  }

  @After
  public void teardown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  public void test() {
    // Your test code here. For example:
    WebDriverWait wait = new WebDriverWait(driver, 30); // 30 seconds of timeout
    driver.get("https://en.wikipedia.org/wiki/Main_Page"); // navigate to Wikipedia

    By searchInput = By.id("searchInput"); // search for "Software"
    wait.until(ExpectedConditions.presenceOfElementLocated(searchInput));
    driver.findElement(searchInput).sendKeys("Software");
    By searchButton = By.id("searchButton");
    wait.until(ExpectedConditions.elementToBeClickable(searchButton));
    driver.findElement(searchButton).click();

    wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"),
        "Computer software")); // assert that the resulting page contains a text
  }

}
