package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase {
    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(id = "user")
    WebElement loginField;

    @FindBy(id = "error")
    WebElement errorMessage;

    @FindBy(id = "login-error")
    WebElement errorMessageAtl;

    @FindBy(xpath = "//input[@value='Log in with Atlassian']")
    WebElement loginButtonAtl;

    @FindBy(id = "login-submit")
    WebElement loginSubmitButton;

    public LoginPageHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public LoginPageHelper loginAsAtlassian(String login, String password) {
        enterLoginAsAtlassian(login);
        submitAsAtlassian();
        enterPasswordAsAtlassian(password);
        return this;
    }

    public LoginPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(passwordField, 15);
        waitUntilElementIsClickable(loginButton, 10);
        waitUntilElementIsClickable(loginField, 15);
        return this;
    }

    public String getErrorMessage() {
        waitUntilElementIsVisible(errorMessage, 15);
        return errorMessage.getText();
    }

    public String getAtlassianErrorMessage() {
        waitUntilElementIsVisible(errorMessageAtl, 15);
        return errorMessageAtl.getText();
    }

    public LoginPageHelper pressLoginButton() {
        log4j.info("LoginPageHelper: pressLoginButton()");
        log4j.info("Wait 5 sec");
        waitUntilElementIsClickable(loginButton, 10);
        log4j.info("Click on the login button");
        loginButton.click();
        return this;
    }

    public LoginPageHelper enterNotAtlassianPassword(String password) {
        // WebElement passwordField = driver.findElement(By.id("password"));
        log4j.info("LoginPageHelper: enterNotAtlassianPassword(password="+password+")");
        log4j.info("Enter password value to the password field");
        editField(passwordField, password);
        return this;
    }


    public LoginPageHelper enterNotAtlassianLogin(String login) {
        log4j.info("LoginPageHelper: enterNotAtlassianLogin(login="+login+")");
        log4j.info("Enter login value to the login field");
        editField(loginField, login);
        return this;
    }

    public LoginPageHelper enterLoginAsAtlassian(String login) {
        editField(loginField, login);
        return this;
    }

    public LoginPageHelper submitAsAtlassian() {
        waitUntilElementIsClickable(loginButtonAtl, 10);
        loginButtonAtl.click();
        waitUntilElementIsClickable(passwordField, 20);
        return this;
    }

    public LoginPageHelper enterPasswordAsAtlassian(String password) {
        editField(passwordField, password);
        loginSubmitButton.click();
        return this;
    }

    public LoginPageHelper loginNotAtlassian(String login, String password) {
        log4j.info("LoginPageHelper: loginNotAtlassian(login=" +login+ ", password="+password+")");
        enterNotAtlassianLogin(login);
        enterNotAtlassianPassword(password);
        pressLoginButton();
        return this;
    }

}
