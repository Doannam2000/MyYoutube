package dd.wan.myyoutube.Interface;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import dd.wan.myyoutube.Model.DataVideo;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

//'https://youtube.googleapis.com/youtube/v3/search?part=snippet&key=[YOUR_API_KEY]
public interface CallApi {

    String BASE_URL = "https://youtube.googleapis.com/";
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    CallApi callApi = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build().create(CallApi.class);


    @GET("youtube/v3/videos")
    Call<DataVideo> getDataVideo(@Query("part") String part,
                                 @Query("chart") String chart,
                                 @Query("key") String key );

    @GET("youtube/v3/videos")
    Call<DataVideo> getDataVideoPage(@Query("part") String part,
                                 @Query("chart") String chart,
                                 @Query("pageToken") String pageToken,
                                 @Query("key") String key);
}
