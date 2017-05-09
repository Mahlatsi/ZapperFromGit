package com.example.monty.zapper;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.monty.zapper.api_interface.PersonAPI;
import com.example.monty.zapper.fragments.DetailFragment;
import com.example.monty.zapper.fragments.MasterFragment;
import com.example.monty.zapper.helper.Constants;
import com.example.monty.zapper.medels.PersonDetails;
import java.io.Serializable;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.monty.zapper.R.id.details_container;
import static com.example.monty.zapper.R.id.main_container;

public class MainActivity extends AppCompatActivity {


    private List<PersonDetails> PersonDetailsData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            PersonDetailsData = (List<PersonDetails>) savedInstanceState.getSerializable(Constants.STATE_ITEMS);
        }
         FragmentManager mFragmentManager = getSupportFragmentManager();
         FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        getData(mFragmentTransaction);
    }

    /**
     * this is called to get data from api and add a new fragment
     * @param mFragmentTransaction
     */
    private void getData( final FragmentTransaction mFragmentTransaction) {
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

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    mFragmentTransaction.replace(main_container, new MasterFragment());
                    mFragmentTransaction.add(details_container, new DetailFragment(1));
                } else {
                    mFragmentTransaction.replace(main_container, new MasterFragment());
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
        super.onSaveInstanceState(outState);
        outState.putSerializable(Constants.STATE_ITEMS, (Serializable) PersonDetailsData);
    }

    /**
     * this returns a list of dataf orm api
     * @return
     */
    public List<PersonDetails> getPersonDetailsData() {
        return PersonDetailsData;
    }
}
