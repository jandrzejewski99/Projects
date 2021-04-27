#include <sys/wait.h>
#include <errno.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define DLUGOSC 100

//KOLORY
void red ()
{
        printf("\033[1;31m");
}
void  green()
{
        printf("\033[1;32m");
}
void  yellow()
{
        printf("\033[1;33m");
}
void  blue()
{
        printf("\033[1;34m");
}
void pink ()
{
        printf("\033[1;35m");
}
void end()
{
        printf("\033[0m");
}

//POMOC
void pomoc()
{       blue();
        printf("Autor: Jakub Andrzejewski");
        end();
        pink();
        printf("help: Wyswietlenie informacji o komendach\n");
        end();
        yellow();
        printf("cd: Przechodzenie do innego katalogu \n");
        end();
        green();
        printf("exit: Konczy dzialanie programu\n");
        end();
        red();
        printf("history: Wyswietla historje ostatnio uzytych polecen\n");
        end();
        printf("user: Wyœwietla aktualnie zalogowanego u¿ytkownika\n");
}

//FORK
void procc (char *xyz)
{
    int doczekania;

    pid_t pid = fork();


    if (pid == 0)
    {
        execlp (xyz, xyz, (char *)0);
        perror("Wyst¹pi³ nastêpuj¹cy b³¹d");
        printf("Wartoœæ  errno: %d\n", errno);
    }

    if (wait (&doczekania) < 0)
     {
        perror ("Z³e Polecenie! Czekaj 3 sekundy za kare!");
        sleep(3);
     }
}

//HISTORIA
//** Do funkcji historja pos³u¿y³em siê kodem ze strony:
//www.cmycode.com/2014/03/c-shell-simple-history-command.html?fbclid=IwAR2P-Ld3NKIbrhw5WCDTNEk73bUAeiFxmW9w9zaiX1Fldq5DZvr3c0E0OvA
//Jezeli by³ by to klopot PROSZE O NIE SPRAWDZENIE tej czêœci projektu(tylko funkcji/komendy "historia", aby nie pos¹dzono mnie o plagiad umieszczampowyej kod do
// strony. Ta czêœæ kodu nie musi byæ sprawdzana.
int history(char *hist[], int nurt)
{
int i = nurt;
int cyfra = 1;
do
        {
        if (hist[i])
                {
                printf("%4d   %s\n", cyfra, hist[i]);
                cyfra++;
                }
        i = (i + 1) % DLUGOSC;
        }
while (i != nurt);
return 0;
}

//FUNKCJA G£ÓWNA
int main()
{
char recom[100];
char sciezka[75];
char dom[100];
char uczen[20];
char *hist[DLUGOSC];
int i, nurt=0;
char *tekst;
char* change;
char uczenx[100];
const char* white_mark=" ";
getcwd(sciezka, sizeof(sciezka));
getlogin_r(uczen, sizeof(uczen));
getlogin_r(uczenx, sizeof(uczenx));

for (i = 0; i < DLUGOSC; i=i+1)
      {
      hist[i] = NULL;
      }
i=0;
while(i<1)
        {
        printf("{");
        green();
        printf("%s", uczen);
        end();
        printf(":");
        blue();
        printf("~%s", sciezka);
        end();
        printf("}$");

        fgets(recom,100,stdin);
        if(recom[strlen(recom) - 1] == '\n')
                {
                recom[strlen(recom) - 1]=0;
                }
        tekst = strtok(recom, white_mark);

        free(hist[nurt]);
        hist[nurt] = strdup(recom);
        nurt = (nurt + 1) % DLUGOSC;
        if(strcmp(tekst, "history") == 0)
                {
                history(hist, nurt);
                }

        else  if(strcmp(tekst,"cd")==0)
                {
                change = strtok(NULL, white_mark);
                chdir(change);
                getcwd(sciezka, 80);
                }

        else  if(strcmp(tekst,"user")==0)
                {
                printf("%s\n", uczenx);
                }


        else  if(strcmp(tekst,"help")==0)
                {
                pomoc();
                }
        else if(strcmp(tekst,"exit")==0)
                {
                return 0;
                }
        else
                {
                procc(tekst);
                }
        }
}


