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

package io.github.bonigarcia.wdm.test.ng;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Test with TestNG in parallel.
 *
 * @author Boni Garcia
 * @since 1.0.0
 */
class TestNgFirefoxTest {

    static final Logger log = getLogger(lookup().lookupClass());

    @BeforeTest(alwaysRun = true)
    void beforeTest() {
        log.debug("TestNgFirefoxTest @BeforeEachTest");
        WebDriverManager.firefoxdriver().clearResolutionCache().forceDownload()
                .setup();
    }

    @Test
    void testFirefox() {
        log.debug("TestNgFirefoxTest @Test");
        assertThat(System.getProperty("webdriver.gecko.driver"))
                .contains("geckodriver");
    }

}
