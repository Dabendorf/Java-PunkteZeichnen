package punktezeichnen;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.NumberFormatter;

/**
 * Diese Klasse ist die Hauptklasse des PunkteZeichnenProjektes, welche die Punkte generiert und auch graphisch darstellt.
 * 
 * @author Lukas Schramm
 * @version 1.0
 * 
 */
public class PunkteZeichnen {
	
	private JFrame frame1 = new JFrame("Punkte Zeichnen");
	private Canvas canvas = new Canvas() {
		public void paint(Graphics gr) {
			zeichne(gr);
		}
	};
	private JButton buttonGenerieren = new JButton("Zufallswerte");
	private NumberFormat format = NumberFormat.getInstance(); 
	private NumberFormatter formatter = new NumberFormatter(format);
	private JFormattedTextField anzahlPunkte = new JFormattedTextField(formatter);
	private JList<String> punkteListe = new JList<String>();
	private DefaultListModel<String> punkteListeModel = new DefaultListModel<String>();
	private JScrollPane punkteListeScrollPane = new JScrollPane(punkteListe);
	private ArrayList<Punkt> punkte = new ArrayList<Punkt>();
	
	public PunkteZeichnen() { 
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setPreferredSize(new Dimension(500,350));
		frame1.setMinimumSize(new Dimension(500,350));
		frame1.setMaximumSize(new Dimension(1000,700));
		frame1.setResizable(true);
		Container cp = frame1.getContentPane();
		cp.setLayout(new GridBagLayout());
		
		JPanel rechts = new JPanel();
		rechts.setLayout(new BorderLayout());
		JPanel temp = new JPanel();
		temp.setLayout(new BorderLayout());
		temp.add(anzahlPunkte,BorderLayout.WEST);
		temp.add(buttonGenerieren,BorderLayout.CENTER);
		rechts.add(temp,BorderLayout.NORTH);
		rechts.add(punkteListeScrollPane,BorderLayout.CENTER);
	    
		anzahlPunkte.setText("100");
		buttonGenerieren.setMargin(new Insets(2, 2, 2, 2));
	    buttonGenerieren.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent evt) { 
	    		berechnen();
	    	}
	    });
	    
	    format.setGroupingUsed(false);
	    formatter.setAllowsInvalid(false);
	    frame1.add(canvas,new GridBagFelder(0,0,1,1,0.7,1));
	    frame1.add(rechts,new GridBagFelder(1,0,1,1,0.3,1));
	    punkteListe.setModel(punkteListeModel);
	    canvas.setPreferredSize(new Dimension(0,0));
	    rechts.setPreferredSize(new Dimension(0,0));
	    frame1.pack();
	    frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
	  }
	
	/**
	 * Diese Methode generiert eine neue Reihe an Punkten, traegt sie in die Liste ein und loest eine Neuzeichnung des Canvas aus.
	 */
	private void berechnen() {
		try {
			punkte.clear();
			punkteListeModel.clear();
			Random wuerfel = new Random();
			if(Integer.parseInt(anzahlPunkte.getText())<0) {
				JOptionPane.showMessageDialog(null, "Du hast falsche Werte eingetragen."+System.getProperty("line.separator")+"Wenn Du dies nicht korrigierst"+System.getProperty("line.separator")+"bekommst Du kein Ergebnis!", "Falscheingabe", JOptionPane.WARNING_MESSAGE);
			} else {
				for(int n=0;n<Integer.parseInt(anzahlPunkte.getText());n++) {;
					punkte.add(new Punkt(wuerfel.nextInt(201)-100,wuerfel.nextInt(201)-100));
				}
				for(Punkt p:punkte) {
					punkteListeModel.addElement("P("+p.getX()+"|"+p.getY()+")");
				}
				canvas.repaint();
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Du hast falsche Werte eingetragen."+System.getProperty("line.separator")+"Wenn Du dies nicht korrigierst"+System.getProperty("line.separator")+"bekommst Du kein Ergebnis!", "Falscheingabe", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * Diese Methode berechnet die Koordinaten der Punkte im Canvas und zeichnet sie.
	 * @param gr Nimmt GraphicsElement entgegen.
	 */
	private void zeichne(Graphics gr) {
		int breite = canvas.getWidth();
		int hoehe = canvas.getHeight();
		gr.drawLine(0,hoehe/2,breite,hoehe/2);
		gr.drawLine(breite/2,0,breite/2,hoehe);
		for(Punkt p:punkte) {
			int xp = p.getX()+100;
			int yp = -p.getY()+100;
			double x = (Double.valueOf(breite)/200)*xp;
			double y = (Double.valueOf(hoehe)/200)*yp;
			gr.drawLine((int)x-3,(int)y-3,(int)x+3,(int)y+3);
			gr.drawLine((int)x-3,(int)y+3,(int)x+3,(int)y-3);
		}
	}
	
	public static void main(String[] args) {
		new PunkteZeichnen();
	}
}