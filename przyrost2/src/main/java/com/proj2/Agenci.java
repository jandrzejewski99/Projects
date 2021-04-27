package com.proj2;

import javax.persistence.*;

@Entity
public class Agenci {

    String PESEL_agenta ;
    String imieAgenta;
    String nazwiskoAgenta;
    String doswiadczenie;
    String agencja;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idagenta;

    @OneToOne
    private Aktorzy aktorzy;

    public String getPESEL_agenta() {
        return PESEL_agenta;
    }

    public void setPESEL_agenta(String PESEL_agenta) {
        this.PESEL_agenta = PESEL_agenta;
    }

    public String getimieAgenta() {
        return imieAgenta;
    }

    public void setimieAgenta(String imię_agenta) {
        this.imieAgenta = imię_agenta;
    }

    public String getNazwiskoAgenta() {
        return nazwiskoAgenta;
    }

    public void setNazwiskoAgenta(String nazwiskoAgenta) {
        this.nazwiskoAgenta = nazwiskoAgenta;
    }

    public String getDoświadczenie() {
        return doswiadczenie;
    }

    public void setDoświadczenie(String doświadczenie) {
        this.doswiadczenie = doświadczenie;
    }

    public String getAgencja() {
        return agencja;
    }

    public void setAgencja(String agencja) {
        this.agencja = agencja;
    }

    public int getIdagenta() {
        return idagenta;
    }

    public void setIdagenta(int idagenta) {
        this.idagenta = idagenta;
    }

    public Aktorzy getAktorzy() {
        return aktorzy;
    }

    public void setAktorzy(Aktorzy aktorzy) {
        this.aktorzy = aktorzy;
    }

}
