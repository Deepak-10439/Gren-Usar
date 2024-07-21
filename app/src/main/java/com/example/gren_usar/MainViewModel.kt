package com.example.gren_usar

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(GrenUiState())
    val uiState: StateFlow<GrenUiState> = _uiState.asStateFlow()

    fun updateClickStatus(updatedText: String) {
        _uiState.update {
            it.copy(
                clickStatus = updatedText
            )
        }
    }

    fun updateSelectedCategory(updatedCategory: Int) {
        _uiState.update {
            it.copy(
                selectedCategory = updatedCategory
            )
        }
    }
}
