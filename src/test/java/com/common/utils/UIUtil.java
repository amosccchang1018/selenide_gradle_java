package com.common.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.common.constants.ElementConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.$;

/**
 * @Description: functions for web actions
 * @Author: Chi-Chun Chang
 * @Date: 2023/09/04
 */
public class UIUtil {

    public static void clickPopup() {
        String pageSource = WebDriverRunner.getWebDriver().getPageSource();
        while(pageSource.contains(ElementConstants.CLOSE)){
            if(pageSource.contains(ElementConstants.CLOSE)){
                $(ElementConstants.CLOSE).shouldBe(Condition.visible, Duration.ofSeconds(5)).click();
                break;
            }
        }
    }

    public static void clickElementByCSS(String css) {
        clickPopup();

        SelenideElement buttonElement = $(css);
        buttonElement.shouldBe(Condition.visible, Duration.ofSeconds(5)).click();

        Selenide.sleep(3000);
    }

    public static void sendKeyByCSS(String css, String key) {
        clickPopup();
        SelenideElement inputElement = $(css);
        inputElement.setValue(key);

        Selenide.sleep(3000);
    }

    public static void clickElementByText(String buttonText) {
        Selenide.$("[button data-testid=\"add-to-cart-btn\"]").click();
    }

}
