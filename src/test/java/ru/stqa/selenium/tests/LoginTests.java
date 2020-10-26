package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.BoardsPageHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;
import ru.stqa.selenium.util.DataProviders;


public class LoginTests extends TestBase {
LoginPageHelper loginPage;
BoardsPageHelper boardsPage;
HomePageHelper homePage;

    @BeforeMethod
    public void initTests() {
        log4j.info("LoginTest:@BeforeMethod initTests()");
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver,BoardsPageHelper.class);
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded();
    }
    @Test
    public void loginNegativeLoginEmpty()  {
        log4j.startTestCase("loginNegativeLoginEmpty()");
        loginPage.loginNotAtlassian("",PASSWORD)
                .pressLoginButton();
        log4j.info("-----Test case was finished-----");
        Assert.assertEquals(loginPage.getErrorMessage(),"Missing email",
                "The text of the error message is not correct");
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderIncorrectLogin")
    public void loginNegativeTest(String login,String password,String errorMessage) {
      log4j.startTestCase("loginNegativeTest");
      log4j.info("Parameter login - " + login);
        log4j.info("Parameter password - " + password);
        log4j.info("Parameter errorMessage - " + errorMessage);
        loginPage.loginNotAtlassian(login, password);
        Assert.assertEquals(loginPage.getErrorMessage(),errorMessage,
                "The error message is not '" + errorMessage + "'");
        log4j.info("-----Test case was finished-----");
    }
    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderThird")
    public void loginNegativeTest2(String login,String password) {
        log4j.startTestCase("loginNegativeTest2");
        log4j.info("Parameter login - " + login);
        log4j.info("Parameter password - " + password);
        loginPage.loginNotAtlassian(login, password);
        Assert.assertEquals(loginPage.getErrorMessage(), "There isn't an account for this email",
                "The error message is not '" + "There isn't an account for this email" +"'");
        log4j.info("-----Test case was finished-----");

    }
    @Test
    public void loginNegativePasswordIncorrect() {
        log4j.startTestCase("loginNegativePasswordIncorrect");
        log4j.info("Parameter login - " + LOGIN);
        log4j.info("Parameter password - " + PASSWORD+"1");
        loginPage.loginAsAtlassian(LOGIN, PASSWORD+"1");
        log4j.info("-----Test case was finished-----");
        Assert.assertTrue(loginPage.getAtlassianErrorMessage().contains("Incorrect email address and"),
                 "The error message is not contains the text 'Incorrect email address and'");
    }
    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderFirst")
    public void loginPositive(String login, String password)  {
        log4j.startTestCase("loginPositive");
        log4j.info("Parameter login - " + login);
        log4j.info("Parameter password - " + password);
        loginPage.loginAsAtlassian(login,password);
        boardsPage.waitUntilPageIsLoaded();
        log4j.info("-----Test case was finished-----");
        Assert.assertTrue(boardsPage.getBoardsIconName().equals("Boards"),"The text on the button is not 'Board'");
    }


}
