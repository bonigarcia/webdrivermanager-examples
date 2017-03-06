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

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.ChromeDriverManager;

/**
 * Test with Chrome.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.0.0
 */
public class CanvasTest {

	private WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		ChromeDriverManager.getInstance().setup();
	}

	@Before
	public void setupTest() {
		driver = new ChromeDriver();
	}

	@After
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void test() throws InterruptedException {
		driver.get("http://szimek.github.io/signature_pad/");

		WebElement div = driver.findElement(
				By.cssSelector("#signature-pad > div.m-signature-pad--body"));
		WebElement canvas = driver.findElement(By.cssSelector(
				"#signature-pad > div.m-signature-pad--body > canvas"));

		System.out.println(div.getTagName());
		System.out.println(canvas.getTagName());

		Actions actionBuilder = new Actions(driver);
		Action drawOnCanvas = actionBuilder.click(div)
				.moveToElement(canvas, 8, 8).clickAndHold(canvas)
				.moveByOffset(120, 120).moveByOffset(60, 70)
				.moveByOffset(-140, -140).release(div).build();
		drawOnCanvas.perform();

		Thread.sleep(5000);
	}

}
