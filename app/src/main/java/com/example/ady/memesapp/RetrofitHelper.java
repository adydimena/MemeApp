package com.example.ady.memesapp;




import com.example.ady.memesapp.pojo.GoogleResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * <script>
 (function() {
 var cx = '005947202212711455101:s0hxc7549dg';
 var gcse = document.createElement('script');
 gcse.type = 'text/javascript';
 gcse.async = true;
 gcse.src = 'https://cse.google.com/cse.js?cx=' + cx;
 var s = document.getElementsByTagName('script')[0];
 s.parentNode.insertBefore(gcse, s);
 })();
 </script>
 <gcse:search></gcse:search>
 */

public class RetrofitHelper {

    public static final String BASE_URL = "https://www.googleapis.com/customsearch/";
    //AIzaSyCP2dP0cMCpQtw2v63bEtAYbiG7yB9R1P4


    //    build the retrofit object to be used
    public static Retrofit create() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }
    public static Call<GoogleResponse> responseback(String mysearch){
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.responseback(mysearch);
    }


    //    create an interface for http verbs
    interface RetrofitService  {

        //https://www.googleapis.com/customsearch/v1?key=AIzaSyCP2dP0cMCpQtw2v63bEtAYbiG7yB9R1P4&cx=005947202212711455101:s0hxc7549dg&imgType=photo


        //https://www.googleapis.com/customsearch/v1?key=AIzaSyCP2dP0cMCpQtw2v63bEtAYbiG7yB9R1P4&cx=005947202212711455101:s0hxc7549dg&searchtype=image&num=10&start=21&q=memes

       @GET("v1?key=AIzaSyCP2dP0cMCpQtw2v63bEtAYbiG7yB9R1P4&cx=005947202212711455101:s0hxc7549dg&searchtype=image&num=10&start=1")
       Call<GoogleResponse> responseback(@Query("q") String mysearch);
        //Call<ForeCastbyHour> responseback(@Path("zipcode")int zipcode);
    }
}