package com.mytaxi.android_demo.cucumber.steps;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.AuthenticationActivity;
import com.mytaxi.android_demo.cucumber.pages.AuthenticationPage;
import com.mytaxi.android_demo.cucumber.pages.BasePage;
import com.mytaxi.android_demo.cucumber.pages.MainPage;

import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.assertNotNull;
/**
 * Created by DilshanF on 9/8/2018.
 */
@SuppressWarnings("JUnitTestCaseWithNoTests")
@RunWith(AndroidJUnit4.class)
public class LoginActivitySteps {

    private BasePage mCurrentPage;
    private Activity mActivity;
    @Rule
    public ActivityTestRule<AuthenticationActivity> mActivityRule = new ActivityTestRule<>(
            AuthenticationActivity.class,false,false);

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityRule.launchActivity(new Intent()); // Start Activity before each test scenario
        assertNotNull(mActivity);

    }
    @Given("^I see the login page$")
    public void i_see_the_login_page() {
        mCurrentPage = new AuthenticationPage();
    }

    @When("^I login with user name \"(.+)\" and password \"(.+)\"$")
    public void i_login_with_username_and_password(final String userName, final String password) {
        mCurrentPage = mCurrentPage.is(AuthenticationPage.class).doLogin(userName, password);
    }

    @Then("^I see the main page$")
    public void i_see_the_welcome_page() {
        mCurrentPage.is(MainPage.class);
    }

}
