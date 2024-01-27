package MusicFiles;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class PlaySound {

    public static void getSound(Clip ruiSoundClip, Clip akazaSoundClip, Clip muzanSoundClip, FloatControl ruiVolume,
            FloatControl akazaVolume, FloatControl muzanVolume, Clip startMenuClip,
            FloatControl startMenuVolume, Clip gameOverClip, FloatControl gameOverVolume,
            Clip youWinClip, FloatControl youWinVolume, String choice) {
        // Take a choice from the user action and then play a song according to it
        switch (choice.toUpperCase()) {
            case "S" -> {
                youWinClip.stop();
                gameOverClip.stop();
                startMenuClip.stop();
                ruiSoundClip.stop();
                akazaSoundClip.stop();
                muzanSoundClip.stop();
                startMenuClip.start();
                startMenuVolume.setValue(-25.0f);
                startMenuClip.loop(Clip.LOOP_CONTINUOUSLY);
                startMenuClip.start();
            }
            case "R" -> {
                youWinClip.stop();
                gameOverClip.stop();
                startMenuClip.stop();
                ruiSoundClip.stop();
                akazaSoundClip.stop();
                muzanSoundClip.stop();
                ruiSoundClip.setMicrosecondPosition(0);
                ruiSoundClip.start();
                ruiVolume.setValue(-25.0f);
                ruiSoundClip.loop(Clip.LOOP_CONTINUOUSLY);
                ruiSoundClip.start();
            }
            case "A" -> {
                youWinClip.stop();
                gameOverClip.stop();
                startMenuClip.stop();
                ruiSoundClip.stop();
                akazaSoundClip.stop();
                muzanSoundClip.stop();
                akazaSoundClip.setMicrosecondPosition(0);
                akazaSoundClip.start();
                akazaVolume.setValue(-25.0f);
                akazaSoundClip.loop(Clip.LOOP_CONTINUOUSLY);
                akazaSoundClip.start();
            }
            case "M" -> {
                youWinClip.stop();
                gameOverClip.stop();
                startMenuClip.stop();
                ruiSoundClip.stop();
                akazaSoundClip.stop();
                muzanSoundClip.stop();
                muzanSoundClip.setMicrosecondPosition(0);
                muzanSoundClip.start();
                muzanVolume.setValue(-25.0f);
                muzanSoundClip.loop(Clip.LOOP_CONTINUOUSLY);
                muzanSoundClip.start();
            }
            case "B" -> {
                youWinClip.stop();
                gameOverClip.stop();
                ruiSoundClip.stop();
                akazaSoundClip.stop();
                muzanSoundClip.stop();
                startMenuClip.setMicrosecondPosition(0);
                startMenuVolume.setValue(-25.0f);
                startMenuClip.start();
            }
            case "B2" -> {
                youWinClip.stop();
                gameOverClip.stop();
                ruiSoundClip.stop();
                akazaSoundClip.stop();
                muzanSoundClip.stop();
                startMenuClip.start();
                startMenuVolume.setValue(-25.0f);
            }
            case "STOP" -> {
                youWinClip.stop();
                gameOverClip.stop();
                ruiSoundClip.stop();
                akazaSoundClip.stop();
                muzanSoundClip.stop();
                gameOverVolume.setValue(-20.0f);
                gameOverClip.start();
                gameOverClip.setMicrosecondPosition(0);
            }
            case "YOU WIN" -> {
                youWinClip.stop();
                gameOverClip.stop();
                ruiSoundClip.stop();
                akazaSoundClip.stop();
                muzanSoundClip.stop();
                youWinVolume.setValue(-20.0f);
                youWinClip.start();
                youWinClip.setMicrosecondPosition(0);
            }
        }
    }
}
