package com.bayu07750.chooseyourinterests

import app.cash.turbine.test
import com.bayu07750.chooseyourinterests.data.Interest
import com.bayu07750.chooseyourinterests.data.InterestsRepository
import com.bayu07750.chooseyourinterests.data.InterestsRepositoryImp
import com.bayu07750.chooseyourinterests.util.Data
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class InterestRepositoryTest {

    private val dummyInterests = Data.dummyInterests
    private val emptyInterests = emptyList<Interest>()
    private val dummyInterestsFlow = flowOf(dummyInterests)

    @Mock
    private lateinit var interestsRepositoryImp: InterestsRepositoryImp

    private lateinit var interestsRepository: InterestsRepository

    @Before
    fun setUp() {
        interestsRepository = interestsRepositoryImp
    }

    @Test
    fun `get list interests should not empty`() = runTest {
        whenever(interestsRepositoryImp.getInterests()).thenReturn(dummyInterestsFlow)

        interestsRepository.getInterests().test {
            val interests = awaitItem()
            MatcherAssert.assertThat(interests.isNotEmpty(), CoreMatchers.`is`(true))
            MatcherAssert.assertThat(interests[0], CoreMatchers.`is`(dummyInterests[0]))
            MatcherAssert.assertThat(interests[1], CoreMatchers.`is`(dummyInterests[1]))
            awaitComplete()
        }

        Mockito.verify(interestsRepositoryImp).getInterests()
    }

    @Test
    fun `get list interest empty`() = runTest {
        whenever(interestsRepositoryImp.getInterests()).thenReturn(flowOf(emptyInterests))

        interestsRepository.getInterests().test {
            val interests = awaitItem()
            MatcherAssert.assertThat(interests.isEmpty(), CoreMatchers.`is`(true))
            awaitComplete()
        }

        Mockito.verify(interestsRepositoryImp).getInterests()
    }
}