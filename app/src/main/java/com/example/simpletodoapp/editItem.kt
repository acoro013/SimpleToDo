package com.example.simpletodoapp

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class editItem(val listOfItems : List<String>,
    val onClickListener: editItem.OnClickListener)
    : RecyclerView.Adapter<TaskItemAdapter.ViewHolder>()  {

    interface OnClickListener{
        //function
        fun onItemClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TaskItemAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //store referances to elements in our layout
        val textView: TextView

        init {
            textView = itemView.findViewById(android.R.id.text1)

            itemView.setOnClickListener{
                onClickListener.onItemClicked(adapterPosition)
                //Log.i("Caren", "long clicked on item: " + adapterPosition)
                true
            }
        }
    }

}