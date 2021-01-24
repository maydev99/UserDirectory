package com.bombadu.userdirectory.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bombadu.userdirectory.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

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

        mainAdapter = MainAdapter()
        getTheData()



    }

    private fun getTheData() {
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.userDatas.observe(this,
            { list->
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
    }

    override fun onClick(p0: View?) {

    }


}