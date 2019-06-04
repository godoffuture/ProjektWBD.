package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;

public class Table_view_model {

    //Tabela Pracownicy
    private int ID_PRACOWNIK;
    private String IMIE;
    private String NAZWISKO;
    private String STANOWISKO;
    private String MIASTO;
    private String ULICA;
    private String NUMER;
    private String KONTO_BANKOWE;
    private String KOD_POCZTOWY;
    private String USERNAME;
    private String PASSWORD;

    //Tabela stacja
    private int ID_STACJA;
    private String NAZWA;
    private int GPS_X;
    private  int GPS_Y;
    private  String TYP_STACJI;
    private  String MODEL_STACJI;
    private  String BILETOMAT;
    private  String STREFA;
    private  int ID_ZTM;


    //tabela linia
    private   int ID_LINII;
    private  String NUMER_LINII;

    //tabela pojazd
    private int ID_POJAZD;
    private Date ROK_PRODUKCJI;
    private String PRODUCENT;
    private String NUMER_BOCZNY;
    private String TYP;
    private Date DATA_PRZEGLADU;
    private  int POJEMNOSC;
    private  String MODEL;
    private int ID_ZAJEZDNIA;


    public ObservableList<Table_view_model> getAllFromStacja(Connection connection){
        ObservableList<Table_view_model> listTableview = FXCollections.observableArrayList();
        String sql = "SELECT ID_STACJA,NAZWA,GPS_X," +
                "GPS_Y,TYP,TYP,MODEL,BILETOMAT,STREFA from STACJA order by ID_STACJA";
        Statement stmt;
        ResultSet rs;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Table_view_model tableview = new Table_view_model();
                tableview.ID_STACJA = rs.getInt(1);
                tableview.NAZWA = rs.getString(2);
                tableview.GPS_X = rs.getInt(3);
                tableview.GPS_Y = rs.getInt(4);
                tableview.TYP_STACJI = rs.getString(5);
                tableview.MODEL_STACJI = rs.getString(6);
                tableview.BILETOMAT = rs.getString(7);
                tableview.STREFA = rs.getString(8);

                listTableview.add(tableview);
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }

        return listTableview;
    }
    public ObservableList<Table_view_model> getAllFromPracownicy(Connection connection){
        ObservableList<Table_view_model> listTableview = FXCollections.observableArrayList();
        String sql = "SELECT ID_PRACOWNIK,IMIE,NAZWISKO,STANOWISKO,MIASTO,ULICA,NUMER," +
                "KONTO_BANKOWE,KOD_POCZTOWY from PRACOWNICY order by ID_PRACOWNIK";
        Statement stmt;
        ResultSet rs;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Table_view_model tableview = new Table_view_model();
                tableview.ID_PRACOWNIK = rs.getInt(1);
                tableview.IMIE = rs.getString(2);
                tableview.NAZWISKO = rs.getString(3);
                tableview.STANOWISKO = rs.getString(4);
                tableview.MIASTO = rs.getString(5);
                tableview.ULICA = rs.getString(6);
                tableview.NUMER = rs.getString(7);
                tableview.KONTO_BANKOWE = rs.getString(8);
                tableview.KOD_POCZTOWY = rs.getString(9);

                listTableview.add(tableview);
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }

        return listTableview;
    }

    public ObservableList<Table_view_model> getAllFromPojazd(Connection connection){
        ObservableList<Table_view_model> listTableview = FXCollections.observableArrayList();
        String sql = "SELECT ID_POJAZD,ROK_PRODUKCJI,PRODUCENT," +
                "NUMER_BOCZNY,TYP,DATA_PRZEGLADU,POJEMNOSC,MODEL from POJAZD order by ID_POJAZD";
        Statement stmt;
        ResultSet rs;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Table_view_model tableview = new Table_view_model();
                tableview.ID_POJAZD = rs.getInt(1);
                tableview.ROK_PRODUKCJI = rs.getDate(2);
                tableview.PRODUCENT = rs.getString(3);
                tableview.NUMER_BOCZNY = rs.getString(4);
                tableview.TYP = rs.getString(5);
                tableview.DATA_PRZEGLADU = rs.getDate(6);
                tableview.POJEMNOSC = rs.getInt(7);
                tableview.MODEL = rs.getString(8);

                listTableview.add(tableview);
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }

        return listTableview;
    }

    public ObservableList<Table_view_model> getFilteredBy(Connection connection, String table_name, String column_name, String value){
        ObservableList<Table_view_model> listTableview = FXCollections.observableArrayList();

        String sql1 = "SELECT * from "+table_name+" WHERE "+column_name+" like '"+value+"'";

        String sql2 = "SELECT * FROM STACJA WHERE stacja.id_stacja IN " +
                "(SELECT stacja_linia.id_stacja FROM stacja_linia WHERE stacja_linia.id_linii ="+getValueOfField(column_name)+")";

        String sql = table_name=="STACJA NA LINII" ? sql2 : sql1;


        Statement stmt;
        ResultSet rs;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Table_view_model tableview = new Table_view_model();

                switch(table_name) {
                    case "PRACOWNICY":
                        tableview.ID_PRACOWNIK = rs.getInt(1);
                        tableview.IMIE = rs.getString(2);
                        tableview.NAZWISKO = rs.getString(3);
                        tableview.STANOWISKO = rs.getString(4);
                        tableview.MIASTO = rs.getString(5);
                        tableview.ULICA = rs.getString(6);
                        tableview.NUMER = rs.getString(7);
                        tableview.KONTO_BANKOWE = rs.getString(9);
                        tableview.KOD_POCZTOWY = rs.getString(10);
                        break;
                    case "POJAZD":
                        tableview.ID_POJAZD = rs.getInt(1);
                        tableview.ROK_PRODUKCJI = rs.getDate(2);
                        tableview.PRODUCENT = rs.getString(3);
                        tableview.NUMER_BOCZNY = rs.getString(4);
                        tableview.TYP = rs.getString(5);
                        tableview.DATA_PRZEGLADU = rs.getDate(6);
                        tableview.POJEMNOSC = rs.getInt(7);
                        tableview.MODEL = rs.getString(8);
                        break;
                    case "STACJA":
                        tableview.ID_STACJA = rs.getInt(1);
                        tableview.NAZWA = rs.getString(2);
                        tableview.GPS_X = rs.getInt(3);
                        tableview.GPS_Y = rs.getInt(4);
                        tableview.TYP_STACJI = rs.getString(5);
                        tableview.MODEL_STACJI = rs.getString(6);
                        tableview.BILETOMAT = rs.getString(7);
                        tableview.STREFA = rs.getString(8);
                        break;
                    case "STACJA NA LINII":
                        tableview.ID_STACJA = rs.getInt(1);
                        tableview.NAZWA = rs.getString(2);
                        tableview.GPS_X = rs.getInt(3);
                        tableview.GPS_Y = rs.getInt(4);
                        tableview.TYP_STACJI = rs.getString(5);
                        tableview.MODEL_STACJI = rs.getString(6);
                        tableview.BILETOMAT = rs.getString(7);
                        tableview.STREFA = rs.getString(8);
                        break;
                    default:
                        // code block
                }

                listTableview.add(tableview);
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }

        return listTableview;
    }

    private int getValueOfField(String str){
        int value = 1;
        if(str=="N1")value=1;
        if(str=="1A")value=2;
        if(str=="1B")value=3;
        if(str=="2")value=4;
        return value;
    }


    public boolean access_check(Connection connection,String username,String password){
        String sql = "SELECT USERNAME,PASSWORD from PRACOWNICY where USERNAME like'"+username+"' and PASSWORD like '"+password+"'";
        Statement stmt;
        ResultSet rs;
        boolean check = false;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            check = rs.next();
        }catch (SQLException e){}
        return check;
    }

    public void view_profile(Connection connection,String username,String password){
        String sql = "SELECT ID_PRACOWNIK,IMIE,NAZWISKO,STANOWISKO,MIASTO,ULICA,NUMER," +
                "KONTO_BANKOWE,KOD_POCZTOWY,USERNAME,PASSWORD from PRACOWNICY WHERE USERNAME like'"+username+"' and PASSWORD like '"+password+"'";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Your Profile");
            alert.setContentText("Imie: " +rs.getNString("IMIE")+
                    "\nNazwisko: " +rs.getNString("NAZWISKO")+
                    "\nStanowisko: " +rs.getNString("STANOWISKO")+
                    "\nMiasto: " +rs.getNString("MIASTO")+
                    "\nUlica: " +rs.getNString("ULICA")+
                    "\nNumer: " +rs.getNString("NUMER")+
                    "\nKonto bankowe: " +rs.getNString("KONTO_BANKOWE")+
                    "\nKod Pocztowy: "+rs.getNString("KOD_POCZTOWY")+
                    "\nUsername: " +rs.getNString("USERNAME")+
                    "\nPassword: "+rs.getNString("PASSWORD"));
            alert.showAndWait();



        }catch (SQLException e){}
    }

    public String getJob_title(Connection connection,String username,String password){
        String sql = "SELECT STANOWISKO from PRACOWNICY where USERNAME like'"+username+"' and PASSWORD like '"+password+"'";
        Statement stmt;
        ResultSet rs;
        String job=null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();

            job = rs.getString(1);

        }catch (SQLException e){}
        return job;
    }

    public void profile_bar(Connection connection,String username,String password){
        String sql = "SELECT USERNAME,PASSWORD,IMIE,NAZWISKO,STANOWISKO,ID_PRACOWNIK from PRACOWNICY where USERNAME like'"+username+"' and PASSWORD like '"+password+"'";
        Statement stmt;
        ResultSet rs;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();

            USERNAME = rs.getNString("USERNAME");
            IMIE = rs.getNString("IMIE");
            NAZWISKO = rs.getNString("NAZWISKO");
            STANOWISKO = rs.getNString("STANOWISKO");
            ID_PRACOWNIK = rs.getInt("ID_PRACOWNIK");

        }catch (SQLException e){}

    }



    public void change_password(Connection connection,String username,String password,String new_password){
        String sql = "UPDATE PRACOWNICY SET PASSWORD = '"+new_password+"' WHERE USERNAME like'"+username+"' and PASSWORD like '"+password+"'";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();

        }catch (SQLException e){}
    }

    public void change_username(Connection connection,String username,String password,String new_username){
        String sql = "UPDATE PRACOWNICY SET USERNAME = '"+new_username+"' WHERE USERNAME like'"+username+"' and PASSWORD like '"+password+"'";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();

        }catch (SQLException e){}
    }

    public void insert_pracownik(Connection connection, String IMIE, String NAZWISKO, String STANOWISKO, String MIASTO, String ULICA,
             String NUMER, String KONTO_BANKOWE, String KOD_POCZTOWY, String USERNAME, String PASSWORD){

        String sql = "INSERT INTO PRACOWNICY (imie, nazwisko,stanowisko, miasto, ulica, numer, konto_bankowe,username,password)"+
        "VALUES ('"+IMIE+"','"+NAZWISKO+"','"+STANOWISKO+"','"+MIASTO+"','"+ULICA+"','"+NUMER+"','"+
                KONTO_BANKOWE+"','"+USERNAME+"','"+PASSWORD+"')";
        Statement stmt;
        ResultSet rs;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();

        }catch (SQLException e){}
    }

    public void edit_pracownik( Connection connection,  int ID_PRACOWNIK, String IMIE, String NAZWISKO, String STANOWISKO,
             String MIASTO,String ULICA, String NUMER, String KONTO_BANKOWE, String KOD_POCZTOWY){
        String sql = "UPDATE PRACOWNICY SET" +
                " IMIE = '"+IMIE+"'," +
                " NAZWISKO = '"+NAZWISKO+"'," +
                " STANOWISKO = '"+STANOWISKO+"'," +
                " MIASTO = '"+MIASTO+"'," +
                " ULICA = '"+ULICA+"'," +
                " NUMER = '"+NUMER+"'," +
                " KONTO_BANKOWE = '"+KONTO_BANKOWE+"'," +
                " KOD_POCZTOWY = '"+KOD_POCZTOWY+"'" +
                " WHERE ID_PRACOWNIK = "+ID_PRACOWNIK;
        Statement stmt;
        ResultSet rs;
        System.out.print(ID_PRACOWNIK);
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();

        }catch (SQLException e){}
    }

    public void delete_pracownik(Connection connection, int ID_PRACOWNIK){
        String sql = "DELETE FROM PRACOWNICY WHERE ID_PRACOWNIK = "+ID_PRACOWNIK;
        Statement stmt;
        ResultSet rs;
        System.out.print(ID_PRACOWNIK);
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();

        }catch (SQLException e){}
    }


    public int getID_STACJA() {
        return ID_STACJA;
    }

    public void setID_STACJA(int ID_STACJA) {
        this.ID_STACJA = ID_STACJA;
    }

    public String getNAZWA() {
        return NAZWA;
    }

    public void setNAZWA(String NAZWA) {
        this.NAZWA = NAZWA;
    }

    public int getGPS_X() {
        return GPS_X;
    }

    public void setGPS_X(int GPS_X) {
        this.GPS_X = GPS_X;
    }

    public int getGPS_Y() {
        return GPS_Y;
    }

    public void setGPS_Y(int GPS_Y) {
        this.GPS_Y = GPS_Y;
    }

    public String getTYP_STACJI() {
        return TYP_STACJI;
    }

    public void setTYP_STACJI(String TYP_STACJI) {
        this.TYP_STACJI = TYP_STACJI;
    }

    public String getMODEL_STACJI() {
        return MODEL_STACJI;
    }

    public void setMODEL_STACJI(String MODEL_STACJI) {
        this.MODEL_STACJI = MODEL_STACJI;
    }

    public String getBILETOMAT() {
        return BILETOMAT;
    }

    public void setBILETOMAT(String BILETOMAT) {
        this.BILETOMAT = BILETOMAT;
    }

    public String getSTREFA() {
        return STREFA;
    }

    public void setSTREFA(String STREFA) {
        this.STREFA = STREFA;
    }

    public int getID_ZTM() {
        return ID_ZTM;
    }

    public void setID_ZTM(int ID_ZTM) {
        this.ID_ZTM = ID_ZTM;
    }

    public int getID_LINII() {
        return ID_LINII;
    }

    public void setID_LINII(int ID_LINII) {
        this.ID_LINII = ID_LINII;
    }

    public String getNUMER_LINII() {
        return NUMER_LINII;
    }

    public void setNUMER_LINII(String NUMER_LINII) {
        this.NUMER_LINII = NUMER_LINII;
    }

    public int getID_PRACOWNIK() {
        return ID_PRACOWNIK;
    }

    public void setID_PRACOWNIK(int ID_PRACOWNIK) {
        this.ID_PRACOWNIK = ID_PRACOWNIK;
    }

    public String getIMIE() {
        return IMIE;
    }

    public void setIMIE(String IMIE) {
        this.IMIE = IMIE;
    }

    public String getNAZWISKO() {
        return NAZWISKO;
    }

    public void setNAZWISKO(String NAZWISKO) {
        this.NAZWISKO = NAZWISKO;
    }

    public String getSTANOWISKO() {
        return STANOWISKO;
    }

    public void setSTANOWISKO(String STANOWISKO) {
        this.STANOWISKO = STANOWISKO;
    }

    public String getMIASTO() {
        return MIASTO;
    }

    public void setMIASTO(String MIASTO) {
        this.MIASTO = MIASTO;
    }

    public String getULICA() {
        return ULICA;
    }

    public void setULICA(String ULICA) {
        this.ULICA = ULICA;
    }

    public String getNUMER() {
        return NUMER;
    }

    public void setNUMER(String NUMER) {
        this.NUMER = NUMER;
    }


    public String getKONTO_BANKOWE() {
        return KONTO_BANKOWE;
    }

    public void setKONTO_BANKOWE(String KONTO_BANKOWE) {
        this.KONTO_BANKOWE = KONTO_BANKOWE;
    }

    public String getKOD_POCZTOWY() {
        return KOD_POCZTOWY;
    }

    public void setKOD_POCZTOWY(String KOD_POCZTOWY) {
        this.KOD_POCZTOWY = KOD_POCZTOWY;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public int getID_POJAZD() {
        return ID_POJAZD;
    }

    public void setID_POJAZD(int ID_POJAZD) {
        this.ID_POJAZD = ID_POJAZD;
    }

    public Date getROK_PRODUKCJI() {
        return ROK_PRODUKCJI;
    }

    public void setROK_PRODUKCJI(Date ROK_PRODUKCJI) {
        this.ROK_PRODUKCJI = ROK_PRODUKCJI;
    }

    public String getPRODUCENT() {
        return PRODUCENT;
    }

    public void setPRODUCENT(String PRODUCENT) {
        this.PRODUCENT = PRODUCENT;
    }

    public String getNUMER_BOCZNY() {
        return NUMER_BOCZNY;
    }

    public void setNUMER_BOCZNY(String NUMER_BOCZNY) {
        this.NUMER_BOCZNY = NUMER_BOCZNY;
    }

    public String getTYP() {
        return TYP;
    }

    public void setTYP(String TYP) {
        this.TYP = TYP;
    }

    public Date getDATA_PRZEGLADU() {
        return DATA_PRZEGLADU;
    }

    public void setDATA_PRZEGLADU(Date DATA_PRZEGLADU) {
        this.DATA_PRZEGLADU = DATA_PRZEGLADU;
    }

    public int getPOJEMNOSC() {
        return POJEMNOSC;
    }

    public void setPOJEMNOSC(int POJEMNOSC) {
        this.POJEMNOSC = POJEMNOSC;
    }

    public String getMODEL() {
        return MODEL;
    }

    public void setMODEL(String MODEL) {
        this.MODEL = MODEL;
    }

    public int getID_ZAJEZDNIA() {
        return ID_ZAJEZDNIA;
    }

    public void setID_ZAJEZDNIA(int ID_ZAJEZDNIA) {
        this.ID_ZAJEZDNIA = ID_ZAJEZDNIA;
    }
}
