package ui.massage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import ui.JPanelGame;

public class MassageOk extends Massage {

	
	private JTextArea textArea = null;

	private MassageButton ok;
	

	public MassageOk(String titile, String information, JPanelGame panel) {
		super(titile, panel);
		this.titileStr = titile;
		// ���Ӱ�ť
		addButtons();
		// �����ı���
		addTextArea();
		this.textArea.setText(information);
	}

	public void setInfo(String titileStr) {
		this.textArea.setText(titileStr);
		this.titile.setText("<html><font color='white' >"+titileStr+"</font></html>");
	}
	
	private void addButtons() {
		ok = new MassageButton("ok",this, 18 * 6, 131);
		add(ok);
	}

	private void addTextArea() {
		textArea = new JTextArea();
		textArea.setText("Oops");
		textArea.setBounds(18, 39, 230, 50);
		textArea.setSelectedTextColor(Color.BLUE);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		add(textArea);
	}

	@Override
	public void paint(Graphics g) {
		paints(g);

		ok.update(g);
		super.paint(g);
	}

	public void paints(Graphics g) {
		g.drawImage(bg, 0, 0, bg.getWidth(null), bg.getHeight(null), 0, 0,
				bg.getWidth(null), bg.getHeight(null), null);
	}

}
