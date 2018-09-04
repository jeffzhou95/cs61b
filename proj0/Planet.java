public class Planet {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}
	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}
	public double calcDistance(Planet p) {
		return Math.pow((p.xxPos - xxPos) * (p.xxPos - xxPos) + (p.yyPos - yyPos) * (p.yyPos - yyPos), 0.5);
	}
	public double calcForceExertedBy(Planet p) {
		return 6.67e-11 * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
	}
	public double calcForceExertedByX(Planet p) {
		return this.calcForceExertedBy(p) * (p.xxPos - xxPos) / this.calcDistance(p);
	}
	public double calcForceExertedByY(Planet p) {
		return this.calcForceExertedBy(p) * (p.yyPos - yyPos) / this.calcDistance(p);
	}
	public double calcNetForceExertedByX(Planet[] planets) {
		double NetForceExertedByX = 0;
		int temp = planets.length + 1;
		for (int i = 0; i< planets.length; i += 1) {
			if(this.equals(planets[i])) {
				temp = i;
				break;
			}
		}
		for(int i = 0; i < planets.length; i += 1) {
			if (i != temp) {
				NetForceExertedByX = NetForceExertedByX + this.calcForceExertedByX(planets[i]);
			}
		}	
		return NetForceExertedByX;
	}
	public double calcNetForceExertedByY(Planet[] planets) {
		double NetForceExertedByY = 0;
		int temp = planets.length + 1;
		for (int i = 0; i< planets.length; i += 1) {
			if(this.equals(planets[i])) {
				temp = i;
				break;
			}
		}
		for(int i = 0; i < planets.length; i += 1) {
			if (i != temp) {
				NetForceExertedByY = NetForceExertedByY + this.calcForceExertedByY(planets[i]);
			}
		}	
		return NetForceExertedByY;
	}
	public void update(double dt,double fX,double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel = this.xxVel + aX * dt;
		this.yyVel = this.yyVel + aY * dt;
		this.xxPos = this.xxPos + this.xxVel * dt;
		this.yyPos = this.yyPos + this.yyVel * dt;
	}
	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
	}
}