package br.com.rsicarelli.openmovie;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;
import android.widget.EditText;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.rsicarelli.openmovie.home.HomeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by rodrigosicarelli on 5/4/16.
 */
@RunWith(AndroidJUnit4.class)
public class SimpleTest extends BaseActivityTestCase {

    @Rule
    public ActivityTestRule<HomeActivity> homeActivityActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Override
    public void setUp() {
        super.setUp();
        registerIdlingResource();
    }

    @Test
    public void shouldDoSomething() {
        onView(withId(R.id.action_search)).perform(click());
        onView(isAssignableFrom(EditText.class))
                .perform(typeText("Lord of rings"), pressKey(KeyEvent.KEYCODE_ENTER));
    }

    /**
     * Convenience method to register an IdlingResources with Espresso. IdlingResource resource is
     * a great way to tell Espresso when your app is in an idle state. This helps Espresso to
     * synchronize your test actions, which makes tests significantly more reliable.
     */
    private void registerIdlingResource() {
        Espresso.registerIdlingResources(
                homeActivityActivityTestRule.getActivity().getCountingIdlingResource());
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(
                homeActivityActivityTestRule.getActivity().getCountingIdlingResource());
    }
}
