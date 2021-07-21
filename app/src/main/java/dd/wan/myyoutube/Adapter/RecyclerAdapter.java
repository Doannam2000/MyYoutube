package dd.wan.myyoutube.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dd.wan.myyoutube.MainActivity;
import dd.wan.myyoutube.Model.DataVideo;
import dd.wan.myyoutube.Model.Video;
import dd.wan.myyoutube.R;
import dd.wan.myyoutube.YoutubeVideo;
import dd.wan.myyoutube.databinding.ActivityMainBinding;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DataVideoHolder> {
    List<Video> videos = new ArrayList<Video>();
    Context context;
    DataVideo dataVideo;
    public RecyclerAdapter(Context context,List<Video> list,DataVideo dataVideo) {
        this.context = context;
        this.videos = list;
        this.dataVideo = dataVideo;
    }

    @NonNull
    @NotNull
    @Override
    public DataVideoHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video,parent,false);
        return new DataVideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DataVideoHolder holder, int position) {
        holder.txtNameVideo.setText(videos.get(position).getSnippet().getTitle());
        holder.txtNameChanel.setText(videos.get(position).getSnippet().getChannelTitle());
        Glide.with(context)
                .load(videos.get(position).getSnippet().getImg().getUrl().getUrl())
                .into(holder.circleImageView);
        Glide.with(context)
                .load(videos.get(position).getSnippet().getImg().getUrl().getUrl())
                .into(holder.Anh);
        holder.startVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadVideo(position);
            }
        });
        holder.Anh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadVideo(position);
            }
        });
        holder.txtNameVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadVideo(position);
            }
        });
    }
    public void LoadVideo(int position)
    {
        Intent intent = new Intent(context, YoutubeVideo.class);
        intent.putExtra("videoID",videos.get(position).getVideoID());
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return videos.size();
    }
    public static class DataVideoHolder extends RecyclerView.ViewHolder{
        ImageView Anh;
        CircleImageView circleImageView;
        LinearLayout startVideo;
        TextView txtNameVideo,txtNameChanel;
        public DataVideoHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            Anh = itemView.findViewById(R.id.imageVideo);
            circleImageView = itemView.findViewById(R.id.imageChanel);
            txtNameChanel = itemView.findViewById(R.id.nameChanel);
            txtNameVideo = itemView.findViewById(R.id.nameVideo);
            startVideo = itemView.findViewById(R.id.startVideo);
        }
    }
    public void AddList(DataVideo da,List<Video> vid)
    {
        videos.addAll(vid);
        this.dataVideo = da;
        notifyDataSetChanged();
    }

    public DataVideo getDataVideo() {
        return dataVideo;
    }
}
