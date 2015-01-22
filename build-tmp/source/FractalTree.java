import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class FractalTree extends PApplet {

Snowflake[] snow;
private double fractionLength = .8f; 
private int smallestBranch = 8; 
private double branchAngle = .7f;  

public void setup() 
{   
size(700,600); 
snow = new Snowflake[400];
  for (int i=0; i<snow.length; i++)
  {
    snow[i]=new Snowflake();
  }   
	//noLoop(); 
frameRate(5);
} 
public void draw() 
{   
	background(0);  
	//stroke(191,236,240);
	stroke((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
	line(350,600,350,450);   
	drawBranches(350,450,100,3*Math.PI/2); 
	for (int i=0; i<snow.length; i++)
  {
    snow[i].move();
    snow[i].wrap();
    snow[i].show();
  }
} 
public void drawBranches(int x,int y, double branchLength, double angle) 
{   
	double angle1 = angle+branchAngle;
	double angle2 = angle-branchAngle;
	branchLength=branchLength*fractionLength;  
	int endX1 = (int)(branchLength*Math.cos(angle1) + x);
	int endY1 = (int)(branchLength*Math.sin(angle1) + y);
	int endX2 = (int)(branchLength*Math.cos(angle2) + x);
	int endY2 = (int)(branchLength*Math.sin(angle2) + y);
	line(x,y,endX1,endY1);
	line(x,y,endX2,endY2);
	if(branchLength > smallestBranch)
	{
		drawBranches(endX1,endY1,branchLength,angle1);
		drawBranches(endX2,endY2,branchLength,angle2);
	}
} 
class Snowflake
{
  int x, y;
  Snowflake()
  {
    x=(int)(Math.random()*700);
    y=(int)(Math.random()*600);
  }
  public void move()
  {
      y=y+(int)(Math.random()*8+1);
  }
  public void wrap()
  {
    if ((y+6)>=600)
    {
      y=0;
      x=(int)(Math.random()*600);
    }
  }
  public void show()
  {
  	noStroke();
    fill(255,255,255,(int)(Math.random()*255));
    smooth();
    int sizeA = (int)(Math.random()*5+5);
    ellipse(x, y, sizeA, sizeA);
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "FractalTree" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
