package com.apps.materialdesign;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.apps.materialdesign.fragment.CardFragment;
import com.apps.materialdesign.fragment.ListFragment;
import com.apps.materialdesign.fragment.TilesFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    TabLayout tabLayout;
    Toolbar toolbar;
    ViewPager viewPager;
    String  TAG = MainActivity.class.getSimpleName();
    DrawerLayout mDrawerLayout;
NavigationView navigationView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Adding Toolbar to Main screen
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setting ViewPager for each Tabs
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        // Set Tabs inside Toolbar
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        // Adding menu icon to Toolbar

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            VectorDrawableCompat vectorDrawableCompat = VectorDrawableCompat
                    .create(getResources(),R.drawable.ic_menu,getTheme());
            vectorDrawableCompat.setTint(ResourcesCompat.getColor(getResources(),R.color.white,getTheme()));
            actionBar.setHomeAsUpIndicator(vectorDrawableCompat);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Set item in checked state
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Snackbar.make(v,"Hey SnackBar !!! ",Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
       Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ListFragment(),"List");
        adapter.addFragment(new TilesFragment(),"Tiles");
        adapter.addFragment(new CardFragment(),"Card");
        viewPager.setAdapter(adapter);
    }


     class  Adapter extends FragmentPagerAdapter{

         private final List<Fragment> mFragmentList = new ArrayList<>();
         private final List<String> mFragmentTitleList = new ArrayList<>();

         public  Adapter(FragmentManager fragmentManager){
             super(fragmentManager);
         }

         @Override
         public Fragment getItem(int position) {
             return mFragmentList.get(position);
         }

         @Override
         public int getCount() {
             Log.i(TAG,"getCount "+mFragmentList.size());
             return mFragmentList.size();
         }

         public void addFragment(Fragment fragment,String title){
             mFragmentList.add(fragment);
             mFragmentTitleList.add(title);
         }

         @Override
         public CharSequence getPageTitle(int position) {
             return mFragmentTitleList.get(position);
         }
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }else if (id == android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
