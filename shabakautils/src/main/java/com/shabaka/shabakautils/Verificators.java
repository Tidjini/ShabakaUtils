package com.shabaka.shabakautils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Verificators {

    /**
     * Verificate if the network is available
     * @param appContext
     * @return
     */
    public static boolean isNetworkAvailable(Application appContext) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public static boolean isMapServicesOk(){
        GoogleApiAvailability.getInsatance();
    }
}
