package cz.shoodroid.magistri.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cz.shoodroid.magistri.Adapters.ClassesAdapter
import cz.shoodroid.magistri.R
import cz.shoodroid.magistri.ViewModel.ClassViewModel

import kotlinx.android.synthetic.main.activity_classes.*
import kotlinx.android.synthetic.main.content_classes.*
import com.google.firebase.database.FirebaseDatabase
import cz.shoodroid.magistri.Model.Entities.Class

class ClassesActivity : AppCompatActivity() {

    private lateinit var adapter: ClassesAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var classViewModel: ClassViewModel

    companion object {
        fun createIntent(context: Context): Intent
        {
            return Intent(context, ClassesActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classes)
        setSupportActionBar(toolbar)

        adapter = ClassesAdapter(this)
        layoutManager = LinearLayoutManager(this)

        rvClasses.adapter = adapter
        rvClasses.layoutManager = layoutManager
        classViewModel = ViewModelProvider(this).get(ClassViewModel::class.java)
        classViewModel.allClasses.observe(this, Observer { classes ->
            // Update the cached copy of the words in the adapter.
            classes?.let { adapter.setClasses(it) }
        })
        fab.setOnClickListener { view ->
//            Toast.makeText(this, "Bla", Toast.LENGTH_LONG).show()
//            classViewModel.insert(Class(100, "Test"))

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("classes")
            val x = listOf<Class>(Class(1, "E1.A"), Class(2, "E2.A"))
            myRef.setValue(x)
        }
    }

}
