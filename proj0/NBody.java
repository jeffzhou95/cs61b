public class NBody {
	public static double readRadius(String planetsTxtPath) {
		In in = new In(planetsTxtPath);
		in.readInt();
		return in.readDouble();
	}
	public static Planet[] readPlanets(String planetsTxtPath) {
		In length = new In(planetsTxtPath);
		int l = 0;
		length.readInt();
		length.readDouble();
		while(!length.isEmpty()) {
			length.readDouble();
			length.readDouble();
			length.readDouble();
			length.readDouble();
			length.readDouble();
			length.readString();
			l++;
		}
		In in = new In(planetsTxtPath);
		in.readInt();
		in.readDouble();
		Planet[] p = new Planet[l];
		for(int i = 0; i < p.length; i++) {
			p[i] = new Planet(in.readDouble(), in.readDouble(), 
				in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}
		return p;
	}
	public static void main(String[] args) {
		/*Collecting All Needed Input*/
		if (args.length == 0) {
			System.out.println("Please enter Time, a Small Period of time, Filename");
		} 
		double t = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String planetsTxtPath = args[2];
		double rU = NBody.readRadius(planetsTxtPath);
		Planet[] p = NBody.readPlanets(planetsTxtPath);

		/*Drawing the Background*/
		String imageToDraw = "images/starfield.jpg";
		StdDraw.setScale(-rU, rU);
		StdDraw.picture(0, 0, imageToDraw);
		for(int i = 0; i < p.length; i++) {
			p[i].imgFileName = "images/" + p[i].imgFileName;
			p[i].draw();
		}
		/*Creating a Animation*/
		StdDraw.enableDoubleBuffering();
		StdDraw.clear();
		for(double time = 0; time < t; time += dt) {
			double[] xForces = new double[p.length]; 
			double[] yForces = new double[p.length]; 
			for(int i = 0; i < p.length; i++) {
				xForces[i] = p[i].calcNetForceExertedByX(p);
				yForces[i] = p[i].calcNetForceExertedByY(p);
			}
			StdDraw.picture(0, 0, imageToDraw);
			for(int i = 0; i < p.length; i++) {
				p[i].update(dt, xForces[i], yForces[i]);
				p[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			
		}
		StdOut.printf("%d\n", p.length);
    	StdOut.printf("%.2e\n", rU);
		for (int i = 0; i < p.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                 	 	p[i].xxPos, p[i].yyPos, p[i].xxVel,
                		p[i].yyVel, p[i].mass, p[i].imgFileName);   
		}
	}
	
}