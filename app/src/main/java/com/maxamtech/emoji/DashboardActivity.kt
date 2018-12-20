package com.maxamtech.emoji

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.maxamtech.emoji.fragments.HelloFragment
import com.maxamtech.emoji.fragments.PopularFragment
import com.maxamtech.emoji.fragments.SearchFragment
import com.maxamtech.emoji.fragments.ViewPagerAdapter
import com.maxamtech.emoji.store.StoreActivity

import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.content_dashboard.*

class DashboardActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"))
        setSupportActionBar(toolbar)
        init()

    }



    private fun init() {

        var adapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(SearchFragment(),"Search")
        adapter.addFragment(PopularFragment(),"Popular")
        adapter.addFragment(HelloFragment(),"Hello")
        homePager.adapter = adapter

        //set tab icons
        tabLayout.setupWithViewPager(homePager)
        tabLayout.getTabAt(0)?.icon = getDrawable(R.drawable.ic_search)
        tabLayout.getTabAt(1)?.icon = getDrawable(R.drawable.ic_star)
        tabLayout.getTabAt(2)?.icon = getDrawable(R.drawable.ic_waving_hand)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_actions, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {

        R.id.action_rate -> {
            true
        }

        R.id.action_settings -> {
            true
        }

        R.id.action_store -> {
            var intent: Intent = Intent(this@DashboardActivity, StoreActivity::class.java)
            this@DashboardActivity.startActivity(intent)
            true
        }

        else ->{
            super.onOptionsItemSelected(item)
        }

    }



}
