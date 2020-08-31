package com.mystihgreeh.mareu;

import android.app.Activity;

import androidx.test.espresso.contrib.RecyclerViewActions;
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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.mystihgreeh.mareu.RecyclerViewItemCountAssertion.withItemCount;



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
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;


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

    /**Test that the recyclerview display at least one item in the list
     * */

    @Test
    public void ReunionListShouldNotBeEmpty() {
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(matches(hasMinimumChildCount(1)));
    }


    /** When we delete an item, the item is no more shown
     */
    @Test
    public void ReunionListDeleteAction() {
        // Given : we remove the element at position 2
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        //When perform a click on the delete icon
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        //Then the number of element is now 4
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));


    }

    /** When we click on the neighbour, the reunion display
     */
    @Test
    public void neighbourProfileDisplayOnClick() {
        onView(allOf(withId(R.id.recyclerView),isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        //onView(withId(R.id.reunion_presentation)).check(matches(isDisplayed()));
    }

    /** When we click on the reunion, the name of the reunion room display
     */
    @Test
    public void textViewDisplayRightName() {
        String reunionA = "Reunion A";
        //onView(withRecyclerView(R.id.recyclerView).atPosition(0)).check(matches(hasDescendant(withText(reunionA))));
        //Checking if the reunion details display on click
        //onView(withRecyclerView(R.id.recyclerView).atPosition(0)).perform(click());
        onView(withId(R.id.room)).check(matches(isDisplayed()));
        //Checking if the name displayed on the reunion details is the same as the reunion's one
        onView((allOf(withId(R.id.room),isDisplayed()))).check(matches(withText(reunionA)));
    }

    //Filters tests

}
