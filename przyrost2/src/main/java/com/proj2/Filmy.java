package com.proj2;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Filmy {
    String tytul;
    ZonedDateTime rok;
    String gatnek;
    String dlugosc;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(mappedBy = "filmyiaktorzy")
    private Set<Aktorzy> aktorzyifilmy = new HashSet<>();
    @ManyToMany(cascade = { CascadeType.ALL })
    private Set<Studia>  filmyistudia = new HashSet<>();

    public Set<Aktorzy> getAktorzyifilmy() {
        return aktorzyifilmy;
    }

    public void setAktorzyifilmy(Set<Aktorzy> aktorzyifilmy) {
        this.aktorzyifilmy = aktorzyifilmy;
    }

    public Set<Studia> getFilmyistudia() {
        return filmyistudia;
    }

    public void setFilmyistudia(Set<Studia> filmyistudia) {
        this.filmyistudia = filmyistudia;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }
    public ZonedDateTime getRok() {
        return rok;
    }

    public void setRok(ZonedDateTime rok) {
        this.rok = rok;
    }

    public String getGatnek() {
        return gatnek;
    }

    public void setGatnek(String gatnek) {
        this.gatnek = gatnek;
    }

    public String getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(String dlugosc) {
        this.dlugosc = dlugosc;
    }

    public int getIdfilm() {
        return id;
    }

    public void setIdfilm(int idfilm) {
        this.id = idfilm;
    }



}
