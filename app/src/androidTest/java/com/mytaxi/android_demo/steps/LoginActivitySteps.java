package com.mytaxi.android_demo.steps;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.AuthenticationActivity;
import com.mytaxi.android_demo.pages.AuthenticationPage;
import com.mytaxi.android_demo.pages.BasePage;
import com.mytaxi.android_demo.pages.MainPage;

import org.junit.Rule;
import org.junit.runner.RunWith;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by DilshanF on 9/8/2018.
 */
@SuppressWarnings("JUnitTestCaseWithNoTests")
@RunWith(AndroidJUnit4.class)
public class LoginActivitySteps {
    private BasePage mCurrentPage;
    private IdlingResource mIdlingResource;
    private Activity mActivity;
    @Rule
    public ActivityTestRule<AuthenticationActivity> mActivityRule = new ActivityTestRule<>(
            AuthenticationActivity.class);


    @Before
    public void setUp() throws Exception {
        mActivity = mActivityRule.launchActivity(new Intent()); // Start Activity before each test scenario
        assertNotNull(mActivity);

    }

    @After
    public void tearDown() {
        mActivityRule.finishActivity();
    }

    @Given("^I see the login page$")
    public void i_see_the_login_page() {
        mCurrentPage = new AuthenticationPage();
    }

    @When("^I login with user name \"(.+)\" and password \"(.+)\"$")
    public void i_login_with_username_and_password(final String userName, final String password) throws InterruptedException {
        mCurrentPage = mCurrentPage.is(AuthenticationPage.class).doLogin(userName, password);
    }

    @Then("^I see the main page$")
    public void i_see_the_welcome_page() {
        mCurrentPage.is(MainPage.class);
    }

    @When("^I enter userName \"(.*?)\"$")
    public void I_enter_userName(String userName) {
        mCurrentPage.is(AuthenticationPage.class).inputUserName(userName);
    }

    @When("^I enter passwrod \"(.*?)\"$")
    public void I_enter_password(String password) {
        mCurrentPage.is(AuthenticationPage.class).inputPassword(password);
    }

    @And("^I click Login button$")
    public void I_press_submit_button() {
        mCurrentPage.is(AuthenticationPage.class).clickLoginButton();
    }

    @Then("^I should see error message$")
    public void I_see_error() throws InterruptedException {
        try {
            mCurrentPage.is(AuthenticationPage.class).verifyErrorMessage();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
