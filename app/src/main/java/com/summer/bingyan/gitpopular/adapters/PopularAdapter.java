package com.summer.bingyan.gitpopular.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.summer.bingyan.gitpopular.R;

import com.summer.bingyan.gitpopular.activities.ContentActivity;
import com.summer.bingyan.gitpopular.data.Popular;
import com.summer.bingyan.gitpopular.utils.MyDatabaseHelper;

import java.util.List;


public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    private Context context;
    private List<Popular>populars;
    private RecyclerView recyclerView;
    private MyDatabaseHelper myDatabaseHelper;
    public View.OnClickListener mlisetener=new myClickListener();
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView full_name;
        private TextView description;
        private ImageView star;
        private ImageView imageView;
        public ViewHolder(View itemView){
             super(itemView);
             full_name=(TextView)itemView.findViewById(R.id.popular_title);
             description=(TextView)itemView.findViewById(R.id.popular_content);
             star=(ImageView)itemView.findViewById(R.id.star);
             imageView=(ImageView)itemView.findViewById(R.id.selected_color);
        }
    }
    public PopularAdapter(Context context,RecyclerView recyclerView,List<Popular> populars)
    {
        this.context=context;
        this.recyclerView=recyclerView;
        this.populars=populars;
        myDatabaseHelper=new MyDatabaseHelper(context,"Git.db",null,2);
        myDatabaseHelper.getWritableDatabase();
    }
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        Context context1=parent.getContext();
        LayoutInflater layoutInflater=LayoutInflater.from(context1);
        View popularView=layoutInflater.inflate(R.layout.item_popular,parent,false);
        ViewHolder viewHolder=new ViewHolder(popularView);
        popularView.setOnClickListener(mlisetener);
        return viewHolder;
    }
    public void onBindViewHolder(final ViewHolder viewHolder, int position)
    {
        if (!populars.isEmpty())
        {
            final Popular popular=populars.get(position);
            viewHolder.description.setText(popular.getDescription());
            viewHolder.full_name.setText(popular.getFull_name());

            if(popular.isFavorite())
            {
               viewHolder.star.setVisibility(View.INVISIBLE);
               viewHolder.imageView.setVisibility(View.VISIBLE);

            }
            else {
                viewHolder.star.setVisibility(View.VISIBLE);
                viewHolder.imageView.setVisibility(View.INVISIBLE);
            }
            viewHolder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=myDatabaseHelper.getWritableDatabase();
                if (!popular.isFavorite())
                { viewHolder.star.setVisibility(View.INVISIBLE);
                    viewHolder.imageView.setVisibility(View.VISIBLE);
                    ContentValues values=new ContentValues();
                    values.put("title",popular.getFull_name());
                    values.put("content",popular.getDescription());
                    popular.setFavorite(true);
                    db.insert("Popular",null,values);
                    values.clear();
                }
            }
        });
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db=myDatabaseHelper.getWritableDatabase();

                        viewHolder.star.setVisibility(View.VISIBLE);
                        viewHolder.imageView.setVisibility(View.INVISIBLE);
                        db.delete("Popular","title=?",new String[]{popular.getFull_name()});
                        popular.setFavorite(false);
                    }
            });
                }
        }
    public void popularListChanged(List<Popular>populars)
    {
        this.populars=populars;
    }
    class myClickListener implements View.OnClickListener
    {
        public void onClick(View view)
        {
            int position=recyclerView.getChildAdapterPosition(view);
            Popular popular=populars.get(position);
            String link=popular.getHtml_url();
            Intent intent=new Intent(context, ContentActivity.class);
            intent.putExtra("Link",link);
            context.startActivity(intent);
        }
    }

    @Override
    public int getItemCount() {
        return populars.size();
    }
}
