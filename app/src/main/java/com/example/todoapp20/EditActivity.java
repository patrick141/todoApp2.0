package com.example.todoapp20;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp20.databinding.ActivityEditBinding;
import com.example.todoapp20.models.TODOItem;

public class EditActivity extends AppCompatActivity {
    TODOItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEditBinding binding = ActivityEditBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Toast.makeText(this, "" + item, Toast.LENGTH_SHORT).show();
    }
}