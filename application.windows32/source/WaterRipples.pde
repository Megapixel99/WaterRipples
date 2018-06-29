int cols;
int rows;

float[][] curr;
float[][] prev;

float damping = 0.97;
void setup(){
  size(600,400);
  cols = width;
  rows = height;
  curr = new float[cols][rows];
  prev = new float[cols][rows];
}

void mousePressed(){
  curr[mouseX][mouseY] = 25500;
}  
void draw(){
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
