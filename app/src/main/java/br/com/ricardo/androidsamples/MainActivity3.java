package br.com.ricardo.androidsamples;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    private static final String TAG = "MainActivity3";

    private EditText editNotification;
    private EditText editProgramarNotification;
    private Button buttonNotification;
    private Button buttonProgramarNotification;

    private BroadcastReceiver broadcastReceiver;
    private TextView textProgresso;
    private ProgressBar progressProgresso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textProgresso = (TextView) findViewById(R.id.text_mensagem);

        progressProgresso = (ProgressBar) findViewById(R.id.progressBar_progresso);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                progressProgresso.setVisibility(progressProgresso.GONE);
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

                boolean enviado = sharedPreferences.getBoolean("enviado", false);
                if(enviado){
                    textProgresso.setText("O Token foi gerado e enviado ao servidor.");
                } else {
                    textProgresso.setText("Um erro aconteceu ao gerar o token.");
                }

            }
        };

        Intent intent = new Intent(this, RegistroIntentServices.class);
        startService(intent);


        editNotification = (EditText) findViewById(R.id.edit_notification);
        editProgramarNotification = (EditText) findViewById(R.id.edit_programar_notification);
        buttonNotification = (Button) findViewById(R.id.button_notification);
        buttonProgramarNotification = (Button) findViewById(R.id.button_programar_notification);

        buttonNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Método que verifica se é uma versão acima do Oreo.
                createNotificationChannel();

                //Intent com putExtra pra mandar o valor do campo digitado para a outra activity.
                Intent intent = new Intent(MainActivity3.this, NotificationActivity.class);
                intent.putExtra("MENSAGEM", editNotification.getText().toString());

                //PendingIntent serve para criar um token da notificação para que ela seja executada.
                PendingIntent pi = PendingIntent.getActivity(getBaseContext(), 001,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //Montando a notificação.
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity3.this, "Personal Notification");
                builder.setSmallIcon(R.drawable.ic_menu_gallery);
                builder.setContentTitle("Mensagem");
                builder.setContentText(editNotification.getText().toString());
                builder.setContentIntent(pi);

                //Gerenciador de notificações. que vai ser responsável por exibir a notificação pro usuário.
                NotificationManagerCompat nmc = NotificationManagerCompat.from(MainActivity3.this);
                nmc.notify(001, builder.build());

            }
        });



        buttonProgramarNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Criando uma instancia da classe ComponentName pra dizer qual é o nome do serviço, chamando a classe ServicoAlarme.
                ComponentName serviceName = new ComponentName(MainActivity3.this, ServicoAlarme.class);

                //Montando as informações do Job.
                JobInfo jobInfo = new JobInfo.Builder(0, serviceName)
                        .setMinimumLatency(Integer.parseInt(editProgramarNotification.getText().toString()))
                        .build();

                //Agendando o Job.
                JobScheduler scheduler = (JobScheduler) getBaseContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
                int result = scheduler.schedule(jobInfo);

                if(result == JobScheduler.RESULT_SUCCESS){
                    Log.d("MainActivity3", "Serviço agendadíssimo!");
                }

            }
        });

    }

    //Método que verifica se é uma versão acima do Oreo.
    public void createNotificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name = "Personal Notification";
            String description = "Include all the personal notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("Personal Notification", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,
                new IntentFilter("registrationComplete"));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        super.onPause();
    }
}
