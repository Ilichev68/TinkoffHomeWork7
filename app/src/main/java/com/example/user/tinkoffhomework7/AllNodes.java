package com.example.user.tinkoffhomework7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.user.tinkoffhomework7.database.BaseHelper;
import com.example.user.tinkoffhomework7.database.NodesCursorWrapper;
import com.example.user.tinkoffhomework7.database.NodesIdCursorWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 12.11.2017.
 */

public class AllNodes {

    private static AllNodes sAllNodes;
    private SQLiteDatabase mDatabase;

    public static AllNodes get(Context context) {
        if (sAllNodes == null) {
            sAllNodes = new AllNodes(context);
        }
        return sAllNodes;
    }

    private AllNodes(Context context) {
        Context mContext = context.getApplicationContext();
        mDatabase = new BaseHelper(mContext).getWritableDatabase();
    }

    public void addNode(NodesColor node) {
        ContentValues values = getContentValues(node);

        mDatabase.insert(BaseHelper.NODES_TABLE_NAME, null, values);
    }

    public List<NodesColor> getNodes() {
        List<NodesColor> nodes = new ArrayList<>();

        NodesCursorWrapper cursorWrapper = queryNodes(null, null);

        try {
            cursorWrapper.moveToLast();
            while (!cursorWrapper.isBeforeFirst()) {
                nodes.add(cursorWrapper.getNode());
                cursorWrapper.moveToPrevious();
            }
        } finally {
            cursorWrapper.close();
        }


        for (NodesColor node : nodes) {
            long i = node.getNode().getId();
            NodesIdCursorWrapper cursorWrapperForParents = findParents(new String[]{String.valueOf(i)});
            NodesIdCursorWrapper cursorWrapperChildren = findChildren(new String[]{String.valueOf(i)});

            if (cursorWrapperForParents.getCount() >= 1 && cursorWrapperChildren.getCount() == 0) {
                node.setColor("blue");
            } else if (cursorWrapperChildren.getCount() >= 1 && cursorWrapperForParents.getCount() == 0) {
                node.setColor("yellow");
            } else if (cursorWrapperChildren.getCount() >= 1 && cursorWrapperForParents.getCount() >= 1) {
                node.setColor("red");
            }


            cursorWrapperChildren.close();
            cursorWrapperForParents.close();
        }

        return nodes;
    }

    private static ContentValues getContentValues(NodesColor node) {
        ContentValues values = new ContentValues();
        values.put(BaseHelper.VALUE, node.getNode().getValue());

        return values;
    }

    private NodesCursorWrapper queryNodes(String whereClase, String[] whereArgs) {
        Cursor cursor = mDatabase.query(BaseHelper.NODES_TABLE_NAME, null, whereClase, whereArgs, null, null, null);
        return new NodesCursorWrapper(cursor);
    }

    private NodesIdCursorWrapper findParents(String[] whereArgs) {
        Cursor cursor = mDatabase.query(BaseHelper.NODES_ID_TABLE_NAME, null, "pId = ?", whereArgs, null, null, null);
        return new NodesIdCursorWrapper(cursor);
    }

    private NodesIdCursorWrapper findChildren(String[] whereArgs) {
        Cursor cursor = mDatabase.query(BaseHelper.NODES_ID_TABLE_NAME, null, "cId = ?", whereArgs, null, null, null);
        return new NodesIdCursorWrapper(cursor);
    }

}
