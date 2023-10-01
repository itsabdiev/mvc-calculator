import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Viewer {
    private Controller controller;
    private short FRAME_WIDTH = 324;
    private short FRAME_HEIGHT = 399;
    private Color MAIN_COLOR = new Color(49, 54, 52);
    private Color GREEN_COLOR = new Color(10, 225, 127);
    private Color BACKGROUND_COLOR = new Color(156,164,164);
    private Color WHITE_COLOR = new Color(250,250,250);
    private Font BUTTON_FONT = new Font("Lucida Sans", Font.BOLD, 15);
    private short BUTTON_WIDTH = 60;
    private short BUTTON_HEIGHT = 60;
    private JTextField mainTextField;
    private JTextField secondaryTextField;
    
    public Viewer() {
        controller = new Controller(this);
        JFrame frame = new JFrame("Calculator");
        frame.add(initScene());
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        frame.setBackground(Color.BLUE);
        frame.setIconImage(new ImageIcon(Viewer.class.getResource("/src/c.png")).getImage());
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private JPanel initScene() {
        JPanel panel = new JPanel();
        panel.setBounds(0,0 ,FRAME_WIDTH - 18,FRAME_HEIGHT - 47);
        panel.setBackground(BACKGROUND_COLOR);
        panel.setLayout(null);
        initTextFields(panel);
        initButtons(panel);
        return panel;
    }

    private void initTextFields(JPanel panel) {
        secondaryTextField = initTextField(3);
        mainTextField = initTextField(57);
	    mainTextField.setText("0");
        panel.add(secondaryTextField);
        panel.add(mainTextField);
        initButtons(panel);
    }

    private JTextField initTextField(int y) {
        JTextField textField = new JTextField();
        textField.setFont(BUTTON_FONT);
        textField.setForeground(MAIN_COLOR);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBackground(WHITE_COLOR);
        textField.setBounds(3, y, 300, 50);
        textField.setEditable(false);
        return textField;
    }

    private void initButtons(JPanel panel) {
        byte symbolIndex = 0;
        String[] symbols = {"C","+","-","x","7","4","1","/","8","5","2","0","9","6","3",".","<","+-"};
        for (byte i = 0;i < 5;i++) {
            for (byte y = 0;y < 4 ;y++) {
                if (i == 4 && (y == 2 || y == 3)) {
                    continue;
                }
                panel.add(createButton(symbols[symbolIndex],symbols[symbolIndex],
                        i*BUTTON_WIDTH+3,(y*BUTTON_HEIGHT)+110,BUTTON_WIDTH,BUTTON_HEIGHT,MAIN_COLOR));
                symbolIndex++;
            }
        }
        JButton equalsButton = createButton("=","=",243,230,BUTTON_WIDTH,BUTTON_HEIGHT*2,GREEN_COLOR);
        panel.add(equalsButton);
    }

    private JButton createButton(String text,String actionCommand,int x,int y,int width,int height,Color color) {
        JButton button = new JButton();
        button.setBounds(x,y,width,height);
        button.setBackground(color);
        button.setFont(BUTTON_FONT);
        button.setBorder(new LineBorder(BACKGROUND_COLOR,1));
        button.setForeground(WHITE_COLOR);
        button.setText(text);
        button.setActionCommand(actionCommand.equals("+-") ? "Negate" : actionCommand);
        button.addActionListener(controller);
        return button;
    }

    public void setSecondaryTextField(String command) {
        secondaryTextField.setText(command);
    }

    public void setMainTextField(String command) {
        mainTextField.setText(command);
    }

    public JTextField getMainTextField() {
        return mainTextField;
    }
}
