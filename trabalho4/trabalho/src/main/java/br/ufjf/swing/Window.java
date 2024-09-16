package br.ufjf.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LinearGradientPaint;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.ufjf.Person;
import br.ufjf.PersonService;

public class Window extends JFrame {
    
    int WIDTH = 700;
    int HEIGHT = 500;
    int OFFSET = 50;

    PersonService service;
    
    public Window() {
        this.service = new PersonService();
        setSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridLayout(1, 3));

        LineBorder roundedLine = new LineBorder(Color.gray, 2, true);
        TitledBorder inputTitledBorder = new TitledBorder(roundedLine, "Cadastro de Pessoas");
        JPanel inputPanel = new JPanel();   
            GridLayout layout = new GridLayout(5, 1);
            layout.setVgap(15);
            inputPanel.setLayout(layout);
            inputPanel.setPreferredSize(new Dimension(WIDTH/3, HEIGHT-OFFSET));
            inputPanel.setMaximumSize(new Dimension(WIDTH/3, HEIGHT-OFFSET));
            inputPanel.setBorder(inputTitledBorder);
            inputPanel.setVisible(true);

        InputSection name = new InputSection("Digite aqui seu nome: ");
        inputPanel.add(name);

        InputSection birthday = new InputSection("Digite aqui sua Data de Nascimento: ");
        inputPanel.add(birthday);

        InputSection cpf = new InputSection("Digite aqui seu CPF: ");
        inputPanel.add(cpf);

        JPanel buttonsPanel = new JPanel();
        JButton add = new JButton("Adicionar");
            add.addActionListener(e -> {
                this.service.add(
                    name.getText(),
                    birthday.getText(),
                    cpf.getText()
                );
            });
        JButton remove = new JButton("Remover");
        JButton edit = new JButton("Editar");
        //button.setAction(e -> );
         

        TitledBorder tableTitledBorder = new TitledBorder(roundedLine, "Tabela de Dados");
        JPanel tablePanel = new JPanel();
            tablePanel.setPreferredSize(new Dimension((2*WIDTH/3 -OFFSET) , HEIGHT-OFFSET));
            tablePanel.setBorder(tableTitledBorder);
            tablePanel.setVisible(true); 

            //add(inputPanel);
            //add(tablePanel);
            //setVisible(true);

        // Cria um JSplitPane para dividir a janela (esquerda: inputPanel, direita: tableScrollPane)
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, tablePanel);
        splitPane.setDividerLocation(0.33); // Divisória na posição 1/3
        splitPane.setEnabled(false); // Prioriza redimensionamento da tabela
    
        add(splitPane);
        setVisible(true);
        
    }   
}
