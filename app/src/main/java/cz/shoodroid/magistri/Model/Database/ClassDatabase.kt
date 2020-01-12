package cz.shoodroid.magistri.Model.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import cz.shoodroid.magistri.Model.Entities.Class
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Class::class), version = 1, exportSchema = false)
abstract class ClassDatabase : RoomDatabase() {
    abstract fun classDao(): ClassDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ClassDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ClassDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ClassDatabase::class.java,
                    "class"
                ).addCallback(ClassDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }

        private class ClassDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.classDao())
                    }
                }
            }

            suspend fun populateDatabase(classDao: ClassDao) {
           /*
                // Delete all content here.
                classDao.deleteAll()

                // Add sample words.
                classDao.insert(Class(1, "E1.A"))
                classDao.insert(Class(2, "I1.B"))
                classDao.insert(Class(3, "I1.C"))
                classDao.insert(Class(4, "I1.D"))
                classDao.insert(Class(5, "E2.A"))
                classDao.insert(Class(6, "I2.B"))
                classDao.insert(Class(7, "I2.C"))
                classDao.insert(Class(8, "I2.D"))
                classDao.insert(Class(9, "E3.A"))
                classDao.insert(Class(10, "I3.B"))
                classDao.insert(Class(11, "I3.C"))
                classDao.insert(Class(12, "I3.D"))
                classDao.insert(Class(13, "L3.E"))
                classDao.insert(Class(14, "E4.A"))
                classDao.insert(Class(15, "E4.B"))
                classDao.insert(Class(16, "I4.C"))
                classDao.insert(Class(17, "I4.D"))
                classDao.insert(Class(18, "L4.E"))

            */
            }
        }
    }
}