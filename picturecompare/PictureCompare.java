package picturecompare;

import java.util.ArrayList;
import java.util.Arrays;

public class PictureCompare {

	ArrayList<String> pictures;
	double[] differences;

	public PictureCompare() {
		pictures = new ArrayList<String>();
	}

	public void addImage(String h) {
		pictures.add(h);
	}

	public void calculateDifference() {
		differences = new double[pictures.size()];

		for (int i = 1; i < differences.length; i++) {
			differences[i] = Compare.compare(Util.load(pictures.get(i)), Util.load(pictures.get(i-1)));
		}
	}

	public String[] getClusterContaining(int n, String filename) {

		for (String[] names : getClusters(n)) {
			for (String s : names) {
				if (s.equals(filename)) {return names;}
			}
		}

		return null;
	}


	public static int[] indexesOfTopElements(double[] orig, int nummax) {
		double[] copy = Arrays.copyOf(orig, orig.length);
		Arrays.sort(copy);
		double[] honey = Arrays.copyOfRange(copy, copy.length - nummax, copy.length);
		int[] result = new int[nummax];
		int resultPos = 0;
		for (int i = 0; i < orig.length; i++) {
			double onTrial = orig[i];
			int index = Arrays.binarySearch(honey, onTrial);
			if (index < 0)
				continue;
			result[resultPos++] = i;
		}
		return result;
	}

	public String[][] getClusters(int groups) {
		calculateDifference();

		String[][] output;

		if (groups < 2) {
			output = new String[1][];
			output[0] = pictures.toArray(new String[pictures.size()]);
			return output;
		}

		int[] pivots = indexesOfTopElements(differences, groups-1);

		output = new String[groups][];

		String[] pictures = this.pictures.toArray(new String[this.pictures.size()]);

		output[0] = Arrays.copyOfRange(pictures, 0, pivots[0]);

		for (int i = 0; i < pivots.length-1; i++) {
			output[i+1] = Arrays.copyOfRange(pictures, pivots[i], pivots[i+1]);
		}

		output[output.length-1] = Arrays.copyOfRange(pictures, pivots[pivots.length-1], pictures.length);

		return output;
	}
}
