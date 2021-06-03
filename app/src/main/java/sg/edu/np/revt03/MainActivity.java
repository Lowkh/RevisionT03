package sg.edu.np.revt03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.StartButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText durationInput = findViewById(R.id.UserInput);

                Intent intent = new Intent(MainActivity.this, TimeActivity.class);
                intent.putExtra("duration", durationInput.getText().toString());
                startActivity(intent);
            }
        });
    }
}