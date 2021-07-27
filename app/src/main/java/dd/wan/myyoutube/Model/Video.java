package dd.wan.myyoutube.Model;

import com.google.gson.annotations.SerializedName;

public class Video {
    @SerializedName("etag")
    private String etag;
    @SerializedName("id")
    private String VideoID;
    @SerializedName("snippet")
    private Snippet snippet;

    public Video() {
    }

    public Video(String etag, String videoID, Snippet snippet) {
        this.etag = etag;
        this.VideoID = videoID;
        this.snippet = snippet;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }


    public String getVideoID() {
        return VideoID;
    }

    public void setVideoID(String videoID) {
        VideoID = videoID;
    }

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    @Override
    public String toString() {
        return "Video{" +
                "etag='" + etag + '\'' +
                ", videoID='" + VideoID + '\'' +
                ", snippet=" + snippet +
                '}';
    }

}

