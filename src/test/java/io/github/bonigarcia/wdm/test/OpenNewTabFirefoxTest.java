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

import static java.awt.event.KeyEvent.VK_CONTROL;
import static java.awt.event.KeyEvent.VK_META;
import static java.awt.event.KeyEvent.VK_T;
import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.FirefoxDriverManager;

/**
 * Example which opens a new Tab with Java Robot using Firefox as browser.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class OpenNewTabFirefoxTest {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        FirefoxDriverManager.getInstance().setup();
    }

    @Before
    public void setupTest() {
        driver = new FirefoxDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() throws Exception {
        // Open URL in default tab
        driver.get("https://wikipedia.org/");

        // If Mac OS X, the key combination is CMD+t, otherwise is CONTROL+t
        int vkControl = IS_OS_MAC ? VK_META : VK_CONTROL;
        Robot robot = new Robot();
        robot.keyPress(vkControl);
        robot.keyPress(VK_T);
        robot.keyRelease(vkControl);
        robot.keyRelease(VK_T);

        // Wait up to 5 seconds to the second tab to be opened
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(numberOfWindowsToBe(2));

        // Switch to new tab
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        System.err.println(windowHandles);
        driver.switchTo().window(windowHandles.get(1));

        // Open other URL in second tab
        driver.get("https://google.com/");
    }

}
