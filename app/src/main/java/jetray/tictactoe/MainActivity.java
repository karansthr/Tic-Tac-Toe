package jetray.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

    public EditText plyr1;
    public EditText plyr2;

    public Spinner difficulty;
    public CharSequence player1 = "Player 1";
    public CharSequence player2 = "Player 2";

    public CharSequence cloneplayer2;
    boolean player1ax = true;
    boolean selectedSinglePlayer;
    boolean easy = true;
    boolean medium = false;
    boolean hard = false;
    boolean impossible = false;
    public CheckBox p1x, p1o, p2x, p2o, singleplayer, twoplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//apply the animation ( fade In ) to your LAyout

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        addItemToDifficultySpinner();

        plyr1 = (EditText) findViewById(R.id.playerone);
        plyr2 = (EditText) findViewById(R.id.playertwo);


        p1x = (CheckBox) findViewById(R.id.player1x);
        p1o = (CheckBox) findViewById(R.id.player1o);
        p2x = (CheckBox) findViewById(R.id.player2x);
        p2o = (CheckBox) findViewById(R.id.player2o);
        singleplayer = (CheckBox) findViewById(R.id.splayer);
        twoplayer = (CheckBox) findViewById(R.id.tplayer);

        p1x.setOnClickListener(checkboxClickListener);
        p1o.setOnClickListener(checkboxClickListener);
        p2x.setOnClickListener(checkboxClickListener);
        p2o.setOnClickListener(checkboxClickListener);
        singleplayer.setOnClickListener(checkboxClickListener);
        twoplayer.setOnClickListener(checkboxClickListener);

        difficulty.setEnabled(false);


        p1x.setChecked(true);
        p2o.setChecked(true);
        twoplayer.setChecked(true);


        plyr1.addTextChangedListener(new TextWatcher() {                               /*this code take player1's name characterwise i.e it takes one character at a time and
                                                                                         saved to string variable player1*/
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                player1 = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        plyr2.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                player2 = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    public void addItemToDifficultySpinner() {
        difficulty = (Spinner) findViewById(R.id.difficulty);

        List<String> list = new ArrayList<String>();
        list.add("Easy");
        list.add("Medium");
        list.add("Hard");
        list.add("Impossible");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(dataAdapter);


        difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp = parent.getItemAtPosition(position).toString();

                switch (temp) {
                    case "Easy":
                        easy = true;
                        medium = false;
                        hard = false;
                        impossible = false;
                        break;
                    case "Medium":
                        easy = false;
                        medium = true;
                        hard = false;
                        impossible = false;
                        break;
                    case "Hard":
                        easy = false;
                        medium = false;
                        hard = true;
                        impossible = false;
                        break;
                    case "Impossible":
                        easy = false;
                        medium = false;
                        hard = false;
                        impossible = true;
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                medium = true;
                easy = false;
                hard = false;
                impossible = false;
            }
        });
    }


    View.OnClickListener checkboxClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean checked = ((CheckBox) view).isChecked();
            if (checked) {
                switch (view.getId()) {
                    case R.id.player1x:
                        p1o.setChecked(false);
                        p2x.setChecked(false);
                        p2o.setChecked(true);
                        player1ax = true;
                        break;
                    case R.id.player1o:
                        p1x.setChecked(false);
                        p2o.setChecked(false);
                        p2x.setChecked(true);
                        player1ax = false;
                        break;
                    case R.id.player2x:
                        p2o.setChecked(false);
                        p1x.setChecked(false);
                        p1o.setChecked(true);
                        player1ax = false;
                        break;
                    case R.id.player2o:
                        p2x.setChecked(false);
                        p1o.setChecked(false);
                        p1x.setChecked(true);
                        player1ax = true;
                        break;
                    case R.id.splayer:
                        twoplayer.setChecked(false);
                        selectedSinglePlayer = true;
                        cloneplayer2 = player2;
                        plyr2.setText("CPU");

                        plyr1.setImeOptions(EditorInfo.IME_ACTION_DONE);
                        plyr1.setImeActionLabel("DONE", EditorInfo.IME_ACTION_DONE);


                        difficulty.setEnabled(true);
                        break;
                    case R.id.tplayer:
                        singleplayer.setChecked(false);
                        selectedSinglePlayer = false;
                        plyr2.setText(cloneplayer2);
                        plyr1.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                        plyr1.setImeActionLabel("NEXT", EditorInfo.IME_ACTION_NEXT);
                        difficulty.setEnabled(false);
                        break;
                }

            } else {
                switch (view.getId()) {
                    case R.id.player1x:
                        p1o.setChecked(true);
                        p2x.setChecked(true);
                        p2o.setChecked(false);
                        player1ax = false;
                        break;
                    case R.id.player1o:
                        p1x.setChecked(true);
                        p2o.setChecked(true);
                        p2x.setChecked(false);
                        player1ax = true;
                        break;
                    case R.id.player2x:
                        p2o.setChecked(true);
                        p1x.setChecked(true);
                        p1o.setChecked(false);
                        player1ax = true;
                        break;
                    case R.id.player2o:
                        p2x.setChecked(true);
                        p1o.setChecked(true);
                        p1x.setChecked(false);
                        player1ax = false;
                        break;
                    case R.id.splayer:
                        twoplayer.setChecked(true);
                        selectedSinglePlayer = false;
                        plyr2.setText(cloneplayer2);
                        difficulty.setEnabled(false);
                        plyr1.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                        plyr1.setImeActionLabel("NEXT", EditorInfo.IME_ACTION_NEXT);
                        break;
                    case R.id.tplayer:
                        singleplayer.setChecked(true);
                        selectedSinglePlayer = true;
                        plyr2.setText("CPU");
                        plyr1.setImeOptions(EditorInfo.IME_ACTION_DONE);
                        plyr1.setImeActionLabel("DONE", EditorInfo.IME_ACTION_DONE);
                        difficulty.setEnabled(true);
                        break;
                }

            }

        }
    };


    public void startgame(View view) {

        if (!selectedSinglePlayer)
            if (player2.length() == 0)
                player2 = "player 2";
        if (player1.length() == 0)
            player1 = "player 1";

        CharSequence[] players = {player1, player2};
        Intent i = new Intent(this, Afterstart.class);
        i.putExtra("easy", easy);
        i.putExtra("medium", medium);
        i.putExtra("hard", hard);
        i.putExtra("impossible", impossible);
        i.putExtra("playersnames", players);
        i.putExtra("player1ax", player1ax);
        i.putExtra("selectedsingleplayer", selectedSinglePlayer);
        startActivity(i);
    }

}