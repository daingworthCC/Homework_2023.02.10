package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
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
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JTextField text = new JTextField(16);
        JButton btn = new JButton("Submit");
        JLabel title = new JLabel("ENTER TEXT");
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
        panel1.add(title);
        panel2.add(text);
        panel2.add(btn);
        JPanel mainpanel=new JPanel(new BorderLayout());
        mainpanel.add(panel1,BorderLayout.NORTH);
        mainpanel.add(panel2,BorderLayout.CENTER);
        topFrame.add(mainpanel);
        topFrame.setSize(800, 400);

    }

}
