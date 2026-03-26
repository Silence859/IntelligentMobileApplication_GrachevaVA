package ru.mirea.gracheva.thread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

import ru.mirea.gracheva.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textView2.setText("Имя текущего потока: " + Thread.currentThread().getName());
        Thread.currentThread().setName("МОЙ НОМЕР ГРУППЫ: БСБО-53-24, НОМЕР ПО СПИСКУ: 8, МОЙ ЛЮБИМЫЙ ФИЛЬМ: Обитель теней");
        binding.textView2.append("\n Новое имя потока: " + Thread.currentThread().getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(Thread.currentThread().getStackTrace()));
        Log.d(MainActivity.class.getSimpleName(), "Group: " + Thread.currentThread().getThreadGroup());

        binding.btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void	onClick(View v)	{
                String totalStr = binding.editTotalPairs.getText().toString();
                String daysStr = binding.editDays.getText().toString();

                if (totalStr.isEmpty() || daysStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Заполните оба поля", Toast.LENGTH_SHORT).show();
                    return;
                }

                int totalPairs = Integer.parseInt(totalStr);
                int days = Integer.parseInt(daysStr);

                if (days == 0) {
                    Toast.makeText(MainActivity.this, "Количество дней не может быть нулём", Toast.LENGTH_SHORT).show();
                    return;
                }
                new	Thread(new Runnable()	{
                    double average = (double) totalPairs / days;
                    public	void run()	{
                        int	numberThread = counter++;
                        Log.d("ThreadProject",	String.format("Запущен поток № %d студентом группы № %s	номер по списку	№ %d",	numberThread,	"БСБО-53-24",	8));
                        String result = String.format("Среднее: %.2f пар в день", average);
                        binding.tvResult.setText(result);
                        Log.d("UIThread", "Результат обновлён: " + result);
                        long endTime = System.currentTimeMillis() +	20 * 1000;
                        while (System.currentTimeMillis() < endTime){
                            synchronized(this){
                                try	{
                                    wait(endTime - System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(),"Endtime:	"	+	endTime);
                                }catch(Exception e){
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток №" +	numberThread);
                        }
                    }
                }).start();
            }
        });
    }
}