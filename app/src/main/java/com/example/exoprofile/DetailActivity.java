package com.example.exoprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class DetailActivity extends AppCompatActivity {
    private ImageView imgPhoto;
    private TextView tvNama, tvDesc;
    private Button btnShare;

    public static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imgPhoto = findViewById(R.id.iv_item_photo);
        tvNama = findViewById(R.id.tv_item_name);
        tvDesc = findViewById(R.id.tv_item_description);
        btnShare = findViewById(R.id.btnShare);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                String bodytext = "Baca Selengkapnya Di:https://www.gramedia.com/best-seller/member-exo/";
                share.putExtra(Intent.EXTRA_SUBJECT, "");
                share.putExtra(Intent.EXTRA_TEXT, bodytext);
                share = Intent.createChooser(share, " Share Via :");
                startActivity(share);
            }
        });

        EXO exo = getIntent().getParcelableExtra(ITEM_EXTRA);
        if (exo != null){
            Glide.with(this)
                    .load(exo.getPhoto())
                    .into(imgPhoto);
            tvNama.setText(exo.getName());
            tvDesc.setText(exo.getDescription());
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail Profile Member EXO");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}