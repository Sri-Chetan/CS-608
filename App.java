import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    static int M;

    App(int size) {
        M = size;

        JLabel[][] jLabel = new JLabel[M][M];
        int[][] board = new int[M][M];

        JFrame jFrame = new JFrame("NQueen Visualizer.");
        jFrame.setLayout(new GridLayout(M, M));
        jFrame.setSize(500, 500);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < M; ++j) {
                jLabel[i][j] = new JLabel("(" + i + "," + j + ")");
                jLabel[i][j].setBackground(Color.LIGHT_GRAY);
                jLabel[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                jLabel[i][j].setSize(50, 50);
                jLabel[i][j].setOpaque(true);
                jFrame.add(jLabel[i][j]);
            }
        }

        jFrame.setVisible(true);

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            findSolution(0, jLabel, board);
            printSolution();
        }).start();
    }

    static void printSolution() {
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < M; ++j) {
                System.out.printf("%d ", board[i][j]);
            }
            System.out.printf("\n");
        }
    }

    static boolean isSafe(int row, int col, int[][] board) {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < col; ++i)
            if (board[row][i] == 1)
                return false;
        for (int i = row, j = col; i >= 0 && j >= 0; --i, --j) {
            if (board[i][j] == 1)
                return false;
        }
        for (int i = row, j = col; i < M && j >= 0; ++i, --j) {
            if (board[i][j] == 1)
                return false;
        }
        return true;
    }

    static boolean findSolution(int col, JLabel[][] jLabel, int[][] board) {
        if (col >= M)
            return true;
        for (int i = 0; i < M; ++i) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isSafe(i, col, board)) {
                board[i][col] = 1;
                jLabel[i][col].setBackground(Color.GREEN);
                if (findSolution(col + 1, jLabel, board))
                    return true;
                board[i][col] = 0;
                jLabel[i][col].setBackground(Color.YELLOW);
            }
        }
        return false;
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App(4);
            }
        });
    }
}
