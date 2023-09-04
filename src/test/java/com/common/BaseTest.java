package com.common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.common.constants.UrlConstants;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Cookie;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static java.lang.invoke.MethodHandles.lookup;

/**
 * @Description: Base test class
 * @Author: Chi-Chun Chang
 * @Date: 2023/08/23
 * */

public class BaseTest {

    public Logger logger = LoggerFactory.getLogger(lookup().lookupClass());
    String domain = "test";
    String baseUrl = "https://%s.%s";
    private static String fullUrl;
    private final static String selenideProperties = "selenide.properties";
    private Map<String, String> keyMap = new HashMap<>();

    protected WebDriver driver;

    @BeforeAll
    public void setupClass() {

        System.setProperty("webdriver.gecko.driver", "./drivers/mac/geckodriver");
        System.setProperty("selenide.browser", "firefox");
        open("about:blank");
        driver = WebDriverRunner.getWebDriver();
    }

    @AfterAll
    static void cleanupClass() {
    }

    @BeforeEach
    protected void setupTest() {
        Configuration.browserSize = "1920x1080";
        setDomain(com.constants.DomainConstants.getStage());
        setUrl(UrlConstants.MAIN_PAGE);
    }

    @AfterEach
    protected void cleanupTest() {
        Selenide.closeWebDriver();
    }

    public void setUrl(String url) {
        if (domain == null) {
            throw new IllegalStateException("Domain is not set. Please set a domain first.");
        }
        fullUrl = String.format(baseUrl, domain, url);
        logger.info("url : " + fullUrl);
    }

    public String getUrl() {
        if (fullUrl == null) {
            throw new IllegalStateException("URL is not set. Please set a domain and URL first.");
        }
        return fullUrl;
    }

    public void setDomain(String domain) {
        this.domain = domain;
        logger.info("domain : " + this.domain);
    }

    public void setCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        driver.manage().addCookie(cookie);
    }
}
