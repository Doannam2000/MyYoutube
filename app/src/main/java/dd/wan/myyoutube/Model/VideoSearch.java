package dd.wan.myyoutube.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoSearch {
    @SerializedName("etag")
    private String etag;
    @SerializedName("nextPageToken")
    private String nextPageToken;
    @SerializedName("regionCode")
    private String regionCode;
    @SerializedName("items")
    private List<Videosearch> item;

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public List<Videosearch> getItem() {
        return item;
    }

    public void setItem(List<Videosearch> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "VideoSearch{" +
                "etag='" + etag + '\'' +
                ", nextPageToken='" + nextPageToken + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", item=" + item +
                '}';
    }

    public class videoId {
        @SerializedName("videoId")
        private String videoID;

        @SerializedName("playlistId")
        private String playlistID;

        public String getPlaylistID() {
            return playlistID;
        }

        public void setPlaylistID(String playlistID) {
            this.playlistID = playlistID;
        }

        public String getVideoID() {
            return videoID;
        }

        public void setVideoID(String videoID) {
            this.videoID = videoID;
        }

        @Override
        public String toString() {
            return "videoId{" +
                    "videoID='" + videoID + '\'' +
                    '}';
        }

        public videoId(String videoID, String playlistID) {
            this.videoID = videoID;
            this.playlistID = playlistID;
        }
    }

    public class Videosearch {
        @SerializedName("etag")
        private String etag;
        @SerializedName("id")
        private videoId videoID;
        @SerializedName("snippet")
        private Snippet snippet;

        public Videosearch() {
        }

        public Videosearch(String etag, videoId videoID, Snippet snippet) {
            this.etag = etag;
            this.videoID = videoID;
            this.snippet = snippet;
        }

        public String getEtag() {
            return etag;
        }

        public void setEtag(String etag) {
            this.etag = etag;
        }

        public videoId getvideoId() {
            return videoID;
        }

        public void setVideoID(videoId videoID) {
            this.videoID = videoID;
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
                    ", videoID='" + videoID + '\'' +
                    ", snippet=" + snippet +
                    '}';
        }

    }
}
