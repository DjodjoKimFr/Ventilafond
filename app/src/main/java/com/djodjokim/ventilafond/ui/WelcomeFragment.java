package com.djodjokim.ventilafond.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


import com.djodjokim.ventilafond.R;
import com.djodjokim.ventilafond.databinding.FragmentResultBinding;
import com.djodjokim.ventilafond.databinding.FragmentWelcomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WelcomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WelcomeFragment extends Fragment {

    private FragmentWelcomeBinding binding;
    private EditText editTextTaille;
    private TextView editErreurTaille;
    private RadioButton radioButtonHomme;
    private RadioButton radioButtonFemme;
    private Button btnCalcul;
    private CalculViewModel viewModel;

    public static WelcomeFragment newInstance() {
        WelcomeFragment fragment = new WelcomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);


        editTextTaille = binding.taillePatientEditText;
        radioButtonHomme = binding.hommeButton;
        radioButtonFemme = binding.femmeButton;
        btnCalcul = binding.calculButton;
        editErreurTaille = binding.erreurTaille;

        viewModel = new CalculViewModel();

        return binding.getRoot();
        }



    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.taillePatientEditText.setText("");

        binding.calculButton.setEnabled(false);
        binding.hommeButton.setEnabled(false);
        binding.femmeButton.setEnabled(false);

        binding.taillePatientEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    // Convertir le texte en un nombre entier
                    int taille = Integer.parseInt(s.toString());

                    if (taille < 136) {
                        binding.erreurTaille.setText("Taille minimale : 136 cm.");
                    } else {
                        binding.erreurTaille.setText(""); // On efface le message d'erreur
                        binding.hommeButton.setEnabled(true);
                        binding.femmeButton.setEnabled(true);
                    }
                } catch (NumberFormatException e) {
                    // Si la conversion échoue (par exemple si le texte n'est pas un nombre valide)
                    binding.erreurTaille.setText("Entrée invalide.");
                }
            }
        });

        binding.hommeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                binding.calculButton.setEnabled(true);
                binding.femmeButton.setChecked(false);
            }

        });

        binding.femmeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                binding.calculButton.setEnabled(true);
                binding.hommeButton.setChecked(false);
            }

        });

        if (viewModel != null) {
            Button calculButton = binding.calculButton;
            calculButton.setOnClickListener(v -> {
                // Utilise le ViewModel après avoir vérifié qu'il est non nul
                double poidsIdeal = viewModel.getPoidsIdeal();
                // Utiliser le poids idéal dans ton calcul
            });
        }


        binding.calculButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {


                String tailleText = editTextTaille.getText().toString();
                boolean genreHomme = radioButtonHomme.isChecked();
                boolean genreFemme = radioButtonFemme.isChecked();
                double genre = 0;

                int taille = (int) Double.parseDouble(tailleText);
                if (genreHomme == true) {
                    genre = 50;
                } else if (genreFemme == true) {
                    genre = 45.5;
                }
                viewModel.setTaillePatient(taille);
                viewModel.setGenreConstante(genre);

                Bundle taillePatient = new Bundle();
                taillePatient.putInt("tailleKey", taille);
                taillePatient.putDouble("genreKey", genre);
                getParentFragmentManager().setFragmentResult("requestKey", taillePatient);
                getParentFragmentManager().setFragmentResult("requestGenreKey", taillePatient);




                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ResultFragment resultFragment = ResultFragment.newInstance();
                fragmentTransaction.replace(R.id.fragment_container_view, resultFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}