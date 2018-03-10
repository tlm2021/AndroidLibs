package com.travismosley.datalibs.database;

import com.travismosley.datalibs.database.cursor.Cursor;

/**
 * Interface for objects that can be populated from a Cursor object
 */
public interface PopulateFromCursorInterface {
    void populate(Cursor cursor);
}
