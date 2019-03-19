package com.example.djmlib.http.interfaces;

import com.android.volley.VolleyError;


interface ErrorResponseListener {
    void onServerErrorResponse(VolleyError error);
 }
