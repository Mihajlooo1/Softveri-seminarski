/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Utakmica extends AbstractDomainObject{
    
    private int utakmicaID;
    private String rezultat;
    private Date datum;
    private String stadion;
    private int kolo;
    private RasporedUtakmica raspored;
    private Klub domacin;
    private Klub gost;

    public Utakmica() {
    }

    public Utakmica(int utakmicaID, String rezultat, Date datum, String stadion, int kolo, RasporedUtakmica raspored, Klub domacin, Klub gost) {
        this.utakmicaID = utakmicaID;
        this.rezultat = rezultat;
        this.datum = datum;
        this.stadion = stadion;
        this.kolo = kolo;
        this.raspored = raspored;
        this.domacin = domacin;
        this.gost = gost;
    }

    public int getUtakmicaID() {
        return utakmicaID;
    }

    public void setUtakmicaID(int utakmicaID) {
        this.utakmicaID = utakmicaID;
    }

    public String getRezultat() {
        return rezultat;
    }

    public void setRezultat(String rezultat) {
        this.rezultat = rezultat;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getStadion() {
        return stadion;
    }

    public void setStadion(String stadion) {
        this.stadion = stadion;
    }

    public int getKolo() {
        return kolo;
    }

    public void setKolo(int kolo) {
        this.kolo = kolo;
    }

    public RasporedUtakmica getRaspored() {
        return raspored;
    }

    public void setRaspored(RasporedUtakmica raspored) {
        this.raspored = raspored;
    }

    public Klub getDomacin() {
        return domacin;
    }

    public void setDomacin(Klub domacin) {
        this.domacin = domacin;
    }

    public Klub getGost() {
        return gost;
    }

    public void setGost(Klub gost) {
        this.gost = gost;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utakmica other = (Utakmica) obj;
        if (this.utakmicaID != other.utakmicaID) {
            return false;
        }
        return Objects.equals(this.raspored, other.raspored);
    }


    
    @Override
    public String getQueryForAll() {
        return "Select * from utakmica u inner join rasporedutakmica r on u.rasporedID = r.rasporedID inner join klub kd on u.domacinID = kd.klubID inner join klub kg on u.gostID = kg.klubID inner join administrator a on a.adminID = r.adminID";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        List<AbstractDomainObject> list = new LinkedList<>();
        while(rs.next()){
            int adminID  = rs.getInt("adminID");
            String korisnickoIme = rs.getString("korisnickoIme");
            String sifra = rs.getString("sifra");
            String imeAdministratora = rs.getString("imeAdministratora");
            String prezimeAdministratora = rs.getString("prezimeAdministratora");
            
            Administrator administrator = new Administrator(adminID, korisnickoIme, sifra, imeAdministratora, prezimeAdministratora);
            
            int klubDomacinID = rs.getInt(12);
            String nazivDomacin = rs.getString(13);
            String gradDomacin = rs.getString(14);
            Klub domacin = new Klub(klubDomacinID, nazivDomacin, gradDomacin, new LinkedList<>());
            
            int klubGostID = rs.getInt(15);
            String nazivGost = rs.getString(16);
            String gradGost = rs.getString(17);
            Klub gost = new Klub(klubGostID, nazivGost, gradGost, new LinkedList<>());
            
            int utakmicaID = rs.getInt("utakmicaID");
            String rezultat = rs.getString("rezultat");
            Date datum = new Date(rs.getTimestamp("datum").getTime());
            String stadion = rs.getString("stadion");
            int kolo = rs.getInt("kolo");
            
            int rasporedID = rs.getInt("rasporedID");
            String sezona = rs.getString("sezona");
            
            RasporedUtakmica rasporedUtakmica = new RasporedUtakmica(rasporedID, sezona, administrator, new LinkedList<>());
            Utakmica utakmica = new Utakmica(utakmicaID, rezultat, datum, stadion, kolo, rasporedUtakmica, domacin, gost);
            
            list.add(utakmica);
        }
        rs.close();
        return list;
    }

    @Override
    public String getQueryForSearch(String parametar) {
        return "Select * from utakmica u inner join rasporedutakmica r on u.rasporedID = r.rasporedID inner join klub kd on u.domacinID = kd.klubID inner join klub kg on u.gostID = kg.klubID inner join administrator a on a.adminID = r.adminID WHERE u.rezultat like '%"+parametar+"%' or u.stadion like '%"+parametar+"%' or u.kolo like '%"+parametar+"%' or r.sezona like '%"+parametar+"%' or kd.naziv like '%"+parametar+"%' or kg.naziv like '%"+parametar+"%' or concat(a.imeAdministratora, ' ', a.prezimeAdministratora) like '%"+parametar+"%'";
    }

    @Override
    public String getQueryForInsert() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Insert into utakmica(rezultat, datum, stadion, kolo, rasporedID, domacinID, gostID) values ('"+rezultat+"', '"+simpleDateFormat.format(datum)+"', '"+stadion+"', "+kolo+", "+raspored.getRasporedID()+", "+domacin.getKlubID()+", "+gost.getKlubID()+")";
    }

    @Override
    public String getQueryForUpdate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Update utakmica set rezultat='"+rezultat+"', datum='"+simpleDateFormat.format(datum)+"', stadion='"+stadion+"', kolo="+kolo+", domacinID="+domacin.getKlubID()+", gostID="+gost.getKlubID()+", rasporedID = "+raspored.getRasporedID()+" where utakmicaID="+utakmicaID;
    }

    @Override
    public String getQueryForDelete() {
        return "Delete utakmica where utakmicaID = "+ utakmicaID;
    }

    @Override
    public boolean isAutoIncrement() {
        return true;
    }

    @Override
    public void setId(int id) {
        this.utakmicaID = id;
    }
    
    
}
