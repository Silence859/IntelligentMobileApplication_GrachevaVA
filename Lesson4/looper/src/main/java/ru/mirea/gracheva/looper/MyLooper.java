package ru.mirea.gracheva.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLooper extends Thread {
    public Handler mHandler;
    private Handler mainHandler;

    public MyLooper(Handler mainThreadHandler) {
        mainHandler = mainThreadHandler;
    }

    @Override
    public void run() {
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                int age = bundle.getInt("age");
                String job = bundle.getString("job");

                try {
                    Log.d("MyLooper", "Начинаю задержку на " + age + " секунд");
                    Thread.sleep(age * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String result = "Ваша профессия: " + job + ", возраст: " + age;
                Log.d("MyLooper", result);

                if (mainHandler != null) {
                    Message reply = Message.obtain();
                    Bundle replyBundle = new Bundle();
                    replyBundle.putString("result", result);
                    reply.setData(replyBundle);
                    mainHandler.sendMessage(reply);
                }
            }
        };
        Looper.loop();
    }
}
