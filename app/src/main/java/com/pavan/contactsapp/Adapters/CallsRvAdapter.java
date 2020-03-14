package com.pavan.contactsapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pavan.contactsapp.R;
import com.pavan.contactsapp.models.ModelCalls;

import java.util.List;

public class CallsRvAdapter extends  RecyclerView.Adapter<CallsRvAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context mContext;
    private List<ModelCalls> mlistCalls;

    public CallsRvAdapter(Context context,List<ModelCalls> listCalls){
        mContext=context;
        mlistCalls=listCalls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        layoutInflater=   LayoutInflater.from(mContext);


        View view=layoutInflater.inflate(R.layout.item_calls,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView name,duration,date;

        name=holder.name;
        duration=holder.duration;
        date=holder.date;


        if(mlistCalls.get(position).getDuration().equals("-")){
            name.setText(mlistCalls.get(position).getNumber());
            duration.setText(mlistCalls.get(position).getDuration());
            date.setText(mlistCalls.get(position).getDate());
        }else {
            int dur= Integer.parseInt(mlistCalls.get(position).getDuration());
            name.setText(mlistCalls.get(position).getNumber());
            duration.setText(""+dur/60+":mins");
            date.setText(mlistCalls.get(position).getDate());
        }



    }

    @Override
    public int getItemCount() {
        return mlistCalls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,duration,date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.contact_name);
            duration=itemView.findViewById(R.id.call_duration);
            date=itemView.findViewById(R.id.call_date);

        }
    }
}
