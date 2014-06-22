package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Gui extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    public ArrayList<Integer> idList;
    public boolean run = false;
    public boolean harralander = false;
    public boolean ranarr = false;
    public boolean irit = false;
    public boolean avantoe = false;
    public boolean kwuarm = false;
    public boolean torstol = false;
    public boolean leaveSpace = false;
    private JCheckBox harButton;
    private JCheckBox ranButton;
    private JCheckBox iriButton;
    private JCheckBox avaButton;
    private JCheckBox kwuButton;
    private JCheckBox torButton;
    private JCheckBox spaceButton;
    private JButton startButton;


    public Gui() {
        super("SinisterStacks v0.1");
        setResizable(false);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


        //create labels/checkboxes/buttons
        JLabel idLabel = new JLabel("Identify :");
        harButton = new JCheckBox("harralander");
        ranButton = new JCheckBox("ranarr");
        iriButton = new JCheckBox("irit");
        avaButton = new JCheckBox("avantoe");
        kwuButton = new JCheckBox("kwuarm");
        torButton = new JCheckBox("torstol");
        startButton = new JButton("Start");


        //add listeners
        harButton.addActionListener(this);
        ranButton.addActionListener(this);
        iriButton.addActionListener(this);
        avaButton.addActionListener(this);
        kwuButton.addActionListener(this);
        torButton.addActionListener(this);
        startButton.addActionListener(this);

        //add items to their respective parents
       mainPanel.add(idLabel);
        mainPanel.add(harButton);
        mainPanel.add(ranButton);
        mainPanel.add(iriButton);
        mainPanel.add(avaButton);
        mainPanel.add(kwuButton);
        mainPanel.add(torButton);
        mainPanel.add(startButton);
        add(mainPanel);

        //compact free space
        pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        harralander = harButton.isSelected();
        ranarr = ranButton.isSelected();
        irit = iriButton.isSelected();
        avantoe = avaButton.isSelected();
        kwuarm = kwuButton.isSelected();
        torstol = torButton.isSelected();

        if (e.getSource().equals(startButton)) {

            idList = new ArrayList<>();

            if (harralander) {
                idList.add(205);
            }
            if (ranarr) {
                idList.add(207);
            }
            if (irit) {
                idList.add(209);
            }
            if (avantoe) {
                idList.add(211);
            }
            if (kwuarm) {
                idList.add(213);
            }
            if (torstol) {
                idList.add(219);
            }

            run = true;
            dispose();
        }


    }
}