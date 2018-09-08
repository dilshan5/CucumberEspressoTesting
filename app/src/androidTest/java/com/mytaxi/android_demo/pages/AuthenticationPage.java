package com.mytaxi.android_demo.pages;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.allOf;

import com.mytaxi.android_demo.R;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.Collection;

/**
 * Created by DilshanF on 9/8/2018.
 */

public class AuthenticationPage extends BasePage {

    /**
     * The constructor verifies that we are on the correct page by checking
     * the existence of the unique identifier elements of the page/view
     */
    public AuthenticationPage() {
        onView(withId(R.id.auth_activity)).check(matches(isDisplayed()));
    }

    /**
     * Perform the login and return the next Welcome page/view
     *
     * @param userName Name of the user to login
     * @param password Password of the user to login
     * @return Welcome page/view
     */
    public MainPage doLogin(String userName, String password)throws InterruptedException {
        onView(withId(R.id.edt_username)).perform(typeText(userName));
        onView(withId(R.id.edt_password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        Thread.sleep(3000);
        return new MainPage();
    }

    public void inputUserName(String userName) {
    /*    onView(allOf(withId(R.id.edt_username),withHint("Username")))
                .perform(typeText(userName), closeSoftKeyboard());*/
        onView(withId(R.id.edt_username)).perform(typeText(userName));
    }

    public void inputPassword(String password) {
  /*     onView(allOf(withId(R.id.edt_password),withHint("Password")))
                .perform(typeText(password), closeSoftKeyboard())
                .check(matches(isPasswordHidden()));*/
        onView(withId(R.id.edt_password)).perform(typeText(password), closeSoftKeyboard())
                .check(matches(isPasswordHidden()));
    }

    public void clickLoginButton() {
        onView(allOf(withId(R.id.btn_login),withText("Login"))).perform(click());
    }

    public void verifyErrorMessage() throws InterruptedException {
        //Check the toast message
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("Login failed")))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

    private Matcher<View> isPasswordHidden() {
        return new BoundedMatcher<View, EditText>(EditText.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("Password is not hidden");
            }

            @Override
            protected boolean matchesSafely(EditText item) {
                //returns true if password is hidden
                return item.getTransformationMethod() instanceof PasswordTransformationMethod;
            }
        };
    }

    //This method will return the current activity
    private Activity getActivityInstance() {
        final Activity[] activity = new Activity[1];
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Activity currentActivity = null;
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                if (resumedActivities.iterator().hasNext()) {
                    currentActivity = (Activity) resumedActivities.iterator().next();
                    activity[0] = currentActivity;
                }
            }
        });

        return activity[0];
    }
}
