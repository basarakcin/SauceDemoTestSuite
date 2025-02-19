package com.saucedemo.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.saucedemo.config.WebDriverConfig;
import com.saucedemo.pages.FooterPage;

import io.cucumber.java.en.Then;

public class FooterStepDefinitions {
    
    private final WebDriver driver;
    private final FooterPage footerPage;

    public FooterStepDefinitions() {
        this.driver = WebDriverConfig.getDriver();
        this.footerPage = new FooterPage(driver);
    }

    @Then("the footer should contain links to {string}, {string} and {string}")
    public void theFooterShouldContainSocialMediaLinks(String link1, String link2, String link3) {
        assert footerPage.areSocialLinksPresent() : "Social media links are not present";
    }

    @Then("the social media links should point to the correct URLs")
    public void theSocialMediaLinksShouldPointToCorrectUrls() {
        assert footerPage.areAllSocialLinksValid() : "Social media URLs are not correct";
    }

    @Then("the copyright text should be correct")
    public void theCopyrightTextShouldBeCorrect() {
        assert footerPage.isFooterCopyCorrect() : "Footer copyright text is not correct";
    }
} 