package com.example.djmlib.http.interfaces;

import android.content.Context;
import org.json.JSONObject;

public interface AddCardResponseListener extends ErrorResponseListener {
    void onServerCardResponse(Context ctx, JSONObject responseObject);
}
