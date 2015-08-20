package picturecompare;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Compare {

	static final int RESIZE_SIZE = 50;

	public static double compare(BufferedImage image, BufferedImage anotherImage) {
		BufferedImage imageResized = Util.scale(image, RESIZE_SIZE, RESIZE_SIZE);
		BufferedImage  anotherImageResized = Util.scale(anotherImage, RESIZE_SIZE, RESIZE_SIZE);

		double total = 0;

		for (int i = 0; i < imageResized.getHeight(); i++) {
			for (int j = 0; j < imageResized.getWidth(); j++) {
				Color color = new Color(imageResized.getRGB(j, i));
				Color anotherColor = new Color(anotherImageResized.getRGB(j, i));

				double red = Math.abs(color.getRed() - anotherColor.getRed());
				double green = Math.abs(color.getGreen() - anotherColor.getGreen());
				double blue = Math.abs(color.getBlue() - anotherColor.getBlue());

				total+=Math.pow(red, 2)+Math.pow(green, 2)+Math.pow(blue, 2);
			}
		}
		return total/(imageResized.getWidth()*imageResized.getHeight());
	}
}
