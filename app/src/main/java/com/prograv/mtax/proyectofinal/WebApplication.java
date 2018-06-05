package com.prograv.mtax.proyectofinal;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by mtax on 04/06/2018.
 */

public class WebApplication extends Application {
    private RequestQueue queue;

    public RequestQueue getQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(this);
        }
        return queue;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        queue.cancelAll("FINALIZADO");
    }
}
