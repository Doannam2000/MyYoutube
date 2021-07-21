package dd.wan.myyoutube.Model;

import com.google.gson.annotations.SerializedName;

public class Img {
    @SerializedName("high")
    private high url;

    public high getUrl() {
        return url;
    }

    public void setUrl(high url) {
        this.url = url;
    }

    public Img(high url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Img{" +
                "url=" + url +
                '}';
    }

    public class high{
        @SerializedName("url")
        private  String url;

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return "high{" +
                    "url='" + url + '\'' +
                    '}';
        }

        public high(String url) {
            this.url = url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
