package com.zettig.a65apps.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.zettig.a65apps.R;
import com.zettig.a65apps.model.data.Person;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

public class PersonRecyclerViewAdapter extends RecyclerView.Adapter<PersonRecyclerViewAdapter.PersonViewHolder> {
    List<Person> list = new ArrayList<>();
    Context context;
    public class PersonViewHolder extends RecyclerView.ViewHolder{
        //@Bind(R.id.person_cv) CardView cv;
        @Bind(R.id.person_photo) ImageView avatr;
        @Bind(R.id.person_fName) TextView person_fName;
        @Bind(R.id.person_lName) TextView person_lName;
        @Bind(R.id.person_age) TextView person_age;
        public PersonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    @Override
    public PersonRecyclerViewAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_item,parent,false);
        PersonViewHolder viewHolder = new PersonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.context = recyclerView.getContext();
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
            holder.person_fName.setText(list.get(position).getFName().toString());
            holder.person_lName.setText(list.get(position).getLName().toString());
        int age = list.get(position).getAge();
        if (age!=0) {
            holder.person_age.setText(String.valueOf(age));
        } else {
            holder.person_age.setText("-");
        }
        String url = list.get(position).getAvatrUrl();
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context)
                    .load(url)
                    .placeholder(R.drawable.avatar_error)
                    .error(R.drawable.avatar_error)
                    .into(holder.avatr);
        } else {
            holder.avatr.setImageResource(R.drawable.avatar_error);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void showList(List<Person> list){
        this.list = list;
        notifyDataSetChanged();
    }
}
