package bagi.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.ArrayList;

import bagi.primenumber.R;
import bagi.primenumber.databinding.RowRecyclerBinding;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    ArrayList<String> mArrayList;

    public TableAdapter(ArrayList<String> arrayList) {
        mArrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RowRecyclerBinding binding = DataBindingUtil.inflate(inflater, R.layout.row_recycler, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public void updateArr(ArrayList<String> arrayList) {
        mArrayList = arrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RowRecyclerBinding mBinding;

        public ViewHolder(RowRecyclerBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        public void bind(String num) {
            mBinding.setPrime(false);
            if (returnPrime(BigInteger.valueOf(Long.valueOf(num)))){
               mBinding.setPrime(true);
            }
            mBinding.setNumber(num);
            mBinding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), Integer.toString(getAdapterPosition()), Toast.LENGTH_LONG).show();
        }

        public boolean returnPrime(BigInteger number) {
            BigInteger two = new BigInteger("2");
            if (!two.equals(number) && BigInteger.ZERO.equals(number.mod(two)))
                return false;

            if (!number.isProbablePrime(5)) {
                return false;
            }

            for (BigInteger i = new BigInteger("3"); i.multiply(i).compareTo(number) < 1; i = i.add(two)) {
                if (BigInteger.ZERO.equals(number.mod(i)))
                    return false;
            }
            return true;
        }
    }

}
