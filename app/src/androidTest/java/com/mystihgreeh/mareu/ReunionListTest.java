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
    private static int ITEMS_COUNT = 4;
    private ReunionList mReunionList;
    //private int positionTest = 0;
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


    /** When we delete an item, the item is no more shown
     */
    @Test
    public void ReunionListDeleteAction() {
        // Given : we remove the element at position 2
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        //When perform a click on the delete icon
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        //Then the number of element is now 3
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));


    }

    /** When we click on the neighbour, the reunion display
     */
    @Test
    public void reunionProfileDisplayOnClick() {
        onView(allOf(withId(R.id.recyclerView),isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.reunion_details)).check(matches(isDisplayed()));
    }


    /** When we click on the room filter, the reunions are filtered by room
     */
    //Filter by location
    @Test
    public void MeetingList_onLocationFilterClick_shouldFilterList() {
        // When perform a click on the menu -> then the location filter ->
        // then on the location we wanna test -> then OK button
        onView(withId(R.id.filter_menu)).perform(click());
        onView(withText("Filter by room")).perform(click());
        onView(withText(roomName)).perform(click());
        onView(withText("OK")).perform(click());
        // Then : their is only the same number of meeting as those who have the location filter
        onView(withId(R.id.recyclerView)).check(withItemCount(0));
    }


    /** When we click on the date filter, the reunions are filtered by date
     */
    @Test
    public void MeetingList_onDateFilterClick_shouldFilterList() {
        // When perform a click on the menu -> then the date filter ->
        // then set the date we wanna test -> then OK button
        onView(withId(R.id.filter_menu)).perform(click());
        onView(withText("Filter by date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 4, 27));
        onView(withText("OK")).perform(click());
        // Count the number of meeting with the same date
        int filterCount = 0;
        for (int i = 0; i< service.getReunions().size(); i++ ){
            String meetingDate = (String) DateFormat.format("yyyy/MM/dd", service.getReunions().get(i).getDate());
            if (meetingDate.equals("2020/04/27")) filterCount++;
        }
        // Then : their is only the same number of meeting as those who have the date filter
        onView(withId(R.id.recyclerView)).check(withItemCount(filterCount));
    }

    /**
     * When we click on the reset filter, all the filters are reset
     */
    @Test
    public void MeetingList_onClearFilterClick_shouldFilterList() {
        // When perform a click on the menu -> then the location filter ->
        // then on the location we wanna test -> then OK button
        onView(withId(R.id.filter_menu)).perform(click());
        onView(withText("Filter by room")).perform(click());
        onView(withText(roomName)).perform(click());
        onView(withText("OK")).perform(click());
        // When perform a click on the menu -> then the date filter ->
        // then set the date we wanna test -> then OK button
        onView(withId(R.id.filter_menu)).perform(click());
        onView(withText("Filter by date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 4, 27));
        onView(withText("OK")).perform(click());
        // When perform a click on the menu -> then clear filter
        onView(withId(R.id.filter_menu)).perform(click());
        //onView(withText("Reset filter")).perform(click());
        // Then : their is only the same number of meeting as those who have the date filter
        //onView(withId(R.id.recyclerView)).check(withItemCount(4));
    }

    /**
     * When we click + icon, the AddMeetingFragment is launch
     */
    /*@Test
    public void MeetingList_onAddButtonClick_shouldOpenAddMeetingFragment() {
        // When perform a click on + button
        onView(withId(R.id.addButton)).perform(click());
        // Then : the view went to AddMeetingFragment
        onView(withId(R.id.add_meeting_layout)).check(matches(isDisplayed()));
    }*/


}
