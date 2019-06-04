CREATE TABLE POJAZD(
  ID_POJAZD INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
  ROK_PRODUKCJI DATE,
  PRODUCENT Varchar2(100 ),
  NUMER_BOCZNY Varchar2(16 ),
  TYP Varchar2(30 ) NOT NULL,
  DATA_PRZEGLADU DATE NOT NULL,
  POJEMNOSC INTEGER,
  MODEL Varchar2(100 ) NOT NULL,
  ID_ZAJEZDNIA INTEGER
);

ALTER TABLE POJAZD ADD CONSTRAINT Unique_Identifier1 PRIMARY KEY (ID_POJAZD);

INSERT INTO POJAZD (rok_produkcji, producent, numer_boczny, typ, data_przegladu, pojemnosc, model)
VALUES(TO_DATE('03-02-1997', 'dd-mm-yyyy'), 'JELCZ', 'A139', 'AUTOBUS', TO_DATE('12-12-2012', 'dd-mm-yyyy'), 87, 'm121m' );
 
INSERT INTO POJAZD (rok_produkcji, producent, numer_boczny, typ, data_przegladu, pojemnosc, model)
VALUES(TO_DATE('03-02-2000', 'dd-mm-yyyy'), 'SOLARIS', 'A135', 'AUTOBUS', TO_DATE('12-12-2018', 'dd-mm-yyyy'), 82, 'urbino12' );
 
INSERT INTO POJAZD (rok_produkcji, producent, numer_boczny, typ, data_przegladu, pojemnosc, model)
VALUES(TO_DATE('12-04-2007', 'dd-mm-yyyy'), 'AUTOSAN', 'A136', 'AUTOBUS', TO_DATE('12-12-2019', 'dd-mm-yyyy'), 87, 'm121m' );
 
INSERT INTO POJAZD (rok_produkcji, producent, numer_boczny, typ, data_przegladu, pojemnosc, model)
VALUES(TO_DATE('03-07-2011', 'dd-mm-yyyy'), 'SOLARIS', 'A138', 'AUTOBUS', TO_DATE('12-12-2019', 'dd-mm-yyyy'), 107, 'urbino15' );



CREATE TABLE PRACOWNIK(
  ID_PRACOWNIK INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
  IMIE Varchar2(30 ) NOT NULL,
  NAZWISKO Varchar2(30 ) NOT NULL,
  STANOWISKO Varchar2(30) NOT NULL,
  MIASTO Varchar2(30 ) NOT NULL,
  ULICA Varchar2(50 ) NOT NULL,
  NUMER Varchar2(30 ) NOT NULL,
  KONTO_BANKOWE Varchar2(30 ) NOT NULL,
  KOD_POCZTOWY Varchar2(5 ),
  USERNAME Varchar2(30 ) NOT NULL,
  PASSWORD Varchar2(30 ) NOT NULL,
);

 ALTER TABLE PRACOWNICY ADD CONSTRAINT Unique_Identifier9 PRIMARY KEY (ID_PRACOWNIK);

 ALTER TABLE PRACOWNICY ADD CONSTRAINT Unique_Identifier1 PRIMARY KEY (USERNAME);


INSERT INTO PRACOWNICY (imie, nazwisko,stanowisko, miasto, ulica, numer, konto_bankowe,username,password)
VALUES ('JAN', 'SZYBKIBUS','czlonek zarzadu', 'Nuuk', 'Nowowiejska', '1', '00000006600000000','jan','jan');

INSERT INTO PRACOWNICY (imie, nazwisko,stanowisko, miasto, ulica, numer, konto_bankowe,username,password)
VALUES ('MAREK', 'WOLNYMECH','kierowca' 'Warszawa', 'Nowowiejska', '2', '00000000000000000','marek','marek');

INSERT INTO PRACOWNICY (imie, nazwisko,stanowisko, miasto, ulica, numer, konto_bankowe,username,password)
VALUES ('JACEK', 'KOWALSKI','czlonek zarzadu', 'Nuuk', 'Polna', '4', '00000000000000000','jacek','jacek');

INSERT INTO PRACOWNICY (imie, nazwisko,stanowisko, miasto, ulica, numer, konto_bankowe,username,password)
VALUES ('MICHAL', 'NAZARETACH','mechanik', 'Nuuk', 'Lazienkowska', '4', '00000000000000000','michal','michal');



CREATE TABLE STACJA(
  ID_STACJA INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
  NAZWA Varchar2(40),
  GPS_X NUMBER(10,0),
  GPS_Y NUMBER(10,0),
  TYP Varchar2(30 ) NOT NULL,
  MODEL Varchar2(30 ),
  BILETOMAT CHAR(1 ),
  STREFA Varchar2(2 ) NOT NULL,
  ID_ZTM INTEGER
);
 

ALTER TABLE STACJA ADD CONSTRAINT Unique_Identifier8 PRIMARY KEY (ID_STACJA);


INSERT INTO STACJA (TYP, MODEL, BILETOMAT, STREFA, NAZWA)
VALUES ('AUTOBUSOWY', 'SUPERWIATA', '1', '1', 'Nowowiejska');

INSERT INTO STACJA (TYP, MODEL, BILETOMAT, STREFA, NAZWA)
VALUES ('AUTOBUSOWY', 'TANISLUP', '0', '1', 'Polna');

INSERT INTO STACJA (TYP, MODEL, BILETOMAT, STREFA, NAZWA)
VALUES ('AUTOBUSOWY', 'SUPERWIATA', '0', '2', 'SIMR');

INSERT INTO STACJA (TYP, MODEL, BILETOMAT, STREFA, NAZWA)
VALUES ('AUTOBUSOWY', 'MEGALAWKA', '1', '1', 'Riviera');






