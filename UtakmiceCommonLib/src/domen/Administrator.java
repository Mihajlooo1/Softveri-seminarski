/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class Administrator extends AbstractDomainObject{

    private int adminID;
    private String korisnickoIme;
    private String sifra;
    private String imeAdministratora;
    private String prezimeAdministratora;

    public Administrator() {
    }

    public Administrator(int adminID, String korisnickoIme, String sifra, String imeAdministratora, String prezimeAdministratora) {
        this.adminID = adminID;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.imeAdministratora = imeAdministratora;
        this.prezimeAdministratora = prezimeAdministratora;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getImeAdministratora() {
        return imeAdministratora;
    }

    public void setImeAdministratora(String imeAdministratora) {
        this.imeAdministratora = imeAdministratora;
    }

    public String getPrezimeAdministratora() {
        return prezimeAdministratora;
    }

    public void setPrezimeAdministratora(String prezimeAdministratora) {
        this.prezimeAdministratora = prezimeAdministratora;
    }


    @Override
    public String toString() {
        return imeAdministratora+" "+prezimeAdministratora;
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
        final Administrator other = (Administrator) obj;
        return this.adminID == other.adminID;
    }
    
    
    @Override
    public String getQueryForAll() {
        return "Select * from administrator";
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
            list.add(administrator);
        }
        rs.close();
        return list;
    }

    @Override
    public String getQueryForSearch(String parametar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getQueryForInsert() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getQueryForUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getQueryForDelete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
