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
 * Created by User on 15.11.2017.
 */

public class ParentsRecyclerViewAdapter extends RecyclerView.Adapter<ParentsRecyclerViewAdapter.ViewHolder> {

    private List<NodesColor> nodes;

    private ParentsRecyclerViewAdapter.ParentsListner listner;

    public ParentsRecyclerViewAdapter(List<NodesColor> node) {
        this.nodes = node;
    }


    @Override
    public ParentsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ParentsRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ParentsRecyclerViewAdapter.ViewHolder holder, int position) {
        NodesColor node = nodes.get(position);
        holder.bindNode(node);
    }

    @Override
    public int getItemCount() {
        return nodes.size();
    }

    void setListner(ParentsRecyclerViewAdapter.ParentsListner listner) {
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
            if (node.getNode().getNodes() != null) linearLayout.setBackgroundColor(Color.YELLOW);
        }

    }

    interface ParentsListner {

        void onClick(long id);
    }
}
