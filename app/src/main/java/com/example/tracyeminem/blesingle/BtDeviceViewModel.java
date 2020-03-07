package com.example.tracyeminem.blesingle;

import android.app.Application;
import android.bluetooth.BluetoothDevice;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class BtDeviceViewModel extends AndroidViewModel {
    private DeviceRepository mDeviceRepository;
    public BtDeviceViewModel(Application application) {
        super(application);
        mDeviceRepository = DeviceRepository.getInstance(application);
    }

    public LiveData<List<BluetoothDevice>> getLeDevice() {
        return mDeviceRepository.getBleDevice();
    }

    public void startScan() {
        mDeviceRepository.startScan();
    }

}
