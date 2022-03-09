package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import config.SystemPropertyProvider;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class Autotest {

    public Autotest() {
        Configuration.baseUrl = SystemPropertyProvider.getBaseUrl();
        Configuration.driverManagerEnabled = false;
        Configuration.fastSetValue = true;
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Rini-\\Documents\\chromedriver.exe");
        ChromeOptions options = this.createChromeOptions();
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserSize = "1680x1050";
    }

    protected WebDriver getDriver() {
        return WebDriverRunner.getWebDriver();
    }

    protected ChromeOptions createChromeOptions() {
        DesiredCapabilities capabilities1 = new DesiredCapabilities("chrome", "", Platform.ANY);
        capabilities1.setCapability("enableVNC", true);
        capabilities1.setCapability("enableVideo", false);
        ChromeOptions options = new ChromeOptions();
        String lang = "ru-RU";
        options.addArguments("--lang=" + lang);
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("intl.accept_languages", lang);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("disable-infobars");
        options.addArguments("--profile-directory='Default'");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-web-security");
        options.addArguments("--no-proxy-server");
        options.addArguments("--enable-automation");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--no-sandbox", "--window-size=1920,1080");
        options.addArguments("window-size=" + 1980 + "," + 1080);
        options.merge(capabilities1);
        return options;
    }
}
