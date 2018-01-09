package com.androidcodehub.viewpagerkotlin.ViewPager

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView

import com.androidcodehub.viewpagerkotlin.Fragment.CallsFragment
import com.androidcodehub.viewpagerkotlin.Fragment.ChatFragment
import com.androidcodehub.viewpagerkotlin.Fragment.ContactsFragment
import com.androidcodehub.viewpagerkotlin.R
import com.androidcodehub.viewpagerkotlin.ViewPagerAdapter


class CustomTabActivity : AppCompatActivity() {

    //This is our tablayout
    private var tabLayout: TabLayout? = null

    //This is our viewPager
    private var viewPager: ViewPager? = null

    //Fragments

    internal lateinit var chatFragment: ChatFragment
    internal lateinit var callsFragment: CallsFragment
    internal lateinit var contactsFragment: ContactsFragment

    internal var tabTitle = arrayOf("CALLS", "CHAT", "CONTACTS")
    // int[] unreadCount={0,5,0};

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_without_icon)

        //Initializing viewPager
        viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        viewPager!!.offscreenPageLimit = 3
        setupViewPager(viewPager!!)

        //Initializing the tablayout
        tabLayout = findViewById<View>(R.id.tablayout) as TabLayout
        tabLayout!!.setupWithViewPager(viewPager)

        try {
            setupTabIcons()
        } catch (e: Exception) {
            e.printStackTrace()
        }


        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                viewPager!!.setCurrentItem(position, false)

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })


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
            R.id.action_without_icon -> {
                val withouticon = Intent(this, TabNIconActivity::class.java)
                startActivity(withouticon)
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

    private fun prepareTabView(pos: Int): View {
        val view = layoutInflater.inflate(R.layout.custom_tab, null)
        val tv_title = view.findViewById<View>(R.id.tv_title) as TextView
        val tv_count = view.findViewById<View>(R.id.tv_count) as TextView
        tv_title.text = tabTitle[pos]


        return view
    }

    private fun setupTabIcons() {

        for (i in tabTitle.indices) {
            /*TabLayout.Tab tabitem = tabLayout.newTab();
            tabitem.setCustomView(prepareTabView(i));
            tabLayout.addTab(tabitem);*/

            tabLayout!!.getTabAt(i)!!.customView = prepareTabView(i)
        }


    }
}
