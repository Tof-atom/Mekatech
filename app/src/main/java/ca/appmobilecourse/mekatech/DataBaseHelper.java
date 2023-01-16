package ca.appmobilecourse.mekatech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import ca.appmobilecourse.mekatech.Models.User;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_FIRST_NAME = "USER_FIRST_NAME";
    public static final String COLUMN_USER_LAST_NAME = "USER_LAST_NAME";
    public static final String COLUMN_USER_EMAIL = "USER_EMAIL";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    public static final String COLUMN_USER_GENDER = "USER_GENDER";
    public static final String COLUMN_USER_ID = "USER_ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "mekatech.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_FIRST_NAME + " TEXT, " +
                COLUMN_USER_LAST_NAME + " TEXT, " + COLUMN_USER_EMAIL + " TEXT, "+ COLUMN_USER_PASSWORD + " TEXT, " + COLUMN_USER_GENDER + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_FIRST_NAME, user.getFirstName());
        cv.put(COLUMN_USER_LAST_NAME, user.getLastName());
        cv.put(COLUMN_USER_EMAIL, user.getEmail());
        cv.put(COLUMN_USER_PASSWORD, user.getPassword());
        cv.put(COLUMN_USER_GENDER, user.getGender());

        long insert = db.insert(USER_TABLE, null, cv);

        if(insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public  User searchOne(String email){
        SQLiteDatabase db = this.getReadableDatabase();

        // val query="select * from $TABLE_NAME where username =? and password = ?"
        //val arguments= arrayOf(user, pass)
        //db.rawQuery(query, arguments)
        String query = "SELECT * FROM " + USER_TABLE + " WHERE " + COLUMN_USER_EMAIL + " = '" + email+ "'";
        User user;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            int userId = cursor.getInt(0);
            String userFirstName = cursor.getString(1);
            String userLastName = cursor.getString(2);
            String userEmail = cursor.getString(3);
            String userPassword = cursor.getString(4);
            String userGender = cursor.getString(5);

            user = new User(userId, userFirstName, userLastName, userEmail, userPassword, userGender);
            db.close();
        } else {
            user = new User(-1, "error");
            return user;
        }
        return user;
    }
}
