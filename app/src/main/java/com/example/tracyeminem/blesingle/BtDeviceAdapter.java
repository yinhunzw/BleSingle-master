package com.example.tracyeminem.blesingle;

import android.bluetooth.BluetoothDevice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tracyeminem.blesingle.databinding.LayoutItemListBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class BtDeviceAdapter extends ListAdapter<BluetoothDevice,BtDeviceAdapter.MyViewHolder> {

    public BtDeviceAdapter() {

        super(new DiffUtil.ItemCallback<BluetoothDevice>() {
            @Override
            public boolean areItemsTheSame(@NonNull BluetoothDevice oldItem, @NonNull BluetoothDevice newItem) {
                Utils.log(oldItem.toString() + "   " + newItem.toString());
                return oldItem.getName().equals(newItem.getName());
            }

            @Override
            public boolean areContentsTheSame(@NonNull BluetoothDevice oldItem, @NonNull BluetoothDevice newItem) {
                return oldItem.getName().equals(newItem.getName())
                        && oldItem.getAddress().equals(newItem.getAddress());
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LayoutItemListBinding itemBinding = LayoutItemListBinding.inflate(layoutInflater, parent, false);
        return new MyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Utils.log("position " + position);
        BluetoothDevice device = getItem(position);
        holder.bind(device);
    }
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        LayoutItemListBinding itemBinding = LayoutItemListBinding.inflate(layoutInflater, parent, false);
//        return new MyViewHolder(itemBinding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Utils.log("position " + position);
//        BluetoothDevice device = mList.get(position);
//        holder.bind(device);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mList.size();
//    }
//
//    public void refresh(List<BluetoothDevice> userInfoList) {
//        Utils.log("refresh:  " + mList.hashCode()+ "           " + userInfoList.hashCode());
//        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DeviceDiffCallBack(mList, userInfoList), true);
//
//        diffResult.dispatchUpdatesTo(this);
//        Utils.log("refresh " + userInfoList.size());
//        mList.clear();
//        mList.addAll(userInfoList);
//    }
//
//    public class DeviceDiffCallBack extends DiffUtil.Callback {
//        private List<BluetoothDevice> mOldDeviceList;
//        private List<BluetoothDevice> mNewDeviceList;
//
//        public DeviceDiffCallBack(List<BluetoothDevice> oldDeviceList, List<BluetoothDevice> newDeviceList) {
//            this.mOldDeviceList = oldDeviceList;
//            this.mNewDeviceList = newDeviceList;
//        }
//
//        @Override
//        public int getOldListSize() {
//            return mOldDeviceList != null ? mOldDeviceList.size() : 0;
//        }
//
//        @Override
//        public int getNewListSize() {
//            return mNewDeviceList != null ? mNewDeviceList.size() : 0;
//        }
//
//        @Override
//        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
//            boolean result = false;
//            result=  mOldDeviceList.get(oldItemPosition).getAddress().equals(mNewDeviceList.get(newItemPosition).getAddress());
//            Utils.log("areItemsTheSame " + result);
//            return result;
//        }
//
//        @Override
//        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
//            boolean result = false;
//            result = mOldDeviceList.get(oldItemPosition).getAddress().equals(mNewDeviceList.get(newItemPosition).getAddress())
//                    && mOldDeviceList.get(oldItemPosition).getName().equals(mNewDeviceList.get(newItemPosition).getName());
//            Utils.log("areContentsTheSame " + result);
//            return result;
//        }
//    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemListBinding mItemBinding;
        public MyViewHolder(LayoutItemListBinding binding) {
            super(binding.getRoot());
            mItemBinding = binding;
        }

        public void bind(BluetoothDevice device) {
            mItemBinding.setDevice(device);
            mItemBinding.executePendingBindings();
        }
    }
}
