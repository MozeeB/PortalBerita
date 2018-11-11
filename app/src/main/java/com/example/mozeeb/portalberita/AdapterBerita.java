package com.example.mozeeb.portalberita;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mozeeb.portalberita.Response.BeritaItem;
import com.squareup.picasso.Picasso;

import java.util.List;

class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.MyViewHolder> {
    //Buat GLOBAL variabel untuk menampung context
    Context context;
    List<BeritaItem> berita;
    public AdapterBerita(Context context, List<BeritaItem> data_berita){
        //inisialisi
        this.context = context;
        this.berita = data_berita;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int ViewType) {
        //Layout inflater
        View view = LayoutInflater.from(context).inflate(R.layout.berita_item,parent,false);
        //hubungkan dengan myViewHolder
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position){
        //set widget
        holder.tvJudul.setText(berita.get(position).getJudulBerita());
        holder.tvTglTerbit.setText(berita.get(position).getTanggalPosting());
        holder.tvPenulis.setText(berita.get(position).getPenulis());
        //dapatkan url gambar
        final String urlGambarBerita = "" + berita.get(position).getFoto();
        //set image ke widget deengan picca library
        //karena image dari internet
        Picasso.with(context).load(urlGambarBerita).into(holder.ivGamvarBerita);
        //Event klik ketika item list nya di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //memulai Activity Details
                Intent varIntent = new Intent(context, DetailActivity.class);
                //sisipkan data ke intent
                varIntent.putExtra("JDL_BERITA", berita.get(position).getJudulBerita());
                varIntent.putExtra("TGL_BERITA", berita.get(position).getTanggalPosting());
                varIntent.putExtra("PNS_BERITA", berita.get(position).getPenulis());
                varIntent.putExtra("FTO_BERITA", urlGambarBerita);
                varIntent.putExtra("ISI_BERITA", berita.get(position).getIsiBerita());
                //method startActivity cuma bisa di pakai di Activity/Fragment
                //jadi harus masuk ke context dulu
                context.startActivity(varIntent);
            }
        });
    }
    //menentukan jumlah item yang tampil
    @Override
    public int getItemCount(){
        return berita.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //declarasi widget
        ImageView ivGamvarBerita;
        TextView tvJudul, tvTglTerbit, tvPenulis;
        public MyViewHolder(View itemView){
            super(itemView);
            //inisialisai widget
            ivGamvarBerita =(ImageView)itemView.findViewById(R.id.ivPosterBerita);
            tvJudul = (TextView)itemView.findViewById(R.id.tvJudulBerita);
            tvTglTerbit =(TextView)itemView.findViewById(R.id.tvTglTerbit);
            tvPenulis = (TextView)itemView.findViewById(R.id.tvPenulis);
        }
    }
}
