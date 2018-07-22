package com.summer.bingyan.gitpopular.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.summer.bingyan.gitpopular.activities.ContentActivity;
import com.summer.bingyan.gitpopular.data.Trend;
import com.summer.bingyan.gitpopular.R;
import com.summer.bingyan.gitpopular.utils.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.ViewHolder>{
    private Context context;
    private List<Trend> trendList;
    private RecyclerView recyclerView;
    private MyDatabaseHelper myDatabaseHelper;
    public View.OnClickListener mListener = new myClickListener();
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView content;
        private ImageView star;
        private ImageView imageView;
        public ViewHolder(View itemView){
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.trending_title);
            content=(TextView)itemView.findViewById(R.id.trending_content);
            star=(ImageView)itemView.findViewById(R.id.star);
            imageView=(ImageView)itemView.findViewById(R.id.selected_color);
        }
    }
    public TrendingAdapter(Context context,RecyclerView recyclerView,List<Trend> trendList)
    {
        this.context=context;
        this.recyclerView=recyclerView;
        this.trendList=trendList;
        myDatabaseHelper=new MyDatabaseHelper(context,"Git.db",null,2);
        myDatabaseHelper.getWritableDatabase();
    }
    public TrendingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        Context context1=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context1);
        View trendingView=layoutInflater.inflate(R.layout.item_trending,parent,false);
        ViewHolder viewHolder=new ViewHolder(trendingView);
        trendingView.setOnClickListener(mListener);
        return viewHolder;
    }
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        if(!trendList.isEmpty())
        {
            final Trend trend=trendList.get(position);
            holder.title.setText(trend.getTitle());
            holder.content.setText(trend.getContent());
            if (trend.isFavorite())
            {
               holder.star.setVisibility(View.INVISIBLE);
                holder.imageView.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.star.setVisibility(View.VISIBLE);
                holder.imageView.setVisibility(View.INVISIBLE);
            }
            holder.star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db=myDatabaseHelper.getWritableDatabase();
                    if (!trend.isFavorite())
                    {holder.star.setVisibility(View.INVISIBLE);
                        holder.imageView.setVisibility(View.VISIBLE);
                    ContentValues values=new ContentValues();
                    values.put("title",trend.getTitle());
                    values.put("content",trend.getContent());
                    trend.setFavorite(true);
                    db.insert("Trend",null,values);
                    values.clear();}
                }
            });
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db=myDatabaseHelper.getWritableDatabase();
                    holder.star.setVisibility(View.VISIBLE);
                    holder.imageView.setVisibility(View.INVISIBLE);
                        trend.setFavorite(false);
                        db.delete("Trend","title=?",new String[]{trend.getTitle()});
                }
            });
        }
    }
    public void trendListChanged(List<Trend> trendList)
    {
        this.trendList=trendList;
    }
    class myClickListener implements View.OnClickListener
    {
        public void onClick(View v){
            int position=recyclerView.getChildAdapterPosition(v);
            Trend trend=trendList.get(position);
            String link=trend.getUrl();
            Intent intent=new Intent(context,ContentActivity.class);
            intent.putExtra("Link",link);
            context.startActivity(intent);
    }
    }
    public int getItemCount()
    {
        return trendList.size();
    }
}
//改的时候要改，onbindviewhloder,viewholder下的private,viewholder下的寻找id
