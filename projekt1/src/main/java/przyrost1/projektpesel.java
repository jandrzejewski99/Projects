package przyrost1;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Set;


class Number  {
    int bol = 0;
    String Pesel;
    String Peseltest;
    List<String> lista = new ArrayList<String>();
    List<String> lista2 = new ArrayList<String>();

    public Number() {
        // TODO Auto-generated constructor stub
    }
    public Number( String Pesel )
    {
        this.Pesel=Pesel;
    }
    void Print () {
        System.out.println("podaj Pesel: ");
        System.out.println("");
    }
    boolean Calculate(String[] tab,int sum) {
        sum = 0;
        String[] temp = tab;

        Integer a = Integer.parseInt(temp[0]);
        Integer b = Integer.parseInt(temp[1]);
        Integer c = Integer.parseInt(temp[2]);
        Integer d = Integer.parseInt(temp[3]);
        Integer e = Integer.parseInt(temp[4]);
        Integer f = Integer.parseInt(temp[5]);
        Integer g = Integer.parseInt(temp[6]);
        Integer h = Integer.parseInt(temp[7]);
        Integer i = Integer.parseInt(temp[8]);
        Integer j = Integer.parseInt(temp[9]);
        int If = 9*a+7*b+3*c+1*d+9*e+7*f+3*g+1*h+9*i+7*j;
        int Div = If% 10;
        if( Div == Integer.parseInt(temp[10])) {
            return true;
        }else {
            return false;
        }

    }
   public boolean CheckPeseltest(String PeselT)
   {
  boolean sprawdzenie= false;
       int aaa = PeselT.length();
       if (aaa == 11)
       {
           sprawdzenie = true;
       }else
       {
           sprawdzenie = false;
       }
       return sprawdzenie;
   }

    void CheckPesel () {
        int ToCheck = 0;
        String[] Check = null;
        Scanner scan = new Scanner(System.in);
        while(bol==0) {
            Pesel = scan.nextLine();
            System.out.println("");
            int aaa = Pesel.length();
            while (aaa !=11) {
                System.out.println("Podany pesel ma nieprawidłową długość. Podaj Pesel jeszcze raz:  ");
                Pesel= scan.nextLine();
                lista2.add(String.valueOf(Pesel));

                System.out.println("");
                aaa = Pesel.length();
            }

            Check = Pesel.split("");
            int temp ;
            if (Calculate(Check,ToCheck)) {
                temp = 1;
            }else {
                temp = 0;
            }
            if (temp == 1 ) {
                bol = 1;
            }
            if (bol == 1) {
                System.out.println("Pesel: "+Pesel);
            }else
            {
                System.out.println("Podany pesel jest błędny");
                System.out.println("podaj Pesel: ");
            }
        }
        bol = 0;
    }
    public boolean ToCheckIfCorrect(String Peseltest) {
        boolean ToCheck= false;
        String Check[] = null;
        Check = Peseltest.split("");
        Integer a = Integer.parseInt(Check[0]);
        Integer b = Integer.parseInt(Check[1]);
        Integer c = Integer.parseInt(Check[2]);
        Integer d = Integer.parseInt(Check[3]);
        Integer e = Integer.parseInt(Check[4]);
        Integer f = Integer.parseInt(Check[5]);
        Integer g = Integer.parseInt(Check[6]);
        Integer h = Integer.parseInt(Check[7]);
        Integer i = Integer.parseInt(Check[8]);
        Integer j = Integer.parseInt(Check[9]);
        int sum= 9*a+7*b+3*c+1*d+9*e+7*f+3*g+1*h+9*i+7*j;
        int Div = sum%10;
        if (Div == Integer.parseInt(Check[10]) ) {
            ToCheck=true;
        }
        return ToCheck;
    }

    void list() {

        lista.add(String.valueOf(Pesel));
        //System.out.println(lista);
    }
    void viewlist()
    {
        System.out.println(lista);
    }
    void strumien()
    {
        Stream<String> stream1=lista2.stream();
        Stream<String> streamFiltered=stream1.filter(n -> n.length()!=11);
        Set<String> filteredNames =streamFiltered.collect(Collectors.toSet());
        System.out.println(filteredNames);

    }
    void strumien2()
    {
        Stream<String> stream2=lista2.stream();
        Stream<String> streamFiltered=stream2.filter(n -> n.endsWith("9"));
    }
    void Save () {
        File file= new File("plik.txt");
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.newLine();
            bw.write(String.valueOf(Pesel));
            bw.newLine();
            bw.write("--------------------------------------------------");
            bw.newLine();
            bw.newLine();

            bw.close();
            System.out.println("");
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Zapisano do Pliku.");
    }
}
public class projektpesel {
    public static void main(String[] args) {
        Scanner scan2 = new Scanner(System.in);
        String runAgain;
        Number num = new Number();
        do {
            num.Print();
            num.CheckPesel();
            num.Save();
            num.list();
            System.out.println("Chcesz wykonać kolejny wpis? Wpisz literę t lub n.");
            runAgain = scan2.nextLine();
        } while (runAgain.equals("t"));
        System.out.println("Podsumowanie:");
        System.out.println("Pesele zapisane do pliku:");
        num.viewlist();
        System.out.println("Pesele o niepoprawcej długości");
        num.strumien();
        num.strumien2  ();

        System.out.println("Program zakońcył pracę.");
    }
}
