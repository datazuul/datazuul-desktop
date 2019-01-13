package com.datazuul.apps.desktop;

import com.datazuul.apps.desktop.applications.clock.Clock;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * see https://www.developer.com/java/other/article.php/10936_3609776_2/Using-the-Full-Screen-Exclusive-Mode-API-in-Java.htm
 */
public class Desktop extends JFrame implements ActionListener {

  private final GraphicsDevice graphicsDevice;
  private final DisplayMode origDisplayMode;

  public Desktop(GraphicsDevice graphicsDevice) {
    //Save a reference to the graphics device as an
    // instance variable so that it can be used later to
    // exit the full-screen mode.
    this.graphicsDevice = graphicsDevice;

    setTitle("This title will be hidden (undecorated)");

    //Get and save a reference to the original display
    // mode as an instance variable so that it can be
    // restored later.
    origDisplayMode = graphicsDevice.getDisplayMode();

    //Register an action listener on the exitButton.
    JPanel pane = new JPanel();
    pane.setLayout(new GridBagLayout());
    pane.setAlignmentY(TOP_ALIGNMENT); // FIXME: How to align components on top of JPanel?
    getContentPane().add(pane, BorderLayout.WEST);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.NORTH; // FIXME: How to align components on top of JPanel?
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = GridBagConstraints.RELATIVE;

    // Place the clock in the pane
    final Clock clock = new Clock();
    clock.setPreferredSize(new Dimension(100, 100));
    clock.setBorder(BorderFactory.createRaisedBevelBorder());
    pane.add(clock, gbc);
    clock.start();

    //Place the exitButton in the pane
    JButton exitButton = new JButton("Exit");
    exitButton.addActionListener(this);
    pane.add(exitButton, gbc);
//    getContentPane().add(exitButton, BorderLayout.NORTH);

//Place four labels in the JFrame solely for the
    // purpose of showing that it is a full-screen
    // undecorated JFrame.
    JLabel eastLabel = new JLabel("     East     ");
    eastLabel.setOpaque(true);
    eastLabel.setBackground(Color.RED);
//    getContentPane().add(eastLabel, BorderLayout.EAST);

    JLabel southLabel = new JLabel("South", SwingConstants.CENTER);
    southLabel.setOpaque(true);
    southLabel.setBackground(Color.GREEN);
//    getContentPane().add(southLabel, BorderLayout.SOUTH);

    JLabel westLabel = new JLabel("     West     ");
    westLabel.setOpaque(true);
    westLabel.setBackground(Color.RED);
//    getContentPane().add(westLabel, BorderLayout.WEST);

    JLabel centerLabel = new JLabel("Center", SwingConstants.CENTER);
    centerLabel.setOpaque(true);
    centerLabel.setBackground(Color.WHITE);
    getContentPane().add(centerLabel, BorderLayout.CENTER);

    if (graphicsDevice.isFullScreenSupported()) {
      // Enter full-screen mode witn an undecorated,
      // non-resizable JFrame object.
      setUndecorated(true);
      setResizable(false);
      //Make it happen!
      graphicsDevice.setFullScreenWindow(this);
      validate();
    } else {
      System.out.println("Full-screen mode not supported");
    }//end else    

  }//end constructor

  //The following method is invoked when the useR clicks the exitButton
  @Override
  public void actionPerformed(ActionEvent evt) {
    //Restore the original display mode
    graphicsDevice.setDisplayMode(origDisplayMode);
    //Terminate the program
    System.exit(0);
  }//end actionPerformed
}//end class

