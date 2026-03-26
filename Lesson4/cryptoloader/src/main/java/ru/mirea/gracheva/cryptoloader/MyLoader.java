package ru.mirea.gracheva.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;import javax.crypto.Cipher;import javax.crypto.SecretKey;import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String> {
    public static final String ARG_ENCRYPTED = "encrypted";
    public static final String ARG_KEY = "key";
    private final byte[] encryptedData;
    private final SecretKey secretKey;

    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args != null) {
            encryptedData = args.getByteArray(ARG_ENCRYPTED);
            byte[] keyBytes = args.getByteArray(ARG_KEY);
            if (keyBytes != null) {
                secretKey = new SecretKeySpec(keyBytes, "AES");
            } else {
                secretKey = null;
            }
        } else {
            encryptedData = null;
            secretKey = null;
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        if (encryptedData == null || secretKey == null) {
            return "Ошибка: нет данных или ключа";
        }
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(encryptedData);
            return new String(decryptedBytes);
        } catch (Exception e) {
            Log.e("MyLoader", "Decryption error", e);
            return "Ошибка дешифровки";
        }
    }
}
