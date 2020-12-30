package com.simplekjl.githubbrowser.ui

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import com.simplekjl.githubbrowser.R
import com.simplekjl.githubbrowser.databinding.ActivityMainBinding
import com.simplekjl.githubbrowser.ui.adapter.OnItemClick
import com.simplekjl.githubbrowser.ui.adapter.RepositoryAdapter
import com.simplekjl.githubbrowser.ui.model.RepositoryViewEntity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnItemClick {

    private val mainViewModel: MainViewModel by viewModel()
    private val repositoryAdapter: RepositoryAdapter by lazy { RepositoryAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        binding.adapter = repositoryAdapter
        mainViewModel.state.observe(this, {
            binding.screenState = it
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
                searchWithQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // getTasks(newText)
                return true
            }

            private fun searchWithQuery(searchText: String) {
                mainViewModel.searchRepositories("%${searchText}%")
            }
        }

    override fun onRepositoryClicked(repositoryViewEntity: RepositoryViewEntity) {
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        val customTabsIntent: CustomTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(repositoryViewEntity.repositoryUrl))
    }
}
