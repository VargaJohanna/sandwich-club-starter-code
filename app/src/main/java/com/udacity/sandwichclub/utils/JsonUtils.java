package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String NAME_KEY = "name";
    private static final String MAIN_NAME_KEY = "mainName";
    private static final String ALSO_KNOWN_AS_KEY = "alsoKnownAs";
    private static final String INGREDIENTS_KEY = "ingredients";
    private static final String PLACE_OF_ORIGIN_KEY = "placeOfOrigin";
    private static final String DESCRIPTION_KEY = "description";
    private static final String IMAGE_KEY = "image";
    private static final String FALLBACK_MESSAGE = "Not available";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            String mainName = obj.getJSONObject(NAME_KEY).getString(MAIN_NAME_KEY);
            JSONArray alsoKnownAsArray = obj.getJSONObject(NAME_KEY).getJSONArray(ALSO_KNOWN_AS_KEY);
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsArray.getString(i).trim());
            }
            JSONArray ingredientsArray = obj.getJSONArray(INGREDIENTS_KEY);
            List<String> ingredients = new ArrayList<>();

            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredients.add(ingredientsArray.getString(i).trim());
            }
            String placeOfOrigin = obj.optString(PLACE_OF_ORIGIN_KEY, FALLBACK_MESSAGE);
            String description = obj.optString(DESCRIPTION_KEY, FALLBACK_MESSAGE);
            String image = obj.optString(IMAGE_KEY, FALLBACK_MESSAGE);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
