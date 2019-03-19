package com.example.djmlib;

import android.content.Context;
import org.json.JSONObject;

/*
 *                       Copyright (c) Benjinn
 *
 *                            (C) Benjinn 2019
 */public interface EnrollResponseListener extends ErrorResponseListener{
      void onServerServiceOkResponse(Context ctx, JSONObject response);
}
