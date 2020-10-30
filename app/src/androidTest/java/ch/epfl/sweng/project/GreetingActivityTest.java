package ch.epfl.sweng.project;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
public class GreetingActivityTest {
    private static final String TEST_NAME = "Alice";

    @Test
    public void usernameFromIntentIsDisplayedProperly() {
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), GreetingActivity.class);
        intent.putExtra(GreetingActivity.EXTRA_USER_NAME, TEST_NAME);

        // We don't have a rule here, because we need to control the content of the intent.
        // We use try-with-resource to automatically close the activity at the end of the test
        try (ActivityScenario<GreetingActivity> scenario = ActivityScenario.launch(intent)) {
            onView(ViewMatchers.withId(R.id.greetingMessage)).check(
                    ViewAssertions.matches(ViewMatchers.withText(containsString(TEST_NAME)))
            );
        }
    }
}
