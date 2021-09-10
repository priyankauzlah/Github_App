package com.uzlahpri.githubapp.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.uzlahpri.githubapp.adapter.ViewPagerAdapter
import com.uzlahpri.githubapp.databinding.ActivityDetailBinding
import com.uzlahpri.githubapp.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var sectionPagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tvFollowers: TextView
    private lateinit var tvFollowing: TextView

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        setViewPager()
//        setViewModel(username)
    }

    private fun setViewModel(username: String) {
        detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)
        detailViewModel.setDetailUser(username, this)
        detailViewModel.getDetailUser().observe(this, { user ->
            if (user != null) {
                
            }
        })
    }

    private fun setViewPager() {
        sectionPagerAdapter = ViewPagerAdapter(this)
        viewPager = detailBinding.vpDetail
        viewPager.adapter = sectionPagerAdapter

    }
}