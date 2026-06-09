package com.moekyawaung.portfolio.viewModel

import androidx.lifecycle.*
import com.moekyawaung.portfolio.data.model.*
import com.moekyawaung.portfolio.data.repository.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class HomeViewModel : ViewModel() {
    
    private val repository = PortfolioRepository()
    
    // UI State
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    init {
        loadPortfolioData()
    }
    
    private fun loadPortfolioData() {
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)
            
            try {
                // Load data from Room or API
                val projects = repository.getProjects()
                val skills = repository.getSkills()
                val certificates = repository.getCertificates()
                
                _uiState.value = HomeUiState(
                    isLoading = false,
                    projects = projects,
                    skills = skills,
                    certificates = certificates
                )
            } catch (e: Exception) {
                _uiState.value = HomeUiState(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
    
    fun refreshData() {
        loadPortfolioData()
    }
}

// UI State Model
data class HomeUiState(
    val isLoading: Boolean = false,
    val projects: List<Project> = emptyList(),
    val skills: List<Skill> = emptyList(),
    val certificates: List<Certificate> = emptyList(),
    val error: String? = null
)
