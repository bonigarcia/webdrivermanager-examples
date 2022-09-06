[![Maven Central](https://img.shields.io/maven-central/v/io.github.bonigarcia/webdrivermanager.svg)](https://search.maven.org/#search%7Cga%7C1%7Cg%3Aio.github.bonigarcia%20a%3Awebdrivermanager)
[![Build Status](https://github.com/bonigarcia/webdrivermanager-examples/workflows/build/badge.svg)](https://github.com/bonigarcia/webdrivermanager-examples/actions)
[![badge-jdk](https://img.shields.io/badge/jdk-11-green.svg)](https://www.oracle.com/java/technologies/downloads/)
[![License badge](https://img.shields.io/badge/license-Apache2-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Backers on Open Collective](https://opencollective.com/webdrivermanager/backers/badge.svg)](#backers)
[![Sponsors on Open Collective](https://opencollective.com/webdrivermanager/sponsors/badge.svg)](#sponsors)
[![Support badge](https://img.shields.io/badge/stackoverflow-webdrivermanager_java-green.svg?logo=stackoverflow)](https://stackoverflow.com/questions/tagged/webdrivermanager-java)
[![Twitter Follow](https://img.shields.io/twitter/follow/boni_gg.svg?style=social)](https://twitter.com/boni_gg)

# WebDriverManager Examples [![][Logo]][GitHub Repository]

This repository contains JUnit examples to automate the [Selenium WebDriver] driver management using [WebDriverManager]. These examples are open-source, released under the terms of [Apache 2.0 License].

## Usage

In order to use WebDriverManager from tests in a Maven project, you need to add the following dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>${wdm.version}</version>
    <scope>test</scope>
</dependency>
```

... or in a Gradle project:

```
dependencies {
    testImplementation("io.github.bonigarcia:webdrivermanager:${wdm.version}")
}
```

Then you can let WebDriverManager to manage the drivers required by Selenium WebDriver (e.g., chromedriver, geckodriver). For example, as a JUnit test using Chrome browser:

```java
class ChromeTest {

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
    void test() {
        // Your test code here
    }

}
```

... or using Firefox:

```java
class FirefoxTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new FirefoxDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void test() {
        // Your test code here
    }

}
```

## Help

If you have questions on how to use WebDriverManager properly with a special configuration or suchlike, please consider asking a question on [Stack Overflow] and tag it with  *webdrivermanager-java*.

## Support

WebDriverManager is part of [OpenCollective], an online funding platform for open and transparent communities. You can support the project by contributing as a backer (i.e., a personal [donation] or [recurring contribution]) or as a [sponsor] (i.e., a recurring contribution by a company).

### Backers

<a href="https://opencollective.com/webdrivermanager" target="_blank"><img src="https://opencollective.com/webdrivermanager/backers.svg?width=890"></a>

### Sponsors
<a href="https://opencollective.com/webdrivermanager/sponsor/0/website" target="_blank"><img src="https://opencollective.com/webdrivermanager/sponsor/0/avatar.svg"></a>
<a href="https://opencollective.com/webdrivermanager/sponsor/1/website" target="_blank"><img src="https://opencollective.com/webdrivermanager/sponsor/1/avatar.svg"></a>
<a href="https://opencollective.com/webdrivermanager/sponsor/2/website" target="_blank"><img src="https://opencollective.com/webdrivermanager/sponsor/2/avatar.svg"></a>
<a href="https://opencollective.com/webdrivermanager/sponsor/3/website" target="_blank"><img src="https://opencollective.com/webdrivermanager/sponsor/3/avatar.svg"></a>
<a href="https://opencollective.com/webdrivermanager/sponsor/4/website" target="_blank"><img src="https://opencollective.com/webdrivermanager/sponsor/4/avatar.svg"></a>
<a href="https://opencollective.com/webdrivermanager/sponsor/5/website" target="_blank"><img src="https://opencollective.com/webdrivermanager/sponsor/5/avatar.svg"></a>
<a href="https://opencollective.com/webdrivermanager/sponsor/6/website" target="_blank"><img src="https://opencollective.com/webdrivermanager/sponsor/6/avatar.svg"></a>
<a href="https://opencollective.com/webdrivermanager/sponsor/7/website" target="_blank"><img src="https://opencollective.com/webdrivermanager/sponsor/7/avatar.svg"></a>
<a href="https://opencollective.com/webdrivermanager/sponsor/8/website" target="_blank"><img src="https://opencollective.com/webdrivermanager/sponsor/8/avatar.svg"></a>
<a href="https://opencollective.com/webdrivermanager/sponsor/9/website" target="_blank"><img src="https://opencollective.com/webdrivermanager/sponsor/9/avatar.svg"></a>

## About

WebDriverManager (Copyright &copy; 2015-2022) is a personal project of [Boni Garcia] licensed under [Apache 2.0 License].

[Apache 2.0 License]: https://www.apache.org/licenses/LICENSE-2.0
[Boni Garcia]: https://bonigarcia.github.io/
[Selenium WebDriver]: https://docs.seleniumhq.org/projects/webdriver/
[WebDriverManager]:https://github.com/bonigarcia/webdrivermanager/
[Logo]: https://bonigarcia.github.io/img/webdrivermanager.png
[GitHub Repository]: https://github.com/bonigarcia/webdrivermanager-examples
[Stack Overflow]: https://stackoverflow.com/questions/tagged/webdrivermanager-java
[OpenCollective]: https://opencollective.com/webdrivermanager
[donation]: https://opencollective.com/webdrivermanager/donate
[recurring contribution]: https://opencollective.com/webdrivermanager/contribute/backer-8132/checkout
[sponsor]: https://opencollective.com/webdrivermanager/contribute/sponsor-8133/checkout
