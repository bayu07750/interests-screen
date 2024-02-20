package com.bayu07750.chooseyourinterests.data

import java.util.UUID

data class Interest(
    val id: String,
    val name: String,
) {
    companion object {
        val ONLY_ID get() = Interest(
            id = UUID.randomUUID().toString(),
            name = "",
        )
    }
}