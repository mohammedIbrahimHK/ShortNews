package shortnews.com.shortnews;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import shortnews.com.shortnews.model.Article;

public class ShortNewsActivity extends AppCompatActivity implements VolleyCallBack {

    private RecyclerView mRecyclerView;
    private String TAG = getClass().getSimpleName();
    ArticleService mBoundService;
    boolean mServiceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_news);

        mRecyclerView = (RecyclerView) findViewById(R.id.articles_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intentService = new Intent(this, ArticleService.class);
        bindService(intentService, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void onSuccessResponse(ArrayList<Article> articles) {
        NewsAdapter newsAdapter = new NewsAdapter(articles, getApplicationContext());
        mRecyclerView.setAdapter(newsAdapter);
        Log.i(TAG, "onSuccessResponse: " + articles);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // cast the IBinder and get MyService instance
            Log.i(TAG, "onServiceConnected: ");
            ArticleService.MyBinder myBinder = (ArticleService.MyBinder) service;
            mBoundService = myBinder.getService();
            mServiceBound = true;

            mBoundService.registerCallBack(ShortNewsActivity.this);
            mBoundService.getResponse();

        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };
}
