class EndPage {

  PFont pf;
  EndPage() {
    
    pf = loadFont("Arial-Black-20.vlw");
  }
  void design() {
    cp5.addTextfield("input")
      .setPosition(350, 500)
      .setSize(300, 30)
      .setFont(pf)
      .setFocus(true);
    state = 100;
  }
  void checkinp() {
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

  void scoreresult() {
    background(img);
    
    stroke(255);
    fill(#CBCBCB);
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
  void action() {
    if (lp.overCircle(800, 800, 50)) {
      cp5.get(Textfield.class, "input").hide();
      state=0;
      println("back");
    }
  }
}
