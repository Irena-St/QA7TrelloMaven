package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;

public class ActivityTest extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHaifa7CurrentBoard;
    HomePageHelper homePage;
    MenuPageHelper menuPage;
    ActivityPageHelper activityPage;

    @BeforeMethod
    public void initTests()  {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        qaHaifa7CurrentBoard = new CurrentBoardPageHelper(driver,"QA Haifa7");
 menuPage = PageFactory.initElements(driver, MenuPageHelper.class);
        activityPage = PageFactory.initElements(driver, ActivityPageHelper.class);

        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded()
                .loginAsAtlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        boardsPage.openCurrentBoardPage("QA Haifa7");
        qaHaifa7CurrentBoard.waitUntilPageIsLoaded();
    }
    @Test
    public void addingCardActionInActivity(){
        qaHaifa7CurrentBoard.addNewCardInFirstList("New Card");
        qaHaifa7CurrentBoard.openMenuPage();
        menuPage.waitUntilPageIsLoaded();
        menuPage.openActivityPage();
        activityPage.waitUntilPageIsLoaded();
        Assert.assertTrue(activityPage.getFirstRecordText().contains("New Card"));

    }

}


