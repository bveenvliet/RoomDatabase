package com.example.roomdatabase.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdatabase.R

class AddUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        val etFirstName = findViewById<EditText>(R.id.etFirstName)
        val etLastName = findViewById<EditText>(R.id.etLastName)
        val etImageUrl = findViewById<EditText>(R.id.etImageUrl)

        // create add button handler
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra(FIRST_NAME, etFirstName.text.toString())
            returnIntent.putExtra(LAST_NAME, etLastName.text.toString())
            returnIntent.putExtra(URL, etImageUrl.text.toString())
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }

    companion object {
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
        const val URL = "url"
    }

}