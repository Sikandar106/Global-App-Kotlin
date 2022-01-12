package com.example.globalnews.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.globalnews.R
import com.example.globalnews.adapters.NewsAdapters
import com.example.globalnews.fragments.FavouriteFragment
import com.example.globalnews.fragments.HomeFragment
import com.example.globalnews.fragments.SearchFragment
import com.example.globalnews.fragments.SettingFragment
import com.example.globalnews.interfaces.NewsInterface
import com.example.globalnews.interfaces.NewsServices
import com.example.globalnews.models.Articles
import com.example.globalnews.models.News
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapters
    val articlesList = mutableListOf<Articles>()
    lateinit var homeFragment: HomeFragment
    lateinit var favouriteFragment: FavouriteFragment
    lateinit var searchFragment : SearchFragment
    lateinit var settingFragment: SettingFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = NewsAdapters(this@MainActivity, articlesList)
        recyclerViewNewsList.adapter = adapter
        recyclerViewNewsList.layoutManager = LinearLayoutManager(this@MainActivity)
        getNews()
    }
    private fun bottomNav(){
        val getBottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val getFram = findViewById<FrameLayout>(R.id.frameLayout)

        getBottomNav.setOnItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.nav_home -> {
                        Toast.makeText(this@MainActivity, "Home Clicked", Toast.LENGTH_SHORT).show()
                        setFragments(homeFragment)
                        return true
                    }
                    R.id.nav_search -> {
                        Toast.makeText(this@MainActivity, "Home Clicked", Toast.LENGTH_SHORT).show()
                        setFragments(searchFragment)
                        return true
                    }
                    R.id.nav_favourite -> {
                        Toast.makeText(this@MainActivity, "Home Clicked", Toast.LENGTH_SHORT).show()
                        setFragments(favouriteFragment)
                        return true
                    }
                    R.id.nav_setting -> {
                        Toast.makeText(this@MainActivity, "Home Clicked", Toast.LENGTH_SHORT).show()
                        setFragments(settingFragment)
                        return true
                    }
                }
                return true
            }

        })
    }

    private fun setFragments(fragment: Fragment){
        val fragTrans = supportFragmentManager.beginTransaction()
        fragTrans.replace(R.id.frameLayout,fragment)
        fragTrans.commit()
    }

    private fun getNews() {
        val news = NewsServices.newsInstance.getHeadlines("in",1)
        news.enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null){
                    Log.d("NewsApp",news.toString())
                    articlesList.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("NewsApp", "Error in fetching news")
            }
        })
    }
}