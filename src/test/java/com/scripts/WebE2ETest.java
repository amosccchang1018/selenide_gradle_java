package com.scripts;

import com.codeborne.selenide.*;
import com.common.BaseTest;
import com.common.constants.AccountConstants;
import com.common.constants.ElementConstants;
import com.common.constants.UrlConstants;
import com.common.utils.UIUtil;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;


/**
 * @Description: Example tests
 * @Author: Chi-Chun Chang
 * @Date: 2023/08/23
 * */


public class WebE2ETest extends BaseTest {

    @Test
    @Description("Find a specific ring and add to my favorite")
    @Severity(SeverityLevel.NORMAL)
    void findProductAndAddToCartTest() {
        String DESIRE_ITEM = "Honey Mini Signet";

        // Step 1: Navigate to the homepage
        open(UrlConstants.MAIN_PAGE);

        // login first
        UIUtil.clickElementByCSS(ElementConstants.LOGIN);

        String TestAAccount_AMOS = "User_1";
        UIUtil.sendKeyByCSS(ElementConstants.EMAIL_BOX, AccountConstants.getValue(TestAAccount_AMOS, "email"));
        UIUtil.sendKeyByCSS(ElementConstants.PASSWORD_BOX, AccountConstants.getValue(TestAAccount_AMOS, "pwd"));
        UIUtil.clickElementByCSS(ElementConstants.LOGIN_CONTINUE);

        // Step 2: Click the search icon
        SelenideElement buttonElement = $(ElementConstants.SEARCH);
        buttonElement.shouldBe(Condition.visible, Duration.ofSeconds(5)).click();

        // Step 3: Enter the search keyword
        UIUtil.sendKeyByCSS(ElementConstants.SEARCH_BAR, DESIRE_ITEM);
        UIUtil.clickElementByCSS(ElementConstants.SELECTED_RING);

        // Step 4: Add to my favorite
        String pagesource = WebDriverRunner.getWebDriver().getPageSource();
         if (pagesource.contains(ElementConstants.ADD_FAVORITE)){
          UIUtil.clickElementByCSS(ElementConstants.ADD_FAVORITE);
         }
        else{
             logger.info("already added");
        }

        // Step 5: Check if it's added
        UIUtil.clickElementByCSS(ElementConstants.MY_FAVORITE);
        pagesource = WebDriverRunner.getWebDriver().getPageSource();
        assertTrue(pagesource.contains(DESIRE_ITEM));

    }

}
