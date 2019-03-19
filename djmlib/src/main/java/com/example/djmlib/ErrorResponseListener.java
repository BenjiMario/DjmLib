package com.example.djmlib;

import com.android.volley.VolleyError;

/*
 *                       Copyright (c) Benjinn
*/ interface ErrorResponseListener {
    void onServerErrorResponse(VolleyError error);
 }
