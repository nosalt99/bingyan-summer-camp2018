package com.summer.bingyan.gitpopular.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.summer.bingyan.gitpopular.activities.ContentActivity;
import com.summer.bingyan.gitpopular.data.Trend;
import com.summer.bingyan.gitpopular.R;

import java.util.ArrayList;
import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.ViewHolder>{
    private Context context;
    private List<Trend> trendList=new ArrayList<>();
    private RecyclerView recyclerView;
    public View.OnClickListener mListener = new myClickListener();
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView content;
        public ViewHolder(View itemView){
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.trending_title);
            content=(TextView)itemView.findViewById(R.id.trending_content);
        }
    }
    public TrendingAdapter(Context context,RecyclerView recyclerView,List<Trend> trendList)
    {
        this.context=context;
        this.recyclerView=recyclerView;
        this.trendList=trendList;
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
    public void onBindViewHolder(ViewHolder holder,int position)
    {
        if(!trendList.isEmpty())
        {
            Trend trend=trendList.get(position);
            holder.title.setText(trend.getTitle());
            holder.content.setText(trend.getContent());
        }
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
