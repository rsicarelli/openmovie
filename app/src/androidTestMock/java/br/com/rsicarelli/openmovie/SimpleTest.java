package br.com.rsicarelli.openmovie;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.rsicarelli.openmovie.home.HomeActivity;

/**
 * Created by rodrigosicarelli on 5/4/16.
 */
@RunWith(AndroidJUnit4.class)
public class SimpleTest extends BaseActivityTestCase {

    @Before
    public void setUp(){

    }

    @Rule
    public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void shouldDoSomething() {
        Espresso.onView(ViewMatchers.withText("Mock!")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
