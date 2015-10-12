import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 
 * @author Kishan Kishore and Giri Gaurav Bhatnagar
 */
public class PolygonTest {

	static int MAX_ITER = 1000000;

	public static void main(String[] args) {
		System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);

		// System.load("C:\\opencv\\build\\java\\x64\\opencv_java300.dll");

		// if (args.length < 2) {
		// System.out.println("here ");
		// System.exit(1);
		// }
		// String path = args[1];
		// String path =
		// "/Users/girigb/MyComputer/workspace/eclipsejava/EvoLisa/src/Mondrian.jpg";
		// String path = "./src/MondarinBig.jpg";
		String path = "./src/New.jpg";

		// if (args.length == 3) {
		// MAX_ITER = Integer.parseInt(args[2]);
		// }

		System.out.println("In the begining");
		Mat image = Highgui.imread(path);
		if (image.empty()) {
			System.out.println("Couldn't load file !");
			System.exit(2);
		}

		int generation = 0, picNumber = 0;
		Size imageSize = image.size();

		Dna dna = Dna.generateDna(imageSize);
		Dna.displayDna(dna);
		Dna mutatedDna;

		Mat parent = dna.draw(picNumber);

		double parentFitness = Dna.fitness(parent, image);
		System.out.println("The initial fitness is " + parentFitness);

		while (true) {
			mutatedDna = dna.mutate();
			
			System.out.println("display mutated dna "+mutatedDna);
			Dna.displayDna(mutatedDna);

			Mat child = mutatedDna.draw(picNumber);

			double childFitness = Dna.fitness(child, image);

			System.out.println("The mutated fitness is " + childFitness);

			if (childFitness < parentFitness) {
				dna = mutatedDna;
				parentFitness = childFitness;
				System.out.println("**************picking child with fitness "
						+ childFitness);
			} else {
				System.out.println("keeping parent with fitness "
						+ parentFitness);
			}

			generation += 1;
			picNumber += 1;

			System.out.println("Generation " + generation);

		}

	}
}
