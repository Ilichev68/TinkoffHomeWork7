package com.example.user.tinkoffhomework7.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.user.tinkoffhomework7.Node;
import com.example.user.tinkoffhomework7.NodesColor;


/**
 * Created by User on 12.11.2017.
 */

public class NodesCursorWrapper extends CursorWrapper {

    public NodesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public NodesColor getNode(){
        String value = getString(getColumnIndex(BaseHelper.VALUE));

        NodesColor nodesColor = new NodesColor();
        Node node = new Node();
        node.setValue(Integer.valueOf(value));
        node.setId(getLong(getColumnIndex("id")));
        nodesColor.setNode(node);

        return nodesColor;
    }
}
