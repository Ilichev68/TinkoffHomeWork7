package com.example.user.tinkoffhomework7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainRecyclerViewAdapter.Listner {


    private RecyclerView nodesRecyclerView;
    private MainRecyclerViewAdapter adapter;
    private EditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nodesRecyclerView = findViewById(R.id.items_recycler_view);
        nodesRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        Button addItem = findViewById(R.id.add_element);
        input = findViewById(R.id.input_value);


        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newInput = input.getText().toString();
                if (!newInput.isEmpty()) {
                    int value = Integer.parseInt(newInput);
                    addItem(value);
                    update();
                    input.setText("");
                }
            }
        });


    }

    private void addItem(int value) {
        NodesColor nodesColor = new NodesColor();
        Node node = new Node();
        node.setValue(value);
        nodesColor.setNode(node);
        AllNodes.get(this).addNode(nodesColor);
    }

    private void update() {
        AllNodes allNodes = AllNodes.get(MainActivity.this);
        List<NodesColor> nodes = allNodes.getNodes();
        adapter = new MainRecyclerViewAdapter(nodes);
        adapter.setListner(this);
        nodesRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(long id) {
        NodeActivity.start(this, id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }
}


