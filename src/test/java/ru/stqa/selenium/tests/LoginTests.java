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
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver,BoardsPageHelper.class);
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded();
    }
    @Test
    public void loginNegativeLoginEmpty()  {
        loginPage.loginNotAtlassian("",PASSWORD)
                .pressLoginButton();
        Assert.assertEquals(loginPage.getErrorMessage(),"Missing email",
                "The text of the error message is not correct");
    }
    @Test
    public void loginNegativeTest() {
        loginPage.loginNotAtlassian("123456", PASSWORD);
        Assert.assertEquals(loginPage.getErrorMessage(), "There isn't an account for this username",
                "The error message is not 'There isn't an account for this username'");
    }
    @Test
    public void loginNegativePasswordIncorrect() {
       loginPage.loginAsAtlassian(LOGIN, PASSWORD+"1");
        Assert.assertTrue(loginPage.getAtlassianErrorMessage().contains("Incorrect email address and"),
                 "The error message is not contains the text 'Incorrect email address and'");
    }
    @Test(dataProviderClass = DataProviders.class,dataProvider = "dataProviderFirst")
    public void loginPositive(String login, String password)  {
        loginPage.loginAsAtlassian(login,password);
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertTrue(boardsPage.getBoardsIconName().equals("Boards"),"The text on the button is not 'Board'");
    }


}
