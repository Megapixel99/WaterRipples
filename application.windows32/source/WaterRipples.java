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

public class WaterRipples extends PApplet {

int cols;
int rows;

float[][] curr;
float[][] prev;

float damping = 0.97f;
public void setup(){
  
  cols = width;
  rows = height;
  curr = new float[cols][rows];
  prev = new float[cols][rows];
}

public void mousePressed(){
  curr[mouseX][mouseY] = 25500;
}  
public void draw(){
  background(0);
  loadPixels();
  for(int x = 1; x < cols - 1; x++){
    for (int y = 1; y < rows - 1; y++){
        curr[x][y] = (prev[x-1][y] + 
                      prev[x+1][y] + 
                      prev[x][y-1] + 
                      prev[x][y+1]) / 2 - 
                      curr[x][y];
          curr[x][y] *= damping;
          int index = x + y * cols;
          pixels[index] = color(curr[x][y]);
          
    }
  }
  updatePixels();
  float [][] temp = prev;
  prev = curr;
  curr = temp;
}
  public void settings() {  size(600,400); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "WaterRipples" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
