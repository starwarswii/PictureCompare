package picturecompare;

import java.io.File;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		String path = null;
		int groups = 0;

		if (args.length == 0) {
			System.out.print("Enter the complete path to the directory containing the images (including ending backslash): ");

			path = input.nextLine();

			System.out.print("Enter the number of groups to sort into: ");

			groups = Integer.parseInt(input.nextLine());
		} else if (args.length == 2) {
			groups  = Integer.parseInt(args[0]);
			path = args[1];
		} else {
			System.out.println("Usage: PictureCompare <groups> <file_path> OR PictureCompare");
			System.exit(1);
		}

		System.out.println("Loading... This may take some time.");

		String[] files = Util.getImages(path);

		if (!(new File(path+"tempName0.jpg")).exists()) {
			for (int i = 0; i < files.length; i++) {
				rename(files[i], path+i+".jpg");
			}
		}

		PictureCompare pc = new PictureCompare();
		for (int i = 0; i < files.length; i++) {
			pc.addImage(files[i]);
		}

		String[][] clusters = pc.getClusters(groups);
		for (int i = 0; i < clusters.length; i++) { 
			for (int j = 0; j < clusters[i].length; j++) {
				rename(clusters[i][j], path+i+"_"+j+".jpg");
			}
		}

		System.out.println("Done. the images in the directory have been renamed into groups in the format GROUPNUMBER_NUMBER.");

		System.out.println("Press Enter to exit.");

		input.nextLine();

		input.close();
	}

	public static void rename(String oldFileName, String newFileName) {

		File oldFile = new File(oldFileName);
		File newFile = new File(newFileName);

		if (!oldFile.renameTo(newFile)) {
			throw new NullPointerException("Could not Rename File.");
		}
	}
}
