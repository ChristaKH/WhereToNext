package edu.miracostacollege.cs134.wheretonext;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class CollegeListDetails extends AppCompatActivity {

    private ImageView collegeImageView;
    private TextView collegeNameTextView;
    private RatingBar collegeRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_list_details);

        collegeImageView = findViewById(R.id.collegeImageView);
        collegeNameTextView = findViewById(R.id.collegeNameTextView);
        collegeRatingBar = findViewById(R.id.collegeRatingBar);

        Intent detailsIntent = getIntent();
        String name = detailsIntent.getStringExtra("Name");
        float rating = detailsIntent.getFloatExtra("Rating", 0.0f);
        String imageName = detailsIntent.getStringExtra("ImageName");

        AssetManager am = this.getAssets();
        try {
            InputStream stream = am.open(imageName);
            Drawable event = Drawable.createFromStream(stream, name);
            collegeImageView.setImageDrawable(event);
        }
        catch (IOException ex)
        {
            Log.e("Where To Next", "Error loading " + imageName, ex);
        }

        collegeNameTextView.setText(name);
        collegeRatingBar.setRating(rating);
    }
}
