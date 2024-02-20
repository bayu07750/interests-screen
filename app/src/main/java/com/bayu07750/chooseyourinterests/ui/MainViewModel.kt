package com.bayu07750.chooseyourinterests.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bayu07750.chooseyourinterests.data.InterestsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val interestsRepository: InterestsRepository,
) : ViewModel() {

    private var _interests = MutableStateFlow<List<InterestState>>(emptyList())
    val interests = _interests.asStateFlow()

    init {
        viewModelScope.launch {
            interestsRepository.getInterests().collect { interests ->
                _interests.update {
                    interests.asSequence().map { interest ->
                        InterestState(interest.id, interest.name)
                    }.toList()
                }
            }
        }
    }
}