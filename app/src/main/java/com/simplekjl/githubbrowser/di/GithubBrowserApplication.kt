package com.simplekjl.githubbrowser.di

import android.app.Application
import androidx.room.Room
import com.simplekjl.data.client.GithubService
import com.simplekjl.data.repository.DataSourceRepository
import com.simplekjl.data.repository.LocalSource
import com.simplekjl.data.repository.NetworkSource
import com.simplekjl.githubbrowser.BuildConfig
import com.simplekjl.githubbrowser.framework.InMemoryRepositories
import com.simplekjl.githubbrowser.framework.RepositoriesSource
import com.simplekjl.githubbrowser.framework.StringProvider
import com.simplekjl.githubbrowser.framework.database.AppDatabase
import com.simplekjl.githubbrowser.framework.database.DATABASE_NAME
import com.simplekjl.githubbrowser.ui.MainViewModel
import com.simplekjl.githubbrowser.ui.mapper.RepositoriesPayloadMapper
import com.simplekjl.githubbrowser.ui.mapper.RepositoriesViewEntityMapper
import com.simplekjl.usecases.GetRepositoriesByKeyWord
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class GithubBrowserApplication : Application() {

    private val networkModule = createNetworkModule()
    private val mainModule = createMainModule() // UI level
    private val dataModule = createDataModule()


    private fun createDataModule() = module {
        factory<NetworkSource> { RepositoriesSource() }
        factory<LocalSource> { InMemoryRepositories(get(), get()) }
        factory { DataSourceRepository(get(), get()) }
    }

    private fun createMainModule() = module {
        single {
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, DATABASE_NAME
            ).build()
        }
        factory { StringProvider(applicationContext) }
        factory { RepositoriesViewEntityMapper() }
        factory { RepositoriesPayloadMapper(applicationContext) }
        factory { GetRepositoriesByKeyWord(get()) }
        viewModel { MainViewModel(get(), get(), get()) }
    }

    private fun createNetworkModule() = module {
        single<Retrofit> {
            val builder = OkHttpClient().newBuilder()
            builder.readTimeout(0, TimeUnit.SECONDS)
            builder.connectTimeout(5, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
                builder.addInterceptor(interceptor)
            }

            val client = builder.build()

            Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_GITHUB_URL_API)
                .build()
        }
        single<GithubService> { get<Retrofit>().create(GithubService::class.java) }
    }

    override fun onCreate() {
        super.onCreate()
        //start koin
        startKoin {
            androidLogger()
            androidContext(this@GithubBrowserApplication)
            androidFileProperties()
            modules(
                listOf(
                    dataModule,
                    networkModule, mainModule
                )
            )
        }
    }
}
