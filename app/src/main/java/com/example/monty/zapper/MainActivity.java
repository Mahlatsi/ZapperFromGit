package com.example.monty.zapper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.monty.zapper.adapter.Master_Adapter;
import com.example.monty.zapper.api_interface.PersonAPI;
import com.example.monty.zapper.fragments.DetailFragment;
import com.example.monty.zapper.fragments.MasterFragment;
import com.example.monty.zapper.helper.Constants;
import com.example.monty.zapper.medels.PersonDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.monty.zapper.R.id.details_container;
import static com.example.monty.zapper.R.id.main_container;

public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private List<PersonDetails> PersonDetailsData;
    private SharedPreferences sharedpreferences;
    private static final String STATE_ITEMS = "items";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            PersonDetailsData = (List<PersonDetails>) savedInstanceState.getSerializable(STATE_ITEMS);
        }


        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        getData();

    }

    private void getData() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            PersonAPI api_interface = retrofit.create(PersonAPI.class);

            Call<List<PersonDetails>> call = api_interface.getItems();
            call.enqueue(new Callback<List<PersonDetails>>() {
                @Override
                public void onResponse(Call<List<PersonDetails>> call, Response<List<PersonDetails>> response) {
                    PersonDetailsData = response.body();

                    if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                        mFragmentTransaction.add(main_container, new MasterFragment());
                        mFragmentTransaction.add(details_container,new DetailFragment(1));
                    }else{
                        mFragmentTransaction.add(main_container, new MasterFragment());
                    }
                    mFragmentTransaction.commit();
                }
                @Override
                public void onFailure(Call<List<PersonDetails>> call, Throwable t) {
                    Log.d("onFailure", t.toString());
                }
            });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        // Save our own state now
       outState.putSerializable(STATE_ITEMS, (Serializable) PersonDetailsData);
    }
    public List<PersonDetails> getPersonDetailsData() {
        return PersonDetailsData;
    }
}
