package me.smmizan.texttospeechapp;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    EditText editText;
    int status;
    String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.SUCCESS)
                {
                    status = textToSpeech.setLanguage(Locale.US);
                }else {
                    Toast.makeText(MainActivity.this, "Your Device is not Supported this features", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }




    public void bTextToSpeak (View view)
    {
        switch (view.getId())
        {
            case R.id.bSpeakStart:
                if (status == TextToSpeech.LANG_MISSING_DATA || status == TextToSpeech.LANG_NOT_SUPPORTED)
                {
                    Toast.makeText(MainActivity.this, "Your Device is not Supported this features", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    string = editText.getText().toString();
                    textToSpeech.speak(string,TextToSpeech.QUEUE_FLUSH,null);
                    textToSpeech.setPitch(2);
                }
                break;
            case R.id.bSpeakStop:
                if (textToSpeech!=null)
                {
                    textToSpeech.stop();
                }


                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(textToSpeech!=null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }



}
