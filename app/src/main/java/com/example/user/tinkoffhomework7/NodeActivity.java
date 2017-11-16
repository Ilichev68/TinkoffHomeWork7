package com.example.user.tinkoffhomework7;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class NodeActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private long id;
    private ParentsFragment parentsFragment = new ParentsFragment();

    public static void start(Context context, long id) {
        Intent starter = new Intent(context, NodeActivity.class);
        starter.putExtra("id", id);
        context.startActivity(starter);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node);

        id = (long) getIntent().getExtras().get("id");

        parentsFragment.setId(id);
        fragmentManager.beginTransaction().replace(R.id.container, parentsFragment).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_child: {
                        changeFragment(1);
                        break;
                    }
                    case R.id.action_parent: {
                        changeFragment(0);
                        break;
                    }
                }
                return false;
            }
        });
    }

    private void changeFragment(int position) {

        Fragment newFragment;

        if (position == 0) {
            parentsFragment.setId(id);
            newFragment = parentsFragment;
        } else {
            ChildrenFragment childrenFragment = new ChildrenFragment();
            childrenFragment.setId(id);
            newFragment = childrenFragment;
        }

        fragmentManager.beginTransaction().replace(
                R.id.container, newFragment)
                .commit();
    }

    public long getId(){
        return id;
    }

}
