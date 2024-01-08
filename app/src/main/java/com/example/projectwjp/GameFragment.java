package com.example.projectwjp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DIFF = "diffLevel";
    private static final String ARG_TYPE = "levelType";

    // TODO: Rename and change types of parameters
    private int mdiffLevel;
    private Type mlevelType;

    public GameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param diffLevel Parameter 1.
     * @param levelType Parameter 2.
     * @return A new instance of fragment GameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFragment newInstance(int diffLevel, Type levelType) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();


        args.putInt(ARG_DIFF, diffLevel);
        args.putString(ARG_TYPE, levelType.toString());

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mdiffLevel = getArguments().getInt(ARG_DIFF);
            mlevelType = Type.valueOf(getArguments().getString(ARG_TYPE));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        ViewGroup layout = new FrameLayout(getContext());


        View view = inflater.inflate(R.layout.fragment_game,container,false);

//        gameView.setLayoutParams(new FrameLayout.LayoutParams(
//                FrameLayout.LayoutParams.WRAP_CONTENT,
//                FrameLayout.LayoutParams.WRAP_CONTENT));
        Button b = (Button)view.findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = view.findViewById(R.id.levelType);
                textView.setText("seima");//TODO PAUZA
                Test(view);
            }
        });
        GameView gameView = new GameView(getContext(),view,getArguments());
        layout.addView(gameView);
        layout.addView(view);




        return layout;
                //new GameView(getContext());//inflater.inflate(R.layout.fragment_game, container, false);
    }
    public void Test(View view){

    }

}