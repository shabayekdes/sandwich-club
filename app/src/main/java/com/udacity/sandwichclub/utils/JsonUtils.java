package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static final String NAME = "name";
    public static final String MAIN_NAME = "mainName";
    public static final String ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        JSONObject sandwichJsonObject;
        String mainName = null;
        JSONArray alsoKnownAsJsonArray;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        JSONArray ingredientsJsonArray;
        List<String> alsoKnownAs = new ArrayList<>();
        List<String> ingredients = new ArrayList<>();
        try {
            sandwichJsonObject = new JSONObject(json);
            JSONObject nameJsonObject = null;
            if (sandwichJsonObject.has(NAME)) {
                nameJsonObject = sandwichJsonObject.optJSONObject(NAME);
            }
            if (nameJsonObject.has(MAIN_NAME)) {
                mainName = nameJsonObject.optString(MAIN_NAME);
            }

            if (nameJsonObject.has(ALSO_KNOWN_AS)) {
                alsoKnownAsJsonArray = nameJsonObject.optJSONArray(ALSO_KNOWN_AS);

                for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                    String alsoKnownAsString = alsoKnownAsJsonArray.optString(i);
                    alsoKnownAs.add(alsoKnownAsString);
                }
            }
            if (sandwichJsonObject.has(PLACE_OF_ORIGIN)) {
                placeOfOrigin = sandwichJsonObject.optString(PLACE_OF_ORIGIN);
            }
            if (sandwichJsonObject.has(DESCRIPTION)) {
                description = sandwichJsonObject.optString(DESCRIPTION);
            }
            if (sandwichJsonObject.has(IMAGE)) {
                image = sandwichJsonObject.optString(IMAGE);
            }
            if (sandwichJsonObject.has(INGREDIENTS)) {
                ingredientsJsonArray = sandwichJsonObject.optJSONArray(INGREDIENTS);
                for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                    String ingredientsString = ingredientsJsonArray.getString(i);
                    ingredients.add(ingredientsString);
                }
            }

            sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
