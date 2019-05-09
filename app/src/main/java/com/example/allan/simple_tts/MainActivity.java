package com.example.allan.simple_tts;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech tts;
    int langStat;
    EditText entry;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entry = (EditText)findViewById(R.id.editText);

        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int status)
            {
                if(status == TextToSpeech.SUCCESS)
                    langStat = tts.setLanguage(Locale.UK);

                else
                    Toast.makeText(getApplicationContext(), "Device does not support Text To Speech", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void buttonSpeakClick(View v)
    {
        if(langStat == TextToSpeech.LANG_NOT_SUPPORTED || langStat == TextToSpeech.LANG_MISSING_DATA)
            Toast.makeText(getApplicationContext(), "Device does not support Text To Speech", Toast.LENGTH_LONG).show();

        else{
            text = entry.getText().toString();
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void buttonStopClick(View v)
    {
        if(tts != null)
            tts.stop();
    }

    @Override
    protected void onDestroy() {
        // TODO: Implement this method
        super.onDestroy();

        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}


