package com.example.yangwb.studyalipaybill;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycle_bill;
    private LinearLayoutManager linearLayoutManager;
    private AlipayBillAdapter adapter;
    private SmartRefreshLayout refreshLayout;
    private int loadMoreTimes=0;
    List<Bill> bills = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycle_bill = ((RecyclerView) findViewById(R.id.recycle_bill));
        refreshLayout = ((SmartRefreshLayout) findViewById(R.id.refreshLayout));
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //TODO 下拉刷新
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                //TODO 上拉加载
                loadMoreTimes++;
                refreshLayout.finishLoadmore();
            }
        });
        initData();
    }
    private void initData(){
        List<String> data = loadMoreData(loadMoreTimes);
        bills.add(new Bill("9月",data));
        bills.add(new Bill("10月",data));
        bills.add(new Bill("11月",data));
        bills.add(new Bill("12月",data));
        linearLayoutManager = new LinearLayoutManager(this);
        recycle_bill.setLayoutManager(linearLayoutManager);
        adapter = new AlipayBillAdapter(this, bills);
        recycle_bill.setAdapter(adapter);
        StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        recycle_bill.addItemDecoration(headersDecor);
    }
    private List<String> loadMoreData(int index){
        List<String> data = new ArrayList<>();
        for (int i = 10*index; i <(1+index)*10 ; i++) {
            data.add("超市购物"+i);
        }
        return data;
    }
}
