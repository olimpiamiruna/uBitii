import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Main extends JFrame  {



    public static void main(String[] args) {

        /// C U L O R I
        Color camel = new Color(193, 154, 107);
        Color champagneColor = new Color(247, 231, 206);
        Color almond = new Color(230, 221, 216);
        Color cf = new Color(166, 123, 91);



        // P A N E L U R I

        JPanel display = new JPanel(); //// IN CARE SE AFISEAZA MESAJE
        display.setBounds(0, 0, 500, 500);
        display.setBackground(camel);

        JPanel pipeline = new JPanel(new BorderLayout()); //// PENTRU SWITCH
        pipeline.setBounds(500, 0, 500, 500);
        pipeline.setBackground(cf);


        JPanel ActionButton = new JPanel(new BorderLayout()); // START/STOP ; INTERVAL ; POUR FOOD
        ActionButton.setBounds(500, 500, 500, 500);
        ActionButton.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 35));
        ActionButton.setBackground(almond);

        JPanel tastatura = new JPanel(new BorderLayout());        //// TASTE 0-9; ENTER
        tastatura.setBounds(0, 500, 500, 500);
        tastatura.setLayout(new GridLayout(4, 3, 3, 3));
        tastatura.setBackground(champagneColor);



        /// trebuie facut la mijloc; nu inteleg de ce nuu merge??
        JLabel label = new JLabel();
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));
        label.setForeground(Color.BLACK);
        label.setBackground(Color.white);
        label.setOpaque(true);
        label.setVerticalAlignment(0);
        label.setHorizontalAlignment(0);
        display.add(label);

        final boolean[] isStarted = {false}; // pt start/stop
        StringBuilder inputStringBuilder = new StringBuilder();

        //  B U T O A N E 0-9
        for (int i = 9; i >= 0; i--) {
            JButton button = new JButton(Integer.toString(i));
            button.setFocusable(false);
            int finalI = i;
            button.addActionListener(e -> {
                inputStringBuilder.append(finalI);
                label.setText(inputStringBuilder.toString());
            });
            tastatura.add(button);
        }

        /// E N T E R
        JButton Enterbutton = new JButton();
        Enterbutton.setText("ENTER");
        Enterbutton.setFocusable(false);
        tastatura.add(Enterbutton);

        ////// CREARE "MENIU"
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



        //// F R A M E
        JFrame frame = new JFrame();
        frame.setTitle("Pet Feeder Processor");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);




        ////trb schimbat astfel incat pour si interval sa fie folosite doar cand sunt in starea de start
        ActionListener buttonActionListener = e -> {
            if (e.getSource() == START) {
                if (!isStarted[0]) {
                    label.setText("HELLO WORLD");
                    isStarted[0] = true;
                } else {
                    label.setText("Shutting down...");
                    isStarted[0] = false;
                    frame.dispose();
                    // inputStringBuilder = "";   --- cum fac sa mearga?????
                }
            } else if (isStarted[0]) {  // Adaugă condiția pentru a verifica dacă sistemul este pornit
                if (e.getSource() == POUR) {
                    label.setText("POURING FOOD....");
                    POUR.setEnabled(false);
                    Timer timer = new Timer(5000, timerEvent -> label.setText("Your pet has been fed"));
                    timer.setRepeats(false);
                    timer.start();
                } else if (e.getSource() == INTERVAL) {
                    label.setText("SET INTERVAL: ");
                    ActionListener buttonActionListener2 = e2 -> { // PENTRU APASAREA ALTOR TASTE
                        if (e2.getSource() == Enterbutton) {
                            label.setText("Your pet will be fed every " + inputStringBuilder + " minutes" );
                        } else {
                            char keyPressed = ((JButton) e2.getSource()).getText().charAt(0);
                            inputStringBuilder.append(keyPressed);
                            label.setText("SET INTERVAL: " + inputStringBuilder  );
                        }
                    };

                    Enterbutton.addActionListener(buttonActionListener2);
                }
            }
        };


        //// ADAUGARE ELEMENTE PENTRU DISPLAY
        START.addActionListener(buttonActionListener);
        POUR.addActionListener(buttonActionListener);
        INTERVAL.addActionListener(buttonActionListener);
        Enterbutton.addActionListener(buttonActionListener);





        //ADAUGARE PANELURI LA FRAME
        frame.add(tastatura);
        frame.add(display);
        frame.add(ActionButton);
        frame.add(pipeline);

    }

}