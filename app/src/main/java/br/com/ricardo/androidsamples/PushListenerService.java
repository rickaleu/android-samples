package br.com.ricardo.androidsamples;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

public class PushListenerService extends GcmListenerService {

    private static final String TAG = "PushListenerService";

    @Override
    public void onMessageReceived(String s, Bundle bundle) {
        super.onMessageReceived(s, bundle);

        String message = bundle.getString("message");
        Log.d(TAG, "De: " + s);
        Log.d(TAG, "Mensagem: " + message);

        enviarNotificacao(message);
    }

    public void enviarNotificacao(String message){

        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra("MENSAGEM", message);

        PendingIntent pi = PendingIntent.getActivity(getBaseContext(), 001,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Personal Notification");
        builder.setSmallIcon(R.drawable.ic_menu_gallery);
        builder.setContentTitle("Notificação Push");
        builder.setContentText(message);
        builder.setContentIntent(pi);


        NotificationManagerCompat nmc = NotificationManagerCompat.from(this);
        nmc.notify(001, builder.build());

    }
}
