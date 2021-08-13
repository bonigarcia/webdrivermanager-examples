/*
 * (C) Copyright 2016 Boni Garcia (https://bonigarcia.github.io/)
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

import static java.lang.invoke.MethodHandles.lookup;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Performance test using several concurrent Chrome browsers.
 *
 * @author Boni Garcia
 * @since 1.0.0
 */
class PerformanceChromeTest {

    static final Logger log = getLogger(lookup().lookupClass());

    static final int NUMBER_OF_BROWSERS = 5;
    List<WebDriver> driverList = new ArrayList<>(NUMBER_OF_BROWSERS);

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        for (int i = 0; i < NUMBER_OF_BROWSERS; i++) {
            driverList.add(new ChromeDriver());
        }
    }

    @AfterEach
    void teardown() {
        for (int i = 0; i < NUMBER_OF_BROWSERS; i++) {
            driverList.get(i).quit();
        }
    }

    @Test
    void test() throws InterruptedException {
        ExecutorService executor = newFixedThreadPool(NUMBER_OF_BROWSERS);
        final CountDownLatch latch = new CountDownLatch(NUMBER_OF_BROWSERS);

        for (final WebDriver driver : driverList) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        singleTestExcution(driver);
                    } catch (Exception e) {
                        log.error("Some error happens", e);
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }

        latch.await();
        executor.shutdown();
    }

    void singleTestExcution(WebDriver driver) {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
