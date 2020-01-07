
class level1 extends Win{
  PImage img;
  PShape s1;
  int startx=90, starty= 450;
  int kills=0;
  int movex=0, movey=0;
  level1() {  
    img = loadImage("water.jpg");
    s1 = loadShape("Ferns.obj");
  }

  void surface() {
    
    fill(#CBCBCB);
    ellipse(800, 800, 50, 50);                       //Back
    noFill();
    fill(0);
    textSize(20);
    text("Back", 775, 805);
    noFill();
    
    pushMatrix();
    translate(0, 0, -320);
    rotateX(0.33);

    pushMatrix();
    fill(#26A516);
    noStroke();
    rect(0, 0, 1000, 900);                           //grass
    popMatrix();

    pushMatrix();
    beginShape();
    noStroke();
    texture(img);                                 //water
    vertex(700, 0, 0, 0);
    vertex(1000, 0, 300, 0);
    vertex(1000, 600, 300, 300);
    vertex(700, 600, 0, 300);
    endShape();
    popMatrix();

    pushMatrix();
    stroke(0);
    for (int x=0; x<=580; x=x+40) {
      fill(255);
      rect(650, x, 50, 40);                          //PoolWall
    }
    for (int x=650; x<=950; x=x+50) {
      fill(255);
      rect(x, 600, 50, 40);
    }
    popMatrix();

    pushMatrix();
    translate(300, 600, 0);
    rotateX(1.8);
    scale(70);
    shape(s1, 0, 0);                                //tree
    popMatrix();

    pushMatrix();
    stroke(0);
    strokeWeight(2);
    line(90, 750, 560, 750);                        //Path of Hitman
    line(90, 450, 560, 450);
    line(90, 750, 90, 450);
    line(560, 750, 560, 450);

    line(560, 450, 560, 150);
    popMatrix();

    pushMatrix();
    stroke(0);
    fill(255);
    ellipse(90, 750, 20, 20);                         //Points of Hitman
    ellipse(560, 750, 20, 20);
    ellipse(90, 450, 20, 20);
    ellipse(560, 450, 20, 20);

    ellipse(325, 750, 20, 20);
    ellipse(325, 450, 20, 20);

    ellipse(560, 150, 50, 50);
    ellipse(560, 150, 20, 20);
    popMatrix();
    if(kills==0){
    pushMatrix();
    stroke(color(255,0,0));
    strokeWeight(3);
    line(560,450,530,450);                           //Arrow of Soldier
    fill(color(255,0,0));
    triangle(530,460,530,440,520,450);
    noStroke();
    noFill();
    popMatrix();
    }
    pushMatrix();
    fill(#8E541E);
    quad(-40, 25, 0, 0, 0, 900, -40, 925);             //Outside Surface
    quad(0, 900, -40, 925, 960, 925, 1000, 900);
    popMatrix();
    hitman();
    popMatrix();
  }
  boolean overCircle(int x, int y, int diameter) {
    float disX = x - mouseX;
    float disY = y - mouseY;
    if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
      return true;
    } else {
      return false;
    }
  }
  void action() {
    if (lp.overCircle(800, 800, 50)) {
      state=1;
      println("back");
    }
  }//on left or right click increament x by 235
  //on up or down click increament y by 300
  
  void hitman() {
    pushMatrix();
    stroke(0);
    strokeWeight(2);
    //translate(0, 0, -320);
    //translate(movex,movey);
    //ellipse(startx+movex, starty+movey, 50, 50);
    translate(startx+movex, starty+movey);
    fill(100);
    box(20,20,100);
    popMatrix();
  }
  
  void moveaction() {
    if (key=='w') {
      if (startx+movex==90 && starty+movey==450) {
      } else if (startx+movex==325 && starty+movey==450) {
      } else if (startx+movex==325 && starty+movey==750) {
      } else
        movey-= 300;
    }
    if (key=='s') {
      if (starty+movey==750) {
      } else if (startx+movex==325 && starty+movey==450) {
      } else
        movey+= 300;
    }
    if (key=='a') {
      if (startx+movex==90) {
      } else
        movex-= 235;
    }
    if (key=='d') {
      if (startx+movex==560) {
      } else
        movex+= 235;
    }
    if(startx+movex==560 && starty+movey==150){
        ReachGoal();
    }
  }
  
  void checkdeath() {
    
    if(kills==0){
      if(startx+movex==560 && starty+movey==450){ kills=1; score+=100;}
    if(startx+movex==325 && starty+movey==450){
      state=-1;
    }}
  }
}
