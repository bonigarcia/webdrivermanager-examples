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

import static org.openqa.selenium.OutputType.BASE64;
import static org.openqa.selenium.firefox.FirefoxOptions.FIREFOX_OPTIONS;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * WebRTC test with remote Firefox.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
@Ignore
public class WebRtcRemoteFirefoxTest {

    private WebDriver driver;

    @Before
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("media.navigator.permission.disabled", true);
        options.addPreference("media.navigator.streams.fake", true);
        capabilities.setCapability(FIREFOX_OPTIONS, options);

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
                capabilities);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        driver.get(
                "https://webrtc.github.io/samples/src/content/devices/input-output/");
        String screenshotBase64 = ((TakesScreenshot) driver)
                .getScreenshotAs(BASE64);
        System.err.println("data:image/png;base64," + screenshotBase64);
    }

}
