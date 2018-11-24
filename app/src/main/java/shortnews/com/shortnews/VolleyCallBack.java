package shortnews.com.shortnews;

import java.util.ArrayList;

import shortnews.com.shortnews.model.Article;

/**
 * Created by mohammedibrahim on 24/11/18.
 */

public interface VolleyCallBack {
    void onSuccessResponse(ArrayList<Article> articles);
}
