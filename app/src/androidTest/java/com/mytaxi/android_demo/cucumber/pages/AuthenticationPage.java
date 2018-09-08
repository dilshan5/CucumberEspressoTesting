package com.mytaxi.android_demo.cucumber.pages;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import com.mytaxi.android_demo.R;
import android.support.test.rule.ActivityTestRule;
/**
 * Created by DilshanF on 9/8/2018.
 */

public class AuthenticationPage extends BasePage{

    /**
     * The constructor verifies that we are on the correct page by checking
     * the existence of the unique identifier elements of the page/view
     */
    public AuthenticationPage(){
        onView(withId(R.id.auth_activity)).check(matches(isDisplayed()));
    }
    /**
     * Perform the login and return the next Welcome page/view
     * @param userName Name of the user to login
     * @param password Password of the user to login
     * @return Welcome page/view
     */
    public MainPage doLogin(String userName, String password) {
        onView(withId(R.id.edt_username)).perform(typeText(userName));
        onView(withId(R.id.edt_password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        return new MainPage();
    }

    public void inputUserName(String userName){
        onView(withId(R.id.edt_username)).perform(typeText(userName), closeSoftKeyboard());
    }

    public void inputPassword(String password){
        onView(withId(R.id.edt_password)).perform(typeText(password), closeSoftKeyboard());
    }

    public void clickLoginButton(){
        onView(withId(R.id.btn_login)).perform(click());
    }
}
