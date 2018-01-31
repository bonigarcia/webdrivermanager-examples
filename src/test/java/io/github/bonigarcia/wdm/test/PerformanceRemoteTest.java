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

import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.remote.DesiredCapabilities.chrome;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

/**
 * Performance test using several concurrent remote Chrome browsers.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class PerformanceRemoteTest {

    private static final int NUMBER_OF_BROWSERS = 50;
    private List<WebDriver> driverList = new CopyOnWriteArrayList<>();

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Before
    public void setupTest() throws InterruptedException {
        ExecutorService executor = newFixedThreadPool(NUMBER_OF_BROWSERS);
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_BROWSERS);

        for (int i = 0; i < NUMBER_OF_BROWSERS; i++) {
            executor.execute(() -> {
                try {
                    driverList.add(new RemoteWebDriver(
                            new URL("http://localhost:4444/wd/hub"), chrome()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();
    }

    @After
    public void teardown() throws InterruptedException {
        ExecutorService executor = newFixedThreadPool(NUMBER_OF_BROWSERS);
        CountDownLatch latch = new CountDownLatch(NUMBER_OF_BROWSERS);

        for (int i = 0; i < NUMBER_OF_BROWSERS; i++) {
            final WebDriver driver = driverList.get(i);
            final int j = i;
            executor.execute(() -> {
                try {
                    if (driver != null) {
                        driver.quit();
                    } else {
                        System.out.println("**** Error with driver " + j);
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();
    }

    @Test
    public void test() throws InterruptedException {
        ExecutorService executor = newFixedThreadPool(NUMBER_OF_BROWSERS);
        final CountDownLatch latch = new CountDownLatch(NUMBER_OF_BROWSERS);

        for (int i = 0; i < NUMBER_OF_BROWSERS; i++) {
            final int j = i;
            executor.execute(() -> {
                try {
                    singleTestExcution(driverList.get(j), j);
                } catch (Throwable e) {
                    errorCollector.addError(e);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();
    }

    private void singleTestExcution(WebDriver driver, int index) {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        String title = driver.getTitle();
        assertTrue(title.equals("Wikipedia, the free encyclopedia"));

        SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
        System.out.println(index + " -- " + sessionId + " -- " + title);
    }

}
