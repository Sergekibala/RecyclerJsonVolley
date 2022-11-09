package com.example.recyclerjsonvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String creator = intent.getStringExtra(EXTRA_CREATOR);
        int likes = intent.getStringExtra(EXTRA_URL);


        ImageView imageView = findViewById(R.id.ivDetailImage);
        TextView tvCreator = findViewById(R.id.tvCreator);
        TextView tvlikes = findViewById(R.id.ivDetailImage);

        tvCreator.setText(creator);
        tvlikes.setText("Likes :" + likes);

        Glide.with(this,)



    }
}