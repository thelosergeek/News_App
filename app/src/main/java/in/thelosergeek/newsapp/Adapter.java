package in.thelosergeek.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.thelosergeek.newsapp.Model.Articles;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    Context context;

    public Adapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }

    List<Articles> articles;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles a = articles.get(position);
        holder.tvTitle.setText(a.getTitle());
        holder.tvSource.setText(a.getSource().getName());
        holder.tvDate.setText("\u2022"+dateTime(a.getPublishedAt()));

        String imageUri = a.getUrlToImage();
        Picasso.get().load(imageUri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView tvTitle,tvSource,tvDate;
        ImageView imageView;
        CardView cardView;


        public ViewHolder(@NonNull View itemView){
            super((itemView));

            tvTitle = itemView.findViewById(R.id.tvid);
            tvSource = itemView.findViewById(R.id.tvsource);
            tvDate = itemView.findViewById(R.id.tvdate);
            imageView = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }

    public String dateTime(String t){
        PrettyTime prettyTime = new PrettyTime(new Locale(getCountry()));
        String time  = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:", Locale.ENGLISH);
            Date date = simpleDateFormat.parse(t);
            time= prettyTime.format(date);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return time;
    }
    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}
