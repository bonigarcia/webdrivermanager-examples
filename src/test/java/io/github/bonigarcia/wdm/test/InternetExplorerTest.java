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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Test with Internet Explorer.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
@Disabled
public class InternetExplorerTest {

    private WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.iedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        driver = new InternetExplorerDriver();
    }

    @AfterAll
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        assertThat(driver).isNotNull();
        driver.get("https://bonigarcia.github.io/selenium-jupiter/");
    }

}
