import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SearchVisualizer extends JFrame {

    private static final Color BG_COLOR = Color.BLUE;
    private int comparingIndex = -1;
    private int foundIndex = -1;

    private List<Integer> array;

    public SearchVisualizer() {
        initializeUI();
        array = new ArrayList<>();
    }

    private void initializeUI() {
        JButton generateArrayButton = new JButton("Generate Array");
        generateArrayButton.addActionListener(e -> generateArray());

        JButton linearSearchButton = new JButton("Linear Search");
        linearSearchButton.addActionListener(e -> {
            int searchNumber = getSearchNumber("Enter number for Linear Search");
            if (searchNumber != -1) {
                doLinearSearch(searchNumber);
            }
        });

        JButton binarySearchButton = new JButton("Binary Search");
        binarySearchButton.addActionListener(e -> {
            int searchNumber = getSearchNumber("Enter number for Binary Search");
            if (searchNumber != -1) {
                doBinarySearch(searchNumber);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateArrayButton);
        buttonPanel.add(linearSearchButton);
        buttonPanel.add(binarySearchButton);

        add(buttonPanel, BorderLayout.NORTH); // Place buttons at the top

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setTitle("Search Visualizer");
        setLocationRelativeTo(null);
    }

    private void generateArray() {
        array.clear();
        for (int i = 0; i < 20; i++) {
            array.add((int) (Math.random() * 100) + 1);
        }

        comparingIndex = -1;
        foundIndex = -1;
        repaint(); // Trigger UI repaint
    }

    private void doLinearSearch(int searchNumber) {
        List<Animation> animationArr = getLinearSearchAnimations(searchNumber, array);
        performAnimations(animationArr);
    }

    private void doBinarySearch(int searchNumber) {
        array.sort(Integer::compareTo); // Ensure the array is sorted
        List<Animation> animationArr = getBinarySearchAnimations(searchNumber, array);
        performAnimations(animationArr);
    }

    private void performAnimations(List<Animation> animationArr) {
        int delay = 500; // Delay in milliseconds

        Timer timer = new Timer(delay, e -> {
            if (!animationArr.isEmpty()) {
                Animation animation = animationArr.remove(0);
                comparingIndex = animation.getIndex();
                repaint(); // Trigger UI repaint after each animation

                if (animation.isFound()) {
                    foundIndex = animation.getIndex();
                    repaint(); // Trigger UI repaint after each animation
                    ((Timer) e.getSource()).stop(); // Stop the timer when the element is found
                    int foundIndex = animation.getIndex();
                    JOptionPane.showMessageDialog(this, "Element found at index: " + foundIndex);
                    foundIndex = -1; // Reset foundIndex after displaying the message
                    comparingIndex = -1; // Reset comparingIndex after displaying the message
                    repaint(); // Trigger UI repaint
                }
            }
        });

        timer.start();
    }

    private int getSearchNumber(String message) {
        String input = JOptionPane.showInputDialog(this, message);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (array.isEmpty()) {
            return;
        }

        int barWidth = getWidth() / array.size();
        for (int i = 0; i < array.size(); i++) {
            int barHeight = array.get(i) * 5;
            int x = i * barWidth;
            int y = getHeight() - barHeight;

            if (i == comparingIndex) {
                g.setColor(Color.YELLOW); // Highlight the comparing element in yellow
            } else if (i == foundIndex) {
                g.setColor(Color.GREEN); // Highlight the found element in green
            } else {
                g.setColor(Color.BLUE); // Default color for other elements
            }

            g.fillRect(x, y, barWidth, barHeight);

            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(array.get(i)), x + barWidth / 2, y - 5);
        }
    }

    private static class Animation {
        private int index;
        private boolean found;

        public Animation(int index, boolean found) {
            this.index = index;
            this.found = found;
        }

        public int getIndex() {
            return index;
        }

        public boolean isFound() {
            return found;
        }
    }

    private List<Animation> getLinearSearchAnimations(int searchNumber, List<Integer> array) {
        List<Animation> animations = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            animations.add(new Animation(i, false)); // Highlight the current element being compared
            if (array.get(i) == searchNumber) {
                animations.add(new Animation(i, true)); // Highlight the found element
                return animations;
            }
        }
        return animations;
    }

    private List<Animation> getBinarySearchAnimations(int searchNumber, List<Integer> array) {
        List<Animation> animations = new ArrayList<>();
        int low = 0;
        int high = array.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            animations.add(new Animation(mid, false)); // Highlight the current middle element

            if (array.get(mid) == searchNumber) {
                animations.add(new Animation(mid, true)); // Highlight the found element
                return animations;
            } else if (array.get(mid) < searchNumber) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // If the element is not found, add a final animation to highlight the last checked position.
        animations.add(new Animation(high, false));

        return animations;
    }

    // Remove the main method
}
