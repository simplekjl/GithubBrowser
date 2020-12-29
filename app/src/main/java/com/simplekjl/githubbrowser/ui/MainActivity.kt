package com.simplekjl.githubbrowser.ui

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        // Associate searchable configuration with the SearchView
        val searchView = menu.findItem(R.id.action_search)
            .actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(onQueryTextListener)
        return true
    }


    private val onQueryTextListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // this can be improved if we want to listen in another time
                getTasks(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // getTasks(newText)
                return true
            }

            private fun getTasks(searchText: String) {
                mainViewModel.searchRepositories("%${searchText}%")
            }
        }
}
