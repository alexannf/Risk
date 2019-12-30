package Risk;

import java.util.*;
import java.util.stream.Collectors;

public class Risk {

    Terning terning1 = new Terning(); // angripende terning
    Terning terning2 = new Terning(); // angripende terning
    Terning terning3 = new Terning(); // angripende terning

    Terning terning4 = new Terning(); // forsvarende terning
    Terning terning5 = new Terning(); // forsvarende terning



    public void simulerKamp(int antallSimuleringer, int antallAngrep, int antallForsvar){
        int seiereAngrep = 0;
        int seiereForsvar = 0;
        for(int i=0;i<antallSimuleringer;i++){
            List<Integer> resultat = simuler(antallAngrep, antallForsvar);
            int angrepScore = resultat.get(0);
            int forsvarScore = resultat.get(1);

            if(forsvarScore == 0){
                seiereAngrep++;
            }
            else{
                seiereForsvar++;
            }
            // System.out.println("antall brikker angrep:"+angrepScore+"\nantall brikker forsvar: "+forsvarScore+"\n");
        }
        System.out.println("antall seiere angrep: "+seiereAngrep+"\nantall seiere forsvar: "+seiereForsvar);

    }

    // kaster 3 angrepsterninger og returnerer de som en kolleksjon, hjelpemetode
    private Collection<Terning> angrepsKast3(){
        terning1.kastTerning();
        terning2.kastTerning();
        terning3.kastTerning();
        Collection<Terning> angrepsKast = Arrays.asList(terning1, terning2, terning3);
        return angrepsKast;
    }

    // kaster 2 angrepsterninger og returnerer de som en kolleksjon, hjelpemetode
    private Collection<Terning> angrepsKast2(){
        terning1.kastTerning();
        terning2.kastTerning();
        Collection<Terning> angrepsKast = Arrays.asList(terning1, terning2);
        return angrepsKast;
    }

    // kaster 2 forsvarsterninger og returnerer de som en kolleksjon, hjelpemetode
    private Collection<Terning> forsvarsKast2(){
        terning4.kastTerning();
        terning5.kastTerning();
        Collection<Terning> forsvarsKast = Arrays.asList(terning4, terning5);
        return forsvarsKast;
    }

    // simulerer tilfellet med 3 angripende terninger og 2 forsvarende terninger, se ToUtfall metode for virkemåte
    public List<Integer> simuler3_2(int antallAngrep, int antallForsvar){
        // System.out.println("Simulerer 3 Angrep mot 2 Forsvar:");
        Collection<Terning> angrepsKast = angrepsKast3();
        // System.out.println("Angrepskast: "+angrepsKast);
        return ToUtfall(antallAngrep, antallForsvar, angrepsKast);
    }

    // simulerer tilfellet med 2 angripende terninger og 2 forsvarende terninger, se ToUtfall metode for virkemåte
    public List<Integer> simuler2_2(int antallAngrep, int antallForsvar){
        // System.out.println("Simulerer 2 Angrep mot 2 Forsvar:");
        Collection<Terning> angrepsKast = angrepsKast2();
        // System.out.println("Angrepskast: "+angrepsKast);
        return ToUtfall(antallAngrep, antallForsvar, angrepsKast);
    }

    // tar max terning fra forsvar & angrep og sammenligner, deretter nest-max terning fra forsvar og angrep og
    // sammenligner. Anbefales å lese offisielle risk regler om forvirring rundt denne metoden.
    private List<Integer> ToUtfall(int angrep, int forsvar, Collection<Terning> angrepsKast) {

        // forsvarende parti har to terninger
        Collection<Terning> forsvarsKast = forsvarsKast2();
        // System.out.println("ForsvarsKast: "+forsvarsKast+"\n");


        // sammenligning av de to beste terningkastene for hvert parti

        // henter ut den terningen med høyest antall øyne og fjerner den fra kolleksjonen
        Terning besteAngrep1 = angrepsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new);
        int besteAngrep1Score = besteAngrep1.getScore();

        Collection<Terning> angrepsKastUtenMax = angrepsKast.stream().
                filter(t->!t.equals(besteAngrep1)).collect(Collectors.toList());

        // henter ut den terningen med høyest antall øyne og fjerner den fra kolleksjonen
        Terning besteForsvar1 = forsvarsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new);
        int besteForsvar1Score = besteForsvar1.getScore();

        Collection<Terning> forsvarsKastUtenMax = forsvarsKast.stream().
                filter(t->!t.equals(besteForsvar1)).collect(Collectors.toList());

        // hvis max-terning angrep er høyere enn max-terning forsvar taper forsvarende parti en brikke
        if(besteAngrep1Score > besteForsvar1Score){
            forsvar --;
        }
        // hvis det er likt eller forsvar har høyere taper det angripende parti en brikke
        else{
            angrep --;
        }


        // sammenligning av de to nest-beste terningkastene for hvert parti

        // henter ut terningen med høyest antall øyne, eksludert tidligere maksimum
        int besteAngrep2 = angrepsKastUtenMax.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();

        // henter ut terningen med høyest antall øyne, eksludert tidligere maksimum
        int besteForsvar2 = forsvarsKastUtenMax.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();

        // hvis max-terning angrep er høyere enn max-terning forsvar taper forsvarende parti en brikke
        if(besteAngrep2 > besteForsvar2){
            return simuler(angrep, forsvar-1);
        }
        // hvis det er likt eller forsvar har høyere taper det angripende parti en brikke
        else{
            return simuler(angrep-1, forsvar);
        }
    }

    // simulerer tilfellet med 3 angripende terninger og 1 forsvarende terning (forsvar kun 1 brikke igjen)
    public List<Integer> simuler3_1(int antallAngrep, int antallForsvar){
        // System.out.println("Simulerer 3 Angrep mot 1 Forsvar:");
        Collection<Terning> angrepsKast = angrepsKast3();
        // System.out.println("Angrepskast: "+angrepsKast);
        terning4.kastTerning();
        // System.out.println("Forsvarskast: "+terning4+"\n");
        int besteAngrep = angrepsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();
        if(besteAngrep > terning4.getScore()){
            return Arrays.asList(antallAngrep, 0);
        }
        else{
            return simuler(antallAngrep-1, antallForsvar);
        }
    }

    // simulerer tilfellet med 2 angripende terninger og 1 forsvarende terning (forsvar kun 1 brikke igjen)
    // legg merke til at det kun er 1 sett med terninger som sammenlignes
    public List<Integer> simuler2_1(int antallAngrep, int antallForsvar){
        // System.out.println("Simulerer 2 Angrep mot 1 Forsvar:");
        Collection<Terning> angrepsKast = angrepsKast2();
        // System.out.println("Angrepskast: "+angrepsKast);
        terning4.kastTerning();
        // System.out.println("Forsvarskast: "+terning4+"\n");
        int besteAngrep = angrepsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();
        if(besteAngrep > terning4.getScore()){
            return Arrays.asList(2, 0);
        }
        else{
            return simuler1_1(1, 1);
        }
    }

    // simulerer tilfellet med 1 angripende terning og 2 forsvarende terninger (angrep kun 1 brikke igjen)
    // legg merke til at det kun er 1 sett med terninger som sammenlignes
    public List<Integer> simuler1_2(int antallAngrep, int antallForsvar){
        // System.out.println("Simulerer 1 Angrep mot 2 Forsvar:");
        terning1.kastTerning();
        // System.out.println("Angrepskast: "+terning1);
        Collection<Terning> forsvarsKast = forsvarsKast2();
        // System.out.println("Forsvarskast: "+forsvarsKast+"\n");
        int besteForsvar = forsvarsKast.stream().
                max(Comparator.comparing(Terning::getScore)).orElseThrow(NoSuchElementException::new).getScore();

        if(terning1.getScore() > besteForsvar){
            return simuler(1, antallForsvar-1);
        }
        else{
            return Arrays.asList(0, antallForsvar);
        }


    }

    // simulerer tilfellet med 1 angripende terning og 1 forsvarende terning (begge kun 1 brikke igjen)
    public List<Integer> simuler1_1(int antallAngrep, int antallForsvar){
        // System.out.println("Simulerer 1 Angrep mot 1 Forsvar:");
        terning1.kastTerning();
        // System.out.println("Angrepskast: "+terning1);
        terning4.kastTerning();
        // System.out.println("Forsvarskast: "+terning4+"\n");
        if(terning1.getScore()>terning4.getScore()){
            return Arrays.asList(1, 0);
        }
        else{
            return Arrays.asList(0, 1);
        }
    }

    // condition-handling metode for å sikre riktige rekursive kall
    public List<Integer> simuler(int antallAngrep, int antallForsvar){
        // initialbetingelse 1 av 2, returner forsvar som seirende og returner antall brikker for hvert parti
        if(antallAngrep<=0){
            return Arrays.asList(0, antallForsvar);
        }
        // initialbetingelse 2 av 2, returner angrep som seirende og returner antall brikker for hvert parti
        if(antallForsvar<=0){
            return Arrays.asList(antallAngrep, 0);
        }

        // delegerer videre til metodene som simulerer "krigen" (terningkast) riktig i forhold til Risk sine regler.

        if(antallAngrep>=3){
            if(antallForsvar>=2){
                return simuler3_2(antallAngrep, antallForsvar);
            }
            else
                return simuler3_1(antallAngrep, antallForsvar);
        }
        else if(antallAngrep == 2){
            if(antallForsvar>=2){
                return simuler2_2(antallAngrep, antallForsvar);
            }
            else
                return simuler2_1(antallAngrep, antallForsvar);
        }
        else{
            if(antallForsvar>=2){
                return simuler1_2(antallAngrep, antallForsvar);
            }
            else
                return simuler1_1(antallAngrep, antallForsvar);
        }
    }

    public static void main(String[] args) {
        Risk risk = new Risk();
        risk.simulerKamp(1000000, 1, 1);
    }





}
