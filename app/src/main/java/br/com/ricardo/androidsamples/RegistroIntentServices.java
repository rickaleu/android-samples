package br.com.ricardo.androidsamples;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

public class RegistroIntentServices extends IntentService {

    private static final String TAG = "RegistroIntentService";
    private static final String[] TOPICS = {"global"};

    public RegistroIntentServices(){
        super(TAG);

    }

    //Esta classe irá fazer o registro do serviço que vai permitir o envio da mensagem.
    @Override
    protected void onHandleIntent(Intent intent) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try{
            synchronized (TAG){

                InstanceID instanceID = InstanceID.getInstance(this);
                String token = instanceID.getToken("10819068508", GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                Log.i(TAG, "Registro do ID: " + token);

                sendRegistrationToServer(token);
                subscribeTopics(token);
                sharedPreferences.edit().putBoolean("enviado", true).apply();
            }

        }catch (Exception e){
            Log.d(TAG, "Falha na geração do token", e);
            sharedPreferences.edit().putBoolean("enviado", false).apply();
        }

        Intent registrationComplete = new Intent("registrationComplete");
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);

    }

    private void sendRegistrationToServer(String token){
        Log.d(TAG, "Aqui está sendo feito o regirstro do token: " + token);
    }

    private void subscribeTopics(String token) throws IOException {

        for(String topic : TOPICS){

            GcmPubSub pubSub = GcmPubSub.getInstance(this);
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
}
