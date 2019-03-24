package br.com.ricardo.androidsamples;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity3 extends AppCompatActivity {

    private EditText editNotification;
    private Button buttonNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        editNotification = (EditText) findViewById(R.id.edit_notification);
        buttonNotification = (Button) findViewById(R.id.button_notification);
        buttonNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = editNotification.getText().toString();

                Intent intent = new Intent(MainActivity3.this, NotificationActivity.class);
                intent.putExtra("MENSAGEM", msg);

                int id = (int) (Math.random()*1000);
                PendingIntent pi = PendingIntent.getActivity(getBaseContext(),
                        id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Notification notification = new Notification.Builder(getBaseContext())
                        .setContentTitle("De: Ricardo Sousa")
                        .setContentText(msg).setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pi).build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notification.flags = Notification.FLAG_AUTO_CANCEL;
                notificationManager.notify(id, notification);

            }
        });
    }
}
