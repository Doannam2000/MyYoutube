package dd.wan.myyoutube.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;


import java.util.List;


import dd.wan.myyoutube.Model.VideoSearch;
import dd.wan.myyoutube.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class RelatedVideosAdapter extends RecyclerView.Adapter<RelatedVideosAdapter.RelatedVidesHolder> {

    public interface OnItemClickListener {
        void onItemClick(VideoSearch.Videosearch item);
    }
    List<VideoSearch.Videosearch> videos;
    VideoSearch videoSearch;
    Context context;
    OnItemClickListener listener;
    String Related_Video_ID;



    public RelatedVideosAdapter(List<VideoSearch.Videosearch> videos, VideoSearch videoSearch,Context context,OnItemClickListener listener,String  Related_Video_ID) {
        this.videos = videos;
        this.videoSearch = videoSearch;
        this.context = context;
        this.listener = listener;
        this. Related_Video_ID =  Related_Video_ID;
    }

    @NonNull
    @NotNull
    @Override
    public RelatedVidesHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video,parent,false);
        return new RelatedVidesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RelatedVideosAdapter.RelatedVidesHolder holder, int position) {
        try {
            holder.txtNameVideo.setText(videos.get(position).getSnippet().getTitle());
            holder.txtNameChanel.setText(videos.get(position).getSnippet().getChannelTitle());
            Glide.with(context)
                    .load(videos.get(position).getSnippet().getImg().getUrl().getUrl())
                    .into(holder.circleImageView);
            Glide.with(context)
                    .load(videos.get(position).getSnippet().getImg().getUrl().getUrl())
                    .into(holder.Anh);
            holder.bind(videos.get(position), listener);
        }
       catch (Exception e)
       {

       }
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class RelatedVidesHolder extends RecyclerView.ViewHolder {
        ImageView Anh;
        CircleImageView circleImageView;
        LinearLayout startVideo;
        TextView txtNameVideo,txtNameChanel;

        public RelatedVidesHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            Anh = itemView.findViewById(R.id.imageVideo);
            circleImageView = itemView.findViewById(R.id.imageChanel);
            txtNameChanel = itemView.findViewById(R.id.nameChanel);
            txtNameVideo = itemView.findViewById(R.id.nameVideo);
            startVideo = itemView.findViewById(R.id.startVideo);
        }
        public void bind(final VideoSearch.Videosearch item, final RelatedVideosAdapter.OnItemClickListener listener) {
            Anh.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
            txtNameVideo.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
            txtNameChanel.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public void AddList(VideoSearch videoSearch,String related_Video_ID)
    {
        videos.addAll(videoSearch.getItem());
        this.videoSearch = videoSearch;
        Related_Video_ID = related_Video_ID;
        notifyDataSetChanged();
    }

    public VideoSearch getVideoSearch() {
        return videoSearch;
    }

    public void RemoveList()
    {
        videos.clear();
        notifyDataSetChanged();
    }

    public String getRelated_Video_ID() {
        return Related_Video_ID;
    }
}
