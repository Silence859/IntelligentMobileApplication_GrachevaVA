package ru.mirea.Gracheva_V_A.lesson2;

import static androidx.fragment.app.FragmentManager.TAG;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

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
        Log.i(TAG, "Мой код выполняется!");
    }

    /*@Override
    public void onResume() {
        super.onResume();
// Производится инициализации камеры после получения фокуса экрана
        if (mCamera == null) {
            initializeCamera(); //Метод работы с камерой
        }
    }*/

    /*@Override
    public void onPause() {
        super.onPause();
// Высвобождение ресурсов камеры, потому что во время паузы
// не используется
        if (mCamera != null){
            mCamera.release()
            mCamera = null;
        }
    }*/

    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNT_STUDENTS, 2);
    }*/
}