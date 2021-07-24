package dd.wan.myyoutube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

public class YoutubeVideo extends AppCompatActivity {

    YouTubePlayerView youtubeVideo;
    ImageView textView;
    YoutubeLayout yt ;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        youtubeVideo = findViewById(R.id.youtube_button);
        textView = findViewById(R.id.viewDesc);
        yt = findViewById(R.id.youtubeLayout);
        linearLayout = findViewById(R.id.viewHeader);



        Intent intent = getIntent();
        String videoID = intent.getStringExtra("videoID");
        youtubeVideo.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                youTubePlayer.loadVideo(videoID,0);
            }
        });
        youtubeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(YoutubeVideo.this,"Được bên trong youtube view nè !",Toast.LENGTH_LONG).show();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(YoutubeVideo.this,"Được này !",Toast.LENGTH_LONG).show();
            }
        });


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(YoutubeVideo.this,"Được mỗi bên youtube layout!",Toast.LENGTH_LONG).show();
            }
        });
    }
}