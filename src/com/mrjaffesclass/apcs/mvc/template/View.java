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
    public String leaderboardText = "10";
    public JToggleButton[] button = new JToggleButton[64];
    public boolean[] bombMap = new boolean[64];
    public Random random = new Random();
    public int lives = Constants.lives;
    public int score = 0;
    public int highScore = 10;
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
        leaderboardLoad(false);
        livesUpdate(0);
        jLabel6.setText("HighScore : " + highScore);
    }

    public void buttonInit() {
        jPanel1.setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 64; i++) {
            button[i] = new JToggleButton(String.valueOf(i));
            button[i].setName(String.valueOf(i));
            //button[i].setBackground(new java.awt.Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            button[i].setBackground(new java.awt.Color((i * 2 - i) / 2, i + 100, i));
            jPanel1.add(button[i]);
            button[i].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    button[Integer.parseInt(evt.getActionCommand())].setEnabled(false);
                    out.println(evt.getActionCommand() + " Was clicked");
                    out.println(bombMap[Integer.parseInt(evt.getActionCommand())]);
                    if (bombMap[Integer.parseInt(evt.getActionCommand())] == true) {
                        button[Integer.parseInt(evt.getActionCommand())].setBackground(new java.awt.Color(255, 0, 0));
                        button[Integer.parseInt(evt.getActionCommand())].setText("B");
                        livesUpdate(1);
                    } else {
                        button[Integer.parseInt(evt.getActionCommand())].setBackground(new java.awt.Color(255, 255, 255));
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
        } else if (lives <= 0) {
            gameOver(false);
        }
    }

    public void scoreUpdates(int scoreToAdd) {
        score += scoreToAdd;
        jProgressBar1.setMaximum(highScore);
        jProgressBar1.setValue(score);
        jLabel2.setText("Score : " + score);
        jLabel6.setText("HighScore : " + highScore);
    }

    public void gameOver(boolean win) {

        for (int i = 0; i < 64; i++) {
            button[i].setEnabled(false);
            dialogBoxInit();

        }
    }

    public void dialogBoxInit() {
        jFrame1.setSize(450, 300);
        jFrame1.setResizable(false);
        jLabel4.setText("Score: " + score);
        jLabel5.setText("Highscore: " + highScore);

        jFrame1.setVisible(true);
    }

    public void leaderboardLoad(boolean overWrite) {
        try {
            FileReader leaderboard = new FileReader("../Leaderboard.txt");
            BufferedReader bReader = new BufferedReader(leaderboard);
            Scanner scan = new Scanner(leaderboardText);
            
            //highScore = scan.nextInt();
            
            if (overWrite) {
                Leaderboard.setText(" ");
            }
            while ((leaderboardText = bReader.readLine()) != null) {
                Leaderboard.insert(leaderboardText + "\n", WIDTH);
                if(scan.hasNextInt())
                highScore = scan.nextInt();
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
            String length = Name + " : " + message;

            bWriter.newLine();
            bWriter.write(" " + Name + " : " + message, WIDTH, length.length());

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

        jFrame1 = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Leaderboard = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setText("Retry?");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Highscore:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Score:");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField1.setText("Enter Name here");
        jTextField1.setToolTipText("");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Name:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 403, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        jLabel2.setText("Score: 0");
        jLabel2.setFocusable(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("HighScore: ");
        jLabel6.setFocusable(false);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Name = jTextField1.getText();
        if (score >= highScore) {
            leaderBoardWrite(Integer.toString(score));
            highScore = score;
        }
        scoreUpdates(-score);
        livesUpdate(-Constants.lives);
        jPanel1.removeAll();;
        mvcMessaging.notify("reset");
        jFrame1.setVisible(false);
        buttonInit();
        leaderboardLoad(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Leaderboard;
    private javax.swing.JButton jButton1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
