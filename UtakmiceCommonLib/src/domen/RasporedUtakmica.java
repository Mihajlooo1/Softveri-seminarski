/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RasporedUtakmica extends AbstractDomainObject{

    private int rasporedID;
    private String sezona;
    private Administrator administrator;
    private List<Utakmica> utakmice;

    public RasporedUtakmica() {
    }

    public RasporedUtakmica(int rasporedID, String sezona, Administrator administrator, List<Utakmica> utakmice) {
        this.rasporedID = rasporedID;
        this.sezona = sezona;
        this.administrator = administrator;
        this.utakmice = utakmice;
    }

    public int getRasporedID() {
        return rasporedID;
    }

    public void setRasporedID(int rasporedID) {
        this.rasporedID = rasporedID;
    }

    public String getSezona() {
        return sezona;
    }

    public void setSezona(String sezona) {
        this.sezona = sezona;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public List<Utakmica> getUtakmice() {
        return utakmice;
    }

    public void setUtakmice(List<Utakmica> utakmice) {
        this.utakmice = utakmice;
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
        final RasporedUtakmica other = (RasporedUtakmica) obj;
        return this.rasporedID == other.rasporedID;
    }
    
    
    
    @Override
    public String getQueryForAll() {
        return "SELECT * FROM rasporedutakmica r INNER JOIN utakmica u ON r.rasporedID = u.rasporedID INNER JOIN klub kd ON u.domacinID = kd.klubID INNER JOIN klub kg ON u.gostID = kg.klubID INNER JOIN administrator a ON r.adminID = a.adminID";
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
            Klub gost = new Klub(klubGostID, nazivGost, gradGost, new LinkedList<StatistikaKluba>());
            
            int utakmicaID = rs.getInt("utakmicaID");
            String rezultat = rs.getString("rezultat");
            Date datum = new Date(rs.getTimestamp("datum").getTime());
            String stadion = rs.getString("stadion");
            int kolo = rs.getInt("kolo");
            
            int rasporedID = rs.getInt("rasporedID");
            String sezona = rs.getString("sezona");
            
            RasporedUtakmica rasporedUtakmica = new RasporedUtakmica(rasporedID, sezona, administrator, new LinkedList<>());
            Utakmica utakmica = new Utakmica(utakmicaID, rezultat, datum, stadion, kolo, rasporedUtakmica, domacin, gost);
            
            rasporedUtakmica.getUtakmice().add(utakmica);
            
            if(list.contains(rasporedUtakmica)){
                int indexOf = list.indexOf(rasporedUtakmica);
                RasporedUtakmica rasporedUtakmicaIzListe = (RasporedUtakmica) list.get(indexOf);
                
                utakmica.setRaspored(rasporedUtakmicaIzListe);
                rasporedUtakmicaIzListe.getUtakmice().add(utakmica);
            }else{
                list.add(rasporedUtakmica);
            }
        }
        rs.close();
        return list;
    }

    @Override
    public String getQueryForSearch(String parametar) {
        return "SELECT * FROM rasporedutakmica r INNER JOIN utakmica u ON r.rasporedID = u.rasporedID INNER JOIN klub kd ON u.domacinID = kd.klubID INNER JOIN klub kg ON u.gostID = kg.klubID INNER JOIN administrator a ON r.adminID = a.adminID where concat(a.imeAdministratora, ' ', a.prezimeAdministratora) like '%"+parametar+"%' or r.sezona like '%"+parametar+"%'";
    }

    @Override
    public String getQueryForInsert() {
        return "Insert into rasporedutakmica(sezona,adminID) values ('"+sezona+"', "+administrator.getAdminID()+")";
    }

    @Override
    public String getQueryForUpdate() {
        return "Update rasporedutakmica set sezona='"+sezona+"' where rasporedID = "+ rasporedID;
    }

    @Override
    public String getQueryForDelete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isAutoIncrement() {
        return true;
    }

    @Override
    public void setId(int id) {
        this.rasporedID = id;
    }
    
}
