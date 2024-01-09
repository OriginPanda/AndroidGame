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
 * Klasa arganizujaca okno gry
 */
public class GameFragment extends Fragment {

    private static final String ARG_DIFF = "diffLevel";
    private static final String ARG_TYPE = "levelType";

    // TODO: Rename and change types of parameters
    private int mdiffLevel;
    private Type mlevelType;

    public GameFragment() {
        // Required empty public constructor
    }

    /**
     *
     * @param diffLevel Parameter 1.
     * @param levelType Parameter 2.
     */
    public static GameFragment newInstance(int diffLevel, Type levelType) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();


        args.putInt(ARG_DIFF, diffLevel);
        args.putString(ARG_TYPE, levelType.toString());

        fragment.setArguments(args);
        return fragment;
    }

    /**
     *Kolejne tworzenie
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mdiffLevel = getArguments().getInt(ARG_DIFF);
            mlevelType = Type.valueOf(getArguments().getString(ARG_TYPE));
        }
    }

    /**
     *Funkcja tworzaca widok fragmentu
     *Widok jest tworzony z dwoch podwidokow "UI" i widoku rysowania gry
     * Bardzo dlugo glowilem sie jak wszystko ze soba polaczyc bo jest wiele sposobow i kazdy gdzie ma problemy
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        ViewGroup layout = new FrameLayout(getContext());


        View view = inflater.inflate(R.layout.fragment_game,container,false);

//        gameView.setLayoutParams(new FrameLayout.LayoutParams(
//                FrameLayout.LayoutParams.WRAP_CONTENT,
//                FrameLayout.LayoutParams.WRAP_CONTENT));


        GameView gameView = new GameView(getContext(),view,getArguments());
        layout.addView(gameView);
        layout.addView(view);




        return layout;
                //new GameView(getContext());//inflater.inflate(R.layout.fragment_game, container, false);
    }


}