package tarotato.volumesetter;

import android.content.Context;
import android.media.AudioManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager  = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setContentView(R.layout.activity_main);
        initiate();
    }

    private void initiate(){
        try{
            volumeSeekbar = (SeekBar)findViewById(R.id.seekBar);
            audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setToDefaultVolume(View view){ volumeSeekbar.setProgress(3); }

    public void mute(View view){
        volumeSeekbar.setProgress(0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent){
        if(keyCode == keyEvent.KEYCODE_VOLUME_UP){
            int i = volumeSeekbar.getProgress();
            if(i != 100){
                volumeSeekbar.setProgress(i+1);
            }
            return true;
        }
        if(keyCode == keyEvent.KEYCODE_VOLUME_DOWN){
            int i = volumeSeekbar.getProgress();
            if(i != 0){
                volumeSeekbar.setProgress(i-1);
            }
            return true;
        }
        return super.onKeyDown(keyCode, keyEvent);
    }

}
