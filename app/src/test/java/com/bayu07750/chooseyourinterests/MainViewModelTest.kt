package com.bayu07750.chooseyourinterests

import com.bayu07750.chooseyourinterests.data.InterestsRepository
import com.bayu07750.chooseyourinterests.ui.MainViewModel
import com.bayu07750.chooseyourinterests.util.Data
import com.bayu07750.chooseyourinterests.util.MainDispatcherRule
import com.bayu07750.chooseyourinterests.util.asInterests
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class MainViewModelTest {

    private val dummyInterests = Data.dummyInterests
    private val dummyInterestsFlow = flowOf(dummyInterests)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `get interestsState should not empty`() = runTest {
        val interestsRepository = mock<InterestsRepository>() {
            on { getInterests() } doReturn dummyInterestsFlow
        }
        val viewModel = MainViewModel(interestsRepository)
        val interestsState = viewModel.interests.value
        MatcherAssert.assertThat(interestsState.isNotEmpty(), CoreMatchers.`is`(true))
        MatcherAssert.assertThat(interestsState.asInterests(), CoreMatchers.`is`(dummyInterests))

        verify(interestsRepository).getInterests()
    }
}