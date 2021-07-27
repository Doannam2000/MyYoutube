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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import dd.wan.myyoutube.Adapter.RecyclerAdapter;
import dd.wan.myyoutube.Adapter.RelatedVideosAdapter;
import dd.wan.myyoutube.Interface.CallApi;
import dd.wan.myyoutube.Model.DataVideo;
import dd.wan.myyoutube.Model.Video;
import dd.wan.myyoutube.Model.VideoSearch;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final String YOUTUBE_API_KEY = "AIzaSyAsJkTHV6KzBvN_V4B7GE-KKBpUxkUL_9o";
    RecyclerAdapter adapter;
    RelatedVideosAdapter relatedVideosAdapter;
    DataVideo dataVideo ;
    RecyclerView recyclerView,relatedVideos;
    List<Video> list = new ArrayList<Video>();
    YouTubePlayerView youtubeVideo;
    YoutubeLayout yt ;
    BottomNavigationView bottomNavigationView;
    String VideoID;
    TextView title,nameChanel;
    CircleImageView imageChanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        AnhXa();

        getLifecycle().addObserver(youtubeVideo);
        IFramePlayerOptions iFramePlayerOptions = new IFramePlayerOptions.Builder()
                .controls(0)
                .rel(0)
                .ivLoadPolicy(1)
                .ccLoadPolicy(1)
                .build();


        getLifecycle().addObserver(youtubeVideo);
        youtubeVideo.initialize(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                if(VideoID != null )
                {
                    YouTubePlayerUtils.loadOrCueVideo(
                            youTubePlayer, getLifecycle(),
                            VideoID,0f
                    );
                }

                initRecyclerView(youTubePlayer);

            }
        }, true, iFramePlayerOptions);

    }


    // initialization recyclerView
    private void initRecyclerView(YouTubePlayer youTubePlayer)
    {
        RecyclerAdapter.OnItemClickListener listener = new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Video item) {
                if(yt.getVisibility() == View.GONE)
                {
                    yt.setVisibility(View.VISIBLE);
                    bottomNavigationView.setVisibility(View.GONE);
                }
                VideoID = item.getVideoID();
                initRecyclerViewRelatedVideos(youTubePlayer,VideoID);
                YouTubePlayerUtils.loadOrCueVideo(
                        youTubePlayer, getLifecycle(),
                        VideoID,0f
                );
                title.setText(item.getSnippet().getTitle());
                Glide.with(MainActivity.this).load(item.getSnippet().getImg().getUrl().getUrl()).into(imageChanel);
                nameChanel.setText(item.getSnippet().getChannelTitle());
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
        CallApi.callApi.getDataVideo("snippet","mostPopular",YOUTUBE_API_KEY).enqueue(new Callback<DataVideo>() {
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

    // reload youtube api
    private void clickCallApi(String pageToken)
    {
        CallApi.callApi.getDataVideoPage("snippet","mostPopular",pageToken,YOUTUBE_API_KEY).enqueue(new Callback<DataVideo>() {
            @Override
            public void onResponse(Call<DataVideo> call, Response<DataVideo> response) {
                adapter.AddList(response.body(),response.body().getItem());
            }
            @Override
            public void onFailure(Call<DataVideo> call, Throwable t) {
            }
        });
    }


    private void initRecyclerViewRelatedVideos(YouTubePlayer youTubePlayer,String firstID)
    {
        // Tạo interface listener xử lý onclick trên recyclerView RelatedVideos

        RelatedVideosAdapter.OnItemClickListener listener = new RelatedVideosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(VideoSearch.Videosearch item) {
                String Relateds_Video_ID = item.getvideoId().getVideoID();
                YouTubePlayerUtils.loadOrCueVideo(
                        youTubePlayer, getLifecycle(),
                        Relateds_Video_ID,0f);
                relatedVideosAdapter.RemoveList();
                VideoSearchAPI("","",Relateds_Video_ID);
                title.setText(item.getSnippet().getTitle());
                Glide.with(MainActivity.this).load(item.getSnippet().getImg().getUrl().getUrl()).into(imageChanel);
                nameChanel.setText(item.getSnippet().getChannelTitle());
            }
        };
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        relatedVideos.setHasFixedSize(true);
        relatedVideos.setLayoutManager(linearLayoutManager);
        relatedVideos.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
        relatedVideos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(2))
                {
                    VideoSearchAPI(relatedVideosAdapter.getVideoSearch().getNextPageToken(),"",relatedVideosAdapter.getRelated_Video_ID());
                }
            }
        });
        CallApi.callApi.getVideoSearch("snippet","","",firstID,"video",YOUTUBE_API_KEY).enqueue(new Callback<VideoSearch>() {
            @Override
            public void onResponse(Call<VideoSearch> call, Response<VideoSearch> response) {
               try {
                   VideoSearch data = response.body();
                   List<VideoSearch.Videosearch> list1= response.body().getItem();
                   relatedVideosAdapter = new RelatedVideosAdapter(list1,data,MainActivity.this,listener,firstID);
                   relatedVideos.setAdapter(relatedVideosAdapter);
               }
              catch (Exception e)
              {
                  Toast.makeText(MainActivity.this,"Lượt search trên api đã hết quay lại sau =.=",Toast.LENGTH_LONG).show();
              }
            }
            @Override
            public void onFailure(Call<VideoSearch> call, Throwable t) {
            }
        });
    }

    // SearchVideo
    private void VideoSearchAPI(String pageToken,String q,String videoID)
    {
        CallApi.callApi.getVideoSearch("snippet",pageToken,q,videoID,"video",YOUTUBE_API_KEY).enqueue(new Callback<VideoSearch>() {
            @Override
            public void onResponse(Call<VideoSearch> call, Response<VideoSearch> response) {
                relatedVideosAdapter.AddList(response.body(),videoID);
            }
            @Override
            public void onFailure(Call<VideoSearch> call, Throwable t) {
            }
        });
    }

    private void AnhXa()
    {

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        relatedVideos = findViewById(R.id.relatedVideo);
        recyclerView = findViewById(R.id.recycler_view);
        youtubeVideo = findViewById(R.id.viewHeader);
        yt = findViewById(R.id.youtubeLayout);
        imageChanel = findViewById(R.id.imageChanel1);
        title = findViewById(R.id.title);
        nameChanel = findViewById(R.id.nameChanel1);
    }
}