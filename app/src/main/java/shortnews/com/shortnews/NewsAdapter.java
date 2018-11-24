package shortnews.com.shortnews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import shortnews.com.shortnews.model.Article;

/**
 * Created by mohammedibrahim on 24/11/18.
 */

class NewsAdapter extends RecyclerView.Adapter<ArticleViewHolder>{

    private ArrayList<Article> mArticle;
    private Context mContext;
    public NewsAdapter(ArrayList<Article> articles, Context context) {
        mArticle = articles;
        mContext = context;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_article_card, parent, false);

        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {

        String imageUrl = mArticle.get(position).getUrlToImage();
        if (imageUrl != null) {
            Glide.with(mContext).load(imageUrl).into(holder.imageView);
        }
        holder.title.setText(mArticle.get(position).getTitle());
        holder.description.setText(mArticle.get(position).getDescription());
        holder.author.setText("Author : "+mArticle.get(position).getAuthor());
        holder.publishTime.setText("Publish : "+mArticle.get(position).getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return mArticle.size();
    }
}

class ArticleViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView title;
    TextView description;
    TextView author;
    TextView publishTime;
    public ArticleViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.article_image);
        title = itemView.findViewById(R.id.article_title);
        description = itemView.findViewById(R.id.article_description);
        author = itemView.findViewById(R.id.article_author);
        publishTime = itemView.findViewById(R.id.article_publish_time);
    }

}
