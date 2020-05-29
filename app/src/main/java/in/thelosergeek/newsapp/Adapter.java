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

import java.util.List;

import in.thelosergeek.newsapp.Model.Artciles;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    Context context;

    public Adapter(Context context, List<Artciles> artciles) {
        this.context = context;
        this.artciles = artciles;
    }

    List<Artciles> artciles;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Artciles a = artciles.get(position);
        holder.tvTitle.setText(a.getTitle());
        holder.tvSource.setText(a.getSource().getName());
        holder.tvDate.setText(a.getPublishedAt());

        String imageUri = a.getUrlToImage();
        Picasso.get().load(imageUri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return artciles.size();
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
}
