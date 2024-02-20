package com.bayu07750.chooseyourinterests.data

import com.bayu07750.chooseyourinterests.util.Data
import kotlinx.coroutines.flow.flowOf

class InterestsRepositoryImp : InterestsRepository {

    override fun getInterests() = flowOf(Data.dummyInterests)
}