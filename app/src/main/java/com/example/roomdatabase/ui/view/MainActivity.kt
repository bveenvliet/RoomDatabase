package com.example.roomdatabase.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.data.User
import com.example.roomdatabase.ui.adapter.UserListAdapter
import com.example.roomdatabase.ui.viewmodel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup recyclerview
        val recyclerView = findViewById<RecyclerView>(R.id.rvUsers)
        val adapter = UserListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // setup the users view model
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.allUsersCached.observe(this, { words ->
            // Update the cached copy of the words in the adapter.
            words?.let {
                adapter.setUsers(it)
            }
        })

        // add fab click handler
        val fabAdd: FloatingActionButton = findViewById(R.id.fabAdd)
        fabAdd.setOnClickListener {
            val addUserActivityIntent = Intent(it.context, AddUserActivity::class.java)
            startForResult.launch(addUserActivityIntent)
        }
    }

    // handle the return result from the add user activity
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val resultDataExtras = result.data?.extras
                val newUser = User(
                    firstName = resultDataExtras?.getString(AddUserActivity.FIRST_NAME),
                    lastName = resultDataExtras?.getString(AddUserActivity.LAST_NAME),
                    url = resultDataExtras?.getString(AddUserActivity.URL)
                )
                userViewModel.insert(newUser)
            }
        }

}