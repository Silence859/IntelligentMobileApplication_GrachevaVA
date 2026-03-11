package ru.mirea.gracheva.favoritebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InputActivity extends AppCompatActivity {
    private EditText editTextBook;
    private EditText editTextQuote;
    private Button btnSendBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        editTextBook = findViewById(R.id.editTextBook);
        editTextQuote = findViewById(R.id.editTextQuote);
        btnSendBack = findViewById(R.id.buttonSendBack);

        btnSendBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String book = editTextBook.getText().toString().trim();
                String quote = editTextQuote.getText().toString().trim();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("book_name", book);
                resultIntent.putExtra("quote", quote);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}