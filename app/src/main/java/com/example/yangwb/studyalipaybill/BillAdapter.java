package com.example.yangwb.studyalipaybill;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * @desc:
 * @author: yangwb
 * @date: 2018/10/11 11:18
 */

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.MyViewHolder> {
    private List<String> datas;
    private Context context;
    public BillAdapter(Context context,List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView bill_name;
        public MyViewHolder(View itemView) {
            super(itemView);
            bill_name = ((TextView) itemView.findViewById(R.id.bill_name));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_bill_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.bill_name.setText(datas.get(position));
        holder.bill_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, datas.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
