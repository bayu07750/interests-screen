package com.bayu07750.chooseyourinterests.data

import kotlinx.coroutines.flow.Flow

interface InterestsRepository {
    fun getInterests(): Flow<List<Interest>>
}