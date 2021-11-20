/*
 * (C) Copyright 2017 Boni Garcia (https://bonigarcia.github.io/)
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
package io.github.bonigarcia.wdm.test.multiple;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Test with Chrome and Firefox browsers.
 *
 * @author Boni Garcia
 * @since 1.0.0
 */
class ChromeAndFirefoxTest {

    protected WebDriver chrome;

    protected WebDriver firefox;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void setupTest() {
        chrome = new ChromeDriver();
        firefox = new FirefoxDriver();
    }

    @AfterEach
    void teardown() {
        if (chrome != null) {
            chrome.quit();
        }
        if (firefox != null) {
            firefox.quit();
        }
    }

    @Test
    void test() {
        // Test data
        int timeout = 30;
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";

        // Implicit timeout
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        firefox.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

        // Open page in different browsers
        chrome.get(sutUrl);
        firefox.get(sutUrl);

        // Assertion
        assertThat(chrome.getTitle()).isEqualTo(firefox.getTitle());
    }

}
