package com.mrjaffesclass.apcs.mvc.template;

import com.mrjaffesclass.apcs.messenger.*;
import java.awt.GridLayout;
import java.io.*;
import java.lang.System;
import java.util.*;
import static java.lang.System.*;
import javafx.scene.paint.Color;
import javax.swing.*;

/**
 * MVC Template This is a template of an MVC framework used by APCS for the
 * LandMine project (and others)
 *
 * @author Roger Jaffe
 * @version 1.0
 *
 */
public class View extends javax.swing.JFrame implements MessageHandler {

    private final Messenger mvcMessaging;
    public String leaderboardText;
    public JToggleButton[] button = new JToggleButton[64];
    public boolean[] bombMap = new boolean[64];
    public Random random = new Random();
    public int lives = Constants.lives;
    public int score = 0;
    public int highScore = 20;
    public String Name;

    /**
     *
     * @param messages
     */
    /**
     * Creates a new view
     *
     * @param messages mvcMessaging object
     */
    public View(Messenger messages) {
        mvcMessaging = messages;   // Save the calling controller instance
        initComponents(); // Create and init the GUI components
        buttonInit();
        leaderboardLoad();
        livesUpdate(0);
    }

    public void buttonInit() {
        jPanel1.setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 64; i++) {
            button[i] = new JToggleButton(String.valueOf(i));
            button[i].setName(String.valueOf(i));
            //button[i].setBackground(new java.awt.Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            jPanel1.add(button[i]);
            button[i].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    button[Integer.parseInt(evt.getActionCommand())].setEnabled(false);
                    out.println(evt.getActionCommand() + " Was clicked");
                    out.println(bombMap[Integer.parseInt(evt.getActionCommand())]);
                    if (bombMap[Integer.parseInt(evt.getActionCommand())] == true) {
                        button[Integer.parseInt(evt.getActionCommand())].setBackground(new java.awt.Color(0, 0, 0));
                        livesUpdate(1);
                    } else {
                        button[Integer.parseInt(evt.getActionCommand())].setBackground(new java.awt.Color(0, 0, 0));
                        scoreUpdates(1);
                    }
                }
            });
        }
    }

    public void livesUpdate(int damage) {
        lives -= damage;

        jLabel1.setText("Lives: " + lives);

        if (lives <= 0 && score >= highScore) {
            gameOver(true);
        }
        else
        {
            gameOver(false);
        }
    }

    public void scoreUpdates(int scoreToAdd) {
        score += scoreToAdd;
        jProgressBar1.setMaximum(highScore);
        jProgressBar1.setValue(score);
        
        if (score >= highScore) {
            leaderBoardWrite(Name + " : " + score);
        }
    }

    public void gameOver(boolean win) {

        if (win) {
            for (int i = 0; i < 64; i++) {
                button[i].setEnabled(false);
                dialogBoxInit();
                jDialog1.setVisible(true);
            }
        }
    }

    public void dialogBoxInit() {
        JButton reset = new JButton("Do You Want To Retry?");
        reset.setName("reset");
        reset.setSize(200, 50);
        jDialog1.setSize(300, 200);
        jDialog1.setResizable(false);
        jDialog1.add(reset);
    }

    public void leaderboardLoad() {
        try {
            FileReader leaderboard = new FileReader("../Leaderboard.txt");
            BufferedReader bReader = new BufferedReader(leaderboard);

            while ((leaderboardText = bReader.readLine()) != null) {
                Leaderboard.insert(leaderboardText + "\n", WIDTH);
            }

            bReader.close();
        } catch (IOException e) {
            out.println("File Not found " + leaderboardText);
        }
    }

    public void leaderBoardWrite(String message) {
        try {
            FileWriter leaderboard = new FileWriter("../src/../Leaderboard.txt", true);
            BufferedWriter bWriter = new BufferedWriter(leaderboard);

            bWriter.newLine();
            bWriter.write(" " + message, WIDTH, message.length());

            bWriter.close();
        } catch (IOException e) {
            out.println("File Not found " + leaderboardText);
        }
    }

    /**
     * Initialize the model here and subscribe to any required messages
     */
    public void init() {
        // Subscribe to messages here
        mvcMessaging.subscribe("model:variable1Changed", this);
        mvcMessaging.subscribe("model:variable2Changed", this);
        mvcMessaging.subscribe("model:isBomb", this);
    }

    @Override
    public void messageHandler(String messageName, Object messagePayload) {
        if (messagePayload != null) {
            System.out.println("MSG: received by view: " + messageName + " | " + messagePayload.toString());
        } else {
            System.out.println("MSG: received by view: " + messageName + " | No data sent");
        }

        if (messageName.equals("model:isBomb")) {
            bombMap = (boolean[]) messagePayload;
        }
    }

    /**
     * Instantiate an object with the field number that was clicked (1 or 2) and
     * the direction that the number should go (up or down)
     *
     * @param fieldNumber 1 or 2 for the field being modified
     * @param direction this.UP (1) or this.DOWN (-1), constants defined above
     * @return the HashMap payload to be sent with the message
     */
    private MessagePayload createPayload(int fieldNumber, int direction) {
        MessagePayload payload = new MessagePayload(fieldNumber, direction);
        return payload;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        Leaderboard = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        Leaderboard.setEditable(false);
        Leaderboard.setColumns(20);
        Leaderboard.setRows(5);
        Leaderboard.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Leaderboard.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(Leaderboard);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 472, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jProgressBar1.setMaximum(highScore);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Lives: ");
        jLabel1.setFocusable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Score: ");
        jLabel2.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Leaderboard;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
