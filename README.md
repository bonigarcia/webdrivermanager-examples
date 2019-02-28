[![License badge](https://img.shields.io/badge/license-Apache2-green.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Twitter](https://img.shields.io/badge/follow-@boni_gg-green.svg)](https://twitter.com/boni_gg)

# WebDriverManager Examples [![][Logo]][GitHub Repository]

This repository contains JUnit examples to automate the [Selenium Webdriver] binaries management using [WebDriverManager]. These examples are open source, released under the terms of [Apache 2.0 License].

## Usage

In order to use WebDriverManager in a Maven project, first add the following dependency to your `pom.xml`:

```xml
<dependency>
	<groupId>io.github.bonigarcia</groupId>
	<artifactId>webdrivermanager</artifactId>
	<version>3.3.0</version>
</dependency>
```

Then you can let WebDriverManager to do manage WebDriver binaries for your application/test. For example, as a JUnit test using Chrome browser:

```java
public class ChromeTest {

	private WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
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
	public void test() {
		// Your test code here
	}

}
```

... and using Firefox:

```java
public class FirefoxTest {

	private WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		WebDriverManager.firefoxdriver().setup();
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
	public void test() {
		// Your test code here
	}

}
```

## About

WebDriverManager-Examples (Copyright &copy; 2016-2019) is a personal project of [Boni Garcia] licensed under [Apache 2.0 License]. Comments, questions and suggestions are always very [welcome][WebDriverManager-Examples issues]!

[Apache 2.0 License]: http://www.apache.org/licenses/LICENSE-2.0
[Boni Garcia]: http://bonigarcia.github.io/
[Selenium Webdriver]: http://docs.seleniumhq.org/projects/webdriver/
[WebDriverManager]:https://github.com/bonigarcia/webdrivermanager/
[WebDriverManager-Examples issues]: https://github.com/bonigarcia/webdrivermanager-examples/issues
[Logo]: http://bonigarcia.github.io/img/webdrivermanager.png
[GitHub Repository]: https://github.com/bonigarcia/webdrivermanager-examples
