PShape s2;

class level2 extends Win{
  int kill1=0, kill2, kill3;
  int startx=140, starty= 750;

  int movex=0, movey=0;
  boolean kill31=false, kill32=false;
  ArrayList<Integer> xkills= new ArrayList<Integer>();
  ArrayList<Integer> ykills= new ArrayList<Integer>();

  level2() {
    s2 = loadShape("Palm_Tree.obj");
    xkills.add(140);//death list
    xkills.add(320);
    xkills.add(680);
    ykills.add(450);
    ykills.add(450);
    ykills.add(150);
  }

  void surface() {

    fill(#CBCBCB);
    ellipse(800, 800, 50, 50);                         //Back
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
    stroke(0);
    translate(850, 580, 0);
    fill(#09680A);
    box(250, 80, 80);                                //grass bush
    popMatrix();

    pushMatrix();
    stroke(0);
    translate(765, 720, 0);
    fill(#09680A);
    box(80, 360, 80);                                //grass bush
    popMatrix();

    pushMatrix();
    translate(900, 700, 0);
    rotateX(radians(110));
    scale(40);
    shape(s2, 0, 0);                                  //Palm tree
    popMatrix();

    pushMatrix();
    translate(860, 800, 0);
    rotateX(radians(110));
    scale(40);
    shape(s2, 0, 0);                                  //Palm tree
    popMatrix();

    pushMatrix();
    fill(#8E541E);
    quad(-40, 25, 0, 0, 0, 900, -40, 925);             //Outside Surface Box
    quad(0, 900, -40, 925, 960, 925, 1000, 900);
    popMatrix();

    pushMatrix();
    stroke(0);
    strokeWeight(2);
    line(140, 750, 680, 750);                        //Path of Hitman
    line(140, 450, 500, 450);
    line(140, 150, 680, 150);

    line(140, 750, 140, 450);
    line(680, 750, 680, 450);
    line(140, 450, 140, 150);
    line(680, 450, 680, 150);

    //line(10, 750, 140, 750);
    line(680, 150, 860, 150);
    line(320, 450, 320, 150);
    line(500, 450, 500, 750);
    popMatrix();

    pushMatrix();
    stroke(0);
    fill(color(255, 255, 255));                          //Points of Hitman
    ellipse(140, 750, 20, 20);                        //start point                      
    ellipse(680, 750, 20, 20);
    ellipse(140, 450, 20, 20);                        //death1
    ellipse(680, 450, 20, 20);                        //kill4
    ellipse(140, 150, 20, 20);
    ellipse(680, 150, 20, 20);                        //death3

    ellipse(320, 750, 20, 20);
    ellipse(500, 750, 20, 20);
    ellipse(320, 450, 20, 20);                        //death2//kill2
    ellipse(500, 450, 20, 20);                        //kill1
    ellipse(320, 150, 20, 20);
    ellipse(500, 150, 20, 20);                        //kill3

    //ellipse(10, 750, 20, 20);
    ellipse(860, 150, 50, 50);
    ellipse(860, 150, 20, 20);
    popMatrix();
    if(kill1==0){
    pushMatrix();
    translate(-60,0);
    stroke(color(255, 0, 0));
    strokeWeight(3);
    line(560, 450, 530, 450);                         //Arrow
    fill(color(255, 0, 0));
    triangle(530, 460, 530, 440, 510, 450);
    noStroke();
    noFill();
    popMatrix();
    }
    if(kill2==0){
    pushMatrix();
    translate(-240,0);
    stroke(color(255,0,0));
    strokeWeight(3);
    line(560,450,530,450);                           //Arrow of Soldier
    fill(color(255,0,0));
    triangle(530,460,530,440,510,450);
    noStroke();
    noFill();
    popMatrix();
    }
    if(!kill32){
    pushMatrix();
    stroke(color(255,0,0));
    strokeWeight(3);
    line(500,150,530,150);                           //Arrow of Soldier
    fill(color(255,0,0));
    triangle(530,160,530,140,550,150);
    noStroke();
    noFill();
    popMatrix();
    }
    if(!kill31){
    pushMatrix();
    stroke(color(255,0,0));
    strokeWeight(3);
    line(680,450,680,420);                           //Arrow of Soldier
    fill(color(255,0,0));
    triangle(670,420,690,420,680,400);
    noStroke();
    noFill();
    popMatrix();
    }
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
  //incerament x by 180
  //increamnet y by 300
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
  void moveaction() {//move logic

    if (key=='w') {
      if (starty+movey==150) {
      } else if (startx+movex==320 &&starty+movey==750 ) {
      } else if (startx+movex==500 &&starty+movey==450) {
      } else
        movey-= 300;
    }
    if (key=='s') {
      if (starty+movey==750) {
      } else if (startx+movex==500 &&starty+movey==150) {
      } else if (startx+movex==320 &&starty+movey==450) {
      } else
        movey+= 300;
    }
    if (key=='a') {
      if (startx+movex==140) {
      } else if (startx+movex==680 &&starty+movey==450) {
      } else
        movex-= 180;
    }
    if (key=='d') {
      if (startx+movex==680 && starty+movey==750) {
      } else if (startx+movex==500 && starty+movey==450) {
      } else if (startx+movex==680 && starty+movey==450) {
      } else
        movex+= 180;
    }
    if(startx+movex==860 && starty+movey==150){
        ReachGoal();
    }
  }
  void checkdeath() {
    for (int chk=0; chk<xkills.size(); chk++) {
      if (startx+movex==xkills.get(chk) && starty+movey==ykills.get(chk)) {
        state=-1;
      }
    }
  }
  void checkkill(){
    if(kill1==0){
    if(startx+movex==500 && starty+movey==450){
      println("here");
     xkills.remove(new Integer(320));
     ykills.remove(new Integer(450));
     score+=100;
     kill1=1;
    }
   }
   if(kill2==0){
    if(startx+movex==320 && starty+movey==450){
     xkills.remove(new Integer(140));
     ykills.remove(new Integer(450));
     score+=100;
     kill2=1;
    }
   }
   if(startx+movex==680 && starty+movey==450 && !kill31){
     score+=100;
     kill31=true; 
   }
   if(startx+movex==500 && starty+movey==150 && !kill32){
     score+=100; 
     kill32=true; 
   }
   if(kill3==0){
   if(kill31 && kill32){
     xkills.remove(new Integer(680));
     ykills.remove(new Integer(150));
     kill3=1;
   }}
 }
}
