package com.proj2;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.ZonedDateTime;
import java.util.logging.Level;

public class main {
    static void s1(Session session)
    {
        String hql = "FROM Filmy";
        Query query = session.createQuery(hql);
        List results = query.list();
        List<Filmy> var = (List<Filmy>)(List<?>) results;
        for (Filmy Filmy : var) {
            System.out.println(Filmy.getTytul());
            System.out.println(Filmy.getRok());
            System.out.println(Filmy.getGatnek());
            System.out.println(Filmy.getDlugosc());
            System.out.println();
        }
        System.out.println("1-------------------------------------------------");
    }

    static void s2(Session session)
    {
        String hql2 = "FROM Prezesi AS BOSS";
        Query query2 = session.createQuery(hql2);
        List results2 = query2.list();
        List<Prezesi> var2 = (List<Prezesi>)(List<?>) results2;
        for (Prezesi Prezesi : var2) {
            System.out.println(Prezesi.getPESELPrezesa());
            System.out.println(Prezesi.getimiePrezesa());
            System.out.println(Prezesi.getNazwiskoPrezesa());
            System.out.println(Prezesi.getStudia());
            System.out.println(Prezesi.getPensja());
        }
        System.out.println("2-------------------------------------------------");
    }

    static void s3(Session session)
    {
        String hql3 = "FROM Studia S WHERE S.id = 2 ";
        Query query3 = session.createQuery(hql3);
        Object results3 = query3.getSingleResult();
        Studia var3 = (Studia)results3;
        System.out.println(var3.getRokZał());
        System.out.println(var3.getNazwa());
        System.out.println(var3.getAdres());

        System.out.println("3-------------------------------------------------");
    }

    static void s4(Session session)
    {
        String hql4 = "FROM Aktorzy A WHERE A.imieAktora = 'Anna'";
        Query query4 = session.createQuery(hql4);
        Object results4 = query4.getSingleResult();
        Aktorzy var4 = (Aktorzy)results4;
        System.out.println(var4.getImieAktora());
        System.out.println(var4.getNazwiskoAktora());
        System.out.println(var4.getPESELAktora());
        System.out.println("4-------------------------------------------------");
    }

    static void s5(Session session)
    {
        String hql5 = "FROM Agenci An WHERE An.doswiadczenie = '15lat'";
        Query query5 = session.createQuery(hql5);
        Object results5 = query5.getSingleResult();
        Agenci var5 = (Agenci)results5;
        System.out.println(var5.getimieAgenta());
        System.out.println(var5.getNazwiskoAgenta());
        System.out.println(var5.getDoświadczenie());
        System.out.println(var5.getAgencja());
        System.out.println("5-------------------------------------------------");
    }

    static void s7(Session session)
    {
        String hql7 =" FROM Filmy";
        Query que7 =  session.createQuery(hql7);
        que7.setFirstResult(0);
        que7.setMaxResults(2);
        List results7 = que7.getResultList();
        List<Filmy> variable5 = (List<Filmy>) (List<?>) results7 ;
        for (Filmy Filmy : variable5) {
            System.out.println("Film :" + Filmy.getTytul());
            System.out.println("7---stronicowane----------------------------------------------");
        }
    }
   /* static void s6(Session session)
    {
        String hq6 = "SELECT count(e) FROM Filmy e";
        Query que6 =  session.createQuery(hq6);
        long result6 = (long)que6.getSingleResult();

        hq6 = "SELECT e FROM Filmy e";
        Query quer6 = session.createQuery(hq6);
        int pageSize = 10;
        int pagenr = 2;
        int pagenumber = (int) ((result6 / pageSize) + 1);
        if (pagenr >pagenumber) pagenr = pagenumber;
        quer6.setFirstResult((pagenr - 1) * pageSize);
        quer6.setMaxResults(pageSize);
        List results5 = quer6.getResultList();
        List<Filmy> variable5 = (List<Filmy>) (List<?>) results5 ;
        for (Filmy Filmy : variable5) {
            System.out.println("Film :" + Filmy.getTytul());
            System.out.println("6-------------------------------------------------");
        }
    }*/



    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        Configuration conf = new Configuration();
        conf.addAnnotatedClass(Aktorzy.class);
        conf.addAnnotatedClass(Filmy.class);
        conf.addAnnotatedClass(Studia.class);
        conf.addAnnotatedClass(Prezesi.class);
        conf.addAnnotatedClass(Agenci.class);

        SessionFactory factory = conf.configure().buildSessionFactory();

        Session session = factory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
//--------------------------------------------------------------------------------------
            Filmy film = new Filmy();
            film.setTytul("Osiedle");
            film.setRok(ZonedDateTime.now());
            film.setGatnek("A");
            film.setDlugosc("1h20min");

            Filmy film2 = new Filmy();
            film2.setTytul("Mumiian");
            film2.setRok(ZonedDateTime.now());
            film2.setGatnek("Horror");
            film2.setDlugosc("2h05min");

            Filmy film3 = new Filmy();
            film3.setTytul("Anne");
            film3.setRok(ZonedDateTime.now());
            film3.setGatnek("Komedia");
            film3.setDlugosc("1h35min");
//----------------------------------------------------------------------------------------
            Aktorzy aktor = new Aktorzy();
            aktor.setPESELAktora("989877788887");
            aktor.setImieAktora("Anna");
            aktor.setNazwiskoAktora("Kowalska");

            Aktorzy aktor2 = new Aktorzy();
            aktor2.setPESELAktora("446464464");
            aktor2.setImieAktora("Kamil");
            aktor2.setNazwiskoAktora("Nert");

            Aktorzy aktor3 = new Aktorzy();
            aktor3.setPESELAktora("845155666");
            aktor3.setImieAktora("Amelia");
            aktor3.setNazwiskoAktora("Lernt");
//----------------------------------------------------------------------------------------
            film.getAktorzyifilmy().add(aktor);
            aktor.getFilmyiaktorzy().add(film);

            film2.getAktorzyifilmy().add(aktor2);
            aktor2.getFilmyiaktorzy().add(film2);

            film3.getAktorzyifilmy().add(aktor3);
            aktor.getFilmyiaktorzy().add(film3);
//-----------------------------------------------------------------------------------------

            Agenci agent = new Agenci();
            agent.setPESEL_agenta("87464948497");
            agent.setimieAgenta("Bogdan");
            agent.setNazwiskoAgenta("Nowak");
            agent.setDoświadczenie("15lat");
            agent.setAgencja("PinkStar");
            agent.setAktorzy(aktor);

            Agenci agent2 = new Agenci();
            agent2.setPESEL_agenta("8461321236");
            agent2.setimieAgenta("Mariusz");
            agent2.setNazwiskoAgenta("Kwel");
            agent2.setDoświadczenie("16lat");
            agent2.setAgencja("Bella");
            agent2.setAktorzy(aktor3);

            Agenci agent3 = new Agenci();
            agent3.setPESEL_agenta("84944464236");
            agent3.setimieAgenta("Kamil");
            agent3.setNazwiskoAgenta("Bec");
            agent3.setDoświadczenie("18lat");
            agent3.setAgencja("Ello");
            agent3.setAktorzy(aktor2);

//-----------------------------------------------------------------------------------------
            Studia studio = new Studia();
            studio.setNazwa("PinkStar");
            studio.setRokZał("1982");
            studio.setAdres("ul.Luba 85 Lodz");


            Studia studio2 = new Studia();
            studio2.setNazwa("PinkStar");
            studio2.setRokZał("1952");
            studio2.setAdres("ul.Klimowska 85 Krakow");

            Studia studio3 = new Studia();
            studio3.setNazwa("PinkStar");
            studio3.setRokZał("1564");
            studio3.setAdres("ul.Morska 85 Warszawa");


//-----------------------------------------------------------------------------------------
            Prezesi prezes = new Prezesi();
            prezes.setPESELPrezesa("87554788791");
            prezes.setimiePrezesa("Jan");
            prezes.setNazwiskoPrezesa("Polak");
            prezes.setPensja(7200);
            prezes.setStudia(studio2);

            Prezesi prezes2 = new Prezesi();
            prezes2.setPESELPrezesa("56148464646");
            prezes2.setimiePrezesa("Imie");
            prezes2.setNazwiskoPrezesa("Nazwisko");
            prezes2.setPensja(1500);
            prezes2.setStudia(studio);

            Prezesi prezes3 = new Prezesi();
            prezes3.setPESELPrezesa("461414166464");
            prezes3.setimiePrezesa("Paulina");
            prezes3.setNazwiskoPrezesa("Mach");
            prezes3.setPensja(8300);
            prezes3.setStudia(studio3);
            studio.setPrezesi(prezes2);
            studio2.setPrezesi(prezes3);
            studio3.setPrezesi(prezes);
            aktor.setAgenci(agent2);
            aktor2.setAgenci(agent);
            aktor3.setAgenci(agent);

//-----------------------------------------------------------------------------------------
            studio.getStudiaifilmy().add(film);
            film.getFilmyistudia().add(studio);

            studio2.getStudiaifilmy().add(film2);
            film2.getFilmyistudia().add(studio2);

            studio3.getStudiaifilmy().add(film3);
            film.getFilmyistudia().add(studio3);

//-----------------------------------------------------------------------------------------

            session.save(film);
            session.save(film2);
            session.save(film3);
            session.save(aktor);
            session.save(aktor2);
            session.save(aktor3);
            session.save(agent);
            session.save(agent2);
            session.save(agent3);
            session.save(studio);
            session.save(studio2);
            session.save(studio3);
            session.save(prezes);
            session.save(prezes2);
            session.save(prezes3);
            tx.commit();

//-----------------------------------------------------------------------------------------
            s1(session);
            s2(session);
            s3(session);
            s4(session);
            s5(session);
            //s6(session);
            s7(session);

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
