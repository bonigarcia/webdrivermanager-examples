var webdriver = require("selenium-webdriver");

async function wdmServerTest() {
    var wdmServerUrl = "http://localhost:4444/";
    var capabilities = {
        browserName: "chrome",
        version: "100.0"
    };

    try {
        var driver = await new webdriver.Builder().usingServer(wdmServerUrl)
            .withCapabilities(capabilities).build();

        var sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        await driver.get(sutUrl);

        await driver.getTitle().then(function(title) {
            console.log("The title of " + sutUrl + " is '" + title + "'")
        });

    } catch (err) {
        console.error("Something went wrong!\n", err.stack);

    } finally {
        if (driver) {
            driver.quit();
        }
    }
}

wdmServerTest();
