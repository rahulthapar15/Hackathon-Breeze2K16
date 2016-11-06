package com.geek.firstaid.utilities;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtils {

	/**
	 * Private Instance Variable
	 */
	private static KeyboardUtils classInstance = null;

	/**
	 * Private Constructor to make this class singleton
	 */
	private KeyboardUtils(){}

	/**
	 * Method return the class instance
	 * @return KeyboardUtils
	 */
	public static KeyboardUtils getInstance(){
		if(classInstance == null){
			classInstance = new KeyboardUtils();
		}
		return classInstance;
	}
	
	public void hideKeyboard(Context context) {
	    // Check if no view has focus:
	    View view = ((Activity)context).getCurrentFocus();
	    if (view != null) {
	        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
	        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	    }
	}

	/**
	 * Method call to clean object from memory
	 */
	public void cleanObject(){
		classInstance = null;
	}

}
