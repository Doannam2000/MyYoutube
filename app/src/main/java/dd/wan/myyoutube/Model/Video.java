package dd.wan.myyoutube.Model;

import com.google.gson.annotations.SerializedName;

public class Video {
    @SerializedName("etag")
    private String etag;
    @SerializedName("id")
//    private videoId videoID;
    private String VideoID;
    @SerializedName("snippet")
    private Snippet snippet;

    public Video() {
    }

    public Video(String etag, String videoID, Snippet snippet) {
        this.etag = etag;
//        this.videoID = videoID;
        this.VideoID = videoID;
        this.snippet = snippet;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

//    public videoId getVideoID() {
//        return videoID;
//    }

//    public void setVideoID(videoId videoID) {
//        this.videoID = videoID;
//    }


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
//    public class videoId
//    {
//        @SerializedName("videoId")
//        private String videoID;
//
//        @SerializedName("playlistId")
//        private String playlistID;
//
//        public String getPlaylistID() {
//            return playlistID;
//        }
//
//        public void setPlaylistID(String playlistID) {
//            this.playlistID = playlistID;
//        }
//
//        public String getVideoID() {
//            return videoID;
//        }
//
//        public void setVideoID(String videoID) {
//            this.videoID = videoID;
//        }
//
//        @Override
//        public String toString() {
//            return "videoId{" +
//                    "videoID='" + videoID + '\'' +
//                    '}';
//        }
//        public videoId() {
//
//        }
//
//        public videoId(String videoID, String playlistID) {
//            this.videoID = videoID;
//            this.playlistID = playlistID;
//        }
//    }
}

