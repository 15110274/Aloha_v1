package vn.edu.hcmute.aloha;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

import vn.edu.hcmute.aloha.adapter.PagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //upload to appcenter find bug
        AppCenter.start(getApplication(), "f7cf01e2-666e-473d-a679-397d1c3d8595", Analytics.class, Crashes.class);

        addControls();
        addEvents();
    }


    private void addEvents() {
    }


    private void addControls() {
        pager = this.<ViewPager>findViewById(R.id.view_pager);
        tabLayout = this.<TabLayout>findViewById(R.id.tab_layout);

        FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(manager);

        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTabsFromPagerAdapter(adapter);//deprecated
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));

    }
}
