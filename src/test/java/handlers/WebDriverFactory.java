package handlers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebDriverFactory {
    public static String getBrowserName() {  // Изменено на public
        Properties properties = new Properties();
        try (InputStream input = WebDriverFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Не удалось найти config.properties");
            }
            properties.load(input);
            return properties.getProperty("browser", "chrome");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки файла свойств", e);
        }
    }

    public WebDriver getWebDriver() {
        String browserName = getBrowserName();

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage","--headless");
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(chromeOptions);

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(firefoxOptions);

            default:
                throw new RuntimeException("Некорректное имя браузера. Поддерживаемые браузеры: chrome, firefox.");
        }
    }
}
