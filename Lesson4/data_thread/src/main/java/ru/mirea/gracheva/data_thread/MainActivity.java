package ru.mirea.gracheva.data_thread;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.TimeUnit;

import ru.mirea.gracheva.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        startTime = System.currentTimeMillis();

        binding.tvInfo.setText("Изучение методов:\n" +
                "1) runOnUiThread(Runnable) – выполняет код в UI-потоке немедленно (или ставит в очередь, если вызван не из UI)\n" +
                "2) post(Runnable) – добавляет задачу в очередь сообщений View, выполнится после обработки всех текущих событий\n" +
                "3) postDelayed(Runnable, long) – то же, но с задержкой\n\n" +
                "Последовательность вызовов в коде:\n");

        final Runnable runn1 = new Runnable() {
            public void run() {
                long elapsed = System.currentTimeMillis() - startTime;
                String msg = String.format("→ %.1f сек: runOnUiThread(runn1) – текст изменён на \"runn1\"", elapsed / 1000.0);
                binding.tvInfo.append(msg + "\n");
                binding.tvInfo.append("   (Выполняется сразу в UI-потоке)\n");
            }
        };

        final Runnable runn2 = new Runnable() {
            public void run() {
                long elapsed = System.currentTimeMillis() - startTime;
                String msg = String.format("→ %.1f сек: post(runn2) – текст изменён на \"runn2\"", elapsed / 1000.0);
                binding.tvInfo.append(msg + "\n");
                binding.tvInfo.append("   (Задача поставлена в очередь, выполняется после завершения предыдущих событий)\n");
            }
        };

        final Runnable runn3 = new Runnable() {
            public void run() {
                long elapsed = System.currentTimeMillis() - startTime;
                String msg = String.format("→ %.1f сек: postDelayed(runn3, 2000) – текст изменён на \"runn3\"", elapsed / 1000.0);
                binding.tvInfo.append(msg + "\n");
                binding.tvInfo.append("   (Задача отложена на 2 секунды после вызова)\n");
            }
        };

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.tvInfo.postDelayed(runn3, 2000);
                    binding.tvInfo.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}