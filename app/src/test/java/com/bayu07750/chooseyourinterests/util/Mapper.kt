package com.bayu07750.chooseyourinterests.util

import com.bayu07750.chooseyourinterests.data.Interest
import com.bayu07750.chooseyourinterests.ui.InterestState

fun InterestState.asInterest() = Interest(id, name)

fun List<InterestState>.asInterests() = map { it.asInterest() }