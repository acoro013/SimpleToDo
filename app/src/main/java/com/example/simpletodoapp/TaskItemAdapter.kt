package com.example.simpletodoapp

import android.service.autofill.OnClickAction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView

/*
bridge that tells recyclerview exactly how to display the data we give it
its gonna take our listOfStrings and render it to out recyclerView
 */

//the : means "Extends"
class TaskItemAdapter(val listOfItems : List<String>,
                      val longClickListener: OnLongClickListener)
    : RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {

    interface OnLongClickListener{
        //function
        fun onLongItemClicked(position: Int)
    }

    // Usually involves inflating a layout from XML and returning the holder
    //creates layout of view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    //involves populating data into the item through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /* Example
        Get the data model based on position
        val contact: Contact = mContacts.get(position)
        // Set item views based on your views and data model
        val textView = viewHolder.nameTextView
        textView.setText(contact.name)
        val button = viewHolder.messageButton
        button.text = if (contact.isOnline) "Message" else "Offline"
        button.isEnabled = contact.isOnline*/

        //get data model based on position
        val item = listOfItems.get(position)

        holder.textView.text = item

    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    //provide a direct reference to each fo the views withing an data item
    //used to cache the views within the item layout for fast access
    //itemview is essentially the row of the item being showed in the recylcler view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //store referances to elements in our layout
        val textView: TextView

        init {
            textView = itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener{
                longClickListener.onLongItemClicked(adapterPosition)
                //Log.i("Caren", "long clicked on item: " + adapterPosition)
                true
            }
        }
    }
}