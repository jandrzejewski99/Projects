SET LANGUAGE polski;
GO


----------------------------------
DROP TABLE IF EXISTS Sprzeda¿;
DROP TABLE IF EXISTS Oferowanie;
DROP TABLE IF EXISTS Posiadanie;
DROP TABLE IF EXISTS Specjalizacja;
DROP TABLE IF EXISTS Ciê¿arowy;
DROP TABLE IF EXISTS Osobowy;
DROP TABLE IF EXISTS Samochód;
DROP TABLE IF EXISTS Model;
DROP TABLE IF EXISTS Dodat_wyp;
DROP TABLE IF EXISTS Klient;
DROP TABLE IF EXISTS Dealer;
DROP TABLE IF EXISTS Marka;
DROP TABLE IF EXISTS Typ_silnika;
 ----------------------------------------


 CREATE TABLE Marka
(
nazwa_marka        VARCHAR(15) NOT NULL ,
rok_za³            DATE NOT NULL,
    CONSTRAINT PK_MARKA PRIMARY KEY (nazwa_marka)
);

 
CREATE TABLE Typ_silnika (
id_silnik                INT NOT NULL,
rodzaj_paliwa    VARCHAR(20) NOT NULL,
opis            VARCHAR(40) NOT NULL,
    CONSTRAINT PK_SILNIK PRIMARY KEY (id_silnik)
);


CREATE TABLE Model( 
id_model        INT  NOT NULL,
rok_wprowadzenia DATE NOT NULL,
nazwa            VARCHAR(20)NOT NULL, 
model_orygina³    INT ,  
marka_nazwa        VARCHAR(15) NOT NULL ,
Typ        VARCHAR(15) NOT NULL,
	CONSTRAINT PK_MODELp PRIMARY KEY (id_model),
	CONSTRAINT FK_MODELf FOREIGN KEY (marka_nazwa)
    REFERENCES Marka(nazwa_marka),
    CONSTRAINT UC_Person UNIQUE (model_orygina³)
); 


CREATE TABLE Osobowy 
(
model_id        INT,
poj_bag INT NOT NULL,
liczba_miejsc INT NOT NULL,
    CONSTRAINT PK_OSOBOWY PRIMARY KEY (model_id),
    CONSTRAINT FK_OSOBOWY FOREIGN KEY (model_id)
    REFERENCES Model(id_model)
);


CREATE TABLE Ciê¿arowy 
(
model_id  INT ,
³adownoœæ INT NOT NULL,
    CONSTRAINT PK_CIÊ¯AROWY PRIMARY KEY (model_id),
    CONSTRAINT FK_CIÊ¯AROWY FOREIGN KEY (model_id)
    REFERENCES Model(id_model)
);


CREATE TABLE Posiadanie 
(
idModelu    INT ,
idSilnika    INT,
    CONSTRAINT PK_POSIADANIEp PRIMARY KEY (idModelu, idSilnika),
    CONSTRAINT FK_POSIADANIEf1 FOREIGN KEY (idModelu)
    REFERENCES Model(id_model),
    CONSTRAINT FK_POSIADANIEf2 FOREIGN KEY (idSilnika)
    REFERENCES Typ_silnika(id_silnik)
);


CREATE TABLE Dealer (
nazwa_dealer    VARCHAR(20) NOT NULL ,
adres            VARCHAR(30) NOT NULL,
	CONSTRAINT PK_DEALER PRIMARY KEY (nazwa_dealer)
);

CREATE TABLE Specjalizacja(
    Model_id    INT ,
    Dealer_nazwa    VARCHAR(20) ,
	CONSTRAINT PK_SPECJALIZACJA  PRIMARY KEY (Model_id, Dealer_nazwa),
    CONSTRAINT FK_SPECJALIZACJA1 FOREIGN KEY (Model_id)
    REFERENCES Model(id_model),
    CONSTRAINT FK_SPECJALIZACJA2 FOREIGN KEY (Dealer_nazwa)
    REFERENCES Dealer(nazwa_dealer)
);

 

CREATE TABLE Samochód (
VIN                CHAR(17) NOT NULL,
przebieg        INT NOT NULL,
skrzynia_biegów VARCHAR(20) NOT NULL,
kraj            VARCHAR(20) NOT NULL,
rok_prod        DATE NOT NULL,
Model_id        INT  ,
Typ_silnika_id    INT ,
Dealer_nazwa    VARCHAR(20) ,
 CONSTRAINT PK_SAMOCHÓD  PRIMARY KEY (VIN),
 CONSTRAINT FK_SAMOCHÓD1 FOREIGN KEY (Model_id)
    REFERENCES Model(id_model),
    CONSTRAINT FK_SAMOCHÓD2 FOREIGN KEY (Typ_silnika_id)
    REFERENCES Typ_silnika(id_silnik),
    CONSTRAINT FK_SAMOCHÓD3 FOREIGN KEY (Dealer_nazwa)
    REFERENCES Dealer(nazwa_dealer)
);

CREATE TABLE Dodat_wyp (
nazwa_dodat_wyp VARCHAR(20) NOT NULL  ,
	CONSTRAINT PK_DODAT_WYP PRIMARY KEY (nazwa_dodat_wyp)
);

CREATE TABLE Oferowanie (
Samochód_VIN    CHAR(17),
Dodat_wyp_nazwa VARCHAR(20),
	CONSTRAINT PK_OFEROWSANIE PRIMARY KEY (Samochód_VIN,Dodat_wyp_nazwa),
    CONSTRAINT FK_OFEROWSANIE1 FOREIGN KEY (Samochód_VIN)
    REFERENCES  Samochód(VIN),
    CONSTRAINT FK_OFEROWSANIE2 FOREIGN KEY (Dodat_wyp_nazwa)
    REFERENCES  Dodat_wyp(nazwa_dodat_wyp)
);

CREATE TABLE Klient (
id_klient INT NOT NULL,
imie VARCHAR (20) NOT NULL,
nazwisko VARCHAR(20) NOT NULL CONSTRAINT ck_kli_nazw CHECK (nazwisko LIKE '[A-Z]%'),
nr_tel CHAR(9) NOT NULL,
	CONSTRAINT PK_KLIENT PRIMARY KEY (id_klient)
);

CREATE TABLE Sprzeda¿ (
dataa DATE NOT NULL,
cena MONEY NOT NULL,
Klient_id    INT ,
Samochód_VIN    CHAR(17),
Dealer_nazwa    VARCHAR(20) ,
	CONSTRAINT PK_SPRZEDA¯ PRIMARY KEY (Samochód_VIN,Dealer_nazwa,dataa,Klient_id),
	CONSTRAINT FK_SPRZEDA¯1 FOREIGN KEY (Klient_id)
	REFERENCES Klient(id_klient) ,
	CONSTRAINT FK_SPRZEDA¯2 FOREIGN KEY (Samochód_VIN)
	REFERENCES Samochód(VIN) ,
	CONSTRAINT FK_SPRZEDA¯3 FOREIGN KEY (Dealer_nazwa)
	REFERENCES Dealer(nazwa_dealer)
); 

INSERT INTO Marka VALUES
('BMW', '1916'),
('Volkswagen ', '1938'),
('Fiat', '1899');


INSERT INTO Typ_silnika VALUES
(1, 'benzyna', 'silnik V8'),
(2, 'LPG', 'silnik V4 -generacji'),
(3, 'benzyna', 'silnik 4-t³okowy');


INSERT INTO Model VALUES
(1,'1999', 'C', 1, 'BMW','osobowy' ),
(2,'2000', 'ADD', 2, 'Fiat', 'ciê¿arowy'),
(3,'2005', 'BA', 3, 'Volkswagen','osobowy' );
 

INSERT INTO Osobowy VALUES
(1,21,5),
(3,20,4);


INSERT INTO Ciê¿arowy VALUES
(2,30);

 
INSERT INTO Posiadanie VALUES
(1,2),
(2,3),
(3,1);

 
INSERT INTO Dealer VALUES
( 'drutex', 'warszawa'),
( 'sellex', 'Bydgoszcz'),
( 'dexet', 'Poznañ');


INSERT INTO Specjalizacja VALUES
(1, 'sellex'),
(2, 'dexet'),
(3 , 'sellex');


INSERT INTO Samochód VALUES
('13561231232344445', 12, 'manualna', 'Polska' , '2000', 1,2,'drutex'),
('13561231232432324', 14000, 'automatyczna', 'Niemcy' , '2010', 2,3,'sellex'),
('14324242345344445', 1000004, 'automatyczna', 'Anglia' , '1996', 3,1,'sellex');


INSERT INTO Dodat_wyp VALUES
('Elektryczne szyby'),
('Klimatyzacja'),
('Kamera cofania');

 
INSERT INTO Oferowanie VALUES
('14324242345344445', 'Elektryczne szyby'),
('13561231232432324', 'Kamera cofania'),
('13561231232432324', 'Klimatyzacja');

 
INSERT INTO Klient VALUES
(1, 'Aleksandra', 'Kot', '234556039'),
(2, 'Neko', 'Chmurka', '987678907'),
(3, 'Joe', 'Nesbo', '234556039');

 
INSERT INTO Sprzeda¿ VALUES
('2020', '15000', 1, '13561231232432324','drutex'),
('2018', '10000', 2, '13561231232432324','dexet'),
('2019', '50000', 3, '14324242345344445','dexet');


Select* From Sprzeda¿;
Select* From Oferowanie;
Select* From Posiadanie;
Select* From Specjalizacja;
Select* From Osobowy;
Select* From Ciê¿arowy;
Select* From Samochód;
Select* From Model;
Select* From Dodat_wyp;
Select* From Klient;
Select* From Dealer;
Select* From Marka;
Select* From Typ_silnika;