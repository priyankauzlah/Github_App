package com.uzlahpri.githubapp.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.uzlahpri.githubapp.fragment.FollowersFragment
import com.uzlahpri.githubapp.fragment.FollowingFragment

class ViewPagerAdapter (activity : AppCompatActivity): FragmentStateAdapter(activity){
    //ini untuk berapa banyak fragment yg mau ditampilin
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? = null
        when (position){
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }
}