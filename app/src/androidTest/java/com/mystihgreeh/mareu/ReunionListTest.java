package com.mystihgreeh.mareu;

import android.text.format.DateFormat;
import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.mystihgreeh.mareu.DI.Injection;
import com.mystihgreeh.mareu.controler.ReunionList;
import com.mystihgreeh.mareu.service.ReunionApiService;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.mystihgreeh.mareu.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(AndroidJUnit4.class)
public class ReunionListTest {

    // This is fixed
    private static int ITEMS_COUNT_ONE = 5;
    private static int ITEMS_COUNT_TWO = 4;
    private int positionTest = 0;
    private String roomName = "Mario"; // Can be any other room name
    private ReunionApiService service = Injection.getNewInstanceApiService();

    @Rule
    public ActivityTestRule<ReunionList> mActivityRule =
            new ActivityTestRule<>(ReunionList.class);

    @Before
    public void setUp() {
        service = Injection.getNewInstanceApiService();
        ReunionList activity = mActivityRule.getActivity();
        assertThat(activity, notNullValue());
    }



    /**Test that the recyclerview display at least one item in the list
     * */
    @Test
    public void ReunionListShouldNotBeEmpty() {
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(matches(hasMinimumChildCount(1)));
    }

    /** When we click on the reunion, the reunion details display
     */
    @Test
    public void reunionProfileDisplayOnClick() {
        onView(allOf(withId(R.id.recyclerView),isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.reunion_details)).check(matches(isDisplayed()));
    }


    @Test
    public void newReunionIsCreated() {
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT_TWO));
        onView(withId(R.id.addButton)).perform(click());
        onView(withId(R.id.reunion_object)).check(matches(isDisplayed()));
        onView(withId(R.id.date)).perform(click());
        onView(withText("OK")).perform(click());
        onView(withId(R.id.time)).perform(click());
        onView(withText("OK")).perform(click());
        onView(withId(R.id.reunion_object)).perform(scrollTo(), replaceText("Reunion Z"), closeSoftKeyboard());
        onView(withId(R.id.emails)).perform(scrollTo(), replaceText("charlotte@lamzone.fr"), closeSoftKeyboard());
        onView(withId(R.id.save)).perform(click());
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT_TWO + 1));
    }



    /** When we delete an item, the item is no more shown
     */
    @Test
    public void ReunionListDeleteAction() {
        // Given : we remove the element at position 2
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT_ONE));
        //When perform a click on the delete icon
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        //Then the number of element is now 3
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT_ONE - 1));
    }



    /** When we click on the room filter, the reunions are filtered by room
     */
    //Filter by location
    @Test
    public void MeetingList_onLocationFilterClick_shouldFilterList() {
        // Filter a room
        onView(withId(R.id.filter_menu)).perform(click());
        onView(withText("Filter by room")).perform(click());
        onView(withText(roomName)).perform(click());
        onView(withText("OK")).perform(click());
        //Only the reunion with the same room as the one filtered is displayed
        onView(withId(R.id.recyclerView)).check(withItemCount(0));
    }



    /** When we click on the date filter, the reunions are filtered by date
     */
    @Test
    public void MeetingList_onDateFilterClick_shouldFilterList() {
        // Filter a date
        onView(withId(R.id.filter_menu)).perform(click());
        onView(withText("Filter by date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 9, 6));
        onView(withText("OK")).perform(click());
        // Count the number of reunion with the same date
        int filterCount = 0;
        for (int i = 0; i< service.getReunions().size(); i++ ){
            String meetingDate = (String) DateFormat.format("yyyy/MM/dd", service.getReunions().get(i).getDate());
            if (meetingDate.equals("2020/09/06")) filterCount++;
        }
        // Only the reunion with the same date as the one filtered is displayed
        onView(withId(R.id.recyclerView)).check(withItemCount(filterCount));
    }



    /**
     * When we click on the reset filter, all the filters are reset
     */
    @Test
    public void MeetingList_onClearFilterClick_shouldFilterList() {
        // Filtering a room
        onView(withId(R.id.filter_menu)).perform(click());
        onView(withText("Filter by room")).perform(click());
        onView(withText(roomName)).perform(click());
        onView(withText("OK")).perform(click());
        // Filtering a date
        onView(withId(R.id.filter_menu)).perform(click());
        onView(withText("Filter by date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 9, 6));
        onView(withText("OK")).perform(click());
        // Cleaning filters
        onView(withId(R.id.filter_menu)).perform(click());
        onView(withText("Reset filters")).perform(click());
        // All the meeting display again
        onView(withId(R.id.recyclerView)).check(withItemCount(4));
    }

}
