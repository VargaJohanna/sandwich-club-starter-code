package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            String mainName = obj.getJSONObject("name").getString("mainName");
            JSONArray alsoKnownAsArray = obj.getJSONObject("name").getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsArray.getString(i).trim());
            }
            JSONArray ingredientsArray = obj.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>();

            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredients.add(ingredientsArray.getString(i).trim());
            }
            String placeOfOrigin = obj.getString("placeOfOrigin");
            String description = obj.getString("description");
            String image = obj.getString("image");

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
