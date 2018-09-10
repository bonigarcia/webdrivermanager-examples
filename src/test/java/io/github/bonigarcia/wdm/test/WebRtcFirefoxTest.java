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

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * WebRTC test with Firefox.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class WebRtcFirefoxTest {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Before
    public void setupTest() {
        FirefoxOptions options = new FirefoxOptions();

        // This flag avoids granting the access to the camera
        options.addPreference("media.navigator.permission.disabled", true);

        // This flag force to use fake user media (synthetic video of multiple
        // color)
        options.addPreference("media.navigator.streams.fake", true);

        driver = new FirefoxDriver(options);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() throws InterruptedException {
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
