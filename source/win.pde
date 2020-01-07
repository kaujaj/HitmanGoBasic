class Win{

  Win(){}
  
  void ReachGoal(){
  
    if(state==4){
       winns= true;
      state=-1;
     
    }
    else
      state=state+1;
  }
}
