package com.example.projectwjp;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartFragment extends Fragment {

    private static final String ARG_PARAM1 = "levelType";
    private static final String ARG_PARAM2 = "viewPos";



    private Type mlevelType = Type.Dodawanie;
    private int viewPos = 0;

    public StartFragment() {
        // Required empty public constructor
    }

    /**
     * parametry ktore dostarczamy do klasy przy tworzeniu
     * @param levelType Parameter 1.
     * @param viewPos Parameter 2.
     * @return nowa instancja fragmentu.
     */
    public static StartFragment newInstance(Type levelType, int viewPos) {
        StartFragment fragment = new StartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, levelType.toString());
        args.putInt(ARG_PARAM2, viewPos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mlevelType= Type.valueOf(getArguments().getString(ARG_PARAM1));
            viewPos = getArguments().getInt(ARG_PARAM2);

        }
    }

    /**
     *
     *Funkcja do utworzenia tresci fragmentu (podokna)
     *Wpierw dostarczamy schemat xml po czym przypisujemy odniesienia do poszczegolnych elementow
     *Ustawiamy listenery na guziki
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start, container, false);
        Button bStart = (Button)view.findViewById(R.id.startbutton);
        Button bLeft = (Button)view.findViewById(R.id.buttonLeft);
        Button bRight = (Button)view.findViewById(R.id.buttonRight);
        TextView textType = (TextView)view.findViewById(R.id.fragmentType);
        ImageView imageView = (ImageView) view.findViewById(R.id.completedView);
        TextView diffView =(TextView)view.findViewById(R.id.textViewDiffLevel);

        textType.setText(mlevelType.toString());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_pref",0);
        if(sharedPreferences.getBoolean(mlevelType.toString(),false)){
            imageView.setImageResource(R.drawable.completed);
        }
        else {
            imageView.setImageResource(R.drawable.notcompleted);
        }

        diffView.setText("Poziom Trud: "+ String.valueOf(sharedPreferences.getInt("diffLevel",1)));
        //OnClick w XML przycisku nie działa xD?
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartGame(view);
            }
        });
        bLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPos -=1;
                swipeView(view);
            }
        });
        bRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPos +=1;
                swipeView(view);
            }
        });

        return view;
    }

    /**
     *
     * @param view
     * Funkcja do rozpoczecia gry, czyli utworzenia fragmentu z trescia poziomu gry
     */
    public void StartGame(View view){

        //Bundle args = getArguments();
        Bundle args = new Bundle();
        MainActivity activity= (MainActivity)getActivity();
        args.putInt("diffLevel", activity.getDiffLevel());
        args.putString("levelType",mlevelType.toString());
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, GameFragment.class, args,"frag1").setReorderingAllowed(true).addToBackStack(null).commit();

    }

    /**
     * Funkcja zmieniajaca poziomy wykorzystywana na przyciskach
     * i aktualizuje fragment
     */
    public void swipeView(View view){
        Bundle args = new Bundle();
        if(viewPos<=-1){
            viewPos=3;
        }
        else if(viewPos>=4){
            viewPos=0;
        }
        String type;
        switch (viewPos){
            case 0:
                type = Type.Dodawanie.toString();
                break;
            case 1:
                type = Type.Odejmowanie.toString();
                break;
            case 2:
                type = Type.Mnozenie.toString();
                break;
            case 3:
                type = Type.Dzielenie.toString();
                break;
            default:
                type = Type.Dodawanie.toString();
                break;

        }
        args.putString("levelType",type);
        args.putInt("viewPos",viewPos);


        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, StartFragment.class,args).setReorderingAllowed(true).commit();
    }
}