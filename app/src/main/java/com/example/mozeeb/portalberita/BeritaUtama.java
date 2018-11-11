package com.example.mozeeb.portalberita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.example.mozeeb.portalberita.Network.ApiServices;
import com.example.mozeeb.portalberita.Network.InitRetrofit;
import com.example.mozeeb.portalberita.Response.BeritaItem;
import com.example.mozeeb.portalberita.Response.ResponseBerita;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class BeritaUtama extends AppCompatActivity {
    // Deklarasi Widget
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// Inisialisasi Widget
        recyclerView = (RecyclerView) findViewById(R.id.rvListBerita);
// RecyclerView harus pakai Layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
// Eksekusi method
        tampilBerita();
    }
    private void tampilBerita() {
        ApiServices api = InitRetrofit.getInstance();
// Siapkan request
        Call<ResponseBerita> beritaCall = api.request_show_all_berita();
// Kirim request
        beritaCall.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
// Pasikan response Sukses
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
// tampung data response body ke variable
                    List<BeritaItem> data_berita = response.body().getBerita();
                    boolean status = response.body().isStatus();
// Kalau response status nya = true
                    if (status){
// Buat Adapter untuk recycler view
                        AdapterBerita adapter = new AdapterBerita(BeritaUtama.this, data_berita);
                        recyclerView.setAdapter(adapter);
                    } else {
// kalau tidak true
                        Toast.makeText(BeritaUtama.this, "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
// print ke log jika Error
                t.printStackTrace();
            }
        });
    }
}