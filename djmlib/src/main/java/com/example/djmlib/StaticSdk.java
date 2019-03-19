package com.example.djmlib;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
 *                       Copyright (c) Benjinn
 *
 *                            (C) Benjinn 2019
 * All rights are reserved. Reproduction in whole or in part is
 * prohibited without the written consent of the copyright owner.
 * Benjinn reserves the right to make changes without notice at any time.
 * Benjinn makes no warranty, expressed, implied or statutory, including but
 * not limited to any implied warranty of merchantability or fitness for any
 * particular purpose, or that the use will not infringe any third party patent,
 * copyright or trademark. Benjinn must not be liable for any loss or damage
 * arising from its use.
 */
public final class StaticSdk implements SdkInnerInterface,
        AddCardResponseListener, EnrollResponseListener {
    final static String URL_DJM_BACK = "http://benjiji.fr/djm/djmBack.php";
    /**
     * Observer pattern
     */
    private ArrayList<SdkPublicInterface> obs;

    /**
     * Lazy Singleton
     */
    private static StaticSdk INSTANCE = null;
    public static StaticSdk getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new StaticSdk();
        }
        return INSTANCE;
    }
    private StaticSdk(){
        obs =  new ArrayList<>();
        //create db ?
    }

    public void enrollToService(Context ctx, String fName,
                                String lName, String mail, String token){
        User user = new User(fName, lName, mail);
        VolleyRequester vr = new VolleyRequester(this);
        vr.activateService(ctx, user, token);
    }

    /**
     * Public method to call when willing to add a new card in App
     */
    public void addCard(Context ctx){
        VolleyRequester vr = new VolleyRequester(this);
        vr.getNewCardFromServer(ctx);
    }

    public void deleteCard(Context ctx, Card c){
        SdkDb sdb = new SdkDb(ctx);
        notifyCardDeleted(c.deleteCard(sdb));
    }

    public void getAllCards(Context ctx){
        SdkDb sdb = new SdkDb(ctx);

        notifyCardListAvailable(Card.getAllCards(sdb));
    }

    @Override
    public void registerObserver(SdkPublicInterface sdkObserver) {
        if(!obs.contains(sdkObserver)) {
            obs.add(sdkObserver);
        }
    }

    @Override
    public void removeObserver(SdkPublicInterface sdkObserver) {
            obs.remove(sdkObserver);
    }

    private void notifyPaymentDone(){
        for (SdkPublicInterface ob: obs) {
            ob.onPaymentDone(true);
        }
    }

    private void notifyAnalyticsDataReady() {
        for (SdkPublicInterface ob: obs) {
            ob.onAnalyticsDataReceived(true, new JSONArray());
        }
    }
    private void notifyServiceReady(String token){
        for (SdkPublicInterface ob: obs) {
            ob.onServiceAvailable(token);
        }
    }

    private void notifyCardAdded(boolean ok, Card c) {
        for (SdkPublicInterface ob: obs) {
            ob.onCardAvailable(ok, c);
        }
    }

    private void notifyCardDeleted(boolean ok){
        for (SdkPublicInterface ob: obs) {
            ob.onCardDeleted(ok);
        }
    }

    private void notifyCardListAvailable(ArrayList<Card> cards){
        for (SdkPublicInterface ob: obs) {
            ob.onCardListReceived(cards);
        }
    }

    @Override
    public void onServerCardResponse(Context ctx, JSONObject responseObject) {
        try {
            Long l = responseObject.getLong("cardNumber");
            int m = responseObject.getInt("cardExpMonth");
            int y = responseObject.getInt("cardExpYear");
            int t = responseObject.getInt("cardType");
            Card c = new Card(l,m,y,t);
            //store card
            SdkDb sdb = new SdkDb(ctx);
            //notify card added
            notifyCardAdded(c.saveCard(sdb), c);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServerErrorResponse(VolleyError error) {
        Log.e("SRV", "error from server : " + error.getMessage()
        +  " / " + error.getCause());
    }

    @Override
    public void onServerServiceOkResponse(Context ctx, JSONObject response) {
        try {
            //String msg = response.getString("message");
            String token = response.getString("bankToken");
            //Log.e("SRV", "RECEIVED from server : " + msg + "\n" + token);
            notifyServiceReady(token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
