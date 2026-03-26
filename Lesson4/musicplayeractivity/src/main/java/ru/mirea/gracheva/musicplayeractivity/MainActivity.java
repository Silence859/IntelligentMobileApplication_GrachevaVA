package ru.mirea.gracheva.musicplayeractivity;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import ru.mirea.gracheva.musicplayeractivity.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnPlay.setOnClickListener(v ->
                Log.d("PlayerActivity", "Play clicked")
        );
        binding.btnPause.setOnClickListener(v ->
                Log.d("PlayerActivity", "Pause clicked")
        );
        binding.btnStop.setOnClickListener(v ->
                Log.d("PlayerActivity", "Stop clicked")
        );
    }
}