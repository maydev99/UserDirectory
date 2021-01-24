package com.bombadu.userdirectory.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bombadu.userdirectory.R
import com.bombadu.userdirectory.data.UserData
import com.bombadu.userdirectory.databinding.ActivityAddUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddUserBinding
    lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_user_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_user -> {
                saveUserData()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveUserData() {
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        val location = binding.locationEditText.text.toString()
        val age = binding.ageEditText.text.toString()
        val newUserData = UserData(
            firstName, lastName, location, age
        )
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.insertUserDataIntoDB(newUserData)
        finish()

    }
}