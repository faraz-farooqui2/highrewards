package com.automation.highrewards.pages.web.page;

import com.automation.highrewards.base.BaseWebDriver;
import com.automation.highrewards.pages.web.locators.HomePageLocators;

public class HomePage extends BaseWebDriver {

    public HomePage() {
        super(); // Call the BaseWebDriver constructor to initialize WebDriver and wait
    }

    public void clickLoginButton() {
        waitForElementToBeClickable(HomePageLocators.LOGIN_BUTTON);
        getDriver().findElement(HomePageLocators.LOGIN_BUTTON).click();
    }

    public void enterSearchText(String text) {
        waitForElementToBeVisible(HomePageLocators.SEARCH_BOX);
        getDriver().findElement(HomePageLocators.SEARCH_BOX).sendKeys(text);
    }
}
