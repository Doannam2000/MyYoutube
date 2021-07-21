package dd.wan.myyoutube.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataVideo {

    @SerializedName("etag")
    private String etag ;
    @SerializedName("nextPageToken")
    private String nextPageToken;
    @SerializedName("regionCode")
    private String regionCode;
    @SerializedName("items")
    private List<Video> item;

    public DataVideo() {
    }

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

    public List<Video> getItem() {
        return item;
    }

    public void setItem(List<Video> item) {
        this.item = item;
    }

    public DataVideo(String etag, String nextPageToken, String regionCode, List<Video> item) {
        this.etag = etag;
        this.nextPageToken = nextPageToken;
        this.regionCode = regionCode;
        this.item = item;
    }

    @Override
    public String toString() {
        return "DataVideo{" +
                "etag='" + etag + '\'' +
                ", nextPageToken='" + nextPageToken + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", item=" + item +
                '}';
    }
}
