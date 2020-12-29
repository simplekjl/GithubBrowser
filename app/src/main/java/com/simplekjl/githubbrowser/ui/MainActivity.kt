package com.simplekjl.githubbrowser.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.simplekjl.githubbrowser.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.state.observe(this, Observer {
            when (it) {
                is State.Data -> Toast.makeText(this, it.data.toString(), Toast.LENGTH_SHORT).show()
            }
        })
        mainViewModel.getRepositoriesTest()
    }
}
