package com.example.tracyeminem.blesingle;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.ParcelUuid;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DeviceRepository {
    private static Context mContext;
    private MutableLiveData<List<BluetoothDevice>> mDeviceList;
    private BleManager mBleManager;

    private DeviceRepository(Context context) {
        mContext = context;
        mDeviceList = new MutableLiveData<>();
        mDeviceList.setValue(new ArrayList<BluetoothDevice>());
        mBleManager = new BleManager(context);
    }

    private static class ClassHolder {
        private static final DeviceRepository INSTANCE = new DeviceRepository(mContext);
    }

    public static DeviceRepository getInstance(Context context) {
        mContext = context;
        return ClassHolder.INSTANCE;
    }

    public void startScan() {
        mBleManager.starScan(mDeviceList);
    }

    public LiveData<List<BluetoothDevice>> getBleDevice() {
        return mDeviceList;
    }

    public void stopScan() {
        mBleManager.stopScan();
    }


}