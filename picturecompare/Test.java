package picturecompare;

import java.awt.image.BufferedImage;

public class Test {
	
	public static void main(String[] args) {
		final String PATH = "src\\picturecompare\\";
		
		BufferedImage a = Util.load(PATH+"test1.jpg");
		BufferedImage b = Util.load(PATH+"test2.jpg");
		Util.show(a);
		Util.show(b);
		System.out.println("Total: "+Compare.compare(a,b));
		
		BufferedImage c = Util.load(PATH+"test2.jpg");
		BufferedImage d = Util.load(PATH+"test3.jpg");
		Util.show(c);
		Util.show(d);
		System.out.println("Total: "+Compare.compare(c,d));
		
		BufferedImage e = Util.load(PATH+"test4.jpg");
		BufferedImage f = Util.load(PATH+"test5.jpg");
		Util.show(e);
		Util.show(f);
		System.out.println("Total: "+Compare.compare(e,f));
	}

}
