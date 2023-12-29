package Controller;

import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.*;
import java.io.*;

@Slf4j
public class MusicPlayer {
    private final InputStream inputStream;
    private volatile boolean isLoop = false;
    private volatile boolean isPlaying;
    private float newVolume = 7;
    private PlayThread playThread;

    public MusicPlayer(InputStream inputStream) {
       this.inputStream=inputStream;
    }

    public void play() {
        if (playThread == null) {
            playThread = new PlayThread();
            playThread.start();
        }
    }

    public void over() {
        isPlaying = false;
        if (playThread != null) {
            playThread = null;
        }
    }

    public MusicPlayer setLoop(boolean isLoop) {
        this.isLoop = isLoop;
        return this;
    }

    public MusicPlayer setVolume(float newVolume) {
        this.newVolume = newVolume;
        return this;
    }

    protected class PlayThread extends Thread {
        @Override
        public void run() {
            isPlaying = true;
            do {
                SourceDataLine sourceDataLine = null;
                try (BufferedInputStream bufIn = new BufferedInputStream(inputStream); AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufIn)) {
                    AudioFormat format = audioIn.getFormat();
                    sourceDataLine = AudioSystem.getSourceDataLine(format);
                    sourceDataLine.open();
                    if (newVolume != 7) {
                        FloatControl control = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
                        control.setValue(newVolume);
                    }
                    sourceDataLine.start();
                    byte[] buf = new byte[512];
                    int len = -1;
                    while (isPlaying && (len = audioIn.read(buf)) != -1) {
                        sourceDataLine.write(buf, 0, len);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                } finally {
                    if (sourceDataLine != null) {
                        sourceDataLine.drain();
                        sourceDataLine.close();
                    }
                }
            } while (isPlaying && isLoop);
        }
    }


}
