package com.uzlahpri.githubapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.uzlahpri.githubapp.R
import com.uzlahpri.githubapp.adapter.ViewPagerAdapter
import com.uzlahpri.githubapp.databinding.ActivityDetailBinding
import com.uzlahpri.githubapp.model.Users
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
        val username = intent.getStringArrayExtra(EXTRA_USERNAME) as String

        setViewPager()
        setTabLayout()
        setViewModel(username = username)
        showProgressBar(false)
    }

    private fun setTabLayout() {
        val tabs: TabLayout = detailBinding.tlDetail

        val customTabFollowers =
            LayoutInflater.from(this).inflate(R.layout.tab_followers, tabs, false)
        tvFollowing = customTabFollowers.findViewById(R.id.tvFollowersDetail)

        val customTabFollowing =
            LayoutInflater.from(this).inflate(R.layout.tab_following, tabs, false)
        tvFollowing = customTabFollowing.findViewById(R.id.tvFollowingDetail)

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            when (position) {
                0 -> tab.customView = customTabFollowers
                1 -> tab.customView = customTabFollowing
            }
        }.attach()
    }

    private fun showProgressBar(state: Boolean) {
        if (state) {
            detailBinding.pbDetail.visibility = View.VISIBLE
        } else {
            detailBinding.pbDetail.visibility = View.GONE
        }
    }

    private fun setViewModel(username: String) {
        detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)
        detailViewModel.setDetailUser(username, this)
        detailViewModel.getDetailUser().observe(this, { user ->
            if (user != null) {
                setData(user)
            }
        })
    }

    private fun setData(user: Users) {
        detailBinding.tvNameDetail.text = user.name
        detailBinding.tvLocationDetail.text = user.location
        tvFollowers.text = user.followers.toString()
        tvFollowing.text = user.following.toString()

        Glide.with(this).load(user.avatar).into(detailBinding.ivDetail)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = user.username
        }
    }

    private fun setViewPager() {
        sectionPagerAdapter = ViewPagerAdapter(this)
        viewPager = detailBinding.vpDetail
        viewPager.adapter = sectionPagerAdapter

    }
}