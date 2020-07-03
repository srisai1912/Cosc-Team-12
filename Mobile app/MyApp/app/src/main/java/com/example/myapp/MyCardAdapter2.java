package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

public  class MyCardAdapter2 extends RecyclerView.Adapter<MyCardAdapter2.ViewHolder2> {

    private List<carditem2> carditems;
    private Context context;
    public MyCardAdapter2(List<carditem2> carditems,Context context)
    {
        this.carditems=carditems;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview2,parent,false);
        return new ViewHolder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        carditem2 item=carditems.get(position);
        holder.roleid.setText(item.getRoleid());
        holder.applicationid.setText(item.getAppid());
        holder.deptname.setText(item.getDeptname());
        holder.vacantpos.setText(item.getVacantpos());
        holder.status.setText(item.getStatus());
    }

    @Override
    public int getItemCount() {
        return carditems.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        public TextView roleid;
        public TextView applicationid;
        public TextView deptname;
        public TextView vacantpos;
        public TextView status;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            roleid=(TextView) itemView.findViewById(R.id.roleid);
            applicationid=(TextView) itemView.findViewById(R.id.appid);
            deptname=(TextView) itemView.findViewById(R.id.deptname);
            vacantpos=(TextView) itemView.findViewById(R.id.vacant);
            status=(TextView) itemView.findViewById(R.id.status);
        }
    }
}


