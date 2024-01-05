import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
 
public class Main extends JFrame {
 
    public static void main(String[] args) {
        //  CULORI
        Color camel = new Color(193, 154, 107);
        Color champagneColor = new Color(247, 231, 206);
        Color almond = new Color(230, 221, 216);
        Color cf = new Color(166, 123, 91);
        Color black = new Color(0, 0, 0);
 
 
        //  PANELURI
        JPanel display = new JPanel(); // IN CARE SE AFISEAZA MESAJE
        display.setBounds(0, 0, 500, 500);
        display.setBackground(camel);
 
        JPanel pipeline = new JPanel(null); // PENTRU SWITCH
        pipeline.setBounds(500, 0, 500, 500);
        pipeline.setBackground(cf);
        JPanel pipe1 = new JPanel(null);
        pipe1.setBounds(700, 0, 20, 500);
        pipe1.setBackground(black);
        JPanel pipe2 = new JPanel(null);
        pipe2.setBounds(780, 0, 20, 500);
        pipe2.setBackground(black);
        JPanel food_switch = new JPanel(null);
        food_switch.setBounds(700, 230, 100, 20);
        food_switch.setBackground(Color.DARK_GRAY);
 
        JPanel ActionButton = new JPanel(new BorderLayout()); //START/STOP ; INTERVAL;POUR FOOD
        ActionButton.setBounds(500, 500, 500, 500);
        ActionButton.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 35));
        ActionButton.setBackground(almond);
 
        JPanel tastatura = new JPanel(new BorderLayout()); // TASTE 0-9;ENTER
        tastatura.setBounds(0, 500, 500, 500);
        tastatura.setLayout(new GridLayout(4, 3, 3, 3));
        tastatura.setBackground(champagneColor);
 
        JLabel label = new JLabel();
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));
        label.setForeground(Color.BLACK);
        label.setBackground(Color.white);
        label.setOpaque(true);
        label.setVerticalAlignment(0);
        label.setHorizontalAlignment(0);
        display.add(label);
 
        final boolean[] isStarted = {false}; // PENTRU START/STOP
        final boolean[] isInIntervalMode = {false}; // PENTRU INTERVAL MINUTE
        StringBuilder inputStringBuilder = new StringBuilder();
 
        // BUTOANE 0-9
        for (int i = 9; i >= 0; i--) {
            JButton button = new JButton(Integer.toString(i));
            button.setFocusable(false);
            int finalI = i;
            button.addActionListener(e -> {
                if (isInIntervalMode[0]) {
                    inputStringBuilder.append(finalI);
                    label.setText(inputStringBuilder.toString());
                }
            });
            tastatura.add(button);
        }
 
        // ENTER
        JButton Enterbutton = new JButton();
        Enterbutton.setText("ENTER");
        Enterbutton.setFocusable(false);
        tastatura.add(Enterbutton);
 
        // CREARE MENIU
        JButton START = new JButton();
        START.setText("START/STOP");
        START.setFocusable(false);
        START.setPreferredSize(new Dimension(300, 100));
        ActionButton.add(START);
 
        JButton POUR = new JButton();
        POUR.setText("POUR FOOD");
        POUR.setFocusable(false);
        POUR.setPreferredSize(new Dimension(300, 100));
        ActionButton.add(POUR);
 
        JButton INTERVAL = new JButton();
        INTERVAL.setText("INTERVAL MINUTES");
        INTERVAL.setFocusable(false);
        INTERVAL.setPreferredSize(new Dimension(300, 100));
        ActionButton.add(INTERVAL);
 
 
        // FRAME
        JFrame frame = new JFrame();
        frame.setTitle("Pet Feeder Processor");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
 
        ActionListener buttonActionListener = e -> {
            if (e.getSource() == START) {
                if (!isStarted[0]) {
                    label.setText("HELLO WORLD");
                    isStarted[0] = true;
                } else {
                    int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to stop?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        label.setText("Shutting down...");
                        isStarted[0] = false;
                        inputStringBuilder.setLength(0);
                    }
                }
            } else if (isStarted[0]) {
                if (e.getSource() == POUR) {
                    label.setText("POURING FOOD....");
                    food_switch.setBounds(700, 230, 20, 100);
                    POUR.setEnabled(false);
                    Timer timer = new Timer(5000, timerEvent -> {
                        label.setText("Your pet has been fed");
                        food_switch.setBounds(700, 230, 100, 20);
                        POUR.setEnabled(true);
                    });
                    timer.setRepeats(false);
                    timer.start();
                } else if (e.getSource() == INTERVAL) {
                    label.setText("SET INTERVAL: ");
                    isInIntervalMode[0] = true;
                    ActionListener buttonActionListener2 = e2 -> {
                        if (e2.getSource() == Enterbutton) {
                            label.setText("Your pet will be fed every " + inputStringBuilder + " minutes");
                            isInIntervalMode[0]=false;
                        } else {
                            char keyPressed = ((JButton) e2.getSource()).getText().charAt(0);
                            inputStringBuilder.append(keyPressed);
                            label.setText("SET INTERVAL: " + inputStringBuilder);
                        }
                    };
 
                    Enterbutton.addActionListener(buttonActionListener2);
                }
            }
        };
        // ADAUGARE ELEMENTE PENTRU DISPLAY
        START.addActionListener(buttonActionListener);
        POUR.addActionListener(buttonActionListener);
        INTERVAL.addActionListener(buttonActionListener);
        Enterbutton.addActionListener(buttonActionListener);
 
        frame.add(food_switch);
        frame.add(pipe1);
        frame.add(pipe2);
 
        // ADAUGARE PANELURI LA FRAME
        frame.add(tastatura);
        frame.add(display);
        frame.add(ActionButton);
        frame.add(pipeline);
    }
}