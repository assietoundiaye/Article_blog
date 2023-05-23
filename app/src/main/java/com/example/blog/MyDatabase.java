package com.example.blog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabase extends SQLiteOpenHelper {
    private final Context context;
    private static final String DATABASE_NAME ="article";
    private static final int DATABASE_VERSION =1;


    public static final String TABLE ="mesblogs";
    public static final String ID ="_id";
    public static final String AUTHOR ="auteur";
    public static final String TITLE ="titre";
    public static final String TEXT ="text";
    public static final String DATE ="date";

    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE +
                " ( "+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                AUTHOR+" TEXT,"+
                TITLE+" TEXT,"+
                TEXT+" TEXT,"+
                DATE+" TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE);
        onCreate(db);
    }

    void addBlog(String auteur,String title,String text,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(AUTHOR,auteur);
        cv.put(TITLE,title);
        cv.put(TEXT,text);
        cv.put(DATE,date);
        long result = db.insert(TABLE,null,cv);
        if (result == -1){
            Toast.makeText(context,"L'ajout a échoué",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context,"L'ajout est un Succès",Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readAllData(){
        String query ="SELECT * FROM "+TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
}
