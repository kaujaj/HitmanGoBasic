PShape b1;

class level3 extends Win{
  boolean ar = false;
  int startx=410, starty= 750;
  int gold=0;
  int movex=0, movey=0;
  level3() {
    b1 = loadShape("Fir_Tree.obj");
  }

  void surface() {

    fill(#CBCBCB);
    ellipse(800, 800, 50, 50);                           //Back
    noFill();
    fill(0);
    textSize(20);
    text("Back", 775, 805);
    noFill();

    pushMatrix();
    translate(0, 0, -320);
    rotateX(0.33);

    pushMatrix();
    fill(#E8B454);
    noStroke();
    rect(0, 0, 1000, 900);                           //grass
    popMatrix();

    pushMatrix();
    translate(100, 100, 0);
    rotateX(radians(120));
    scale(35);
    for (int x=0; x<=24; x=x+2) {
      shape(b1, x, 0);
    }
    popMatrix();

    pushMatrix();
    stroke(0);
    strokeWeight(2);
    translate(50, 50);

    line(140, 750, 680, 750);                        //Path of Hitman
    line(140, 450, 680, 450);
    line(410, 150, 680, 150);

    line(140, 750, 140, 450);
    line(680, 750, 680, 450);
    line(680, 450, 680, 150);

    popMatrix();

    pushMatrix();
    stroke(0);
    translate(50, 50);
    fill(color(255, 255, 255));
    ellipse(140, 750, 20, 20);                         //Points of Hitman
    ellipse(680, 750, 20, 20);
    ellipse(680, 450, 20, 20);
    ellipse(680, 150, 20, 20);

    ellipse(410, 750, 20, 20);
    ellipse(410, 450, 20, 20);

    ellipse(410, 150, 50, 50);                        //final point
    ellipse(410, 150, 20, 20);

    fill(#FFA703);
    ellipse(140, 450, 20, 20);                     //golden point
    noFill();
    popMatrix();
    
    
    if(!(startx+movex==680 && starty+movey==150)){
      pushMatrix();
    translate(50,50);
    if (!ar) {  
      stroke(color(255, 0, 0));
      strokeWeight(3);
      line(680, 150, 680, 180);                           //Arrow of Soldier
      fill(color(255, 0, 0));
      triangle(670, 180, 690, 180, 680, 200);
      noStroke();
      noFill();      
    } else {
      stroke(color(255, 0, 0));
      strokeWeight(3);
      line(680, 150, 650, 150);                           //Arrow of Soldier
      fill(color(255, 0, 0));
      triangle(650, 160, 650, 140, 610, 150);
      noStroke();
      noFill();
    }
    popMatrix();
    }
    pushMatrix();
    fill(#8E541E);
    quad(-40, 25, 0, 0, 0, 900, -40, 925);             //Outside Surface Box
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
  }
  void hitman() {
    pushMatrix();
    translate(50, 50);
    stroke(0);
    strokeWeight(2);
    //translate(0, 0, -320);
    //ellipse(startx+movex, starty+movey, 50, 50);
    translate(startx+movex, starty+movey);
    fill(100);
    box(20,20,100);
    popMatrix();
  }
  void moveaction() {

    if (key=='w') {
      if (startx+movex==140 && starty+movey==450) {
      } else if (startx+movex==410 && starty+movey==450) {
      } else if (startx+movex==410 && starty+movey==750) {
      } else
        movey-= 300;
    }
    if (key=='s') {
      if (starty+movey==750) {
      } else if (startx+movex==410 && starty+movey==450) {
      } else
        movey+= 300;
    }
    if (key=='a') {
      if (startx+movex==140) {
      } else
        movex-= 270;
    }
    if (key=='d') {
      if (startx+movex==680) {
      } else
        movex+= 270;
    }
    if(startx+movex==410 && starty+movey==150){
        ReachGoal();
    }
  }
  void checkdeath() {
    if (startx+movex==140 && starty+movey==450) {
      gold=1;
      score+=100;
      ar=true;
    }
    if (gold==0) {
      if (startx+movex==680 && starty+movey==450) {
        state=-1;
      }
    }
  }
}
