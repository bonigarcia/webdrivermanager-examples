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

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Test for WebRTC with Firefox in Selenium Grid.
 *
 * @author Boni Garcia
 * @since 1.0.0
 */
@Disabled
class RemoteWebRtcFirefoxTest {

    WebDriver driver;

    @BeforeEach
    void setupTest() throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("media.navigator.permission.disabled", true);
        profile.setPreference("media.navigator.streams.fake", true);
        capability.setCapability(FirefoxDriver.PROFILE, profile);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
                capability);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void test() throws InterruptedException {
        // Test data
        int timeout = 30;
        String sutUrl = "https://webrtc.github.io/samples/src/content/devices/input-output/";

        // Implicit timeout
        driver.manage().timeouts().implicitlyWait(timeout, SECONDS);
        driver.manage().timeouts().implicitlyWait(timeout, SECONDS);

        // Open page
        driver.get(sutUrl);

        // Wait 5 seconds
        sleep(5000);
    }

}
