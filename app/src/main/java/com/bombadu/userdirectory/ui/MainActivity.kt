package com.bombadu.userdirectory.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bombadu.userdirectory.R
import com.bombadu.userdirectory.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainAdapter.ItemClickListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: UserViewModel
    lateinit var mainAdapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, AddUserActivity::class.java))
        }

        mainAdapter = MainAdapter(this)
        getTheData()


    }

    private fun getTheData() {
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.userDatas.observe(this,
            { list ->
                list.let {
                    mainAdapter.submitList(it)
                }
            })

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val manager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = mainAdapter

        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mainAdapter.getItemAt(viewHolder.adapterPosition)
                    .let { viewModel.deleteUserData(it) }
                Toast.makeText(this@MainActivity, "Deleted", Toast.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(binding.recyclerView)

    }
    
    override fun onItemClick(position: Int) {
        val myUserData = mainAdapter.getItemAt(position)
        val bundle = Bundle()
        val intent = Intent(this, AddUserActivity::class.java)
        with(bundle) {
            putInt("id", myUserData.id!!)
            putString("first_name", myUserData.firstName)
            putString("last_name", myUserData.lastName)
            putString("location", myUserData.location)
            putString("age", myUserData.age)
        }
        intent.putExtras(bundle)
        startActivity(intent)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.delete_all -> {
                viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
                viewModel.deleteAllUserData()
                Toast.makeText(this, "All Data Deleted", Toast.LENGTH_SHORT).show()
            }
        }
        
        return super.onOptionsItemSelected(item)
    }


}