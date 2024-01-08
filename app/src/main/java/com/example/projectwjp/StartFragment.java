package com.example.projectwjp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "levelType";
    private static final String ARG_PARAM2 = "viewPos";
    int viewPos = 0;


    // TODO: Rename and change types of parameters
    private Type mlevelType = Type.Dodawanie;
    private String mParam2;

    public StartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param levelType Parameter 1.
     * @param viewPos Parameter 2.
     * @return A new instance of fragment StartFragment.
     */
    // TODO: Rename and change types and number of parameters
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start, container, false);
        Button bStart = (Button)view.findViewById(R.id.startbutton);
        Button bLeft = (Button)view.findViewById(R.id.buttonLeft);
        Button bRight = (Button)view.findViewById(R.id.buttonRight);
        TextView textType = (TextView)view.findViewById(R.id.fragmentType);

        textType.setText(mlevelType.toString());


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
    public void StartGame(View view){



        //Bundle args = getArguments();
        Bundle args = new Bundle();
        MainActivity activity= (MainActivity)getActivity();
        args.putInt("diffLevel", activity.getDiffLevel());
        args.putString("levelType",mlevelType.toString());
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, GameFragment.class, args,"frag1").setReorderingAllowed(true).addToBackStack(null).commit();

    }
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