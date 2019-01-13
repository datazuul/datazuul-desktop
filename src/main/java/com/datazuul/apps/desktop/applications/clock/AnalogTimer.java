package com.datazuul.apps.desktop.applications.clock;

import java.awt.*;
import javax.swing.*;

public class AnalogTimer extends JFrame {

  Clock clockFace;
//================================================================= main

  public static void main(String[] args) {
    JFrame windo = new AnalogTimer();
    windo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    windo.setVisible(true);
  }//end main
//========================================================== constructor

  public AnalogTimer() {
    Container content = this.getContentPane();
    content.setLayout(new BorderLayout());
    clockFace = new Clock();
    content.add(clockFace, BorderLayout.CENTER);
    this.setTitle("Analog Clock");
    this.pack();
    clockFace.start();
  }//end constructor
}//end class ClockAnalogBuf

