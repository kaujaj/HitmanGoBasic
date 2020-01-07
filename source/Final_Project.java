import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 
import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Final_Project extends PApplet {



SoundFile file;
ControlP5 cp5;
StartPage sp;
LevelPage lp;
EndPage ep;
level1 l1;
level2 l2;
level3 l3;
HelpPage hp;
int score=0;
int state=0;
PImage img;
boolean winns= false;
LeaderBoard lb;
public void setup() {  
  
  cp5 = new ControlP5(this);
  file = new SoundFile(this, "HitmanSnipertheme.mp3");
  file.play();
  file.amp(0.5f);
  img=loadImage("hitman3.jpg");

  sp=new StartPage();
  lp= new LevelPage();
  ep= new EndPage();
  l1 = new level1();
  l2 = new level2();
  l3 = new level3();
  hp= new HelpPage();
  lb=new LeaderBoard();
}

public void draw() {

  clear();
  background(255);
  //scale(0.5);
  switch(state) {
  case -1:
    {

      ep.design();
      break;
    }
  case 0:
    {
      clear();
      sp.startbtn();
      sp.settingsbtn();
      sp.leaderboard();
      sp.exitbtn();
      break;
    }
  case 1:
    {
      clear();
      lp.leveldesign();
      break;
    }
  case 2:
    {
      clear();
      l1.surface(); 
      break;
    }
  case 3:
    {
      clear();
      l2.surface(); 
      break;
    }
  case 4:
    {
      clear();
      l3.surface();
      break;
    }
  case 5:
    {
      clear();
      hp.data();
      break;
    }
  case 6:
    {
      lb.showdata(); 
      break;
    }
  case 100:
    {
      //clear();
      ep.scoreresult();
      break;
    }
  }
}
public void mouseClicked() {
  switch(state) {
  case 0:
    {
      sp.position();
      break;
    }
  case 1:
    {
      lp.actions();
      break;
    }
  case 2:
    {
      l1.action();
      break;
    }
  case 3:
    {
      l2.action();
      break;
    }
  case 4:
    {
      l3.action();
      break;
    }
  case 5:
    {
      hp.back();
      break;
    }
  case 6:
    {
      lb.back();
      break;
    }

  case 100:
    {
      ep.checkinp();
      ep.action();
      break;
    }
  }
}
public void keyPressed() {
  switch(state) {
  case 2:
    {
      l1.moveaction();
      l1.checkdeath();
      break;
    }
  case 3:
    {
      l2.moveaction();
      l2.checkkill(); 
      l2.checkdeath();
      break;
    }
  case 4:
    {
      l3.moveaction();
      l3.checkdeath();
    }
  }
}
class EndPage {

  PFont pf;
  EndPage() {
    
    pf = loadFont("Arial-Black-20.vlw");
  }
  public void design() {
    cp5.addTextfield("input")
      .setPosition(350, 500)
      .setSize(300, 30)
      .setFont(pf)
      .setFocus(true);
    state = 100;
  }
  public void checkinp() {
    String inp= cp5.get(Textfield.class, "input").getText();
    println(inp+","+score); 
    String[] scores=  loadStrings("scores.csv");
    String[] newscore=new String[scores.length+1];
    for(int i=0;i<scores.length;i++){
      newscore[i]=scores[i];
    }
    newscore[scores.length]= inp+","+score;
    saveStrings("scores.csv",newscore);
  }

  public void scoreresult() {
    background(img);
    
    stroke(255);
    fill(0xffCBCBCB);
    ellipse(800, 800, 50, 50);                          //Back
    noFill();
    fill(255);
    textSize(20);
    text("Back", 775, 805);
    noStroke();
    noFill();

    pushMatrix();
    stroke(255);
    fill(255);
    textSize(60);
    text("GAME OVER", 340, 300);
    text("Your Score is "+score, 290, 400);
    noFill();
    noStroke();
    popMatrix();
    if(winns){
      pushMatrix();
    stroke(255);
    fill(255);
    textSize(60);
    text("You Win!!", 340, 100);
    noFill();
    noStroke();
    popMatrix();
    }
  }
  public void action() {
    if (lp.overCircle(800, 800, 50)) {
      cp5.get(Textfield.class, "input").hide();
      state=0;
      println("back");
    }
  }
}
class HelpPage {
  HelpPage() {
  }
  public void data() {
    int i=1;
    background(img);
    String[] helptext = loadStrings("help.txt");
    for (String data : helptext) {
      text(data, 50, i*30);
      i+=1;
    }

    fill(0xffCBCBCB);
    stroke(255);
    ellipse(800, 800, 50, 50);                       //Back
    noFill();
    fill(255);
    textSize(20);
    text("Back", 775, 805);
    noStroke();
    noFill();
  }
  public boolean overCircle(int x, int y, int diameter) {
    float disX = x - mouseX;
    float disY = y - mouseY;
    if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
      return true;
    } else {
      return false;
    }
  }
  public void back() {
    if (lp.overCircle(800, 800, 100)) {
      state=0;
      println("back");
    }
  }
}
class LevelPage {
  LevelPage() {
  }
  public void leveldesign() {
    clear();
    background(img);

    stroke(255);
    ellipse(200, 450, 130, 100);
    fill(255);
    textSize(40);
    text("Level1", 140, 460);
    noFill();
    noStroke();

    stroke(255);
    ellipse(400, 450, 130, 100);
    fill(255);
    textSize(40);
    text("Level2", 340, 460);
    noFill();
    noStroke();

    stroke(255);
    ellipse(600, 450, 130, 100); 
    fill(255);
    textSize(40);
    text("Level3", 540, 460);
    noFill();
    noStroke();

    stroke(255);
    ellipse(800, 450, 130, 100);
    fill(255);
    textSize(40);
    text("Back", 750, 460);
    noFill();
    noStroke();


    //try{
    //Thread.sleep(1500);
    //}
    //catch(Exception e){
    //}
  }
  public boolean overCircle(int x, int y, int diameter) {
    float disX = x - mouseX;
    float disY = y - mouseY;
    if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
      return true;
    } else {
      return false;
    }
  }
  public void actions() {
    if (lp.overCircle(200, 450, 100)) {
      state = 2;
      println("Hi");
    }
    if (lp.overCircle(400, 450, 100)) {
      state= 3;
      println("Hi");
    }
    if (lp.overCircle(600, 450, 100)) {
      state= 4;
      println("Hi");
    }
    if (lp.overCircle(800, 450, 100)) {
      state=0;
      println("back");
    }
  }
}

class StartPage {
  StartPage() {
    strokeWeight(3);
    noFill();
    
  }
  public void startbtn() {
    background(img);
    stroke(255);
    ellipse(500, 550, 120, 120);
    fill(255);
    textSize(50);
    text("GO", 465, 570);
    noFill();
    noStroke();
  }
  public void settingsbtn() {
    stroke(255);
    ellipse(100, 100, 100, 100);
    textSize(25);
    text("Help", 70, 110);
    noStroke();
  }
  public void leaderboard() {
    stroke(255);
    ellipse(250, 100, 100, 100);
    textSize(25);
    text("Ranks", 210, 110);
    noStroke();
  }
  public void exitbtn() {
    stroke(255);
    ellipse(width-200, height-180, 120, 120);
    fill(255);
    textSize(50);
    text("EXIT", width-250, height-160); 
    noFill();
    noStroke();
  }
  public void pagebg() {
  }
  public boolean overCircle(int x, int y, int diameter) {
    float disX = x - mouseX;
    float disY = y - mouseY;
    if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
      return true;
    } else {
      return false;
    }
  }
  public void position() {

    if (sp.overCircle(100, 100, 100)) {
      println("Here");
      state=5;
    }
    if (sp.overCircle(500, 550, 120)) {
      println("Start");
      state=1;
    }
    if (sp.overCircle(250, 100, 100)) {
      println("Leaderboard");
      state=6;
      //lp=new LevelPage();
    }
    if (sp.overCircle(width-200, height-200, 120)) {
      println("Exit"); 
      System.exit(0);
    }
  }
}
class LeaderBoard {
  Table T;
  LeaderBoard() {
    T= loadTable("scores.csv", "header");
  }
  public void showdata() {
    background(img);
    pushMatrix();
    translate(0, 150);
    fill(255);
    int i=0;
    for (TableRow row : T.rows()) {
      textSize(20);
      text(row.getString("name")+ "  " + row.getInt("score"), 100, i*40);
      //println(row.getString("name")+ "  " + row.getInt("score"));
      i+=1;
    }
    noStroke();
    popMatrix();

    fill(0xffCBCBCB);
    stroke(255);
    ellipse(800, 800, 50, 50);                       //Back
    noFill();
    fill(255);
    textSize(20);
    text("Back", 775, 805);
    noStroke();
    noFill();
  }
  public boolean overCircle(int x, int y, int diameter) {
    float disX = x - mouseX;
    float disY = y - mouseY;
    if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
      return true;
    } else {
      return false;
    }
  }
  
  public void back() {
    if (lb.overCircle(800, 800, 50)) {
      state=0;
      println("back");
    }
  }
}

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

  public void surface() {
    
    fill(0xffCBCBCB);
    ellipse(800, 800, 50, 50);                       //Back
    noFill();
    fill(0);
    textSize(20);
    text("Back", 775, 805);
    noFill();
    
    pushMatrix();
    translate(0, 0, -320);
    rotateX(0.33f);

    pushMatrix();
    fill(0xff26A516);
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
    rotateX(1.8f);
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
    fill(0xff8E541E);
    quad(-40, 25, 0, 0, 0, 900, -40, 925);             //Outside Surface
    quad(0, 900, -40, 925, 960, 925, 1000, 900);
    popMatrix();
    hitman();
    popMatrix();
  }
  public boolean overCircle(int x, int y, int diameter) {
    float disX = x - mouseX;
    float disY = y - mouseY;
    if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
      return true;
    } else {
      return false;
    }
  }
  public void action() {
    if (lp.overCircle(800, 800, 50)) {
      state=1;
      println("back");
    }
  }//on left or right click increament x by 235
  //on up or down click increament y by 300
  
  public void hitman() {
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
  
  public void moveaction() {
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
  
  public void checkdeath() {
    
    if(kills==0){
      if(startx+movex==560 && starty+movey==450){ kills=1; score+=100;}
    if(startx+movex==325 && starty+movey==450){
      state=-1;
    }}
  }
}
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

  public void surface() {

    fill(0xffCBCBCB);
    ellipse(800, 800, 50, 50);                         //Back
    noFill();
    fill(0);
    textSize(20);
    text("Back", 775, 805);
    noFill();

    pushMatrix();
    translate(0, 0, -320);
    rotateX(0.33f);

    pushMatrix();
    fill(0xff26A516);
    noStroke();
    rect(0, 0, 1000, 900);                           //grass
    popMatrix();


    pushMatrix();
    stroke(0);
    translate(850, 580, 0);
    fill(0xff09680A);
    box(250, 80, 80);                                //grass bush
    popMatrix();

    pushMatrix();
    stroke(0);
    translate(765, 720, 0);
    fill(0xff09680A);
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
    fill(0xff8E541E);
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
  public boolean overCircle(int x, int y, int diameter) {
    float disX = x - mouseX;
    float disY = y - mouseY;
    if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
      return true;
    } else {
      return false;
    }
  }
  public void action() {
    if (lp.overCircle(800, 800, 50)) {
      state=1;
      println("back");
    }
  }
  //incerament x by 180
  //increamnet y by 300
  public void hitman() {
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
  public void moveaction() {//move logic

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
  public void checkdeath() {
    for (int chk=0; chk<xkills.size(); chk++) {
      if (startx+movex==xkills.get(chk) && starty+movey==ykills.get(chk)) {
        state=-1;
      }
    }
  }
  public void checkkill(){
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
PShape b1;

class level3 extends Win{
  boolean ar = false;
  int startx=410, starty= 750;
  int gold=0;
  int movex=0, movey=0;
  level3() {
    b1 = loadShape("Fir_Tree.obj");
  }

  public void surface() {

    fill(0xffCBCBCB);
    ellipse(800, 800, 50, 50);                           //Back
    noFill();
    fill(0);
    textSize(20);
    text("Back", 775, 805);
    noFill();

    pushMatrix();
    translate(0, 0, -320);
    rotateX(0.33f);

    pushMatrix();
    fill(0xffE8B454);
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

    fill(0xffFFA703);
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
    fill(0xff8E541E);
    quad(-40, 25, 0, 0, 0, 900, -40, 925);             //Outside Surface Box
    quad(0, 900, -40, 925, 960, 925, 1000, 900);
    popMatrix(); 
    hitman();
    popMatrix();
  }
  public boolean overCircle(int x, int y, int diameter) {
    float disX = x - mouseX;
    float disY = y - mouseY;
    if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
      return true;
    } else {
      return false;
    }
  }
  public void action() {
    if (lp.overCircle(800, 800, 50)) {
      state=1;
      println("back");
    }
  }
  public void hitman() {
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
  public void moveaction() {

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
  public void checkdeath() {
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
class Win{

  Win(){}
  
  public void ReachGoal(){
  
    if(state==4){
       winns= true;
      state=-1;
     
    }
    else
      state=state+1;
  }
}
  public void settings() {  size(1000, 900, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Final_Project" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
