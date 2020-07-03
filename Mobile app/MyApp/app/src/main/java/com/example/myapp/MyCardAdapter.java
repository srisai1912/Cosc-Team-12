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

public  class MyCardAdapter extends RecyclerView.Adapter<MyCardAdapter.ViewHolder> {

    private List<carditem> carditems;
    private Context context;
    public MyCardAdapter(List<carditem> carditems,Context context)
    {
        this.carditems=carditems;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        carditem item=carditems.get(position);
        holder.roleid.setText(item.getRoleid());
        holder.rolename.setText(item.getRole());
       holder.prereq.setText(item.getPrereq());
       holder.deptname.setText(item.getDeptname());
    }

    @Override
    public int getItemCount() {
        return carditems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView roleid;
        public TextView rolename;
        public TextView prereq;
        public TextView deptname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roleid=(TextView) itemView.findViewById(R.id.roleid);
            rolename=(TextView) itemView.findViewById(R.id.role);
            prereq=(TextView) itemView.findViewById(R.id.pr);
            deptname=(TextView) itemView.findViewById(R.id.dn);

        }
    }
}


