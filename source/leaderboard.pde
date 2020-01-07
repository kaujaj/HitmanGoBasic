class LeaderBoard {
  Table T;
  LeaderBoard() {
    T= loadTable("scores.csv", "header");
  }
  void showdata() {
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
    if (lb.overCircle(800, 800, 50)) {
      state=0;
      println("back");
    }
  }
}
