package com.example.shaker;

import android.os.AsyncTask;

import org.json.JSONObject;

/**
 * Created by erwin on 9/9/13.
 */
public class doJson extends AsyncTask<String, Void, JSONObject> {
    private JSONObject json;

    @Override
    protected JSONObject doInBackground(String... strings) {
        UserFunctions userFunction = new UserFunctions();
        json = userFunction.tambahData(strings[0], strings[1], strings[2], strings[3], strings[4]);
        return json;
    }

}
