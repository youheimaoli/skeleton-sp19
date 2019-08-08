public class NBody{
	public static double readRadius(String FileName){
		In in = new In(FileName);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}

/***
Why it does not work?
	public static Body[] readBodies(String FileName){
		In in = new In(FileName);
		int Number = in.readInt();
		double secondItemInFile = in.readDouble();

		Body[] Bodies = new Body[Number];
		for (int i = 0; i < Number; i ++){
			Bodies[i].xxPos = in.readDouble();
			Bodies[i].yyPos = in.readDouble();
			Bodies[i].xxVel = in.readDouble();
			Bodies[i].yyVel = in.readDouble();
			Bodies[i].mass = in.readDouble();
			Bodies[i].imgFileName = in.readString();
		}
		return Bodies;
	}
***/
	public static Body[] readBodies(String FileName){
		In in = new In(FileName);
		int Number = in.readInt();
		double secondItemInFile = in.readDouble();

		Body[] Bodies = new Body[Number];
		for (int i = 0; i < Number; i ++){
			double xPos = in.readDouble();
			double yPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			Bodies[i] = new Body(xPos, yPos, xVel, yVel, mass, imgFileName);
		}
		return Bodies;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);

		String filename = args[2];
		double radius = readRadius(filename);
		Body[] bodies = readBodies(filename);
		int N = bodies.length;

		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");

		for (Body b : bodies){
			b.draw();
		}

		StdDraw.enableDoubleBuffering();

		//time = 0
		double time = 0;
		while (time < T){
			double[] xForces = new double[N];
			double[] yForces = new double[N];

			for (int i = 0; i < N; i ++){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			for (int i = 0; i < N; i++){
				bodies[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");

			for (Body b : bodies){
				b.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
		                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);  
		} 
    }

}