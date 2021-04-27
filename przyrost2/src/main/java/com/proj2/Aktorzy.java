package com.proj2;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Aktorzy {
    String PESELAktora;
    String imieAktora;
    String nazwiskoAktora;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(cascade = { CascadeType.ALL })
    private Set<Filmy> filmyiaktorzy = new HashSet<>();
    @OneToOne
    private Agenci agenci;

    public void setFilmyiaktorzy(Set<Filmy> filmyiaktorzy) {
        this.filmyiaktorzy = filmyiaktorzy;
    }

    public String getPESELAktora() {
        return PESELAktora;
    }

    public void setPESELAktora(String PESELAktora) {
        this.PESELAktora = PESELAktora;
    }


    public String getNazwiskoAktora() {
        return nazwiskoAktora;
    }

    public void setNazwiskoAktora(String nazwiskoAktora) {
        this.nazwiskoAktora = nazwiskoAktora;
    }

    public int getIdaktorzy() {
        return id;
    }

    public void setIdaktorzy(int idaktorzy) {
        this.id = idaktorzy;
    }

    public Set<Filmy> getFilmyiaktorzy() {
        return filmyiaktorzy;
    }

    public Agenci getAgenci() {
        return agenci;
    }

    public void setAgenci(Agenci agenci) {
        this.agenci = agenci;
    }
    public String getImieAktora() {
        return imieAktora;
    }

    public void setImieAktora(String imieAktora) {
        this.imieAktora = imieAktora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
