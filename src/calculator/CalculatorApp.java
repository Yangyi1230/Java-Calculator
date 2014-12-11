package calculator;




import java.awt.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;

class CalculatorWindow extends JFrame {
	JLabel disp;
	JButton cancelButton, equalButton, dotButton;
	JButton signButton, addButton, subButton, mulButton, divButton;
	JButton[] numButton;

	CalculatorWindow(){
		this.setSize(400, 440);
		this.setResizable(false);
		this.setTitle("Java Calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container wnd = getContentPane();
		wnd.setLayout(null);

		JPanel dispPanel = new JPanel();
		JPanel controlPanel = new JPanel();
		wnd.add(dispPanel);
		wnd.add(controlPanel);
		dispPanel.setBounds(0,0,400,60);
		controlPanel.setBounds(0,60,400,360);

		dispPanel.setBorder(new LineBorder(Color.GRAY));
		disp = new JLabel("");
	    disp.setSize(new Dimension(380, 60));
		Font dispFont = new Font("Arial", Font.PLAIN, 24);
		disp.setFont(dispFont);
		disp.setHorizontalAlignment(SwingConstants.RIGHT);
		dispPanel.setLayout(null);
		dispPanel.add(disp);
		

		GridBagLayout gridbag = new GridBagLayout();
		controlPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);//设置按钮排列从右往左
		controlPanel.setLayout(gridbag);//设置布局
		controlPanel.setBorder(new EmptyBorder(20,10,20,10));//设置面板边界

		cancelButton = new JButton("c");
		signButton = new JButton("+/-");
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		equalButton = new JButton("=");
		numButton = new JButton[10];
		for(int i=0; i<10; i++) {
			numButton[i] = new JButton(String.valueOf(i));
		}
		dotButton = new JButton(".");

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3,2,3,2);
		c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1.0;
	    c.weighty = 1.0;
	    gridbag.setConstraints(mulButton, c);
		controlPanel.add(mulButton);
	    gridbag.setConstraints(divButton, c);
		controlPanel.add(divButton);
	    gridbag.setConstraints(signButton, c);
		controlPanel.add(signButton);
		c.gridwidth = GridBagConstraints.REMAINDER;
	    gridbag.setConstraints(cancelButton, c);
		controlPanel.add(cancelButton);

		c.gridwidth = 1;
	    gridbag.setConstraints(subButton, c);
		controlPanel.add(subButton);
	    gridbag.setConstraints(numButton[9], c);
		controlPanel.add(numButton[9]);
	    gridbag.setConstraints(numButton[8], c);
		controlPanel.add(numButton[8]);
		c.gridwidth = GridBagConstraints.REMAINDER;
	    gridbag.setConstraints(numButton[7], c);
		controlPanel.add(numButton[7]);

		c.gridwidth = 1;
	    gridbag.setConstraints(addButton, c);
		controlPanel.add(addButton);
	    gridbag.setConstraints(numButton[6], c);
		controlPanel.add(numButton[6]);
	    gridbag.setConstraints(numButton[5], c);
		controlPanel.add(numButton[5]);
		c.gridwidth = GridBagConstraints.REMAINDER;
	    gridbag.setConstraints(numButton[4], c);
		controlPanel.add(numButton[4]);

		c.gridwidth = 1;
		c.gridheight = 2;
	    gridbag.setConstraints(equalButton, c);
		controlPanel.add(equalButton);	
		c.gridheight = 1;
	    gridbag.setConstraints(numButton[3], c);
		controlPanel.add(numButton[3]);
	    gridbag.setConstraints(numButton[2], c);
		controlPanel.add(numButton[2]);
		c.gridwidth = GridBagConstraints.REMAINDER;
	    gridbag.setConstraints(numButton[1], c);
		controlPanel.add(numButton[1]);

		c.gridwidth = 1;
		gridbag.setConstraints(dotButton, c);
		controlPanel.add(dotButton);
		c.gridwidth = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(numButton[0], c);
		controlPanel.add(numButton[0]);

		this.addWindowListener(new WindowAdapter() {
	       	public void windowOpened(WindowEvent e) {
	              equalButton.requestFocus();
	       	}
	    });
	}
	
	class CalculatorHotKey extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			char key = e.getKeyChar();
			if (key >= '0' && key <= '9') {
				numButton[key - '0'].doClick();
			} else switch(e.getKeyChar()) {
				case 'c':
				cancelButton.doClick();
				break;
				case '~':
				signButton.doClick();
				break;
				case '+':
				addButton.doClick();
				break;
				case '-':
				subButton.doClick();
				break;
				case '*':
				mulButton.doClick();
				break;
				case '/':
				divButton.doClick();
				break;
				case '=':
				equalButton.doClick();
				break;
				case '.':
				dotButton.doClick();
				break;
				case '\n':
				equalButton.doClick();
				break;
			}
		}

	}

	void setController(CalculatorController control) {
		control.setDisplayWindow(disp);

		CalculatorHotKey keyMap = new CalculatorHotKey();

		cancelButton.addKeyListener(keyMap);
		cancelButton.addActionListener(control);
		cancelButton.setActionCommand("c");

		signButton.addKeyListener(keyMap);
		signButton.addActionListener(control);
		signButton.setActionCommand("~");

		addButton.addKeyListener(keyMap);
		addButton.addActionListener(control);
		addButton.setActionCommand("+");

		subButton.addKeyListener(keyMap);
		subButton.addActionListener(control);
		subButton.setActionCommand("-");

		mulButton.addKeyListener(keyMap);
		mulButton.addActionListener(control);
		mulButton.setActionCommand("*");

		divButton.addKeyListener(keyMap);
		divButton.addActionListener(control);
		divButton.setActionCommand("/");

		equalButton.addKeyListener(keyMap);
		equalButton.addActionListener(control);
		equalButton.setActionCommand("=");

		dotButton.addKeyListener(keyMap);
		dotButton.addActionListener(control);
		dotButton.setActionCommand(".");

		for(int i=0; i<10; i++) {
			numButton[i].addKeyListener(keyMap);
			numButton[i].addActionListener(control);
			numButton[i].setActionCommand(String.valueOf(i));
		}
	}
}



class CalculatorController implements ActionListener {  
	  
    Calculator calc = new Calculator();  
    JLabel window;  
  
    public void actionPerformed(ActionEvent e) {  
        char key = e.getActionCommand().charAt(0);  
        calc.keyPressed(key);  
        if (window!=null) {  
            window.setText(calc.getExpression(key));  
        }  
    }  
  
    void setDisplayWindow(JLabel w) {  
        window = w;  
    }  
}  


class Calculator {  
    JavaScript xxx;
	String expression = "";  
	String num;
    // TODO: modify the method to return a proper expression   
    //  which will be shown in the screen of the calculator  
    String getExpression(char key) {  
        xxx=new JavaScript();
       
		if(key=='=')
		{
			String subExpression=expression.substring(0,expression.length()-1);
			num=xxx.getMathValue(subExpression);
			return expression+num;
		}
        else
        	return expression;
		
    }  
  
    // TODO: modify the method to handle the key press event  
    void keyPressed(char key) {  
        
    	if(key=='c')
        	expression="";
        else
        	expression += key;  
        
    }  
  
    // TODO: you can modify this method to print any debug  
    //  information (It will be called by CalculatorCmd)  
    void debugPrintStatus() {  
        System.out.println("Expression = " + expression);  
    }  
}  


public class CalculatorApp {  
    public static void main(String[] args) {  
        CalculatorWindow mainwnd = new CalculatorWindow();  
        CalculatorController control = new CalculatorController();  
        mainwnd.setController(control);  
        mainwnd.setVisible(true);  
    }  
}  

class JavaScript {
	ScriptEngineManager factory = new ScriptEngineManager();
    ScriptEngine engine = factory.getEngineByName("JavaScript");
    
    public String getMathValue(String option){
    	Double d = (double) 0;
		try {
			Object o = engine.eval(option);
			d = Double.parseDouble(o.toString());
		} catch (ScriptException e) {
			
			return "error";
		}
		return d.toString();
    }
}