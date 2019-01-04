package sample;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends JFrame  {
    public static final int HEIGHT = 500;
    public static final int WIDTH = 400;

    private JButton playButton;
    private JPanel panel1;
    private JButton startRecordingButton;
    private JButton stopRecordingButton;
    private boolean isRecording = false;
    public static ArrayList<PointerInfo> coordinates= new ArrayList<PointerInfo>();



    public Controller() {

        super("MouseRecorder V 1.0");
        setSize(HEIGHT,WIDTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startRecordingButton = new JButton("Start Recording");
        stopRecordingButton = new JButton("Stop Recording");
        playButton = new JButton("Play Recording");
        FlowLayout layout = new FlowLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(startRecordingButton);
        panel.add(stopRecordingButton);
        panel.add(playButton);
        add(panel);
        setVisible(true);
        pack();




        MouseInputThread t = new MouseInputThread();

        startRecordingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int counter = 0;
                isRecording = true;
                // adds coordinates while the user doesn't hit the end button
                if(!t.isAlive())
                {
                    t.start();
                }
               else
                {
                    t.resume();
                }


            }
        });

        stopRecordingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                isRecording = false;
                t.suspend();
                //coordinates.clear();
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Robot r = new Robot();
                    Thread.sleep(200);
                    for(PointerInfo x : coordinates){
                        int xValue = (int)x.getLocation().getX();
                        int yValue = (int)x.getLocation().getY();

                        r.mouseMove(xValue, yValue);
                    }
                }
                catch(AWTException d)
                {
                    d.printStackTrace();

                }
                catch(InterruptedException f)
                {
                    f.printStackTrace();
                }


            }
        });


    }


}
