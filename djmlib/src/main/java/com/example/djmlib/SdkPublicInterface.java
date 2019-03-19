package com.example.djmlib;

import com.example.djmlib.model.Card;

import org.json.JSONArray;
import java.util.ArrayList;


public interface SdkPublicInterface {
    void onServiceAvailable(String token);
    void onCardAvailable(boolean ok, Card card);
    void onCardDeleted(boolean ok);
    void onCardListReceived(ArrayList<Card> cards);
    void onAnalyticsDataReceived(boolean ok, JSONArray data);
    void onPaymentDone(boolean ok);
}
