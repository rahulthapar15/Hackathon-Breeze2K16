package com.geek.firstaid.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Shivang Goel  on 19/02/16.
 */

public class NetworkUtils {

    /**
     * Private Instance Variable
     */
    private static NetworkUtils classInstance = null;

    /**
     * Private Constructor to make this class singleton
     */
    private NetworkUtils(){}

    /**
     * Method return the class instance
     * @return NetworkUtils
     */
    public static NetworkUtils getInstance(){
        if(classInstance == null){
            classInstance = new NetworkUtils();
        }
        return classInstance;
    }
    /**
     * Method call to know internet connectivity
     * @param context
     * @return boolean
     */
    public boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Method call to know internet connectivity
     * @param context
     * @return boolean
     */
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /**
     * Method call to clean object from memory
     */
    public void cleanObject(){
        classInstance = null;
    }

}