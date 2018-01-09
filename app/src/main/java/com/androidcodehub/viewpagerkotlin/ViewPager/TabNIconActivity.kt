package com.androidcodehub.viewpagerkotlin.ViewPager

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.androidcodehub.viewpagerkotlin.Fragment.CallsFragment
import com.androidcodehub.viewpagerkotlin.Fragment.ChatFragment
import com.androidcodehub.viewpagerkotlin.Fragment.ContactsFragment
import com.androidcodehub.viewpagerkotlin.R
import com.androidcodehub.viewpagerkotlin.ViewPagerAdapter


class TabNIconActivity : AppCompatActivity() {

    //This is our tablayout
    private var tabLayout: TabLayout? = null

    //This is our viewPager
    private var viewPager: ViewPager? = null

    //Fragments

    internal lateinit var chatFragment: ChatFragment
    internal lateinit var callsFragment: CallsFragment
    internal lateinit var contactsFragment: ContactsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_without_icon)
        //Initializing viewPager
        viewPager = findViewById<View>(R.id.viewpager) as ViewPager?
        viewPager!!.offscreenPageLimit = 3

        //Initializing the tablayout
        tabLayout = findViewById<View>(R.id.tablayout) as TabLayout?
        tabLayout!!.setupWithViewPager(viewPager)

        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                viewPager!!.setCurrentItem(position, false)

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        setupViewPager(viewPager!!)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        // Associate searchable configuration with the SearchView
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        when (item.itemId) {

            R.id.action_with_icon -> {
                val withicon = Intent(this, TabWithIconActivity::class.java)
                startActivity(withicon)
                finish()
                return true
            }
            R.id.action_customtab -> {
                val custom_tab = Intent(this, CustomTabActivity::class.java)
                startActivity(custom_tab)
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        callsFragment = CallsFragment()
        chatFragment = ChatFragment()
        contactsFragment = ContactsFragment()
        adapter.addFragment(callsFragment, "CALLS")
        adapter.addFragment(chatFragment, "CHAT")
        adapter.addFragment(contactsFragment, "CONTACTS")
        viewPager.adapter = adapter
    }

}
