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

import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.slf4j.Logger;

/**
 * Performance test using several concurrent remote Chrome browsers.
 *
 * @author Boni Garcia
 * @since 1.0.0
 */
@Disabled
class PerformanceRemoteTest {

    static final Logger log = getLogger(lookup().lookupClass());

    static final int NUMBER_OF_BROWSERS = 50;
    List<WebDriver> driverList = new CopyOnWriteArrayList<>();

    @BeforeEach
    void setupTest() throws Exception {
        ExecutorService executor = newFixedThreadPool(NUMBER_OF_BROWSERS);
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_BROWSERS);
        URL serverUrl = new URL("http://localhost:4444/wd/hub");

        for (int i = 0; i < NUMBER_OF_BROWSERS; i++) {
            executor.execute(() -> {
                try {
                    driverList.add(new RemoteWebDriver(serverUrl,
                            new ChromeOptions()));
                } catch (Exception e) {
                    log.error("Some error happens", e);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        ExecutorService executor = newFixedThreadPool(NUMBER_OF_BROWSERS);
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_BROWSERS);

        for (int i = 0; i < NUMBER_OF_BROWSERS; i++) {
            final WebDriver driver = driverList.get(i);
            executor.execute(() -> {
                try {
                    driver.quit();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();
    }

    @Test
    void test() throws InterruptedException {
        ExecutorService executor = newFixedThreadPool(NUMBER_OF_BROWSERS);
        final CountDownLatch latch = new CountDownLatch(NUMBER_OF_BROWSERS);

        for (int i = 0; i < NUMBER_OF_BROWSERS; i++) {
            final int j = i;
            executor.execute(() -> {
                try {
                    singleTestExcution(driverList.get(j), j);
                } catch (Exception e) {
                    log.error("Some error happens", e);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();
    }

    void singleTestExcution(WebDriver driver, int index) {
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);
        String title = driver.getTitle();
        log.debug("The title of {} is {}", sutUrl, title);
        assertThat(title).contains("Selenium WebDriver");

        SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
        log.debug("{} -- {} -- {}", index, sessionId, title);
    }

}
