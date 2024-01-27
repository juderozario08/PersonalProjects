import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Enemies.Akaza;
import Enemies.Muzan;
import Enemies.Rui;
import MusicFiles.PlaySound;
import Slayers.Inosuke;
import Slayers.Kamado;
import Slayers.Zenitsu;

public class DemonSlayerMain {
    static Rui rui = new Rui();
    static Akaza akaza = new Akaza();
    static Muzan muzan = new Muzan();
    static Inosuke inosuke = new Inosuke("Inosuke Hashibara=", 100, 17, 8);
    static Kamado kamado = new Kamado("Kamado Tanjiro", 100, 15, 10);
    static Zenitsu zenitsu = new Zenitsu("Zenitsu Agatsuma", 100, 12, 7);
    static int heals = 2;
    public static String choice;

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Clip startMenuClip = AudioSystem.getClip(), ruiSoundClip = AudioSystem.getClip(),
                akazaSoundClip = AudioSystem.getClip();
        Clip muzanSoundClip = AudioSystem.getClip(), gameOverClip = AudioSystem.getClip(),
                youWinClip = AudioSystem.getClip();
        startMenuClip.open(AudioSystem.getAudioInputStream(new File("MusicFiles/Homura.wav")));
        ruiSoundClip.open(AudioSystem.getAudioInputStream(new File("MusicFiles/Gurenge.wav")));
        akazaSoundClip.open(AudioSystem.getAudioInputStream(new File("MusicFiles/AkazaTheme.wav")));
        muzanSoundClip.open(AudioSystem.getAudioInputStream(new File("MusicFiles/TanjiroTheme.wav")));
        gameOverClip.open(AudioSystem.getAudioInputStream(new File("MusicFiles/gameOverSound.wav")));
        youWinClip.open(AudioSystem.getAudioInputStream(new File("MusicFiles/youWin.wav")));
        FloatControl startMenuVolume = (FloatControl) startMenuClip.getControl(FloatControl.Type.MASTER_GAIN);
        FloatControl ruiVolume = (FloatControl) ruiSoundClip.getControl(FloatControl.Type.MASTER_GAIN);
        FloatControl akazaVolume = (FloatControl) akazaSoundClip.getControl(FloatControl.Type.MASTER_GAIN);
        FloatControl muzanVolume = (FloatControl) muzanSoundClip.getControl(FloatControl.Type.MASTER_GAIN);
        FloatControl gameOverVolume = (FloatControl) gameOverClip.getControl(FloatControl.Type.MASTER_GAIN);
        FloatControl youWinVolume = (FloatControl) youWinClip.getControl(FloatControl.Type.MASTER_GAIN);

        // Create a frame from the MyFrame class
        choice = "S";
        new MyFrame(ruiSoundClip, akazaSoundClip, muzanSoundClip, ruiVolume,
                akazaVolume, muzanVolume, startMenuClip,
                startMenuVolume, gameOverClip, gameOverVolume, youWinClip, youWinVolume);
        try {
            PlaySound.getSound(ruiSoundClip, akazaSoundClip, muzanSoundClip, ruiVolume,
                    akazaVolume, muzanVolume,
                    startMenuClip, startMenuVolume, gameOverClip, gameOverVolume, youWinClip,
                    youWinVolume, choice);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
