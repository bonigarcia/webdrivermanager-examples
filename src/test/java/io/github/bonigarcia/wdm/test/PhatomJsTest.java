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

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Test with PhantomJS.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class PhatomJsTest {

    private WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.phantomjs().setup();
    }

    @BeforeEach
    public void setupTest() {
        driver = new PhantomJSDriver();
    }

    @AfterAll
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        // Your test code here. For example:
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        By searchInput = By.id("searchInput");
        wait.until(presenceOfElementLocated(searchInput));
        driver.findElement(searchInput).sendKeys("Software");
        By searchButton = By.id("searchButton");
        wait.until(elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();

        wait.until(textToBePresentInElementLocated(By.tagName("body"),
                "Computer software"));
    }

}
