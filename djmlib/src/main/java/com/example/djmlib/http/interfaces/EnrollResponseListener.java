package com.example.djmlib.http.interfaces;

import android.content.Context;
import org.json.JSONObject;


public interface EnrollResponseListener extends ErrorResponseListener {
      void onServerServiceOkResponse(Context ctx, JSONObject response);
}
