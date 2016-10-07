package com.mobilecomputingfinals.valdoriaappfinals;

/**
 * Created by DarkHorse on 06/10/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.HashMap;

public class DBAdapter extends SQLiteOpenHelper {


    private static final String TAG=DBAdapter.class.getSimpleName();
    private static final String DATABASE_NAME = "registered_users.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "User";
    private static final String KEY_ID = "id";
    private static final String KEY_UNAME = "Uname";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_PASSWORD = "Pass";
    private static final String KEY_FNAME = "Fname";
    private static final String KEY_LNAME = "Lname";

    public DBAdapter(Context _context){
        super(_context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_UNAME + " TEXT UNIQUE," + KEY_EMAIL + " TEXT UNIQUE," + KEY_PASSWORD + " TEXT," + KEY_FNAME + " TEXT," + KEY_LNAME + " TEXT" + ")";
        sqlDB.execSQL(CREATE_USER_TABLE);

        Log.d(TAG, "Database table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String registerUser(String Fname, String Lname, String Uname, String Email, String Pass) {
        SQLiteDatabase wdb = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UNAME, Uname);
        values.put(KEY_EMAIL, Email);
        values.put(KEY_PASSWORD, Pass);
        values.put(KEY_FNAME, Fname);
        values.put(KEY_LNAME, Lname);

        long id = wdb.insert(TABLE_USER, null, values);
        wdb.close();

        Log.d(TAG, "Successfully added user: " + id);
        Log.d(TAG, "Successfully added user: " + Email);
        Log.d(TAG, "Successfully added user: " + Pass);
        return "User successfully added";
    }



    public boolean validateuname(String Uname){

        String selectQuery="SELECT * FROM " + TABLE_USER + " WHERE " + KEY_UNAME + "=\"" + Uname + "\"";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            return true;
        }

        else {
            cursor.close();
            return false;
        }

    }
    public boolean validateemail(String Email){

        String selectQuery="SELECT * FROM " + TABLE_USER + " WHERE " + KEY_EMAIL + "=\"" + Email + "\"";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            return true;
        }

        else {
            cursor.close();
            return false;
        }

    }

    public boolean validateUser(String UnameOrEmail, String Pass){
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_EMAIL + "=\"" + UnameOrEmail + "\""+" OR " +  KEY_UNAME + "=\"" + UnameOrEmail + "\"";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("Uname",cursor.getString(1));
            user.put("Email", cursor.getString(2));
            user.put("Pass", cursor.getString(3));
        }
        cursor.close();
        db.close();
        Log.d(TAG, "Fetching user from SQLite: " + user.toString());
        Log.d(TAG, "Fetching user from SQLite: " + user.toString());
        if (((UnameOrEmail.equals(user.get("Email"))) || (UnameOrEmail.equals(user.get("Uname")))) &&(Pass.equals(user.get("Pass")))) {
            return true;
        } else {
            Log.d(TAG, "does not exist");
            return false;
        }
    }

}
