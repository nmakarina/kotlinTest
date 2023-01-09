package demo

import base.alias
import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Screenshots
import com.codeborne.selenide.Selectors.byXpath
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.Selenide.sleep
import com.codeborne.selenide.WebDriverRunner
import io.qameta.allure.Attachment
import io.qameta.allure.Description
import io.qameta.allure.Feature
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.io.File


@Feature("Test")
class SimpleTest : alias() {
    private fun createDriver(){
        Configuration.browser = "chrome"
        //Подключаем плагин для работы с ЭЦП
        val chromeOptions = ChromeOptions().apply {
            addExtensions(File("CryptoPro Extension for CAdES Browser Plug-in.crx"))
            addArguments("--start-maximized")
            //addArguments("disable-popup-blocking", "true")
            //setExperimentalOption("useAutomationExtension", false)
        }
        WebDriverRunner.setWebDriver(ChromeDriver(chromeOptions))
    }

    @BeforeEach
    fun before() {
        createDriver()
        println("Before")
    }

    @Test
    @Description("Приверка поиска Google")
    fun `google test`(){
        open("https://google.com/")
        val txt = "kotlin"
        s(byXpath("//input")).setValue(txt).pressEnter()

        val res = ss(byXpath("//*[contains(text(),'$txt')]")).size
        assertTrue(res>2,"Поиск не работает")
        sleep(3)
   }

    @Attachment(type = "image/png")
    fun screenshot(): File? {
        val screenshot = Screenshots.getLastScreenshot()
        return screenshot
    }
    @AfterEach
    fun after() {
        WebDriverRunner.closeWebDriver()
        screenshot()
        println("After")
    }
}


