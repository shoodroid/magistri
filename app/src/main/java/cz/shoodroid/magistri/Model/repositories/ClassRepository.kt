package cz.shoodroid.magistri.Model.repositories

import androidx.lifecycle.LiveData
import cz.shoodroid.magistri.Model.Database.ClassDao
import cz.shoodroid.magistri.Model.Entities.Class

class ClassRepository(private val classDao: ClassDao) {
    val allClasses: LiveData<List<Class>> = classDao.getClasses()

    suspend fun insert(cl: Class)
    {
        classDao.insert(cl)
    }
}