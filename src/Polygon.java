/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kishan kishore
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class Polygon {

	final static int POLY_MIN_POINTS = 3;
	final static int POLY_MAX_POINTS = 5;
	final static int POLYGONS = 5;
	private static final int OFFSET = 10;
	Scalar color; // color attribute of polygon
	ArrayList<Point> points;

	static Random r = new Random();

	public Polygon() {
		color = null;
		points = new ArrayList<Point>();
	}

	public Polygon(Scalar color, ArrayList<Point> points) {
		this.color = color;
		this.points = points;

	}

	static ArrayList<Polygon> copyList(ArrayList<Polygon> original) {
		ArrayList<Polygon> copy = new ArrayList<Polygon>();
//		System.out.println("Inside copyList :\n "+original+"------"+copy);

		Polygon originalPolygon;
		Polygon copyPolygon;

		for (int i = 0; i < original.size(); i++) {
			originalPolygon = original.get(i);
			copyPolygon = new Polygon();

			// copy color
			copyPolygon.color = originalPolygon.color.clone();
			if (copyPolygon.color == originalPolygon.color) {
				System.out.println("ERROR: Same Color! ");
				System.exit(-7);
			}

			// copy each point
			for (Point p : originalPolygon.points) {
				copyPolygon.points.add(p.clone());
			}

			// for debugging
			if (originalPolygon.points.size() != copyPolygon.points.size()) {
				System.out.println("Quiting!");
				System.exit(-6);
			} else {
				for (int j = 0; j < originalPolygon.points.size(); j++) {
					if (originalPolygon.points.get(j) == copyPolygon.points
							.get(j)) {
						System.out.println("ERROR!! SAME address!!");
						System.exit(6);
					}
				}

			}

			copy.add(copyPolygon);
//			System.out.println("Inside copyList :\n "+original+"------"+copy);

		}

		return copy;
	}

	/**
	 * !!!!!! Here the point generated CAN BE OUTSIDE THE IMAGE
	 * 
	 * @param width
	 * @param height
	 * @return a MatOfPoint object with single Point(x,y)
	 */
	public static Point generatePoint(int width, int height) {
		// generate a random point between -OFFSET and widhth/height + OFFSET

		// x = random.randrange(0 - OFFSET, width + OFFSET, 1)
		// y = random.randrange(0 - OFFSET, height + OFFSET, 1)

		int x = (r.nextInt(width + 2 * OFFSET)) - OFFSET;
		int y = (r.nextInt(height + 2 * OFFSET)) - OFFSET;
		System.out.println("Point generated :" + x + " " + y);
		return (new Point(x, y));
	}

	// Here the width=imgsize.width and height =imgsize.height
	public void mutate(int width, int height) {
		System.out.println("Mutating Polygon: with parameters " + width + " "
				+ height);

		if (r.nextDouble() <= 0.5) {

			// changing the color of the polygon
			int idx = r.nextInt(4);
			int value = r.nextInt(256);
			// System.out.println("value is " + value);

			// scheme is BGRA
			int blue = (int) this.color.val[0];
			int green = (int) this.color.val[1];
			int red = (int) this.color.val[2];
			int alpha = (int) this.color.val[3];
			System.out.println("Changing color id:" + idx);
			System.out.println("Old color:" + blue + " " + green + " " + red
					+ " " + alpha);
			System.out.println("Now , idx= " + idx + " is val= " + value);

			switch (idx) {
			case 0:
				blue = value;
				break;
			case 1:
				green = value;
				break;
			case 2:
				red = value;
				break;
			case 3:
				alpha = value;
				break;
			}
			// scheme is BGRA
			System.out.println("New color:" + blue + " " + green + " " + red
					+ " " + alpha);

			this.color = new Scalar(blue, green, red, alpha);

		} else {
			// changing a point at a random index in points array

			int random = r.nextInt(this.points.size());
			System.out.println("Changing a point: at index " + random);
			// System.out.println("random is" + random);
			this.points.set(random, generatePoint(width, height));
		}

	}

	public String displayColor() {
		double[] color = this.color.val;
		String retColor = ("( " + color[0] + "," + color[1] + "," + color[2]
				+ "," + color[3] + ")");
		return retColor;
	}

}

class Dna {
	private static final boolean DEBUG = true;
	Size imageSize;
	ArrayList<Polygon> polygons;
	int generation = 0;
	static Random r = new Random();

	public Dna(Size imageSize, ArrayList<Polygon> polygons) {
		this.imageSize = imageSize;
		this.polygons = polygons;
	}

	public static MatOfPoint helperFillPoly(ArrayList<Point> list) {
		MatOfPoint matofpoint = new MatOfPoint();
		if (list.size() > 0) {
			matofpoint.fromList(list);
		} else {
			System.out
					.println(" The polygon list is zero!! Can't add anything");
			System.exit(4);
		}
		return matofpoint;
	}

	static double fitness(Mat child, Mat parent) {
		// throw new UnsupportedOperationException("Not supported yet."); //To
		// change body of generated methods, choose Tools | Templates.

		double fitness = 0.0;

		// Pixels from both the images
		double[] ParentPixel;
		double[] ChildPixel;

		// difference between the BRG values
		double d_r = 0, d_g = 0, d_b = 0;

		// System.out.println(child.size().height + " " + child.size().width +
		// " "
		// + parent.size().height + " " + parent.size().width);
		if (child.size().height == parent.size().height
				&& child.size().width == parent.size().width) {
			// System.out.println("child and Parent have equal sizes");
			for (int i = 0; i < child.size().height; i++) {
				for (int j = 0; j < child.size().width; j++) {
					ChildPixel = child.get(i, j);
					ParentPixel = parent.get(i, j);
					// System.out.println("ChildPixel length" +
					// ChildPixel.length);
					// System.out.println("Parent: " + ParentPixel[0] + " "
					// + ParentPixel[1] + " " + ParentPixel[2] + " ");//
					// +ParentPixel[3]+" ");
					// System.out.println("Child " + ChildPixel[0] + " "
					// + ChildPixel[1] + " " + ChildPixel[2] + " ");//
					// +ChildPixel[3]+" ");

					d_b = ParentPixel[0] - ChildPixel[0];
					d_r = ParentPixel[1] - ChildPixel[1];
					d_g = ParentPixel[2] - ChildPixel[2];
					// fitness += Math.sqrt(d_b * d_b + d_r * d_r + d_g * d_g);
					fitness += Math.sqrt(d_b * d_b + d_r * d_r + d_g * d_g);
				}
			}

			// System.out.println("Comparing done");

		} else {
			System.out.println("child and Parent have different sizes");

		}

		return fitness;
	}

	public Dna mutate() {
		// System.out.println("Mutating Dna:");
		ArrayList<Polygon> mutatedPolygons = Polygon.copyList(this.polygons);


		// pick a polygon from the polygon list
		Polygon randomPolygon = mutatedPolygons.get(r.nextInt(mutatedPolygons
				.size()));

		System.out.println("Changing polygon" + randomPolygon);

		// mutate that Polygon
		randomPolygon.mutate((int) imageSize.width, (int) imageSize.height);

		return (new Dna(new Size(this.imageSize.height, this.imageSize.width),
				mutatedPolygons));
	}

	public Mat draw(int picNumber) {

		Size size = this.imageSize;

		Mat img = new Mat((int) size.width, (int) size.height, CvType.CV_8UC4,
				new Scalar(0, 0, 0, 255));

		Mat draw = new Mat((int) size.width, (int) size.height, CvType.CV_8UC4,
				new Scalar(0, 0, 0, 255));

		// blend all the polygons onto the image
		for (int i = 0; i < this.polygons.size(); i++) {
			draw = blender(draw, this.polygons.get(i));
			// Highgui.imwrite("./src/NewimagesBlender.png", draw);
		}

		draw.copyTo(img, draw);

		if (picNumber % 100 == 0) {
			System.out.println("At  image : ./"
					+ String.format("%03d", picNumber) + ".jpg");
		}
		if (picNumber % 1000 == 0) {

			Mat newimg = new Mat((int) size.width, (int) size.height,
					CvType.CV_8UC3, new Scalar(0, 0, 0));
			Imgproc.GaussianBlur(draw, newimg, new Size(5, 5), 50);

			System.out.println("Drawing a new image : ./"
					+ String.format("%03d", picNumber) + ".jpg");
			Highgui.imwrite("./src/Images/" + String.format("%03d", picNumber)
					+ ".jpg", newimg);
		}
		return img;
	}

	/**
	 * Blends the given polygon onto the image using its alpha value
	 * 
	 * @param srcImg
	 * @param p
	 * @return refer to :
	 *         http://bistr-o-mathik.org/2012/06/13/simple-transparency
	 *         -in-opencv/
	 */
	public static Mat blender(Mat srcImg, Polygon p) {
		// create a copy of the image
		Mat polyImg = Mat
				.zeros(srcImg.width(), srcImg.height(), CvType.CV_8UC4);
		srcImg.copyTo(polyImg);

		// Draw the polygon onto the image
		Core.fillConvexPoly(polyImg, Dna.helperFillPoly(p.points), p.color);

		// calculating opacity - converting alpha value in the range 0-1
		double opacity = (p.color.val[3]) / 255;

		// copy the new image on the older one
		Core.addWeighted(polyImg, opacity, srcImg, 1 - opacity, 0, srcImg);

		// Highgui.imwrite("./src/blenderPolyDraw" + ".png", polyImg);

		return srcImg;
	}

	public static Dna generateDna(Size imageSize) {
		// System.out.println("generating RANDOM DNA");
		ArrayList<Polygon> polygons = new ArrayList<Polygon>();

		for (int i = 0; i < Polygon.POLYGONS; i++) {
			int random = r.nextInt(Polygon.POLY_MAX_POINTS
					- Polygon.POLY_MIN_POINTS + 1)
					+ Polygon.POLY_MIN_POINTS;

			ArrayList<Point> points = new ArrayList<Point>();

			for (int j = 0; j < random; j++) {
				points.add(Polygon.generatePoint((int) imageSize.width,
						(int) imageSize.height));
			}

			// All polygons white initially
			Scalar color = new Scalar(255, 255, 255, 255);
			polygons.add(new Polygon(color, points));
			// System.out.println(polygons);

		}
		return new Dna(imageSize, polygons);

	}

	public static void displayDna(Dna dna) {

		if (DEBUG) {
			System.out.println("dna is " + dna);
			System.out.println("ImageSize is  ( " + dna.imageSize.height
					+ " , " + dna.imageSize.width + " )");
			System.out.println("polygonsList is " + dna.polygons);

			int polygonCounter = 0;
			for (Polygon p : dna.polygons) {
				// displayColor
				System.out.println(polygonCounter + " : " + " color is "
						+ p.displayColor());

				// displayPoints
				int pointCounter = 0;
				for (Point q : p.points) {
					System.out.println(q);
					pointCounter++;
				}

				polygonCounter++;
			}

		}

	}
}
