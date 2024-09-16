package br.ufjf.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputSection extends JPanel {
    
    private JTextField text = new JTextField();

    InputSection(String placeholder) {
        GridLayout layout = new GridLayout(2, 0);
        layout.setVgap(5);
        setLayout(layout);
        add(new JLabel(placeholder));
        add(text);
        setBackground(Color.RED);
    }

    public String getText() {
        return text.getText();
    }
}
