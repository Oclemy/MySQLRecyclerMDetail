package com.tutorials.hp.mysqlrecyclermdetail.m_UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tutorials.hp.mysqlrecyclermdetail.R;
import com.tutorials.hp.mysqlrecyclermdetail.m_DataObject.Spacecraft;
import com.tutorials.hp.mysqlrecyclermdetail.m_DetailActivity.DetailActivity;

import java.util.ArrayList;

/**
 * Created by Oclemy on 6/6/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Spacecraft> spacecrafts;

    public MyAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        final Spacecraft s=spacecrafts.get(position);

        holder.nameTxt.setText(s.getName());
        PicassoClient.downloadImage(c, s.getImageUrl(), holder.img);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
                openDetailActivity(s.getName(),s.getPropellant(),s.getDescription(),s.getImageUrl());
            }
        });

    }

    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }

    private void openDetailActivity(String name,String propellant,String desc,String imageUrl)
    {
        Intent i=new Intent(c, DetailActivity.class);

        //PACK DATA
        i.putExtra("NAME_KEY",name);
        i.putExtra("PROPELLANT_KEY",propellant);
        i.putExtra("DESCRIPTION_KEY",desc);
        i.putExtra("IMAGEURL_KEY",imageUrl);

        c.startActivity(i);
    }
}
