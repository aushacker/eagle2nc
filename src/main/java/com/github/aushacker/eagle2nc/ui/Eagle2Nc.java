/*
 * Copyright 2019 Stephen Davies
 *
 * This file is part of eagle2nc.
 *
 * Eagle2nc is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Eagle2nc is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Eagle2nc. If not, see <https://www.gnu.org/licenses/>.
 */
package com.github.aushacker.eagle2nc.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
//import javax.swing.SwingUtilities;

import com.github.aushacker.eagle2nc.model.Board;

/**
 * Top level UI component i.e. the application.
 *
 * @author Stephen Davies
 * @since August 2019
 */
public class Eagle2Nc extends JFrame {

    private static final long serialVersionUID = -8114183127239949446L;

    private UIPreferences preferences;

    private BoardPanel bp = new BoardPanel();

    private JMenuBar mb = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenuItem openItem = new JMenuItem("Open...");
    private JMenuItem exitItem = new JMenuItem("Exit");
    private JMenu codeMenu = new JMenu("GCode");
    private JMenuItem genItem = new JMenuItem("Generate");

    public Eagle2Nc() {
        super("Eagle2nc");

        preferences = new UIPreferences();

        //open(new File("data/astable_555.brd"));
        open(new File("data/Arduino_MEGA2560_ref.brd"));

        Container contentPane = getContentPane();

        initializeMenus();

        contentPane.add(bp, BorderLayout.CENTER);

        // Window resizes are persistent
        contentPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                preferences.setHeight(getHeight());
                preferences.setWidth(getWidth());
            }
        });

        //SwingUtilities.invokeLater(new InitializationProcess(tp));
    }

    private void fileOpen() {
        
        JFileChooser chooser;

//      if (home != null && home.trim().length() > 0) {
//          chooser = new JFileChooser(home);
//      } else {
            chooser = new JFileChooser();
//      }

        int result = chooser.showOpenDialog(this);
//      File script = chooser.getSelectedFile();
//      if (result == JFileChooser.APPROVE_OPTION && script.exists()) {
//          runPanel.openFile(script);
//      }
    }

    private void generate() {
        
    }

    private void initializeMenus() {
        mb.add(fileMenu);
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        mb.add(codeMenu);
        codeMenu.add(genItem);
        setJMenuBar(mb);
        
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileOpen();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });        

        genItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generate();
            }
        });        
    }

    private void open(File f) {
        try {
            bp.setBoard(new Board(f));
        }
        catch (Exception e) {
            // TODO prompt user
            e.printStackTrace(System.err);
        }
    }

    private void shutdown() {
    }

    public static void main(String[] args) {
        Eagle2Nc f = new Eagle2Nc();

        f.setBounds(100, 100, f.preferences.getWidth(), f.preferences.getHeight());
        f.setVisible(true);
        f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                f.shutdown();
                System.exit(0);
            }
        });
    }
}
