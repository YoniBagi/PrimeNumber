package bagi.primenumber;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.math.BigInteger;
import java.util.ArrayList;

import bagi.adapters.TableAdapter;
import bagi.primenumber.databinding.ActivityTableBinding;
import bagi.views.CustomLtrGridLayoutManager;
import bagi.views.SpacesItemDecoration;

public class TableActivity extends AppCompatActivity {
    private static final int BIG_NUMBER = 9999;
    //ArrayList<Integer> mArrayList = new ArrayList<>();
    ArrayList<String> mStrings = new ArrayList<>();
    RecyclerView mRecyclerView;
    //int count;
    BigInteger mBigInteger = new BigInteger(String.valueOf(BIG_NUMBER));
    TableAdapter tableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTableBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_table);
        binding.setTableActivity(this);
        mRecyclerView = binding.rvNumbers;
        mRecyclerView.setLayoutManager(new CustomLtrGridLayoutManager(this, 5));
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(6));
        for (int i = 0; i < mBigInteger.intValue(); i++) {
            //mArrayList.add(i);
            mStrings.add(String.valueOf(i));
        }
        //count = 99;
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (!mRecyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
        for (int i = 0; i < BIG_NUMBER; i++) {
            //count++;
            mBigInteger = mBigInteger.add(BigInteger.ONE);
            //mArrayList.add(count);
            mStrings.add(mBigInteger.toString());
        }
        tableAdapter.updateArr(mStrings);
    }

    public TableAdapter getAdapter() {
        tableAdapter = new TableAdapter(mStrings);
        return tableAdapter;
    }
}
