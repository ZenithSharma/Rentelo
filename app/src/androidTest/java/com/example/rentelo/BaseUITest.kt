package com.example.rentelo

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseUITest {
    val mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
        @Rule get
}