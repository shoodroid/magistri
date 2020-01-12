package cz.shoodroid.magistri.Model.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import cz.shoodroid.magistri.Model.Entities.Class

@Dao
interface ClassDao {
    @Query("SELECT * FROM class ORDER BY name ASC")
    fun getClasses(): LiveData<List<Class>>

    @Insert(onConflict = IGNORE)
    suspend fun insert(cl: Class)

    @Query("DELETE FROM class")
    suspend fun deleteAll()
}