package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private ImageView image;
    private TextView alsoKnownAs;
    private TextView placeOfOrigin;
    private TextView description;
    private TextView ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image = findViewById(R.id.image_iv);
        alsoKnownAs = findViewById(R.id.also_known_tv);
        placeOfOrigin = findViewById(R.id.origin_tv);
        description = findViewById(R.id.description_tv);
        ingredients = findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage().trim())
                .placeholder(R.mipmap.ic_launcher)
                .into(image);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        if (!sandwich.getAlsoKnownAs().isEmpty()) {
            String allAlsoKnownAs = TextUtils.join(", ", sandwich.getAlsoKnownAs() );
            alsoKnownAs.setText(allAlsoKnownAs);
        }
        if (!sandwich.getIngredients().isEmpty()) {
            String allIngredients = TextUtils.join(", ", sandwich.getIngredients() );
            ingredients.setText(allIngredients);
        }
        if (!sandwich.getPlaceOfOrigin().isEmpty()) {
            placeOfOrigin.setText(sandwich.getPlaceOfOrigin());
        }
        if (!sandwich.getDescription().isEmpty()) {
            description.setText(sandwich.getDescription());
        }
    }
}
