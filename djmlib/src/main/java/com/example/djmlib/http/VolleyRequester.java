package com.example.djmlib.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.djmlib.StaticSdk;
import com.example.djmlib.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class VolleyRequester {
    private StaticSdk sd;
    private final static String URL_DJM_BACK = "http://benjiji.fr/djm/djmBack.php";
    public VolleyRequester(StaticSdk ssdk) {
        this.sd = ssdk;
    }

    public void getNewCardFromServer(final Context ctx) {
        RequestQueue queue = Volley.newRequestQueue(ctx);
        JSONObject jo = new JSONObject();
        try {
            jo.accumulate("generateCard", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequest<JSONObject> jsonRequest =
                new JsonObjectRequest(Request.Method.POST, URL_DJM_BACK, jo,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                    Log.e("SRV DEBUG", response.toString());
                                    sd.onServerCardResponse(ctx, response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        sd.onServerErrorResponse(error);
                    }
                });
        queue.add(jsonRequest);
    }


    public void activateService(final Context ctx, User u, String auth) {
        RequestQueue queue = Volley.newRequestQueue(ctx);
        JSONObject jo = new JSONObject();
        try {
            jo.accumulate("fcmauth", auth);
            jo.accumulate("fname", u.getUserFirstName());
            jo.accumulate("lname", u.getUserLastName());
            jo.accumulate("mail", u.getUserEmail());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequest<JSONObject> jsonRequest =
                new JsonObjectRequest(Request.Method.POST, URL_DJM_BACK, jo,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                sd.onServerServiceOkResponse(ctx, response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        sd.onServerErrorResponse(error);
                    }
                });
        queue.add(jsonRequest);
    }

    public void debugRequest(Context ctx, String url){
        RequestQueue queue = Volley.newRequestQueue(ctx);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.e("SRVRQDEBUG", "Response is: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("SRVRQDEBUG", "Response is: " + error.getMessage());
            }
        });
        queue.add(stringRequest);
    }
}
