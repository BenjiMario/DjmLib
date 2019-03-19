package com.example.djmsdk.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.Objects;

/*
 *Copyright (c) Benjinn
 *(C) Benjinn 2019
 */
public class Utils {
    private final static String PREFS_FILE = "DjmPrefs";
    private final static String PREF_FCM_TOKEN = "FcmToken";
    private final static String PREF_DJM_TOKEN = "DjmToken";

    public static String getFCMToken(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        return sp.getString(PREF_FCM_TOKEN, "");
    }

    private static void setFCMToken(Context ctx, String token) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();
        spe.putString(PREF_FCM_TOKEN, token);
        spe.apply();
    }

    public static String getDjmToken(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        return sp.getString(PREF_DJM_TOKEN, "");
    }

    public static void setDjmToken(Context ctx, String token) {
        SharedPreferences sp = ctx.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();
        spe.putString(PREF_DJM_TOKEN, token);
        spe.apply();
    }

    public static void getFCMTokenFromServer(final Context ctx) {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.e("FCM", "FCMTOKEN ERROR");
                            return;
                        }
                        Utils.setFCMToken(ctx,
                                Objects.requireNonNull(task.getResult()).getToken());
                        Log.e("FCM", "FCMTOKEN OK : " +  task.getResult().getToken());
                    }
                });
    }

}
