package br.com.ricardo.androidsamples;

import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class ServicoAlarme extends JobService {


    @Override
    public boolean onStartJob(JobParameters params) {

        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra("MENSAGEM", "Alarme disparado!");

        PendingIntent pi = PendingIntent.getActivity(getBaseContext(), 001,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Personal Notification");
        builder.setSmallIcon(R.drawable.ic_menu_gallery);
        builder.setContentTitle("Mensagem");
        builder.setContentText("Teste de Alarme!");
        builder.setContentIntent(pi);

        NotificationManagerCompat nmc = NotificationManagerCompat.from(this);
        nmc.notify(001, builder.build());

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
