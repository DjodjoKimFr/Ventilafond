package com.djodjokim.ventilafond;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.djodjokim.ventilafond.databinding.ActivityMainBinding;
import com.djodjokim.ventilafond.ui.CalculViewModel;
import com.djodjokim.ventilafond.ui.ResultFragment;
import com.djodjokim.ventilafond.ui.WelcomeFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CalculViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //View view = binding.getRoot();
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (savedInstanceState == null) {
            WelcomeFragment fragmentA = new WelcomeFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view, fragmentA)
                    .commit();


        }

    }

}