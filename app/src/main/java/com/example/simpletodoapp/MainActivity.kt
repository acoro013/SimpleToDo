package com.example.simpletodoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.widget.Button
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOError
import java.io.IOException
import java.nio.charset.Charset


//26:02

//handle UI
class MainActivity : AppCompatActivity() {

    //val/values are variables you will never change again
    //once we turn a val into a var, we can reassign its contents
    var listOfTasks = mutableListOf<String>()
    //lateinit, were gonna initialize it later
    lateinit var adapter : TaskItemAdapter

    lateinit var editor : editItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //variable that holds the long onClickListener and after that we have the code to
        //implememnt what we want to happen
        val onLongClickListener = object : TaskItemAdapter.OnLongClickListener{
            override fun onLongItemClicked(position: Int) {
                //1 remove the item from list
                listOfTasks.removeAt(position)
                //2 notify the adapter out data has changed
                adapter.notifyDataSetChanged()

                saveItems()
            }
        }
        
        /*
        //create a Val to when a short click is done, the user goes to a separate activity
        val onClickListener = object : editItem.OnClickListener{
            override fun onClickListener(position: Int) {
                val i = Intent(this@MainActivity, EditActivity::class.java)
                startActivity(i)


            }

            override fun onItemClicked(position: Int) {
                TODO("Not yet implemented")
            }
        }

        //need to pass the text of the item that has been clicked on and show it in the edit text
*/




        /*1. lets detect when user clicks on add button
        find a view button with the id "button"
        findViewById<Button>(R.id.button).setOnClickListener{
            //code goes in here once this button is clicked
            //log appears in logcat so we can make sure the button is being pressed
            //Log.i("caren", "user clicked on button")
        }*/


        loadItems()

        // Lookup the recyclerview in activity layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        //create adapter passing in the sample user data
        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)

        //attach the adapter to the recycler to populate items
        recyclerView.adapter = adapter

        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)



        val inputTextField = findViewById<EditText>(R.id.addTaskField)

        //set up the button and input field so the user can enter a task and add it to the list
        //get a reference to the button
        //and then set an onClickListener
        findViewById<Button>(R.id.button).setOnClickListener{
            //1. grab the text the user has inputted into @id/addTaskField
            val userInputtedTask = inputTextField.text.toString()

            //2. add the string to our list of tasks: listOf Tasks
            listOfTasks.add(userInputtedTask)

            //Notify the adapter that our data has een updated
            adapter.notifyItemInserted(listOfTasks.size - 1)

            //3 reset text field
            inputTextField.setText("")

            //
            saveItems()
        }

        findViewById<Button>(R.id.button2).setOnClickListener{
            val i = Intent(this@MainActivity, EditActivity::class.java)
            startActivity(i)
        }

    }

    // save the data that the user inputted
    //saving data by writing and reading from a file

    //Get the file we need
    fun getDataFile() : File {

        //Every line is going to represent a specific task in our list of tasks
        return File(filesDir, "data.txt")
    }

    //another method to load the times by reading every line in the data file
    fun loadItems() {
        //Charset is Character set
        try {
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        }catch (ioException : IOException) {
            ioException.printStackTrace()
        }
    }

    //method to save items by writing them into our data file
    //using a try catch statement since we are using files
    //incase it dont exist or it is currupted
    fun saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), listOfTasks)
        }catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }



}