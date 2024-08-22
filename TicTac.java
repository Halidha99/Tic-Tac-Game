import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTac extends JFrame {
    private JButton[] buttons = new JButton[9];
    private boolean isXTurn = true;
    private JLabel nameLabel;

    public TicTac() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nameLabel = new JLabel("X's turn");
        nameLabel.setFont(new Font("", Font.BOLD, 20));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);

        setLayout(new BorderLayout());
        add(nameLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("", Font.PLAIN, 60));
            int index = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    click(index);
                }
            });
            panel.add(buttons[i]);
        }
        add(panel, BorderLayout.CENTER);
    }

    private void click(int index) {
        if (!buttons[index].getText().equals("")) {
            return;
        }

        if (isXTurn) {
            buttons[index].setText("X");
            nameLabel.setText("O's turn");
        } else {
            buttons[index].setText("O");
            nameLabel.setText("X's turn");
        }

        isXTurn = !isXTurn;

        checkWin();
    }

    private void checkWin() {
        String[][] ticboard = new String[3][3];
        for (int i = 0; i < 9; i++) {
            ticboard[i / 3][i % 3] = buttons[i].getText();
        }

        for (int i = 0; i < 3; i++) {
            if (currentboard(ticboard[i][0], ticboard[i][1], ticboard[i][2]) ||
                currentboard(ticboard[0][i], ticboard[1][i], ticboard[2][i])) {
                showWinner();
                return;
            }
        }

        if (currentboard(ticboard[0][0], ticboard[1][1], ticboard[2][2]) ||
            currentboard(ticboard[0][2], ticboard[1][1], ticboard[2][0])) {
            showWinner();
        }
    }

    private boolean currentboard(String x1, String x2, String x3) {
        return !x1.equals("") && x1.equals(x2) && x2.equals(x3);
    }

    private void showWinner() {
        String winner = isXTurn ? "O" : "X";
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
        nameLabel.setText("Player " + winner + " wins!");
        resetButtons();
    }

    private void resetButtons() {
        for (JButton button : buttons) {
            button.setText("");
        }
        isXTurn = true;
        nameLabel.setText("X's turn");
    }

    public static void main(String[] args) {
        TicTac tic = new TicTac();
        tic.setVisible(true);
    }
}
