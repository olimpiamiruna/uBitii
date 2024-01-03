import javax.swing.*;
import java.awt.*;


public class Main extends JFrame  {


    public static void main(String[] args) {
        JLabel label = new JLabel();
        label.setText("T a s t a t u r a");
        label.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        label.setHorizontalAlignment(0);
        label.setVerticalAlignment(0);


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

        JPanel cvbutoane = new JPanel();
        cvbutoane.setBounds(0, 500, 500, 500);
        Color cf = new Color(166, 123, 91);
        cvbutoane.setBackground(cf);

        JPanel processor = new JPanel();
        processor.setBounds(500, 0, 500, 500);
        Color almond = new Color(230, 221, 216);
        processor.setBackground(almond);

        JFrame frame = new JFrame();
        frame.setTitle("Pet Feeder Processor");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(tastatura);
        frame.add(display);
        frame.add(cvbutoane);
        frame.add(processor);

    }

    }
