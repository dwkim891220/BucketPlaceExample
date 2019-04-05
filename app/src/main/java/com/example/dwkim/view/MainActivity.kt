package com.example.dwkim.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.dwkim.R
import com.example.dwkim.util.MainActivityListener
import com.example.dwkim.util.show
import com.example.dwkim.view.fragment.*
import com.example.dwkim.viewmodel.UserViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MainActivityListener {
    private val viewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTabs()
        setLayouts()
    }

    private fun initTabs(){
        vp_aMain.run {
            adapter = ContentsPagerAdapter(supportFragmentManager)
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tl_aMain))
        }

        tl_aMain.run{
            addTab(newTab(), true)
            addTab(newTab(), false)

            setupWithViewPager(vp_aMain)

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabReselected(p0: TabLayout.Tab?) {}
                override fun onTabUnselected(p0: TabLayout.Tab?) {}
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.run {
                        vp_aMain.currentItem = position
                    }
                }
            })
        }
    }

    private fun setLayouts(){
        btn_aMain_join.setOnClickListener {
            showJoinFragment()
        }
        btn_aMain_signIn.setOnClickListener {
            showSignInFragment()
        }
        btn_aMain_logout.setOnClickListener {
            btn_aMain_join.show()
            btn_aMain_signIn.show()
            btn_aMain_logout.show(false)
        }
    }

    private fun showJoinFragment(){
        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fl_aMain_container,
                JoinFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun showSignInFragment(){
        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fl_aMain_container,
                SignInFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun successLogin() {
        btn_aMain_join.show(false)
        btn_aMain_signIn.show(false)
        btn_aMain_logout.show()
    }

    override fun showCardDetail(id: Int?) {
        if(id == null) return

        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fl_aMain_container,
                CardDetailFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }

    override fun showUserDetail(id: Int?) {
        if(id == null) return

        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fl_aMain_container,
                UserDetailFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }

    inner class ContentsPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){
        override fun getPageTitle(position: Int): CharSequence? =
            when(position){
                0 -> "홈"
                1 -> "사진피드"
                else -> throw RuntimeException()
            }

        override fun getItem(position: Int): Fragment =
            when(position){
                0 -> HomeFragment.newInstance()
                1 -> CardListFragment.newInstance()
                else -> throw RuntimeException()
            }

        override fun getCount(): Int = 2
    }
}
