package com.zettig.a65apps.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zettig.a65apps.R;
import com.zettig.a65apps.model.data.Specialty;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;


public class SpecialtyRecyclerViewAdapter extends RecyclerView.Adapter<SpecialtyRecyclerViewAdapter.SpecialtyViewHolder> {
    protected List<Specialty> list = new ArrayList<>();

    public class SpecialtyViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.specialty_id) TextView specialtyId_tv;
        @Bind(R.id.specialty_name) TextView specialtyName_tv;
        public SpecialtyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public SpecialtyRecyclerViewAdapter.SpecialtyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.specialty_list_item,parent,false);
        SpecialtyViewHolder viewHolder = new SpecialtyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SpecialtyViewHolder holder, int position) {
        holder.specialtyId_tv.setText(list.get(position).getSpecialtyId().toString());
        holder.specialtyName_tv.setText(list.get(position).getName().toString());
    }


    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public void showList(List<Specialty> list){
        this.list = list;
        notifyDataSetChanged();
    }

}
