package picturecompare;

import java.awt.DisplayMode;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Util {

	public static void show(BufferedImage image) {
		ImageIcon icon = new ImageIcon(image);

		JFrame frame = new JFrame();

		frame.setLayout(new FlowLayout());

		DisplayMode screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

		frame.setSize(image.getWidth() > screen.getWidth() ? screen.getWidth() : image.getWidth(),
				image.getHeight() > screen.getHeight() ? screen.getHeight() : image.getHeight());

		JLabel label = new JLabel();
		label.setIcon(icon);
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static BufferedImage load(String filename) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public static BufferedImage scale(BufferedImage image, int width, int height) {
		BufferedImage output = new BufferedImage(width, height, image.getType());

		Graphics2D graphics = output.createGraphics();
		graphics.drawImage(image, 0, 0, width, height, null);
		graphics.dispose();

		return output;
	}

	public static String[] getImages(String directory) {
		ArrayList<String> temp = new ArrayList<String>();
		File folder = new File(directory);
		for (File a : folder.listFiles()) {
			if (a.getName().replaceAll("^.+\\.", "").equalsIgnoreCase("jpg")) {
				temp.add(a.getAbsolutePath());
			}
		}
		return temp.toArray(new String[temp.size()]);
	}
}
