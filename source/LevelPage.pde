class LevelPage {
  LevelPage() {
  }
  void leveldesign() {
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
  boolean overCircle(int x, int y, int diameter) {
    float disX = x - mouseX;
    float disY = y - mouseY;
    if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
      return true;
    } else {
      return false;
    }
  }
  void actions() {
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
