package br.com.ricardo.androidsamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    private TextView textNotificationReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        textNotificationReceive = (TextView) findViewById(R.id.text_notification_receive);

        String message = getIntent().getStringExtra("MENSAGEM");
        textNotificationReceive.setText(message);

    }
}
