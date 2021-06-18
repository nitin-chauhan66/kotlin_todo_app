package com.example.kotlintodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.*

class MainActivity : AppCompatActivity() {
//    By declare variables @lateinit we kotlin compiler that the variable will be initialized before using them
    private lateinit var add: Button
    private lateinit var editText: EditText
    private lateinit var listView: ListView
    private lateinit var clear: Button
    private lateinit var delete: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initializing the array lists and the adapter
        val itemlist = arrayListOf<String>()
        var adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_multiple_choice, itemlist
        )

        add = findViewById(R.id.add)// add button
        editText = findViewById(R.id.editText) // editText
        listView = findViewById(R.id.listView) //List View
        clear = findViewById(R.id.clear)//Clear Button to clear arraylist
        delete = findViewById(R.id.delete)// delete button to delete selected items
        // Adding the items to the list when the add button is pressed
        add.setOnClickListener {
            val text: String = editText.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(this, "Item can't empty text", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            itemlist.add(editText.text.toString())  // add text enter by the user to arraylist

            listView.adapter = adapter

            adapter.notifyDataSetChanged() // refresh adapter to update new changes on the UI

            editText.text.clear() // clear the entered text after adding item to the list

        }
        // Clearing all the items in the list when the clear button is pressed
        clear.setOnClickListener {

            itemlist.clear()
            adapter.notifyDataSetChanged()
        }
        // Adding the toast message to the list when an item on the list is pressed
        listView.setOnItemClickListener { _, _, i, _ ->
            Toast.makeText(
                this,
                "You Selected the item --> " + itemlist.get(i),
                Toast.LENGTH_SHORT
            ).show()
        }
        // Selecting and Deleting the items from the list when the delete button is pressed
        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }


    }
}