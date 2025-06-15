package com.example.latihan_praktikum8_amandaputri;



import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;

import com.example.latihan_praktikum8_amandaputri.presentation.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testBottomNavigationSwitchFragment() {
        // Klik tab Konten
        Espresso.onView(withId(R.id.navigation_konten)).perform(ViewActions.click());

        // Cek apakah KontenFragment tampil
        Espresso.onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));
    }
}
