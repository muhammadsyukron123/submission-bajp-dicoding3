package com.syukron.submission1_bajp_dicoding.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.syukron.submission1_bajp_dicoding.R
import com.syukron.submission1_bajp_dicoding.ui.main.MainActivity
import com.syukron.submission1_bajp_dicoding.utils.DummyData
import com.syukron.submission1_bajp_dicoding.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {

    private val dataMoviesDummy = DummyData.generateDummyMovies()
    private val dataTVShowsDummy = DummyData.generateDummySeries()

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }


    @Test
    fun loadAllMovies() {
        onView(withId(R.id.rv_movie_fragment)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie_fragment)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataMoviesDummy.size
            )
        )
    }

    @Test
    fun loadAllTvShows() {
        onView(withText("TV Show")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow_fragment)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tvshow_fragment)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataTVShowsDummy.size
            )
        )
    }

    @Test
    fun loadDetailMovies() {
        onView(withText("Movie")).perform(ViewActions.click())
        onView(withId(R.id.rv_movie_fragment)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                1
            )
        )
        onView(withId(R.id.rv_movie_fragment)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.tv_detail_movie_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_overview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_genre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_year)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withText("TV Show")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow_fragment)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.tv_detail_movie_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_overview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_genre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_year)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadFavoritedDetailMovie() {
        onView(withText("Movie")).perform(ViewActions.click())
        onView(withId(R.id.rv_movie_fragment)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                1
            )
        )
        onView(withId(R.id.rv_movie_fragment)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.fab_favorite_movie)).perform(ViewActions.click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        onView(withText("Movie")).perform(ViewActions.click())
        onView(withId(R.id.rv_movie_fragment)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie_fragment)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.tv_detail_movie_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_overview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_genre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_year)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.fab_favorite_movie)).perform(ViewActions.click())
    }

    @Test
    fun loadFavoritedDetailClickedTvShow() {
        onView(withText("TV Show")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow_fragment)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.fab_favorite_tvshow)).perform(ViewActions.click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        onView(withText("TV Show")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow_fragment)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tvshow_fragment)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.tv_detail_movie_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_overview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_genre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_year)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.fab_favorite_tvshow)).perform(ViewActions.click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadFavoritedDetailMovieOnFavoriteActivity() {
        onView(withText("Movie")).perform(ViewActions.click())
        onView(withId(R.id.rv_movie_fragment)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                1
            )
        )
        onView(withId(R.id.rv_movie_fragment)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.fab_favorite_movie)).perform(ViewActions.click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.favorite)).perform(ViewActions.click())
        onView(withId(R.id.rv_movie_fragment)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_movie_fragment)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.tv_detail_movie_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_overview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_genre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_year)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.fab_favorite_movie)).perform(ViewActions.click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())

    }

    @Test
    fun loadFavoritedDetailClickedTvShowOnFavoriteActivity() {
        onView(withText("TV Show")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow_fragment)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.fab_favorite_tvshow)).perform(ViewActions.click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.favorite)).perform(ViewActions.click())
        onView(withText("TV Show")).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow_fragment)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rv_tvshow_fragment)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.tv_detail_movie_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_overview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_genre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tv_detail_movie_year)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.fab_favorite_tvshow)).perform(ViewActions.click())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())

    }

}