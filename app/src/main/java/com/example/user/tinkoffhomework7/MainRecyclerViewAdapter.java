package com.example.user.tinkoffhomework7;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 12.11.2017.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private List<NodesColor> nodes;

    private Listner listner;

    public MainRecyclerViewAdapter(List<NodesColor> node) {
        this.nodes = node;
    }


    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MainRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainRecyclerViewAdapter.ViewHolder holder, int position) {
        NodesColor node = nodes.get(position);
        holder.bindNode(node);
    }

    @Override
    public int getItemCount() {
        return nodes.size();
    }

    void setListner(Listner listner) {
        this.listner = listner;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView item;
        private NodesColor node;
        private LinearLayout linearLayout;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listner.onClick(node.getNode().getId());
                }
            });
            item = itemView.findViewById(R.id.value);
            linearLayout = itemView.findViewById(R.id.items_layout);
        }

        void bindNode(NodesColor node) {
            this.node = node;
            String str = String.valueOf(node.getNode().getValue());
            item.setText(str);
            if (node.getColor().equals("yellow")) linearLayout.setBackgroundColor(Color.YELLOW);
            if (node.getColor().equals("blue")) linearLayout.setBackgroundColor(Color.BLUE);
            if (node.getColor().equals("red")) linearLayout.setBackgroundColor(Color.RED);
        }

    }

    interface Listner {

        void onClick(long id);
    }

}
