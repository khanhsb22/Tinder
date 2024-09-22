package com.example.tinder_app;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tinder_app.databinding.ActivityMainBinding;
import com.example.tinder_app.ui.CategoryFragment;
import com.example.tinder_app.ui.ChatFragment;
import com.example.tinder_app.ui.HomeFragment;
import com.example.tinder_app.ui.StarFragment;
import com.example.tinder_app.ui.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);

        binding.bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Init default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, new HomeFragment(MainActivity.this.getApplicationContext())).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.fire) {
                selectedFragment = new HomeFragment(MainActivity.this.getApplicationContext());
            } else if (itemId == R.id.category) {
                selectedFragment = new CategoryFragment();
            } else if (itemId == R.id.star) {
                selectedFragment = new StarFragment();
            } else if (itemId == R.id.chat) {
                selectedFragment = new ChatFragment();
            } else if (itemId == R.id.user) {
                selectedFragment = new UserFragment();
            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, selectedFragment).commit();
            }
            return true;
        }
    };
}