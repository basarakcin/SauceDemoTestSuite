package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;

public class FooterPage extends BasePage {

    private static final String EXPECTED_TWITTER_URL = "https://twitter.com/saucelabs";
    private static final String EXPECTED_FACEBOOK_URL = "https://www.facebook.com/saucelabs";
    private static final String EXPECTED_LINKEDIN_URL = "https://www.linkedin.com/company/sauce-labs/";
    private static final String EXPECTED_COPYRIGHT = "© 2025 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy";

    public FooterPage(WebDriver driver) {
        super(driver);
    }

    public boolean areSocialLinksPresent() {
        return driver.findElement(twitterLink).isDisplayed() &&
               driver.findElement(facebookLink).isDisplayed() &&
               driver.findElement(linkedinLink).isDisplayed();
    }

    public boolean areAllSocialLinksValid() {
        return driver.findElement(twitterLink).getDomAttribute("href").equals(EXPECTED_TWITTER_URL) &&
               driver.findElement(facebookLink).getDomAttribute("href").equals(EXPECTED_FACEBOOK_URL) &&
               driver.findElement(linkedinLink).getDomAttribute("href").equals(EXPECTED_LINKEDIN_URL);
    }

    public boolean isFooterCopyCorrect() {
        return driver.findElement(footerCopy).getText().equals(EXPECTED_COPYRIGHT);
    }
} 