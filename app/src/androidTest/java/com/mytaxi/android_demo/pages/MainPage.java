package com.mytaxi.android_demo.pages;

import com.mytaxi.android_demo.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by DilshanF on 9/8/2018.
 */

public class MainPage extends BasePage {
    /**
     * The constructor verifies that we are on the correct page by checking
     * the existence of the unique identifier elements of the page/view
     */
    public MainPage() {
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));
    }
}
