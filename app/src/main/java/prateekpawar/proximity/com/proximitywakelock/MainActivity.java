package prateekpawar.proximity.com.proximitywakelock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1= (Button) findViewById(R.id.button);
        button2= (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, SensorService.class));
                Toast.makeText(MainActivity.this, "Sevice is started", Toast.LENGTH_LONG).show();
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, SensorService.class));
                Toast.makeText(MainActivity.this, "Service is stoped", Toast.LENGTH_LONG).show();
            }
        });

    }
}
