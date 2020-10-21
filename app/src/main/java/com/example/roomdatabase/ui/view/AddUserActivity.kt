package com.example.roomdatabase.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.roomdatabase.databinding.ActivityAddUserBinding

class AddUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setup view binding
        val binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // create add button handler
        binding.btnAdd.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra(FIRST_NAME, binding.etFirstName.text.toString())
            returnIntent.putExtra(LAST_NAME, binding.etLastName.text.toString())
            returnIntent.putExtra(URL, binding.etImageUrl.text.toString())
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