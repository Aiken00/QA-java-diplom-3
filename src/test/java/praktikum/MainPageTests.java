package praktikum;

import handlers.Parameters;
import handlers.WebDriverFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import pageobjects.MainPage;

import static org.hamcrest.Matchers.equalTo;

@DisplayName("Проверки конструктора (главной страницы)")
@RunWith(JUnit4.class)
public class MainPageTests {
    private WebDriver driver;
    private String browserName;
    private MainPage mainPage;

    @Before
    @Step("Запуск браузера")
    public void startUp() {
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        driver = webDriverFactory.getWebDriver();
        browserName = webDriverFactory.getBrowserName();
        driver.get(Parameters.URL_MAIN_PAGE);
        mainPage = new MainPage(driver);
    }

    @After
    @Step("Закрытие браузера")
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Step("Нажатие на вкладку Булки")
    @DisplayName("Проверка работы вкладки Булки в разделе с ингредиентами")
    public void checkNavBunsIsSuccess() {
        Allure.parameter("Браузер", browserName);
        int expectedLocation = mainPage.getIngredientTitleExpectedLocation();

        mainPage.clickToppingsButton();
        mainPage.clickBunsButton();

        MatcherAssert.assertThat(
                "Ингредиенты не проскроллились до булок",
                mainPage.getBunsLocation(),
                equalTo(expectedLocation)
        );
    }

    @Test
    @Step("Нажатие на вкладку Соусы")
    @DisplayName("Проверка работы вкладки Соусы в разделе с ингредиентами")
    public void checkNavToppingsIsSuccess() {
        Allure.parameter("Браузер", browserName);
        int expectedLocation = mainPage.getIngredientTitleExpectedLocation();

        mainPage.clickToppingsButton();

        MatcherAssert.assertThat(
                "Ингредиенты не проскроллились до соусов",
                mainPage.getToppingsLocation(),
                equalTo(expectedLocation)
        );
    }

    @Test
    @Step("Нажатие на вкладку Начинки")
    @DisplayName("Проверка работы вкладки Начинки в разделе с ингредиентами")
    public void checkNavFillingsIsSuccess() {
        Allure.parameter("Браузер", browserName);
        int expectedLocation = mainPage.getIngredientTitleExpectedLocation();

        mainPage.clickFillingsButton();

        MatcherAssert.assertThat(
                "Ингредиенты не проскроллились до соусов",
                mainPage.getFillingsLocation(),
                equalTo(expectedLocation)
        );
    }
}
