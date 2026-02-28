package ru.mirea.gracheva.activitylifecycle;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String onStart_TAG = MainActivity.class.getSimpleName();
    private static final String onRestoreInstanceState_TAG = MainActivity.class.getSimpleName();
    private static final String onPostCreate_TAG = MainActivity.class.getSimpleName();
    private static final String onResume_TAG = MainActivity.class.getSimpleName();
    private static final String onPostResume_TAG = MainActivity.class.getSimpleName();
    private static final String onAttachedToWindow_TAG = MainActivity.class.getSimpleName();
    private static final String onPause_TAG = MainActivity.class.getSimpleName();
    private static final String onSaveInstanceState_TAG = MainActivity.class.getSimpleName();
    private static final String onStop_TAG = MainActivity.class.getSimpleName();
    private static final String onDestroy_TAG = MainActivity.class.getSimpleName();
    private static final String onDetachedFromWindow_TAG = MainActivity.class.getSimpleName();
    private static final String onRestart_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(onStart_TAG, "onStart()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(onRestoreInstanceState_TAG, "onRestoreInstanceState()");
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        Log.i(onPostCreate_TAG, "onPostCreate()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(onResume_TAG, "onResume()");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(onPostResume_TAG, "onPostResume()");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(onAttachedToWindow_TAG, "onAttachedToWindow()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(onPause_TAG, "onPause()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i(onSaveInstanceState_TAG, "onSaveInstanceState()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(onStop_TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(onDestroy_TAG, "onDestroy()");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(onDetachedFromWindow_TAG, "onDetachedFromWindow()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(onRestart_TAG, "onRestart()");
    }
}

