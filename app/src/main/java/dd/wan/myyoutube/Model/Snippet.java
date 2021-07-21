package dd.wan.myyoutube.Model;

import com.google.gson.annotations.SerializedName;

public class Snippet {

    @SerializedName("channelId")
    private String channelId;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("thumbnails")
    private Img img;

    @SerializedName("channelTitle")
    private String channelTitle;

    @SerializedName("liveBroadcastContent")
    private String liveBroadcastContent;

    public Snippet() {
    }

    public Snippet(String channelId, String title, String description, Img img, String channelTitle, String liveBroadcastContent) {

        this.channelId = channelId;
        this.title = title;
        this.description = description;
        this.img = img;
        this.channelTitle = channelTitle;
        this.liveBroadcastContent = liveBroadcastContent;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Img getImg() {
        return img;
    }

    public void setImg(Img img) {
        this.img = img;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getLiveBroadcastContent() {
        return liveBroadcastContent;
    }

    public void setLiveBroadcastContent(String liveBroadcastContent) {
        this.liveBroadcastContent = liveBroadcastContent;
    }

    @Override
    public String toString() {
        return "Snippet{" +
                "channelId='" + channelId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", img=" + img +
                ", channelTitle='" + channelTitle + '\'' +
                ", liveBroadcastContent='" + liveBroadcastContent + '\'' +
                '}';
    }
}
