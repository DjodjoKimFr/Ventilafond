package com.djodjokim.ventilafond.ui;

import android.annotation.SuppressLint;
import android.icu.text.DecimalFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.djodjokim.ventilafond.R;
import com.djodjokim.ventilafond.databinding.FragmentResultBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {

    private FragmentResultBinding binding;
    private TextView textViewResultatPoids;
    private TextView textViewReultat6mL;
    private TextView textViewReultat8mL;
    private CalculViewModel viewModel;


    public static ResultFragment newInstance() {
        ResultFragment fragment = new ResultFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {

            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle taillePatient) {

                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);

                int taille = taillePatient.getInt("tailleKey");
                double genre = taillePatient.getDouble("genreKey");

                double poidsIdeal = viewModel.calculerPoidsIdeal(taille, genre);
                double volume6mL = viewModel.calculerVolume6mL(poidsIdeal);
                double volume8mL = viewModel.calculerVolume8mL(poidsIdeal);

                textViewResultatPoids = binding.resultatPoidsIdealTheorique;
                textViewReultat6mL = binding.resultatVolume6mL;
                textViewReultat8mL = binding.resultatVolume8mL;

                String formattedPoids = String.format("%.02f", poidsIdeal);
                textViewResultatPoids.setText(formattedPoids + " kg");
                String formatedVolume6mL = String.format("%.02f", volume6mL);
                textViewReultat6mL.setText(formatedVolume6mL + " mL");
                String formatedVolume8mL = String.format("%.02f", volume8mL);
                textViewReultat8mL.setText(formatedVolume8mL + " mL");
            }
        });

        viewModel = new CalculViewModel();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResultBinding.inflate(inflater, container, false);
        viewModel = new CalculViewModel();

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        binding.retourButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                WelcomeFragment fragment = WelcomeFragment.newInstance();
                fragmentTransaction.replace(R.id.fragment_container_view, fragment);
                fragmentTransaction.commit();
            }
        });
    }

}