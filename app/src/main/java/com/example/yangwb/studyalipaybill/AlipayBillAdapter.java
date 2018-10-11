package com.example.yangwb.studyalipaybill;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @author: yangwb
 * @date: 2018/10/11 13:48
 */

public class AlipayBillAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Bill> bills;
    private List<String> billList = new ArrayList<>();
    public AlipayBillAdapter(Context context,List<Bill> bills) {
        this.context = context;
        this.bills=bills;
        getBillList();
    }
    private void getBillList(){
        for (int i = 0; i < bills.size(); i++) {
            billList.addAll(bills.get(i).getList());
        }
    }
    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView bill_type;//分类名

        public HeaderViewHolder(View itemView) {
            super(itemView);
            bill_type = (TextView) itemView.findViewById(R.id.bill_type);
        }
    }
    @Override
    public long getHeaderId(int position) {
        return getSortType(position);
    }
    //获取当前饭菜的类型
    public int getSortType(int position) {
        int sort = -1;
        int sum = 0;
        for (int i = 0; i< bills.size(); i++){
            if(position>=sum){
                sort++;
            }else {
                return sort;
            }
            sum += bills.get(i).getList().size();
        }
        return sort;
    }
    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_bill_head_item, parent,false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        HeaderViewHolder headerViewHolder = ((HeaderViewHolder) holder);
        headerViewHolder.bill_type.setText(bills.get(getSortType(position)).getType());
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {
        TextView bill_name;//分类名

        public ListViewHolder(View itemView) {
            super(itemView);
            bill_name = (TextView) itemView.findViewById(R.id.bill_name);
        }
    }
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_bill_item, parent,false);
        return new ListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ListViewHolder listViewHolder = ((ListViewHolder) holder);
        listViewHolder.bill_name.setText(billList.get(position));
        listViewHolder.bill_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, billList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return billList.size();
    }
}
