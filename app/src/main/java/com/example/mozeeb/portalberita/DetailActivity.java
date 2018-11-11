package com.example.mozeeb.portalberita;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    //declarasi
    ImageView ivGambarBerita;
    TextView tvTglTerbit, tvPenulis;
    WebView wvKontenBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //inisialisai
        ivGambarBerita = (ImageView)findViewById(R.id.ivGambarBerita);
        tvTglTerbit = (TextView)findViewById(R.id.tvTglTerbit);
        tvPenulis = (TextView)findViewById(R.id.tvPenulis);
        wvKontenBerita = (WebView)findViewById(R.id.wvKontenBerita);
        //jalankan method tampil berita
        tampilDetailBerita();

    }
    private void tampilDetailBerita(){
        //tangkap data dari intent
        String judulBerita = getIntent().getStringExtra("JDL_BERITA");
        String tanggalBerita = getIntent().getStringExtra("TGL_BERITA");
        String penulisBerita = getIntent().getStringExtra("PNS_BERITA");
        String isiBerita = getIntent().getStringExtra("ISI_BERITA");
        String fotoBerita = getIntent().getStringExtra("FTO_BERITA");
        //set judul actionbar / toolbar
        getSupportActionBar().setTitle(judulBerita);
        //set ke widget
        tvPenulis.setText("Oleh :" + penulisBerita);
        tvTglTerbit.setText(tanggalBerita);
        //untuk gamvar berita
        Picasso.with(this).load(fotoBerita).into(ivGambarBerita);
        // Set isi berita sebagai html ke WebView
        wvKontenBerita.getSettings().setJavaScriptEnabled(true);
        wvKontenBerita.loadData(isiBerita, "text/html; charset=utf-8", "UTF-8");

    }

}
