package com.pavan.contactsapp.Adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pavan.contactsapp.R;
import com.pavan.contactsapp.models.ModelContacts;

import java.util.List;

public class FvtContactsRvAdapter extends RecyclerView.Adapter<FvtContactsRvAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<ModelContacts> mListContacts;
    private Context mContext;

    public FvtContactsRvAdapter(Context context, List<ModelContacts> listContacts) {
        mListContacts = listContacts;
        mContext = context;

    }

    @Override
    public FvtContactsRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_fav, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FvtContactsRvAdapter.ViewHolder holder, int position) {
        TextView contact_name, contact_number;
        contact_name = holder.contact_name;
        contact_number = holder.contact_nuumber;
        contact_name.setText(mListContacts.get(position).getName());

        final int temp=position;

        contact_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("came","onlineck");
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(mListContacts.get(temp).getNumber()));
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
               // mContext.startActivity(intent);

            }
        });

        //contact_number.setText(mListContacts.get(position).getNumber());


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
            contact_nuumber=itemView.findViewById(R.id.fav_number);


        }
    }
}
