package com.example.roomdatabase.ui.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase.data.User
import com.example.roomdatabase.databinding.ActivityMainBinding
import com.example.roomdatabase.ui.adapter.UserListAdapter
import com.example.roomdatabase.ui.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setup view binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup recyclerview
        val adapter = UserListAdapter()
        binding.rvUsers.adapter = adapter
        binding.rvUsers.layoutManager = LinearLayoutManager(this)

        // setup the users view model
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.allUsersCached.observe(this, { words ->
            // Update the cached copy of the words in the adapter.
            words?.let {
                adapter.setUsers(it)
            }
        })

        // add pull to refresh handler (with fake 2 second pause)
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = true
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipeRefresh.isRefreshing = false
            }, 2000)
        }

        // add fab click handler
        binding.fabAdd.setOnClickListener {
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