 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Igrac extends AbstractDomainObject{

    private int igracID;
    private String ime;
    private String prezime;
    private String pozicija;
    private int brojDresa;
    private Klub klub;

    public Igrac() {
    }

    public Igrac(int igracID, String ime, String prezime, String pozicija, int brojDresa, Klub klub) {
        this.igracID = igracID;
        this.ime = ime;
        this.prezime = prezime;
        this.pozicija = pozicija;
        this.brojDresa = brojDresa;
        this.klub = klub;
    }

    public int getIgracID() {
        return igracID;
    }

    public void setIgracID(int igracID) {
        this.igracID = igracID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPozicija() {
        return pozicija;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    public int getBrojDresa() {
        return brojDresa;
    }

    public void setBrojDresa(int brojDresa) {
        this.brojDresa = brojDresa;
    }

    public Klub getKlub() {
        return klub;
    }

    public void setKlub(Klub klub) {
        this.klub = klub;
    }

    @Override
    public String toString() {
        return ime+" "+prezime+" - "+brojDresa;
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
        final Igrac other = (Igrac) obj;
        return this.igracID == other.igracID;
    }
    
    
    @Override
    public String getQueryForAll() {
        return "Select * from igrac inner join klub on  igrac.klubID = klub.klubID";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        List<AbstractDomainObject> list = new LinkedList<>();
        while(rs.next()){
            
            int klubID = rs.getInt("klubID");
            String naziv = rs.getString("naziv");
            String grad = rs.getString("grad");
            Klub klub = new Klub(klubID, naziv, grad, new LinkedList<>());
            
            int igracID = rs.getInt("igracID");
            String ime  = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String pozicija = rs.getString("pozicija");
            int brojDresa = rs.getInt("brojDresa");
            
            Igrac igrac = new Igrac(igracID, ime, prezime, pozicija, brojDresa, klub);
            list.add(igrac);
        }
        rs.close();
        return list;
    }

    @Override
    public String getQueryForSearch(String parametar) {
        return "Select * from igrac inner join klub on  igrac.klubID = klub.klubID where CONCAT(ime, ' ', prezime) like '%"+parametar+"%' or brojDresa like '%"+parametar+"%' or pozicija like '%"+parametar+"%' or naziv like '%"+parametar+"%'";
    }

    @Override
    public String getQueryForInsert() {
        return "Insert into igrac(ime,prezime,pozicija,brojDresa,klubID) values ('"+ime+"', '"+prezime+"', '"+pozicija+"', "+brojDresa+", "+klub.getKlubID()+")";
    }

    @Override
    public String getQueryForUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getQueryForDelete() {
        return "Delete from igrac where igracID="+igracID;
    }

    @Override
    public boolean isAutoIncrement() {
        return true;
    }

    @Override
    public void setId(int id) {
        this.igracID = id;
    }
    
}
