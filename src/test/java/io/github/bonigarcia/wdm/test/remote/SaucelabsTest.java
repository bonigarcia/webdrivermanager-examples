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

package io.github.bonigarcia.wdm.test.remote;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;

/**
 * Test with Saucelabs.
 *
 * @author Boni Garcia
 * @since 1.0.0
 */
@Disabled
class SaucelabsTest {

    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;

    static final String USERNAME = "YOUR_USERNAME";
    static final String ACCESS_KEY = "YOUR_ACCESS_KEY";
    static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY
            + "@ondemand.saucelabs.com:443/wd/hub";

    @BeforeEach
    void setupTest() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("platform", "Windows 10");
        options.setCapability("version", "52.0");

        driver = new RemoteWebDriver(new URL(URL), options);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void test() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
