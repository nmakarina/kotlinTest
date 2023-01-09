package base

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By

open class alias {

    fun s(locator: By): SelenideElement {
        return Selenide.`$`(locator)
    }

    fun ss(locator: By): ElementsCollection {
        return Selenide.`$$`(locator)
    }
}