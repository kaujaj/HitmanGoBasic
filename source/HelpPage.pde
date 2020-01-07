class HelpPage {
  HelpPage() {
  }
  void data() {
    int i=1;
    background(img);
    String[] helptext = loadStrings("help.txt");
    for (String data : helptext) {
      text(data, 50, i*30);
      i+=1;
    }

    fill(#CBCBCB);
    stroke(255);
    ellipse(800, 800, 50, 50);                       //Back
    noFill();
    fill(255);
    textSize(20);
    text("Back", 775, 805);
    noStroke();
    noFill();
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
  void back() {
    if (lp.overCircle(800, 800, 100)) {
      state=0;
      println("back");
    }
  }
}
