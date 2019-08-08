public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G = 6.67e-11;

//constructor 1
    public Body(double xP, double yP, double xV,
              double yV, double m, String img){
    	xxPos = xP;
    	yyPos = yP;
    	xxVel = xV;
    	yyVel = yV;
    	mass = m;
    	imgFileName = img;
    }
//consturctor 2
    public Body(Body b){
    	xxPos = b.xxPos;
    	yyPos = b.yyPos;
    	xxVel = b.xxVel;
    	yyVel = b.yyVel;
    	mass = b.mass;
    	imgFileName = b.imgFileName;
    }
//
    public double calcDistance(Body b) {
    	double dx = b.xxPos - this.xxPos;
    	double dy = b.yyPos - this.yyPos;
    	double distance = Math.sqrt((dx)*(dx) + (dy)*(dy));
    	return distance;
    }

    public double calcForceExertedBy(Body b) {
    	return (b.mass) * (this.mass) * G / (calcDistance(b)*(calcDistance(b)));
    }

    public double calcForceExertedByX(Body b){
    	return calcForceExertedBy(b) * (b.xxPos - this.xxPos) / calcDistance(b);
    }

    public double calcForceExertedByY(Body b){
    	return calcForceExertedBy(b) * (b.yyPos - this.yyPos) / calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] allbodys){
    	double sum = 0;
    	for (int i = 0; i < allbodys.length; i++){
    		if (this.equals(allbodys[i])){
    			continue;
    		}
    		sum = sum + calcForceExertedByX(allbodys[i]);
    	}
    	return sum;
    }

    public double calcNetForceExertedByY(Body[] allbodys){
    	double sum = 0;
    	for (int i = 0; i < allbodys.length; i++){
    		if (this.equals(allbodys[i])){
    			continue;
    		}
    		sum = sum + calcForceExertedByY(allbodys[i]);
    	}
    	return sum;
    }

    public void update (double dt, double fX, double fY){
    	double axx = fX / this.mass;
    	double ayy = fY / this.mass;
    	this.xxVel = this.xxVel + axx * dt;
    	this.yyVel = this.yyVel + ayy * dt;
    	this.xxPos = this.xxPos + this.xxVel * dt;
    	this.yyPos = this.yyPos + this.yyVel * dt;
    }

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}    
}
