/*
 * (C) Copyright 2019 Boni Garcia (http://bonigarcia.github.io/)
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
package io.github.bonigarcia.wdm.test.tabs;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Example which opens a new Tab with JavaScript using Chrome as browser.
 *
 * @author Boni Garcia
 * @since 1.0.0
 */
class OpenNewTabUsingJsFirefoxTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new FirefoxDriver();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void test() throws Exception {
        // Open URL in default tab
        driver.get("https://bonigarcia.org/selenium-jupiter/");

        // Open new tab using JavaScript
        ((JavascriptExecutor) driver).executeScript("window.open()");

        // Wait up to 3 seconds to the second tab to be opened
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(numberOfWindowsToBe(2));

        // Switch to new tab
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));

        // Open other URL in second tab
        driver.get("https://bonigarcia.github.io/");
    }

}
