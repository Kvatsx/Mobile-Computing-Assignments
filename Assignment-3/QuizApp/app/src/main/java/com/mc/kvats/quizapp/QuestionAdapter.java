package com.mc.kvats.quizapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    private static final String TAG = "QuestionAdapter.class";

    private Context context;
    private List<Question> questionList;
    private Helper hp = Helper.getInstance();
    private SQLiteDatabase db;

    public QuestionAdapter(Context context, List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;
        db = context.openOrCreateDatabase(hp.DATABASE_NAME, context.MODE_PRIVATE, null);
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_list_items, null);
        QuestionViewHolder questionViewHolder = new QuestionViewHolder(view);
        return questionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionViewHolder questionViewHolder, int i) {
        Question question = questionList.get(i);
        final String id = String.valueOf(i+1);
        final String ques = "Q"+String.valueOf(i+1)+". "+question.getQuestion();
        questionViewHolder.textView.setText(ques);

        // TODO: Added ImageUpdate
        String query = "SELECT Answer FROM QuestionAnswer WHERE ID = " + id + ";";
        Cursor cursor = db.rawQuery(query, null);
        if ( cursor.getCount() > 0 ) {
            cursor.moveToFirst();
            String ans = cursor.getString(0);
            if ( ans.equals("0") ) {
                questionViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_right));
                questionViewHolder.imageView.setBackgroundColor(context.getResources().getColor(R.color.redLight));
            }
            else {
                questionViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_check));
                questionViewHolder.imageView.setBackgroundColor(context.getResources().getColor(R.color.green));
            }
        }

        questionViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Need to implement on click on card view
                FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                QuestionDetailsFragment questionDetailsFragment = new QuestionDetailsFragment();
                Bundle args = new Bundle();
                args.putString("Ques", ques);
                args.putString("Id", id);
                questionDetailsFragment.setArguments(args);
                ft.replace(R.id.fragment_container, questionDetailsFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView textView;
        private ImageView imageView;

        public QuestionViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_view);
            textView = (TextView) itemView.findViewById(R.id.question);
            imageView = (ImageView) itemView.findViewById(R.id.status_question);

        }

    }


}