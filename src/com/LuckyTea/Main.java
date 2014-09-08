package com.LuckyTea;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import java.util.concurrent.TimeUnit;

class Timer
    implements Runnable {
    public void run(){
        try {
            while (Main.x != 0){
                TimeUnit.SECONDS.sleep(1);
                Main.x--;
                if (Main.iS2 > 0) {
                    Main.iS2--;
                    Main.numChange();
                } else if (Main.iS2 == 0 && Main.iS1 > 0){
                    Main.iS1--;
                    Main.iS2 = 9;
                    Main.numChange();
                } else if (Main.iS2 == 0 && Main.iS1 == 0 && Main.iM2 > 0){
                    Main.iM2--;
                    Main.iS1 = 5;
                    Main.iS2 = 9;
                    Main.numChange();
                } else if (Main.iS2 == 0 && Main.iS1 == 0 && Main.iM2 == 0 && Main.iM1 > 0){
                    Main.iM1--;
                    Main.iM2 = 9;
                    Main.iS1 = 5;
                    Main.iS2 = 9;
                    Main.numChange();
                }
            }
                if (Main.x == 0) {
                    AudioClip bep = Applet.newAudioClip(getClass().getResource("snd/bep.wav"));
                    bep.play();
                }
        } catch (InterruptedException e) {
            System.out.println("Error: " + e);
        }
    }
}

public class Main extends JFrame {
    static  Point           dragPoint;
    static  JFrame          body;
    static  JPanel          pUp, lcd, timerPanel;
    static  String          BFon = "img/BimoFaceON.gif",
                            BF00 = "img/BimoFace00.png",
                            BF01 = "img/BimoFace01.png",
                            FS   = "img/BimoFloppy.png",
                            FB00 = "img/BimoFloppyButton00.png",
                            FB01 = "img/BimoFloppyButton01.png",
                            DP   = "img/BimoDpad00.png",
                            RB00 = "img/BimoRedButton00.png",
                            RB01 = "img/BimoRedButton01.png",
                            GB00 = "img/BimoGreenButton00.png",
                            GB01 = "img/BimoGreenButton01.png",
                            BB00 = "img/BimoBlueButton00.png",
                            BB01 = "img/BimoBlueButton01.png",
                            JP   = "img/BimoJoyPort.png",
                            N0   = "img/timer/TimerNumber0.png",
                            N1   = "img/timer/TimerNumber1.png",
                            N2   = "img/timer/TimerNumber2.png",
                            N3   = "img/timer/TimerNumber3.png",
                            N4   = "img/timer/TimerNumber4.png",
                            N5   = "img/timer/TimerNumber5.png",
                            N6   = "img/timer/TimerNumber6.png",
                            N7   = "img/timer/TimerNumber7.png",
                            N8   = "img/timer/TimerNumber8.png",
                            N9   = "img/timer/TimerNumber9.png";
    static ImageIcon        bf, fb, rb, gb, bb, jp, TBSt, TBSp, TBSr, TDU, TDD,
                            n1 = createIcon(N0),
                            n2 = createIcon(N0),
                            n3 = createIcon(N0),
                            n4 = createIcon(N0);
    static JLabel           bimoFace,
                            floppyButton,
                            dPad,
                            redButton,
                            greenButton,
                            blueButton,
                            joyPort1, joyPort2,
                            TimerM1, TimerM2, TimerS1, TimerS2,
                            TimerButtonStart, TimerButtonStop, TimerButtonReset,
                            TimerDotUp1, TimerDotUp2, TimerDotUp3, TimerDotUp4,
                            TimerDotDown1, TimerDotDown2, TimerDotDown3, TimerDotDown4;
    static int              showTimer = 0, startTimer = 0,
                            iM1 = 0, iM2 = 0, iS1 = 0, iS2 = 0,
                            x = 0;
    static Thread           myThread;
    static Timer 	        mThing;

    public static void createBimo(){
        body = new JFrame();
        body.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        body.setSize(275,400);
        body.setResizable(false);
        body.setUndecorated(true);
        body.setLocationRelativeTo(null);
        body.setShape(new RoundRectangle2D.Double(0,0,275,400,25,25));
        body.setVisible(true);

        JPanel pBody = new JPanel();
        pBody.setBackground(new Color(100,100,170));
        pBody.setSize(275,400);
        pBody.setLayout(new BorderLayout());
        body.add(pBody);

            pUp = new JPanel();
            pUp.setPreferredSize(new Dimension(275,200));
            pUp.setBackground(new Color(100,190,170));
            pUp.setLayout(null);
            pBody.add(pUp, BorderLayout.NORTH);

                lcd = new JPanel();
                lcd.setBounds(15,15,245,190);
                lcd.setBackground(new Color(217,255,232));
                lcd.setLayout(null);
                pUp.add(lcd);

                    bimoFace = new JLabel();
                    bf = createIcon(BFon);
                    bimoFace.setIcon(bf);
                    bimoFace.setBounds(0,0,245,190);
                    lcd.add(bimoFace);

            JPanel pDown = new JPanel();
            pDown.setLayout(null);
            pDown.setPreferredSize(new Dimension(275,200));
            pDown.setBackground(new Color(100,190,170));
            pBody.add(pDown, BorderLayout.SOUTH);

                JLabel floppySlide = new JLabel();
                ImageIcon fs = createIcon(FS);
                floppySlide.setIcon(fs);
                floppySlide.setBounds(15,25,150,20);
                pDown.add(floppySlide);
//Кнопка флоппика///////////////////////////////////////////////////////////////////////////////////////
                floppyButton = new JLabel("test");
                fb = createIcon(FB00);
                floppyButton.setIcon(fb);
                floppyButton.setBounds(215,20,30,30);
                pDown.add(floppyButton);

                floppyButton.addMouseListener(new MouseListener() {
                    public void mouseClicked    (MouseEvent e) { }
                    public void mousePressed    (MouseEvent e) {
                        AudioClip bep = Applet.newAudioClip(getClass().getResource("snd/bep.wav"));
                        bep.play();
                        fb = createIcon(FB01);
                        floppyButton.setIcon(fb);}
                    public void mouseReleased   (MouseEvent e) {
                        fb = createIcon(FB00);
                        floppyButton.setIcon(fb);}
                    public void mouseEntered    (MouseEvent e) { }
                    public void mouseExited     (MouseEvent e) { }
                    });
//Крестовина////////////////////////////////////////////////////////////////////////////////////////////
        dPad = new JLabel();
        ImageIcon dp = createIcon(DP);
        dPad.setIcon(dp);
        dPad.setBounds(32,70,70,70);
        pDown.add(dPad);
//Красная кнопка////////////////////////////////////////////////////////////////////////////////////////
        redButton = new JLabel();
        rb = createIcon(RB00);
        redButton.setIcon(rb);
        redButton.setBounds(150,115,70,70);
        pDown.add(redButton);

        redButton.addMouseListener(new MouseListener() {
            public void mouseClicked    (MouseEvent e) {
                timerInit();
                showTimer = 1;
            }
            public void mousePressed    (MouseEvent e) {
                rb = createIcon(RB01);
                redButton.setIcon(rb);
            }
            public void mouseReleased   (MouseEvent e) {
                rb = createIcon(RB00);
                redButton.setIcon(rb);}
            public void mouseEntered    (MouseEvent e) { }
            public void mouseExited     (MouseEvent e) { }
        });
//Зелёная кнопка////////////////////////////////////////////////////////////////////////////////////////
        greenButton = new JLabel();
        gb = createIcon(GB00);
        greenButton.setIcon(gb);
        greenButton.setBounds(210,80,50,50);
        pDown.add(greenButton);

        greenButton.addMouseListener(new MouseListener() {
            public void mouseClicked    (MouseEvent e) {
                if (startTimer == 0){
                    bf = createIcon(BF01);
                    bimoFace.setIcon(bf);
                    lcd.add(bimoFace);
                    if (showTimer == 1){
                        lcd.remove(timerPanel);
                        lcd.repaint();
                        showTimer = 0;}
                    }
            }
            public void mousePressed    (MouseEvent e) {
                gb = createIcon(GB01);
                greenButton.setIcon(gb);}
            public void mouseReleased   (MouseEvent e) {
                gb = createIcon(GB00);
                greenButton.setIcon(gb);}
            public void mouseEntered    (MouseEvent e) { }
            public void mouseExited     (MouseEvent e) { }
        });
//Синяя кнопка////////////////////////////////////////////////////////////////////////////////////////
        blueButton = new JLabel();
        bb = createIcon(BB00);
        blueButton.setIcon(bb);
        blueButton.setBounds(140,50,70,70);
        pDown.add(blueButton);

        blueButton.addMouseListener(new MouseListener() {
            public void mouseClicked    (MouseEvent e) { }
            public void mousePressed    (MouseEvent e) {
                bb = createIcon(BB01);
                blueButton.setIcon(bb);}
            public void mouseReleased   (MouseEvent e) {
                bb = createIcon(BB00);
                blueButton.setIcon(bb);}
            public void mouseEntered    (MouseEvent e) { }
            public void mouseExited     (MouseEvent e) { }
        });
//Порт//////////////////////////////////////////////////////////////////////////////////////////////////
        joyPort1 = new JLabel();
        jp = createIcon(JP);
        joyPort1.setIcon(jp);
        joyPort1.setBounds(15,150,50,50);
        pDown.add(joyPort1);

        joyPort2 = new JLabel();
        joyPort2.setIcon(jp);
        joyPort2.setBounds(70,150,50,50);
        pDown.add(joyPort2);
//Перетаскивание окна///////////////////////////////////////////////////////////////////////////////////
        dragPoint = null;
        body.addMouseListener(new MouseListener(){
            public void mouseClicked    (MouseEvent e) { }
            public void mousePressed    (MouseEvent e) {dragPoint = e.getPoint();}
            public void mouseReleased   (MouseEvent e) {dragPoint = null;}
            public void mouseEntered    (MouseEvent e) { }
            public void mouseExited     (MouseEvent e) { }
        });

        body.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged    (MouseEvent e) {Point currentPoint = e.getLocationOnScreen();
                body.setLocation(currentPoint.x - dragPoint.x,
                        currentPoint.y - dragPoint.y);}
            public void mouseMoved      (MouseEvent e) { }
        });
//ПКМ - Меню///////////////////////////////////////////////////////////////////////////////////////////
        body.addMouseListener(new MouseListener(){
            public void mouseEntered    (MouseEvent event){ }
            public void mouseClicked    (MouseEvent event){
                if (event.getButton() == MouseEvent.BUTTON3){
                    JPopupMenu exit = new JPopupMenu();
                    JMenuItem mExit = new JMenuItem("<html><center>Exit</center></html>");
                    mExit.setPreferredSize(new Dimension(45,25));
                    exit.add(mExit);
                    exit.show(event.getComponent(), event.getX(), event.getY());

                    mExit.addMouseListener(new MouseListener() {
                        public void mouseClicked    (MouseEvent e) {System.exit(0);}
                        public void mousePressed    (MouseEvent e) {System.exit(0);}
                        public void mouseReleased   (MouseEvent e) {System.exit(0);}
                        public void mouseEntered    (MouseEvent e) { }
                        public void mouseExited     (MouseEvent e) { }
                    });
                }
            }
            public void mouseReleased   (MouseEvent event){ }
            public void mouseExited     (MouseEvent event){ }
            public void mousePressed    (MouseEvent event){ }
        });
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void timerInit() {
        lcd.remove(bimoFace);
        timerPanel = new JPanel();
        timerPanel.setBounds(0,0,245,190);
        timerPanel.setBackground(new Color(217,255,232));
        timerPanel.setLayout(null);
        lcd.add(timerPanel);
//Рамки/////////////////////////////////////////////////////////////////////////////////////////////////
        JLabel TimerBorderM1 = new JLabel();
        JLabel TimerBorderM2 = new JLabel();
        JLabel TimerBorderS1 = new JLabel();
        JLabel TimerBorderS2 = new JLabel();
        ImageIcon tb = createIcon("img/timer/TimerBorder.png");
        TimerBorderM1.setIcon(tb);
        TimerBorderM2.setIcon(tb);
        TimerBorderS1.setIcon(tb);
        TimerBorderS2.setIcon(tb);
        TimerBorderM1.setBounds(10,35,50,53);
        TimerBorderM2.setBounds(68,35,50,53);
        TimerBorderS1.setBounds(127,35,50,53);
        TimerBorderS2.setBounds(185,35,50,53);
        timerPanel.add(TimerBorderM1);
        timerPanel.add(TimerBorderM2);
        timerPanel.add(TimerBorderS1);
        timerPanel.add(TimerBorderS2);
//Цифры/////////////////////////////////////////////////////////////////////////////////////////////////
        TimerM1 = new JLabel();
        TimerM2 = new JLabel();
        TimerS1 = new JLabel();
        TimerS2 = new JLabel();
        TimerM1.setIcon(n1);
        TimerM2.setIcon(n2);
        TimerS1.setIcon(n3);
        TimerS2.setIcon(n4);
        TimerM1.setBounds(13,43,44,37);
        TimerM2.setBounds(71,43,44,37);
        TimerS1.setBounds(130,43,44,37);
        TimerS2.setBounds(188,43,44,37);
        timerPanel.add(TimerM1);
        timerPanel.add(TimerM2);
        timerPanel.add(TimerS1);
        timerPanel.add(TimerS2);
//Точки/////////////////////////////////////////////////////////////////////////////////////////////////
        JLabel TimerDot1 = new JLabel();
        JLabel TimerDot2 = new JLabel();
        ImageIcon TD = createIcon("img/timer/TimerDot.png");
        TimerDot1.setIcon(TD);
        TimerDot2.setIcon(TD);
        TimerDot1.setBounds(119,44,7,7);
        TimerDot2.setBounds(119,71,7,7);
        timerPanel.add(TimerDot1);
        timerPanel.add(TimerDot2);
        timerDots();
//Кнопки/////////////////////////////////////////////////////////////////////////////////////////////////
        TimerButtonStart = new JLabel();
        TimerButtonStop = new JLabel();
        TimerButtonReset = new JLabel();
        TBSt = createIcon("img/timer/TimerButtonStart.png");
        TBSp = createIcon("img/timer/TimerButtonStop.png");
        TBSr = createIcon("img/timer/TimerButtonReset.png");
        TimerButtonStart.setIcon(TBSt);
        TimerButtonReset.setIcon(TBSr);
        TimerButtonStart.setBounds(38,133,54,12);
        TimerButtonReset.setBounds(155,133,54,12);
        timerPanel.add(TimerButtonStart);
        timerPanel.add(TimerButtonReset);

        TimerButtonStart.addMouseListener(new MouseListener() {
            public void mouseClicked    (MouseEvent e) {
                if (startTimer == 0){
                    startTimer = 1;
                    timerDots();
                    TimerButtonStart.setIcon(TBSp);
                    mThing = new Timer();
                    myThread = new Thread(mThing);
                    myThread.start();
                    x = (iM1*10 + iM2) * 60 + (iS1 * 10 + iS2);
                    System.out.println(x);
                } else {
                    TimerButtonStart.setIcon(TBSt);
                    startTimer = 0;
                    myThread.stop();
                    timerDots();
                }
            }
            public void mousePressed    (MouseEvent e) { }
            public void mouseReleased   (MouseEvent e) { }
            public void mouseEntered    (MouseEvent e) { }
            public void mouseExited     (MouseEvent e) { }
        });
        TimerButtonReset.addMouseListener(new MouseListener() {
            public void mouseClicked    (MouseEvent e) {
                myThread.stop();
                startTimer = 0;
                TimerButtonStart.setIcon(TBSt);
                iM1 = 0; iM2 = 0; iS1 = 0; iS2 = 0;
                numChange();
            }
            public void mousePressed    (MouseEvent e) { }
            public void mouseReleased   (MouseEvent e) { }
            public void mouseEntered    (MouseEvent e) { }
            public void mouseExited     (MouseEvent e) { }
        });
        timerPanel.repaint();
    }

    public static void numChange() {
        if (iS2 == 0){
            n4 = createIcon(N0);
            TimerS2.setIcon(n4);
        } else if (iS2 == 1) {
            n4 = createIcon(N1);
            TimerS2.setIcon(n4);
        } else if (iS2 == 2) {
            n4 = createIcon(N2);
            TimerS2.setIcon(n4);
        } else if (iS2 == 3) {
            n4 = createIcon(N3);
            TimerS2.setIcon(n4);
        } else if (iS2 == 4) {
            n4 = createIcon(N4);
            TimerS2.setIcon(n4);
        } else if (iS2 == 5) {
            n4 = createIcon(N5);
            TimerS2.setIcon(n4);
        } else if (iS2 == 6) {
            n4 = createIcon(N6);
            TimerS2.setIcon(n4);
        } else if (iS2 == 7) {
            n4 = createIcon(N7);
            TimerS2.setIcon(n4);
        } else if (iS2 == 8) {
            n4 = createIcon(N8);
            TimerS2.setIcon(n4);
        } else if (iS2 == 9) {
            n4 = createIcon(N9);
            TimerS2.setIcon(n4);
        } else if (iS2 == -1) {
            iS2 = 9;
            n4 = createIcon(N9);
            TimerS2.setIcon(n4);
        } else if (iS2 == 10) {
            iS2 = 0;
            n4 = createIcon(N0);
            TimerS2.setIcon(n4);
        }
///////////////////////////////
        if (iS1 == 0){
            n3 = createIcon(N0);
            TimerS1.setIcon(n3);
        } else if (iS1 == 1) {
            n3 = createIcon(N1);
            TimerS1.setIcon(n3);
        } else if (iS1 == 2) {
            n3 = createIcon(N2);
            TimerS1.setIcon(n3);
        } else if (iS1 == 3) {
            n3 = createIcon(N3);
            TimerS1.setIcon(n3);
        } else if (iS1 == 4) {
            n3 = createIcon(N4);
            TimerS1.setIcon(n3);
        } else if (iS1 == 5) {
            n3 = createIcon(N5);
            TimerS1.setIcon(n3);
        } else if (iS1 == -1) {
            iS1 = 5;
            n3 = createIcon(N5);
            TimerS1.setIcon(n3);
        } else if (iS1 == 6) {
            iS1 = 0;
            n3 = createIcon(N0);
            TimerS1.setIcon(n3);
        }
/////////////////////////////////
        if (iM2 == 0) {
            n2 = createIcon(N0);
            TimerM2.setIcon(n2);
        } else if (iM2 == 1) {
            n2 = createIcon(N1);
            TimerM2.setIcon(n2);
        } else if (iM2 == 2) {
            n2 = createIcon(N2);
            TimerM2.setIcon(n2);
        } else if (iM2 == 3) {
            n2 = createIcon(N3);
            TimerM2.setIcon(n2);
        } else if (iM2 == 4) {
            n2 = createIcon(N4);
            TimerM2.setIcon(n2);
        } else if (iM2 == 5) {
            n2 = createIcon(N5);
            TimerM2.setIcon(n2);
        } else if (iM2 == 6) {
            n2 = createIcon(N6);
            TimerM2.setIcon(n2);
        } else if (iM2 == 7) {
            n2 = createIcon(N7);
            TimerM2.setIcon(n2);
        } else if (iM2 == 8) {
            n2 = createIcon(N8);
            TimerM2.setIcon(n2);
        } else if (iM2 == 9){
            n2 = createIcon(N9);
            TimerM2.setIcon(n2);
        } else if (iM2 == 10){
            iM2 = 0;
            n2 = createIcon(N0);
            TimerM2.setIcon(n2);
        } else if (iM2 == -1){
            iM2 = 9;
            n2 = createIcon(N9);
            TimerM2.setIcon(n2);}
////////////////////////////////
        if (iM1 == 0) {
            n1 = createIcon(N0);
            TimerM1.setIcon(n1);
        } else if (iM1 == 1) {
            n1 = createIcon(N1);
            TimerM1.setIcon(n1);
        } else if (iM1 == 2) {
            n1 = createIcon(N2);
            TimerM1.setIcon(n1);
        } else if (iM1 == 3) {
            n1 = createIcon(N3);
            TimerM1.setIcon(n1);
        } else if (iM1 == 4) {
            n1 = createIcon(N4);
            TimerM1.setIcon(n1);
        } else if (iM1 == 5) {
            n1 = createIcon(N5);
            TimerM1.setIcon(n1);
        } else if (iM1 == -1) {
            iM1 = 5;
            n1 = createIcon(N5);
            TimerM1.setIcon(n1);
        } else if (iM1 == 6) {
            iM1 = 0;
            n1 = createIcon(N0);
            TimerM1.setIcon(n1);
        }
    }

    public static void timerDots(){
        if (startTimer == 0){
            //+1////////////////////////////////////////////////////////////////////////////////////////////////////
            TimerDotUp1 = new JLabel();
            TimerDotUp2 = new JLabel();
            TimerDotUp3 = new JLabel();
            TimerDotUp4 = new JLabel();
            TDU = createIcon("img/timer/TimerDotUp.png");
            TimerDotUp1.setIcon(TDU);
            TimerDotUp2.setIcon(TDU);
            TimerDotUp3.setIcon(TDU);
            TimerDotUp4.setIcon(TDU);
            TimerDotUp1.setBounds(25,11,20,17);
            TimerDotUp2.setBounds(85,11,20,17);
            TimerDotUp3.setBounds(142,11,20,17);
            TimerDotUp4.setBounds(200,11,20,17);
            timerPanel.add(TimerDotUp1);
            timerPanel.add(TimerDotUp2);
            timerPanel.add(TimerDotUp3);
            timerPanel.add(TimerDotUp4);

            TimerDotUp1.addMouseListener(new MouseListener() {
                public void mouseClicked    (MouseEvent e) {
                    iM1++;
                    numChange();
                }
                public void mousePressed    (MouseEvent e) { }
                public void mouseReleased   (MouseEvent e) { }
                public void mouseEntered    (MouseEvent e) { }
                public void mouseExited     (MouseEvent e) { }
            });

            TimerDotUp2.addMouseListener(new MouseListener() {
                public void mouseClicked    (MouseEvent e) {
                    iM2++;
                    numChange();
                }
                public void mousePressed    (MouseEvent e) { }
                public void mouseReleased   (MouseEvent e) { }
                public void mouseEntered    (MouseEvent e) { }
                public void mouseExited     (MouseEvent e) { }
            });

            TimerDotUp3.addMouseListener(new MouseListener() {
                public void mouseClicked    (MouseEvent e) {
                    iS1++;
                    numChange();
                }
                public void mousePressed    (MouseEvent e) { }
                public void mouseReleased   (MouseEvent e) { }
                public void mouseEntered    (MouseEvent e) { }
                public void mouseExited     (MouseEvent e) { }
            });

            TimerDotUp4.addMouseListener(new MouseListener() {
                public void mouseClicked    (MouseEvent e) {
                    iS2++;
                    numChange();
                }
                public void mousePressed    (MouseEvent e) { }
                public void mouseReleased   (MouseEvent e) { }
                public void mouseEntered    (MouseEvent e) { }
                public void mouseExited     (MouseEvent e) { }
            });
//-1////////////////////////////////////////////////////////////////////////////////////////////////////
            TimerDotDown1 = new JLabel();
            TimerDotDown2 = new JLabel();
            TimerDotDown3 = new JLabel();
            TimerDotDown4 = new JLabel();
            TDD = createIcon("img/timer/TimerDotDown.png");
            TimerDotDown1.setIcon(TDD);
            TimerDotDown2.setIcon(TDD);
            TimerDotDown3.setIcon(TDD);
            TimerDotDown4.setIcon(TDD);
            TimerDotDown1.setBounds(25,95,20,17);
            TimerDotDown2.setBounds(83,95,20,17);
            TimerDotDown3.setBounds(142,95,20,17);
            TimerDotDown4.setBounds(200,95,20,17);
            timerPanel.add(TimerDotDown1);
            timerPanel.add(TimerDotDown2);
            timerPanel.add(TimerDotDown3);
            timerPanel.add(TimerDotDown4);

            TimerDotDown1.addMouseListener(new MouseListener() {
                public void mouseClicked    (MouseEvent e) {
                    iM1--;
                    numChange();
                }
                public void mousePressed    (MouseEvent e) { }
                public void mouseReleased   (MouseEvent e) { }
                public void mouseEntered    (MouseEvent e) { }
                public void mouseExited     (MouseEvent e) { }
            });

            TimerDotDown2.addMouseListener(new MouseListener() {
                public void mouseClicked    (MouseEvent e) {
                    iM2--;
                    numChange();
                }
                public void mousePressed    (MouseEvent e) { }
                public void mouseReleased   (MouseEvent e) { }
                public void mouseEntered    (MouseEvent e) { }
                public void mouseExited     (MouseEvent e) { }
            });

            TimerDotDown3.addMouseListener(new MouseListener() {
                public void mouseClicked    (MouseEvent e) {
                    iS1--;
                    numChange();
                }
                public void mousePressed    (MouseEvent e) { }
                public void mouseReleased   (MouseEvent e) { }
                public void mouseEntered    (MouseEvent e) { }
                public void mouseExited     (MouseEvent e) { }
            });

            TimerDotDown4.addMouseListener(new MouseListener() {
                public void mouseClicked    (MouseEvent e) {
                    iS2--;
                    numChange();
                }
                public void mousePressed    (MouseEvent e) { }
                public void mouseReleased   (MouseEvent e) { }
                public void mouseEntered    (MouseEvent e) { }
                public void mouseExited     (MouseEvent e) { }
            });
            lcd.repaint();
        }else if(startTimer == 1){
            timerPanel.remove(TimerDotUp1);
            timerPanel.remove(TimerDotUp2);
            timerPanel.remove(TimerDotUp3);
            timerPanel.remove(TimerDotUp4);
            timerPanel.remove(TimerDotDown1);
            timerPanel.remove(TimerDotDown2);
            timerPanel.remove(TimerDotDown3);
            timerPanel.remove(TimerDotDown4);
            lcd.repaint();
        }
    }

    protected static ImageIcon createIcon(String path) {
        URL imgURL = Main.class.getResource(path);
        if (imgURL != null){
            return new ImageIcon(imgURL);
        } else {
            System.err.println("File not found: "+path);
            return null;
        }
    }

    public static void init() {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createBimo();
                AudioClip on = Applet.newAudioClip(getClass().getResource("snd/on.wav"));
                on.play();
            }
        });
    }
    public static void main(String[] args){
        init();
    }
}