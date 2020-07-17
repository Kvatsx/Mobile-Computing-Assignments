package com.mc.kvats.assignment_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ( intent.getAction().intern() == Intent.ACTION_BOOT_COMPLETED ) {
            Toast.makeText(context, "BOOT_COMPLETED intent detected!", Toast.LENGTH_LONG).show();
        }
        else if ( intent.getAction().intern() == Intent.ACTION_POWER_CONNECTED ) {
            Toast.makeText(context, "ACTION_POWER_CONNECTED intent detected!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "AIRPLANE_MODE intent detected!", Toast.LENGTH_LONG).show();
        }
    }
}
