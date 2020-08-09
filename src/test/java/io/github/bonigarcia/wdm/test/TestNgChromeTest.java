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

import static java.lang.invoke.MethodHandles.lookup;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Test with TestNG in parallel.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class TestNgChromeTest {

    static final Logger log = getLogger(lookup().lookupClass());

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        log.debug("TestNgChromeTest @BeforeTest");
        WebDriverManager.chromedriver().config().setAvoidAutoReset(true);
        WebDriverManager.chromedriver().clearResolutionCache().forceDownload()
                .setup();
    }

    @Test
    public void testChrome() {
        log.debug("TestNgChromeTest @Test");
        assertThat(System.getProperty("webdriver.chrome.driver"),
                containsString("chromedriver"));
    }

}
