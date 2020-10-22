package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;


public class ProfileVisibilityTest extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qaHaifa7CurrentBoard;
    HomePageHelper homePage;
    MenuPageHelper menuPage;
    ProfileVisibilityHelper profileVisibility;

    @BeforeMethod
    public void initTests()  {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        qaHaifa7CurrentBoard = new CurrentBoardPageHelper(driver,"QA Haifa7");
        menuPage = PageFactory.initElements(driver, MenuPageHelper.class);
        profileVisibility = PageFactory.initElements(driver, ProfileVisibilityHelper.class);

        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded()
                .loginAsAtlassian(LOGIN,PASSWORD);
        boardsPage.waitUntilPageIsLoaded()
                .openCurrentBoardPage("QA Haifa7");
        qaHaifa7CurrentBoard.waitUntilPageIsLoaded();
        qaHaifa7CurrentBoard.openMenuPage();
        menuPage.waitUntilPageIsLoaded();
        menuPage .openProfileVisibility();
        profileVisibility.waitUntilPageIsLoaded();
    }


    @Test
    public void isProfileVisibilityPage(){
        Assert.assertEquals(profileVisibility.getProfileVisibilityTabName(), "Profile and Visibility");
    }

    @Test
    public void userNameVerification(){
        String titleMenu = profileVisibility.getTitleMenuIcon();
        String userNameInTitle = titleMenu.substring(titleMenu.indexOf("(")+1,titleMenu.length()-1);
        String userName = profileVisibility.getUserName();
        Assert.assertEquals(userNameInTitle, userName);
    }
}