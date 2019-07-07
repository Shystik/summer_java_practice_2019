package com.practice.blueTeam.UI;

import com.practice.blueTeam.DataBase.DataBase;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LevelWindow extends JFrame {
    private JButton closeButton = new JButton("Close");
    private JButton randomButton = new JButton("Random");
    private JButton buildButton = new JButton("Show Solution");
    private JButton nextButton = new JButton("Next");
    private JButton prevButton = new JButton("Prev");
    private JButton returnButton = new JButton("Return to Main menu");
    private int levelNumber;
    private Tile[] tiles;
    private void setTiles() {
        for (int i = 0; i < 16; i++)
        {
            tiles[i] = new Tile(i);
            tiles[i].setIcon(DataBase.getLevelTiles(levelNumber)[i]);
            tiles[i].setBorderPainted(false);
            tiles[i].setFocusPainted(false);
        }
    }
    public  LevelWindow(){}
    public LevelWindow(int levelNumber) {
        super("Пятнашки");
        this.levelNumber = levelNumber;
        DataBase.setCurrentLevel(levelNumber);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        tiles = new Tile[16];
        setTiles();
        int sizeWidth = 800;
        int sizeHeight = 700;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        this.setBounds(locationX, locationY, sizeWidth, sizeHeight);
        JPanel mainPanel = new JPanel();
        JPanel tilesPanel = new JPanel();
        this.setContentPane(mainPanel);
        mainPanel.setLayout(null);
        mainPanel.setSize(this.getSize());
        tilesPanel.setLayout(new GridLayout(4,4,0,0));
        tilesPanel.setLocation(mainPanel.getWidth()/16,mainPanel.getHeight()/6 );
        tilesPanel.setSize(400, 400);
        for (int i = 0; i < 16; i++)
        {
            tilesPanel.add(tiles[i]);
            tiles[i].setIcon(new ImageIcon(DataBase.getLevelTiles(levelNumber)[i].getImage().getScaledInstance(tilesPanel.getWidth()/4, tilesPanel.getHeight()/4, Image.SCALE_SMOOTH)));
        }
        mainPanel.add(tilesPanel);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLocation(tilesPanel.getWidth() + 100, tilesPanel.getY() -10);
        buttonsPanel.setSize(200,300);
        buttonsPanel.setLayout(new GridLayout(5,1,5,5));
        buttonsPanel.add(randomButton);
        buttonsPanel.add(buildButton);
        JPanel nextPrevPanel = new JPanel();
        nextPrevPanel.setLayout(new GridLayout(1,2,0,0));
        nextPrevPanel.add(prevButton);
        nextPrevPanel.add(nextButton);
        buttonsPanel.add(nextPrevPanel);
        buttonsPanel.add(closeButton);
        buttonsPanel.add(returnButton);
        mainPanel.add(buttonsPanel);
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });
        returnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DataBase.getLevelWindows(levelNumber).setVisible(false);
                MainWindow.getUI().setVisible(true);
            }
        });
    }
}
