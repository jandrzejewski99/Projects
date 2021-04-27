package com.proj2;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Studia {

    String nazwa ;
    String rokZal;
    String adres;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idstudia;

    @ManyToMany(mappedBy = "filmyistudia")
    private Set<Filmy> studiaifilmy = new HashSet<>();

    @OneToOne
    private Prezesi Prezesi;
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getRokZał() {
        return rokZal;
    }

    public void setRokZał(String rokZał) {
        this.rokZal = rokZał;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getIdstudia() {
        return idstudia;
    }

    public void setIdstudia(int idstudia) {
        this.idstudia = idstudia;
    }

    public Set<Filmy> getStudiaifilmy() {
        return studiaifilmy;
    }

    public void setStudiaifilmy(Set<Filmy> aktorzyifilmy) {
        this.studiaifilmy = aktorzyifilmy;
    }

    public com.proj2.Prezesi getPrezesi() {
        return Prezesi;
    }

    public void setPrezesi(com.proj2.Prezesi prezesi) {
        Prezesi = prezesi;
    }

}
