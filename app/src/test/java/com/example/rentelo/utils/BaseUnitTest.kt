package com.example.rentelo.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import org.junit.Rule

open class BaseUnitTest {

    @get: Rule
    var coroutineTestR = MainCoroutineScopeRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
}