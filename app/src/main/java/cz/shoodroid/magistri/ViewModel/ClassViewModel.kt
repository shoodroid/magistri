package cz.shoodroid.magistri.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import cz.shoodroid.magistri.Model.Database.ClassDatabase
import cz.shoodroid.magistri.Model.repositories.ClassRepository
import cz.shoodroid.magistri.Model.Entities.Class
import kotlinx.coroutines.launch

class ClassViewModel(application: Application) : AndroidViewModel(application)
{
    private lateinit var repository: ClassRepository

    lateinit var allClasses: LiveData<List<Class>>

    init {
        val classDao = ClassDatabase.getDatabase(application, viewModelScope).classDao()
        repository = ClassRepository(classDao)
        allClasses = repository.allClasses
    }

    fun insert(cl: Class) = viewModelScope.launch {
        repository.insert(cl)
    }
}