package com.maxamtech.emoji.store

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.maxamtech.emoji.R
import kotlinx.android.synthetic.main.fragment_search.*
import android.util.TypedValue
import android.widget.Toast
import androidapp.test.com.onscreenalertnotify.RecyclerTouchListener
import com.maxamtech.emoji.StoreListAdapter
import com.maxamtech.emoji.fragments.CommonStickerAdapter


import kotlinx.android.synthetic.main.activity_store.*

class StoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        setSupportActionBar(toolbar)
        init()

    }

    private fun init() {

        var tagRecyclerView : RecyclerView = findViewById(R.id.storeRecycler)
        tagRecyclerView.itemAnimator = DefaultItemAnimator()
        tagRecyclerView.adapter = StoreListAdapter()
        tagRecyclerView.addOnItemTouchListener(RecyclerTouchListener(this, tagRecyclerView,
                object : RecyclerTouchListener.ClickListener {
                    override fun onClick(view: View, position: Int) {
                        var intent: Intent = Intent(this@StoreActivity, StickersDetailsActivity::class.java)
                        this@StoreActivity.startActivity(intent)
                    }

                    override fun onLongClick(view: View?, position: Int) {

                    }
                }))
        //set title

    }

}
