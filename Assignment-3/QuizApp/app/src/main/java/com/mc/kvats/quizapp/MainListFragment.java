package com.mc.kvats.quizapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class MainListFragment extends Fragment {
    private static final String TAG= "MainListFragment.class";

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Question> questionList;
    private Helper hp = Helper.getInstance();
    private SQLiteDatabase db;

    public MainListFragment() {
        // Required empty public constructor
    }

    public static MainListFragment newInstance() {
        MainListFragment fragment = new MainListFragment();
        Bundle args = new Bundle();
        // args.put(x, 'x');
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionList = new ArrayList<Question>();
        populateQuestionsList();
        db = getContext().openOrCreateDatabase(hp.DATABASE_NAME, getContext().MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS QuestionAnswer(ID INTEGER PRIMARY KEY AUTOINCREMENT, Question TEXT, Answer TEXT)");

        for ( int i=0; i<questionList.size(); i++ ) {
            String q = questionList.get(i).getQuestion();
            db.execSQL("INSERT INTO QuestionAnswer VALUES(NULL, '" + q + "','" + "0" + "');");
            Log.d(TAG, "Mssg: "+q);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.questions_list_container);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new QuestionAdapter(getContext(), questionList);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private void populateQuestionsList() {

        questionList.add(
                new Question(
                        "You cannot send a file from a Web-based e-mail account.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "The Language that the computer can understand is called Machine Language.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "Magnetic Tape used random access method.",
                        null
                )
        );

        questionList.add(
                new Question(
                        "Twitter is an online social networking and blogging service.",
                        null
                )
        );

        questionList.add(
                new Question(
                        "Worms and trojan horses are easily detected and eliminated by antivirus software.",
                        null
                )
        );

        questionList.add(
                new Question(
                        "Dot-matrix, Deskjet, Inkjet and Laser are all types of Printers.",
                        null
                )
        );

        questionList.add(
                new Question(
                        "GNU / Linux is a open source operating system.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "Whaling / Whaling attack is a kind of phishing attacks that target senior executives and other high profile to access valuable information.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "Freeware is software that is available for use at no monetary cost.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "IPv6 Internet Protocol address is represented as eight groups of four Octal digits.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "The hexadecimal number system contains digits from 1 - 15.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "Octal number system contains digits from 0 - 7.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "MS Word is a hardware.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "CPU controls only input data of computer.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "Web-based e-mail accounts do not required passwords.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "CPU stands for Central Performance Unit.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "You must include a subject in any mail message you compose.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "If you want to respond to the sender of a message, click the Respond button.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "When you reply to a message, you need to enter the text in the Subject: field.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "You can only print one copy of a selected message.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "You cannot preview a message before you print it.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "There is only one way to print a message.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "When you print a message, it is automatically deleted from your Inbox.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "You need to delete a contact and creat a new one to change contact information.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "You must complete all fields in the Contact form before you can save the contact.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "You cannot edit Contact forms.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "You should always open and attachment before saving it.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "All attachment are safe.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "It is impossible to send a worm or virus over the Internet using in attachment.",
                        null
                )
        );
        questionList.add(
                new Question(
                        "There is only one way to delete a message.",
                        null
                )
        );
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
