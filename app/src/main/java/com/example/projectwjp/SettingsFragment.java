package com.example.projectwjp;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 *Klasa okna ustawien
 */
public class SettingsFragment extends Fragment {

//
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//
//    private String mParam1;
//    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment SettingsFragment.
//     */

    /**
     * utworzenie instancji fragmentu
     * @return
     */
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        //Bundle args = new Bundle();

        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     *funkcja wczytuje dane z pliku zapisu i przygotowuje do wyswietlenia.
     * przydziela funkcje do przyciskow.
     *
     * @return zwraca widok do wyswietlenia w fragmencie
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_pref",0);
        Button btnReset = (Button)view.findViewById(R.id.buttonResetProgress);

        ImageView Dodawanie = (ImageView) view.findViewById(R.id.Dodawanie);
        ImageView Odejmowanie = (ImageView) view.findViewById(R.id.Odejmowanie);
        ImageView Mnozenie = (ImageView) view.findViewById(R.id.Mnozenie);
        ImageView Dzielenie = (ImageView) view.findViewById(R.id.Dzielenie);

        progChange(Dodawanie);
        progChange(Odejmowanie);
        progChange(Mnozenie);
        progChange(Dzielenie);

        ImageButton btnHero0 = (ImageButton)view.findViewById(R.id.imageButtonHero0);
        ImageButton btnHero1 = (ImageButton)view.findViewById(R.id.imageButtonHero1);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetProgress(view);
            }
        });
        btnHero0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sethero(view,R.drawable.hero);
            }
        });
        btnHero1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sethero(view,R.drawable.hero1);
            }
        });
        return view;
    }

    /**
     * funkcja wyboru bitmapy bohatera
     * @param view to widok z ktorego slucha funkcja
     * @param i to adres id resource'a bitmapy
     */
    private void sethero(View view, int i) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_pref",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("hero",i);
        editor.apply();
    }

    /**
     * Funkcja czysci plik zapisu
     * @param view to widok z ktorego slucha funkcja
     */
    private void resetProgress(View view) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_pref",0);
        sharedPreferences.edit().clear().apply();

    }

    /**
     * Funkcja przydziela odpowiedni obrazek na podstwie czy poziom zostal ukonczony czy nie, biorac pod uwage tag kontenera na obraz
     * @param imageView kontener obrazu
     */
    private void progChange(ImageView imageView){
       SharedPreferences sharedPreferences= getActivity().getSharedPreferences("my_pref",0);

        if (sharedPreferences.getBoolean(imageView.getTag().toString(),false)){
            imageView.setImageResource(R.drawable.completed);
        }
        else{
            imageView.setImageResource(R.drawable.notcompleted);
        }

    }
}