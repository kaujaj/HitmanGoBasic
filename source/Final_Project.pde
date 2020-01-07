import controlP5.*;
import processing.sound.*;
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
void setup() {  
  size(1000, 900, P3D);
  cp5 = new ControlP5(this);
  file = new SoundFile(this, "HitmanSnipertheme.mp3");
  file.play();
  file.amp(0.5);
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

void draw() {

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
void mouseClicked() {
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
void keyPressed() {
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
