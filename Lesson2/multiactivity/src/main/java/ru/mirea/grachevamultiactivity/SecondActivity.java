package ru.mirea.grachevamultiactivity;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ru.mirea.grachevamultiactivity.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(onCreate_TAG, "onCreate_SecondActivity()");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.textView3);
        String text = (String) getIntent().getSerializableExtra("key");
        textView.setText(text);
    }

    // Жизненный цикл
    private static final String onCreate_TAG = MainActivity.class.getSimpleName();
    private static final String onStart_TAG = MainActivity.class.getSimpleName();
    private static final String onResume_TAG = MainActivity.class.getSimpleName();
    private static final String onPause_TAG = MainActivity.class.getSimpleName();
    private static final String onStop_TAG = MainActivity.class.getSimpleName();
    private static final String onDestroy_TAG = MainActivity.class.getSimpleName();
    private static final String onRestart_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(onStart_TAG, "onStart_SecondActivity()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(onResume_TAG, "onResume_SecondActivity()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(onPause_TAG, "onPause_SecondActivity()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(onStop_TAG, "onStop_SecondActivity()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(onDestroy_TAG, "onDestroy_SecondActivity()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(onRestart_TAG, "onRestart_SecondActivity()");
    }
}