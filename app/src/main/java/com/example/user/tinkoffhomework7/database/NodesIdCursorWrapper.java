package com.example.user.tinkoffhomework7.database;

import android.database.Cursor;
import android.database.CursorWrapper;


/**
 * Created by User on 14.11.2017.
 */

public class NodesIdCursorWrapper extends CursorWrapper {

    public NodesIdCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public String getParentId(){

        return getString(getColumnIndex(BaseHelper.PARENT_ID));
    }

    public String getChildId(){
        return getString(getColumnIndex(BaseHelper.CHILD_ID));
    }
}
