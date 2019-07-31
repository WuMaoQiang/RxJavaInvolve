package com.example.rxjavainvolve;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * description ： TODO:
 * author : wilfried
 * email :
 * date : 2019/7/31 11:39
 */

public class GeneralAdapter extends RecyclerView.Adapter<GeneralAdapter.MyViewHolder> {
    //当前上下文对象
    Context context;
    //RecyclerView填充Item数据的List对象
    List<String> datas;

    public GeneralAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    //创建ViewHolder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View v = View.inflate(context, R.layout.item_recycler, null);
        //返回MyViewHolder的对象
        return new MyViewHolder(v);
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(datas.get(position));
    }

    //返回Item的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //继承RecyclerView.ViewHolder抽象类的自定义ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }
}