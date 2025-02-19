package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;

import com.saucedemo.constants.URLs;

public class FooterPage extends BasePage {

    public FooterPage(WebDriver driver) {
        super(driver);
    }

    public boolean areSocialLinksPresent() {
        return driver.findElement(twitterLink).isDisplayed() &&
               driver.findElement(facebookLink).isDisplayed() &&
               driver.findElement(linkedinLink).isDisplayed();
    }

    public boolean areAllSocialLinksValid() {
        return driver.findElement(twitterLink).getDomAttribute("href").equals(URLs.EXPECTED_TWITTER_URL) &&
               driver.findElement(facebookLink).getDomAttribute("href").equals(URLs.EXPECTED_FACEBOOK_URL) &&
               driver.findElement(linkedinLink).getDomAttribute("href").equals(URLs.EXPECTED_LINKEDIN_URL);
    }

    public boolean isFooterCopyCorrect() {
        return driver.findElement(footerCopy).getText().equals(URLs.EXPECTED_COPYRIGHT);
    }
} 