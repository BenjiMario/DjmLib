package com.example.djmsdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.djmlib.model.Card;
import com.example.djmlib.SdkPublicInterface;
import com.example.djmlib.StaticSdk;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements SdkPublicInterface {

    public final static String DEBUGAPP = "DJMAPP";

    private StaticSdk sdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tvtest);
       // sdk = StaticSdk.getInstance();

        //sdk.registerObserver(this);

  /*      sdk.enrollToService(this, "Ben", "Leeroy",
                "toto@gmail.com", Utils.getFCMToken(this));
*/

   //    sdk.addCard(this);


//        sdk.deleteCard(this, new Card());
//        sdk.getAllCards(this);




    }



    @Override
    public void onServiceAvailable(String token) {
        Log.e(DEBUGAPP, "Service Ready : Djm TOKEN : " + token);
    }

    @Override
    public void onCardAvailable(boolean ok, Card card) {
        if (ok) {
            Log.e(DEBUGAPP, "Card Added :\n" + card);
        } else {
            Log.e(DEBUGAPP, "Error getting card :\n" + card);
        }
    }

    @Override
    public void onCardDeleted(boolean ok) {
        if (ok) {
            Log.e(DEBUGAPP, "Card Deleted");
        } else {
            Log.e(DEBUGAPP, "Error deleting Card");
        }
    }

    @Override
    public void onCardListReceived(ArrayList<Card> cards) {
        if (cards.size() == 0) {
            Log.e(DEBUGAPP, "NO Cards Found");
        } else {
            for (Card c : cards) {
                Log.e(DEBUGAPP, c.toString());
            }
        }
    }

    @Override
    public void onAnalyticsDataReceived(boolean ok, JSONArray data) {

    }

    @Override
    public void onPaymentDone(boolean ok) {

    }


}
