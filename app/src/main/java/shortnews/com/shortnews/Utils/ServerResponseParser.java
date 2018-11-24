package shortnews.com.shortnews.Utils;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import shortnews.com.shortnews.model.Article;

/**
 * Created by mohammedibrahim on 24/11/18.
 */

public class ServerResponseParser {

    public static ArrayList<Article> parseJsonResponse(JSONObject jsonObject) {
        ArrayList<Article> articles = new ArrayList<>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("articles");
            Log.i("ibbu", "parseJsonResponse: "+jsonArray);
            Gson gson = new Gson();
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonArticle =jsonArray.getJSONObject(i);
                Log.i("ibbu", "jsonArticle: "+jsonArticle);
                Article article = gson.fromJson(jsonArticle.toString(), Article.class);
                articles.add(article);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return articles;
    }
}
