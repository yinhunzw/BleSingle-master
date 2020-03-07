package com.example.tracyeminem.blesingle;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.ParcelUuid;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.ManagerFactoryParameters;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("zhangwei", "onReceive: ");
        if("ble".equals(intent.getStringExtra("source"))) {
            Log.i("zhangwei", "ble receive: ");
            return;
        }
        BleManager bleManager = new BleManager(context);
        bleManager.starScan();
    }
}
