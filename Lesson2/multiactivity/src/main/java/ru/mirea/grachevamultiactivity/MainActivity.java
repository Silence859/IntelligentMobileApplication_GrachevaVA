package ru.mirea.grachevamultiactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(onCreate_TAG, "onCreate_MainActivity()");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextTextMultiLine);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onClickNewActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("key", "MIREA - Грачева Василиса Александровна");
        startActivity(intent);
    }

    public void onClickSetText(View view)
    {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        String text = editText.getText().toString();
        intent.putExtra("key", text);
        startActivity(intent);
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
        Log.i(onStart_TAG, "onStart_MainActivity()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(onResume_TAG, "onResume_MainActivity()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(onPause_TAG, "onPause_MainActivity()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(onStop_TAG, "onStop_MainActivity()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(onDestroy_TAG, "onDestroy_MainActivity()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(onRestart_TAG, "onRestart_MainActivity()");
    }
}