package frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
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
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        JLabel title = new JLabel("Enter Text:");

        JLabel stylelabel = new JLabel("Choose Style:");
        String[] choices = { "acad","blog", "fic","mag","news","spok","tvm","web"};
        JComboBox<String> style = new JComboBox<String>(choices);

        JLabel output = new JLabel("Output:");

        JTextField text = new JTextField();
        text.setPreferredSize( new Dimension( 200, 24 ) );
        JButton btn = new JButton("Submit");
        //btn.setLocation(500, 100);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    b.getFiles(style.getSelectedItem().toString());
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    output.setText("Error. Try Again.");
                }
                System.out.println(text.getText());
                System.out.println(b.getText(text.getText()));
                text.setText("");
                output.setText("Output:"+b.getText(text.getText()));
            }
        });
        panel1.add(stylelabel);
        panel1.add(style);

        panel2.add(title);
        panel2.add(text);
        panel2.add(btn);
        panel3.add(output);
        
        JPanel mainpanel=new JPanel(new BorderLayout());
        //mainpanel.add(panel1,BorderLayout.NORTH);
        mainpanel.add(panel1,BorderLayout.PAGE_START);
        mainpanel.add(panel2,BorderLayout.CENTER);
         mainpanel.add(panel3,BorderLayout.PAGE_END);
        topFrame.add(mainpanel);
        topFrame.setSize(800, 400);

    }

}
