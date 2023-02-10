package frontend;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTextFieldUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

import backend.Backend;

public class Frontend {

    private JFrame topFrame;
    private Backend backend;

    public Frontend() {
        backend=new Backend();
        setUp(backend);
    }

    private void setUp(Backend b){
        topFrame = new JFrame();
        topFrame.setVisible(true);
        topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topFrame.getContentPane().setBackground(Color.WHITE);
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JLabel title = new JLabel("Enter Text:");

        JLabel stylelabel = new JLabel("Choose Style:");
        JTextField style = new JTextField("Fiction,Magazine,News...");
        style.setToolTipText("Fiction,Magazine,News...");

        JTextField text = new JTextField();
        text.setPreferredSize( new Dimension( 200, 24 ) );
        JButton btn = new JButton("Submit");
        //btn.setLocation(500, 100);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.addToData(text.getText());
                System.out.println(text.getText());
                System.out.println(b.getText(text.getText()));
                title.setText(b.getText(text.getText()));
                text.setText("");
            }
        });
        panel3.add(stylelabel);
        panel3.add(style);

        panel2.add(title);
        panel2.add(text);
        panel2.add(btn);
        
        JPanel mainpanel=new JPanel(new BorderLayout());
        //mainpanel.add(panel1,BorderLayout.NORTH);
        mainpanel.add(panel3,BorderLayout.PAGE_START);
        mainpanel.add(panel2,BorderLayout.CENTER);
        topFrame.add(mainpanel);
        topFrame.setSize(800, 400);

    }

}
