package in.thelosergeek.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FullStory extends AppCompatActivity {

   TextView tvTitle,tvSource,tvTime,tvFull;
   WebView webView;
   ImageView imageView;
   ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_story);

        tvTitle = findViewById(R.id.tvid);
        tvSource = findViewById(R.id.tvsource);
        tvTime = findViewById(R.id.tvdate);
        tvFull = findViewById(R.id.tvdes);
        webView = findViewById(R.id.webview);
        imageView = findViewById(R.id.image);
        progressBar = findViewById(R.id.webviewloader);
        progressBar.setVisibility(View.VISIBLE);


        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String source = intent.getStringExtra("source");
        String time = intent.getStringExtra("time");
        String desc = intent.getStringExtra("desc");
        String imageUri = intent.getStringExtra("imageUri");
        String url = intent.getStringExtra("url");

        tvTitle.setText(title);
        tvSource.setText(source);
        tvTime.setText(time);
        tvFull.setText(desc);

        Picasso.get().load(imageUri).into(imageView);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        if (webView.isShown()){
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}