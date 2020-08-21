package com.mystihgreeh.mareu;

import android.app.Activity;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.*;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import com.mystihgreeh.mareu.DI.Injection;
import com.mystihgreeh.mareu.events.DeleteReunionEvent;
import com.mystihgreeh.mareu.model.Reunion;
import com.mystihgreeh.mareu.service.ReunionApiService;
import com.mystihgreeh.mareu.view.ReunionList;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.security.AllPermission;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(AndroidJUnit4.class)
public class ReunionListTest {

    //This is fixed
    private static int ITEMS_COUNT = 5;
    private ReunionList mReunionList;


    @Rule
    public ActivityTestRule<ReunionList> mActivityRule = new ActivityTestRule(ReunionList.class);

    @Before
    public void setUp() {
        mReunionList = mActivityRule.getActivity();
        assertThat(mReunionList, notNullValue());
    }

    //Test that the recyclerview display at least one item in the list
    @Test
    public void ReunionListShouldNotBeEmpty() {
        Espresso.onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(matches(hasMinimumChildCount(1)));
    }

    //Test if when we delete an item, the item is no more shown
    @Test
    public void ReunionListDeleteAction() {
        // Given : we remove the element at position 2
        //Espresso.onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        //When perform a click on the delete icon
        //Espresso.onView(allOf(withId(R.id.recyclerView), isDisplayed())).perform(actionOnItemPosition(1, new DeleteViewAction()));
        //Then the number of element is now 4
        //Espresso.onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT-1));

    }
    

    //Filters tests

}
