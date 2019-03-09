package ui.massage;

import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MassageButton extends JPanel implements MouseListener {

	private Image[] img;

	private Image normalImage;
	private Image rolloverImage;
	private Image pressedImage;

	private Image currentImage;

	private boolean enabled = true;

	private String name = null;
	
	private Massage massage = null;

	public MassageButton(String name,Massage massage,int x,int y) {
		this.name = name;// ��������
		this.massage = massage;
		this.img = this.getImg(name);
		this.normalImage = this.img[0];
		this.rolloverImage = this.img[1];
		this.pressedImage = this.img[2];
		this.currentImage = normalImage;
		this.setBounds(x, y, this.img[0].getWidth(null), this.img[0].getHeight(null));
		this.addMouseListener(this);
	}
	
	
	private Image[] getImg(String name) {
		return new Image[] {
				new ImageIcon("images/massage/" + name + "/normal.png")
						.getImage(),
				new ImageIcon("images/massage/" + name + "/mouseOver.png")
						.getImage(),
				new ImageIcon("images/massage/" + name + "/pressed.png")
						.getImage(),
				new ImageIcon("images/massage/" + name + "/disabled.png")
						.getImage() };
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void paint(Graphics g) {
		this.setOpaque(false); // ����͸��
		if (enabled) {
			g.drawImage(currentImage, this.getX(), this.getY(),
					this.getWidth(), this.getHeight(), this);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		currentImage = pressedImage;
		if (enabled) {
			if (name.equals("ok")) {
				massage.ok();
			} else  if (name.equals("cancel")) {
				massage.cancel();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		currentImage = rolloverImage;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		currentImage = rolloverImage;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		currentImage = normalImage;
	}
}