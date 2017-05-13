/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clock;

/**
 *
 * @author Garrett
 */
import java.awt.Dimension;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class ClockForm extends javax.swing.JFrame {

    String SecFormatted;
    String MinFormatted;
    String HourFormatted;
    String time;

    int hour;
    int min;
    int sec;
    int AM_PM;
    

    int alarmHours; //int used to store alarm hour
    int alarmMinutes; //int used to store alarm minutes

    int alarmAM_PM; //used to select if it is AM or PM for the Alarm
    boolean set = false; //used to set and unset the alarm    

    File alarm = new File("NAME.WAV");//sound file for the alarm

    static void PlaySound(File Sound)//code to get the alarm sound file to play
    {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
        } catch (Exception e) {
        }
    }

    String hourTextLength;//using these to limit the amount of numbers that can placed in the alarm text boxes
    String minTextLength; //using these to limit the amount of numbers that can placed in the alarm text boxes

    /**
     * Creates new form ClockForm
     */
    public ClockForm() {
        initComponents();
        t1.start();
    }

    Thread t1 = new Thread() { //creating a new thread
        public void run() {
            for (;;) { //infinite for loop
                Calendar cal = new GregorianCalendar();
                hour = cal.get(Calendar.HOUR);
                min = cal.get(Calendar.MINUTE);
                sec = cal.get(Calendar.SECOND);
                AM_PM = cal.get(Calendar.AM_PM);

                if (sec < 10) {
                    SecFormatted = String.format("%02d", sec); //put a predefined number of 0's in front of the number
                } else {
                    SecFormatted = Integer.toString(sec); // if the number is greater than 10 do nothing
                }
                if (min < 10) {
                    MinFormatted = String.format("%02d", min); //put a predefined number of 0's in front of the number            
                } else {
                    MinFormatted = Integer.toString(min); //if the number is greater than 10 do nothing
                }
                if (hour < 10) {
                    HourFormatted = String.format("%02d", hour); //put a predefined number of 0's in front of the number 
                } else {
                    HourFormatted = Integer.toString(hour); //if the number is greater than 10 do nothing
                }
                time = HourFormatted + ":" + MinFormatted + ":" + SecFormatted;
                jLabel1.setText(time);
                if (AM_PM == 1) // if AM_PM is 1 then it has to be a PM time
                {
                    jLabel2.setText("PM");
                } else if (AM_PM == 0) // if AM_PM is 0 then it has to be an AM time
                {
                    jLabel2.setText("AM");
                }

                if (jRadioButton1.isSelected() && jRadioButton2.isSelected()) //make it impossible to have both AM & PM selected at the same time
                {
                    jRadioButton1.setSelected(false);
                    jRadioButton2.setSelected(false);
                }

                hourTextLength = jTextField1.getText();
                minTextLength = jTextField2.getText();

                if (hourTextLength.length() > 2)//using this to reset the text field if a number larger than two digits is attempted to be inserted into the hour field
                {
                    jTextField1.setText("");
                }

                if (minTextLength.length() > 2) //using this to reset the text field if a number larger than two digits is attempted to be inserted into the minute field
                {
                    jTextField2.setText("");
                }

                if (set == true)//checking to see if the boolean that is used for the alarm set button has been pressed
                {
                    if (AM_PM == alarmAM_PM && hour == alarmHours && min == alarmMinutes) //if statements to compare the time to the time the alarm has been set to
                    {
                        //ALARM code
                        jLabel1.setText("WAKE UP");
                        jLabel2.setText("");
                        PlaySound(alarm);
                    }
                }

                try {
                    Thread.sleep(1000); //every 1 second, put the thread to sleep so that the time is updated once every second
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClockForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Digital Clock");

        jLabel1.setFont(new java.awt.Font("DS-Digital", 0, 160)); // NOI18N
        jLabel1.setText("Time");

        jLabel2.setFont(new java.awt.Font("DS-Digital", 0, 160)); // NOI18N
        jLabel2.setText("AM/PM");

        jRadioButton1.setText("AM");

        jRadioButton2.setText("PM");

        jLabel3.setFont(new java.awt.Font("DS-Digital", 0, 64)); // NOI18N
        jLabel3.setText("Alarm");

        jTextField1.setFont(new java.awt.Font("DS-Digital", 0, 24)); // NOI18N
        jTextField1.setText("7");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("DS-Digital", 0, 24)); // NOI18N
        jTextField2.setText("30");

        jButton1.setFont(new java.awt.Font("DS-Digital", 0, 48)); // NOI18N
        jButton1.setText("Set");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("DS-Digital", 0, 22)); // NOI18N
        jLabel4.setText("Hours");

        jLabel5.setFont(new java.awt.Font("DS-Digital", 0, 22)); // NOI18N
        jLabel5.setText("Minutes");
        jLabel5.setToolTipText("");

        jButton2.setFont(new java.awt.Font("DS-Digital", 0, 48)); // NOI18N
        jButton2.setText("Unset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addGap(40, 40, 40)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //set alarm button
        set = true;//the alarm is set
        alarmHours = Integer.parseInt(jTextField1.getText()); //grabbing the value in the hours field for the alarm time
        alarmMinutes = Integer.parseInt(jTextField2.getText());//grabbing the value in the minutes field for the alarm time

        if (alarmHours > 12) {
            JOptionPane.showMessageDialog(this, "Please input a number that is 12 or lower", "Error", JOptionPane.ERROR_MESSAGE); //prevent user from typing a number greater than 12 for the hours
            jTextField1.setText("");
        }
        if (alarmMinutes > 60) {
            JOptionPane.showMessageDialog(this, "Please input a number that is 60 or lower", "Error", JOptionPane.ERROR_MESSAGE); //prevent user from typing a number greater than 60 for the minutes
            jTextField2.setText("");
        }

        if (jRadioButton1.isSelected())//this is the button for AM
        {
            alarmAM_PM = 0; //0 for AM, use this to compare to the value of the regular AM_PM
        } else if (jRadioButton2.isSelected())//this is the button for PM
        {
            alarmAM_PM = 1; //1 for PM, use this to compare to the value of the regular AM_PM
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //unset alarm button
        set = false;//the alarm is not set
        jRadioButton1.setSelected(false); //clears the radio buttons selection
        jRadioButton2.setSelected(false); //clears the radio buttons selection
        jTextField1.setText(""); // clears the text field
        jTextField2.setText(""); // clears the text field
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClockForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClockForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClockForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClockForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClockForm().setVisible(true);
            }
           
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
