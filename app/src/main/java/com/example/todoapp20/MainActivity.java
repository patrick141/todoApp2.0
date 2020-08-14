package com.example.todoapp20;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp20.databinding.ActivityMainBinding;
import com.example.todoapp20.models.TODOItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    List<TODOItem> items;
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

        //loadItems();
        items = new ArrayList<>();
        rvItems = binding.rvItems;
        etText = binding.etText;
        btnSubmit = binding.btnSubmit;
        final TODOItemAdapter adapter = new TODOItemAdapter(items, this);
        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = etText.getText().toString();
                TODOItem item = new TODOItem();
                item.setComment(comment);
                items.add(item);
                adapter.notifyItemInserted(items.size() - 1);
                etText.setText("");
          //      saveItems();
            }
        });
    }

    /*
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
    }*/

}