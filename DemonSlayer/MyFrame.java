import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import MusicFiles.PlaySound;

class MyFrame extends JFrame implements ActionListener {

    // Initialize the music variables in this class and define them in the construct
    Clip startMenuClip;
    Clip ruiSoundClip;
    Clip akazaSoundClip;
    Clip muzanSoundClip;
    Clip gameOverClip;
    Clip youWinClip;
    FloatControl startMenuVolume;
    FloatControl ruiVolume;
    FloatControl youWinVolume;
    FloatControl akazaVolume;
    FloatControl muzanVolume;
    FloatControl gameOverVolume;

    // Initialize all buttons
    JButton play;
    JButton credits;
    JButton quit;
    JButton back;
    JButton back2;
    JButton ruiButton;
    JButton akazaButton;
    JButton muzanButton;
    JButton kamadoCharacterButton;
    JButton zenitsuCharacterButton;
    JButton inosukeCharacterButton;
    JButton attackKamadoButton;
    JButton attackZenitsuButton;
    JButton attackInosukeButton;
    JButton evadeKamadoButton;
    JButton evadeZenitsuButton;
    JButton evadeInosukeButton;
    JButton healKamado;
    JButton healZenitsu;
    JButton healInosuke;
    JButton characterChoiceButton;

    // Initialize a panel
    JPanel panel;

    // Initialize all labels
    JLabel startBackground;
    JLabel title;
    JLabel gameOver;
    JLabel youWin;
    JLabel ruiBackground;
    JLabel akazaBackground;
    JLabel muzanBackground;
    JLabel ruiBody;
    JLabel akazaBody;
    JLabel muzanBody;
    JLabel kamadoBody;
    JLabel zenitsuBody;
    JLabel inosukeBody;
    JLabel healthKamadoLabel;
    JLabel healthZenitsuLabel;
    JLabel healthInosukeLabel;
    JLabel healthRuiLabel;
    JLabel healthAkazaLabel;
    JLabel healthMuzanLabel;
    JLabel musicCredits;
    JLabel characterSelect;

    // Get all images and resize them according to their desired width and height
    ImageIcon mainIcon = new ImageIcon("Images/icon.png");
    ImageIcon characterChoiceBackground = new ImageIcon("Images/characterChoiceButton.png");
    ImageIcon akazaImageBackground = new ImageIcon("Images/akazaBackground.png");
    ImageIcon muzanImageBackground = new ImageIcon("Images/muzanBackground.png");
    ImageIcon ruiImageBackground = new ImageIcon("Images/ruiBackground.png");
    ImageIcon thunderBackground = new ImageIcon("Images/thunderStrike.png");
    ImageIcon fireBackground = new ImageIcon("Images/fireBackground.png");
    ImageIcon waterBackground = new ImageIcon("Images/waterStrike.png");
    ImageIcon inosukeBodyImage = new ImageIcon("Images/inosuke.png");
    ImageIcon zenitsuBodyImage = new ImageIcon("Images/zenitsu.png");
    ImageIcon kamadoBodyImage = new ImageIcon("Images/tanjiro.png");
    ImageIcon GameOverIcon = new ImageIcon("Images/gameover.png");
    ImageIcon akazaBodyImage = new ImageIcon("Images/akaza.png");
    ImageIcon muzanBodyImage = new ImageIcon("Images/muzan.png");
    ImageIcon CreditsIcon = new ImageIcon("Images/credits.png");
    ImageIcon YouWinIcon = new ImageIcon("Images/youWin.png");
    ImageIcon menuIcon = new ImageIcon("Images/menuBack.jpg");
    ImageIcon ruiBodyImage = new ImageIcon("Images/rui.png");
    ImageIcon icon = new ImageIcon("Images/logo.png");
    Image iconImage = icon.getImage();
    Image menuBackground = menuIcon.getImage();
    ImageIcon resizeIcon = new ImageIcon(iconImage.getScaledInstance(300, 150, Image.SCALE_AREA_AVERAGING));
    ImageIcon menuBack = new ImageIcon(menuBackground.getScaledInstance(800, 600, Image.SCALE_AREA_AVERAGING));

    // Health bar values
    int healthWidthKamado = 200;
    int healthWidthZenitsu = 200;
    int healthWidthInosuke = 200;
    int healthWidthRui = 250;
    int healthWidthAkaza = 280;
    int healthWidthMuzan = 300;
    int healthXRui = 550;
    int healthXAkaza = 520;
    int healthXMuzan = 500;
    int damageAbsorbed;
    String boss = "";
    String slayer = "";

    // Timer class was used to add a delay between functions
    Timer timer = new Timer();

    MyFrame(Clip ruiSoundClip, Clip akazaSoundClip, Clip muzanSoundClip, FloatControl ruiVolume,
            FloatControl akazaVolume, FloatControl muzanVolume,
            Clip startMenuClip, FloatControl startMenuVolume, Clip gameOverClip, FloatControl gameOverVolume,
            Clip youWinClip, FloatControl youWinVolume) {

        // Set all values from the main function
        this.startMenuClip = startMenuClip;
        this.ruiSoundClip = ruiSoundClip;
        this.akazaSoundClip = akazaSoundClip;
        this.muzanSoundClip = muzanSoundClip;
        this.startMenuVolume = startMenuVolume;
        this.ruiVolume = ruiVolume;
        this.akazaVolume = akazaVolume;
        this.muzanVolume = muzanVolume;
        this.gameOverClip = gameOverClip;
        this.gameOverVolume = gameOverVolume;
        this.youWinClip = youWinClip;
        this.youWinVolume = youWinVolume;

        setAllButtonsAndLabels();

        // Add all labels and buttons to the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(800, 600);
        this.setTitle("Demon Slayer");
        this.setIconImage(mainIcon.getImage());
        this.setVisible(true);
        this.add(title);
        this.add(play);
        this.add(credits);
        this.add(quit);
        this.add(back);
        this.add(back2);
        this.add(characterChoiceButton);
        this.add(gameOver);
        this.add(youWin);
        this.add(ruiButton);
        this.add(akazaButton);
        this.add(muzanButton);
        this.add(musicCredits);
        this.add(panel);
        this.add(startBackground);
        this.add(kamadoCharacterButton);
        this.add(zenitsuCharacterButton);
        this.add(inosukeCharacterButton);
        this.add(ruiBody);
        this.add(akazaBody);
        this.add(muzanBody);
        this.add(kamadoBody);
        this.add(zenitsuBody);
        this.add(inosukeBody);
        this.add(attackKamadoButton);
        this.add(attackZenitsuButton);
        this.add(attackInosukeButton);
        this.add(evadeKamadoButton);
        this.add(evadeZenitsuButton);
        this.add(evadeInosukeButton);
        this.add(healKamado);
        this.add(healZenitsu);
        this.add(healInosuke);
        this.add(healthKamadoLabel);
        this.add(healthZenitsuLabel);
        this.add(healthInosukeLabel);
        this.add(healthRuiLabel);
        this.add(healthAkazaLabel);
        this.add(healthMuzanLabel);
        this.add(ruiBackground);
        this.add(akazaBackground);
        this.add(muzanBackground);
    }

    private void setAllButtonsAndLabels() {
        // Define all buttons and labels
        startBackground = new JLabel();
        startBackground.setIcon(menuBack);
        startBackground.setBounds(0, 0, 800, 600);
        startBackground.setVisible(true);

        title = new JLabel();
        title.setBounds(250, 0, 300, 150);
        title.setVisible(true);
        title.setIcon(resizeIcon);

        play = new JButton("Play");
        play.setBounds(250, 200, 300, 50);
        play.addActionListener(this);
        play.setHorizontalTextPosition(JButton.CENTER);
        play.setVerticalTextPosition(JButton.CENTER);
        play.setFocusable(false);
        play.setBackground(Color.BLACK);
        // play.setForeground(Color.WHITE);

        credits = new JButton("Credits");
        credits.setBounds(250, 300, 300, 50);
        credits.addActionListener(this);
        credits.setHorizontalTextPosition(JButton.CENTER);
        credits.setVerticalTextPosition(JButton.CENTER);
        credits.setFocusable(false);
        credits.setBackground(Color.BLACK);
        // credits.setForeground(Color.WHITE);

        quit = new JButton("Quit");
        quit.setBounds(250, 400, 300, 50);
        quit.addActionListener(this);
        quit.setHorizontalTextPosition(JButton.CENTER);
        quit.setVerticalTextPosition(JButton.CENTER);
        quit.setFocusable(false);
        quit.setBackground(Color.BLACK);
        // quit.setForeground(Color.WHITE);

        back = new JButton("Back");
        back.setBounds(0, 0, 150, 50);
        back.addActionListener(this);
        back.setHorizontalTextPosition(JButton.CENTER);
        back.setVerticalTextPosition(JButton.CENTER);
        back.setFocusable(false);
        back.setVisible(false);
        back.setBackground(Color.BLACK);
        // back.setForeground(Color.WHITE);

        back2 = new JButton("Back");
        back2.setBounds(0, 0, 150, 50);
        back2.addActionListener(this);
        back2.setHorizontalTextPosition(JButton.CENTER);
        back2.setVerticalTextPosition(JButton.CENTER);
        back2.setFocusable(false);
        back2.setVisible(false);
        back2.setBackground(Color.BLACK);
        // back2.setForeground(Color.WHITE);

        characterSelect = new JLabel();
        characterSelect.setOpaque(true);
        characterSelect.setText("Character Selection");
        characterSelect.setVisible(true);
        // characterSelect.setForeground(Color.WHITE);
        characterSelect.setBackground(Color.BLACK);
        characterSelect.setFont(new Font("Comin Sans", Font.PLAIN, 25));
        characterSelect.setHorizontalTextPosition(JLabel.CENTER);
        characterSelect.setVerticalTextPosition(JLabel.CENTER);

        panel = new JPanel();
        panel.setBounds(250, 0, 300, 50);
        panel.setBackground(Color.BLACK);
        panel.add(characterSelect);
        panel.setVisible(false);

        gameOver = new JLabel();
        gameOver.setIcon(GameOverIcon);
        gameOver.setBounds(0, 0, 800, 600);
        gameOver.setVisible(false);

        youWin = new JLabel();
        youWin.setIcon(YouWinIcon);
        youWin.setBounds(0, 0, 800, 600);
        youWin.setVisible(false);

        characterChoiceButton = new JButton("Character Choice");
        characterChoiceButton.setVisible(false);
        characterChoiceButton.setBounds(600, 0, 200, 50);
        characterChoiceButton.setIcon(characterChoiceBackground);
        characterChoiceButton.addActionListener(this);
        characterChoiceButton.setHorizontalTextPosition(JButton.CENTER);
        characterChoiceButton.setVerticalTextPosition(JButton.CENTER);
        characterChoiceButton.setFocusable(false);
        characterChoiceButton.setBackground(Color.BLACK);
        characterChoiceButton.setForeground(Color.WHITE);

        ruiButton = new JButton("Rui");
        ruiButton.setVisible(false);
        ruiButton.setBounds(250, 150, 300, 50);
        ruiButton.addActionListener(this);
        ruiButton.setHorizontalTextPosition(JButton.CENTER);
        ruiButton.setVerticalTextPosition(JButton.CENTER);
        ruiButton.setFocusable(false);
        ruiButton.setBackground(Color.BLACK);
        // ruiButton.setForeground(Color.WHITE);

        akazaButton = new JButton("Akaza");
        akazaButton.setVisible(false);
        akazaButton.setBounds(250, 250, 300, 50);
        akazaButton.addActionListener(this);
        akazaButton.setHorizontalTextPosition(JButton.CENTER);
        akazaButton.setVerticalTextPosition(JButton.CENTER);
        akazaButton.setFocusable(false);
        akazaButton.setBackground(Color.BLACK);
        // akazaButton.setForeground(Color.WHITE);

        muzanButton = new JButton("Muzan");
        muzanButton.setVisible(false);
        muzanButton.setBounds(250, 350, 300, 50);
        muzanButton.addActionListener(this);
        muzanButton.setHorizontalTextPosition(JButton.CENTER);
        muzanButton.setVerticalTextPosition(JButton.CENTER);
        muzanButton.setFocusable(false);
        muzanButton.setBackground(Color.BLACK);
        // muzanButton.setForeground(Color.WHITE);

        ruiBackground = new JLabel();
        ruiBackground.setIcon(ruiImageBackground);
        ruiBackground.setBounds(0, 0, 800, 600);
        ruiBackground.setVisible(false);

        akazaBackground = new JLabel();
        akazaBackground.setIcon(akazaImageBackground);
        akazaBackground.setBounds(0, 0, 800, 600);
        akazaBackground.setVisible(false);

        muzanBackground = new JLabel();
        muzanBackground.setIcon(muzanImageBackground);
        muzanBackground.setBounds(0, 0, 800, 600);
        muzanBackground.setVisible(false);

        musicCredits = new JLabel();
        musicCredits.setIcon(CreditsIcon);
        musicCredits.setBounds(0, -20, 800, 600);
        musicCredits.setVisible(false);

        ruiBody = new JLabel();
        ruiBody.setIcon(ruiBodyImage);
        ruiBody.setBounds(0, 0, 800, 600);
        ruiBody.setVisible(false);

        akazaBody = new JLabel();
        akazaBody.setIcon(akazaBodyImage);
        akazaBody.setBounds(0, 0, 800, 600);
        akazaBody.setVisible(false);

        muzanBody = new JLabel();
        muzanBody.setIcon(muzanBodyImage);
        muzanBody.setBounds(-15, 0, 800, 600);
        muzanBody.setVisible(false);

        kamadoBody = new JLabel();
        kamadoBody.setIcon(kamadoBodyImage);
        kamadoBody.setBounds(0, 0, 800, 600);
        kamadoBody.setVisible(false);

        zenitsuBody = new JLabel();
        zenitsuBody.setIcon(zenitsuBodyImage);
        zenitsuBody.setBounds(0, 0, 800, 600);
        zenitsuBody.setVisible(false);

        inosukeBody = new JLabel();
        inosukeBody.setIcon(inosukeBodyImage);
        inosukeBody.setBounds(0, 0, 800, 600);
        inosukeBody.setVisible(false);

        healthKamadoLabel = new JLabel();
        healthKamadoLabel.setBackground(new Color(0x007500));
        healthKamadoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        healthKamadoLabel.setBounds(20, 70, 200, 30);
        healthKamadoLabel.setVisible(false);
        healthKamadoLabel.setOpaque(true);

        healthZenitsuLabel = new JLabel();
        healthZenitsuLabel.setBackground(new Color(0x007500));
        healthZenitsuLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        healthZenitsuLabel.setBounds(20, 70, 200, 30);
        healthZenitsuLabel.setVisible(false);
        healthZenitsuLabel.setOpaque(true);

        healthInosukeLabel = new JLabel();
        healthInosukeLabel.setBackground(new Color(0x007500));
        healthInosukeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        healthInosukeLabel.setBounds(20, 70, 200, 30);
        healthInosukeLabel.setVisible(false);
        healthInosukeLabel.setOpaque(true);

        healthRuiLabel = new JLabel();
        healthRuiLabel.setBackground(new Color(0x750000));
        healthRuiLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        healthRuiLabel.setBounds(healthXRui, 70, healthWidthRui, 30);
        healthRuiLabel.setVisible(false);
        healthRuiLabel.setOpaque(true);

        healthAkazaLabel = new JLabel();
        healthAkazaLabel.setBackground(new Color(0x750000));
        healthAkazaLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        healthAkazaLabel.setBounds(healthXAkaza, 70, healthWidthAkaza, 30);
        healthAkazaLabel.setVisible(false);
        healthAkazaLabel.setOpaque(true);

        healthMuzanLabel = new JLabel();
        healthMuzanLabel.setBackground(new Color(0x750000));
        healthMuzanLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        healthMuzanLabel.setBounds(healthXMuzan, 70, healthWidthMuzan, 30);
        healthMuzanLabel.setVisible(false);
        healthMuzanLabel.setOpaque(true);

        attackKamadoButton = new JButton("Fire Strike");
        attackKamadoButton.setVisible(false);
        attackKamadoButton.setBounds(0, 510, 200, 50);
        attackKamadoButton.addActionListener(this);
        attackKamadoButton.setHorizontalTextPosition(JButton.CENTER);
        attackKamadoButton.setVerticalTextPosition(JButton.CENTER);
        attackKamadoButton.setFocusable(false);
        attackKamadoButton.setIcon(fireBackground);
        attackKamadoButton.setForeground(Color.WHITE);

        attackZenitsuButton = new JButton("Thunder Strike");
        attackZenitsuButton.setVisible(false);
        attackZenitsuButton.setBounds(0, 510, 200, 50);
        attackZenitsuButton.addActionListener(this);
        attackZenitsuButton.setHorizontalTextPosition(JButton.CENTER);
        attackZenitsuButton.setVerticalTextPosition(JButton.CENTER);
        attackZenitsuButton.setFocusable(false);
        attackZenitsuButton.setIcon(thunderBackground);
        attackZenitsuButton.setForeground(Color.YELLOW);

        attackInosukeButton = new JButton("Water Strike");
        attackInosukeButton.setVisible(false);
        attackInosukeButton.setBounds(0, 510, 200, 50);
        attackInosukeButton.addActionListener(this);
        attackInosukeButton.setHorizontalTextPosition(JButton.CENTER);
        attackInosukeButton.setVerticalTextPosition(JButton.CENTER);
        attackInosukeButton.setFocusable(false);
        attackInosukeButton.setIcon(waterBackground);
        attackInosukeButton.setForeground(Color.WHITE);

        kamadoCharacterButton = new JButton("Kamado");
        kamadoCharacterButton.setVisible(false);
        kamadoCharacterButton.setBounds(70, 510, 200, 50);
        kamadoCharacterButton.addActionListener(this);
        kamadoCharacterButton.setHorizontalTextPosition(JButton.CENTER);
        kamadoCharacterButton.setVerticalTextPosition(JButton.CENTER);
        kamadoCharacterButton.setFocusable(false);
        kamadoCharacterButton.setIcon(fireBackground);
        kamadoCharacterButton.setForeground(Color.WHITE);

        zenitsuCharacterButton = new JButton("Zenitsu");
        zenitsuCharacterButton.setVisible(false);
        zenitsuCharacterButton.setBounds(290, 510, 200, 50);
        zenitsuCharacterButton.addActionListener(this);
        zenitsuCharacterButton.setHorizontalTextPosition(JButton.CENTER);
        zenitsuCharacterButton.setVerticalTextPosition(JButton.CENTER);
        zenitsuCharacterButton.setFocusable(false);
        zenitsuCharacterButton.setIcon(thunderBackground);
        zenitsuCharacterButton.setForeground(Color.YELLOW);

        inosukeCharacterButton = new JButton("Inosuke");
        inosukeCharacterButton.setVisible(false);
        inosukeCharacterButton.setBounds(510, 510, 200, 50);
        inosukeCharacterButton.addActionListener(this);
        inosukeCharacterButton.setHorizontalTextPosition(JButton.CENTER);
        inosukeCharacterButton.setVerticalTextPosition(JButton.CENTER);
        inosukeCharacterButton.setFocusable(false);
        inosukeCharacterButton.setIcon(waterBackground);
        inosukeCharacterButton.setForeground(Color.WHITE);

        evadeKamadoButton = new JButton("Evade");
        evadeKamadoButton.setVisible(false);
        evadeKamadoButton.setBounds(200, 510, 200, 50);
        evadeKamadoButton.addActionListener(this);
        evadeKamadoButton.setHorizontalTextPosition(JButton.CENTER);
        evadeKamadoButton.setVerticalTextPosition(JButton.CENTER);
        evadeKamadoButton.setFocusable(false);
        evadeKamadoButton.setIcon(fireBackground);
        evadeKamadoButton.setForeground(Color.WHITE);

        evadeZenitsuButton = new JButton("Evade");
        evadeZenitsuButton.setVisible(false);
        evadeZenitsuButton.setBounds(200, 510, 200, 50);
        evadeZenitsuButton.addActionListener(this);
        evadeZenitsuButton.setHorizontalTextPosition(JButton.CENTER);
        evadeZenitsuButton.setVerticalTextPosition(JButton.CENTER);
        evadeZenitsuButton.setFocusable(false);
        evadeZenitsuButton.setIcon(thunderBackground);
        evadeZenitsuButton.setForeground(Color.YELLOW);

        evadeInosukeButton = new JButton("Evade");
        evadeInosukeButton.setVisible(false);
        evadeInosukeButton.setBounds(200, 510, 200, 50);
        evadeInosukeButton.addActionListener(this);
        evadeInosukeButton.setHorizontalTextPosition(JButton.CENTER);
        evadeInosukeButton.setVerticalTextPosition(JButton.CENTER);
        evadeInosukeButton.setFocusable(false);
        evadeInosukeButton.setIcon(waterBackground);
        evadeInosukeButton.setForeground(Color.WHITE);

        healKamado = new JButton("Heal");
        healKamado.setVisible(false);
        healKamado.setBounds(400, 510, 200, 50);
        healKamado.addActionListener(this);
        healKamado.setHorizontalTextPosition(JButton.CENTER);
        healKamado.setVerticalTextPosition(JButton.CENTER);
        healKamado.setFocusable(false);
        healKamado.setIcon(fireBackground);
        healKamado.setForeground(Color.WHITE);

        healZenitsu = new JButton("Heal");
        healZenitsu.setVisible(false);
        healZenitsu.setBounds(400, 510, 200, 50);
        healZenitsu.addActionListener(this);
        healZenitsu.setHorizontalTextPosition(JButton.CENTER);
        healZenitsu.setVerticalTextPosition(JButton.CENTER);
        healZenitsu.setFocusable(false);
        healZenitsu.setIcon(thunderBackground);
        healZenitsu.setForeground(Color.WHITE);

        healInosuke = new JButton("Heal");
        healInosuke.setVisible(false);
        healInosuke.setBounds(400, 510, 200, 50);
        healInosuke.addActionListener(this);
        healInosuke.setHorizontalTextPosition(JButton.CENTER);
        healInosuke.setVerticalTextPosition(JButton.CENTER);
        healInosuke.setFocusable(false);
        healInosuke.setIcon(waterBackground);
        healInosuke.setForeground(Color.WHITE);
    }

    @Override
    // This is a action scaner which waits for interaction with any button provided
    // and perform the action according to it.
    public void actionPerformed(ActionEvent e) {
        // Give options for all bosses
        if (e.getSource() == play) {
            hideAll();
            quit.setBounds(600, 0, 200, 50);
            quit.setVisible(true);
            back2.setVisible(true);
            startBackground.setVisible(true);
            ruiButton.setVisible(true);
            akazaButton.setVisible(true);
            muzanButton.setVisible(true);
        }
        // Show credits
        else if (e.getSource() == credits) {
            title.setVisible(false);
            play.setVisible(false);
            credits.setVisible(false);
            quit.setBounds(600, 510, 200, 50);
            back2.setVisible(true);
            musicCredits.setVisible(true);
        }
        // Go to the main menu screen with both back button
        else if (e.getSource() == back) {
            gameOver.setVisible(false);
            hideAll();
            title.setVisible(true);
            play.setVisible(true);
            credits.setVisible(true);
            quit.setVisible(true);
            startBackground.setVisible(true);
            musicCredits.setVisible(false);
            quit.setBounds(250, 400, 300, 50);
            healthWidthInosuke = 200;
            healthWidthKamado = 200;
            healthWidthZenitsu = 200;
            healthWidthRui = 250;
            healthWidthAkaza = 280;
            healthWidthMuzan = 300;
            healthXRui = 550;
            healthXAkaza = 520;
            healthXMuzan = 500;
            DemonSlayerMain.kamado.setHealth(100);
            DemonSlayerMain.zenitsu.setHealth(100);
            DemonSlayerMain.inosuke.setHealth(100);
            DemonSlayerMain.rui.healthRui = 250;
            DemonSlayerMain.akaza.akazaHealth = 280;
            DemonSlayerMain.muzan.healthMuzan = 300;
            healthRuiLabel.setBounds(healthXRui, 70, healthWidthRui, 30);
            healthAkazaLabel.setBounds(healthXAkaza, 70, healthWidthAkaza, 30);
            healthMuzanLabel.setBounds(healthXMuzan, 70, healthWidthMuzan, 30);
            enableAll();
            DemonSlayerMain.heals = 2;
            DemonSlayerMain.choice = "B";
            try {
                playMusic();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == back2) {
            hideAll();
            title.setVisible(true);
            play.setVisible(true);
            credits.setVisible(true);
            startBackground.setVisible(true);
            quit.setVisible(true);
            quit.setBounds(250, 400, 300, 50);
            DemonSlayerMain.choice = "B2";
            try {
                playMusic();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        // If rui button is clicked open character selection and show all Rui
        // functionalites
        else if (e.getSource() == ruiButton) {
            hideAll();
            quit.setBounds(600, 0, 200, 50);
            healthKamadoLabel.setBounds(20, 70, 200, 30);
            healthZenitsuLabel.setBounds(20, 70, 200, 30);
            healthInosukeLabel.setBounds(20, 70, 200, 30);
            healthRuiLabel.setBounds(healthXRui - 30, 70, healthWidthRui, 30);
            healthAkazaLabel.setBounds(healthXAkaza - 30, 70, healthWidthAkaza, 30);
            healthMuzanLabel.setBounds(healthXMuzan - 30, 70, healthWidthMuzan, 30);
            panel.setVisible(true);
            quit.setVisible(true);
            back.setVisible(true);
            ruiBackground.setVisible(true);
            kamadoCharacterButton.setVisible(true);
            zenitsuCharacterButton.setVisible(true);
            inosukeCharacterButton.setVisible(true);
            kamadoBody.setBounds(70, 0, 800, 600);
            kamadoBody.setVisible(true);
            zenitsuBody.setBounds(290, 0, 800, 600);
            zenitsuBody.setVisible(true);
            inosukeBody.setBounds(510, 0, 800, 600);
            inosukeBody.setVisible(true);
            boss = "RUI";
            DemonSlayerMain.choice = "R";
            try {
                playMusic();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        // If akaza button is clicked open character selection and show all Rui
        // functionalites
        else if (e.getSource() == akazaButton) {
            hideAll();
            quit.setBounds(600, 0, 200, 50);
            healthKamadoLabel.setBounds(20, 70, 200, 30);
            healthZenitsuLabel.setBounds(20, 70, 200, 30);
            healthInosukeLabel.setBounds(20, 70, 200, 30);
            healthRuiLabel.setBounds(healthXRui - 30, 70, healthWidthRui, 30);
            healthAkazaLabel.setBounds(healthXAkaza - 30, 70, healthWidthAkaza, 30);
            healthMuzanLabel.setBounds(healthXMuzan - 30, 70, healthWidthMuzan, 30);
            panel.setVisible(true);
            quit.setVisible(true);
            back.setVisible(true);
            akazaBackground.setVisible(true);
            kamadoCharacterButton.setVisible(true);
            zenitsuCharacterButton.setVisible(true);
            inosukeCharacterButton.setVisible(true);
            kamadoBody.setBounds(70, 0, 800, 600);
            kamadoBody.setVisible(true);
            zenitsuBody.setBounds(290, 0, 800, 600);
            zenitsuBody.setVisible(true);
            inosukeBody.setBounds(510, 0, 800, 600);
            inosukeBody.setVisible(true);
            boss = "AKAZA";
            DemonSlayerMain.choice = "A";
            try {
                playMusic();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        // If muzan button is clicked open character selection and show all Rui
        // functionalites
        else if (e.getSource() == muzanButton) {
            hideAll();
            healthKamadoLabel.setBounds(20, 70, 200, 30);
            healthZenitsuLabel.setBounds(20, 70, 200, 30);
            healthInosukeLabel.setBounds(20, 70, 200, 30);
            back.setVisible(true);
            muzanBackground.setVisible(true);
            quit.setVisible(true);
            quit.setBounds(600, 0, 200, 50);
            healthRuiLabel.setBounds(healthXRui - 30, 70, healthWidthRui, 30);
            healthAkazaLabel.setBounds(healthXAkaza - 30, 70, healthWidthAkaza, 30);
            healthMuzanLabel.setBounds(healthXMuzan - 30, 70, healthWidthMuzan, 30);
            kamadoCharacterButton.setVisible(true);
            zenitsuCharacterButton.setVisible(true);
            inosukeCharacterButton.setVisible(true);
            kamadoBody.setBounds(70, 0, 800, 600);
            kamadoBody.setVisible(true);
            zenitsuBody.setBounds(290, 0, 800, 600);
            zenitsuBody.setVisible(true);
            inosukeBody.setBounds(510, 0, 800, 600);
            inosukeBody.setVisible(true);
            panel.setVisible(true);
            boss = "MUZAN";
            DemonSlayerMain.choice = "M";
            try {
                playMusic();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        // Character choice buttons for the 3 slayers
        else if (e.getSource() == kamadoCharacterButton) {
            quit.setBounds(600, 510, 200, 50);
            kamadoBody.setBounds(0, 0, 800, 600);
            zenitsuBody.setBounds(0, 0, 800, 600);
            zenitsuBody.setVisible(false);
            inosukeBody.setBounds(0, 0, 800, 600);
            inosukeBody.setVisible(false);
            spawnKamado();
            kamadoCharacterButton.setVisible(false);
            zenitsuCharacterButton.setVisible(false);
            inosukeCharacterButton.setVisible(false);
            panel.setVisible(false);
            characterChoiceButton.setVisible(true);
            slayer = "KAMADO";
        } else if (e.getSource() == zenitsuCharacterButton) {
            quit.setBounds(600, 510, 200, 50);
            kamadoBody.setBounds(0, 0, 800, 600);
            kamadoBody.setVisible(false);
            zenitsuBody.setBounds(0, 0, 800, 600);
            inosukeBody.setBounds(0, 0, 800, 600);
            inosukeBody.setVisible(false);
            spawnZenitsu();
            kamadoCharacterButton.setVisible(false);
            zenitsuCharacterButton.setVisible(false);
            inosukeCharacterButton.setVisible(false);
            panel.setVisible(false);
            characterChoiceButton.setVisible(true);
            slayer = "ZENITSU";
        } else if (e.getSource() == inosukeCharacterButton) {
            quit.setBounds(600, 510, 200, 50);
            kamadoBody.setBounds(0, 0, 800, 600);
            kamadoBody.setVisible(false);
            zenitsuBody.setBounds(0, 0, 800, 600);
            zenitsuBody.setVisible(false);
            inosukeBody.setBounds(0, 0, 800, 600);
            spawnInosuke();
            kamadoCharacterButton.setVisible(false);
            zenitsuCharacterButton.setVisible(false);
            inosukeCharacterButton.setVisible(false);
            panel.setVisible(false);
            characterChoiceButton.setVisible(true);
            slayer = "INOSUKE";
        }

        // Functionalities for all attack buttons based on bosses
        else if (e.getSource() == attackKamadoButton)
            kamadoAttack();
        else if (e.getSource() == attackZenitsuButton)
            zenitsuAttack();
        else if (e.getSource() == attackInosukeButton)
            inosukeAttack();

        // Character choice button can be used to switch characters in the middle of the
        // match
        else if (e.getSource() == characterChoiceButton) {
            if (boss.equals("RUI")) {
                hideAll();
                boss = "RUI";
                quit.setBounds(600, 0, 200, 50);
                healthRuiLabel.setBounds(healthXRui - 30, 70, healthWidthRui, 30);
                healthAkazaLabel.setBounds(healthXAkaza - 30, 70, healthWidthAkaza, 30);
                healthMuzanLabel.setBounds(healthXMuzan - 30, 70, healthWidthMuzan, 30);
                panel.setVisible(true);
                quit.setVisible(true);
                back.setVisible(true);
                ruiBackground.setVisible(true);
                showInosukeIf();
                showKamadoIf();
                showZenitsuIf();
            } else if (boss.equals("AKAZA")) {
                hideAll();
                boss = "AKAZA";
                quit.setBounds(600, 0, 200, 50);
                healthRuiLabel.setBounds(healthXRui - 30, 70, healthWidthRui, 30);
                healthAkazaLabel.setBounds(healthXAkaza - 30, 70, healthWidthAkaza, 30);
                healthMuzanLabel.setBounds(healthXMuzan - 30, 70, healthWidthMuzan, 30);
                panel.setVisible(true);
                quit.setVisible(true);
                back.setVisible(true);
                akazaBackground.setVisible(true);
                showInosukeIf();
                showKamadoIf();
                showZenitsuIf();
            } else if (boss.equals("MUZAN")) {
                hideAll();
                boss = "MUZAN";
                back.setVisible(true);
                muzanBackground.setVisible(true);
                panel.setVisible(true);
                quit.setVisible(true);
                quit.setBounds(600, 0, 200, 50);
                healthRuiLabel.setBounds(healthXRui - 30, 70, healthWidthRui, 30);
                healthAkazaLabel.setBounds(healthXAkaza - 30, 70, healthWidthAkaza, 30);
                healthMuzanLabel.setBounds(healthXMuzan - 30, 70, healthWidthMuzan, 30);
                showInosukeIf();
                showKamadoIf();
                showZenitsuIf();
            }
        }

        // Evade functionalities for all slayers
        else if (e.getSource() == evadeKamadoButton) {
            evadeKamado();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == evadeZenitsuButton) {
            evadeZenitsu();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == evadeInosukeButton) {
            evadeInosuke();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

        // Heal functionalities for all slayers
        else if (e.getSource() == healKamado) {
            if (DemonSlayerMain.heals == 1) {
                DemonSlayerMain.heals--;
                DemonSlayerMain.kamado.increaseHealth(30);
                JOptionPane.showMessageDialog(null,
                        "Health increased from " + (DemonSlayerMain.kamado.getHealth() - 30) + " to " +
                                DemonSlayerMain.kamado.getHealth() + "hp.\nHeals left " + DemonSlayerMain.heals);
                healKamado.setEnabled(false);
                healZenitsu.setEnabled(false);
                healInosuke.setEnabled(false);
                healthWidthKamado += 60;
                healthKamadoLabel.setBounds(20, 70, healthWidthKamado, 30);
            } else if (DemonSlayerMain.heals > 1) {
                DemonSlayerMain.heals--;
                DemonSlayerMain.kamado.increaseHealth(30);
                JOptionPane.showMessageDialog(null,
                        "Health increased from " + (DemonSlayerMain.kamado.getHealth() - 30) + " to " +
                                DemonSlayerMain.kamado.getHealth() + "hp.\nHeals left " + DemonSlayerMain.heals);
                healthWidthKamado += 60;
                healthKamadoLabel.setBounds(20, 70, healthWidthKamado, 30);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == healZenitsu) {
            if (DemonSlayerMain.heals == 1) {
                DemonSlayerMain.heals--;
                DemonSlayerMain.zenitsu.increaseHealth(30);
                JOptionPane.showMessageDialog(null,
                        "Health increased from " + (DemonSlayerMain.zenitsu.getHealth() - 30) + " to " +
                                DemonSlayerMain.zenitsu.getHealth() + "hp.\nHeals left " + DemonSlayerMain.heals);
                healKamado.setEnabled(false);
                healZenitsu.setEnabled(false);
                healInosuke.setEnabled(false);
                healthWidthZenitsu += 60;
                healthZenitsuLabel.setBounds(20, 70, healthWidthZenitsu, 30);
            } else if (DemonSlayerMain.heals > 1) {
                DemonSlayerMain.heals--;
                DemonSlayerMain.zenitsu.increaseHealth(30);
                JOptionPane.showMessageDialog(null,
                        "Health increased from " + (DemonSlayerMain.zenitsu.getHealth() - 30) + " to " +
                                DemonSlayerMain.zenitsu.getHealth() + "hp.\nHeals left " + DemonSlayerMain.heals);
                healthWidthZenitsu += 60;
                healthZenitsuLabel.setBounds(20, 70, healthWidthZenitsu, 30);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == healInosuke) {
            if (DemonSlayerMain.heals == 1) {
                DemonSlayerMain.heals--;
                DemonSlayerMain.inosuke.increaseHealth(30);
                JOptionPane.showMessageDialog(null,
                        "Health increased from " + (DemonSlayerMain.inosuke.getHealth() - 30) + " to " +
                                DemonSlayerMain.inosuke.getHealth() + "hp.\nHeals left " + DemonSlayerMain.heals);
                healKamado.setEnabled(false);
                healZenitsu.setEnabled(false);
                healInosuke.setEnabled(false);
                healthWidthInosuke += 60;
                healthInosukeLabel.setBounds(20, 70, healthWidthInosuke, 30);
            } else if (DemonSlayerMain.heals > 1) {
                DemonSlayerMain.heals--;
                DemonSlayerMain.inosuke.increaseHealth(30);
                JOptionPane.showMessageDialog(null,
                        "Health increased from " + (DemonSlayerMain.inosuke.getHealth() - 30) + " to " +
                                DemonSlayerMain.inosuke.getHealth() + "hp.\nHeals left " + DemonSlayerMain.heals);
                healthWidthInosuke += 60;
                healthInosukeLabel.setBounds(20, 70, healthWidthInosuke, 30);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

        // If quit button is clicked, close the game
        else if (e.getSource() == quit)
            System.exit(0);
    }

    // Setting character healths and healthbar
    private void hpKamadoLabel() {
        if (boss.equals("RUI")) {
            int damage = DemonSlayerMain.rui.dealDamage();
            damageAbsorbed = damage;
            DemonSlayerMain.kamado.gotHit(damage);
            healthWidthKamado -= (damage * 2);
            healthKamadoLabel.setBounds(20, 70, healthWidthKamado, 30);
        } else if (boss.equals("AKAZA")) {
            int damage = DemonSlayerMain.akaza.dealDamage();
            damageAbsorbed = damage;
            DemonSlayerMain.kamado.gotHit(damage);
            healthWidthKamado -= (damage * 2);
            healthKamadoLabel.setBounds(20, 70, healthWidthKamado, 30);
        } else if (boss.equals("MUZAN")) {
            int damage = DemonSlayerMain.muzan.dealDamage();
            damageAbsorbed = damage;
            DemonSlayerMain.kamado.gotHit(damage);
            healthWidthKamado -= (damage * 2);
            healthKamadoLabel.setBounds(20, 70, healthWidthKamado, 30);
        }
    }

    private void hpZenitsuLabel() {
        if (boss.equals("RUI")) {
            int damage = DemonSlayerMain.rui.dealDamage();
            damageAbsorbed = damage;
            DemonSlayerMain.zenitsu.gotHit(damage);
            healthWidthZenitsu -= (damage * 2);
            healthZenitsuLabel.setBounds(20, 70, healthWidthZenitsu, 30);
        } else if (boss.equals("AKAZA")) {
            int damage = DemonSlayerMain.akaza.dealDamage();
            damageAbsorbed = damage;
            DemonSlayerMain.zenitsu.gotHit(damage);
            healthWidthZenitsu -= (damage * 2);
            healthZenitsuLabel.setBounds(20, 70, healthWidthZenitsu, 30);
        } else if (boss.equals("MUZAN")) {
            int damage = DemonSlayerMain.muzan.dealDamage();
            damageAbsorbed = damage;
            DemonSlayerMain.zenitsu.gotHit(damage);
            healthWidthZenitsu -= (damage * 2);
            healthZenitsuLabel.setBounds(20, 70, healthWidthZenitsu, 30);
        }
    }

    private void hpInosukeLabel() {
        if (boss.equals("RUI")) {
            int damage = DemonSlayerMain.rui.dealDamage();
            damageAbsorbed = damage;
            DemonSlayerMain.inosuke.gotHit(damage);
            healthWidthInosuke -= (damage * 2);
            healthInosukeLabel.setBounds(20, 70, healthWidthInosuke, 30);
        } else if (boss.equals("AKAZA")) {
            int damage = DemonSlayerMain.akaza.dealDamage();
            damageAbsorbed = damage;
            DemonSlayerMain.inosuke.gotHit(damage);
            healthWidthInosuke -= (damage * 2);
            healthInosukeLabel.setBounds(20, 70, healthWidthInosuke, 30);
        } else if (boss.equals("MUZAN")) {
            int damage = DemonSlayerMain.muzan.dealDamage();
            damageAbsorbed = damage;
            DemonSlayerMain.inosuke.gotHit(damage);
            healthWidthInosuke -= (damage * 2);
            healthInosukeLabel.setBounds(20, 70, healthWidthInosuke, 30);
        }
    }

    private void hpRuiLabel() {
        if (slayer.equals("KAMADO")) {
            int takeDamage = DemonSlayerMain.kamado.dealDamage();
            DemonSlayerMain.rui.gotHit(takeDamage);
            healthWidthRui -= takeDamage;
            healthXRui += takeDamage;
            healthRuiLabel.setBounds(healthXRui - 30, 70, healthWidthRui, 30);
        } else if (slayer.equals("ZENITSU")) {
            int takeDamage = DemonSlayerMain.zenitsu.dealDamage();
            DemonSlayerMain.rui.gotHit(takeDamage);
            healthWidthRui -= takeDamage;
            healthXRui += takeDamage;
            healthRuiLabel.setBounds(healthXRui - 30, 70, healthWidthRui, 30);
        } else if (slayer.equals("INOSUKE")) {
            int takeDamage = DemonSlayerMain.inosuke.dealDamage();
            DemonSlayerMain.rui.gotHit(takeDamage);
            healthWidthRui -= takeDamage;
            healthXRui += takeDamage;
            healthRuiLabel.setBounds(healthXRui - 30, 70, healthWidthRui, 30);
        }
    }

    private void hpAkazaLabel() {
        if (slayer.equals("KAMADO")) {
            int takeDamage = DemonSlayerMain.kamado.dealDamage();
            DemonSlayerMain.akaza.gotHit(takeDamage);
            healthWidthAkaza -= takeDamage;
            healthXAkaza += takeDamage;
            healthAkazaLabel.setBounds(healthXAkaza - 30, 70, healthWidthAkaza, 30);
        } else if (slayer.equals("ZENITSU")) {
            int takeDamage = DemonSlayerMain.zenitsu.dealDamage();
            DemonSlayerMain.akaza.gotHit(takeDamage);
            healthWidthAkaza -= takeDamage;
            healthXAkaza += takeDamage;
            healthAkazaLabel.setBounds(healthXAkaza - 30, 70, healthWidthAkaza, 30);
        } else if (slayer.equals("INOSUKE")) {
            int takeDamage = DemonSlayerMain.inosuke.dealDamage();
            DemonSlayerMain.akaza.gotHit(takeDamage);
            healthWidthAkaza -= takeDamage;
            healthXAkaza += takeDamage;
            healthAkazaLabel.setBounds(healthXAkaza - 30, 70, healthWidthAkaza, 30);
        }
    }

    private void hpMuzanLabel() {
        if (slayer.equals("KAMADO")) {
            int takeDamage = (int) Math.round(DemonSlayerMain.kamado.dealDamage() * 0.70);
            DemonSlayerMain.muzan.gotHit(takeDamage);
            healthWidthMuzan -= takeDamage;
            healthXMuzan += takeDamage;
            healthMuzanLabel.setBounds(healthXMuzan - 30, 70, healthWidthMuzan, 30);
        } else if (slayer.equals("ZENITSU")) {
            int takeDamage = (int) Math.round(DemonSlayerMain.zenitsu.dealDamage() * 0.70);
            DemonSlayerMain.muzan.gotHit(takeDamage);
            healthWidthMuzan -= takeDamage;
            healthXMuzan += takeDamage;
            healthMuzanLabel.setBounds(healthXMuzan - 30, 70, healthWidthMuzan, 30);
        } else if (slayer.equals("INOSUKE")) {
            int takeDamage = (int) Math.round(DemonSlayerMain.inosuke.dealDamage() * 0.70);
            DemonSlayerMain.muzan.gotHit(takeDamage);
            healthWidthMuzan -= takeDamage;
            healthXMuzan += takeDamage;
            healthMuzanLabel.setBounds(healthXMuzan - 30, 70, healthWidthMuzan, 30);
        }
    }

    // This hides all buttons and labels that are mentioned
    private void hideAll() {
        play.setVisible(false);
        credits.setVisible(false);
        quit.setVisible(false);
        startBackground.setVisible(false);
        back.setVisible(false);
        back2.setVisible(false);
        musicCredits.setVisible(false);
        ruiButton.setVisible(false);
        akazaButton.setVisible(false);
        muzanButton.setVisible(false);
        attackKamadoButton.setVisible(false);
        attackZenitsuButton.setVisible(false);
        attackInosukeButton.setVisible(false);
        evadeKamadoButton.setVisible(false);
        evadeZenitsuButton.setVisible(false);
        evadeInosukeButton.setVisible(false);
        healKamado.setVisible(false);
        healZenitsu.setVisible(false);
        healInosuke.setVisible(false);
        healthKamadoLabel.setVisible(false);
        healthZenitsuLabel.setVisible(false);
        healthInosukeLabel.setVisible(false);
        healthRuiLabel.setVisible(false);
        healthAkazaLabel.setVisible(false);
        healthMuzanLabel.setVisible(false);
        title.setVisible(false);
        ruiBackground.setVisible(false);
        akazaBackground.setVisible(false);
        muzanBackground.setVisible(false);
        youWin.setVisible(false);
        panel.setVisible(false);
        ruiBody.setVisible(false);
        akazaBody.setVisible(false);
        muzanBody.setVisible(false);
        kamadoBody.setVisible(false);
        zenitsuBody.setVisible(false);
        inosukeBody.setVisible(false);
        characterChoiceButton.setVisible(false);
        kamadoCharacterButton.setVisible(false);
        zenitsuCharacterButton.setVisible(false);
        inosukeCharacterButton.setVisible(false);
    }

    // This enables all buttons
    private void enableAll() {
        play.setEnabled(true);
        credits.setEnabled(true);
        quit.setEnabled(true);
        back.setEnabled(true);
        back2.setEnabled(true);
        ruiButton.setEnabled(true);
        akazaButton.setEnabled(true);
        muzanButton.setEnabled(true);
        attackKamadoButton.setEnabled(true);
        attackKamadoButton.setEnabled(true);
        attackKamadoButton.setEnabled(true);
        attackZenitsuButton.setEnabled(true);
        attackZenitsuButton.setEnabled(true);
        attackZenitsuButton.setEnabled(true);
        attackZenitsuButton.setEnabled(true);
        attackZenitsuButton.setEnabled(true);
        attackZenitsuButton.setEnabled(true);
        evadeKamadoButton.setEnabled(true);
        evadeKamadoButton.setEnabled(true);
        evadeKamadoButton.setEnabled(true);
        evadeInosukeButton.setEnabled(true);
        evadeInosukeButton.setEnabled(true);
        evadeInosukeButton.setEnabled(true);
        evadeZenitsuButton.setEnabled(true);
        evadeZenitsuButton.setEnabled(true);
        evadeZenitsuButton.setEnabled(true);
        healZenitsu.setEnabled(true);
        healKamado.setEnabled(true);
        healInosuke.setEnabled(true);
        characterChoiceButton.setEnabled(true);
    }

    // Check if all Demon Slayers are already dead
    private boolean allSlayerDeath() {
        int[] slayersHealth = { DemonSlayerMain.kamado.getHealth(), DemonSlayerMain.zenitsu.getHealth(),
                DemonSlayerMain.inosuke.getHealth() };
        for (int i : slayersHealth) {
            if (i > 0)
                return false;
        }
        return true;
    }

    // Spawn Kamado and all enable all of his functionalities
    private void spawnKamado() {
        if (boss.equals("RUI")) {
            kamadoBody.setVisible(true);
            healthKamadoLabel.setVisible(true);
            attackKamadoButton.setVisible(true);
            evadeKamadoButton.setVisible(true);
            healKamado.setVisible(true);
            ruiBackground.setVisible(true);
            ruiBody.setVisible(true);
            healthRuiLabel.setVisible(true);
        } else if (boss.equals("AKAZA")) {
            kamadoBody.setVisible(true);
            healthKamadoLabel.setVisible(true);
            attackKamadoButton.setVisible(true);
            evadeKamadoButton.setVisible(true);
            healKamado.setVisible(true);
            akazaBackground.setVisible(true);
            akazaBody.setVisible(true);
            healthAkazaLabel.setVisible(true);
        } else if (boss.equals("MUZAN")) {
            kamadoBody.setVisible(true);
            healthKamadoLabel.setVisible(true);
            attackKamadoButton.setVisible(true);
            evadeKamadoButton.setVisible(true);
            healKamado.setVisible(true);
            muzanBackground.setVisible(true);
            muzanBody.setVisible(true);
            healthMuzanLabel.setVisible(true);
        }
    }

    // Spawn Zenitsu and all enable all of his functionalities
    private void spawnZenitsu() {
        if (boss.equals("RUI")) {
            zenitsuBody.setVisible(true);
            healthZenitsuLabel.setVisible(true);
            attackZenitsuButton.setVisible(true);
            evadeZenitsuButton.setVisible(true);
            healZenitsu.setVisible(true);
            ruiBackground.setVisible(true);
            ruiBody.setVisible(true);
            healthRuiLabel.setVisible(true);
        } else if (boss.equals("AKAZA")) {
            zenitsuBody.setVisible(true);
            healthZenitsuLabel.setVisible(true);
            attackZenitsuButton.setVisible(true);
            evadeZenitsuButton.setVisible(true);
            healZenitsu.setVisible(true);
            akazaBackground.setVisible(true);
            akazaBody.setVisible(true);
            healthAkazaLabel.setVisible(true);
        } else if (boss.equals("MUZAN")) {
            zenitsuBody.setVisible(true);
            healthZenitsuLabel.setVisible(true);
            attackZenitsuButton.setVisible(true);
            evadeZenitsuButton.setVisible(true);
            healZenitsu.setVisible(true);
            muzanBackground.setVisible(true);
            muzanBody.setVisible(true);
            healthMuzanLabel.setVisible(true);
        }
    }

    // Spawn Inosuke and all enable all of his functionalities
    private void spawnInosuke() {
        if (boss.equals("RUI")) {
            inosukeBody.setVisible(true);
            healthInosukeLabel.setVisible(true);
            attackInosukeButton.setVisible(true);
            evadeInosukeButton.setVisible(true);
            healInosuke.setVisible(true);
            ruiBackground.setVisible(true);
            ruiBody.setVisible(true);
            healthRuiLabel.setVisible(true);
        } else if (boss.equals("AKAZA")) {
            inosukeBody.setVisible(true);
            healthInosukeLabel.setVisible(true);
            attackInosukeButton.setVisible(true);
            evadeInosukeButton.setVisible(true);
            healInosuke.setVisible(true);
            akazaBackground.setVisible(true);
            akazaBody.setVisible(true);
            healthAkazaLabel.setVisible(true);
        } else if (boss.equals("MUZAN")) {
            inosukeBody.setVisible(true);
            healthInosukeLabel.setVisible(true);
            attackInosukeButton.setVisible(true);
            evadeInosukeButton.setVisible(true);
            healInosuke.setVisible(true);
            muzanBackground.setVisible(true);
            muzanBody.setVisible(true);
            healthMuzanLabel.setVisible(true);
        }
    }

    // Show kamado for character selection if he is alive
    private void showKamadoIf() {
        if (DemonSlayerMain.kamado.getHealth() > 0) {
            kamadoBody.setVisible(true);
            kamadoCharacterButton.setVisible(true);
            kamadoBody.setBounds(70, 0, 800, 600);
        }
    }

    // Show zenitsu for character selection if he is alive
    private void showZenitsuIf() {
        if (DemonSlayerMain.zenitsu.getHealth() > 0) {
            zenitsuBody.setBounds(290, 0, 800, 600);
            zenitsuBody.setVisible(true);
            zenitsuCharacterButton.setVisible(true);
        }
    }

    // Show inosuke for character selection if he is alive
    private void showInosukeIf() {
        if (DemonSlayerMain.inosuke.getHealth() > 0) {
            inosukeBody.setBounds(510, 0, 800, 600);
            inosukeBody.setVisible(true);
            inosukeCharacterButton.setVisible(true);
        }
    }

    private void kamadoAttack() {
        if (boss.equals("RUI")) {
            hpRuiLabel();
            attackKamadoButton.setEnabled(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hpKamadoLabel();
                    JOptionPane.showMessageDialog(null, DemonSlayerMain.kamado.printStats() + "\nDamage absorbed "
                            + damageAbsorbed + ".\n" + DemonSlayerMain.rui.toString());
                    attackKamadoButton.setEnabled(true);
                    if (DemonSlayerMain.kamado.getHealth() <= 0 && !allSlayerDeath()) {
                        boss = "RUI";
                        kamadoBody.setVisible(false);
                        healthKamadoLabel.setVisible(false);
                        attackKamadoButton.setVisible(false);
                        evadeKamadoButton.setVisible(false);
                        healKamado.setVisible(false);
                        panel.setVisible(true);
                        ruiBody.setVisible(false);
                        quit.setVisible(true);
                        quit.setBounds(600, 0, 200, 50);
                        back.setVisible(true);
                        healthRuiLabel.setVisible(false);
                        showZenitsuIf();
                        showInosukeIf();
                        showKamadoIf();
                    } else if (allSlayerDeath())
                        endGame();
                    else if (DemonSlayerMain.rui.healthRui <= 0)
                        winGame();
                }
            }, 1000);
        } else if (boss.equals("AKAZA")) {
            hpAkazaLabel();
            attackKamadoButton.setEnabled(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hpKamadoLabel();
                    JOptionPane.showMessageDialog(null, DemonSlayerMain.kamado.printStats() + "\nDamage absorbed "
                            + damageAbsorbed + "\n" + DemonSlayerMain.akaza.toString());
                    attackKamadoButton.setEnabled(true);
                    if (DemonSlayerMain.kamado.getHealth() <= 0 && !allSlayerDeath()) {
                        boss = "AKAZA";
                        kamadoBody.setVisible(false);
                        healthKamadoLabel.setVisible(false);
                        attackKamadoButton.setVisible(false);
                        evadeKamadoButton.setVisible(false);
                        healKamado.setVisible(false);
                        panel.setVisible(true);
                        akazaBody.setVisible(false);
                        quit.setBounds(600, 0, 200, 50);
                        quit.setVisible(true);
                        back.setVisible(true);
                        healthAkazaLabel.setVisible(false);
                        showZenitsuIf();
                        showInosukeIf();
                        showKamadoIf();
                    } else if (allSlayerDeath())
                        endGame();
                    else if (DemonSlayerMain.akaza.akazaHealth <= 0)
                        winGame();
                }
            }, 1000);
        } else if (boss.equals("MUZAN")) {
            hpMuzanLabel();
            attackKamadoButton.setEnabled(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hpKamadoLabel();
                    JOptionPane.showMessageDialog(null, DemonSlayerMain.kamado.printStats() + "\nDamage absorbed "
                            + damageAbsorbed + "\n" + DemonSlayerMain.muzan.toString());
                    attackKamadoButton.setEnabled(true);
                    if (DemonSlayerMain.kamado.getHealth() <= 0 && !allSlayerDeath()) {
                        boss = "MUZAN";
                        kamadoBody.setVisible(false);
                        healthKamadoLabel.setVisible(false);
                        attackKamadoButton.setVisible(false);
                        evadeKamadoButton.setVisible(false);
                        healKamado.setVisible(false);
                        panel.setVisible(true);
                        muzanBody.setVisible(false);
                        quit.setBounds(600, 0, 200, 50);
                        quit.setVisible(true);
                        back.setVisible(true);
                        healthMuzanLabel.setVisible(false);
                        showZenitsuIf();
                        showInosukeIf();
                        showKamadoIf();
                    } else if (allSlayerDeath())
                        endGame();
                    else if (DemonSlayerMain.muzan.healthMuzan <= 0)
                        winGame();
                }
            }, 1000);
        }
    }

    private void zenitsuAttack() {
        if (boss.equals("RUI")) {
            hpRuiLabel();
            attackZenitsuButton.setEnabled(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hpZenitsuLabel();
                    JOptionPane.showMessageDialog(null, DemonSlayerMain.zenitsu.printStats() + "\nDamage absorbed "
                            + damageAbsorbed + ".\n" + DemonSlayerMain.rui.toString());
                    attackZenitsuButton.setEnabled(true);
                    if (DemonSlayerMain.zenitsu.getHealth() <= 0 && !allSlayerDeath()) {
                        boss = "RUI";
                        zenitsuBody.setVisible(false);
                        healthZenitsuLabel.setVisible(false);
                        attackZenitsuButton.setVisible(false);
                        evadeZenitsuButton.setVisible(false);
                        healZenitsu.setVisible(false);
                        panel.setVisible(true);
                        ruiBody.setVisible(false);
                        quit.setVisible(true);
                        quit.setBounds(600, 0, 200, 50);
                        back.setVisible(true);
                        healthRuiLabel.setVisible(false);
                        showZenitsuIf();
                        showInosukeIf();
                        showKamadoIf();
                    } else if (allSlayerDeath())
                        endGame();
                    else if (DemonSlayerMain.rui.healthRui <= 0)
                        winGame();
                }
            }, 1000);
        } else if (boss.equals("AKAZA")) {
            hpAkazaLabel();
            attackZenitsuButton.setEnabled(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hpZenitsuLabel();
                    JOptionPane.showMessageDialog(null, DemonSlayerMain.zenitsu.printStats() + "\nDamage absorbed "
                            + damageAbsorbed + "\n" + DemonSlayerMain.akaza.toString());
                    attackZenitsuButton.setEnabled(true);
                    if (DemonSlayerMain.zenitsu.getHealth() <= 0 && !allSlayerDeath()) {
                        boss = "AKAZA";
                        zenitsuBody.setVisible(false);
                        healthZenitsuLabel.setVisible(false);
                        attackZenitsuButton.setVisible(false);
                        evadeZenitsuButton.setVisible(false);
                        healZenitsu.setVisible(false);
                        panel.setVisible(true);
                        akazaBody.setVisible(false);
                        quit.setBounds(600, 0, 200, 50);
                        quit.setVisible(true);
                        back.setVisible(true);
                        healthAkazaLabel.setVisible(false);
                        showZenitsuIf();
                        showInosukeIf();
                        showKamadoIf();
                    } else if (allSlayerDeath())
                        endGame();
                    else if (DemonSlayerMain.akaza.akazaHealth <= 0)
                        winGame();
                }
            }, 1000);
        } else if (boss.equals("MUZAN")) {
            hpMuzanLabel();
            attackZenitsuButton.setEnabled(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hpZenitsuLabel();
                    JOptionPane.showMessageDialog(null, DemonSlayerMain.zenitsu.printStats() + "\nDamage absorbed "
                            + damageAbsorbed + "\n" + DemonSlayerMain.muzan.toString());
                    attackZenitsuButton.setEnabled(true);
                    if (DemonSlayerMain.zenitsu.getHealth() <= 0 && !allSlayerDeath()) {
                        boss = "MUZAN";
                        zenitsuBody.setVisible(false);
                        healthZenitsuLabel.setVisible(false);
                        attackZenitsuButton.setVisible(false);
                        evadeZenitsuButton.setVisible(false);
                        healZenitsu.setVisible(false);
                        panel.setVisible(true);
                        muzanBody.setVisible(false);
                        quit.setBounds(600, 0, 200, 50);
                        quit.setVisible(true);
                        back.setVisible(true);
                        healthMuzanLabel.setVisible(false);
                        showZenitsuIf();
                        showInosukeIf();
                        showKamadoIf();
                    } else if (allSlayerDeath())
                        endGame();
                    else if (DemonSlayerMain.muzan.healthMuzan <= 0)
                        winGame();
                }
            }, 1000);
        }
    }

    private void inosukeAttack() {
        if (boss.equals("RUI")) {
            hpRuiLabel();
            attackInosukeButton.setEnabled(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hpInosukeLabel();
                    JOptionPane.showMessageDialog(null, DemonSlayerMain.inosuke.printStats() + "\nDamage absorbed "
                            + damageAbsorbed + ".\n" + DemonSlayerMain.rui.toString());
                    attackInosukeButton.setEnabled(true);
                    if (DemonSlayerMain.inosuke.getHealth() <= 0 && !allSlayerDeath()) {
                        boss = "RUI";
                        inosukeBody.setVisible(false);
                        healthInosukeLabel.setVisible(false);
                        attackInosukeButton.setVisible(false);
                        evadeInosukeButton.setVisible(false);
                        healInosuke.setVisible(false);
                        panel.setVisible(true);
                        ruiBody.setVisible(false);
                        quit.setBounds(600, 0, 200, 50);
                        quit.setVisible(true);
                        back.setVisible(true);
                        healthRuiLabel.setVisible(false);
                        showZenitsuIf();
                        showInosukeIf();
                        showKamadoIf();
                    } else if (allSlayerDeath())
                        endGame();
                    else if (DemonSlayerMain.rui.healthRui <= 0)
                        winGame();
                }
            }, 1000);
        } else if (boss.equals("AKAZA")) {
            hpAkazaLabel();
            attackInosukeButton.setEnabled(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hpInosukeLabel();
                    JOptionPane.showMessageDialog(null, DemonSlayerMain.inosuke.printStats() + "\nDamage absorbed "
                            + damageAbsorbed + "\n" + DemonSlayerMain.akaza.toString());
                    attackInosukeButton.setEnabled(true);
                    if (DemonSlayerMain.inosuke.getHealth() <= 0 && !allSlayerDeath()) {
                        boss = "AKAZA";
                        inosukeBody.setVisible(false);
                        healthInosukeLabel.setVisible(false);
                        attackInosukeButton.setVisible(false);
                        evadeInosukeButton.setVisible(false);
                        healInosuke.setVisible(false);
                        panel.setVisible(true);
                        akazaBody.setVisible(false);
                        quit.setBounds(600, 0, 200, 50);
                        quit.setVisible(true);
                        back.setVisible(true);
                        healthAkazaLabel.setVisible(false);
                        showZenitsuIf();
                        showInosukeIf();
                        showKamadoIf();
                    } else if (allSlayerDeath())
                        endGame();
                    else if (DemonSlayerMain.akaza.akazaHealth <= 0)
                        winGame();
                }
            }, 1000);
        } else if (boss.equals("MUZAN")) {
            hpMuzanLabel();
            attackInosukeButton.setEnabled(false);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hpInosukeLabel();
                    JOptionPane.showMessageDialog(null, DemonSlayerMain.inosuke.printStats() + "\nDamage absorbed "
                            + damageAbsorbed + "\n" + DemonSlayerMain.muzan.toString());
                    attackInosukeButton.setEnabled(true);
                    if (DemonSlayerMain.inosuke.getHealth() <= 0 && !allSlayerDeath()) {
                        boss = "MUZAN";
                        inosukeBody.setVisible(false);
                        healthInosukeLabel.setVisible(false);
                        attackInosukeButton.setVisible(false);
                        evadeInosukeButton.setVisible(false);
                        healInosuke.setVisible(false);
                        panel.setVisible(true);
                        muzanBody.setVisible(false);
                        quit.setBounds(600, 0, 200, 50);
                        quit.setVisible(true);
                        back.setVisible(true);
                        healthMuzanLabel.setVisible(false);
                        showZenitsuIf();
                        showInosukeIf();
                        showKamadoIf();
                    } else if (allSlayerDeath())
                        endGame();
                    else if (DemonSlayerMain.muzan.healthMuzan <= 0)
                        winGame();
                }
            }, 1000);
        }
    }

    private void evadeKamado() {
        switch (boss) {
            case "RUI" -> JOptionPane.showMessageDialog(null, DemonSlayerMain.kamado.printStatsEvade() +
                    "\n" + DemonSlayerMain.rui.toString());
            case "AKAZA" -> JOptionPane.showMessageDialog(null, DemonSlayerMain.kamado.printStatsEvade() +
                    "\n" + DemonSlayerMain.akaza.toString());
            case "MUZAN" -> JOptionPane.showMessageDialog(null, DemonSlayerMain.kamado.printStatsEvade() +
                    "\n" + DemonSlayerMain.muzan.toString());
        }
    }

    private void evadeZenitsu() {
        switch (boss) {
            case "RUI" -> JOptionPane.showMessageDialog(null, DemonSlayerMain.zenitsu.printStatsEvade() +
                    "\n" + DemonSlayerMain.rui.toString());
            case "AKAZA" -> JOptionPane.showMessageDialog(null, DemonSlayerMain.zenitsu.printStatsEvade() +
                    "\n" + DemonSlayerMain.akaza.toString());
            case "MUZAN" -> JOptionPane.showMessageDialog(null, DemonSlayerMain.zenitsu.printStatsEvade() +
                    "\n" + DemonSlayerMain.muzan.toString());
        }
    }

    private void evadeInosuke() {
        switch (boss) {
            case "RUI" -> JOptionPane.showMessageDialog(null, DemonSlayerMain.inosuke.printStatsEvade() +
                    "\n" + DemonSlayerMain.rui.toString());
            case "AKAZA" -> JOptionPane.showMessageDialog(null, DemonSlayerMain.inosuke.printStatsEvade() +
                    "\n" + DemonSlayerMain.akaza.toString());
            case "MUZAN" -> JOptionPane.showMessageDialog(null, DemonSlayerMain.inosuke.printStatsEvade() +
                    "\n" + DemonSlayerMain.muzan.toString());
        }
    }

    private void endGame() {
        try {
            hideAll();
            quit.setBounds(600, 0, 200, 50);
            quit.setVisible(true);
            back.setVisible(true);
            gameOver.setVisible(true);
            DemonSlayerMain.choice = "STOP";
            playMusic();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void winGame() {
        try {
            hideAll();
            youWin.setVisible(true);
            quit.setVisible(true);
            quit.setBounds(600, 0, 200, 50);
            back.setVisible(true);
            DemonSlayerMain.choice = "YOU WIN";
            playMusic();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void playMusic() {
        try {
            PlaySound.getSound(ruiSoundClip, akazaSoundClip, muzanSoundClip, ruiVolume, akazaVolume, muzanVolume,
                    startMenuClip,
                    startMenuVolume, gameOverClip, gameOverVolume, youWinClip, youWinVolume, DemonSlayerMain.choice);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
