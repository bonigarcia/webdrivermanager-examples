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

import static java.lang.invoke.MethodHandles.lookup;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Performance test using several concurrent PhantomJS browsers (headless).
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class PerformancePhantomJsTest {

    static final Logger log = getLogger(lookup().lookupClass());

    private static final int NUMBER_OF_BROWSERS = 5;
    private List<WebDriver> driverList = new ArrayList<>(NUMBER_OF_BROWSERS);

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.phantomjs().setup();
    }

    @BeforeEach
    public void setupTest() {
        for (int i = 0; i < NUMBER_OF_BROWSERS; i++) {
            driverList.add(new PhantomJSDriver());
        }
    }

    @AfterEach
    public void teardown() {
        for (int i = 0; i < NUMBER_OF_BROWSERS; i++) {
            driverList.get(i).close();
        }
    }

    @Test
    public void test() throws InterruptedException {
        ExecutorService executor = Executors
                .newFixedThreadPool(NUMBER_OF_BROWSERS);
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

    private void singleTestExcution(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        By searchInput = By.id("searchInput");
        wait.until(presenceOfElementLocated(searchInput));
        driver.findElement(searchInput).sendKeys("Software");
        By searchButton = By.id("searchButton");

        wait.until(elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();

        wait.until(textToBePresentInElementLocated(By.tagName("body"),
                "Computer software"));
    }

}
