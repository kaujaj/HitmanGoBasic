
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
  boolean overCircle(int x, int y, int diameter) {
    float disX = x - mouseX;
    float disY = y - mouseY;
    if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
      return true;
    } else {
      return false;
    }
  }
  void position() {

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
