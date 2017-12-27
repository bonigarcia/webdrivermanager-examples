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

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Test with Chrome in Selenium Grid.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class EusTest {

    private WebDriver driver;

    @Before
    public void setupTest() throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(new URL("http://localhost:8040/eus/v1"),
                capability);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() throws Exception {
        int testTime = 2;
        for (int i = 0; i < testTime; i++) {
            if (i % 2 == 0) {
                driver.get("http://elastest.io/");
            } else {
                driver.get(
                        "https://github.com/elastest/elastest-user-emulator-service");
            }
            String title = driver.getTitle();
            System.out.println(title);

            Thread.sleep(1000);
        }
    }

}
