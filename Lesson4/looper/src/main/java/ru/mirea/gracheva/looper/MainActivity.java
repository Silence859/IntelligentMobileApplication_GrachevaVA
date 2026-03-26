package ru.mirea.gracheva.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.gracheva.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MyLooper myLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                String result = msg.getData().getString("result");
                binding.tvStatus.setText(result);
                Log.d(MainActivity.class.getSimpleName(), "Результат: " + result);
            }
        };

        myLooper = new MyLooper(mainThreadHandler);
        myLooper.start();

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ageStr = binding.editAge.getText().toString();
                String job = binding.editJob.getText().toString();

                if (ageStr.isEmpty() || job.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Заполните оба поля", Toast.LENGTH_SHORT).show();
                    return;
                }

                int age = Integer.parseInt(ageStr);
                if (age <= 0) {
                    Toast.makeText(MainActivity.this, "Возраст должен быть положительным", Toast.LENGTH_SHORT).show();
                    return;
                }

                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt("age", age);
                bundle.putString("job", job);
                msg.setData(bundle);
                myLooper.mHandler.sendMessage(msg);

                binding.tvStatus.setText("Отправлено. Ожидание " + age + " сек...");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myLooper != null && myLooper.mHandler != null) {
            myLooper.mHandler.getLooper().quit();
        }
    }
}