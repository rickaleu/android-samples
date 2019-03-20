package br.com.ricardo.androidsamples;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PermissaoActivity extends AppCompatActivity {

    private Button buttonDados;
    private Button buttonCamera;

    private static final int PERMISSION_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissao);

        buttonDados = (Button) findViewById(R.id.button_dados);
        buttonDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPermission(PermissaoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        });

        buttonCamera = (Button) findViewById(R.id.button_camera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPermission(PermissaoActivity.this, Manifest.permission.CAMERA);
            }
        });
    }


    public void checkPermission(final Context context, final String permissao){

        //Checa se já tem a premissão dada.
        if(ContextCompat.checkSelfPermission(context, permissao) != PackageManager.PERMISSION_GRANTED){

            //Caso seja a 2º vez, explica com um dialog porque é preciso esta permissão.
            if(ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permissao)){

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Aviso");
                alert.setMessage("Precisamos da permissão " + permissao + " para o correto funcionamento do aplicativo.");
                alert.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Caso o usuário entenda e dê a permissão, chama novamente o dialog de permissão.
                        ActivityCompat.requestPermissions((Activity) context, new String[]{permissao}, PERMISSION_REQUEST);
                    }
                });
                alert.show();

            } else {
                //caso contrário, se for a 1º vez, chama o dialog de permissão.
                ActivityCompat.requestPermissions((Activity) context, new String[]{permissao}, PERMISSION_REQUEST);
            }
        } else {
            //Chama o dialog de permissão pela 1º vez.
            ActivityCompat.requestPermissions((Activity) context, new String[]{permissao}, PERMISSION_REQUEST);
        }


    }


    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == PERMISSION_REQUEST){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if(permissions[0].equalsIgnoreCase(Manifest.permission.READ_EXTERNAL_STORAGE)){
                    Toast.makeText(this, "Acesso ao dados internos permitido", Toast.LENGTH_SHORT).show();

                } else if(permissions[0].equalsIgnoreCase(Manifest.permission.CAMERA)){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent);
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
