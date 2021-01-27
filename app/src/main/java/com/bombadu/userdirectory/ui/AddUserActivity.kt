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
    private lateinit var viewModel: UserViewModel
    private var id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkEditing()


    }

    private fun checkEditing() {
        val extras = intent.extras
        if (extras != null) {
            id = extras.getInt("id")
            with(binding) {
                firstNameEditText.setText(extras.getString("first_name"))
                lastNameEditText.setText(extras.getString("last_name"))
                locationEditText.setText(extras.getString("location"))
                ageEditText.setText(extras.getString("age"))
            }
        }
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
            R.id.auto_gen -> {
                autoGenEntries()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun autoGenEntries() {
        for (i in 0 until 4) {
            val newUserData1 = (UserData("Michael", "May", "Delray Beach", "50"))
            val newUserData2 = (UserData("Cate", "May", "Delray Beach", "66"))
            val newUserData3 = (UserData("Steve", "May", "San Anselmo", "52"))
            viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            viewModel.insertUserDataIntoDB(newUserData1)
            viewModel.insertUserDataIntoDB(newUserData2)
            viewModel.insertUserDataIntoDB(newUserData3)
        }
        finish()
    }

    private fun saveUserData() {
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        val location = binding.locationEditText.text.toString()
        val age = binding.ageEditText.text.toString()

        val newUserData = UserData(
            firstName, lastName, location, age
        )

        if (id != -1) {
            newUserData.id = id
        }
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.insertUserDataIntoDB(newUserData)
        finish()

    }
}