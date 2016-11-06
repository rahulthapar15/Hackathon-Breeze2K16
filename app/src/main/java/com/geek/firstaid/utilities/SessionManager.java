package com.geek.firstaid.utilities;

import android.content.Context;
import android.content.SharedPreferences;


public class SessionManager {


    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "TTN";


    private static final String IS_HINDI = "isHindi";
    private static final String PLACE = "place";
    private static final String IS_GENDER = "gender";
    private static final String IS_LOGIN = "login";

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * FOR HINDI true and English False
     * */
    public void saveLanguage(boolean isHindi ){
        editor.putBoolean(IS_HINDI, isHindi);
        editor.commit();
    }

    /**
     * RETURN TRUE IF HINDI,OR false
     * @return
     */
    public boolean getLanguage(){
        return pref.getBoolean(IS_HINDI, false);
    }
    public void savePlace(String placeName ){
        editor.putString(PLACE, placeName);
        editor.commit();
    }
    public String getPlace(){
        return pref.getString(PLACE,"");
    }
    /**
     * Create login session
     * FOR MALE FALSE and FOR FEMALE TRUE
     * */
    public void saveGender(boolean isMail){
        editor.putBoolean(IS_GENDER, isMail);
        editor.commit();
    }
    /**
     * RETURN FALSE IF USER US MAIL,OTHERWISE FEAMALE
     * @return
     */
    public boolean getGender(){
        return pref.getBoolean(IS_GENDER, false);
    }

    /**
     * Create login session
    * TRUE IF LOGIN,
    * */
    public void setLogin(boolean login){
        editor.putBoolean(IS_LOGIN, login);
        editor.commit();
    }
    /**
     * IF TRUE THEN LOGIN
     * @return
     */
    public boolean isLogin(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void clearLocalData(){
        _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE).edit().clear().commit();
    }

}
