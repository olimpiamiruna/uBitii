import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main extends JFrame  {


    public static void main(String[] args) {

        JPanel tastatura = new JPanel();
        tastatura.setBounds(0, 0, 500, 500);
        tastatura.setLayout(new GridLayout(4, 3, 3, 3));
        Color champagneColor = new Color(247, 231, 206);
        tastatura.setBackground(champagneColor);

        for (int i = 9; i >= 0; i--) {
            JButton button = new JButton(Integer.toString(i));
            button.setFocusable(false);
            int finalI = i;
            button.addActionListener(e -> System.out.printf("A fost apasata tasta %d\n", finalI));
            tastatura.add(button);
        }

        JButton Enterbutton = new JButton();
        Enterbutton.setText("ENTER");
        Enterbutton.setFocusable(false);
        Enterbutton.addActionListener(e -> System.out.println("A fost apasata tasta  enter"));
        tastatura.add(Enterbutton);


        JPanel display = new JPanel();
        display.setBounds(500, 0, 500, 500);
        Color camel = new Color(193, 154, 107);
        display.setBackground(camel);


        /// trebuie facut la mijloc; nu inteleg de ce nuu merge??
        JLabel label = new JLabel();
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));
        label.setForeground(Color.BLACK);
        label.setBackground(Color.white);
        label.setOpaque(true);
        label.setVerticalAlignment(0);
        label.setHorizontalAlignment(0);
        display.add(label);


        JPanel ActionButton = new JPanel();
        ActionButton.setBounds(0, 500, 500, 500);
        ActionButton.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 35));
        Color cf = new Color(166, 123, 91);
        ActionButton.setBackground(cf);

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

        final boolean[] isStarted = {false}; // pt start/stop


        ////trb schimbat astfel incat pour si interval sa fie folosite doar cand sunt in starea de start
        ActionListener buttonActionListener = e -> {
            if (e.getSource() == START) {
                if (!isStarted[0]) {
                    label.setText("HELLO WORLD");
                    isStarted[0] = true;
                }
                else {
                    label.setText("Shutting down...");
                    isStarted[0] = false;
                }
            } else if (e.getSource() == POUR) {
                label.setText("POURING FOOD....");
            } else if (e.getSource() == INTERVAL) { // cv de afisare a tastelor pana se apasa ENTER
                label.setText("SET INTERVAL: ");
            }
        };


        JPanel processor = new JPanel();
        processor.setBounds(50, 10, 500, 500);
        Color almond = new Color(230, 221, 216);
        processor.setBackground(almond);

        START.addActionListener(buttonActionListener);
        POUR.addActionListener(buttonActionListener);
        INTERVAL.addActionListener(buttonActionListener);

        JFrame frame = new JFrame();
        frame.setTitle("Pet Feeder Processor");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(tastatura);
        frame.add(display);
        frame.add(ActionButton);
        frame.add(processor);

    }

    }
