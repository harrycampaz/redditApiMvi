package com.harrycampaz.redditapi.utils

import org.junit.Rule

open class BaseUnitTest {

    @get:Rule
    var coroutineScopeRule = MainCoroutineScopeRule()


}