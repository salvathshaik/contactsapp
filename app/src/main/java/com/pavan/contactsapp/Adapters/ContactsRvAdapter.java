package com.pavan.contactsapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pavan.contactsapp.R;
import com.pavan.contactsapp.models.ModelContacts;

import java.util.List;

public class ContactsRvAdapter extends RecyclerView.Adapter<ContactsRvAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<ModelContacts> mListContacts;
    private Context mContext;

    public ContactsRvAdapter(Context context, List<ModelContacts> listContacts) {
        mListContacts=listContacts;
        mContext=context;

    }

    @Override
    public ContactsRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        inflater=LayoutInflater.from(mContext);
        View view=inflater.inflate(R.layout.item_contacts,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactsRvAdapter.ViewHolder holder, int position) {
        TextView contact_name,contact_number;
        contact_name=holder.contact_name;
        contact_number=holder.contact_nuumber;
        contact_name.setText(mListContacts.get(position).getName());
        contact_number.setText(mListContacts.get(position).getNumber());


    }

    @Override
    public int getItemCount() {
        return mListContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView contact_name,contact_nuumber;

        public ViewHolder(View itemView) {
            super(itemView);
            contact_name=itemView.findViewById(R.id.contact_name);
            contact_nuumber=itemView.findViewById(R.id.contact_number);


        }
    }
}
