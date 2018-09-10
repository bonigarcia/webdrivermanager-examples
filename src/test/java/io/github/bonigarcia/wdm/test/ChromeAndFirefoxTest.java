/*
 * (C) Copyright 2017 Boni Garcia (http://bonigarcia.github.io/)
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

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Test with Chrome and Firefox browsers.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class ChromeAndFirefoxTest {

    protected WebDriver chrome;
    protected WebDriver firefox;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @Before
    public void setupTest() {
        chrome = new ChromeDriver();
        firefox = new FirefoxDriver();
    }

    @After
    public void teardown() {
        if (chrome != null) {
            chrome.quit();
        }
        if (firefox != null) {
            firefox.quit();
        }
    }

    @Test
    public void test() {
        // Test data
        int timeout = 30;
        String sutUrl = "https://en.wikipedia.org/wiki/Main_Page";

        // Implicit timeout
        chrome.manage().timeouts().implicitlyWait(timeout, SECONDS);
        firefox.manage().timeouts().implicitlyWait(timeout, SECONDS);

        // Open page in different browsers
        chrome.get(sutUrl);
        firefox.get(sutUrl);

        // Assertion
        assertEquals(chrome.getTitle(), firefox.getTitle());
    }

}
