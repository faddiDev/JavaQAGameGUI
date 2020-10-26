import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class QAGui extends JFrame {
	
	private JPanel myPanel = new JPanel();
	
	private JLabel[] myLabel = { new JLabel(), new JLabel(), new JLabel(), 
								new JLabel(), new JLabel(), new JLabel("High Score:"),
								new JLabel(), new JLabel("Jawaban") };
								
	private JTextField jawabanField = new JTextField();
	
	private JButton[] myButton = { new JButton("Start Play!"), new JButton("Next") };
	
	private int scores = 0;
	private int nextScreen = 0;
	private int currentScores = 0;
	
	private lullaby db;
	
	public QAGui() {
		super("Simple Game QA");
		this.setLayout(null);
		this.setSize(600,360);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.myPanel.setSize(600,360);
		this.myPanel.setLayout(null);
		this.add(myPanel);
		
		db = new lullaby("QA.txt", 6);
		
		this.showScreen();

		this.myButton[0].addActionListener(new ButtonHandle());
		this.myButton[1].addActionListener(new ButtonHandle());
		
		this.setVisible(true);
	}
	
	private class ButtonHandle implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == myButton[0]) {
				myLabel[0].setText(db.selectMultiAll()[nextScreen][0]);
				myLabel[1].setText(db.selectMultiAll()[nextScreen][1]);
				myLabel[2].setText(db.selectMultiAll()[nextScreen][2]);
				myLabel[3].setText(db.selectMultiAll()[nextScreen][3]);
				myLabel[4].setText(db.selectMultiAll()[nextScreen][4]);
				jawabanField.setEnabled(true);
				myButton[0].setEnabled(false);
				myButton[1].setEnabled(true);
			} else {
				nextScreen();
			}
		}
	}
	
	public void showScreen() {
		int[][] lblBound = { {10,90,580,60}, {10,170,270,25}, {10,205,270,25}, {300,170,270,25}, 
							{300,205,270,25}, {10,10,125,25}, {145,10,50,25}, {10,270,60,25} };
		
		for(int k=0;k<this.myLabel.length;k++) {
			this.myLabel[k].setBounds(lblBound[k][0], lblBound[k][1], lblBound[k][2], lblBound[k][3]);
			this.myPanel.add(this.myLabel[k]);
		}
		
		this.jawabanField.setBounds(70,270,20,25);
		
		this.myButton[0].setBounds(10,45,125,25);
		this.myButton[1].setBounds(100,270,75,25);
		
		this.myPanel.add(this.myButton[0]);
		this.myPanel.add(this.myButton[1]);
		this.myPanel.add(this.jawabanField);
		
		this.jawabanField.setEnabled(false);
		this.myButton[1].setEnabled(false);
		
		this.myLabel[6].setText(Integer.toString(scores));
	}
	
	private void nextScreen() {
		try {
			if(jawabanField.getText().equals(db.selectMultiAll()[nextScreen][5])) currentScores++;
			jawabanField.setText("");
			nextScreen++;
			String qA = "";
			if(db.selectMultiAll()[nextScreen][0].length() > 90) {
				qA = "<html>" + db.selectMultiAll()[nextScreen][0].substring(0,90) + "<br />" + db.selectMultiAll()[nextScreen][0].substring(90) + "</html>";
			} else {
				qA = db.selectMultiAll()[nextScreen][0];
			}
			myLabel[0].setText(qA);
			myLabel[1].setText(db.selectMultiAll()[nextScreen][1]);
			myLabel[2].setText(db.selectMultiAll()[nextScreen][2]);
			myLabel[3].setText(db.selectMultiAll()[nextScreen][3]);
			myLabel[4].setText(db.selectMultiAll()[nextScreen][4]);
		} catch(ArrayIndexOutOfBoundsException eoe) {
			JOptionPane.showMessageDialog(null, "Score Anda : " + currentScores);
			jawabanField.setEnabled(false);
			myButton[0].setEnabled(true);
			myButton[1].setEnabled(false);
			nextScreen = 0;
			if(currentScores > scores) myLabel[6].setText(Integer.toString(currentScores));
		}
	}
	
	public static void main(String[] args) {
		new QAGui();
	}
	
}