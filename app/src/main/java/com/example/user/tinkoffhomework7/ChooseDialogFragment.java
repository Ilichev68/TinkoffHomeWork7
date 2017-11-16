package com.example.user.tinkoffhomework7;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.tinkoffhomework7.database.BaseHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseDialogFragment extends DialogFragment {

    private long id;
    private long nodeIdInRecycler;
    private SQLiteDatabase mDatabase;


    public ChooseDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mDatabase = new BaseHelper(getActivity().getApplicationContext()).getWritableDatabase();

        View view = inflater.inflate(R.layout.fragment_choose_dialog, null);

        Button addParent = view.findViewById(R.id.add_parent_node);
        Button addChild = view.findViewById(R.id.add_child_node);
        Button del = view.findViewById(R.id.del_node);

        addParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = getContentValues(id, nodeIdInRecycler);

                mDatabase.insert(BaseHelper.NODES_ID_TABLE_NAME, null, values);
                dismiss();
            }
        });

        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = getContentValues(nodeIdInRecycler, id);

                mDatabase.insert(BaseHelper.NODES_ID_TABLE_NAME, null, values);
                dismiss();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.delete(BaseHelper.NODES_ID_TABLE_NAME, "pID = ? AND cID = ?", new String[]{String.valueOf(id), String.valueOf(nodeIdInRecycler)});
                mDatabase.delete(BaseHelper.NODES_ID_TABLE_NAME, "pID = ? AND cID = ?", new String[]{String.valueOf(nodeIdInRecycler), String.valueOf(id)});
                dismiss();
            }
        });
        return view;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNodeIdInRecycler(long nodeIdInRecycler) {
        this.nodeIdInRecycler = nodeIdInRecycler;
    }

    private static ContentValues getContentValues(long pId, long cId) {
        ContentValues values = new ContentValues();
        values.put(BaseHelper.PARENT_ID, String.valueOf(pId));
        values.put(BaseHelper.CHILD_ID, String.valueOf(cId));

        return values;
    }
}
