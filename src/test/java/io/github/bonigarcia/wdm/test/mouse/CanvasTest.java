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

package io.github.bonigarcia.wdm.test.mouse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Test with Canvas in Chrome.
 *
 * @author Boni Garcia
 * @since 1.0.0
 */
class CanvasTest {

    final int NUM_POINTS = 20;
    final double RADIUS = 30;

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void test() throws InterruptedException {
        driver.get("http://szimek.github.io/signature_pad/");

        WebElement canvas = driver.findElement(By.tagName("canvas"));
        Actions actions = new Actions(driver).moveToElement(canvas)
                .clickAndHold();

        for (int i = 0; i <= NUM_POINTS; i++) {
            double angle = Math.toRadians(((double) i / NUM_POINTS) * 360);
            double x = Math.sin(angle) * RADIUS;
            double y = Math.cos(angle) * RADIUS;
            actions.moveByOffset((int) x, (int) y);
        }

        actions.release(canvas);
        actions.build().perform();

        Thread.sleep(5000);
    }

}
