package com.example.todoapp20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp20.databinding.ActivityEditBinding;

public class EditActivity extends AppCompatActivity {
    EditText etEdit;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEditBinding binding = ActivityEditBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        etEdit = binding.etEdit;
        btnSave = binding.btnSave;

        getSupportActionBar().setTitle("Edit Item");
        etEdit.setText(getIntent().getStringExtra(MainActivity.TEXT));
        btnSave.setOnClickListener(new View.OnClickListener() {
            //On click, user will open screen and be able to update item.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.TEXT, etEdit.getText().toString());
                intent.putExtra(MainActivity.POSITION, getIntent().getExtras().getInt(MainActivity.POSITION));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

