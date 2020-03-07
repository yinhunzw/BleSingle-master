package com.example.tracyeminem.blesingle;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Handler;
import android.os.ParcelUuid;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class BleManager {
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mScanner;
    private static final long SCAN_PERIOD = 10000;
    private MutableLiveData<List<BluetoothDevice>> mDeviceList;
    private Context mContext;
    private boolean mIsScaning = false;

    public BleManager(Context context) {
        mContext = context;
        mDeviceList = new MutableLiveData<>();
        mDeviceList.setValue(new ArrayList<BluetoothDevice>());
        BluetoothManager bluetoothManager = (BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        mScanner = mBluetoothAdapter.getBluetoothLeScanner();
    }

    private ScanCallback mBLEScanCallback =
            new ScanCallback() {
                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    super.onScanResult(callbackType, result);
                    if(!mDeviceList.getValue().contains(result.getDevice())) {
                        Utils.log("add into livedata" + result.getDevice().getName());
                        mDeviceList.getValue().add(result.getDevice());
                        mDeviceList.setValue(mDeviceList.getValue());
                    }
                }

                @Override
                public void onScanFailed(int errorCode) {
                    super.onScanFailed(errorCode);
                    Utils.log("errorCode:  "+ errorCode);
                }

                @Override
                public void onBatchScanResults(List<ScanResult> results) {
                    super.onBatchScanResults(results);
                    Utils.log("onBatchScanResults:  "+ results.size());
                }
            };

    public void starScan(){
        Utils.log("starScan");
        stopScan();
        //指定需要识别到的蓝牙设备
        List<ScanFilter> scanFilterList = new ArrayList<>();

        ScanFilter.Builder builder2 = new ScanFilter.Builder();

        builder2.setDeviceName("test2");//你要扫描的设备的名称，如果使用lightble这个app来模拟蓝牙可以直接设置name
        builder2.setServiceUuid(ParcelUuid.fromString("BB9F8CFA-654B-4B76-976A-A7982D2DF0F3"));
        ScanFilter scanFilter2 = builder2.build();

        scanFilterList.add(scanFilter2);

        //指定蓝牙的方式，这里设置的ScanSettings.SCAN_MODE_LOW_LATENCY是比较高频率的扫描方式
        ScanSettings.Builder settingBuilder = new ScanSettings.Builder();
        settingBuilder.setScanMode(ScanSettings.SCAN_MODE_LOW_POWER);
        settingBuilder.setMatchMode(ScanSettings.MATCH_MODE_AGGRESSIVE);
        settingBuilder.setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES);
        ScanSettings settings = settingBuilder.build();
        mScanner.startScan(scanFilterList,settings, mBLEScanCallback);
    }

    public void stopScan(){
        mScanner.stopScan(mBLEScanCallback);
    }
}