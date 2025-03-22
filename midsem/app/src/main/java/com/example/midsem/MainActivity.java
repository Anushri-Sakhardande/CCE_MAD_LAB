package com.example.midsem;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new TabFragment("Tab 1 Content");
                    case 1:
                        return new TabFragment("Tab 2 Content");
                    default:
                        return new TabFragment("Tab 3 Content");
                }
            }

            @Override
            public int getCount() {
                return 3; // Number of tabs
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Tab " + (position + 1);
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    // Overriding onCreateOptionsMenu Method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    // Overriding onOptionsItemSelected Method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId())
        if(id == R.id.send) {

            Toast.makeText(getApplicationContext(),
                    "Shows share icon", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id==R.id.gallery) {
            Toast.makeText(getApplicationContext(),
                    "Shows image icon", Toast.LENGTH_SHORT).show();
            return true;
        }
            case R.id.call:
                Toast.makeText(getApplicationContext(),
                        "Shows call icon", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.calculator:
                Toast.makeText(getApplicationContext(),
                        "Calculator menu", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.exit:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}