package cz.shoodroid.magistri.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cz.shoodroid.magistri.Model.Entities.Class
import cz.shoodroid.magistri.R

class ClassesAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<ClassesAdapter.ClassViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var classes = emptyList<Class>() // Cached copy of classes

    inner class ClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val classItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val itemView = inflater.inflate(R.layout.row_classes, parent, false)
        return ClassViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val current = classes[position]
        holder.classItemView.text = current.name
    }

    internal fun setClasses(classes: List<Class>) {
        this.classes = classes
        notifyDataSetChanged()
    }

    override fun getItemCount() = classes.size
}