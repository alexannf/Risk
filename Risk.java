package Risk;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Risk {

    Terning terning1 = new Terning();
    Terning terning2 = new Terning();
    Terning terning3 = new Terning();
    Terning terning4 = new Terning();
    Terning terning5 = new Terning();



    public void simulerKamp(int antallSimuleringer, int antallAngrep, int antallForsvar){

    }

    private Collection<Terning> angrepsKast3(){
        terning1.kastTerning();
        terning2.kastTerning();
        terning3.kastTerning();
        Collection<Terning> angrepsKast = Arrays.asList(terning1, terning2, terning3);
        return angrepsKast;
    }

    private Collection<Terning> angrepsKast2(){
        terning1.kastTerning();
        terning2.kastTerning();
        Collection<Terning> angrepsKast = Arrays.asList(terning1, terning2);
        return angrepsKast;
    }

    private Collection<Terning> angrepsKast1(){
        terning1.kastTerning();
        Collection<Terning> angrepsKast = Arrays.asList(terning1);
        return angrepsKast;
    }

    private Collection<Terning> forsvarsKast2(){
        terning4.kastTerning();
        terning5.kastTerning();
        Collection<Terning> forsvarsKast = Arrays.asList(terning4, terning5);
        return forsvarsKast;
    }

    private Collection<Terning> forsvarsKast1(){
        terning4.kastTerning();
        Collection<Terning> forsvarsKast = Arrays.asList(terning4);
        return forsvarsKast;
    }

    public void simuler3_2(int antallAngrep, int antallForsvar){
        int angrep = antallAngrep;
        int forsvar = antallForsvar;

        Collection<Terning> angrepsKast = angrepsKast3();
        Collection<Terning> forsvarsKast = forsvarsKast2();
        int besteAngrep1 = angrepsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();
        angrepsKast.remove(besteAngrep1);

        int besteForsvar1 = forsvarsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();
        forsvarsKast.remove(besteForsvar1);

        if(besteAngrep1 > besteForsvar1){
            forsvar --;
        }
        else{
            angrep --;
        }

        int besteAngrep2 = angrepsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();

        int besteForsvar2 = forsvarsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();

        if(besteAngrep2 > besteForsvar2){
            simuler(angrep, forsvar-1);
        }
        else{
            simuler(angrep-1, forsvar);
        }
    }

    public void simuler3_1(int antallAngrep, int antallForsvar){
        Collection<Terning> angrepsKast = angrepsKast3();
        terning4.kastTerning();
        int besteAngrep = angrepsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();
        if(besteAngrep > terning4.getScore()){
            // TODO: finne ut hva som skal returneres
        }
        else{
            simuler(antallAngrep-1, antallForsvar);
        }
    }

    public void simuler2_2(int antallAngrep, int antallForsvar){
        int angrep = antallAngrep;
        int forsvar = antallForsvar;

        Collection<Terning> angrepsKast = angrepsKast2();
        Collection<Terning> forsvarsKast = forsvarsKast2();
        int besteAngrep1 = angrepsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();
        angrepsKast.remove(besteAngrep1);

        int besteForsvar1 = forsvarsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();
        forsvarsKast.remove(besteForsvar1);

        if(besteAngrep1 > besteForsvar1){
            forsvar --;
        }
        else{
            angrep --;
        }

        int besteAngrep2 = angrepsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();

        int besteForsvar2 = forsvarsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();

        if(besteAngrep2 > besteForsvar2){
            simuler(angrep, forsvar-1);
        }
        else{
            simuler(angrep-1, forsvar);
        }
    }

    public void simuler2_1(int antallAngrep, int antallForsvar){
        Collection<Terning> angrepsKast = angrepsKast2();
        terning4.kastTerning();
        int besteAngrep = angrepsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();
        if(besteAngrep > terning4.getScore()){
            // TODO: finne ut hva som skal returneres
        }
        else{
            simuler(antallAngrep-1, antallForsvar);
        }
    }

    public void simuler1_2(int antallAngrep, int antallForsvar){
        terning1.kastTerning();
        Collection<Terning> forsvarsKast = forsvarsKast2();

        int besteForsvar = forsvarsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();

        if(terning1.getScore() > besteForsvar){
            simuler(antallAngrep, antallForsvar-1);
        }
        else{
            // TODO: finne ut hva som skal returneres
        }


    }

    public void simuler1_1(int antallAngrep, int antallForsvar){
        terning1.kastTerning();
        terning4.kastTerning();
        if(terning1.getScore()>terning4.getScore()){
            // TODO: finne ut hva som skal returneres
        }
        else{
            // TODO: finne ut hva som skal returneres
        }
    }

    // condition-handling metode for å sikre riktige rekursive kall
    public void simuler(int antallAngrep, int antallForsvar){
        if(antallAngrep<=0){
        }
        if(antallForsvar<=0){
        }
        if(antallAngrep>=3){
            if(antallForsvar>=2){
                simuler3_2(antallAngrep, antallForsvar);
            }
            else
                simuler3_1(antallAngrep, antallForsvar);
        }
        else if(antallAngrep == 2){
            if(antallForsvar>=2){
                simuler2_2(antallAngrep, antallForsvar);
            }
            else
                simuler2_1(antallAngrep, antallForsvar);
        }
        else{
            if(antallForsvar>=2){
                simuler1_2(antallAngrep, antallForsvar);
            }
            else
                simuler1_1(antallAngrep, antallForsvar);
        }
    }





}
