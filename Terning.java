package Risk;

import java.util.Comparator;

public class Terning implements Comparator<Terning> {

    private int score;
    private final int idnr;
    private static int id = 1;


    public Terning(){
        this.idnr = Terning.id ++;
        this.score = -1;

    }

    public void kastTerning(){
        this.score = (int) (Math.random()*6) + 1 ;
    }

    public int getScore(){
        return this.score;
    }

    public void kastXganger(int x){
        int oneCounter = 0;
        int twoCounter = 0;
        int threeCounter = 0;
        int fourCounter = 0;
        int fiveCounter = 0;
        int sixCounter = 0;
        for(int i=1; i <= x; i++){
            this.kastTerning();
            // System.out.println(this.getScore());
            if(this.getScore() == 1){
                oneCounter ++;
            }
            else if(this.getScore() == 2){
                twoCounter ++;
            }
            else if(this.getScore() == 3){
                threeCounter ++;
            }
            else if(this.getScore() == 4){
                fourCounter ++;
            }
            else if(this.getScore() == 5){
                fiveCounter ++;
            }
            else{
                sixCounter ++;
            }
        }
        System.out.println("#1s: "+oneCounter+"\n#2s: "+twoCounter+"\n#3s: "+threeCounter+"\n#4s: "+
                fourCounter+"\n#5s: "+fiveCounter+"\n#6s: "+sixCounter);
    }

    @Override
    public String toString(){
        return "T" + this.idnr + ": "+this.score;
    }

    public static void main(String[] args) {
        Terning terning = new Terning();
        terning.kastXganger(100);
    }

    @Override
    public int compare(Terning o1, Terning o2) {
        return o1.getScore() - o2.getScore();
    }
}
