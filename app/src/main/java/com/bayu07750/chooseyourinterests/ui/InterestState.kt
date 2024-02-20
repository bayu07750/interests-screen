package com.bayu07750.chooseyourinterests.ui

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Stable
class InterestState(
    val id: String,
    val name: String,
    initialChecked: Boolean = false,
) {
    var checked by mutableStateOf(initialChecked)
}