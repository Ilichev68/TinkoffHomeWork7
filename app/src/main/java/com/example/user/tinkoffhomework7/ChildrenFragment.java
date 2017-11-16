package com.example.user.tinkoffhomework7;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChildrenFragment extends Fragment implements ChildrenRecyclerViewAdapter.ChildrenListner {

    private RecyclerView nodesRecyclerView;
    private ChildrenRecyclerViewAdapter adapter;
    private long nodeId;
    private ChooseDialogFragment chooserDialog = new ChooseDialogFragment();

    public ChildrenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_children, container, false);

        nodesRecyclerView = view.findViewById(R.id.children_recycler_view);
        nodesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        update();

        return view;
    }


    private void update() {
        AllNodes allNodes = AllNodes.get(getActivity());
        List<NodesColor> nodes = allNodes.getNodes();
        adapter = new ChildrenRecyclerViewAdapter(nodes);
        adapter.setListner(this);
        nodesRecyclerView.setAdapter(adapter);

    }

    public void setId(long id) {
        nodeId = id;
    }

    @Override
    public void onClick(long id) {
        chooserDialog.setId(nodeId);
        chooserDialog.setNodeIdInRecycler(id);
        chooserDialog.show(getFragmentManager(), String.valueOf(chooserDialog));
    }
}
