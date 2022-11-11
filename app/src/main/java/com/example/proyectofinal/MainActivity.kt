package com.example.proyectofinal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import java.util.ArrayList
import java.util.HashSet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.add_note) {

            // Going from MainActivity to NotesEditorActivity
            val intent = Intent(getApplicationContext(), NoteEditor::class.java)
            startActivity(intent)
            return true
        }
        return false
    }

    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView: ListView = findViewById(R.id.listView)
        val sharedPreferences: SharedPreferences =
            getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE)
        val set = sharedPreferences.getStringSet("notes", null) as HashSet<String?>?
        if (set == null) {
            notes.add("Example note")
        } else {
            notes = ArrayList<Any?>(set).toString()
        }

        // Using custom listView Provided by Android Studio
        arrayAdapter =
            ArrayAdapter<Any?>(this, android.R.layout.simple_expandable_list_item_1, notes)
        listView.adapter = arrayAdapter
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l -> // Going from MainActivity to NotesEditorActivity
                val intent = Intent(getApplicationContext(), NoteEditor::class.java)
                intent.putExtra("noteId", i)
                startActivity(intent)
            }
        listView.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { adapterView, view, i, l ->
                val itemToDelete = i
                // To delete the data from the App
                AlertDialog.Builder(this@MainActivity)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Are you sure?")
                    .setMessage("Do you want to delete this note?")
                    .setPositiveButton("Yes") { dialogInterface, i ->
                        notes.removeAt(itemToDelete)
                        arrayAdapter!!.notifyDataSetChanged()
                        val sharedPreferences: SharedPreferences =
                            getApplicationContext().getSharedPreferences(
                                "com.example.notes",
                                MODE_PRIVATE
                            )
                        val set: HashSet<String> = HashSet<Any?>(notes)
                        sharedPreferences.edit().putStringSet("notes", set).apply()
                    }.setNegativeButton("No", null).show()
                true
            }
    }

    companion object {
        var notes = ArrayList<String?>()
        var arrayAdapter: ArrayAdapter<*>? = null
    }

    val sharedPref = activity?.getSharedPreferences(
        getString(R.string.preference_file_key), Context.MODE_PRIVATE)

}


