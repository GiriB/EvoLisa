import java.util.ArrayList;
import java.util.Random;

import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;

public class MYtest {
	public static Random r = new Random();

	public static void main(String[] args) {
		System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);

		// for (int i = 0; i < Polygon.POLYGONS; i++) {
		// int random = r.nextInt(Polygon.POLY_MAX_POINTS
		// - Polygon.POLY_MIN_POINTS + 1)
		// + Polygon.POLY_MIN_POINTS;
		// System.out.println(random);
		//
		// }

		// int a = 0;
		// while (a != 100) {
		// a++;
		// Point[] p = Polygon.generatePoint(100, 100).toArray();
		//
		// // System.out.println(p.toString());
		// System.out.println("point is " + p[0].x + " " + p[0].y);
		// }

		// TODO Auto-generated method stub
		// s Mat a = Imgcodecs.imread("./Mondrian.jpg");
		// Mat a = Imgcodecs.imread("./Mondarian.jpg");
		// Mat a = Imgcodecs.imread("./Mondrian.jpg");
		// Mat a = Highgui.imread("./Mondrian.jpg");

		// System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
		// System.out.println("Loaded\n");
		// Mat a = new Mat(5, 4, 1, new Scalar(0, 0, 0, 25));
		// Mat b = Imgcodecs.imread("./Mondarian.jpg");
		// b.copyTo(a);
		// System.out.println(b.dump());

		// List<MatOfPoint> pts = new ArrayList<MatOfPoint>();
		// pts.add(new MatOfPoint(new Point(420, 420)));
		// pts.add(new MatOfPoint(new Point(30, 20)));
		// pts.add(new MatOfPoint(new Point(30, 100)));
		//
		// Scalar colora = new Scalar(156, 27, 182, 100);
		// Scalar colorb = new Scalar(166, 127, 42, 150);
		// Scalar colorc = new Scalar(116, 27, 142, 150);
		//
		// Scalar colord = new Scalar(216, 45, 67, 0);
		// Scalar colore = new Scalar(165, 17, 142, 255);

		// List<Point> l = new ArrayList<Point>();
		// l.add(new Point(2,3));
		// System.out.println(l.toString());
		//
		// Mat m = new Mat();
		// Mat m = Mat.zeros(1040, 1040, CvType.CV_8UC3);
		// Mat withoutm = Mat.zeros(1040, 1040, CvType.CV_8UC3);
		// Mat m = Mat.zeros(1040, 1040, CvType.CV_8UC4);
		// Mat withoutm = Mat.zeros(1040, 1040, CvType.CV_8UC4);
		// // Mat dst = Mat.zeros(1040, 1040, CvType.CV_8UC4);
		//
		// // Mat draw = Mat.zeros(1040, 1040, CvType.CV_8UC4);
		// Mat draw = new Mat(1040, 1040, CvType.CV_8UC4, new Scalar(255, 255,
		// 255, 255));
		System.out.println("Start");

		String path1 = "./src/1.jpg";
		String path2 = "./src/2.jpg";
		//
		// // if (args.length == 3) {
		// // MAX_ITER = Integer.parseInt(args[2]);
		// // }
		//
		// Mat src1 = Highgui.imread(path1);
		//
		// System.out.println(src1.size().height + " " + src1.size().width);
		//
		// double[] p = src1.get(30, 10);
		//
		// System.out.print('[');
		// for (double a : p) {
		// System.out.print((int) a + " ");
		// }
		// System.out.print(']');

		// for (int i = 0; i < src1.size().width; i++) {
		// for (int j = 0; j < src1.size().height; j++) {
		// double[] p = src1.get(j, i);
		//
		// System.out.print('[');
		// for (double a : p) {
		// System.out.print((int) a + " ");
		// }
		// System.out.print(']');
		// }
		// System.out.println();
		//
		// }

		// // Mat src11 = Mat.zeros(1000, 1000, CvType.CV_8UC3);
		//
		//
		// Dna dna = Dna.generateDna(new Size(100, 100));
		// Dna mutatedDna;
		// mutatedDna = dna.mutate();
		//
		// Scalar s = new Scalar(200,100,230,102);
		// System.out.println(s.val[0]);
		// System.out.println(s.val[1]);
		// System.out.println(s.val[3]);
		// System.out.println(s.val[2]);
//		Point p = new Point(2, 3);
//		Point q = p.clone();
//		System.out.println(p + " " + p.x + " " + p.y);
//		System.out.println(q + " " + q.x + " " + q.y);
//		System.out.println(p == q);

//		Dna dna1;
//		Dna dna2;
//		ArrayList<Point> p1 = new ArrayList<Point>();
//		ArrayList<Point> p2 = new ArrayList<Point>();
//		for (int i = 0; i < 5; i++) {
//			int x = r.nextInt(100);
//			int y = r.nextInt(100);
//			p1.add(new Point(x, y));
//			p2.add(new Point(x, y));
//		}
//
//		Polygon pl1 = new Polygon(new Scalar(10, 10, 10, 10), p1);
//		Polygon pl2 = new Polygon(new Scalar(10, 10, 10, 10), p2);
//		System.out.println(pl1 + " " + pl2);
//
//		ArrayList<Polygon> pllist1 = new ArrayList<Polygon>();
//		ArrayList<Polygon> pllist2 = new ArrayList<Polygon>();
//		pllist1.add(pl1);
//		dna1 = new Dna(new Size(100, 100), pllist1);
//		dna2 = new Dna(new Size(100, 100), pllist2);

		Dna dna = Dna.generateDna(new Size(100,100));
		Dna mutatedDna = dna.mutate();
		System.out.println(dna.polygons +"---"+mutatedDna.polygons);
		
		Dna.displayDna(dna);
		Dna.displayDna(mutatedDna);
		
		System.out.println("--------------------------------------------");
		//
		// System.out.println(mutatedDna + " " + dna);
		// for (int i = 0; i < mutatedDna.polygons.size(); i++) {
		// System.out.println(mutatedDna.polygons.get(i) + " "
		// + dna.polygons.get(i));
		// if (mutatedDna.polygons.get(i) != dna.polygons.get(i)) {
		// System.out.println("FLAESA");
		// }
		// }

		// Mat src2 = Highgui.imread(path2, -1);
		// System.out.println(Dna.fitness(src2, src1));
		//

		// System.out.println("Height is " + src2.height());
		// Mat dst = Mat.zeros(src2.height(), src2.width(), CvType.CV_8UC4);
		// // System.out.println(dst.get(0, 0));
		// // for (int i = 0; i < src2.height(); i++) {
		// // for (int j = 0; j < src2.width(); j++) {
		// // for (double val : src2.get(i, j)) {
		// // System.out.print(val + " ");
		// // }
		// // System.out.println();
		// // }
		// // System.out.println();
		// // }
		// // System.out.println();
		// src2.copyTo(dst);
		// Core.circle(dst, new Point(420, 420), 10, new Scalar(150, 255, 0),
		// -1);
		// Core.circle(dst, new Point(440, 440), 140, new Scalar(0, 255, 0),
		// -1);
		// ArrayList<MatOfPoint> m1 = new ArrayList<MatOfPoint>();
		// m1.add(new MatOfPoint(new Point(420, 320)));
		// m1.add(new MatOfPoint(new Point(330, 320)));
		// m1.add(new MatOfPoint(new Point(240, 210)));
		// // m1.add(new MatOfPoint(new Point(150, 190)));
		// // m1.add(new MatOfPoint(new Point(10, 15)));
		//
		// Core.fillConvexPoly(dst, Dna.helperFillPoly(m1), colorb);
		// Highgui.imwrite("./src/newDst" + ".png", dst);
		//
		// double opacity = (0.4);
		// Core.addWeighted(dst, opacity, src2, 1 - opacity, 0, src2);
		//
		// Highgui.imwrite("./src/newTest" + ".png", src2);

		// double alpha = 0.5;
		// double beta;
		// beta = 1 - alpha;

		//
		// src11.copyTo(dst);
		// ArrayList<MatOfPoint> b = new ArrayList<MatOfPoint>();
		// b.add(new MatOfPoint(new Point(200, 200)));
		// b.add(new MatOfPoint(new Point(300, 100)));
		// b.add(new MatOfPoint(new Point(300, 107)));
		//
		// ArrayList<MatOfPoint> c = new ArrayList<MatOfPoint>();
		// c.add(new MatOfPoint(new Point(200, 200)));
		// c.add(new MatOfPoint(new Point(300, 127)));
		// c.add(new MatOfPoint(new Point(400, 127)));
		//
		// Imgproc.fillConvexPoly(dst, Dna.helperFillPoly(c), colorc);
		// Imgproc.fillConvexPoly(dst, Dna.helperFillPoly(b), colorb);
		//
		// Core.addWeighted(dst, alpha, src2, beta, 0.0, src11);
		// System.out.println("Saving belnder");
		// Imgcodecs.imwrite("./src/Newdst" + ".jpg", dst);
		// Imgcodecs.imwrite("./src/Newsrc2" + ".jpg", src2);

		// Imgcodecs.imwrite("./src/NewSrc" + ".png", src2);

		// ArrayList<MatOfPoint> a = new ArrayList<MatOfPoint>();
		// a.add(new MatOfPoint(new Point(700, 200)));
		// a.add(new MatOfPoint(new Point(200, 600)));
		// a.add(new MatOfPoint(new Point(0, 0)));
		// // Imgproc.fillConvexPoly(m, Dna.helperFillPoly(a), colora);
		//

		//
		//
		//
		// Imgproc.fillConvexPoly(draw, Dna.helperFillPoly(a), colora);

		// Size imageSize = image.size();
		//
		// Mat img = new Mat((int) imageSize.width, (int) imageSize.height,
		// CvType.CV_8UC4, new Scalar(0, 0, 0, 255));
		// Imgcodecs.imwrite("./src/PlainImage" + ".jpg", img);

		System.out.println("Stop");

		//
	}
}
