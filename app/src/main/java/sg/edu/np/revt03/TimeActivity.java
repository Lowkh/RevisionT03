package sg.edu.np.revt03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TimeActivity extends AppCompatActivity {
    TextView player1score, player2score;
    ArrayList<String> data;
    Button next;
    boolean isP2;
    CountDownTimer cdt;
    int player1, player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        Intent intent  = getIntent();
        int duration = Integer.parseInt(intent.getStringExtra("duration"));

        player1 = duration * 1000;
        player2 = duration * 1000;

        player1score = findViewById(R.id.Player1Score);
        player2score = findViewById(R.id.Player2Score);

        player1score.setText(""+duration);
        player2score.setText(""+duration);

        data = new ArrayList<>();
        ListView list = findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        list.setAdapter(adapter);

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdt.cancel();
                isP2 = !isP2;
                if(isP2)
                {
                    data.add("Player 1 left " + player1/1000 + " sec.");
                    startTimer(player2);
                }
                else
                {
                    data.add("Player 2 left " + player2/1000 + " sec.");
                    startTimer(player1);
                }
                adapter.notifyDataSetChanged();
            }
        });
        startTimer(player1);
    }
    /*
    Player 1 will start on entry.... ....
     */
    private void startTimer(int dur)
    {
        cdt = new CountDownTimer(dur, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(isP2)
                {
                    player2score.setText("" + millisUntilFinished/1000);
                    player2 = (int) millisUntilFinished;  // setting player 2 current time
                }
                else
                {
                    player1score.setText("" + millisUntilFinished/1000);
                    player1 = (int) millisUntilFinished;
                }
            }
            /*
             Timer will on finish set values to 0
             */
            @Override
            public void onFinish() {
                if(isP2)
                {
                    Toast.makeText(TimeActivity.this, "Player 2's time has ran out",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(TimeActivity.this, "Player 1's time has ran out",Toast.LENGTH_SHORT).show();

                }
            }
        };
        cdt.start();

    }
}