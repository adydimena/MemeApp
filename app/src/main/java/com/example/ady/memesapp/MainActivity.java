package com.example.ady.memesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.ady.memesapp.pojo.Context;
import com.example.ady.memesapp.pojo.GoogleResponse;
import com.example.ady.memesapp.pojo.Item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText serach;
    private List<Item> result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = new ArrayList<>();


    }

    public void findMeMes(View view) {
        serach = findViewById(R.id.serach);
        String dummy = serach.getText().toString();



        RetrofitHelper.responseback(dummy +" meme")
                .enqueue(new Callback<GoogleResponse>() {
                    @Override
                    public void onResponse(Call<GoogleResponse> call, Response<GoogleResponse> response) {

                            result = response.body().getItems();

                        if (result != null) {
                            Log.d(TAG, "onResponse: " + result.get(0));
                            for (int i = 0; i < result.size(); i++) {
                                Log.d(TAG, "onResponse: " + result.size());
                                if (result.get(i).getPagemap()!=null) {
                                    if (result.get(i).getPagemap().getCseImage() != null) {
                                        Log.d(TAG, "onResponse: " + i + " PageMap "
                                                + result.get(i).getPagemap().getCseImage().get(0).getSrc());
                                        Log.d(TAG, "onResponse: " + i + " PageMap Size "
                                                + result.get(i).getPagemap().getCseImage().size());
                                    } else {
                                        Log.d(TAG, "onResponse: PageMap is empty");
                                    }
                                }
                            /*
                            if (result.get(i).getPagemap().getBook() != null) {
                                Log.d(TAG, "onResponse: " + i + " Blook "
                                        + result.get(i).getPagemap().getBook().get(0).getImage());
                                Log.d(TAG, "onResponse: " + i + " Block Size "
                                        + result.get(i).getPagemap().getCseImage().size());
                            }else{
                                Log.d(TAG, "onResponse: Blook is empty");
                            }
                            if (result.get(i).getPagemap().getWebsite() != null) {
                                Log.d(TAG, "onResponse: " + i + " website "
                                        + result.get(i).getPagemap().getWebsite().get(0).getImage());
                                Log.d(TAG, "onResponse: " + i + " Block Size "
                                        + result.get(i).getPagemap().getWebsite().size());
                            }else{
                                Log.d(TAG, "onResponse: Blook is empty");
                            }
                            */

                            }
                            RecyclerView recyclerView = findViewById(R.id.recycleMainAcitivity);
                            RecyclerView.LayoutManager layoutManager =
                                    new LinearLayoutManager(MainActivity.this);
                            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                            Recycleadapter recycleadapter = new Recycleadapter(result,MainActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(itemAnimator);
                            recyclerView.setAdapter(recycleadapter);


                        }else
                        {
                            Log.d(TAG, "onResponse: RESULT EMPTY");
                        }
                    }

                    @Override
                    public void onFailure(Call<GoogleResponse> call, Throwable t) {

                    }
                });

    }
}
