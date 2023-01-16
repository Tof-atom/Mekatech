package ca.appmobilecourse.mekatech.Session;

import android.content.Context;
import android.content.SharedPreferences;

import ca.appmobilecourse.mekatech.Models.User;

public class SessionManagement {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS_NAME = "shared_prefs";
    // key for storing userId
    public static final String USER_ID_KEY = "userID_key";
    // key for storing email.
    public static final String EMAIL_KEY = "email_key";
    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    public SessionManagement(Context context) {

        this.sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
//        editor.clear();
//        editor.commit();
    }

    //save session of user whenever user is logged in
    public void saveSession(User user) {
        editor.putInt(USER_ID_KEY, user.get_id());
        editor.putString(EMAIL_KEY, user.getEmail());
        editor.putString(PASSWORD_KEY, user.getPassword());
        editor.commit();
    }

    // return user whose session is saved
    public int getSession(){
        //return user id whose session is saved
        int userID = sharedPreferences.getInt(USER_ID_KEY, -1);
        return userID;
    }

    //delete current record in session
    public void removeSession(){
        editor.clear();
        editor.commit();
        editor.putInt(USER_ID_KEY, -1).commit();
    }
}
