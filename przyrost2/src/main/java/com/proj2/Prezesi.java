package com.proj2;

import javax.persistence.*;

@Entity
public class Prezesi {

    String PESELPrezesa;
    String imiePrezesa;
    String nazwiskoPrezesa;
    int pensja;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idprezesa;

    @OneToOne
    private Studia studia;

    public String getPESELPrezesa() {
        return PESELPrezesa;
    }

    public void setPESELPrezesa(String PESELPrezesa) {
        this.PESELPrezesa = PESELPrezesa;
    }

    public String getimiePrezesa() {
        return imiePrezesa;
    }

    public void setimiePrezesa(String imię_prezesa) {
        this.imiePrezesa = imię_prezesa;
    }

    public String getNazwiskoPrezesa() {
        return nazwiskoPrezesa;
    }

    public void setNazwiskoPrezesa(String nazwiskoPrezesa) {
        this.nazwiskoPrezesa = nazwiskoPrezesa;
    }

    public int getPensja() { return pensja; }

    public void setPensja(int pensja) { this.pensja = pensja; }

    public int getIdprezesa() {
        return idprezesa;
    }

    public void setIdprezesa(int idprezesa) {
        this.idprezesa = idprezesa;
    }

    public Studia getStudia() {
        return studia;
    }

    public void setStudia(Studia studia) {
        this.studia = studia;
    }

}
