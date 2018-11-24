package shortnews.com.shortnews;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

import shortnews.com.shortnews.Utils.Constants;
import shortnews.com.shortnews.model.Article;

import static shortnews.com.shortnews.Utils.ServerResponseParser.parseJsonResponse;

public class ArticleService extends Service {
    private IBinder mBinder = new MyBinder();
    private RequestQueue mRequestQueue;
    private String TAG = getClass().getSimpleName();
    private VolleyCallBack mCallback;


    @Override
    public void onCreate() {
        super.onCreate();
        mRequestQueue = Volley.newRequestQueue(this);
        Log.d(TAG, "in onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "in onBind");
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "in onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "in onUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "in onDestroy");
    }


    void registerCallBack(VolleyCallBack callback) {

        mCallback = callback;
    }

    void getResponse() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.i(TAG, "onResponse: " + response);
                parseResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(jsonObjectRequest);
    }

    private void parseResponse(JSONObject response) {
        ArrayList<Article> articles = parseJsonResponse(response);
        mCallback.onSuccessResponse(articles);

    }

    public class MyBinder extends Binder {
        ArticleService getService() {
            return ArticleService.this;
        }
    }
}