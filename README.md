[![Maven Central](https://img.shields.io/maven-central/v/io.github.bonigarcia/webdrivermanager.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3Aio.github.bonigarcia%20a%3Awebdrivermanager)
[![badge-jdk](https://img.shields.io/badge/jdk-8-green.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![License badge](https://img.shields.io/badge/license-Apache2-green.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Backers on Open Collective](https://opencollective.com/webdrivermanager/backers/badge.svg)](#backers)
[![Sponsors on Open Collective](https://opencollective.com/webdrivermanager/sponsors/badge.svg)](#sponsors)
[![Support badge](https://img.shields.io/badge/stackoverflow-webdrivermanager_java-green.svg)](http://stackoverflow.com/questions/tagged/webdrivermanager-java)
[![Twitter Follow](https://img.shields.io/twitter/follow/boni_gg.svg?style=social)](https://twitter.com/boni_gg)

# WebDriverManager Examples [![][Logo]][GitHub Repository]

This repository contains JUnit examples to automate the [Selenium Webdriver] binaries management using [WebDriverManager]. These examples are open source, released under the terms of [Apache 2.0 License].

**WebDriverManager-Examples** is being sponsored by the following tool; please help to support us by taking a look and signing up to a free trial:<br>
<a href="https://tracking.gitads.io/?repo=webdrivermanager-examples"><img src="https://images.gitads.io/webdrivermanager-examples" alt="GitAds"/></a>

## Usage

In order to use WebDriverManager from tests in a Maven project, you need to add the following dependency in your `pom.xml` (Java 8 or upper required):

```xml
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>4.1.0</version>
    <scope>test</scope>
</dependency>
```

... or in Gradle project:

```
dependencies {
    testCompile("io.github.bonigarcia:webdrivermanager:4.1.0")
}
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

## Help

If you have questions on how to use WebDriverManager properly with a special configuration or suchlike, please consider asking a question on [Stack Overflow] and tag it with  *webdrivermanager-java*.


## Backers

Thank you to all our backers! [[Become a backer](https://opencollective.com/webdrivermanager#backer)]

<a href="https://opencollective.com/webdrivermanager#backers" target="_blank"><img src="https://opencollective.com/webdrivermanager/backers.svg?width=890"></a>

## Sponsors

Support this project by becoming a sponsor. Your logo will show up here with a link to your website. [[Become a sponsor](https://opencollective.com/webdrivermanager#sponsor)]

<a href="https://opencollective.com/webdrivermanager/sponsor/0/website" target="_blank"><img src="https://opencollective.com/webdrivermanager/sponsor/0/avatar.svg"></a>
<a href="https://opencollective.com/webdrivermanager/sponsor/1/website" target="_blank"><img src="https://opencollective.com/webdrivermanager/sponsor/1/avatar.svg"></a>


## About

WebDriverManager-Examples (Copyright &copy; 2016-2020) is a personal project of [Boni Garcia] licensed under [Apache 2.0 License]. Comments, questions and suggestions are always very [welcome][WebDriverManager-Examples issues]!

[Apache 2.0 License]: http://www.apache.org/licenses/LICENSE-2.0
[Boni Garcia]: http://bonigarcia.github.io/
[Selenium Webdriver]: http://docs.seleniumhq.org/projects/webdriver/
[WebDriverManager]:https://github.com/bonigarcia/webdrivermanager/
[WebDriverManager-Examples issues]: https://github.com/bonigarcia/webdrivermanager-examples/issues
[Logo]: http://bonigarcia.github.io/img/webdrivermanager.png
[GitHub Repository]: https://github.com/bonigarcia/webdrivermanager-examples
