package skrasel.com.posttest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "user_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(User.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertUser(User user) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(User.USER_NAME, user.getName());
        values.put(User.USER_COMMENTS, user.getComment());
        //values.put(User.COLUMN_TIMESTAMP, String.valueOf(System.currentTimeMillis()));

        // insert row
        long id = db.insert(User.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public List<User> getAllUsers() {
        List<User> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + User.TABLE_NAME + " ORDER BY " +
                User.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(User.USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(User.USER_NAME)));
                user.setComment(cursor.getString(cursor.getColumnIndex(User.USER_COMMENTS)));
                user.setTimeStamp(cursor.getString(cursor.getColumnIndex(User.USER_COMMENTS)));

                notes.add(user);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }


    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + User.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(User note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.USER_NAME, note.getName());

        // updating row
        return db.update(User.TABLE_NAME, values, User.USER_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(User note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(User.TABLE_NAME, User.USER_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
}
