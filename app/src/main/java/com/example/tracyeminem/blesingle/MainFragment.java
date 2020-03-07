package com.example.tracyeminem.blesingle;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.tracyeminem.blesingle.databinding.FragmentListBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainFragment extends Fragment {
    private FragmentListBinding mBinding;
    private BtDeviceAdapter mAdapter;
    public MainFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_scan:
                BtDeviceViewModel viewModel = ViewModelProviders.of(requireActivity()).get(BtDeviceViewModel.class);
                viewModel.startScan();
                break;
            case R.id.menu_no_sense_connect:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BtDeviceViewModel viewModel = ViewModelProviders.of(requireActivity()).get(BtDeviceViewModel.class);
        mBinding.setViewmodel(viewModel);
        mBinding.setLifecycleOwner(this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mBinding.recyclerView.setLayoutManager(layoutManager);
        mAdapter = new BtDeviceAdapter();
        mBinding.recyclerView.setAdapter(mAdapter);

        viewModel.getLeDevice().observe(getViewLifecycleOwner(), new Observer<List<BluetoothDevice>>() {
            @Override
            public void onChanged(List<BluetoothDevice> bluetoothDevices) {
                Utils.log("onChanged " + bluetoothDevices.size());
                mAdapter.submitList(bluetoothDevices);
            }
        });

    }
}
