package com.moekyawaung.portfolio.data.repository

import com.moekyawaung.portfolio.data.model.*
import com.moekyawaung.portfolio.data.local.*
import kotlinx.coroutines.flow.*

class PortfolioRepository {
    
    private val database = PortfolioDatabase.getInstance()
    
    fun getProjects(): Flow<List<Project>> {
        return database.projectDao().getAllProjects()
    }
    
    fun getSkills(): Flow<List<Skill>> {
        return database.skillDao().getAllSkills()
    }
    
    fun getCertificates(): Flow<List<Certificate>> {
        return database.certificateDao().getAllCertificates()
    }
    
    suspend fun insertProject(project: Project) {
        database.projectDao().insertProject(project)
    }
    
    suspend fun updateProject(project: Project) {
        database.projectDao().updateProject(project)
    }
}
