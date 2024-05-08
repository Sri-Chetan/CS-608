import javax.swing.*;

import Visualizer.SortingVisualizer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class AlgorithmVisualizer {
    JFrame mainFrame;
    JFrame searchFrame, sortFrame, dijkstraFrame, nqueenFrame;
    JLabel title, subtitle, searchTitle, sortTitle, dijkstraTitle, nqueenTitle;

    public AlgorithmVisualizer() {
        mainFrame = new JFrame("Algorithm Visualizer");
        mainFrame.setSize(1000, 450);
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        title = new JLabel("ALGORITHMS VISUALIZER");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Calibri", Font.BOLD, 50));
        title.setBounds(350, 10, 900, 50);
        mainFrame.add(title);

        subtitle = new JLabel("Group - 14");
        subtitle.setForeground(Color.BLACK);
        subtitle.setFont(new Font("Calibri", Font.BOLD, 20));
        subtitle.setBounds(600, 50, 900, 50);
        mainFrame.add(subtitle);

        searchTitle = new JLabel("For Searching Algorithms : ");
        searchTitle.setForeground(Color.BLACK);
        searchTitle.setFont(new Font("Calibri", Font.BOLD, 30));
        searchTitle.setBounds(50, 150, 500, 50);
        mainFrame.add(searchTitle);

        JButton search = new JButton("Search");
        search.setBounds(390, 155, 250, 30);
        mainFrame.add(search);

        sortTitle = new JLabel("For Sorting Algorithms : ");
        sortTitle.setForeground(Color.BLACK);
        sortTitle.setFont(new Font("Calibri", Font.BOLD, 30));
        sortTitle.setBounds(50, 250, 500, 50);
        mainFrame.add(sortTitle);

        JButton sort = new JButton("Sorting");
        sort.setBounds(360, 255, 250, 30);
        mainFrame.add(sort);

        dijkstraTitle = new JLabel("For Dijkstra's Algorithms : ");
        dijkstraTitle.setForeground(Color.BLACK);
        dijkstraTitle.setFont(new Font("Calibri", Font.BOLD, 30));
        dijkstraTitle.setBounds(50, 350, 500, 50);
        mainFrame.add(dijkstraTitle);

        JButton dijkstra = new JButton("Dijkstra's Algorithm");
        dijkstra.setBounds(390, 355, 250, 30);
        mainFrame.add(dijkstra);

        nqueenTitle = new JLabel("For NXN Queen : ");
        nqueenTitle.setForeground(Color.BLACK);
        nqueenTitle.setFont(new Font("Calibri", Font.BOLD, 30));
        nqueenTitle.setBounds(50, 450, 500, 50);
        mainFrame.add(nqueenTitle);

        JButton nqueen = new JButton("NXN Queens");
        nqueen.setBounds(280, 455, 250, 30);
        mainFrame.add(nqueen);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new SearchVisualizer().setVisible(true);
                });
            }
        });

        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the sorting algorithms frame
                // Start the sorting thread
                SortingVisualizer.startSort("Bubble");
            }
        });

        dijkstra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new MouseGraph().setVisible(true);
                });
            }
        });

        nqueen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNQueenFrame();
            }
        });

        mainFrame.setVisible(true);
    }

    private void openDijkstraFrame() {
        dijkstraFrame = new JFrame("Dijkstra's Algorithm");
        JLabel label = new JLabel("This is the Dijkstra's Algorithm Frame");
        label.setHorizontalAlignment(JLabel.CENTER);
        dijkstraFrame.getContentPane().add(label);
        dijkstraFrame.setSize(800, 600);
        dijkstraFrame.setVisible(true);
    }

    private void openNQueenFrame() {
        nqueenFrame = new JFrame("N-Queens Board Size");
        nqueenFrame.setLayout(new FlowLayout());

        JButton button4x4 = new JButton("4x4");
        JButton button8x8 = new JButton("8x8");

        nqueenFrame.add(button4x4);
        nqueenFrame.add(button8x8);

        button4x4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new App(4).setVisible(true);
                });
                nqueenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the board size selection frame
            }
        });

        button8x8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new App(8).setVisible(true);
                });
                nqueenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the board size selection frame
            }
        });

        nqueenFrame.setSize(300, 150);
        nqueenFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nqueenFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AlgorithmVisualizer();
            }
        });
    }
}