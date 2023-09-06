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
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.DisplayName;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;


/**
 * @Description: UI E2E tests
 * @Author: Chi-Chun Chang
 * @Date: 2023/08/23
 * */

@Owner("Chi-Chun Chang")
public class WebE2ETest extends BaseTest {

    @Test
    @Tag("Positive")
    @DisplayName("find Product And Add To Cart Test")
    @Description("Find a specific ring and add to my favorite")
    @Severity(SeverityLevel.NORMAL)
    void findProductAndAddToCartTest() {
        String DESIRE_ITEM = "Honey Mini Signet";
        String TestAAccount_AMOS = "User_1";

        // Step 1: Navigate to the homepage
        open(UrlConstants.MAIN_PAGE);

        // Step 2: Login
        UIUtil.clickElementByCSS(ElementConstants.LOGIN);

        UIUtil.sendKeyByCSS(ElementConstants.EMAIL_BOX, AccountConstants.getValue(TestAAccount_AMOS, "email"));
        UIUtil.sendKeyByCSS(ElementConstants.PASSWORD_BOX, AccountConstants.getValue(TestAAccount_AMOS, "pwd"));
        UIUtil.clickElementByCSS(ElementConstants.LOGIN_CONTINUE);

        // Step 3: Click the search bar
        SelenideElement buttonElement = $(ElementConstants.SEARCH);
        buttonElement.shouldBe(Condition.visible, Duration.ofSeconds(5)).click();

        // Step 4: Enter the search keyword and click the item
        UIUtil.sendKeyByCSS(ElementConstants.SEARCH_BAR, DESIRE_ITEM);
        UIUtil.clickElementByCSS(ElementConstants.SELECTED_RING);

        // Step 5: Add to my favorite if it's not added
        String pagesource = WebDriverRunner.getWebDriver().getPageSource();
        if (pagesource.contains(ElementConstants.ADD_FAVORITE)){
            sleep(3000);
            UIUtil.clickElementByCSS(ElementConstants.ADD_FAVORITE);
        }
        else{
             logger.info("already added");
        }

        // Step 6: Check if it's added
        UIUtil.clickElementByCSS(ElementConstants.MY_FAVORITE);
        pagesource = WebDriverRunner.getWebDriver().getPageSource();
        assertTrue(pagesource.contains(DESIRE_ITEM));

    }


    @Test
    @Tag("Negative")
    @DisplayName("find Product And Fail To Add To Favorite Unauthenticated Test")
    @Description("Find a specific ring but fail to add to my favorite due to being unauthenticated")
    @Severity(SeverityLevel.NORMAL)
    void findProductAndFailToAddToFavoriteUnauthenticatedTest() {
        String DESIRE_ITEM = "Honey Mini Signet";
        String TestAAccount_AMOS = "User_1";

        // Step 1: Navigate to the homepage
        open(UrlConstants.MAIN_PAGE);

        // Step 2: Click the search bar
        SelenideElement buttonElement = $(ElementConstants.SEARCH);
        buttonElement.shouldBe(Condition.visible, Duration.ofSeconds(5)).click();

        // Step 3: Enter the search keyword and click the item
        UIUtil.sendKeyByCSS(ElementConstants.SEARCH_BAR, DESIRE_ITEM);
        UIUtil.clickElementByCSS(ElementConstants.SELECTED_RING);

        // Step 4: Add to my favorite if it's not added

        String pagesource = WebDriverRunner.getWebDriver().getPageSource();
        if (pagesource.contains(ElementConstants.ADD_FAVORITE)){
            UIUtil.clickElementByCSS(ElementConstants.ADD_FAVORITE);
        }
        else{
            logger.error("'Add to my favorite' button has issue");
        }

        // Step 5: See Login block popup
        pagesource = WebDriverRunner.getWebDriver().getPageSource();
        assertTrue(pagesource.contains("Please log in"));

    }

}
