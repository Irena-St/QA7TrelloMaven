package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MenuPageHelper extends PageBase {
    @FindBy(xpath = "//a[@data-test-id = 'header-member-menu-profile']")
    WebElement profileVisibility;

    @FindBy(xpath = "//span[contains(text(),'Activity')]/..")
    List<WebElement> activityMenuLocatorList;


    public MenuPageHelper(WebDriver driver) {
        super(driver);

    }
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(profileVisibility,10);
        waitUntilElementIsClickable(activityMenuLocatorList.get(1),15);

    }

    public void openProfileVisibility() {
        profileVisibility.click();

    }

    public void openActivityPage() {
        activityMenuLocatorList.get(1).click();

    }
}
