package dd.wan.myyoutube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import dd.wan.myyoutube.Adapter.RecyclerAdapter;
import dd.wan.myyoutube.Interface.CallApi;
import dd.wan.myyoutube.Model.DataVideo;
import dd.wan.myyoutube.Model.Video;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    RecyclerAdapter adapter;
    DataVideo dataVideo ;
    RecyclerView recyclerView;
    List<Video> list = new ArrayList<Video>();
    YouTubePlayerView youtubeVideo;
    ImageView textView;
    YoutubeLayout yt ;
    BottomNavigationView bottomNavigationView;
    int i  = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);




        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        youtubeVideo = findViewById(R.id.viewHeader);
        textView = findViewById(R.id.viewDesc);
        yt = findViewById(R.id.youtubeLayout);
        recyclerView = findViewById(R.id.recycler_view);
        getLifecycle().addObserver(youtubeVideo);


        RecyclerAdapter.OnItemClickListener listener = new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Video item) {
                yt.setVisibility(View.VISIBLE);
                youtubeVideo.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                        super.onReady(youTubePlayer);
                        youTubePlayer.loadVideo(item.getVideoID(),0);
                    }
                });
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(2))
                {
                    clickCallApi(adapter.getDataVideo().getNextPageToken());
                }
            }
        });
        CallApi.callApi.getDataVideo("snippet","mostPopular","AIzaSyDox651-xo6JSTQeZGialodBMGCxToEGFc").enqueue(new Callback<DataVideo>() {
            @Override
            public void onResponse(Call<DataVideo> call, Response<DataVideo> response) {
                dataVideo = response.body();
                list = response.body().getItem();
                adapter = new RecyclerAdapter(MainActivity.this,list,dataVideo,listener);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<DataVideo> call, Throwable t) {
            }
        });
    }

    private void clickCallApi(String pageToken)
    {
        CallApi.callApi.getDataVideoPage("snippet","mostPopular",pageToken,"AIzaSyDox651-xo6JSTQeZGialodBMGCxToEGFc").enqueue(new Callback<DataVideo>() {
            @Override
            public void onResponse(Call<DataVideo> call, Response<DataVideo> response) {
                adapter.AddList(response.body(),response.body().getItem());
            }
            @Override
            public void onFailure(Call<DataVideo> call, Throwable t) {
            }
        });
    }

}