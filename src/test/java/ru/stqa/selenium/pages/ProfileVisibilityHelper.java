package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfileVisibilityHelper extends PageBase {
    @FindBy(xpath = "//a[@data-tab='profile']")
    WebElement profileTab;

    @FindBy(xpath ="//button[@aria-label = 'Open Member Menu']")
    WebElement memberMenuIcon;

    @FindBy(xpath = "//input[@name='username']")
    WebElement userNameField;

    public ProfileVisibilityHelper(WebDriver driver) {
        super(driver);
    }

    public ProfileVisibilityHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(userNameField,10);
        waitUntilElementIsVisible(profileTab,10);
        return this;
    }
    public String getProfileVisibilityTabName(){
        return profileTab.getText();
    }

    public String getTitleMenuIcon() {
         return memberMenuIcon.getAttribute("title");
    }

    public String getUserName() {
        return userNameField.getAttribute("value");
    }
}

