package com.example.monty.zapper.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.monty.zapper.MainActivity;
import com.example.monty.zapper.R;
import com.example.monty.zapper.api_interface.PersonAPI;
import com.example.monty.zapper.helper.Constants;
import com.example.monty.zapper.medels.PersonDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.monty.zapper.R.id.main_container;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class DetailFragment extends Fragment {

    private int mAdapterPosistion = 1;
    private OnFragmentInteractionListener mListener;
    public DetailFragment() {
        // Required empty public constructor
    }
    public DetailFragment(int adapterPosition) {
        this.mAdapterPosistion = adapterPosition;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PersonAPI api_interface = retrofit.create(PersonAPI.class);

        Call<PersonDetails> call = api_interface.getDetails(mAdapterPosistion);
        call.enqueue(new Callback<PersonDetails>() {
            @Override
            public void onResponse(Call<PersonDetails> call, Response<PersonDetails> response) {
                 response.body();
                TextView id = (TextView)rootView.findViewById(R.id.id_txtview);
                TextView firstName = (TextView)rootView.findViewById(R.id.firstname_text_view_details);
                TextView lastName = (TextView)rootView.findViewById(R.id.lastname_text_view_details);
                TextView age = (TextView)rootView.findViewById(R.id.age_text_view_details);
                TextView favouriteColour = (TextView)rootView.findViewById(R.id.favouriteColour_text_view_details);
                if (response.body()!= null){
                    id.setText("Id: " + String.valueOf(response.body().getId()));
                    favouriteColour.setText("favouriteColour: " + response.body().getFavouriteColour());
                    age.setText("age: " + String.valueOf(response.body().getAge()));

                    firstName.setText("firstName: " + response.body().getFirstName());
                    lastName.setText("lastName: " + response.body().getLastName());
                }
            }

            @Override
            public void onFailure(Call<PersonDetails> call, Throwable t) {
            }
        });
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
