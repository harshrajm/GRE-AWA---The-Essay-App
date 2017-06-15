package com.burntcarlabs.greawa;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        AwaAdapter awaAdapter = new AwaAdapter(this,getSupportFragmentManager());

        viewPager.setAdapter(awaAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
        //fgdgfdgdfg
    }


    /*public void progressClicked(View v){
        Intent intent = new Intent(this,ProgressActivity.class);
        startActivity(intent);
    }

 public void issueClicked(View v){
     Intent intent = new Intent(this,IssueActivity.class);
     startActivity(intent);

}


    public void argumentClicked(View v){
        Intent intent = new Intent(this,ArgumentActivity.class);
        startActivity(intent);

    }


    public void bookmarkClicked(View v){
        Intent intent = new Intent(this,BookMarkActivity.class);
        startActivity(intent);


    }*/
}
