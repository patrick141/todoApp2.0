package com.example.todoapp20;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp20.databinding.ActivityMainBinding;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "MainActivity";
    static final String TEXT = "text";
    static final String POSITION = "position";
    static final int KEY_EDIT = 12;
    List<String> items;
    RecyclerView rvItems;
    EditText etText;
    Button btnSubmit;
    TODOItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        items = new ArrayList<>();
        rvItems = binding.rvItems;
        etText = binding.etText;
        btnSubmit = binding.btnSubmit;
        loadItems();

        TODOItemAdapter.OnLongClickListener onLongClickListener = new TODOItemAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClicked(int position) {
                items.remove(position);
                adapter.notifyItemRemoved(position);
            }
        };

        TODOItemAdapter.OnClickListener onClickListener = new TODOItemAdapter.OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent i = new Intent(MainActivity.this,EditActivity.class);
                i.putExtra(TEXT, items.get(position));
                i.putExtra(POSITION, position);
                startActivityForResult(i, KEY_EDIT);
            }
        };

        adapter = new TODOItemAdapter(items, this, onLongClickListener, onClickListener);
        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = etText.getText().toString();
                items.add(comment);
                adapter.notifyItemInserted(items.size() - 1);
                etText.setText("");
                saveItems();
            }
        });
    }


    private File getDataFile(){
        return new File(getFilesDir(), "data.txt");
    }

    private void loadItems(){
        try {
            items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e(TAG, "Error reading items", e);
            items = new ArrayList<>();
        }
    }
    //Write to the file and saves the item
    public void saveItems(){
        try {
            FileUtils.writeLines(getDataFile(), items);
        } catch (IOException e) {
            Log.e("MainActivity", "Error writing items", e);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //User will be notified that item was updated
        if (resultCode == RESULT_OK && requestCode == KEY_EDIT) {
            String itemText = data.getStringExtra(TEXT);
            int position = data.getExtras().getInt(POSITION);
            items.set(position, itemText);
            adapter.notifyItemChanged(position);
            saveItems();
            Toast.makeText(getApplicationContext(), "Item updated successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Log.w("MainActivity", "Unknown call to onActivityResult");
        }
    }
}