package dd.wan.myyoutube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import dd.wan.myyoutube.Adapter.RecyclerAdapter;
import dd.wan.myyoutube.Interface.CallApi;
import dd.wan.myyoutube.Model.DataVideo;
import dd.wan.myyoutube.Model.Video;
import dd.wan.myyoutube.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    interface InterfaceCallback {
        public void getDataIntrface(DataVideo re);

    }
    interface getDataVideoIn{
        public DataVideo getListVideo();
    }
    RecyclerAdapter adapter;
    DataVideo dataVideo ;
    RecyclerView recyclerView;
    List<Video> list = new ArrayList<Video>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(1))
                {
                    clickCallApi(adapter.getDataVideo().getNextPageToken());
                }
            }
        });
        CallApi.callApi.getDataVideo("snippet","mostPopular","AIzaSyDox651-xo6JSTQeZGialodBMGCxToEGFc").enqueue(new Callback<DataVideo>() {
            @Override
            public void onResponse(Call<DataVideo> call, Response<DataVideo> response) {
                dataVideo = response.body();
                Toast.makeText(MainActivity.this,response.code()+" ",Toast.LENGTH_LONG).show();
                list = response.body().getItem();
                adapter = new RecyclerAdapter(MainActivity.this,list,dataVideo);
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