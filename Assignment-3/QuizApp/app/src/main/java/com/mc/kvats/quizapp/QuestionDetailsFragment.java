package com.mc.kvats.quizapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class QuestionDetailsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private String question;

    private TextView textView;
    private Button true_button, false_button, save_button;
    private int button_selected; // 0 not selected, 1 true selected, 2 false selected

    public QuestionDetailsFragment() {
        // Required empty public constructor
    }

    public static QuestionDetailsFragment newInstance(String param1, String param2) {
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        button_selected = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_question_details, container, false);
//        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        textView = (TextView) rootView.findViewById(R.id.question);
        true_button = (Button) rootView.findViewById(R.id.true_button);
        false_button = (Button) rootView.findViewById(R.id.false_button);
        save_button = (Button) rootView.findViewById(R.id.save_button);

        Bundle bundle = getArguments();
        question = String.valueOf(bundle.get("Ques"));

        textView.setText(question);

        true_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( button_selected == 0 ) {
                    true_button.setBackground(getResources().getDrawable(R.drawable.true_button_selected));
                    true_button.setTextColor(getResources().getColor(R.color.white));
                    button_selected = 1;
                }
                else if ( button_selected == 1 ) {
                    true_button.setBackground(getResources().getDrawable(R.drawable.true_button_background));
                    true_button.setTextColor(getResources().getColor(R.color.green));
                    button_selected = 0;
                }
                else {
                    true_button.setBackground(getResources().getDrawable(R.drawable.true_button_selected));
                    true_button.setTextColor(getResources().getColor(R.color.white));

                    false_button.setBackground(getResources().getDrawable(R.drawable.false_button_background));
                    false_button.setTextColor(getResources().getColor(R.color.redLight));
                    button_selected = 1;
                }
            }
        });

        false_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( button_selected == 0 ) {
                    false_button.setBackground(getResources().getDrawable(R.drawable.false_button_selected));
                    false_button.setTextColor(getResources().getColor(R.color.white));
                    button_selected = 2;
                }
                else if ( button_selected == 1 ) {
                    false_button.setBackground(getResources().getDrawable(R.drawable.false_button_selected));
                    false_button.setTextColor(getResources().getColor(R.color.white));

                    true_button.setBackground(getResources().getDrawable(R.drawable.true_button_background));
                    true_button.setTextColor(getResources().getColor(R.color.green));
                    button_selected = 2;
                }
                else {
                    false_button.setBackground(getResources().getDrawable(R.drawable.false_button_background));
                    false_button.setTextColor(getResources().getColor(R.color.redLight));
                    button_selected = 0;
                }
            }
        });

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return rootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                getActivity().onBackPressed();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
