package com.djodjokim.ventilafond.ui;

import androidx.lifecycle.ViewModel;

public class CalculViewModel extends ViewModel {

    public double poidsIdealTheorique;
    public double volumeCourant6mL;
    public double volumeCourant8mL;
    public int taillePatient;
    public double genreConstante;

    public void setGenreConstante(double genreConstante) {
        this.genreConstante = genreConstante;
    }

    public void setTaillePatient(int taillePatient) {
        this.taillePatient = taillePatient;
    }



    public double calculerPoidsIdeal(int taillePatient, double genreConstante) {
        return poidsIdealTheorique = genreConstante + 0.91 * taillePatient - 0.91 * 152.4;
    }

    public double calculerVolume6mL(double poidsIdealTheorique){
        return volumeCourant6mL = 6 * poidsIdealTheorique;
    }

    public double calculerVolume8mL(double poidsIdealTheorique){
        return volumeCourant8mL = 8 * poidsIdealTheorique;
    }

    public double getPoidsIdeal() {
        return poidsIdealTheorique;
    }

    public void setPoidsIdealTheorique(double poidsIdealTheorique) {
        this.poidsIdealTheorique = poidsIdealTheorique;
    }

    public void setVolumeCourant6mL(double volumeCourant6mL) {
        this.volumeCourant6mL = volumeCourant6mL;
    }

    public void setVolumeCourant8mL(double volumeCourant8mL) {
        this.volumeCourant8mL = volumeCourant8mL;
    }
}

