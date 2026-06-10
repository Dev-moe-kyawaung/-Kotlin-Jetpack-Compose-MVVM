package com.moekyawaung.portfolio.data.local

import android.content.*
import androidx.room.*
import com.moekyawaung.portfolio.data.model.*
import kotlinx.coroutines.flow.*

@Database(
    entities = [Project::class, Skill::class, Certificate::class],
    version = 1,
    exportSchema = false
)
abstract class PortfolioDatabase : RoomDatabase() {
    
    abstract fun projectDao(): ProjectDao
    abstract fun skillDao(): SkillDao
    abstract fun certificateDao(): CertificateDao
    
    companion object {
        @Volatile
        private var instance: PortfolioDatabase? = null
        
        fun getInstance(context: Context): PortfolioDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context,
                    PortfolioDatabase::class.java,
                    "portfolio_database"
                )
                    .build()
                    .also { instance = it }
            }
        }
    }
}

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects ORDER BY created_at DESC")
    fun getAllProjects(): Flow<List<Project>>
    
    @Insert
    suspend fun insertProject(project: Project)
    
    @Update
    suspend fun updateProject(project: Project)
}

@Entity(tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val imageUrl: String,
    val githubUrl: String,
    val demoUrl: String?,
    val tags: List<String>,
    val createdAt: Long = System.currentTimeMillis()
)
