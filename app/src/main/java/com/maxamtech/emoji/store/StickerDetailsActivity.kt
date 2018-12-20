package com.maxamtech.emoji.store

import android.content.Context
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.maxamtech.emoji.R
import kotlinx.android.synthetic.main.fragment_search.*
import android.util.TypedValue
import com.maxamtech.emoji.CommonStickerAdapter2
import com.maxamtech.emoji.fragments.CommonStickerAdapter


import kotlinx.android.synthetic.main.activity_store.*

class StickersDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sticker_details)
        setSupportActionBar(toolbar)
        init()

    }

    private fun init() {

        var tagRecyclerView : RecyclerView = findViewById(R.id.stickerRecycler)
        var mLayoutManager : GridLayoutManager =
                GridLayoutManager(this, 3)
        tagRecyclerView.layoutManager = mLayoutManager
        tagRecyclerView.itemAnimator = DefaultItemAnimator()
//        tagRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(15), true))
        tagRecyclerView.adapter = CommonStickerAdapter2()

        //set title

    }

    inner class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
    }


}

