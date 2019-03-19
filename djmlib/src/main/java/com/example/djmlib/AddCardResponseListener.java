package com.example.djmlib;

import android.content.Context;
import org.json.JSONObject;

/*
 *                       Copyright (c) Benjinn
 *
 *                            (C) Benjinn 2019

 */interface AddCardResponseListener extends ErrorResponseListener{
    void onServerCardResponse(Context ctx, JSONObject responseObject);
}
