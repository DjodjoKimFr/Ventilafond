package com.djodjokim.ventilafond.data;

public class Patient implements IPatient {

    public int taillePatient;
    public double genreConstante;
    public double poidsIdealTheorique;
    public double volumeCourant6mL;
    public double volumeCourant8mL;


    public int getTaillePatient(){
        return taillePatient;
    }
    public double getGenreConstante(){
        return genreConstante;
    }

    public void setTaillePatient(int taillePatient) {
        this.taillePatient = taillePatient;
    }

    public void setGenreConstante(double genreConstante) {
        this.genreConstante = genreConstante;
    }




    public static double calculPoidsIdealTheorique(int taillePatient, double genreConstante){
    double poidsIdealTheorique = 0.0;
        return poidsIdealTheorique = genreConstante + 0.91 * taillePatient - 0.91 * 152.4;
    }

    public static double calculVentilation6mL(double poidsIdealTheorique){
    double volumeCourant6mL = 0;
        return volumeCourant6mL = 6 * poidsIdealTheorique;
    }

    public static double calculVentilation8mL(double poidsIdealTheorique){
        double volumeCourant8mL = 0;
        return volumeCourant8mL = 8 * poidsIdealTheorique;
    }

    public double getPoidsIdealTheorique(){
        return poidsIdealTheorique;
    }
    public double getVolumeCourant6mL(){
        return volumeCourant6mL;
    }
    public double getVolumeCourant8mL(){
        return volumeCourant8mL;
    }


}
