package com.example.proyectofinal

import android.R
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class NoteEditor : AppCompatActivity() {
    var noteId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_item)
        val editText = findViewById<EditText>(R.id.edit)

        // Fetch data that is passed from MainActivity
        val intent = intent

        // Accessing the data using key and value
        noteId = intent.getIntExtra("noteId", -1)
        if (noteId != -1) {
            editText.setText(MainActivity.notes.get(noteId))
        } else {
            MainActivity.notes.add("")
            noteId = MainActivity.notes.size - 1
            MainActivity.arrayAdapter?.notifyDataSetChanged()
        }
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // add your code here
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                MainActivity.notes.set(noteId, charSequence.toString())
                MainActivity.arrayAdapter?.notifyDataSetChanged()
                // Creating Object of SharedPreferences to store data in the phone
                val sharedPreferences =
                    applicationContext.getSharedPreferences("com.example.notes", MODE_PRIVATE)
                val set: HashSet<Any?> = HashSet<Any?>(MainActivity.notes)
                sharedPreferences.edit().putStringSet("notes", set).apply()
            }

            override fun afterTextChanged(editable: Editable) {
                // add your code here
            }
        })
    }
}

private fun SharedPreferences.Editor.putStringSet(s: String, set: HashSet<Any?>): SharedPreferences.Editor? {
    val MY_PREFS_NAME
    val editor: SharedPreferences.Editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit()

    editor.putString("nombre", "Elena")

    editor.putInt("idName", 12)

    editor.apply()
}

