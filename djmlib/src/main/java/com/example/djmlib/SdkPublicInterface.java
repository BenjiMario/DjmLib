package com.example.djmlib;

import org.json.JSONArray;

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
public interface SdkPublicInterface {
    void onServiceAvailable(String token);
    void onCardAvailable(boolean ok, Card card);
    void onCardDeleted(boolean ok);
    void onCardListReceived(ArrayList<Card> cards);
    void onAnalyticsDataReceived(boolean ok, JSONArray data);
    void onPaymentDone(boolean ok);
}
