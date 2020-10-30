package ch.epfl.sweng.project;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> testRule = new ActivityScenarioRule<>(MainActivity.class);

    // In general, it is good practice to keep test values in constant fields
    private static final String TEST_NAME = "Alice";

    @Test
    public void intentIsFiredWhenUserClicksOnButton() {
        Intents.init();

        onView(ViewMatchers.withId(R.id.mainName)).perform(clearText(), typeText(TEST_NAME));
        closeSoftKeyboard();
        onView(ViewMatchers.withId(R.id.mainGoButton)).perform(click());

        Intents.intended(
            Matchers.allOf(
                IntentMatchers.hasComponent(GreetingActivity.class.getName()),
                IntentMatchers.hasExtra(GreetingActivity.EXTRA_USER_NAME, TEST_NAME)
            )
        );

        Intents.release();
    }
}
